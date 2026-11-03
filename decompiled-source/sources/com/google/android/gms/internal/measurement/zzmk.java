package com.google.android.gms.internal.measurement;

import java.io.IOException;
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
abstract class zzmk<T, B> {
    private static volatile int zza = 100;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zza(T t);

    abstract B zza();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract T zza(T t, T t2);

    abstract void zza(B b2, int i2, int i3);

    abstract void zza(B b2, int i2, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(B b2, int i2, zzik zzikVar);

    abstract void zza(B b2, int i2, T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(T t, zznb zznbVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zza(zzlr zzlrVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zzb(T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(B b2, int i2, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(T t, zznb zznbVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(Object obj, B b2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract B zzc(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzc(Object obj, T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract T zzd(Object obj);

    abstract T zze(B b2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzf(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(B b2, zzlr zzlrVar, int i2) throws IOException {
        int zzd = zzlrVar.zzd();
        int i3 = zzd >>> 3;
        int i4 = zzd & 7;
        if (i4 == 0) {
            zzb(b2, i3, zzlrVar.zzl());
            return true;
        } else if (i4 == 1) {
            zza((zzmk<T, B>) b2, i3, zzlrVar.zzk());
            return true;
        } else if (i4 == 2) {
            zza((zzmk<T, B>) b2, i3, zzlrVar.zzp());
            return true;
        } else if (i4 != 3) {
            if (i4 != 4) {
                if (i4 == 5) {
                    zza((zzmk<T, B>) b2, i3, zzlrVar.zzf());
                    return true;
                }
                throw zzkb.zza();
            }
            return false;
        } else {
            B zza2 = zza();
            int i5 = 4 | (i3 << 3);
            int i6 = i2 + 1;
            if (i6 >= zza) {
                throw zzkb.zzh();
            }
            while (zzlrVar.zzc() != Integer.MAX_VALUE && zza((zzmk<T, B>) zza2, zzlrVar, i6)) {
            }
            if (i5 != zzlrVar.zzd()) {
                throw zzkb.zzb();
            }
            zza((zzmk<T, B>) b2, i3, (int) zze(zza2));
            return true;
        }
    }
}
