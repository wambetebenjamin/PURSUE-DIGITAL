package com.bytedance.notification.utils;

import android.content.Context;
/* compiled from: DpUtils.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static float f9959a;

    public static int dp2px(Context context, float f2) {
        if (f9959a == 0.0f) {
            f9959a = context.getResources().getDisplayMetrics().density;
        }
        c.d("DpUtils", "dp2px: mScale is " + f9959a);
        return (int) ((f2 * f9959a) + 0.5f);
    }
}
