package com.ixigua.touchtileimageview.utils;

import android.graphics.Matrix;
import android.graphics.RectF;
/* compiled from: GestureDismissPolicy.java */
/* loaded from: classes9.dex */
public abstract class e {
    public Matrix matrix;

    public boolean canExit(com.ixigua.touchtileimageview.j jVar, float f2, RectF rectF, RectF rectF2, float f3) {
        return false;
    }

    public abstract float getAlphaValue(float f2, float f3, RectF rectF, RectF rectF2, float f4);

    public float getMinScaleValue(RectF rectF, RectF rectF2) {
        return -1.0f;
    }

    public float getScaleValue(float f2, float f3, RectF rectF, RectF rectF2, float f4) {
        return 1.0f;
    }

    public e(Matrix matrix) {
        this.matrix = matrix;
    }

    public boolean isSupport(Matrix matrix) {
        return h.isEqual(this.matrix, matrix);
    }
}
