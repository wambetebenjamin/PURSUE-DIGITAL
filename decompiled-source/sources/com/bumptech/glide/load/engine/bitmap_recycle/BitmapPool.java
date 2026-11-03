package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
/* loaded from: classes.dex */
public interface BitmapPool {
    void clearMemory();

    Bitmap get(int i2, int i3, Bitmap.Config config);

    Bitmap getDirty(int i2, int i3, Bitmap.Config config);

    long getMaxSize();

    void put(Bitmap bitmap);

    void setSizeMultiplier(float f2);

    void trimMemory(int i2);
}
