package com.google.android.gms.internal.play_billing;

import java.util.concurrent.Executor;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzh {
    static final zzh zza = new zzh(null, null);
    final Runnable zzb;
    final Executor zzc;
    zzh zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzh(Runnable runnable, Executor executor) {
        this.zzb = runnable;
        this.zzc = executor;
    }
}
