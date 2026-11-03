package com.google.android.gms.internal.fido;

import java.io.IOException;
/* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
/* loaded from: classes5.dex */
public abstract class zzbf {
    private static final zzbf zza = new zzbd("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    private static final zzbf zzb = new zzbd("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');
    private static final zzbf zzc = new zzbe("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
    private static final zzbf zzd = new zzbe("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
    private static final zzbf zze = new zzbc("base16()", "0123456789ABCDEF");

    public static zzbf zzd() {
        return zze;
    }

    abstract void zza(Appendable appendable, byte[] bArr, int i2, int i3) throws IOException;

    abstract int zzb(int i2);

    public final String zze(byte[] bArr, int i2, int i3) {
        zzam.zze(0, i3, bArr.length);
        StringBuilder sb = new StringBuilder(zzb(i3));
        try {
            zza(sb, bArr, 0, i3);
            return sb.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
