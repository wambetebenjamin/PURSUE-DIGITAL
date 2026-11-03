package com.bytedance.ttgame.sdk.module.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
/* loaded from: classes8.dex */
public class ToastUtils {
    private static int SHOW_INTERVAL = 2000;
    private static String sLastStr;
    private static long sLastToastTime;

    private ToastUtils() {
    }

    public static void showToast(Context context, int i2) {
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        showToast(applicationContext, applicationContext.getString(i2));
    }

    public static void showToast(Context context, String str) {
        if (context == null) {
            return;
        }
        showToast(context, str, 0);
    }

    public static void showToast(Context context, String str, int i2) {
        if (TextUtils.isEmpty(str) || context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        int i3 = i2 == 0 ? 0 : 1;
        if (!str.equals(sLastStr) || System.currentTimeMillis() - sLastToastTime >= SHOW_INTERVAL) {
            Toast makeText = Toast.makeText(applicationContext, str, i3);
            makeText.setText(str);
            makeText.show();
            sLastStr = str;
            sLastToastTime = System.currentTimeMillis();
        }
    }
}
