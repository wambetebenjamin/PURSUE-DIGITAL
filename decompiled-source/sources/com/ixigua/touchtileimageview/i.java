package com.ixigua.touchtileimageview;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.View;
/* compiled from: TouchBaseImageView.java */
/* loaded from: classes9.dex */
class i {

    /* renamed from: a  reason: collision with root package name */
    final com.ixigua.touchtileimageview.drawable.d<com.ixigua.touchtileimageview.drawable.c> f17163a;

    /* renamed from: b  reason: collision with root package name */
    final Matrix f17164b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(com.ixigua.touchtileimageview.drawable.c cVar, View view, RectF rectF) {
        com.ixigua.touchtileimageview.drawable.d<com.ixigua.touchtileimageview.drawable.c> dVar = new com.ixigua.touchtileimageview.drawable.d<>(cVar);
        this.f17163a = dVar;
        dVar.setCallback(view);
        this.f17164b = a(rectF);
    }

    private Matrix a(RectF rectF) {
        return com.ixigua.touchtileimageview.utils.f.rectToRectCenterCrop(com.ixigua.touchtileimageview.utils.f.rectFFromDrawable(this.f17163a), new RectF(rectF));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        this.f17163a.getWrapDrawable().recycle();
    }
}
