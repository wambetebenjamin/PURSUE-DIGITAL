package com.bytedance.ttgame.rocketapi.pay;
/* loaded from: classes7.dex */
public class RocketPayInfo {
    public static final int PAY_MODE_MIXED = 2;
    public static final int PAY_MODE_QMFPAY = 1;
    private String activity;
    private long amount;
    private String appLanguage;
    private String appRegion;
    private String basePlaneId;
    private String currency;
    private int duration;
    private String extraInfo;
    private int goodType;
    private String offerId;
    private String offerIdToken;
    private int payMode;
    private String productId;
    private String purchaseId;
    private String roleId;
    private String roleLevel;
    private String roleName;
    private int roleVipLevel;
    private String sdkOpenId;
    private String serverId;

    public String getSdkOpenId() {
        return this.sdkOpenId;
    }

    public void setSdkOpenId(String str) {
        this.sdkOpenId = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String str) {
        this.roleId = str;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String str) {
        this.roleName = str;
    }

    public String getServerId() {
        return this.serverId;
    }

    public void setServerId(String str) {
        this.serverId = str;
    }

    public int getRoleVipLevel() {
        return this.roleVipLevel;
    }

    public void setRoleVipLevel(int i2) {
        this.roleVipLevel = i2;
    }

    public String getExtraInfo() {
        return this.extraInfo;
    }

    public void setExtraInfo(String str) {
        this.extraInfo = str;
    }

    public String getRoleLevel() {
        return this.roleLevel;
    }

    public void setRoleLevel(String str) {
        this.roleLevel = str;
    }

    public String getAppRegion() {
        return this.appRegion;
    }

    public void setAppRegion(String str) {
        this.appRegion = str;
    }

    public String getAppLanguage() {
        return this.appLanguage;
    }

    public void setAppLanguage(String str) {
        this.appLanguage = str;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setActivity(String str) {
        this.activity = str;
    }

    public long getAmount() {
        return this.amount;
    }

    public void setAmount(long j2) {
        this.amount = j2;
    }

    public int getGoodType() {
        return this.goodType;
    }

    public void setGoodType(int i2) {
        this.goodType = i2;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i2) {
        this.duration = i2;
    }

    public String getPurchaseId() {
        return this.purchaseId;
    }

    public void setPurchaseId(String str) {
        this.purchaseId = str;
    }

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

    public int getPayMode() {
        return this.payMode;
    }

    public void setPayMode(int i2) {
        this.payMode = i2;
    }
}
