package com.google.android.gms.internal.play_billing;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
class zzik {
    /* JADX INFO: Access modifiers changed from: protected */
    public static final int zza(String str, byte[] bArr, int i2, int i3) {
        byte[] bytes = str.getBytes(zzga.zza);
        int length = bytes.length;
        if (length - i2 > i3) {
            throw new ArrayIndexOutOfBoundsException("Not enough space in output buffer to encode UTF-8 string");
        }
        System.arraycopy(bytes, 0, bArr, i2, length);
        return i2 + length;
    }
}
