package com.ixigua.touchtileimageview.utils;

import android.graphics.Matrix;
/* compiled from: MatrixUtils.java */
/* loaded from: classes9.dex */
public class h {
    public static float scaleValue(Matrix matrix) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        float f2 = fArr[0];
        float f3 = fArr[3];
        return (float) Math.sqrt((f2 * f2) + (f3 * f3));
    }

    public static float[] translateValue(Matrix matrix) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        return new float[]{fArr[2], fArr[5]};
    }

    public static float rotateValue(Matrix matrix) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        return (float) Math.round(Math.atan2(fArr[1], fArr[0]) * 57.29577951308232d);
    }

    public static boolean isEqual(Matrix matrix, Matrix matrix2) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        float[] fArr2 = new float[9];
        matrix2.getValues(fArr2);
        for (int i2 = 0; i2 < 9; i2++) {
            if (!d.isEqual(fArr[i2], fArr2[i2])) {
                return false;
            }
        }
        return true;
    }
}
