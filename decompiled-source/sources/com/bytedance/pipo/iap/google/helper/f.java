package com.bytedance.pipo.iap.google.helper;

import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import java.util.List;
/* compiled from: SkuHelper.java */
/* loaded from: classes2.dex */
public class f {
    public static String a(Purchase purchase) {
        return (purchase == null || purchase.getProducts() == null || purchase.getProducts().size() == 0) ? "" : purchase.getProducts().get(0);
    }

    public static ProductDetails.SubscriptionOfferDetails a(ProductDetails productDetails) {
        List<ProductDetails.SubscriptionOfferDetails> subscriptionOfferDetails = productDetails.getSubscriptionOfferDetails();
        if (subscriptionOfferDetails == null || subscriptionOfferDetails.isEmpty()) {
            return null;
        }
        return subscriptionOfferDetails.get(0);
    }

    public static ProductDetails.PricingPhase b(ProductDetails productDetails) {
        ProductDetails.SubscriptionOfferDetails a2 = a(productDetails);
        if (a2 != null && !a2.getPricingPhases().getPricingPhaseList().isEmpty()) {
            List<ProductDetails.PricingPhase> pricingPhaseList = a2.getPricingPhases().getPricingPhaseList();
            try {
                return pricingPhaseList.get(pricingPhaseList.size() - 1);
            } catch (Exception unused) {
            }
        }
        return null;
    }
}
