package com.facebook.yoga;
/* loaded from: classes8.dex */
public class YogaMeasureOutput {
    public static long make(float f2, float f3) {
        int floatToRawIntBits = Float.floatToRawIntBits(f2);
        return Float.floatToRawIntBits(f3) | (floatToRawIntBits << 32);
    }

    public static long make(int i2, int i3) {
        return make(i2, i3);
    }

    public static float getWidth(long j2) {
        return Float.intBitsToFloat((int) ((j2 >> 32) & (-1)));
    }

    public static float getHeight(long j2) {
        return Float.intBitsToFloat((int) (j2 & (-1)));
    }
}
