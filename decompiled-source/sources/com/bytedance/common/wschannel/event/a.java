package com.bytedance.common.wschannel.event;
/* compiled from: ChannelType.java */
/* loaded from: classes2.dex */
public enum a {
    CHANNEL_SELF(1),
    CHANNEL_OK(2);
    

    /* renamed from: c  reason: collision with root package name */
    int f5620c;

    a(int i2) {
        this.f5620c = i2;
    }

    public static a a(int i2) {
        if (i2 == 1) {
            return CHANNEL_SELF;
        }
        return CHANNEL_OK;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "ChannelType{Type=" + this.f5620c + '}';
    }

    public int a() {
        return this.f5620c;
    }
}
