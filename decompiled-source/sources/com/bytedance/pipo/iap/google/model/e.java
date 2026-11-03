package com.bytedance.pipo.iap.google.model;

import com.android.billingclient.api.PurchaseHistoryRecord;
import com.bytedance.pipo.iap.model.AbstractPurchaseHistory;
/* compiled from: GooglePurchaseHistory.java */
/* loaded from: classes2.dex */
public class e extends AbstractPurchaseHistory {

    /* renamed from: a  reason: collision with root package name */
    private PurchaseHistoryRecord f10784a;

    public e(PurchaseHistoryRecord purchaseHistoryRecord) {
        this.f10784a = purchaseHistoryRecord;
        this.products = purchaseHistoryRecord.getProducts();
        this.purchaseTime = purchaseHistoryRecord.getPurchaseTime();
        this.quantity = purchaseHistoryRecord.getQuantity();
        this.originalString = purchaseHistoryRecord.getOriginalJson();
        this.developerPayload = purchaseHistoryRecord.getDeveloperPayload();
        this.purchaseToken = purchaseHistoryRecord.getPurchaseToken();
    }
}
