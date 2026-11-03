package com.android.billingclient.api;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
public final class BillingProgramReportingDetails {
    private final int billingProgram;
    private final String externalTransactionToken;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BillingProgramReportingDetails(String str, int i2) {
        this.externalTransactionToken = str;
        this.billingProgram = i2;
    }

    public int getBillingProgram() {
        return this.billingProgram;
    }

    public String getExternalTransactionToken() {
        return this.externalTransactionToken;
    }
}
