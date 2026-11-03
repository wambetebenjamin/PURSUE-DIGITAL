package com.facebook.imagepipeline.platform;

import android.graphics.BitmapFactory;
import androidx.core.util.Pools;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imageutils.BitmapUtil;
/* loaded from: classes8.dex */
public class ArtDecoder extends DefaultDecoder {
    public ArtDecoder(BitmapPool bitmapPool, int i2, Pools.SynchronizedPool synchronizedPool) {
        super(bitmapPool, i2, synchronizedPool);
    }

    @Override // com.facebook.imagepipeline.platform.DefaultDecoder
    public int getBitmapSize(int i2, int i3, BitmapFactory.Options options) {
        return BitmapUtil.getSizeInByteForBitmap(i2, i3, options.inPreferredConfig);
    }
}
