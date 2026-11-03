package com.google.android.gms.internal.play_billing;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzga {
    static final Charset zza = Charset.forName("UTF-8");
    public static final byte[] zzb;

    static {
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        zzb = bArr;
        ByteBuffer.wrap(bArr);
        int i2 = zzey.zza;
        try {
            new zzew(bArr, 0, 0, false, null).zza(0);
        } catch (zzgc e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static int zza(boolean z) {
        return z ? 1231 : 1237;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i2, byte[] bArr, int i3, int i4) {
        for (int i5 = i3; i5 < i3 + i4; i5++) {
            i2 = (i2 * 31) + bArr[i5];
        }
        return i2;
    }
}
