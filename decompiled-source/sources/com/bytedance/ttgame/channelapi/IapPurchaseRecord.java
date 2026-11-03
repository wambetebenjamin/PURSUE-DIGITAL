package com.bytedance.ttgame.channelapi;
/* loaded from: classes3.dex */
public class IapPurchaseRecord {
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
        StringBuilder append = new StringBuilder().append("IapPurchaseRecord{isOrderStateSuccess=").append(this.isOrderStateSuccess).append(", rawChannelOrderData=");
        Object obj = this.rawChannelOrderData;
        return append.append(obj != null ? obj.toString() : "null").append(", mProductId='").append(this.mProductId).append('\'').append(", mSelfOrderId='").append(this.mSelfOrderId).append('\'').append(", mMerchantUserId='").append(this.mMerchantUserId).append('\'').append(", mIsSubscription=").append(this.mIsSubscription).append(", mChannelOrderId='").append(this.mChannelOrderId).append('\'').append(", mIsAckEd=").append(this.mIsAckEd).append(", mSignature='").append(this.mSignature).append('\'').append(", mOriginalJson='").append(this.mOriginalJson).append('\'').append(", mObAccount='").append(this.mObAccount).append('\'').append(", mObProfile='").append(this.mObProfile).append('\'').append(", mPurchaseTime=").append(this.mPurchaseTime).append(", orderState=").append(this.orderState).append('}').toString();
    }
}
