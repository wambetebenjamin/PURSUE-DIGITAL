package com.ttnet.org.chromium.base;
/* loaded from: classes9.dex */
public class MathUtils {
    public static final float EPSILON = 0.001f;

    public static float clamp(float f2, float f3, float f4) {
        int i2 = (f3 > f4 ? 1 : (f3 == f4 ? 0 : -1));
        float f5 = i2 > 0 ? f4 : f3;
        if (i2 <= 0) {
            f3 = f4;
        }
        return f2 < f5 ? f5 : f2 > f3 ? f3 : f2;
    }

    public static int clamp(int i2, int i3, int i4) {
        int i5 = i3 > i4 ? i4 : i3;
        if (i3 <= i4) {
            i3 = i4;
        }
        return i2 < i5 ? i5 : i2 > i3 ? i3 : i2;
    }

    public static long clamp(long j2, long j3, long j4) {
        int i2 = (j3 > j4 ? 1 : (j3 == j4 ? 0 : -1));
        long j5 = i2 > 0 ? j4 : j3;
        if (i2 <= 0) {
            j3 = j4;
        }
        return j2 < j5 ? j5 : j2 > j3 ? j3 : j2;
    }

    public static int compareLongs(long j2, long j3) {
        int i2 = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
        if (i2 < 0) {
            return -1;
        }
        return i2 == 0 ? 0 : 1;
    }

    public static float flipSignIf(float f2, boolean z) {
        return z ? -f2 : f2;
    }

    public static int flipSignIf(int i2, boolean z) {
        return z ? -i2 : i2;
    }

    public static float interpolate(float f2, float f3, float f4) {
        return f2 + ((f3 - f2) * f4);
    }

    public static float map(float f2, float f3, float f4, float f5, float f6) {
        return f5 + ((f6 - f5) * ((f2 - f3) / (f4 - f3)));
    }

    public static float smoothstep(float f2) {
        return f2 * f2 * (3.0f - (f2 * 2.0f));
    }

    private MathUtils() {
    }

    public static int positiveModulo(int i2, int i3) {
        int i4 = i2 % i3;
        return i4 >= 0 ? i4 : i4 + i3;
    }

    public static float scaleToFitTargetSize(int[] iArr, int i2, int i3) {
        if (iArr.length < 2 || iArr[0] <= 0 || iArr[1] <= 0) {
            throw new IllegalArgumentException("Expected dimensions to have length >= 2 && dimensions[0] > 0 && dimensions[1] > 0");
        }
        float max = Math.max(i2 / iArr[0], i3 / iArr[1]);
        iArr[0] = (int) (iArr[0] * max);
        iArr[1] = (int) (iArr[1] * max);
        return max;
    }

    public static boolean areFloatsEqual(float f2, float f3) {
        return Math.abs(f2 - f3) < 0.001f;
    }

    public static float distance(float f2, float f3, float f4, float f5) {
        float f6 = f4 - f2;
        float f7 = f5 - f3;
        return (float) Math.sqrt((f6 * f6) + (f7 * f7));
    }

    public static double roundTwoDecimalPlaces(double d2) {
        return Math.round(d2 * 100.0d) / 100.0d;
    }
}
