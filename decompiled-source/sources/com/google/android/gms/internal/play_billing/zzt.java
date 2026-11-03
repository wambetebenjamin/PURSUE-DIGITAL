package com.google.android.gms.internal.play_billing;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzt implements zzdc {
    final WeakReference zza;
    private final zzo zzb = new zzs(this);

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzt(zzp zzpVar) {
        this.zza = new WeakReference(zzpVar);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        zzp zzpVar = (zzp) this.zza.get();
        boolean cancel = this.zzb.cancel(z);
        if (!cancel || zzpVar == null) {
            return cancel;
        }
        zzpVar.zza();
        return true;
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws InterruptedException, ExecutionException {
        return this.zzb.get();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.zzb.zzc instanceof zze;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.zzb.isDone();
    }

    public final String toString() {
        return this.zzb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(Object obj) {
        return this.zzb.zzd(obj);
    }

    @Override // com.google.android.gms.internal.play_billing.zzdc
    public final void zzb(Runnable runnable, Executor executor) {
        this.zzb.zzb(runnable, executor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzc(Throwable th) {
        zzg zzgVar = new zzg(th);
        zzd zzdVar = zzo.zzb;
        zzo zzoVar = this.zzb;
        if (zzdVar.zzd(zzoVar, null, zzgVar)) {
            zzo.zzc(zzoVar);
            return true;
        }
        return false;
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j2, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.zzb.get(j2, timeUnit);
    }
}
