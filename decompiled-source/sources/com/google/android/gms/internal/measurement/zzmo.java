package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
final class zzmo {
    private static boolean zza(byte b2) {
        return b2 > -65;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zza(byte b2, byte b3, byte b4, byte b5, char[] cArr, int i2) {
        if (zza(b3) || (((b2 << Ascii.FS) + (b3 + 112)) >> 30) != 0 || zza(b4) || zza(b5)) {
            throw zzkb.zzd();
        }
        int i3 = ((b2 & 7) << 18) | ((b3 & 63) << 12) | ((b4 & 63) << 6) | (b5 & 63);
        cArr[i2] = (char) ((i3 >>> 10) + 55232);
        cArr[i2 + 1] = (char) ((i3 & 1023) + 56320);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zza(byte b2, char[] cArr, int i2) {
        cArr[i2] = (char) b2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zza(byte b2, byte b3, byte b4, char[] cArr, int i2) {
        if (zza(b3) || ((b2 == -32 && b3 < -96) || ((b2 == -19 && b3 >= -96) || zza(b4)))) {
            throw zzkb.zzd();
        }
        cArr[i2] = (char) (((b2 & Ascii.SI) << 12) | ((b3 & 63) << 6) | (b4 & 63));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zza(byte b2, byte b3, char[] cArr, int i2) {
        if (b2 < -62 || zza(b3)) {
            throw zzkb.zzd();
        }
        cArr[i2] = (char) (((b2 & Ascii.US) << 6) | (b3 & 63));
    }
}
