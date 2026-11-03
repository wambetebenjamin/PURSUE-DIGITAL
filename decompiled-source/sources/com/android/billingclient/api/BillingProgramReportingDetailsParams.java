package com.android.billingclient.api;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
public final class BillingProgramReportingDetailsParams {
    private final int billingProgram;

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes.dex */
    public static final class Builder {
        private int billingProgram;

        private Builder() {
            this.billingProgram = 0;
        }

        public BillingProgramReportingDetailsParams build() {
            if (this.billingProgram == 0) {
                throw new IllegalArgumentException("Billing program is not specified.");
            }
            return new BillingProgramReportingDetailsParams(this);
        }

        public Builder setBillingProgram(int i2) {
            this.billingProgram = i2;
            return this;
        }
    }

    private BillingProgramReportingDetailsParams(Builder builder) {
        this.billingProgram = builder.billingProgram;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getBillingProgram() {
        return this.billingProgram;
    }
}
