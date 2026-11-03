package com.bytedance.ttgame.module.pay.api;

import java.util.List;
/* loaded from: classes6.dex */
public class SubscriptionInfo {
    private String description;
    private String[] localizedIn;
    private String name;
    private String productId;
    private String skuDetailsToken;
    private List<SubscriptionOfferDetail> subscriptionOfferDetails;
    private String title;
    private String type;

    /* loaded from: classes6.dex */
    public static class PricingPhase {
        private int billingCycleCount;
        private String billingPeriod;
        private String formattedPrice;
        private long priceAmountMicros;
        private String priceCurrencyCode;
        private int recurrenceMode;

        public long getPriceAmountMicros() {
            return this.priceAmountMicros;
        }

        public void setPriceAmountMicros(long j2) {
            this.priceAmountMicros = j2;
        }

        public String getPriceCurrencyCode() {
            return this.priceCurrencyCode;
        }

        public void setPriceCurrencyCode(String str) {
            this.priceCurrencyCode = str;
        }

        public String getFormattedPrice() {
            return this.formattedPrice;
        }

        public void setFormattedPrice(String str) {
            this.formattedPrice = str;
        }

        public String getBillingPeriod() {
            return this.billingPeriod;
        }

        public void setBillingPeriod(String str) {
            this.billingPeriod = str;
        }

        public int getRecurrenceMode() {
            return this.recurrenceMode;
        }

        public void setRecurrenceMode(int i2) {
            this.recurrenceMode = i2;
        }

        public int getBillingCycleCount() {
            return this.billingCycleCount;
        }

        public void setBillingCycleCount(int i2) {
            this.billingCycleCount = i2;
        }
    }

    /* loaded from: classes6.dex */
    public static class SubscriptionOfferDetail {
        private String basePlaneId;
        private String offerId;
        private String offerIdToken;
        private List<String> offerTags;
        private List<PricingPhase> pricingPhases;

        public String getOfferIdToken() {
            return this.offerIdToken;
        }

        public void setOfferIdToken(String str) {
            this.offerIdToken = str;
        }

        public String getBasePlaneId() {
            return this.basePlaneId;
        }

        public void setBasePlaneId(String str) {
            this.basePlaneId = str;
        }

        public String getOfferId() {
            return this.offerId;
        }

        public void setOfferId(String str) {
            this.offerId = str;
        }

        public List<String> getOfferTags() {
            return this.offerTags;
        }

        public void setOfferTags(List<String> list) {
            this.offerTags = list;
        }

        public List<PricingPhase> getPricingPhases() {
            return this.pricingPhases;
        }

        public void setPricingPhases(List<PricingPhase> list) {
            this.pricingPhases = list;
        }
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String[] getLocalizedIn() {
        return this.localizedIn;
    }

    public void setLocalizedIn(String[] strArr) {
        this.localizedIn = strArr;
    }

    public String getSkuDetailsToken() {
        return this.skuDetailsToken;
    }

    public void setSkuDetailsToken(String str) {
        this.skuDetailsToken = str;
    }

    public List<SubscriptionOfferDetail> getSubscriptionOfferDetails() {
        return this.subscriptionOfferDetails;
    }

    public void setSubscriptionOfferDetails(List<SubscriptionOfferDetail> list) {
        this.subscriptionOfferDetails = list;
    }
}
