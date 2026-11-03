package com.bytedance.applog;
/* compiled from: IEventObserver.java */
/* loaded from: classes.dex */
public interface l {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2720a = 0;

    /* renamed from: b  reason: collision with root package name */
    public static final int f2721b = 1;

    /* renamed from: c  reason: collision with root package name */
    public static final int f2722c = 2;

    void onEvent(String str, String str2, String str3, long j2, long j3, String str4);

    void onEventV3(String str, String str2);

    void onMiscEvent(String str, String str2);
}
