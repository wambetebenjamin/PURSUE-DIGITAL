package com.google.android.gms.internal.play_billing;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzbj {
    public static int zza(int i2, int i3, String str) {
        String zzb;
        if (i2 < 0 || i2 >= i3) {
            if (i2 < 0) {
                zzb = zzbm.zzb("%s (%s) must not be negative", "index", Integer.valueOf(i2));
            } else if (i3 < 0) {
                throw new IllegalArgumentException("negative size: " + i3);
            } else {
                zzb = zzbm.zzb("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i2), Integer.valueOf(i3));
            }
            throw new IndexOutOfBoundsException(zzb);
        }
        return i2;
    }

    public static int zzb(int i2, int i3, String str) {
        if (i2 < 0 || i2 > i3) {
            throw new IndexOutOfBoundsException(zzf(i2, i3, "index"));
        }
        return i2;
    }

    public static Object zzc(Object obj, Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException((String) obj2);
    }

    public static void zzd(int i2, int i3, int i4) {
        String zzf;
        if (i2 < 0 || i3 < i2 || i3 > i4) {
            if (i2 < 0 || i2 > i4) {
                zzf = zzf(i2, i4, "start index");
            } else {
                zzf = (i3 < 0 || i3 > i4) ? zzf(i3, i4, "end index") : zzbm.zzb("end index (%s) must not be less than start index (%s)", Integer.valueOf(i3), Integer.valueOf(i2));
            }
            throw new IndexOutOfBoundsException(zzf);
        }
    }

    public static void zze(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException((String) obj);
        }
    }

    private static String zzf(int i2, int i3, String str) {
        if (i2 < 0) {
            return zzbm.zzb("%s (%s) must not be negative", str, Integer.valueOf(i2));
        }
        if (i3 >= 0) {
            return zzbm.zzb("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IllegalArgumentException("negative size: " + i3);
    }
}
