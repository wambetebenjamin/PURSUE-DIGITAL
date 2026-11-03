package com.bytedance.applog.isolate;
/* compiled from: DataIsolateKey.java */
/* loaded from: classes.dex */
public enum a {
    USER_ID("user_id");
    

    /* renamed from: b  reason: collision with root package name */
    private final String f2719b;

    a(String str) {
        this.f2719b = str;
    }

    public String a() {
        return this.f2719b;
    }

    public static a a(String str) {
        a[] values;
        for (a aVar : values()) {
            if (aVar.a().equals(str)) {
                return aVar;
            }
        }
        return null;
    }
}
