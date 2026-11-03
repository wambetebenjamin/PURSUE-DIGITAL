package com.ixigua.touchtileimageview.configuration;

import android.graphics.Matrix;
import android.graphics.RectF;
import com.ixigua.touchtileimageview.g;
/* compiled from: Configuration.java */
/* loaded from: classes9.dex */
public interface c {
    void fixFullSize(Matrix matrix);

    Matrix getDefaultMatrix(g gVar);

    Matrix getMaxMatrix(g gVar);

    Matrix getMiniMatrix(g gVar);

    Matrix getNextMatrix(Matrix matrix, g gVar);

    com.ixigua.touchtileimageview.utils.e[] getPullToDismissPolicy();

    com.ixigua.touchtileimageview.utils.e getScaleToDismissPolicy(g gVar);

    Matrix[] getSwipeToDismissMatrix(g gVar);

    void updateConfiguration(RectF rectF, RectF rectF2);
}
