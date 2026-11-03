package com.bytedance.crash;

import com.bytedance.crash.anr.e;
/* loaded from: classes2.dex */
public enum CrashType {
    LAUNCH("launch"),
    JAVA("java"),
    NATIVE("native"),
    ASAN("asan"),
    TSAN("tsan"),
    ANR(e.f6231d),
    NATIVE_CUSTOMIZE("ensure_zip"),
    BLOCK("block"),
    ENSURE("ensure"),
    DART("dart"),
    GAME("game"),
    CUSTOM_JAVA("custom_java"),
    OOM("oom"),
    CJAVA("cjava"),
    COREDUMP("coredump"),
    EVENT("event"),
    ALL("all"),
    UNKNOWN("unknown");
    
    private final String mName;

    CrashType(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    public static CrashType getType(String str) {
        try {
            return valueOf(str.toUpperCase());
        } catch (Throwable unused) {
            return UNKNOWN;
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return getName();
    }
}
