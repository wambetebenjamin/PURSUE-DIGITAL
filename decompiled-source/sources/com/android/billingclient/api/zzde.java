package com.android.billingclient.api;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
enum zzde {
    GET_BILLING_CONFIG("getBillingConfig", 29),
    IS_BILLING_PROGRAM_AVAILABLE_ASYNC("isIndirectBillingProgramAvailable", 33),
    CREATE_BILLING_PROGRAM_REPORTING_DETAILS_ASYNC("createIndirectBillingReportingDetails", 35);
    
    private final String zze;
    private final int zzf;

    zzde(String str, int i2) {
        this.zze = str;
        this.zzf = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zza() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzb() {
        return this.zzf;
    }
}
