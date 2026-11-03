#!/usr/bin/env python3
"""
Extract the SDK's wire schema from the decompiled Java.

Two products:

  endpoints[]  every Retrofit route: verb, path, Java method, parameters
  payloads{}   every class that carries @SerializedName fields: the JSON schema

The output is the source of truth for game-backend/ — the Express routes and
the Mongoose models are generated from this file, not from guesswork.

Usage:
    python3 tools/extract-java-schema.py                 # write docs/reference/schema/sdk-schema.json
    python3 tools/extract-java-schema.py --out out.json
    python3 tools/extract-java-schema.py --package com/bytedance.ttgame
"""

from __future__ import annotations

import argparse
import json
import re
from pathlib import Path

REPO = Path(__file__).resolve().parent.parent
SOURCES = REPO / "decompiled-source" / "sources"

# ---------------------------------------------------------------- endpoints

HTTP_VERB = re.compile(
    r'@(GET|POST|PUT|DELETE|PATCH|HEAD)\(\s*"([^"]+)"\s*\)\s*'
    r'(?:@[\w.]+(?:\([^)]*\))?\s*)*'
    r'(?:public\s+)?(?:static\s+)?'
    r'([\w.<>,\s\[\]]+?)\s+(\w+)\s*\('
)

# Route annotations sometimes carry a full URL (Twitter Kit).
HTTP_VERB_URL = re.compile(
    r'@(GET|POST|PUT|DELETE|PATCH|HEAD)\(\s*"https?://[^"]+"\s*\)\s*'
    r'(?:@[\w.]+(?:\([^)]*\))?\s*)*'
    r'(?:public\s+)?(?:static\s+)?'
    r'([\w.<>,\s\[\]]+?)\s+(\w+)\s*\('
)

RETROFIT_IMPORT = re.compile(r"import\s+([\w.]*retrofit2\.[\w.]+)\s*;")

# Parameter annotations: @Query("name") Type arg   |   @QueryMap Type arg
PARAM_SCALAR = re.compile(
    r'@(Query|Field|Path|Body|Header|Url)\(\s*"([^"]*)"\s*\)\s*'
    r'([\w.<>,\s\[\]?]+?)\s+(\w+)'
)
PARAM_MAP = re.compile(r"@(QueryMap|FieldMap|HeaderMap|PartMap)\s+([\w.<>,\s\[\]]+?)\s+(\w+)")
PARAM_FLAG = re.compile(r"@(AddCommonParam|ExtraInfo)\s+(?:final\s+)?([\w.<>?]+)\s+(\w+)")

# ---------------------------------------------------------------- payloads

CLASS_DECL = re.compile(
    r"^\s*(?:public\s+)?(?:final\s+|abstract\s+|static\s+)*"
    r"(class|interface|enum)\s+(\w+)"
)
FIELD = re.compile(
    r"^\s*(?:public|private|protected)\s+(?:static\s+)?(?:final\s+)?"
    r"([\w.<>,\s\[\]?]+?)\s+(\w+)\s*[;=]"
)
SERIALIZED = re.compile(r'@SerializedName\(\s*"([^"]*)"\s*\)')
# @SerializedName(SOME_CONST) — the value lives in another class.
SERIALIZED_CONST = re.compile(r"@SerializedName\(\s*([\w.]+)\s*\)")

# public static final String NAME = "literal";  /  public static final int NAME = -1;
CONST_STR = re.compile(r'public\s+static\s+final\s+String\s+(\w+)\s*=\s*"([^"]*)"\s*;')
CONST_INT = re.compile(r"public\s+static\s+final\s+int\s+(\w+)\s*=\s*(-?\d+)\s*;")
IMPORT = re.compile(r"^import\s+([\w.]+)\s*;")

# Packages that are game/SDK surface rather than third-party noise.
SKIP_PACKAGE_PREFIXES = (
    "com/facebook",
    "com/google/android",
    "com/tencent",
    "androidx/",
    "android/",
    "kotlin/",
    "kotlinx/",
    "okhttp3/",
    "okio/",
    "retrofit2/",
    "org/json",
    "com/squareup",
    "com/bumptech",
    "com/airbnb",
    "com/applovin",
    "com/facebook",
)


def java_package(path: Path) -> str:
    try:
        text = path.read_text(errors="ignore")
    except OSError:
        return ""
    m = re.search(r"^package\s+([\w.]+)\s*;", text, re.MULTILINE)
    return m.group(1).replace(".", "/") if m else ""


def rel_source(path: Path) -> str:
    try:
        return str(path.relative_to(SOURCES))
    except ValueError:
        return str(path)


def scan_endpoints() -> list[dict]:
    """Every Retrofit route declared anywhere in the dump."""
    out: list[dict] = []
    for path in sorted(SOURCES.rglob("*.java")):
        try:
            text = path.read_text(errors="ignore")
        except OSError:
            continue
        if not RETROFIT_IMPORT.search(text):
            continue
        pkg = java_package(path)
        if pkg.startswith(SKIP_PACKAGE_PREFIXES):
            continue

        for pattern, is_url in ((HTTP_VERB, False), (HTTP_VERB_URL, True)):
            for m in pattern.finditer(text):
                if is_url:
                    verb, ret_type, method = m.groups()
                    route = re.search(r'"([^"]+)"', m.group(0)).group(1)
                else:
                    verb, route, ret_type, method = m.groups()
                    if route.startswith("http"):
                        continue  # counted once, by the URL pass
                # The signature may span lines; grab the parameter list.
                tail = text[m.end(): m.end() + 900]
                close = tail.find(")")
                sig = tail[: close if close != -1 else 900]

                params: list[dict] = []
                for pm in PARAM_SCALAR.finditer(sig):
                    kind, name, ptype, arg = pm.groups()
                    params.append(
                        {"kind": kind.lower(), "name": name, "type": ptype.strip(), "arg": arg}
                    )
                for pm in PARAM_MAP.finditer(sig):
                    kind, ptype, arg = pm.groups()
                    params.append(
                        {"kind": kind.lower(), "name": "*", "type": ptype.strip(), "arg": arg}
                    )
                for pm in PARAM_FLAG.finditer(sig):
                    kind, ptype, arg = pm.groups()
                    params.append(
                        {"kind": kind.lower(), "name": "-", "type": ptype.strip(), "arg": arg}
                    )

                out.append(
                    {
                        "verb": verb,
                        "path": route,
                        "method": method,
                        "returns": " ".join(ret_type.split()),
                        "interface": f"{pkg}.{path.stem}" if pkg else path.stem,
                        "file": rel_source(path),
                        "params": params,
                    }
                )
    return out


def build_const_index() -> dict:
    """FQN -> {constant name: value}. Resolves @SerializedName(SOME_CONST)."""
    index: dict[str, dict] = {}
    for path in SOURCES.rglob("*.java"):
        try:
            text = path.read_text(errors="ignore")
        except OSError:
            continue
        pkg = java_package(path)
        if pkg.startswith(SKIP_PACKAGE_PREFIXES):
            continue
        class_name = path.stem
        for line in text.splitlines():
            cm = CLASS_DECL.match(line)
            if cm:
                class_name = cm.group(2)
                break
        key = f"{pkg}.{class_name}" if pkg else class_name
        bag = index.setdefault(key, {})
        for m in CONST_STR.finditer(text):
            bag[m.group(1)] = m.group(2)
        for m in CONST_INT.finditer(text):
            bag[m.group(1)] = int(m.group(2))
    return index


def build_error_codes() -> dict:
    """Class -> {NAME: int}. The SDK's error vocabulary."""
    out: dict = {}
    for path in SOURCES.rglob("*.java"):
        try:
            text = path.read_text(errors="ignore")
        except OSError:
            continue
        pkg = java_package(path)
        if pkg.startswith(SKIP_PACKAGE_PREFIXES):
            continue
        ints = {m.group(1): int(m.group(2)) for m in CONST_INT.finditer(text)}
        if not ints:
            continue
        class_name = path.stem
        for line in text.splitlines():
            cm = CLASS_DECL.match(line)
            if cm:
                class_name = cm.group(2)
                break
        key = f"{pkg}.{class_name}" if pkg else class_name
        out[key] = ints
    return out


def scan_payloads(const_index: dict) -> dict:
    """Every @SerializedName-carrying class: the JSON schema of the wire."""
    out: dict = {}
    for path in sorted(SOURCES.rglob("*.java")):
        try:
            text = path.read_text(errors="ignore")
        except OSError:
            continue
        pkg = java_package(path)
        if pkg.startswith(SKIP_PACKAGE_PREFIXES):
            continue
        if "@SerializedName" not in text:
            continue

        lines = text.splitlines()
        class_name = path.stem
        for line in lines:
            cm = CLASS_DECL.match(line)
            if cm:
                class_name = cm.group(2)
                break

        # Simple-name -> FQN, so `a.FIELD_AVATAR_URL` can be resolved.
        aliases: dict[str, str] = {}
        for line in lines:
            im = IMPORT.match(line.strip())
            if im:
                aliases[im.group(1).split(".")[-1]] = im.group(1)

        def resolve(expr: str) -> str | None:
            if "." in expr:
                head, tail_name = expr.split(".", 1)
                fqn = aliases.get(head, "")
                if fqn:
                    d, _, c = fqn.rpartition(".")
                    fqn = f"{d.replace('.', '/')}.{c}"
                if fqn and fqn in const_index:
                    return const_index[fqn].get(tail_name)
                return None
            fqn = f"{pkg}.{class_name}"
            if fqn in const_index:
                return const_index[fqn].get(expr)
            return None

        fields: list[dict] = []
        pending: str | None = None
        pending_raw: str | None = None
        for line in lines:
            sm = SERIALIZED.search(line)
            if sm:
                pending = sm.group(1)
                pending_raw = None
                continue
            cmatch = SERIALIZED_CONST.search(line)
            if cmatch and not sm:
                expr = cmatch.group(1)
                pending = resolve(expr)
                pending_raw = expr
                continue
            if pending is None and pending_raw is None:
                continue
            fm = FIELD.match(line)
            if fm:
                ftype, fname = fm.groups()
                fields.append(
                    {
                        "json": pending if pending is not None else f"const:{pending_raw}",
                        "type": " ".join(ftype.split()),
                        "name": fname,
                    }
                )
                pending = None
                pending_raw = None

        if not fields:
            continue
        key = f"{pkg}.{class_name}" if pkg else class_name
        entry = out.setdefault(key, {"package": pkg, "file": rel_source(path), "fields": []})
        seen = {f["json"] for f in entry["fields"]}
        for f in fields:
            if f["json"] not in seen:
                entry["fields"].append(f)
                seen.add(f["json"])
    return out


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("--out", default=str(REPO / "docs/reference/schema/sdk-schema.json"))
    ap.add_argument("--package", default=None, help="only payload classes in this package prefix")
    args = ap.parse_args()

    endpoints = scan_endpoints()
    const_index = build_const_index()
    payloads = scan_payloads(const_index)
    error_codes = build_error_codes()

    if args.package:
        prefix = args.package.replace(".", "/")
        payloads = {k: v for k, v in payloads.items() if k.startswith(prefix)}

    by_domain: dict[str, int] = {}
    for e in endpoints:
        head = e["path"].strip("/").split("/")[0] or "root"
        by_domain[head] = by_domain.get(head, 0) + 1

    doc = {
        "source": "decompiled-source/sources",
        "note": (
            "Generated by tools/extract-java-schema.py. Retrofit routes, "
            "@SerializedName payload classes and error-code vocabularies "
            "recovered from the decompiled SDK."
        ),
        "counts": {
            "endpoints": len(endpoints),
            "payload_classes": len(payloads),
            "error_code_classes": len(error_codes),
            "routes_by_first_segment": by_domain,
        },
        "endpoints": endpoints,
        "payloads": payloads,
        "error_codes": error_codes,
    }

    out_path = Path(args.out)
    out_path.parent.mkdir(parents=True, exist_ok=True)
    out_path.write_text(json.dumps(doc, indent=2) + "\n")
    print(f"endpoints: {len(endpoints)}")
    print(f"payload classes: {len(payloads)}")
    print(f"wrote {out_path.relative_to(REPO)}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
