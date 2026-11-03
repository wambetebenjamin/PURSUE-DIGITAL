package com.android.billingclient.api;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
public final class PendingPurchasesParams {
    private final boolean enableOneTimeProducts;
    private final boolean enablePrepaidPlans;

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes.dex */
    public static final class Builder {
        private boolean enableOneTimeProducts;
        private boolean enablePrepaidPlans;

        private Builder() {
        }

        public PendingPurchasesParams build() {
            if (this.enableOneTimeProducts) {
                return new PendingPurchasesParams(true, this.enablePrepaidPlans);
            }
            throw new IllegalArgumentException("Pending purchases for one-time products must be supported.");
        }

        public Builder enableOneTimeProducts() {
            this.enableOneTimeProducts = true;
            return this;
        }

        public Builder enablePrepaidPlans() {
            this.enablePrepaidPlans = true;
            return this;
        }
    }

    private PendingPurchasesParams(boolean z, boolean z2) {
        this.enableOneTimeProducts = z;
        this.enablePrepaidPlans = z2;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isEnabledForOneTimeProducts() {
        return this.enableOneTimeProducts;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isEnabledForPrepaidPlans() {
        return this.enablePrepaidPlans;
    }
}
