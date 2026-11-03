#!/usr/bin/env python3
"""
extract-reference-art.py — pull the reference game's art straight out of the
bundles in this repository.

    pip install UnityPy Pillow
    python3 tools/extract-reference-art.py --out ref-art

This supersedes the earlier plan of uploading a folder to Google Drive: the
bundles under decompiled-source/resources/assets/AssetBundle decode here, in
the sandbox, with no upload and no desktop toolchain.

What it writes
--------------
out/<bundle>/<texture>.png      every Texture2D, decoded
out/<bundle>/<sprite>.png       every Sprite, cut out
out/palette.json                the dominant colours per texture
out/manifest.json               the full inventory of every bundle

Note on use: this is *reference* material — for palette, proportion and
silhouette. It is not shipped in the game.
"""

import argparse
import json
import os
import sys
from collections import Counter

try:
    import UnityPy
except ImportError:
    sys.exit("needs UnityPy:  pip install UnityPy")
try:
    from PIL import Image
except ImportError:
    sys.exit("needs Pillow:  pip install Pillow")


DEFAULT_ROOT = os.path.join(
    os.path.dirname(os.path.dirname(os.path.abspath(__file__))),
    "decompiled-source", "resources", "assets", "AssetBundle",
)


def dominant(img, count=14):
    """Quantise an image down to its dominant colours, as hex + percent."""
    small = img.convert("RGB")
    small = small.resize((min(small.width, 320), min(small.height, 320)))
    quantised = small.quantize(colors=count, method=Image.MEDIANCUT).convert("RGB")
    hist = Counter(quantised.getdata())
    total = sum(hist.values()) or 1
    return [("#%02x%02x%02x" % rgb, round(100.0 * n / total, 1))
            for rgb, n in hist.most_common(count)]


def main():
    ap = argparse.ArgumentParser(
        description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    ap.add_argument("--root", default=DEFAULT_ROOT, help="the AssetBundle directory")
    ap.add_argument("--out", default="ref-art", help="where to write")
    ap.add_argument("--palette-only", action="store_true",
                    help="skip writing PNGs, just the palette and manifest")
    args = ap.parse_args()

    if not os.path.isdir(args.root):
        sys.exit("no such directory: %s" % args.root)

    manifest = {}
    palette = {}

    bundles = []
    for dirpath, _dirs, files in os.walk(args.root):
        for name in sorted(files):
            bundles.append(os.path.join(dirpath, name))
    bundles.sort()

    for path in bundles:
        rel = os.path.relpath(path, args.root)
        safe = rel.replace("/", "__")
        try:
            env = UnityPy.load(path)
            objects = list(env.objects)
        except Exception as exc:
            manifest[rel] = {"error": str(exc)[:160]}
            print("  ! %-70s %s" % (rel[:70], str(exc)[:60]))
            continue

        counts = Counter(o.type.name for o in objects)
        entry = {"types": dict(counts), "sprites": [], "textures": []}
        outdir = os.path.join(args.out, safe)
        os.makedirs(outdir, exist_ok=True)

        for obj in objects:
            if obj.type.name == "Texture2D":
                try:
                    data = obj.read()
                    image = data.image
                except Exception:
                    continue
                entry["textures"].append(data.m_Name)
                palette[rel + "::" + data.m_Name] = dominant(image)
                if not args.palette_only:
                    image.save(os.path.join(outdir, "%s.png" % data.m_Name))
            elif obj.type.name == "Sprite":
                try:
                    data = obj.read()
                    image = data.image
                except Exception:
                    continue
                entry["sprites"].append(data.m_Name)
                if not args.palette_only:
                    image.save(os.path.join(outdir, "%s.png" % data.m_Name))
            elif obj.type.name == "TextAsset":
                try:
                    data = obj.read()
                    script = data.m_Script
                    if isinstance(script, str):
                        script = script.encode("latin-1", "replace")
                    with open(os.path.join(outdir, "%s.txt" % data.m_Name), "wb") as fh:
                        fh.write(script)
                except Exception:
                    pass

        manifest[rel] = entry
        print("  ✓ %-70s tex=%-4d spr=%-4d"
              % (rel[:70], counts.get("Texture2D", 0), counts.get("Sprite", 0)))

    os.makedirs(args.out, exist_ok=True)
    with open(os.path.join(args.out, "manifest.json"), "w") as fh:
        json.dump(manifest, fh, indent=1)
    with open(os.path.join(args.out, "palette.json"), "w") as fh:
        json.dump(palette, fh, indent=1)

    total_tex = sum(v.get("types", {}).get("Texture2D", 0) for v in manifest.values())
    total_spr = sum(v.get("types", {}).get("Sprite", 0) for v in manifest.values())
    print("\n%d bundles · %d textures · %d sprites → %s"
          % (len(manifest), total_tex, total_spr, args.out))


if __name__ == "__main__":
    main()
