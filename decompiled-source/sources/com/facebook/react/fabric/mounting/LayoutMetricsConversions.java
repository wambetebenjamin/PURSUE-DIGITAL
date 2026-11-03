package com.facebook.react.fabric.mounting;

import android.view.View;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.yoga.YogaMeasureMode;
/* loaded from: classes8.dex */
public class LayoutMetricsConversions {
    public static float getMinSize(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            return size;
        }
        return 0.0f;
    }

    public static float getMaxSize(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 0) {
            return Float.POSITIVE_INFINITY;
        }
        return size;
    }

    public static float getYogaSize(float f2, float f3) {
        if (f2 == f3) {
            return PixelUtil.toPixelFromDIP(f3);
        }
        if (Float.isInfinite(f3)) {
            return Float.POSITIVE_INFINITY;
        }
        return PixelUtil.toPixelFromDIP(f3);
    }

    public static YogaMeasureMode getYogaMeasureMode(float f2, float f3) {
        if (f2 == f3) {
            return YogaMeasureMode.EXACTLY;
        }
        if (Float.isInfinite(f3)) {
            return YogaMeasureMode.UNDEFINED;
        }
        return YogaMeasureMode.AT_MOST;
    }
}
