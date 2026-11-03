package com.bytedance.compression.zstd;
/* compiled from: EndDirective.java */
/* loaded from: classes2.dex */
public enum d {
    CONTINUE(0),
    FLUSH(1),
    END(2);
    

    /* renamed from: d  reason: collision with root package name */
    private final int f6034d;

    d(int i2) {
        this.f6034d = i2;
    }

    int a() {
        return this.f6034d;
    }
}
