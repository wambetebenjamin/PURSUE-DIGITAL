package com.android.billingclient.api;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
public final class ConsumeParams {
    private String zza;

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes.dex */
    public static final class Builder {
        private String zza;

        private Builder() {
            throw null;
        }

        /* synthetic */ Builder(zzdc zzdcVar) {
        }

        public ConsumeParams build() {
            String str = this.zza;
            if (str == null) {
                throw new IllegalArgumentException("Purchase token must be set");
            }
            ConsumeParams consumeParams = new ConsumeParams(null);
            consumeParams.zza = str;
            return consumeParams;
        }

        public Builder setPurchaseToken(String str) {
            this.zza = str;
            return this;
        }
    }

    private ConsumeParams() {
        throw null;
    }

    /* synthetic */ ConsumeParams(zzdc zzdcVar) {
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public String getPurchaseToken() {
        return this.zza;
    }
}
