package com.bytedance.react.framework.floatingview.utils;

import android.content.Context;
/* loaded from: classes2.dex */
public class SystemUtils {
    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getScreenWidth(Context context) {
        try {
            return context.getResources().getDisplayMetrics().widthPixels;
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static int getScreenHeight(Context context) {
        try {
            return context.getResources().getDisplayMetrics().heightPixels;
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }
}
