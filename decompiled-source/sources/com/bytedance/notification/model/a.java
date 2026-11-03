package com.bytedance.notification.model;
/* compiled from: NotificationStyle.java */
/* loaded from: classes2.dex */
public enum a {
    NORMAL(1, "NORMAL"),
    SMALL_PICTURE(2, "SMALL_PICTURE");
    

    /* renamed from: c  reason: collision with root package name */
    public final int f9901c;

    /* renamed from: d  reason: collision with root package name */
    public final String f9902d;

    a(int i2, String str) {
        this.f9901c = i2;
        this.f9902d = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.f9902d;
    }
}
