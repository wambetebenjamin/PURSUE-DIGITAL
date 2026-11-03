package com.ixigua.touchtileimageview.utils.closegesturepolicy;

import android.graphics.Matrix;
import android.graphics.RectF;
import com.ixigua.touchtileimageview.j;
import com.ixigua.touchtileimageview.utils.h;
/* compiled from: AnyScrollGesturePolicy.java */
/* loaded from: classes9.dex */
public class a extends com.ixigua.touchtileimageview.utils.e {

    /* renamed from: a  reason: collision with root package name */
    private static final float f17191a = 1.0f;

    /* renamed from: b  reason: collision with root package name */
    private static final float f17192b = 1.0f;

    /* renamed from: c  reason: collision with root package name */
    private static final float f17193c = 0.1f;

    /* renamed from: d  reason: collision with root package name */
    private float f17194d;

    public a() {
        super(null);
        this.f17194d = 0.0f;
    }

    @Override // com.ixigua.touchtileimageview.utils.e
    public boolean isSupport(Matrix matrix) {
        this.matrix = matrix;
        this.f17194d = h.scaleValue(this.matrix);
        return true;
    }

    @Override // com.ixigua.touchtileimageview.utils.e
    public float getScaleValue(float f2, float f3, RectF rectF, RectF rectF2, float f4) {
        float min = Math.min(1.0f, Math.abs(f4) / (rectF2.height() * 1.0f));
        float f5 = this.f17194d;
        return f5 + ((f3 - f5) * min);
    }

    @Override // com.ixigua.touchtileimageview.utils.e
    public float getAlphaValue(float f2, float f3, RectF rectF, RectF rectF2, float f4) {
        return 1.0f - Math.min(1.0f, Math.abs(f4) / (rectF2.height() * 1.0f));
    }

    @Override // com.ixigua.touchtileimageview.utils.e
    public boolean canExit(j jVar, float f2, RectF rectF, RectF rectF2, float f3) {
        return com.ixigua.touchtileimageview.utils.d.isGreaterThanEqual(Math.abs(f3), rectF2.height() * 0.1f);
    }
}
