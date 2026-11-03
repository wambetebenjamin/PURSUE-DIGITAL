package com.android.billingclient.api;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
public final class QueryPurchasesParams {
    private final String zza;
    private final boolean zzb;

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes.dex */
    public static class Builder {
        private String zza;
        private boolean zzb = false;

        private Builder() {
        }

        /* synthetic */ Builder(zzdz zzdzVar) {
        }

        public QueryPurchasesParams build() {
            String str = this.zza;
            if (str != null) {
                if (!this.zzb || str.equals("subs")) {
                    return new QueryPurchasesParams(this, null);
                }
                throw new IllegalArgumentException("includeSuspendedSubscriptions is only supported for subscription purchases");
            }
            throw new IllegalArgumentException("Product type must be set");
        }

        public Builder includeSuspendedSubscriptions(boolean z) {
            this.zzb = z;
            return this;
        }

        public Builder setProductType(String str) {
            this.zza = str;
            return this;
        }
    }

    /* synthetic */ QueryPurchasesParams(Builder builder, zzdz zzdzVar) {
        this.zza = builder.zza;
        this.zzb = builder.zzb;
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public boolean getIncludeSuspendedSubscriptions() {
        return this.zzb;
    }

    public final String zza() {
        return this.zza;
    }
}
