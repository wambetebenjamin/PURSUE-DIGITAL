package com.bytedance.push.interfaze;

import android.content.Context;
/* compiled from: IPushNotificationManagerService.java */
/* loaded from: classes2.dex */
public interface ae {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11391a = "createNotificationChannels";

    /* renamed from: b  reason: collision with root package name */
    public static final String f11392b = "android.permission.POST_NOTIFICATIONS";

    /* renamed from: c  reason: collision with root package name */
    public static final int f11393c = 61519;
    public static final String n = "enqueueNotificationWithTag";

    void a(int i2, String[] strArr, int[] iArr);

    void a(Context context);

    boolean a();

    boolean a(af afVar);
}
