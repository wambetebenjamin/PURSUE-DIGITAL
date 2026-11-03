package com.ixigua.touchtileimageview;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
/* compiled from: TouchBaseImageView.java */
/* loaded from: classes9.dex */
class c {

    /* renamed from: a  reason: collision with root package name */
    final com.ixigua.touchtileimageview.drawable.d<Drawable> f17015a;

    /* renamed from: b  reason: collision with root package name */
    final Matrix f17016b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Drawable drawable, View view, RectF rectF, k kVar) {
        com.ixigua.touchtileimageview.drawable.d<Drawable> dVar = new com.ixigua.touchtileimageview.drawable.d<>(drawable);
        this.f17015a = dVar;
        dVar.setCallback(view);
        this.f17016b = a(rectF, kVar);
    }

    private Matrix a(RectF rectF, k kVar) {
        RectF rectFFromDrawable = com.ixigua.touchtileimageview.utils.f.rectFFromDrawable(this.f17015a);
        if (kVar == k.NONE) {
            return com.ixigua.touchtileimageview.utils.f.rectToRectCenterCrop(rectFFromDrawable, new RectF(rectF));
        }
        if (kVar == k.CENTER) {
            return com.ixigua.touchtileimageview.utils.f.rectToRectCenterFit(rectFFromDrawable, new RectF(rectF));
        }
        if (kVar == k.TOP) {
            return com.ixigua.touchtileimageview.utils.f.rectToRectTop(rectFFromDrawable, new RectF(rectF));
        }
        throw new IllegalArgumentException("unknown thumbnail scale position");
    }
}
