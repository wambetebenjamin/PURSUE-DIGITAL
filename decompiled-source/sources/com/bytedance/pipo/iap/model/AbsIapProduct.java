package com.bytedance.pipo.iap.model;
/* loaded from: classes2.dex */
public abstract class AbsIapProduct {
    protected IapChannelUserData mChannelUserData;
    protected String mDescription;
    protected boolean mIsSubscription;
    protected String mOriginalJson;
    protected String mPrice = "";
    protected long mPriceAmountMicros;
    protected String mPriceCurrencyCode;
    protected String mProductId;
    protected String mProductType;
    protected String mSubscriptionPeriod;
    protected String mTitle;

    public String getGpOfferIdToken() {
        return "";
    }

    public String getGpSkuDetailsToken() {
        return "";
    }

    public String getProductId() {
        return this.mProductId;
    }

    public String getProductType() {
        return this.mProductType;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public long getPriceAmountMicros() {
        return this.mPriceAmountMicros;
    }

    public String getPriceCurrencyCode() {
        return this.mPriceCurrencyCode;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean getIsSubscription() {
        return this.mIsSubscription;
    }

    public String getSubscriptionPeriod() {
        return this.mSubscriptionPeriod;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getOriginalJson() {
        return this.mOriginalJson;
    }

    public IapChannelUserData getChannelUserData() {
        return this.mChannelUserData;
    }

    public AbsIapProduct setChannelUserData(IapChannelUserData iapChannelUserData) {
        this.mChannelUserData = iapChannelUserData;
        return this;
    }
}
