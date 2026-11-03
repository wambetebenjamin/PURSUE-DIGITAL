package com.facebook.imagepipeline.blurhash;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseArray;
import com.meituan.robust.Constants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;
/* loaded from: classes8.dex */
public final class BlurHashDecoder {
    private static final Map charMap;
    private static List<Character> testList;
    private final SparseArray<double[]> cacheCosinesX = new SparseArray<>();
    private final SparseArray<double[]> cacheCosinesY = new SparseArray<>();

    private float coerceIn(float f2, float f3, float f4) {
        return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
    }

    static {
        List<Character> asList = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Character.valueOf(Constants.OBJECT_TYPE), 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '#', Character.valueOf(Typography.dollar), '%', '*', '+', ',', '-', '.', ':', ';', '=', '?', '@', '[', ']', '^', '_', '{', '|', '}', '~');
        testList = asList;
        charMap = convertListBeforeJava8(asList);
    }

    public static Map<Character, Integer> convertListBeforeJava8(List<Character> list) {
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < list.size(); i2++) {
            hashMap.put(list.get(i2), Integer.valueOf(i2));
        }
        return hashMap;
    }

    public void clearCache() {
        this.cacheCosinesX.clear();
        this.cacheCosinesY.clear();
    }

    public Bitmap decode(String str, int i2, int i3, float f2, boolean z) {
        if (str == null || str.length() < 6) {
            return null;
        }
        int decode83 = decode83(str, 0, 1);
        int i4 = (decode83 % 9) + 1;
        int i5 = (decode83 / 9) + 1;
        if (str.length() != (i4 * 2 * i5) + 4) {
            return null;
        }
        float decode832 = (decode83(str, 1, 2) + 1) / 166.0f;
        int i6 = i4 * i5;
        float[][] fArr = new float[i6];
        for (int i7 = 0; i7 < i6; i7++) {
            if (i7 == 0) {
                fArr[i7] = decodeDc(decode83(str, 2, 6));
            } else {
                int i8 = (i7 * 2) + 4;
                fArr[i7] = decodeAc(decode83(str, i8, i8 + 2), decode832 * f2);
            }
        }
        return composeBitmap(i2, i3, i4, i5, fArr, z);
    }

    private int decode83(String str, int i2, int i3) {
        int i4 = 0;
        while (i2 < i3) {
            Integer num = (Integer) charMap.get(Character.valueOf(str.charAt(i2)));
            int intValue = num != null ? num.intValue() : -1;
            if (intValue != -1) {
                i4 = (i4 * 83) + intValue;
            }
            i2++;
        }
        return i4;
    }

    private float[] decodeDc(int i2) {
        return new float[]{srgbToLinear(i2 >> 16), srgbToLinear((i2 >> 8) & 255), srgbToLinear(i2 & 255)};
    }

    private float srgbToLinear(int i2) {
        float f2 = i2 / 255.0f;
        return f2 <= 0.04045f ? f2 / 12.92f : (float) Math.pow((f2 + 0.055f) / 1.055f, 2.4000000953674316d);
    }

    private float[] decodeAc(int i2, float f2) {
        return new float[]{signedPow2(((float) ((i2 / 361) - 9)) / 9.0f) * f2, signedPow2(((float) (((i2 / 19) % 19) - 9)) / 9.0f) * f2, signedPow2(((float) ((i2 % 19) - 9)) / 9.0f) * f2};
    }

    private static float signedPow2(float f2) {
        return Math.copySign((float) Math.pow(f2, 2.0d), f2);
    }

    private Bitmap composeBitmap(int i2, int i3, int i4, int i5, float[][] fArr, boolean z) {
        int[] iArr = new int[i2 * i3];
        boolean z2 = !z || this.cacheCosinesX.get(i3 * i5) == null;
        double[] arrayForCosinesX = getArrayForCosinesX(z2, i2, i4);
        boolean z3 = !z || this.cacheCosinesY.get(i3 * i5) == null;
        double[] arrayForCosinesY = getArrayForCosinesY(z3, i3, i5);
        Log.d("blurhash", "composeBitmap: blurhash decode cosinesX = " + arrayForCosinesX + ", cosinesY = " + arrayForCosinesY);
        if (arrayForCosinesX == null || arrayForCosinesY == null) {
            return null;
        }
        int i6 = 0;
        while (i6 < i3) {
            int i7 = 0;
            while (i7 < i2) {
                float f2 = 0.0f;
                float f3 = 0.0f;
                float f4 = 0.0f;
                int i8 = 0;
                while (i8 < i5) {
                    float f5 = f3;
                    int i9 = 0;
                    float f6 = f4;
                    float f7 = f2;
                    while (i9 < i4) {
                        int i10 = i9;
                        int i11 = i7;
                        int i12 = i6;
                        double[] dArr = arrayForCosinesY;
                        boolean z4 = z3;
                        float cos = (float) (getCos(dArr, z4, i8, i5, i12, i3) * getCos(arrayForCosinesX, z2, i10, i4, i11, i2));
                        float[] fArr2 = fArr[(i8 * i4) + i10];
                        f7 += fArr2[0] * cos;
                        f5 += fArr2[1] * cos;
                        f6 += fArr2[2] * cos;
                        i9 = i10 + 1;
                        i7 = i11;
                        i6 = i12;
                        arrayForCosinesY = dArr;
                        z3 = z4;
                        arrayForCosinesX = arrayForCosinesX;
                    }
                    i8++;
                    f2 = f7;
                    f3 = f5;
                    f4 = f6;
                    i7 = i7;
                }
                int i13 = i7;
                int i14 = i6;
                iArr[i13 + (i2 * i14)] = Color.rgb(linearToSrgb(f2), linearToSrgb(f3), linearToSrgb(f4));
                i7 = i13 + 1;
                i6 = i14;
                arrayForCosinesY = arrayForCosinesY;
            }
            i6++;
        }
        return Bitmap.createBitmap(iArr, i2, i3, Bitmap.Config.ARGB_8888);
    }

    private double[] getArrayForCosinesY(boolean z, int i2, int i3) {
        if (z) {
            int i4 = i2 * i3;
            double[] dArr = new double[i4];
            this.cacheCosinesY.put(i4, dArr);
            return dArr;
        }
        double[] dArr2 = this.cacheCosinesY.get(i2 * i3);
        if (dArr2 != null) {
            return dArr2;
        }
        return null;
    }

    private double[] getArrayForCosinesX(boolean z, int i2, int i3) {
        double[] dArr;
        Log.d("blurhash", "getArrayForCosinesX: calculate = " + z);
        if (z) {
            int i4 = i2 * i3;
            dArr = new double[i4];
            this.cacheCosinesX.put(i4, dArr);
        } else {
            double[] dArr2 = this.cacheCosinesX.get(i2 * i3);
            Log.d("blurhash", "getArrayForCosinesX: value = " + dArr2);
            dArr = dArr2 != null ? dArr2 : null;
        }
        Log.d("blurhash", "getArrayForCosinesX: result = " + dArr);
        return dArr;
    }

    private double getCos(double[] dArr, boolean z, int i2, int i3, int i4, int i5) {
        if (z) {
            dArr[(i3 * i4) + i2] = Math.cos(((i4 * 3.141592653589793d) * i2) / i5);
        }
        return dArr[i2 + (i3 * i4)];
    }

    private int linearToSrgb(float f2) {
        float coerceIn = coerceIn(f2, 0.0f, 1.0f);
        return coerceIn <= 0.0031308f ? (int) ((coerceIn * 12.92f * 255.0f) + 0.5f) : (int) ((((Math.pow(coerceIn, 0.4166666567325592d) * 1.0549999475479126d) - 0.054999999701976776d) * 255.0d) + 0.5d);
    }
}
