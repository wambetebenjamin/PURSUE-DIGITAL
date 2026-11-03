package com.bytedance.pipo.iap.model;

import java.util.List;
/* loaded from: classes2.dex */
public class AbstractPurchaseHistory {
    protected String developerPayload;
    protected String originalString;
    protected List<String> products;
    protected long purchaseTime;
    protected String purchaseToken;
    protected int quantity;

    public List<String> getProducts() {
        return this.products;
    }

    public long getPurchaseTime() {
        return this.purchaseTime;
    }

    public String getPurchaseToken() {
        return this.purchaseToken;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getOriginalString() {
        return this.originalString;
    }

    public String getDeveloperPayload() {
        return this.developerPayload;
    }
}
