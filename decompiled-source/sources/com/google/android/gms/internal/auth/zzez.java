package com.google.android.gms.internal.auth;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes5.dex */
public final class zzez {
    static final Charset zza = Charset.forName("US-ASCII");
    static final Charset zzb = Charset.forName("UTF-8");
    static final Charset zzc = Charset.forName("ISO-8859-1");
    public static final byte[] zzd;
    public static final ByteBuffer zze;
    public static final zzei zzf;

    static {
        byte[] bArr = new byte[0];
        zzd = bArr;
        zze = ByteBuffer.wrap(bArr);
        int i2 = zzei.zza;
        zzeg zzegVar = new zzeg(bArr, 0, 0, false, null);
        try {
            zzegVar.zza(0);
            zzf = zzegVar;
        } catch (zzfa e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static int zza(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int zzb(byte[] bArr) {
        int length = bArr.length;
        int zzd2 = zzd(length, bArr, 0, length);
        if (zzd2 == 0) {
            return 1;
        }
        return zzd2;
    }

    public static int zzc(long j2) {
        return (int) (j2 ^ (j2 >>> 32));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int i2, byte[] bArr, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            i2 = (i2 * 31) + bArr[i5];
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zze(Object obj) {
        Objects.requireNonNull(obj);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzf(Object obj, String str) {
        Objects.requireNonNull(obj, str);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzg(Object obj, Object obj2) {
        return ((zzfw) obj).zzd().zzc((zzfw) obj2).zzg();
    }

    public static String zzh(byte[] bArr) {
        return new String(bArr, zzb);
    }

    public static boolean zzi(byte[] bArr) {
        return zzhm.zzc(bArr);
    }
}
