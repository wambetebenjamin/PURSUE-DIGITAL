package com.bytedance.common.wschannel.event;
/* compiled from: ConnectionState.java */
/* loaded from: classes2.dex */
public enum c {
    CONNECTION_UNKNOWN(1),
    CONNECTING(2),
    CONNECT_FAILED(4),
    CONNECT_CLOSED(8),
    CONNECTED(16);
    

    /* renamed from: f  reason: collision with root package name */
    final int f5631f;

    @Override // java.lang.Enum
    public String toString() {
        return "ConnectionState{State=" + this.f5631f + '}';
    }

    c(int i2) {
        this.f5631f = i2;
    }

    public int a() {
        return this.f5631f;
    }
}
