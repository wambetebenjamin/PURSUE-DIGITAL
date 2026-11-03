package com.google.android.gms.internal.auth;

import com.google.common.base.Ascii;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes5.dex */
public final class zzhm {
    private static final zzhk zza;

    static {
        if (zzhi.zzu() && zzhi.zzv()) {
            int i2 = zzdr.zza;
        }
        zza = new zzhl();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ int zza(byte[] bArr, int i2, int i3) {
        byte b2 = bArr[i2 - 1];
        int i4 = i3 - i2;
        if (i4 != 0) {
            if (i4 == 1) {
                byte b3 = bArr[i2];
                if (b2 <= -12 && b3 <= -65) {
                    return b2 ^ (b3 << 8);
                }
            } else if (i4 == 2) {
                byte b4 = bArr[i2];
                byte b5 = bArr[i2 + 1];
                if (b2 <= -12 && b4 <= -65 && b5 <= -65) {
                    return ((b4 << 8) ^ b2) ^ (b5 << Ascii.DLE);
                }
            } else {
                throw new AssertionError();
            }
        } else if (b2 <= -12) {
            return b2;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzb(byte[] bArr, int i2, int i3) throws zzfa {
        int length = bArr.length;
        if ((i2 | i3 | ((length - i2) - i3)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        int i4 = i2 + i3;
        char[] cArr = new char[i3];
        int i5 = 0;
        while (i2 < i4) {
            byte b2 = bArr[i2];
            if (!zzhj.zzd(b2)) {
                break;
            }
            i2++;
            cArr[i5] = (char) b2;
            i5++;
        }
        while (i2 < i4) {
            int i6 = i2 + 1;
            byte b3 = bArr[i2];
            if (zzhj.zzd(b3)) {
                int i7 = i5 + 1;
                cArr[i5] = (char) b3;
                i2 = i6;
                while (true) {
                    i5 = i7;
                    if (i2 < i4) {
                        byte b4 = bArr[i2];
                        if (!zzhj.zzd(b4)) {
                            break;
                        }
                        i2++;
                        i7 = i5 + 1;
                        cArr[i5] = (char) b4;
                    }
                }
            } else if (b3 < -32) {
                if (i6 < i4) {
                    zzhj.zzc(b3, bArr[i6], cArr, i5);
                    i2 = i6 + 1;
                    i5++;
                } else {
                    throw zzfa.zzb();
                }
            } else if (b3 < -16) {
                if (i6 < i4 - 1) {
                    int i8 = i6 + 1;
                    zzhj.zzb(b3, bArr[i6], bArr[i8], cArr, i5);
                    i2 = i8 + 1;
                    i5++;
                } else {
                    throw zzfa.zzb();
                }
            } else if (i6 < i4 - 2) {
                int i9 = i6 + 1;
                int i10 = i9 + 1;
                zzhj.zza(b3, bArr[i6], bArr[i9], bArr[i10], cArr, i5);
                i5 += 2;
                i2 = i10 + 1;
            } else {
                throw zzfa.zzb();
            }
        }
        return new String(cArr, 0, i5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzc(byte[] bArr) {
        return zza.zzb(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzd(byte[] bArr, int i2, int i3) {
        return zza.zzb(bArr, i2, i3);
    }
}
