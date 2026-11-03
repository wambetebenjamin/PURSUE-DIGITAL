package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.core.util.Pools;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imageutils.BitmapUtil;
/* loaded from: classes8.dex */
public class OreoDecoder extends DefaultDecoder {
    public OreoDecoder(BitmapPool bitmapPool, int i2, Pools.SynchronizedPool synchronizedPool) {
        super(bitmapPool, i2, synchronizedPool);
    }

    @Override // com.facebook.imagepipeline.platform.DefaultDecoder
    public int getBitmapSize(int i2, int i3, BitmapFactory.Options options) {
        return hasColorGamutMismatch(options) ? i2 * i3 * 8 : BitmapUtil.getSizeInByteForBitmap(i2, i3, options.inPreferredConfig);
    }

    private static boolean hasColorGamutMismatch(BitmapFactory.Options options) {
        return (options.outColorSpace == null || !options.outColorSpace.isWideGamut() || options.inPreferredConfig == Bitmap.Config.RGBA_F16) ? false : true;
    }
}
