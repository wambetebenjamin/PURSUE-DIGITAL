package com.google.zxing.oned;

import com.facebook.imagepipeline.common.RotationOptions;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
/* loaded from: classes9.dex */
public abstract class OneDReader implements Reader {
    public abstract Result decodeRow(int i2, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    @Override // com.google.zxing.Reader
    public void reset() {
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        try {
            return doDecode(binaryBitmap, map);
        } catch (NotFoundException e2) {
            if ((map != null && map.containsKey(DecodeHintType.TRY_HARDER)) && binaryBitmap.isRotateSupported()) {
                BinaryBitmap rotateCounterClockwise = binaryBitmap.rotateCounterClockwise();
                Result doDecode = doDecode(rotateCounterClockwise, map);
                Map<ResultMetadataType, Object> resultMetadata = doDecode.getResultMetadata();
                int i2 = RotationOptions.ROTATE_270;
                if (resultMetadata != null && resultMetadata.containsKey(ResultMetadataType.ORIENTATION)) {
                    i2 = (((Integer) resultMetadata.get(ResultMetadataType.ORIENTATION)).intValue() + RotationOptions.ROTATE_270) % 360;
                }
                doDecode.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(i2));
                ResultPoint[] resultPoints = doDecode.getResultPoints();
                if (resultPoints != null) {
                    int height = rotateCounterClockwise.getHeight();
                    for (int i3 = 0; i3 < resultPoints.length; i3++) {
                        resultPoints[i3] = new ResultPoint((height - resultPoints[i3].getY()) - 1.0f, resultPoints[i3].getX());
                    }
                }
                return doDecode;
            }
            throw e2;
        }
    }

    private Result doDecode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        Map<DecodeHintType, ?> map2;
        int i2;
        int i3;
        EnumMap enumMap = map;
        int width = binaryBitmap.getWidth();
        int height = binaryBitmap.getHeight();
        BitArray bitArray = new BitArray(width);
        int i4 = 0;
        int i5 = 1;
        boolean z = enumMap != null && enumMap.containsKey(DecodeHintType.TRY_HARDER);
        int max = Math.max(1, height >> (z ? 8 : 5));
        int i6 = z ? height : 15;
        int i7 = height / 2;
        int i8 = 0;
        while (i8 < i6) {
            int i9 = i8 + 1;
            int i10 = i9 / 2;
            if (((i8 & 1) == 0 ? i5 : i4) == 0) {
                i10 = -i10;
            }
            int i11 = (i10 * max) + i7;
            if (i11 < 0 || i11 >= height) {
                break;
            }
            try {
                bitArray = binaryBitmap.getBlackRow(i11, bitArray);
                int i12 = i4;
                while (i12 < 2) {
                    if (i12 == i5) {
                        bitArray.reverse();
                        if (enumMap != null && enumMap.containsKey(DecodeHintType.NEED_RESULT_POINT_CALLBACK)) {
                            EnumMap enumMap2 = new EnumMap(DecodeHintType.class);
                            enumMap2.putAll(enumMap);
                            enumMap2.remove(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                            enumMap = enumMap2;
                        }
                    }
                    try {
                        Result decodeRow = decodeRow(i11, bitArray, enumMap);
                        if (i12 == i5) {
                            try {
                                decodeRow.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf((int) RotationOptions.ROTATE_180));
                                ResultPoint[] resultPoints = decodeRow.getResultPoints();
                                if (resultPoints != null) {
                                    map2 = enumMap;
                                    float f2 = width;
                                    try {
                                        i2 = width;
                                    } catch (ReaderException unused) {
                                        i2 = width;
                                        i3 = 1;
                                        i12++;
                                        i5 = i3;
                                        enumMap = map2;
                                        width = i2;
                                        i4 = 0;
                                    }
                                    try {
                                        resultPoints[0] = new ResultPoint((f2 - resultPoints[i4].getX()) - 1.0f, resultPoints[i4].getY());
                                        i3 = 1;
                                        try {
                                            resultPoints[1] = new ResultPoint((f2 - resultPoints[1].getX()) - 1.0f, resultPoints[1].getY());
                                        } catch (ReaderException unused2) {
                                            continue;
                                            i12++;
                                            i5 = i3;
                                            enumMap = map2;
                                            width = i2;
                                            i4 = 0;
                                        }
                                    } catch (ReaderException unused3) {
                                        i3 = 1;
                                        i12++;
                                        i5 = i3;
                                        enumMap = map2;
                                        width = i2;
                                        i4 = 0;
                                    }
                                }
                            } catch (ReaderException unused4) {
                                map2 = enumMap;
                            }
                        }
                        return decodeRow;
                    } catch (ReaderException unused5) {
                        map2 = enumMap;
                        i2 = width;
                        i3 = i5;
                    }
                }
                continue;
            } catch (NotFoundException unused6) {
            }
            i8 = i9;
            i5 = i5;
            width = width;
            i4 = 0;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void recordPattern(BitArray bitArray, int i2, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int i3 = 0;
        Arrays.fill(iArr, 0, length, 0);
        int size = bitArray.getSize();
        if (i2 >= size) {
            throw NotFoundException.getNotFoundInstance();
        }
        boolean z = !bitArray.get(i2);
        while (i2 < size) {
            if (bitArray.get(i2) == z) {
                i3++;
                if (i3 == length) {
                    break;
                }
                iArr[i3] = 1;
                z = !z;
            } else {
                iArr[i3] = iArr[i3] + 1;
            }
            i2++;
        }
        if (i3 != length) {
            if (i3 != length - 1 || i2 != size) {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void recordPatternInReverse(BitArray bitArray, int i2, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        boolean z = bitArray.get(i2);
        while (i2 > 0 && length >= 0) {
            i2--;
            if (bitArray.get(i2) != z) {
                length--;
                z = !z;
            }
        }
        if (length >= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        recordPattern(bitArray, i2 + 1, iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static float patternMatchVariance(int[] iArr, int[] iArr2, float f2) {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            i2 += iArr[i4];
            i3 += iArr2[i4];
        }
        if (i2 < i3) {
            return Float.POSITIVE_INFINITY;
        }
        float f3 = i2;
        float f4 = f3 / i3;
        float f5 = f2 * f4;
        float f6 = 0.0f;
        for (int i5 = 0; i5 < length; i5++) {
            float f7 = iArr2[i5] * f4;
            float f8 = iArr[i5];
            float f9 = f8 > f7 ? f8 - f7 : f7 - f8;
            if (f9 > f5) {
                return Float.POSITIVE_INFINITY;
            }
            f6 += f9;
        }
        return f6 / f3;
    }
}
