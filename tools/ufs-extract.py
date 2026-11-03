#!/usr/bin/env python3
"""
ufs-extract.py — unpack Land of Empires / UnityFS asset bundles and dump the
Lua 5.3 constant tables, without any third-party tooling.

    python3 tools/ufs-extract.py decompiled-source/resources/assets/AssetBundle out/

What it does
------------
1. Parses the UnityFS v8 container (LZ4HC / LZMA / raw blocks), including the
   two alignment flags that trip most extractors:
       0x40   the blocks-info table is padded to a 16-byte boundary
       0x200  the data blocks start on a 16-byte boundary
2. Splits the container into its nodes (CAB-xxx and CAB-xxx.resS).
3. Finds every Lua 5.3 chunk in the payload and reports:
       - the source path baked into the chunk
       - the constant table (strings, numbers, booleans)

Requires: pip install lz4

Output
------
out/<bundle-name>/node-<n>-<name>      the raw node payload
out/<bundle-name>/lua-<n>-<name>.json  the constant table for each chunk
"""

import argparse
import json
import os
import struct
import sys

try:
    import lz4.block as lz4
except ImportError:  # pragma: no cover
    sys.exit("this tool needs lz4:  pip install lz4")

try:
    import lzma
except ImportError:  # pragma: no cover
    lzma = None


# --------------------------------------------------------------------------
# UnityFS container
# --------------------------------------------------------------------------

def _cstr(buf, off):
    end = buf.index(0, off)
    return buf[off:end].decode("utf-8", "replace"), end + 1


def _decompress(chunk, comp, uncompressed):
    if comp == 0:
        return chunk
    if comp == 1:
        if lzma is None:
            raise ValueError("LZMA block but python has no lzma module")
        return lzma.decompress(chunk)
    if comp in (2, 3):
        return lz4.decompress(chunk, uncompressed_size=uncompressed)
    raise ValueError("unknown block compression %r" % comp)


def read_unityfs(path):
    """Return (list_of_nodes, metadata). node = (name, bytes)."""
    data = open(path, "rb").read()
    off = 0
    signature, off = _cstr(data, off)
    if not signature.startswith("UnityFS"):
        raise ValueError("not a UnityFS bundle: %s" % signature)

    version, = struct.unpack_from(">I", data, off)
    off += 4
    unity_version, off = _cstr(data, off)
    unity_revision, off = _cstr(data, off)
    size, cbis, ubis, flags = struct.unpack_from(">qIIi", data, off)
    off += 20

    if flags & 0x40:          # blocks-info padded to 16 bytes
        off = (off + 15) & ~15
    info = data[off:off + cbis]
    off += cbis
    if (flags & 0x3F) in (2, 3):
        info = lz4.decompress(info, uncompressed_size=ubis)

    p = 16                                    # skip the info hash
    nblocks, = struct.unpack_from(">I", info, p)
    p += 4
    blocks = []
    for _ in range(nblocks):
        usz, csz, bf = struct.unpack_from(">IIH", info, p)
        p += 10
        blocks.append((usz, csz, bf))

    nnodes, = struct.unpack_from(">I", info, p)
    p += 4
    nodes = []
    for _ in range(nnodes):
        noff, nsz, _flags = struct.unpack_from(">qqI", info, p)
        p += 20
        name, p = _cstr(info, p)
        nodes.append((name, noff, nsz))

    if flags & 0x200:         # the data blocks start on a 16-byte boundary
        off = (off + 15) & ~15

    payload = bytearray()
    for usz, csz, bf in blocks:
        chunk = data[off:off + csz]
        off += csz
        payload += _decompress(chunk, bf & 0x3F, usz)

    out = []
    for name, noff, nsz in nodes:
        out.append((name, bytes(payload[noff:noff + nsz])))

    meta = {
        "unity_version": unity_version,
        "unity_revision": unity_revision,
        "bundle_version": version,
        "flags": flags,
        "blocks": len(blocks),
        "nodes": len(nodes),
        "size": size,
    }
    return out, meta


# --------------------------------------------------------------------------
# Lua 5.3 constant tables
# --------------------------------------------------------------------------

LUA_HEADER = 33          # sig(4) ver(1) fmt(1) data(6) sizes(5) int(8) num(8) end(1)


def _lua_source(buf, off):
    """Return (source_path, offset_after_the_string)."""
    n = buf[off]
    off += 1
    raw = buf[off:off + n - 1]
    off += n
    return raw.decode("utf-8", "replace"), off


def _consts(buf, pos):
    """Parse a Lua constant table at `pos` (int32 count, then entries).

    Note on the layout: the game's chunks use a *compact* variant of the
    Lua 5.3 constant encoding — string lengths are a single byte (including
    the terminating NUL) rather than a full size_t. The parser below
    follows what the bytes actually do.
    """
    count, = struct.unpack_from("<I", buf, pos)
    if count > 100000:
        raise ValueError("implausible constant count")
    pos += 4
    out = []
    for _ in range(count):
        tag = buf[pos]
        pos += 1
        if tag == 0:
            out.append(None)
        elif tag == 1:
            out.append(bool(buf[pos]))
            pos += 1
        elif tag == 3:
            out.append(struct.unpack_from("<d", buf, pos)[0])
            pos += 8
        elif tag == 0x13:
            out.append(struct.unpack_from("<q", buf, pos)[0])
            pos += 8
        elif tag in (4, 0x14):
            n = buf[pos]
            pos += 1
            out.append(buf[pos:pos + n - 1].decode("utf-8", "replace"))
            pos += n - 1
        else:
            raise ValueError("unknown constant tag %r" % tag)
    return out, pos


def lua_chunks(buf):
    """Yield (offset, source_path, constants_or_None) for each Lua chunk."""
    pos = 0
    while True:
        i = buf.find(b"\x1bLua", pos)
        if i < 0:
            return
        pos = i + 4
        try:
            src, after = _lua_source(buf, i + LUA_HEADER)
        except Exception:
            continue
        consts = None
        # the constant table sits after the (stripped) code section; scan for
        # the first offset where a table parses and yields real identifiers
        for probe in range(after + 8, min(after + 400, len(buf) - 4)):
            try:
                table, _ = _consts(buf, probe)
            except Exception:
                continue
            strings = [c for c in table if isinstance(c, str)]
            if len(strings) < 3:
                continue
            ident = sum(
                1 for s in strings
                if s.replace("_", "").replace(".", "").replace("/", "").isalnum()
            )
            if ident / len(strings) >= 0.8:
                consts = table
                break
        yield i, src, consts


# --------------------------------------------------------------------------

def main():
    ap = argparse.ArgumentParser(description=__doc__,
                                 formatter_class=argparse.RawDescriptionHelpFormatter)
    ap.add_argument("bundle", help="a .bundle/.apk asset bundle file")
    ap.add_argument("outdir", nargs="?", default="out", help="where to write")
    ap.add_argument("--quiet", action="store_true")
    args = ap.parse_args()

    try:
        nodes, meta = read_unityfs(args.bundle)
    except Exception as exc:
        sys.exit("cannot read %s: %s" % (args.bundle, exc))

    base = os.path.splitext(os.path.basename(args.bundle))[0][:48]
    outdir = os.path.join(args.outdir, base)
    os.makedirs(outdir, exist_ok=True)

    if not args.quiet:
        print("%s  unity %s  flags %#x  %d nodes"
              % (base, meta["unity_version"], meta["flags"], meta["nodes"]))

    report = {"bundle": args.bundle, "meta": meta, "nodes": []}

    for index, (name, payload) in enumerate(nodes):
        safe = name.replace("/", "_")
        path = os.path.join(outdir, "node-%02d-%s" % (index, safe))
        with open(path, "wb") as fh:
            fh.write(payload)

        entry = {"node": name, "file": path, "bytes": len(payload), "lua": []}
        for offset, src, consts in lua_chunks(payload):
            entry["lua"].append({
                "offset": offset,
                "source": src,
                "constants": consts,
            })
            if not args.quiet:
                print("   %s  %s" % (name, src.split("\\")[-1]))
        report["nodes"].append(entry)

    with open(os.path.join(outdir, "report.json"), "w") as fh:
        json.dump(report, fh, indent=1)

    if not args.quiet:
        print("wrote %s" % outdir)


if __name__ == "__main__":
    main()
