package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
/* loaded from: classes8.dex */
public class NativeRoundingFilter {
    private static native void nativeToCircleFilter(Bitmap bitmap, boolean z);

    private static native void nativeToCircleWithBorderFilter(Bitmap bitmap, int i2, int i3, boolean z);

    static {
        NativeFiltersLoader.load();
    }

    public static void toCircle(Bitmap bitmap) {
        toCircle(bitmap, false);
    }

    public static void toCircle(Bitmap bitmap, boolean z) {
        Preconditions.checkNotNull(bitmap);
        nativeToCircleFilter(bitmap, z);
    }

    public static void toCircleWithBorder(Bitmap bitmap, int i2, int i3, boolean z) {
        Preconditions.checkNotNull(bitmap);
        nativeToCircleWithBorderFilter(bitmap, i2, i3, z);
    }
}
