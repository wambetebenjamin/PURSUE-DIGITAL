package com.appsflyer.internal;
/* loaded from: classes.dex */
public final class AFj1oSDK {
    public int AFInAppEventParameterName;
    public int AFKeystoreWrapper;

    public static char[] values(long j2, char[] cArr, int i2) {
        int length = cArr.length;
        char[] cArr2 = new char[length];
        int i3 = 4;
        int i4 = 0;
        for (int i5 = 0; i5 < cArr.length; i5++) {
            if (((j2 >>> i5) & 1) == i2 && i4 < 4) {
                cArr2[i4] = cArr[i5];
            } else if (i3 < length) {
                cArr2[i3] = cArr[i5];
                i3++;
            } else {
                cArr2[i4] = cArr[i5];
            }
            i4++;
        }
        return cArr2;
    }
}
