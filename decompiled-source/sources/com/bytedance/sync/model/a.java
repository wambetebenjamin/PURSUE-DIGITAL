package com.bytedance.sync.model;
/* compiled from: DataType.java */
/* loaded from: classes3.dex */
public enum a {
    ORIGIN(0),
    FILE_PATH(1);
    

    /* renamed from: a  reason: collision with root package name */
    private final int f13472a;

    a(int i2) {
        this.f13472a = i2;
    }

    public static a fromValue(int i2) {
        a aVar = ORIGIN;
        if (i2 == aVar.getValue()) {
            return aVar;
        }
        a aVar2 = FILE_PATH;
        if (i2 == aVar2.getValue()) {
            return aVar2;
        }
        return null;
    }

    public int getValue() {
        return this.f13472a;
    }
}
