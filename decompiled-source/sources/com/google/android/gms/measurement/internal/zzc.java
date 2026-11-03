package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzc implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzb zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(zzb zzbVar, long j2) {
        this.zza = j2;
        this.zzb = zzbVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzb(this.zza);
    }
}
