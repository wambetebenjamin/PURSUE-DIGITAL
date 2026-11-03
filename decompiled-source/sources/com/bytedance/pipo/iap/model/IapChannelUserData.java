package com.bytedance.pipo.iap.model;
/* loaded from: classes2.dex */
public class IapChannelUserData {
    private IapPaymentMethod mIapPaymentMethod;
    private String mUserId;
    private String mUserMarkPlace;

    public IapChannelUserData(IapPaymentMethod iapPaymentMethod, String str, String str2) {
        this.mIapPaymentMethod = iapPaymentMethod;
        this.mUserId = str;
        this.mUserMarkPlace = str2;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public IapChannelUserData setUserId(String str) {
        this.mUserId = str;
        return this;
    }

    public String getUserMarkPlace() {
        return this.mUserMarkPlace;
    }

    public IapChannelUserData setUserMarkPlace(String str) {
        this.mUserMarkPlace = str;
        return this;
    }

    public IapPaymentMethod getIapPaymentMethod() {
        return this.mIapPaymentMethod;
    }

    public IapChannelUserData setIapPaymentMethod(IapPaymentMethod iapPaymentMethod) {
        this.mIapPaymentMethod = iapPaymentMethod;
        return this;
    }
}
