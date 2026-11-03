package com.bytedance.sdk.account.utils;

import android.content.Context;
import android.widget.Toast;
/* compiled from: ToastUtils.java */
/* loaded from: classes2.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static final int f13090a = 1500;

    private i() {
    }

    public static void showToast(Context context, int i2) {
        showToast(context, i2, 0);
    }

    public static void showToast(Context context, String str) {
        showToast(context, str, 0);
    }

    public static void showToast(Context context, int i2, int i3) {
        if (context == null) {
            return;
        }
        Toast.makeText(context.getApplicationContext(), i2, i3).show();
    }

    public static void showToast(Context context, String str, int i2) {
        if (context == null) {
            return;
        }
        Toast.makeText(context.getApplicationContext(), str, i2).show();
    }
}
