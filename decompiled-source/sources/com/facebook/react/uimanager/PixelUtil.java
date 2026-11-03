package com.facebook.react.uimanager;

import android.util.DisplayMetrics;
import android.util.TypedValue;
/* loaded from: classes8.dex */
public class PixelUtil {
    public static float toPixelFromDIP(float f2) {
        return TypedValue.applyDimension(1, f2, DisplayMetricsHolder.getWindowDisplayMetrics());
    }

    public static float toPixelFromDIP(double d2) {
        return toPixelFromDIP((float) d2);
    }

    public static float toSPFromPixel(float f2) {
        return f2 / DisplayMetricsHolder.getScreenDisplayMetrics().scaledDensity;
    }

    public static float toPixelFromSP(float f2) {
        return toPixelFromSP(f2, Float.NaN);
    }

    public static float toPixelFromSP(float f2, float f3) {
        DisplayMetrics windowDisplayMetrics = DisplayMetricsHolder.getWindowDisplayMetrics();
        float f4 = windowDisplayMetrics.scaledDensity;
        float f5 = f4 / windowDisplayMetrics.density;
        if (f3 >= 1.0f && f3 < f5) {
            f4 = windowDisplayMetrics.density * f3;
        }
        return f2 * f4;
    }

    public static float toPixelFromSP(double d2) {
        return toPixelFromSP((float) d2);
    }

    public static float toDIPFromPixel(float f2) {
        return f2 / DisplayMetricsHolder.getWindowDisplayMetrics().density;
    }

    public static float getDisplayMetricDensity() {
        return DisplayMetricsHolder.getScreenDisplayMetrics().density;
    }
}
