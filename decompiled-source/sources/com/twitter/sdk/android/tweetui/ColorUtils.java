package com.twitter.sdk.android.tweetui;

import android.graphics.Color;
/* loaded from: classes10.dex */
final class ColorUtils {
    private ColorUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int calculateOpacityTransform(double d2, int i2, int i3) {
        double d3 = 1.0d - d2;
        return Color.rgb((int) ((Color.red(i3) * d3) + (Color.red(i2) * d2)), (int) ((Color.green(i3) * d3) + (Color.green(i2) * d2)), (int) ((d3 * Color.blue(i3)) + (d2 * Color.blue(i2))));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isLightColor(int i2) {
        return ((((double) Color.red(i2)) * 0.21d) + (((double) Color.green(i2)) * 0.72d)) + (((double) Color.blue(i2)) * 0.07d) > 128.0d;
    }
}
