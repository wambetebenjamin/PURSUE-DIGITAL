package com.google.android.gms.internal.location;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
/* loaded from: classes5.dex */
public final class zzbm {
    public static int zzb(int i2, int i3, @NullableDecl String str) {
        if (i2 < 0 || i2 > i3) {
            throw new IndexOutOfBoundsException(zzd(i2, i3, "index"));
        }
        return i2;
    }

    private static String zzd(int i2, int i3, @NullableDecl String str) {
        if (i2 < 0) {
            return zzbn.zza("%s (%s) must not be negative", str, Integer.valueOf(i2));
        }
        if (i3 >= 0) {
            return zzbn.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        StringBuilder sb = new StringBuilder(26);
        sb.append("negative size: ");
        sb.append(i3);
        throw new IllegalArgumentException(sb.toString());
    }

    public static void zzc(int i2, int i3, int i4) {
        String zzd;
        if (i2 < 0 || i3 < i2 || i3 > i4) {
            if (i2 < 0 || i2 > i4) {
                zzd = zzd(i2, i4, "start index");
            } else {
                zzd = (i3 < 0 || i3 > i4) ? zzd(i3, i4, "end index") : zzbn.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i3), Integer.valueOf(i2));
            }
            throw new IndexOutOfBoundsException(zzd);
        }
    }

    public static int zza(int i2, int i3, @NullableDecl String str) {
        String zza;
        if (i2 < 0 || i2 >= i3) {
            if (i2 < 0) {
                zza = zzbn.zza("%s (%s) must not be negative", "index", Integer.valueOf(i2));
            } else if (i3 < 0) {
                StringBuilder sb = new StringBuilder(26);
                sb.append("negative size: ");
                sb.append(i3);
                throw new IllegalArgumentException(sb.toString());
            } else {
                zza = zzbn.zza("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i2), Integer.valueOf(i3));
            }
            throw new IndexOutOfBoundsException(zza);
        }
        return i2;
    }
}
