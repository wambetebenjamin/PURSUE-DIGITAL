package com.bytedance.ttgame.rocketapi.pay;
/* loaded from: classes7.dex */
public class RocketPurchaseRecord {
    public boolean isOrderStateSuccess = false;
    public String mChannelOrderId;
    public boolean mIsAckEd;
    public boolean mIsSubscription;
    public String mMerchantUserId;
    public String mObAccount;
    public String mObProfile;
    public String mOriginalJson;
    public String mProductId;
    public long mPurchaseTime;
    public String mSelfOrderId;
    public String mSignature;
    public int orderState;
    public Object rawChannelOrderData;

    public String toString() {
        return "RocketPurchaseRecord{isOrderStateSuccess=" + this.isOrderStateSuccess + ", rawChannelOrderData=" + this.rawChannelOrderData + ", mProductId='" + this.mProductId + "', mSelfOrderId='" + this.mSelfOrderId + "', mMerchantUserId='" + this.mMerchantUserId + "', mIsSubscription=" + this.mIsSubscription + ", mChannelOrderId='" + this.mChannelOrderId + "', mIsAckEd=" + this.mIsAckEd + ", mSignature='" + this.mSignature + "', mOriginalJson='" + this.mOriginalJson + "', mObAccount='" + this.mObAccount + "', mObProfile='" + this.mObProfile + "', mPurchaseTime=" + this.mPurchaseTime + ", orderState=" + this.orderState + '}';
    }
}
