package com.ixigua.touchtileimageview.utils;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
/* compiled from: ImageRectUtils.java */
/* loaded from: classes9.dex */
public class f {
    public static Matrix rectToRectCenterCrop(RectF rectF, RectF rectF2) {
        float max = Math.max(rectF2.width() / rectF.width(), rectF2.height() / rectF.height());
        Matrix matrix = new Matrix();
        matrix.setScale(max, max, rectF.centerX(), rectF.centerY());
        matrix.postTranslate(rectF2.centerX() - rectF.centerX(), rectF2.centerY() - rectF.centerY());
        return matrix;
    }

    public static Matrix rectToRectCenterFit(RectF rectF, RectF rectF2) {
        float min = Math.min(rectF2.width() / rectF.width(), rectF2.height() / rectF.height());
        Matrix matrix = new Matrix();
        matrix.setScale(min, min, rectF.centerX(), rectF.centerY());
        matrix.postTranslate(rectF2.centerX() - rectF.centerX(), rectF2.centerY() - rectF.centerY());
        return matrix;
    }

    public static Matrix rectToRectTop(RectF rectF, RectF rectF2) {
        float min = Math.min(rectF2.width() / rectF.width(), rectF2.height() / rectF.height());
        Matrix matrix = new Matrix();
        matrix.setScale(min, min, 0.0f, 0.0f);
        return matrix;
    }

    public static RectF rectFFromDrawable(Drawable drawable) {
        return new RectF(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }
}
