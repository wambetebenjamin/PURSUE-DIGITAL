package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzjv {
    static final Charset zza;
    public static final byte[] zzb;

    public static int zza(long j2) {
        return (int) (j2 ^ (j2 >>> 32));
    }

    public static int zza(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int zza(byte[] bArr) {
        int length = bArr.length;
        int zza2 = zza(length, bArr, 0, length);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i2, byte[] bArr, int i3, int i4) {
        for (int i5 = i3; i5 < i3 + i4; i5++) {
            i2 = (i2 * 31) + bArr[i5];
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T zza(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T zza(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }

    static {
        Charset.forName("US-ASCII");
        zza = Charset.forName("UTF-8");
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        zzb = bArr;
        ByteBuffer.wrap(bArr);
        zziw.zza(bArr, 0, bArr.length, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zza(zzlc zzlcVar) {
        if (zzlcVar instanceof zzic) {
            zzic zzicVar = (zzic) zzlcVar;
            return false;
        }
        return false;
    }
}
