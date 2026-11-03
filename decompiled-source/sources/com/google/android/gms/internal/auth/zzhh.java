package com.google.android.gms.internal.auth;

import java.lang.reflect.Field;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes5.dex */
public abstract class zzhh {
    final Unsafe zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhh(Unsafe unsafe) {
        this.zza = unsafe;
    }

    public abstract double zza(Object obj, long j2);

    public abstract float zzb(Object obj, long j2);

    public abstract void zzc(Object obj, long j2, boolean z);

    public abstract void zzd(Object obj, long j2, double d2);

    public abstract void zze(Object obj, long j2, float f2);

    public abstract boolean zzf(Object obj, long j2);

    public final int zzg(Class cls) {
        return this.zza.arrayBaseOffset(cls);
    }

    public final int zzh(Class cls) {
        return this.zza.arrayIndexScale(cls);
    }

    public final int zzi(Object obj, long j2) {
        return this.zza.getInt(obj, j2);
    }

    public final long zzj(Object obj, long j2) {
        return this.zza.getLong(obj, j2);
    }

    public final long zzk(Field field) {
        return this.zza.objectFieldOffset(field);
    }

    public final Object zzl(Object obj, long j2) {
        return this.zza.getObject(obj, j2);
    }

    public final void zzm(Object obj, long j2, int i2) {
        this.zza.putInt(obj, j2, i2);
    }

    public final void zzn(Object obj, long j2, long j3) {
        this.zza.putLong(obj, j2, j3);
    }

    public final void zzo(Object obj, long j2, Object obj2) {
        this.zza.putObject(obj, j2, obj2);
    }
}
