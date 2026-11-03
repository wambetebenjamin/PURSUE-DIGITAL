package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.memory.MemoryTrimType;
/* loaded from: classes8.dex */
public class DummyBitmapPool implements BitmapPool {
    @Override // com.facebook.common.memory.MemoryTrimmable
    public void trim(MemoryTrimType memoryTrimType) {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.facebook.common.memory.Pool
    public Bitmap get(int i2) {
        return Bitmap.createBitmap(1, (int) Math.ceil(i2 / 2.0d), Bitmap.Config.RGB_565);
    }

    @Override // com.facebook.common.memory.Pool, com.facebook.common.references.ResourceReleaser
    public void release(Bitmap bitmap) {
        bitmap.recycle();
    }
}
