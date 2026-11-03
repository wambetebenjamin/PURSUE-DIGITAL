package com.google.android.gms.internal.fido;
/* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
/* loaded from: classes5.dex */
public final class zzam {
    public static int zza(int i2, int i3, String str) {
        String zza;
        if (i2 < 0 || i2 >= i3) {
            if (i2 < 0) {
                zza = zzan.zza("%s (%s) must not be negative", "index", Integer.valueOf(i2));
            } else if (i3 < 0) {
                throw new IllegalArgumentException("negative size: " + i3);
            } else {
                zza = zzan.zza("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i2), Integer.valueOf(i3));
            }
            throw new IndexOutOfBoundsException(zza);
        }
        return i2;
    }

    public static int zzb(int i2, int i3, String str) {
        if (i2 < 0 || i2 > i3) {
            throw new IndexOutOfBoundsException(zzf(i2, i3, "index"));
        }
        return i2;
    }

    public static void zzc(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void zzd(boolean z, String str, char c2) {
        if (!z) {
            throw new IllegalArgumentException(zzan.zza(str, Character.valueOf(c2)));
        }
    }

    public static void zze(int i2, int i3, int i4) {
        String zzf;
        if (i2 < 0 || i3 < i2 || i3 > i4) {
            if (i2 < 0 || i2 > i4) {
                zzf = zzf(i2, i4, "start index");
            } else {
                zzf = (i3 < 0 || i3 > i4) ? zzf(i3, i4, "end index") : zzan.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i3), Integer.valueOf(i2));
            }
            throw new IndexOutOfBoundsException(zzf);
        }
    }

    private static String zzf(int i2, int i3, String str) {
        if (i2 < 0) {
            return zzan.zza("%s (%s) must not be negative", str, Integer.valueOf(i2));
        }
        if (i3 >= 0) {
            return zzan.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IllegalArgumentException("negative size: " + i3);
    }
}
