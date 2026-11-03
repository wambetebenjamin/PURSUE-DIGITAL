package com.ixigua.touchtileimageview.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
/* compiled from: PictureRegionDecoder.java */
/* loaded from: classes9.dex */
public abstract class g {
    public abstract Bitmap decodeRegion(Rect rect, BitmapFactory.Options options);

    public abstract int getHeight();

    public abstract int getWidth();

    public abstract void recycle();
}
