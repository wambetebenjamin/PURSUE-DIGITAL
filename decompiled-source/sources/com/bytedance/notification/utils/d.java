package com.bytedance.notification.utils;

import com.bytedance.push.utils.h;
/* compiled from: NotificationLogger.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static String f9960a = "PushNotification";

    public static void d(String str) {
        d("", str);
    }

    public static void d(String str, String str2) {
        h.d(f9960a, str + "\t>>>\t" + str2);
    }

    public static void e(String str) {
        e("", str);
    }

    public static void e(String str, String str2) {
        h.e(f9960a, str + "\t>>> " + str2);
    }

    public static void i(String str) {
        i("", str);
    }

    public static void i(String str, String str2) {
        h.i(f9960a, str + "\t>>>\t" + str2);
    }

    public static void i(String str, String str2, Throwable th) {
        h.i(str, str2, th);
    }

    public static void v(String str) {
        v("", str);
    }

    public static void v(String str, String str2) {
        h.v(f9960a, str + "\t>>>\t" + str2);
    }
}
