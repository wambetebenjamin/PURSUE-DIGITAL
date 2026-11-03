package com.ixigua.touchtileimageview.utils;

import android.graphics.Matrix;
import android.graphics.RectF;
/* compiled from: ConfigurationUtils.java */
/* loaded from: classes9.dex */
public class b {
    public static com.ixigua.touchtileimageview.matrixitem.f fitCenter90(RectF rectF, RectF rectF2) {
        final Matrix matrix = new Matrix();
        matrix.postRotate(90.0f);
        RectF rectF3 = new RectF(rectF2);
        matrix.mapRect(rectF3);
        matrix.postTranslate(-rectF3.left, -rectF3.top);
        matrix.postConcat(new com.ixigua.touchtileimageview.matrixitem.b(rectF, new RectF(0.0f, 0.0f, rectF2.height(), rectF2.width())).getMatrix());
        return new com.ixigua.touchtileimageview.matrixitem.f() { // from class: com.ixigua.touchtileimageview.utils.b.1
            @Override // com.ixigua.touchtileimageview.matrixitem.f
            public Matrix getMatrix() {
                return matrix;
            }
        };
    }

    public static com.ixigua.touchtileimageview.matrixitem.f cropCenter90(RectF rectF, RectF rectF2) {
        final Matrix matrix = new Matrix();
        matrix.postRotate(90.0f);
        RectF rectF3 = new RectF(rectF2);
        matrix.mapRect(rectF3);
        matrix.postTranslate(-rectF3.left, -rectF3.top);
        matrix.postConcat(new com.ixigua.touchtileimageview.matrixitem.a(rectF, new RectF(0.0f, 0.0f, rectF2.height(), rectF2.width())).getMatrix());
        return new com.ixigua.touchtileimageview.matrixitem.f() { // from class: com.ixigua.touchtileimageview.utils.b.2
            @Override // com.ixigua.touchtileimageview.matrixitem.f
            public Matrix getMatrix() {
                return matrix;
            }
        };
    }

    public static com.ixigua.touchtileimageview.matrixitem.f fullCenter90(RectF rectF, RectF rectF2) {
        final Matrix matrix = new Matrix();
        matrix.postRotate(90.0f);
        RectF rectF3 = new RectF(rectF2);
        matrix.mapRect(rectF3);
        matrix.postTranslate(-rectF3.left, -rectF3.top);
        matrix.postConcat(new com.ixigua.touchtileimageview.matrixitem.e(rectF, new RectF(0.0f, 0.0f, rectF2.height(), rectF2.width())).getMatrix());
        return new com.ixigua.touchtileimageview.matrixitem.f() { // from class: com.ixigua.touchtileimageview.utils.b.3
            @Override // com.ixigua.touchtileimageview.matrixitem.f
            public Matrix getMatrix() {
                return matrix;
            }
        };
    }

    public static Matrix getFitCenterMatrix(Matrix matrix, Matrix matrix2, RectF rectF, com.ixigua.touchtileimageview.g gVar) {
        return getMatrixByDegree(matrix, matrix2, rectF, gVar);
    }

    public static Matrix getCropCenterMatrix(Matrix matrix, Matrix matrix2, RectF rectF, com.ixigua.touchtileimageview.g gVar) {
        return getMatrixByDegree(matrix, matrix2, rectF, gVar);
    }

    public static Matrix getFullCenterMatrix(Matrix matrix, Matrix matrix2, RectF rectF, com.ixigua.touchtileimageview.g gVar) {
        return getMatrixByDegree(matrix, matrix2, rectF, gVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ConfigurationUtils.java */
    /* renamed from: com.ixigua.touchtileimageview.utils.b$4  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f17189a;

        static {
            int[] iArr = new int[com.ixigua.touchtileimageview.g.values().length];
            f17189a = iArr;
            try {
                iArr[com.ixigua.touchtileimageview.g.ROTATE_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f17189a[com.ixigua.touchtileimageview.g.ROTATE_90.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f17189a[com.ixigua.touchtileimageview.g.ROTATE_180.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f17189a[com.ixigua.touchtileimageview.g.ROTATE_270.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static Matrix getMatrixByDegree(Matrix matrix, Matrix matrix2, RectF rectF, com.ixigua.touchtileimageview.g gVar) {
        int i2 = AnonymousClass4.f17189a[gVar.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    Matrix matrix3 = new Matrix(matrix);
                    RectF rectF2 = new RectF();
                    matrix3.mapRect(rectF2, rectF);
                    matrix3.postRotate(180.0f, rectF2.centerX(), rectF2.centerY());
                    return matrix3;
                } else if (i2 == 4) {
                    Matrix matrix4 = new Matrix(matrix2);
                    RectF rectF3 = new RectF();
                    matrix4.mapRect(rectF3, rectF);
                    matrix4.postRotate(180.0f, rectF3.centerX(), rectF3.centerY());
                    return matrix4;
                } else {
                    throw new IllegalArgumentException("unknown imageRotateDegrees");
                }
            }
            return new Matrix(matrix2);
        }
        return new Matrix(matrix);
    }
}
