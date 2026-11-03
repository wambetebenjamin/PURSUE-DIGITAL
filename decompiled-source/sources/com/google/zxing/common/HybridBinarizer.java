package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import java.lang.reflect.Array;
/* loaded from: classes9.dex */
public final class HybridBinarizer extends GlobalHistogramBinarizer {
    private static final int BLOCK_SIZE = 8;
    private static final int BLOCK_SIZE_MASK = 7;
    private static final int BLOCK_SIZE_POWER = 3;
    private static final int MINIMUM_DIMENSION = 40;
    private static final int MIN_DYNAMIC_RANGE = 24;
    private BitMatrix matrix;

    private static int cap(int i2, int i3, int i4) {
        return i2 < i3 ? i3 : i2 > i4 ? i4 : i2;
    }

    public HybridBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
    }

    @Override // com.google.zxing.common.GlobalHistogramBinarizer, com.google.zxing.Binarizer
    public BitMatrix getBlackMatrix() throws NotFoundException {
        BitMatrix bitMatrix = this.matrix;
        if (bitMatrix != null) {
            return bitMatrix;
        }
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        int height = luminanceSource.getHeight();
        if (width >= 40 && height >= 40) {
            byte[] matrix = luminanceSource.getMatrix();
            int i2 = width >> 3;
            if ((width & 7) != 0) {
                i2++;
            }
            int i3 = i2;
            int i4 = height >> 3;
            if ((height & 7) != 0) {
                i4++;
            }
            int i5 = i4;
            int[][] calculateBlackPoints = calculateBlackPoints(matrix, i3, i5, width, height);
            BitMatrix bitMatrix2 = new BitMatrix(width, height);
            calculateThresholdForBlock(matrix, i3, i5, width, height, calculateBlackPoints, bitMatrix2);
            this.matrix = bitMatrix2;
        } else {
            this.matrix = super.getBlackMatrix();
        }
        return this.matrix;
    }

    @Override // com.google.zxing.common.GlobalHistogramBinarizer, com.google.zxing.Binarizer
    public Binarizer createBinarizer(LuminanceSource luminanceSource) {
        return new HybridBinarizer(luminanceSource);
    }

    private static void calculateThresholdForBlock(byte[] bArr, int i2, int i3, int i4, int i5, int[][] iArr, BitMatrix bitMatrix) {
        int i6 = i5 - 8;
        int i7 = i4 - 8;
        for (int i8 = 0; i8 < i3; i8++) {
            int i9 = i8 << 3;
            int i10 = i9 > i6 ? i6 : i9;
            int cap = cap(i8, 2, i3 - 3);
            for (int i11 = 0; i11 < i2; i11++) {
                int i12 = i11 << 3;
                int i13 = i12 > i7 ? i7 : i12;
                int cap2 = cap(i11, 2, i2 - 3);
                int i14 = 0;
                for (int i15 = -2; i15 <= 2; i15++) {
                    int[] iArr2 = iArr[cap + i15];
                    i14 += iArr2[cap2 - 2] + iArr2[cap2 - 1] + iArr2[cap2] + iArr2[cap2 + 1] + iArr2[cap2 + 2];
                }
                thresholdBlock(bArr, i13, i10, i14 / 25, i4, bitMatrix);
            }
        }
    }

    private static void thresholdBlock(byte[] bArr, int i2, int i3, int i4, int i5, BitMatrix bitMatrix) {
        int i6 = (i3 * i5) + i2;
        int i7 = 0;
        while (i7 < 8) {
            for (int i8 = 0; i8 < 8; i8++) {
                if ((bArr[i6 + i8] & 255) <= i4) {
                    bitMatrix.set(i2 + i8, i3 + i7);
                }
            }
            i7++;
            i6 += i5;
        }
    }

    private static int[][] calculateBlackPoints(byte[] bArr, int i2, int i3, int i4, int i5) {
        char c2;
        int i6 = 8;
        int i7 = i5 - 8;
        int i8 = i4 - 8;
        char c3 = 2;
        boolean z = true;
        int i9 = 0;
        int[][] iArr = (int[][]) Array.newInstance(int.class, i3, i2);
        int i10 = 0;
        while (i10 < i3) {
            int i11 = i10 << 3;
            if (i11 > i7) {
                i11 = i7;
            }
            int i12 = i9;
            while (i12 < i2) {
                int i13 = i12 << 3;
                if (i13 > i8) {
                    i13 = i8;
                }
                int i14 = (i11 * i4) + i13;
                int i15 = i9;
                int i16 = i15;
                int i17 = i16;
                int i18 = 255;
                while (i15 < i6) {
                    int i19 = i17;
                    int i20 = 0;
                    while (i20 < i6) {
                        int i21 = bArr[i14 + i20] & 255;
                        i16 += i21;
                        if (i21 < i18) {
                            i18 = i21;
                        }
                        if (i21 > i19) {
                            i19 = i21;
                        }
                        i20++;
                        i6 = 8;
                    }
                    if (i19 - i18 <= 24) {
                        i15++;
                        i14 += i4;
                        i17 = i19;
                        z = true;
                        i6 = 8;
                    }
                    while (true) {
                        i15++;
                        i14 += i4;
                        if (i15 < 8) {
                            int i22 = 0;
                            for (int i23 = 8; i22 < i23; i23 = 8) {
                                i16 += bArr[i14 + i22] & 255;
                                i22++;
                            }
                        }
                    }
                    i15++;
                    i14 += i4;
                    i17 = i19;
                    z = true;
                    i6 = 8;
                }
                boolean z2 = z;
                int i24 = i16 >> 6;
                if (i17 - i18 <= 24) {
                    i24 = i18 / 2;
                    if (i10 > 0 && i12 > 0) {
                        int i25 = i10 - 1;
                        int i26 = i12 - 1;
                        c2 = 2;
                        int i27 = ((iArr[i25][i12] + (iArr[i10][i26] * 2)) + iArr[i25][i26]) / 4;
                        if (i18 < i27) {
                            i24 = i27;
                        }
                        iArr[i10][i12] = i24;
                        i12++;
                        z = z2;
                        c3 = c2;
                        i6 = 8;
                        i9 = 0;
                    }
                }
                c2 = 2;
                iArr[i10][i12] = i24;
                i12++;
                z = z2;
                c3 = c2;
                i6 = 8;
                i9 = 0;
            }
            i10++;
            i6 = 8;
            i9 = 0;
        }
        return iArr;
    }
}
