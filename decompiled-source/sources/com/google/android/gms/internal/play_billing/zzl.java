package com.google.android.gms.internal.play_billing;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzl extends zzd {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzd
    public final void zza(zzm zzmVar, zzm zzmVar2) {
        zzmVar.zzc = zzmVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzd
    public final void zzb(zzm zzmVar, Thread thread) {
        zzmVar.zzb = thread;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzd
    public final boolean zzc(zzo zzoVar, zzh zzhVar, zzh zzhVar2) {
        synchronized (zzoVar) {
            if (zzoVar.zzd == zzhVar) {
                zzoVar.zzd = zzhVar2;
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzd
    public final boolean zzd(zzo zzoVar, Object obj, Object obj2) {
        synchronized (zzoVar) {
            if (zzoVar.zzc == obj) {
                zzoVar.zzc = obj2;
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzd
    public final boolean zze(zzo zzoVar, zzm zzmVar, zzm zzmVar2) {
        synchronized (zzoVar) {
            if (zzoVar.zze == zzmVar) {
                zzoVar.zze = zzmVar2;
                return true;
            }
            return false;
        }
    }
}
