package com.bytedance.pipo.iap.model;

import com.bytedance.pipo.iap.enums.a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes2.dex */
public abstract class AbsIapChannelOrderData {
    private a extraScene = a.Unknown;
    protected String mChannelOrderId;
    protected boolean mIsAckEd;
    protected boolean mIsSubscription;
    protected String mMerchantUserId;
    protected String mObAccount;
    protected String mObProfile;
    protected String mOriginalJson;
    protected String mProductId;
    protected long mPurchaseTime;
    protected String mSelfOrderId;
    protected String mSignature;
    protected Object rawChannelOrderData;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface OrderState {
        public static final int PENDING = 2;
        public static final int SUCCESS = 1;
        public static final int UNKNOWN_STATE = 0;
    }

    public abstract String getChannelToken();

    public abstract String getChannelUserId();

    public abstract int getOrderState();

    public abstract String getOriginalJson();

    public abstract IapPaymentMethod getPaymentMethod();

    public abstract String getSignature();

    public abstract boolean isOrderFromOtherSystem();

    public abstract boolean isOrderStateSuccess();

    public boolean isSubscription() {
        return this.mIsSubscription;
    }

    public String getChannelOrderId() {
        return this.mChannelOrderId;
    }

    public String getSelfOrderId() {
        return this.mSelfOrderId;
    }

    public String getProductId() {
        return this.mProductId;
    }

    public boolean isAcknowledged() {
        return this.mIsAckEd;
    }

    public String getMerchantUserId() {
        return this.mMerchantUserId;
    }

    public Object getRawChannelOrderData() {
        return this.rawChannelOrderData;
    }

    public String getObAccount() {
        return this.mObAccount;
    }

    public void setObAccount(String str) {
        this.mObAccount = str;
    }

    public String getObProfile() {
        return this.mObProfile;
    }

    public void setPurchaseTime(long j2) {
        this.mPurchaseTime = j2;
    }

    public long getPurchaseTime() {
        return this.mPurchaseTime;
    }

    public void setObProfile(String str) {
        this.mObProfile = str;
    }

    public a getExtraScene() {
        return this.extraScene;
    }

    public AbsIapChannelOrderData withExtraScene(a aVar) {
        this.extraScene = aVar;
        return this;
    }
}
