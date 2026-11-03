package com.appsflyer.internal;
/* loaded from: classes.dex */
public final class AFj1tSDK {
    public int AFInAppEventType;
    public int AFKeystoreWrapper;
    public int valueOf;

    public static void AFKeystoreWrapper(int[] iArr) {
        for (int i2 = 0; i2 < iArr.length / 2; i2++) {
            int i3 = iArr[i2];
            iArr[i2] = iArr[(iArr.length - i2) - 1];
            iArr[(iArr.length - i2) - 1] = i3;
        }
    }

    public static int values(int i2) {
        AFj1vSDK aFj1vSDK = AFj1vSDK.AFInAppEventParameterName;
        return ((aFj1vSDK.AFKeystoreWrapper[0][(i2 >>> 24) & 255] + aFj1vSDK.AFKeystoreWrapper[1][(i2 >>> 16) & 255]) ^ aFj1vSDK.AFKeystoreWrapper[2][(i2 >>> 8) & 255]) + aFj1vSDK.AFKeystoreWrapper[3][i2 & 255];
    }
}
