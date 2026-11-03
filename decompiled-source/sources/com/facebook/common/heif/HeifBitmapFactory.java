package com.facebook.common.heif;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import com.facebook.common.webp.BitmapCreator;
import java.io.InputStream;
/* loaded from: classes8.dex */
public interface HeifBitmapFactory {
    Bitmap decodeByteArray(byte[] bArr, int i2, int i3, BitmapFactory.Options options);

    Bitmap decodeStream(InputStream inputStream, Rect rect, BitmapFactory.Options options);

    Bitmap decodeStream(InputStream inputStream, Rect rect, BitmapFactory.Options options, HeifDecodeData heifDecodeData);

    Bitmap decodeThumb(InputStream inputStream, Rect rect, BitmapFactory.Options options);

    Bitmap decodeThumb(InputStream inputStream, Rect rect, BitmapFactory.Options options, HeifDecodeData heifDecodeData);

    void setBitmapCreator(BitmapCreator bitmapCreator);
}
