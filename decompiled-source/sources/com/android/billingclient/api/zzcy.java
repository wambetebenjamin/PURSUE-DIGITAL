package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zziw;
import com.google.android.gms.internal.play_billing.zzja;
import com.google.android.gms.internal.play_billing.zzji;
import com.google.android.gms.internal.play_billing.zzjk;
import com.google.android.gms.internal.play_billing.zzkn;
import com.google.android.gms.internal.play_billing.zzkr;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
public interface zzcy {
    public static final /* synthetic */ int zza = 0;

    static {
        com.google.android.gms.internal.play_billing.zzbz.zzc("com.android.vending.billing.PURCHASES_UPDATED", zzjk.PURCHASES_UPDATED_ACTION, "com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED", zzjk.LOCAL_PURCHASES_UPDATED_ACTION, "com.android.vending.billing.ALTERNATIVE_BILLING", zzjk.ALTERNATIVE_BILLING_ACTION);
    }

    void zza(zziw zziwVar);

    void zzb(zziw zziwVar, int i2);

    void zzc(zziw zziwVar, int i2, long j2);

    void zzd(zziw zziwVar, long j2, boolean z);

    void zze(zziw zziwVar, int i2, long j2, boolean z);

    void zzf(zzja zzjaVar);

    void zzg(zzja zzjaVar, int i2);

    void zzh(zzja zzjaVar, long j2, boolean z);

    void zzi(zzji zzjiVar);

    void zzj(zzkn zzknVar);

    void zzk(zzkr zzkrVar);
}
