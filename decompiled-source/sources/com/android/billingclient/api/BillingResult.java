package com.android.billingclient.api;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
public final class BillingResult {
    private int zza;
    private int zzb;
    private String zzc;

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes.dex */
    public static class Builder {
        private int zza;
        private int zzb = 0;
        private String zzc = "";

        private Builder() {
        }

        /* synthetic */ Builder(zzda zzdaVar) {
        }

        public BillingResult build() {
            BillingResult billingResult = new BillingResult();
            billingResult.zza = this.zza;
            billingResult.zzb = this.zzb;
            billingResult.zzc = this.zzc;
            return billingResult;
        }

        public Builder setDebugMessage(String str) {
            this.zzc = str;
            return this;
        }

        public Builder setOnPurchasesUpdatedSubResponseCode(int i2) {
            this.zzb = i2;
            return this;
        }

        public Builder setResponseCode(int i2) {
            this.zza = i2;
            return this;
        }
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public String getDebugMessage() {
        return this.zzc;
    }

    public int getOnPurchasesUpdatedSubResponseCode() {
        return this.zzb;
    }

    public int getResponseCode() {
        return this.zza;
    }

    public String toString() {
        String zzl = com.google.android.gms.internal.play_billing.zzc.zzl(this.zza);
        String str = this.zzc;
        return "Response Code: " + zzl + ", Debug Message: " + str;
    }
}
