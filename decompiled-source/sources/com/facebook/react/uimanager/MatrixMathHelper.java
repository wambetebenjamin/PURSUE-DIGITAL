package com.facebook.react.uimanager;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.reflect.Array;
/* loaded from: classes8.dex */
public class MatrixMathHelper {
    private static final double EPSILON = 1.0E-5d;

    public static double degreesToRadians(double d2) {
        return (d2 * 3.141592653589793d) / 180.0d;
    }

    /* loaded from: classes8.dex */
    public static class MatrixDecompositionContext {
        double[] perspective = new double[4];
        double[] scale = new double[3];
        double[] skew = new double[3];
        double[] translation = new double[3];
        double[] rotationDegrees = new double[3];

        private static void resetArray(double[] dArr) {
            for (int i2 = 0; i2 < dArr.length; i2++) {
                dArr[i2] = 0.0d;
            }
        }

        public void reset() {
            resetArray(this.perspective);
            resetArray(this.scale);
            resetArray(this.skew);
            resetArray(this.translation);
            resetArray(this.rotationDegrees);
        }
    }

    private static boolean isZero(double d2) {
        return !Double.isNaN(d2) && Math.abs(d2) < 1.0E-5d;
    }

    public static void multiplyInto(double[] dArr, double[] dArr2, double[] dArr3) {
        double d2 = dArr2[0];
        double d3 = dArr2[1];
        double d4 = dArr2[2];
        double d5 = dArr2[3];
        double d6 = dArr2[4];
        double d7 = dArr2[5];
        double d8 = dArr2[6];
        double d9 = dArr2[7];
        double d10 = dArr2[8];
        double d11 = dArr2[9];
        double d12 = dArr2[10];
        double d13 = dArr2[11];
        double d14 = dArr2[12];
        double d15 = dArr2[13];
        double d16 = dArr2[14];
        double d17 = dArr2[15];
        double d18 = dArr3[0];
        double d19 = dArr3[1];
        double d20 = dArr3[2];
        double d21 = dArr3[3];
        dArr[0] = (d18 * d2) + (d19 * d6) + (d20 * d10) + (d21 * d14);
        dArr[1] = (d18 * d3) + (d19 * d7) + (d20 * d11) + (d21 * d15);
        dArr[2] = (d18 * d4) + (d19 * d8) + (d20 * d12) + (d21 * d16);
        dArr[3] = (d18 * d5) + (d19 * d9) + (d20 * d13) + (d21 * d17);
        double d22 = dArr3[4];
        double d23 = dArr3[5];
        double d24 = dArr3[6];
        double d25 = dArr3[7];
        dArr[4] = (d22 * d2) + (d23 * d6) + (d24 * d10) + (d25 * d14);
        dArr[5] = (d22 * d3) + (d23 * d7) + (d24 * d11) + (d25 * d15);
        dArr[6] = (d22 * d4) + (d23 * d8) + (d24 * d12) + (d25 * d16);
        dArr[7] = (d22 * d5) + (d23 * d9) + (d24 * d13) + (d25 * d17);
        double d26 = dArr3[8];
        double d27 = dArr3[9];
        double d28 = dArr3[10];
        double d29 = dArr3[11];
        dArr[8] = (d26 * d2) + (d27 * d6) + (d28 * d10) + (d29 * d14);
        dArr[9] = (d26 * d3) + (d27 * d7) + (d28 * d11) + (d29 * d15);
        dArr[10] = (d26 * d4) + (d27 * d8) + (d28 * d12) + (d29 * d16);
        dArr[11] = (d26 * d5) + (d27 * d9) + (d28 * d13) + (d29 * d17);
        double d30 = dArr3[12];
        double d31 = dArr3[13];
        double d32 = dArr3[14];
        double d33 = dArr3[15];
        dArr[12] = (d2 * d30) + (d6 * d31) + (d10 * d32) + (d14 * d33);
        dArr[13] = (d3 * d30) + (d7 * d31) + (d11 * d32) + (d15 * d33);
        dArr[14] = (d4 * d30) + (d8 * d31) + (d12 * d32) + (d16 * d33);
        dArr[15] = (d30 * d5) + (d31 * d9) + (d32 * d13) + (d33 * d17);
    }

    public static void decomposeMatrix(double[] dArr, MatrixDecompositionContext matrixDecompositionContext) {
        if (dArr.length != 16) {
            return;
        }
        double[] dArr2 = matrixDecompositionContext.perspective;
        double[] dArr3 = matrixDecompositionContext.scale;
        double[] dArr4 = matrixDecompositionContext.skew;
        double[] dArr5 = matrixDecompositionContext.translation;
        double[] dArr6 = matrixDecompositionContext.rotationDegrees;
        if (isZero(dArr[15])) {
            return;
        }
        double[][] dArr7 = (double[][]) Array.newInstance(double.class, 4, 4);
        double[] dArr8 = new double[16];
        for (int i2 = 0; i2 < 4; i2++) {
            for (int i3 = 0; i3 < 4; i3++) {
                int i4 = (i2 * 4) + i3;
                double d2 = dArr[i4] / dArr[15];
                dArr7[i2][i3] = d2;
                if (i3 == 3) {
                    d2 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                }
                dArr8[i4] = d2;
            }
        }
        dArr8[15] = 1.0d;
        if (isZero(determinant(dArr8))) {
            return;
        }
        if (!isZero(dArr7[0][3]) || !isZero(dArr7[1][3]) || !isZero(dArr7[2][3])) {
            multiplyVectorByMatrix(new double[]{dArr7[0][3], dArr7[1][3], dArr7[2][3], dArr7[3][3]}, transpose(inverse(dArr8)), dArr2);
        } else {
            dArr2[2] = 0.0d;
            dArr2[1] = 0.0d;
            dArr2[0] = 0.0d;
            dArr2[3] = 1.0d;
        }
        for (int i5 = 0; i5 < 3; i5++) {
            dArr5[i5] = dArr7[3][i5];
        }
        double[][] dArr9 = (double[][]) Array.newInstance(double.class, 3, 3);
        for (int i6 = 0; i6 < 3; i6++) {
            dArr9[i6][0] = dArr7[i6][0];
            dArr9[i6][1] = dArr7[i6][1];
            dArr9[i6][2] = dArr7[i6][2];
        }
        dArr3[0] = v3Length(dArr9[0]);
        dArr9[0] = v3Normalize(dArr9[0], dArr3[0]);
        dArr4[0] = v3Dot(dArr9[0], dArr9[1]);
        dArr9[1] = v3Combine(dArr9[1], dArr9[0], 1.0d, -dArr4[0]);
        dArr4[0] = v3Dot(dArr9[0], dArr9[1]);
        dArr9[1] = v3Combine(dArr9[1], dArr9[0], 1.0d, -dArr4[0]);
        dArr3[1] = v3Length(dArr9[1]);
        dArr9[1] = v3Normalize(dArr9[1], dArr3[1]);
        dArr4[0] = dArr4[0] / dArr3[1];
        dArr4[1] = v3Dot(dArr9[0], dArr9[2]);
        dArr9[2] = v3Combine(dArr9[2], dArr9[0], 1.0d, -dArr4[1]);
        dArr4[2] = v3Dot(dArr9[1], dArr9[2]);
        dArr9[2] = v3Combine(dArr9[2], dArr9[1], 1.0d, -dArr4[2]);
        dArr3[2] = v3Length(dArr9[2]);
        dArr9[2] = v3Normalize(dArr9[2], dArr3[2]);
        dArr4[1] = dArr4[1] / dArr3[2];
        dArr4[2] = dArr4[2] / dArr3[2];
        if (v3Dot(dArr9[0], v3Cross(dArr9[1], dArr9[2])) < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            for (int i7 = 0; i7 < 3; i7++) {
                dArr3[i7] = dArr3[i7] * (-1.0d);
                double[] dArr10 = dArr9[i7];
                dArr10[0] = dArr10[0] * (-1.0d);
                double[] dArr11 = dArr9[i7];
                dArr11[1] = dArr11[1] * (-1.0d);
                double[] dArr12 = dArr9[i7];
                dArr12[2] = dArr12[2] * (-1.0d);
            }
        }
        dArr6[0] = roundTo3Places((-Math.atan2(dArr9[2][1], dArr9[2][2])) * 57.29577951308232d);
        dArr6[1] = roundTo3Places((-Math.atan2(-dArr9[2][0], Math.sqrt((dArr9[2][1] * dArr9[2][1]) + (dArr9[2][2] * dArr9[2][2])))) * 57.29577951308232d);
        dArr6[2] = roundTo3Places((-Math.atan2(dArr9[1][0], dArr9[0][0])) * 57.29577951308232d);
    }

    public static double determinant(double[] dArr) {
        double d2 = dArr[0];
        double d3 = dArr[1];
        double d4 = dArr[2];
        double d5 = dArr[3];
        double d6 = dArr[4];
        double d7 = dArr[5];
        double d8 = dArr[6];
        double d9 = dArr[7];
        double d10 = dArr[8];
        double d11 = dArr[9];
        double d12 = dArr[10];
        double d13 = dArr[11];
        double d14 = dArr[12];
        double d15 = dArr[13];
        double d16 = dArr[14];
        double d17 = dArr[15];
        double d18 = d5 * d8;
        double d19 = d4 * d9;
        double d20 = d5 * d7;
        double d21 = d3 * d9;
        double d22 = d4 * d7;
        double d23 = d3 * d8;
        double d24 = d5 * d6;
        double d25 = d9 * d2;
        double d26 = d4 * d6;
        double d27 = d8 * d2;
        double d28 = d3 * d6;
        double d29 = d2 * d7;
        return ((((((((((((((((((((((((d18 * d11) * d14) - ((d19 * d11) * d14)) - ((d20 * d12) * d14)) + ((d21 * d12) * d14)) + ((d22 * d13) * d14)) - ((d23 * d13) * d14)) - ((d18 * d10) * d15)) + ((d19 * d10) * d15)) + ((d24 * d12) * d15)) - ((d25 * d12) * d15)) - ((d26 * d13) * d15)) + ((d27 * d13) * d15)) + ((d20 * d10) * d16)) - ((d21 * d10) * d16)) - ((d24 * d11) * d16)) + ((d25 * d11) * d16)) + ((d28 * d13) * d16)) - ((d13 * d29) * d16)) - ((d22 * d10) * d17)) + ((d23 * d10) * d17)) + ((d26 * d11) * d17)) - ((d27 * d11) * d17)) - ((d28 * d12) * d17)) + (d29 * d12 * d17);
    }

    public static double[] inverse(double[] dArr) {
        double determinant = determinant(dArr);
        if (isZero(determinant)) {
            return dArr;
        }
        double d2 = dArr[0];
        double d3 = dArr[1];
        double d4 = dArr[2];
        double d5 = dArr[3];
        double d6 = dArr[4];
        double d7 = dArr[5];
        double d8 = dArr[6];
        double d9 = dArr[7];
        double d10 = dArr[8];
        double d11 = dArr[9];
        double d12 = dArr[10];
        double d13 = dArr[11];
        double d14 = dArr[12];
        double d15 = dArr[13];
        double d16 = dArr[14];
        double d17 = dArr[15];
        double d18 = d8 * d13;
        double d19 = d9 * d12;
        double d20 = d9 * d11;
        double d21 = d7 * d13;
        double d22 = d8 * d11;
        double d23 = ((((d18 * d15) - (d19 * d15)) + (d20 * d16)) - (d21 * d16)) - (d22 * d17);
        double d24 = d7 * d12;
        double d25 = d5 * d12;
        double d26 = d4 * d13;
        double d27 = d5 * d11;
        double d28 = d3 * d13;
        double d29 = d4 * d11;
        double d30 = (((d25 * d15) - (d26 * d15)) - (d27 * d16)) + (d28 * d16) + (d29 * d17);
        double d31 = d3 * d12;
        double d32 = d4 * d9;
        double d33 = d5 * d8;
        double d34 = d5 * d7;
        double d35 = d3 * d9;
        double d36 = d4 * d7;
        double d37 = d3 * d8;
        double d38 = (d19 * d14) - (d18 * d14);
        double d39 = d9 * d10;
        double d40 = d6 * d13;
        double d41 = d8 * d10;
        double d42 = (d38 - (d39 * d16)) + (d40 * d16) + (d41 * d17);
        double d43 = d6 * d12;
        double d44 = (d26 * d14) - (d25 * d14);
        double d45 = d5 * d10;
        double d46 = d2 * d13;
        double d47 = d4 * d10;
        double d48 = d2 * d12;
        double d49 = d5 * d6;
        double d50 = d9 * d2;
        double d51 = d4 * d6;
        double d52 = d8 * d2;
        double d53 = d7 * d10;
        double d54 = ((((d21 * d14) - (d20 * d14)) + (d39 * d15)) - (d40 * d15)) - (d53 * d17);
        double d55 = d6 * d11;
        double d56 = d3 * d10;
        double d57 = d2 * d11;
        double d58 = d3 * d6;
        double d59 = d2 * d7;
        return new double[]{(d23 + (d24 * d17)) / determinant, (d30 - (d31 * d17)) / determinant, ((((((d32 * d15) - (d33 * d15)) + (d34 * d16)) - (d35 * d16)) - (d36 * d17)) + (d37 * d17)) / determinant, ((((((d33 * d11) - (d32 * d11)) - (d34 * d12)) + (d35 * d12)) + (d36 * d13)) - (d37 * d13)) / determinant, (d42 - (d43 * d17)) / determinant, ((((d44 + (d45 * d16)) - (d46 * d16)) - (d47 * d17)) + (d48 * d17)) / determinant, ((((((d33 * d14) - (d32 * d14)) - (d49 * d16)) + (d50 * d16)) + (d51 * d17)) - (d52 * d17)) / determinant, ((((((d32 * d10) - (d33 * d10)) + (d49 * d12)) - (d50 * d12)) - (d51 * d13)) + (d52 * d13)) / determinant, (d54 + (d55 * d17)) / determinant, ((((((d27 * d14) - (d28 * d14)) - (d45 * d15)) + (d46 * d15)) + (d56 * d17)) - (d57 * d17)) / determinant, ((((((d35 * d14) - (d34 * d14)) + (d49 * d15)) - (d50 * d15)) - (d58 * d17)) + (d17 * d59)) / determinant, ((((((d34 * d10) - (d35 * d10)) - (d49 * d11)) + (d50 * d11)) + (d58 * d13)) - (d13 * d59)) / determinant, ((((((d22 * d14) - (d24 * d14)) - (d41 * d15)) + (d43 * d15)) + (d53 * d16)) - (d55 * d16)) / determinant, ((((((d31 * d14) - (d29 * d14)) + (d47 * d15)) - (d48 * d15)) - (d56 * d16)) + (d57 * d16)) / determinant, ((((((d36 * d14) - (d14 * d37)) - (d51 * d15)) + (d15 * d52)) + (d58 * d16)) - (d16 * d59)) / determinant, ((((((d37 * d10) - (d36 * d10)) + (d51 * d11)) - (d52 * d11)) - (d58 * d12)) + (d59 * d12)) / determinant};
    }

    public static double[] transpose(double[] dArr) {
        return new double[]{dArr[0], dArr[4], dArr[8], dArr[12], dArr[1], dArr[5], dArr[9], dArr[13], dArr[2], dArr[6], dArr[10], dArr[14], dArr[3], dArr[7], dArr[11], dArr[15]};
    }

    public static void multiplyVectorByMatrix(double[] dArr, double[] dArr2, double[] dArr3) {
        double d2 = dArr[0];
        double d3 = dArr[1];
        double d4 = dArr[2];
        double d5 = dArr[3];
        dArr3[0] = (dArr2[0] * d2) + (dArr2[4] * d3) + (dArr2[8] * d4) + (dArr2[12] * d5);
        dArr3[1] = (dArr2[1] * d2) + (dArr2[5] * d3) + (dArr2[9] * d4) + (dArr2[13] * d5);
        dArr3[2] = (dArr2[2] * d2) + (dArr2[6] * d3) + (dArr2[10] * d4) + (dArr2[14] * d5);
        dArr3[3] = (d2 * dArr2[3]) + (d3 * dArr2[7]) + (d4 * dArr2[11]) + (d5 * dArr2[15]);
    }

    public static double v3Length(double[] dArr) {
        return Math.sqrt((dArr[0] * dArr[0]) + (dArr[1] * dArr[1]) + (dArr[2] * dArr[2]));
    }

    public static double[] v3Normalize(double[] dArr, double d2) {
        if (isZero(d2)) {
            d2 = v3Length(dArr);
        }
        double d3 = 1.0d / d2;
        return new double[]{dArr[0] * d3, dArr[1] * d3, dArr[2] * d3};
    }

    public static double v3Dot(double[] dArr, double[] dArr2) {
        return (dArr[0] * dArr2[0]) + (dArr[1] * dArr2[1]) + (dArr[2] * dArr2[2]);
    }

    public static double[] v3Combine(double[] dArr, double[] dArr2, double d2, double d3) {
        return new double[]{(dArr[0] * d2) + (dArr2[0] * d3), (dArr[1] * d2) + (dArr2[1] * d3), (d2 * dArr[2]) + (d3 * dArr2[2])};
    }

    public static double[] v3Cross(double[] dArr, double[] dArr2) {
        return new double[]{(dArr[1] * dArr2[2]) - (dArr[2] * dArr2[1]), (dArr[2] * dArr2[0]) - (dArr[0] * dArr2[2]), (dArr[0] * dArr2[1]) - (dArr[1] * dArr2[0])};
    }

    public static double roundTo3Places(double d2) {
        return Math.round(d2 * 1000.0d) * 0.001d;
    }

    public static double[] createIdentityMatrix() {
        double[] dArr = new double[16];
        resetIdentityMatrix(dArr);
        return dArr;
    }

    public static void resetIdentityMatrix(double[] dArr) {
        dArr[14] = 0.0d;
        dArr[13] = 0.0d;
        dArr[12] = 0.0d;
        dArr[11] = 0.0d;
        dArr[9] = 0.0d;
        dArr[8] = 0.0d;
        dArr[7] = 0.0d;
        dArr[6] = 0.0d;
        dArr[4] = 0.0d;
        dArr[3] = 0.0d;
        dArr[2] = 0.0d;
        dArr[1] = 0.0d;
        dArr[15] = 1.0d;
        dArr[10] = 1.0d;
        dArr[5] = 1.0d;
        dArr[0] = 1.0d;
    }

    public static void applyPerspective(double[] dArr, double d2) {
        dArr[11] = (-1.0d) / d2;
    }

    public static void applyScaleX(double[] dArr, double d2) {
        dArr[0] = d2;
    }

    public static void applyScaleY(double[] dArr, double d2) {
        dArr[5] = d2;
    }

    public static void applyScaleZ(double[] dArr, double d2) {
        dArr[10] = d2;
    }

    public static void applyTranslate2D(double[] dArr, double d2, double d3) {
        dArr[12] = d2;
        dArr[13] = d3;
    }

    public static void applyTranslate3D(double[] dArr, double d2, double d3, double d4) {
        dArr[12] = d2;
        dArr[13] = d3;
        dArr[14] = d4;
    }

    public static void applySkewX(double[] dArr, double d2) {
        dArr[4] = Math.tan(d2);
    }

    public static void applySkewY(double[] dArr, double d2) {
        dArr[1] = Math.tan(d2);
    }

    public static void applyRotateX(double[] dArr, double d2) {
        dArr[5] = Math.cos(d2);
        dArr[6] = Math.sin(d2);
        dArr[9] = -Math.sin(d2);
        dArr[10] = Math.cos(d2);
    }

    public static void applyRotateY(double[] dArr, double d2) {
        dArr[0] = Math.cos(d2);
        dArr[2] = -Math.sin(d2);
        dArr[8] = Math.sin(d2);
        dArr[10] = Math.cos(d2);
    }

    public static void applyRotateZ(double[] dArr, double d2) {
        dArr[0] = Math.cos(d2);
        dArr[1] = Math.sin(d2);
        dArr[4] = -Math.sin(d2);
        dArr[5] = Math.cos(d2);
    }
}
