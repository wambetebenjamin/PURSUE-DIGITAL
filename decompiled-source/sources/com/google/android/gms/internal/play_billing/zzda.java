package com.google.android.gms.internal.play_billing;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzda implements zzdc {
    private static final zzdb zza = new zzdb(zzda.class);
    private final Object zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzda(Object obj) {
        this.zzb = obj;
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.zzb;
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j2, TimeUnit timeUnit) throws ExecutionException {
        Objects.requireNonNull(timeUnit);
        return this.zzb;
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return true;
    }

    public final String toString() {
        Object obj = this.zzb;
        String obj2 = super.toString();
        String obj3 = obj.toString();
        return obj2 + "[status=SUCCESS, result=[" + obj3 + "]]";
    }

    @Override // com.google.android.gms.internal.play_billing.zzdc
    public final void zzb(Runnable runnable, Executor executor) {
        zzbj.zzc(executor, "Executor was null.");
        try {
            executor.execute(runnable);
        } catch (Exception e2) {
            Logger zza2 = zza.zza();
            Level level = Level.SEVERE;
            String obj = runnable.toString();
            String valueOf = String.valueOf(executor);
            zza2.logp(level, "com.google.common.util.concurrent.ImmediateFuture", "addListener", "RuntimeException while executing runnable " + obj + " with executor " + valueOf, (Throwable) e2);
        }
    }
}
