package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.core.util.Pools;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.memory.BitmapPool;
import java.io.InputStream;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class PieDecoder extends OreoDecoder {
    private static final String TAG = "PieDecoder";
    @Nullable
    private final WebpBitmapFactory mWebpBitmapFactory;

    public PieDecoder(BitmapPool bitmapPool, int i2, Pools.SynchronizedPool synchronizedPool) {
        super(bitmapPool, i2, synchronizedPool);
        this.mWebpBitmapFactory = WebpSupportStatus.loadWebpBitmapFactoryIfExists();
    }

    @Override // com.facebook.imagepipeline.platform.DefaultDecoder
    protected Bitmap decodeBitmap(InputStream inputStream, BitmapFactory.Options options, int i2, int i3, Bitmap bitmap) {
        Bitmap bitmap2;
        if ((Build.VERSION.SDK_INT == 28 || Build.VERSION.SDK_INT == 29) && this.mWebpBitmapFactory != null) {
            bitmap.reconfigure(i2, i3, options.inPreferredConfig);
            bitmap2 = this.mWebpBitmapFactory.decodeStream(inputStream, null, options);
        } else {
            bitmap2 = null;
        }
        return bitmap2 == null ? BitmapFactory.decodeStream(inputStream, null, options) : bitmap2;
    }

    @Override // com.facebook.imagepipeline.platform.OreoDecoder, com.facebook.imagepipeline.platform.DefaultDecoder
    public int getBitmapSize(int i2, int i3, BitmapFactory.Options options) {
        return super.getBitmapSize(i2, i3, options);
    }
}
