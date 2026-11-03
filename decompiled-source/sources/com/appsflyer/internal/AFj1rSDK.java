package com.appsflyer.internal;
/* loaded from: classes.dex */
public final class AFj1rSDK {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static long[] AFInAppEventType(int i2, int i3) {
        long[] jArr = new long[4];
        jArr[0] = (i3 & 4294967295L) | ((i2 & 4294967295L) << 32);
        for (int i4 = 1; i4 < 4; i4++) {
            long j2 = jArr[i4 - 1];
            jArr[i4] = ((j2 ^ (j2 >> 30)) * 1812433253) + i4;
        }
        return jArr;
    }
}
