package com.ixigua.touchtileimageview.utils;
/* compiled from: FloatUtils.java */
/* loaded from: classes9.dex */
public class d {
    public static boolean isEqual(float f2, float f3) {
        return Math.abs(f2 - f3) < 1.0f;
    }

    public static boolean isGreaterThanEqual(float f2, float f3) {
        return f2 >= f3 || isEqual(f2, f3);
    }

    public static boolean isGreaterThan(float f2, float f3) {
        return f2 > f3 && !isEqual(f2, f3);
    }

    public static boolean isLessThanEqual(float f2, float f3) {
        return f2 <= f3 || isEqual(f2, f3);
    }

    public static boolean isLessThan(float f2, float f3) {
        return f2 < f3 && !isEqual(f2, f3);
    }

    public static boolean isExactlyEqual(float f2, float f3) {
        return Math.abs(f2 - f3) < 0.001f;
    }

    public static boolean isExactlyGreaterThan(float f2, float f3) {
        return f2 > f3 && !isExactlyEqual(f2, f3);
    }

    public static boolean isExactlyLessThanEqual(float f2, float f3) {
        return f2 <= f3 || isExactlyEqual(f2, f3);
    }

    public static boolean isExactlyLessThan(float f2, float f3) {
        return f2 < f3 && !isExactlyEqual(f2, f3);
    }
}
