package com.google.android.gms.internal.play_billing;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzde implements Runnable {
    zzdh zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzde(zzdh zzdhVar) {
        this.zza = zzdhVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0006, code lost:
        r1 = r0.zzd;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r10 = this;
            com.google.android.gms.internal.play_billing.zzdh r0 = r10.zza
            if (r0 != 0) goto L6
            goto L84
        L6:
            com.google.android.gms.internal.play_billing.zzdc r1 = com.google.android.gms.internal.play_billing.zzdh.zzr(r0)
            if (r1 == 0) goto L84
            r2 = 0
            r10.zza = r2
            boolean r3 = r1.isDone()
            if (r3 == 0) goto L19
            r0.zzj(r1)
            return
        L19:
            r3 = 1
            java.util.concurrent.ScheduledFuture r4 = com.google.android.gms.internal.play_billing.zzdh.zzt(r0)     // Catch: java.lang.Throwable -> L7f
            com.google.android.gms.internal.play_billing.zzdh.zzu(r0, r2)     // Catch: java.lang.Throwable -> L7f
            java.lang.String r5 = "Timed out"
            if (r4 == 0) goto L52
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L50
            long r6 = r4.getDelay(r6)     // Catch: java.lang.Throwable -> L50
            long r6 = java.lang.Math.abs(r6)     // Catch: java.lang.Throwable -> L50
            r8 = 10
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 <= 0) goto L52
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L50
            r4.<init>()     // Catch: java.lang.Throwable -> L50
            r4.append(r5)     // Catch: java.lang.Throwable -> L50
            java.lang.String r8 = " (timeout delayed by "
            r4.append(r8)     // Catch: java.lang.Throwable -> L50
            r4.append(r6)     // Catch: java.lang.Throwable -> L50
            java.lang.String r6 = " ms after scheduled time)"
            r4.append(r6)     // Catch: java.lang.Throwable -> L50
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L50
            r5 = r4
            goto L52
        L50:
            r4 = move-exception
            goto L76
        L52:
            java.lang.String r4 = r1.toString()     // Catch: java.lang.Throwable -> L50
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L50
            r6.<init>()     // Catch: java.lang.Throwable -> L50
            r6.append(r5)     // Catch: java.lang.Throwable -> L50
            java.lang.String r7 = ": "
            r6.append(r7)     // Catch: java.lang.Throwable -> L50
            r6.append(r4)     // Catch: java.lang.Throwable -> L50
            java.lang.String r4 = r6.toString()     // Catch: java.lang.Throwable -> L50
            com.google.android.gms.internal.play_billing.zzdf r5 = new com.google.android.gms.internal.play_billing.zzdf     // Catch: java.lang.Throwable -> L7f
            r5.<init>(r4, r2)     // Catch: java.lang.Throwable -> L7f
            r0.zzi(r5)     // Catch: java.lang.Throwable -> L7f
            r1.cancel(r3)
            return
        L76:
            com.google.android.gms.internal.play_billing.zzdf r6 = new com.google.android.gms.internal.play_billing.zzdf     // Catch: java.lang.Throwable -> L7f
            r6.<init>(r5, r2)     // Catch: java.lang.Throwable -> L7f
            r0.zzi(r6)     // Catch: java.lang.Throwable -> L7f
            throw r4     // Catch: java.lang.Throwable -> L7f
        L7f:
            r0 = move-exception
            r1.cancel(r3)
            throw r0
        L84:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzde.run():void");
    }
}
