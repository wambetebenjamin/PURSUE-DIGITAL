package com.android.billingclient.api;

import android.content.Context;
import android.content.IntentFilter;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
public final class zzaa {
    private final Context zza;
    private final PurchasesUpdatedListener zzb;
    private final zzb zzc;
    private final UserChoiceBillingListener zzd;
    private final zzcy zze;
    private final zzz zzf = new zzz(this, true);
    private final zzz zzg = new zzz(this, false);
    private boolean zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(Context context, PurchasesUpdatedListener purchasesUpdatedListener, zzdk zzdkVar, zzb zzbVar, UserChoiceBillingListener userChoiceBillingListener, zzcy zzcyVar) {
        this.zza = context;
        this.zzb = purchasesUpdatedListener;
        this.zzc = zzbVar;
        this.zzd = userChoiceBillingListener;
        this.zze = zzcyVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final PurchasesUpdatedListener zzd() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzf() {
        zzz zzzVar = this.zzf;
        Context context = this.zza;
        zzzVar.zzc(context);
        this.zzg.zzc(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzg(boolean z) {
        IntentFilter intentFilter = new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED");
        IntentFilter intentFilter2 = new IntentFilter("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED");
        intentFilter2.addAction("com.android.vending.billing.ALTERNATIVE_BILLING");
        this.zzh = z;
        zzz zzzVar = this.zzg;
        Context context = this.zza;
        zzzVar.zza(context, intentFilter2);
        if (this.zzh) {
            this.zzf.zzb(context, intentFilter, "com.google.android.finsky.permission.PLAY_BILLING_LIBRARY_BROADCAST");
        } else {
            this.zzf.zza(context, intentFilter);
        }
    }
}
