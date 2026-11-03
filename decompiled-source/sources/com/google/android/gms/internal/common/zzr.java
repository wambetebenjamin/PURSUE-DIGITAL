package com.google.android.gms.internal.common;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes5.dex */
public final class zzr {
    public static void zza(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static int zzb(int i2, int i3, String str) {
        String zza;
        if (i2 < 0 || i2 >= i3) {
            if (i2 < 0) {
                zza = zzx.zza("%s (%s) must not be negative", "index", Integer.valueOf(i2));
            } else if (i3 < 0) {
                StringBuilder sb = new StringBuilder(String.valueOf(i3).length() + 15);
                sb.append("negative size: ");
                sb.append(i3);
                throw new IllegalArgumentException(sb.toString());
            } else {
                zza = zzx.zza("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i2), Integer.valueOf(i3));
            }
            throw new IndexOutOfBoundsException(zza);
        }
        return i2;
    }

    public static int zzc(int i2, int i3, String str) {
        if (i2 < 0 || i2 > i3) {
            throw new IndexOutOfBoundsException(zze(i2, i3, "index"));
        }
        return i2;
    }

    public static void zzd(int i2, int i3, int i4) {
        String zze;
        if (i2 < 0 || i3 < i2 || i3 > i4) {
            if (i2 < 0 || i2 > i4) {
                zze = zze(i2, i4, "start index");
            } else {
                zze = (i3 < 0 || i3 > i4) ? zze(i3, i4, "end index") : zzx.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i3), Integer.valueOf(i2));
            }
            throw new IndexOutOfBoundsException(zze);
        }
    }

    private static String zze(int i2, int i3, String str) {
        if (i2 < 0) {
            return zzx.zza("%s (%s) must not be negative", str, Integer.valueOf(i2));
        }
        if (i3 >= 0) {
            return zzx.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        StringBuilder sb = new StringBuilder(String.valueOf(i3).length() + 15);
        sb.append("negative size: ");
        sb.append(i3);
        throw new IllegalArgumentException(sb.toString());
    }
}
