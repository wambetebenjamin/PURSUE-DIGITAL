package com.ixigua.touchtileimageview.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
/* compiled from: AndroidPictureRegionDecoder.java */
/* loaded from: classes9.dex */
public class a extends g {

    /* renamed from: a  reason: collision with root package name */
    private final BitmapRegionDecoder f17071a;

    public a(BitmapRegionDecoder bitmapRegionDecoder) {
        this.f17071a = bitmapRegionDecoder;
    }

    @Override // com.ixigua.touchtileimageview.drawable.g
    public int getWidth() {
        return this.f17071a.getWidth();
    }

    @Override // com.ixigua.touchtileimageview.drawable.g
    public int getHeight() {
        return this.f17071a.getHeight();
    }

    @Override // com.ixigua.touchtileimageview.drawable.g
    public Bitmap decodeRegion(Rect rect, BitmapFactory.Options options) {
        return this.f17071a.decodeRegion(rect, options);
    }

    @Override // com.ixigua.touchtileimageview.drawable.g
    public void recycle() {
        this.f17071a.recycle();
    }
}
