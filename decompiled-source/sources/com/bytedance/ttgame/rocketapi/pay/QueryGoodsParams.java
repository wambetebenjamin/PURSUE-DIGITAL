package com.bytedance.ttgame.rocketapi.pay;
/* loaded from: classes7.dex */
public class QueryGoodsParams {
    private int goodsType;
    private String productType;
    private String roleId;
    private String sdkOpenId;
    private String serverId;
    private String channelDetailPath = "";
    private boolean doCurrencyAdapt = true;
    private boolean isDouyinLiveAndCloud = false;
    private boolean isMiDeviceAndUnionPay = false;
    private boolean isChannelDetailPathPay = false;

    public String getSdkOpenId() {
        return this.sdkOpenId;
    }

    public void setSdkOpenId(String str) {
        this.sdkOpenId = str;
    }

    public String getServerId() {
        return this.serverId;
    }

    public void setServerId(String str) {
        this.serverId = str;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String str) {
        this.roleId = str;
    }

    public boolean isDoCurrencyAdapt() {
        return this.doCurrencyAdapt;
    }

    public void setDoCurrencyAdapt(boolean z) {
        this.doCurrencyAdapt = z;
    }

    public int getGoodsType() {
        return this.goodsType;
    }

    public void setGoodsType(int i2) {
        this.goodsType = i2;
    }

    public boolean isDouyinLiveAndCloud() {
        return this.isDouyinLiveAndCloud;
    }

    public void setDouyinLiveAndCloud(boolean z) {
        this.isDouyinLiveAndCloud = z;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String str) {
        this.productType = str;
    }

    public boolean isMiDeviceAndUnionPay() {
        return this.isMiDeviceAndUnionPay;
    }

    public void setMiDeviceAndUnionPay(boolean z) {
        this.isMiDeviceAndUnionPay = z;
    }

    public boolean isChannelDetailPathPay() {
        return this.isChannelDetailPathPay;
    }

    public void setChannelDetailPathPay(boolean z) {
        this.isChannelDetailPathPay = z;
    }

    public String getChannelDetailPath() {
        return this.channelDetailPath;
    }

    public void setChannelDetailPath(String str) {
        this.channelDetailPath = str;
    }
}
