package com.google.zxing.pdf417.decoder;

import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.pdf417.PDF417Common;
import java.lang.reflect.Array;
/* loaded from: classes9.dex */
final class PDF417CodewordDecoder {
    private static final float[][] RATIOS_TABLE = (float[][]) Array.newInstance(float.class, PDF417Common.SYMBOL_TABLE.length, 8);

    static {
        int i2;
        for (int i3 = 0; i3 < PDF417Common.SYMBOL_TABLE.length; i3++) {
            int i4 = PDF417Common.SYMBOL_TABLE[i3];
            int i5 = i4 & 1;
            int i6 = 0;
            while (i6 < 8) {
                float f2 = 0.0f;
                while (true) {
                    i2 = i4 & 1;
                    if (i2 == i5) {
                        f2 += 1.0f;
                        i4 >>= 1;
                    }
                }
                RATIOS_TABLE[i3][(8 - i6) - 1] = f2 / 17.0f;
                i6++;
                i5 = i2;
            }
        }
    }

    private PDF417CodewordDecoder() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getDecodedValue(int[] iArr) {
        int decodedCodewordValue = getDecodedCodewordValue(sampleBitCounts(iArr));
        return decodedCodewordValue != -1 ? decodedCodewordValue : getClosestDecodedValue(iArr);
    }

    private static int[] sampleBitCounts(int[] iArr) {
        float sum = MathUtils.sum(iArr);
        int[] iArr2 = new int[8];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 17; i4++) {
            if (iArr[i3] + i2 <= (sum / 34.0f) + ((i4 * sum) / 17.0f)) {
                i2 += iArr[i3];
                i3++;
            }
            iArr2[i3] = iArr2[i3] + 1;
        }
        return iArr2;
    }

    private static int getDecodedCodewordValue(int[] iArr) {
        int bitValue = getBitValue(iArr);
        if (PDF417Common.getCodeword(bitValue) == -1) {
            return -1;
        }
        return bitValue;
    }

    private static int getBitValue(int[] iArr) {
        long j2 = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            for (int i3 = 0; i3 < iArr[i2]; i3++) {
                int i4 = 1;
                long j3 = j2 << 1;
                if (i2 % 2 != 0) {
                    i4 = 0;
                }
                j2 = j3 | i4;
            }
        }
        return (int) j2;
    }

    private static int getClosestDecodedValue(int[] iArr) {
        int sum = MathUtils.sum(iArr);
        float[] fArr = new float[8];
        if (sum > 1) {
            for (int i2 = 0; i2 < 8; i2++) {
                fArr[i2] = iArr[i2] / sum;
            }
        }
        float f2 = Float.MAX_VALUE;
        int i3 = -1;
        int i4 = 0;
        while (true) {
            float[][] fArr2 = RATIOS_TABLE;
            if (i4 >= fArr2.length) {
                return i3;
            }
            float f3 = 0.0f;
            float[] fArr3 = fArr2[i4];
            for (int i5 = 0; i5 < 8; i5++) {
                float f4 = fArr3[i5] - fArr[i5];
                f3 += f4 * f4;
                if (f3 >= f2) {
                    break;
                }
            }
            if (f3 < f2) {
                i3 = PDF417Common.SYMBOL_TABLE[i4];
                f2 = f3;
            }
            i4++;
        }
    }
}
