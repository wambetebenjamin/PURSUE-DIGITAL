package com.appsflyer.internal;
/* loaded from: classes.dex */
public final class AFj1xSDK {
    public static void AFInAppEventParameterName(int i2, int i3, boolean z, int i4, int[] iArr, int[][] iArr2, int[] iArr3) {
        if (!z) {
            valueOf(iArr);
        }
        int i5 = i2;
        int i6 = i3;
        int i7 = 0;
        while (i7 < i4) {
            int i8 = i5 ^ iArr[i7];
            int i9 = i6 ^ ((iArr2[2][(i8 >>> 8) & 255] ^ (iArr2[0][i8 >>> 24] + iArr2[1][(i8 >>> 16) & 255])) + iArr2[3][i8 & 255]);
            i7++;
            i6 = i8;
            i5 = i9;
        }
        int i10 = i5 ^ iArr[iArr.length - 2];
        int i11 = i6 ^ iArr[iArr.length - 1];
        if (!z) {
            valueOf(iArr);
        }
        iArr3[0] = i11;
        iArr3[1] = i10;
    }

    private static void valueOf(int[] iArr) {
        for (int i2 = 0; i2 < iArr.length / 2; i2++) {
            int i3 = iArr[i2];
            iArr[i2] = iArr[(iArr.length - i2) - 1];
            iArr[(iArr.length - i2) - 1] = i3;
        }
    }
}
