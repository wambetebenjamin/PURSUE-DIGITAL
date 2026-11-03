package com.bytedance.pipo.game.impl.model;

import android.text.TextUtils;
import com.android.billingclient.api.ProductDetails;
import java.util.List;
/* compiled from: PayProductDetails.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final ProductDetails f10339a;

    public void k() {
    }

    public b(ProductDetails productDetails) {
        this.f10339a = productDetails;
    }

    public String a() {
        return this.f10339a.getProductId();
    }

    public String b() {
        return this.f10339a.getProductType();
    }

    public long c() {
        ProductDetails.OneTimePurchaseOfferDetails oneTimePurchaseOfferDetails = this.f10339a.getOneTimePurchaseOfferDetails();
        if (oneTimePurchaseOfferDetails != null) {
            return oneTimePurchaseOfferDetails.getPriceAmountMicros();
        }
        return 0L;
    }

    public String d() {
        ProductDetails.OneTimePurchaseOfferDetails oneTimePurchaseOfferDetails = this.f10339a.getOneTimePurchaseOfferDetails();
        return oneTimePurchaseOfferDetails != null ? oneTimePurchaseOfferDetails.getPriceCurrencyCode() : "";
    }

    public String e() {
        ProductDetails.OneTimePurchaseOfferDetails oneTimePurchaseOfferDetails = this.f10339a.getOneTimePurchaseOfferDetails();
        return oneTimePurchaseOfferDetails != null ? oneTimePurchaseOfferDetails.getFormattedPrice() : "";
    }

    public String f() {
        return this.f10339a.getTitle();
    }

    public String g() {
        return this.f10339a.getDescription();
    }

    public List<ProductDetails.SubscriptionOfferDetails> h() {
        return this.f10339a.getSubscriptionOfferDetails();
    }

    public ProductDetails.OneTimePurchaseOfferDetails i() {
        return this.f10339a.getOneTimePurchaseOfferDetails();
    }

    public long j() {
        List<ProductDetails.SubscriptionOfferDetails> subscriptionOfferDetails;
        if (!"subs".equals(this.f10339a.getProductType()) || (subscriptionOfferDetails = this.f10339a.getSubscriptionOfferDetails()) == null) {
            return 0L;
        }
        for (ProductDetails.SubscriptionOfferDetails subscriptionOfferDetails2 : subscriptionOfferDetails) {
            for (ProductDetails.PricingPhase pricingPhase : subscriptionOfferDetails2.getPricingPhases().getPricingPhaseList()) {
                if (!TextUtils.isEmpty(subscriptionOfferDetails2.getOfferId())) {
                    return pricingPhase.getPriceAmountMicros();
                }
            }
        }
        return 0L;
    }

    public String toString() {
        return "ProductDetails: " + this.f10339a.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f10339a.equals(((b) obj).f10339a);
    }

    public int hashCode() {
        return this.f10339a.hashCode();
    }
}
