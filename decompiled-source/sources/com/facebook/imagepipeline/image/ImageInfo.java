package com.facebook.imagepipeline.image;

import android.graphics.Rect;
import com.facebook.imageformat.ImageFormat;
/* loaded from: classes8.dex */
public interface ImageInfo {
    int getHeight();

    int getImageCount();

    ImageFormat getImageFormat();

    QualityInfo getQualityInfo();

    Rect getRegionToDecode();

    int getSampleSize();

    Rect getSmartCrop();

    int getWidth();
}
