package com.facebook.imagepipeline.common;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.imageformat.ImageFormat;
import java.util.Locale;
/* loaded from: classes8.dex */
public abstract class ImageDecodeBitmapConfigStrategy {
    public static ImageDecodeBitmapConfigStrategy DEFAULT = new ImageDecodeBitmapConfigStrategy() { // from class: com.facebook.imagepipeline.common.ImageDecodeBitmapConfigStrategy.1
        @Override // com.facebook.imagepipeline.common.ImageDecodeBitmapConfigStrategy
        public Bitmap.Config getBitmapConfig(Uri uri, int i2, int i3, boolean z, ImageFormat imageFormat) {
            return Bitmap.Config.ARGB_8888;
        }
    };
    public static ImageDecodeBitmapConfigStrategy MEMORY_AT_LEAST = new ImageDecodeBitmapConfigStrategy() { // from class: com.facebook.imagepipeline.common.ImageDecodeBitmapConfigStrategy.2
        @Override // com.facebook.imagepipeline.common.ImageDecodeBitmapConfigStrategy
        public Bitmap.Config getBitmapConfig(Uri uri, int i2, int i3, boolean z, ImageFormat imageFormat) {
            if (!z && !isMatchBadCase()) {
                return Bitmap.Config.RGB_565;
            }
            return Bitmap.Config.ARGB_8888;
        }

        private boolean isMatchBadCase() {
            return ImageDecodeBitmapConfigStrategy.access$000() && Build.VERSION.SDK_INT == 25;
        }
    };
    private static ImageDecodeBitmapConfigStrategy sStrategy = DEFAULT;

    public abstract Bitmap.Config getBitmapConfig(Uri uri, int i2, int i3, boolean z, ImageFormat imageFormat);

    static /* synthetic */ boolean access$000() {
        return isMeizu15();
    }

    public static ImageDecodeBitmapConfigStrategy getStrategy() {
        return sStrategy;
    }

    public static void setStrategy(ImageDecodeBitmapConfigStrategy imageDecodeBitmapConfigStrategy) {
        sStrategy = imageDecodeBitmapConfigStrategy;
    }

    private static boolean isMeizu() {
        String str = Build.BRAND;
        return str != null && str.toLowerCase(Locale.ENGLISH).indexOf("meizu") > -1;
    }

    private static boolean isMeizu15() {
        if (!isMeizu() || TextUtils.isEmpty(Build.DEVICE)) {
            return false;
        }
        return Build.DEVICE.contains("15");
    }
}
