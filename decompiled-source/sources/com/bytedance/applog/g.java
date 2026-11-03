package com.bytedance.applog;
/* compiled from: EventType.java */
/* loaded from: classes.dex */
public enum g {
    V3(0),
    V1(1),
    LogData(2),
    Launch(3),
    Terminate(4),
    IMPRESSION(5),
    FORWARD_V3(6),
    UNKNOWN(99);
    

    /* renamed from: i  reason: collision with root package name */
    final int f2702i;

    g(int i2) {
        this.f2702i = i2;
    }

    public int a() {
        return this.f2702i;
    }
}
