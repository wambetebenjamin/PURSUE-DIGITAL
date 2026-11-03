package com.bytedance.common.wschannel.event;
/* compiled from: ConnectEvent.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final a f5621a;

    /* renamed from: b  reason: collision with root package name */
    public final c f5622b;

    /* renamed from: c  reason: collision with root package name */
    public final int f5623c;

    /* renamed from: d  reason: collision with root package name */
    public final int f5624d;

    public b(c cVar, a aVar, int i2, int i3) {
        this.f5622b = cVar;
        this.f5621a = aVar;
        this.f5624d = i2;
        this.f5623c = i3;
    }

    public String toString() {
        return "ConnectEvent{mType=" + this.f5621a + ", connectionState=" + this.f5622b + ", mChannelId=" + this.f5624d + ", errorCode=" + this.f5623c + '}';
    }
}
