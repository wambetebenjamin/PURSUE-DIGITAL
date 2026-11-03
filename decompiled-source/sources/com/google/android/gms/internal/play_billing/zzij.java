package com.google.android.gms.internal.play_billing;

import com.google.common.base.Ascii;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzij {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zza(byte b2, byte b3, byte b4, byte b5, char[] cArr, int i2) {
        if (zze(b3) || (((b2 << Ascii.FS) + (b3 + 112)) >> 30) != 0 || zze(b4) || zze(b5)) {
            throw new zzgc("Protocol message had invalid UTF-8.");
        }
        int i3 = ((b2 & 7) << 18) | ((b3 & 63) << 12) | ((b4 & 63) << 6) | (b5 & 63);
        cArr[i2] = (char) ((i3 >>> 10) + 55232);
        cArr[i2 + 1] = (char) ((i3 & 1023) + 56320);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzc(byte b2, byte b3, char[] cArr, int i2) {
        if (b2 < -62 || zze(b3)) {
            throw new zzgc("Protocol message had invalid UTF-8.");
        }
        cArr[i2] = (char) (((b2 & Ascii.US) << 6) | (b3 & 63));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean zzd(byte b2) {
        return b2 >= 0;
    }

    private static boolean zze(byte b2) {
        return b2 > -65;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzb(byte b2, byte b3, byte b4, char[] cArr, int i2) {
        if (!zze(b3)) {
            if (b2 == -32) {
                if (b3 >= -96) {
                    b2 = -32;
                }
            }
            if (b2 == -19) {
                if (b3 < -96) {
                    b2 = -19;
                }
            }
            if (!zze(b4)) {
                cArr[i2] = (char) (((b2 & Ascii.SI) << 12) | ((b3 & 63) << 6) | (b4 & 63));
                return;
            }
        }
        throw new zzgc("Protocol message had invalid UTF-8.");
    }
}
