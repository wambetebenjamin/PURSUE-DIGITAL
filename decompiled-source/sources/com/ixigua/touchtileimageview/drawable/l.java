package com.ixigua.touchtileimageview.drawable;

import android.graphics.Bitmap;
import androidx.collection.ArrayMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BitmapRegionDecoderDrawable.java */
/* loaded from: classes9.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    final int f17130a;

    /* renamed from: b  reason: collision with root package name */
    final ArrayMap<k, Bitmap> f17131b = new ArrayMap<>();

    public l(int i2) {
        if (i2 < 1) {
            throw new IllegalArgumentException("scale must >= 1");
        }
        this.f17130a = i2;
    }
}
