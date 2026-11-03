package com.google.android.gms.internal.play_billing;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zze {
    static final zze zza;
    static final zze zzb;
    final Throwable zzc;

    static {
        if (zzo.zza) {
            zzb = null;
            zza = null;
            return;
        }
        zzb = new zze(false, null);
        zza = new zze(true, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zze(boolean z, Throwable th) {
        this.zzc = th;
    }
}
