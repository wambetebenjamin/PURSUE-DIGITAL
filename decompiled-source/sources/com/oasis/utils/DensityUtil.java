package com.oasis.utils;

import android.content.Context;
/* loaded from: classes9.dex */
public class DensityUtil {
    public static int dp2px(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dp(Context context, float f2) {
        return (int) ((f2 / context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
