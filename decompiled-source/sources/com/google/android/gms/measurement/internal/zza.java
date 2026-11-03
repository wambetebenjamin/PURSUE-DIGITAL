package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
public final class zza implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ zzb zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(zzb zzbVar, String str, long j2) {
        this.zza = str;
        this.zzb = j2;
        this.zzc = zzbVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzb.zza(this.zzc, this.zza, this.zzb);
    }
}
