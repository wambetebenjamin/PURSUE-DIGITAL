package com.google.firebase.analytics;

import com.google.android.gms.internal.measurement.zzdg;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* compiled from: com.google.android.gms:play-services-measurement-api@@22.1.2 */
/* loaded from: classes9.dex */
final class zza extends ThreadPoolExecutor {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(FirebaseAnalytics firebaseAnalytics, int i2, int i3, long j2, TimeUnit timeUnit, BlockingQueue blockingQueue) {
        super(0, 1, 30L, timeUnit, blockingQueue);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        super.execute(zzdg.zza().zza(runnable));
    }
}
