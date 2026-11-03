package com.google.android.gms.internal.play_billing;

import android.os.SystemClock;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzbc extends zzbo {
    @Override // com.google.android.gms.internal.play_billing.zzbo
    public final long zza() {
        return SystemClock.elapsedRealtime() * 1000000;
    }
}
