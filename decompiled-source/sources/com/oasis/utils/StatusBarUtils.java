package com.oasis.utils;

import android.content.Context;
/* loaded from: classes9.dex */
public class StatusBarUtils {
    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
