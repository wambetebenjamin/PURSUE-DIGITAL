package com.google.android.play.core.assetpacks;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class br {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(byte[] bArr, int i2) {
        return ((bArr[i2 + 1] & 255) << 8) | (bArr[i2] & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(byte[] bArr, int i2) {
        return (bArr[i2 + 3] & 255) | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long c(byte[] bArr, int i2) {
        int i3 = i2 + 2;
        return ((a(bArr, i3) << 16) | a(bArr, i2)) & 4294967295L;
    }
}
