package com.bytedance.ttgame.module.account.api;

import com.bytedance.ttgame.rocketapi.pay.RocketPayInfo;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class OrderParams {
    private String activity;
    private String actuallyCurrency;
    private long actuallyPrice;
    private String appId;
    private String appLanguage;
    private String appRegion;
    private String channelId;
    private String deviceId;
    private String extraInfo;
    private int goodsType;
    private boolean isQrcode;
    private String productId;
    private String roleId;
    private String roleLevel;
    private String roleName;
    private int roleVipLevel;
    private String sdkOpenId;
    private String serverId;
    private boolean isDouyinLiveAndCloud = false;
    private String purchaseScene = "";
    private String purchaseId = "";
    private String extraParams = "";
    private long ts = System.currentTimeMillis() / 1000;
    private String os = "android";

    public OrderParams(String str, RocketPayInfo rocketPayInfo, String str2) {
        this.actuallyCurrency = "";
        this.sdkOpenId = rocketPayInfo.getSdkOpenId();
        this.channelId = str;
        this.productId = rocketPayInfo.getProductId();
        this.serverId = rocketPayInfo.getServerId();
        this.roleId = rocketPayInfo.getRoleId();
        this.roleName = rocketPayInfo.getRoleName();
        this.roleVipLevel = rocketPayInfo.getRoleVipLevel();
        this.roleLevel = rocketPayInfo.getRoleLevel();
        this.extraInfo = rocketPayInfo.getExtraInfo();
        this.deviceId = str2;
        this.appRegion = rocketPayInfo.getAppRegion();
        this.appLanguage = rocketPayInfo.getAppLanguage();
        this.activity = rocketPayInfo.getActivity();
        this.goodsType = rocketPayInfo.getGoodType();
        this.actuallyPrice = rocketPayInfo.getAmount();
        this.actuallyCurrency = rocketPayInfo.getCurrency();
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getSdkOpenId() {
        return this.sdkOpenId;
    }

    public void setSdkOpenId(String str) {
        this.sdkOpenId = str;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String str) {
        this.channelId = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public long getTs() {
        return this.ts;
    }

    public void setTs(long j2) {
        this.ts = j2;
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

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String str) {
        this.roleName = str;
    }

    public String getRoleLevel() {
        return this.roleLevel;
    }

    public void setRoleLevel(String str) {
        this.roleLevel = str;
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

    public String getOs() {
        return this.os;
    }

    public void setOs(String str) {
        this.os = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
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
        String str = this.activity;
        return str == null ? new JSONObject().toString() : str;
    }

    public void setActivity(String str) {
        this.activity = str;
    }

    public long getActuallyPrice() {
        return this.actuallyPrice;
    }

    public void setActuallyPrice(long j2) {
        this.actuallyPrice = j2;
    }

    public String getActuallyCurrency() {
        return this.actuallyCurrency;
    }

    public void setActuallyCurrency(String str) {
        this.actuallyCurrency = str;
    }

    public boolean isQrcode() {
        return this.isQrcode;
    }

    public void setQrcode(boolean z) {
        this.isQrcode = z;
    }

    public String getPurchaseScene() {
        return this.purchaseScene;
    }

    public void setPurchaseScene(String str) {
        this.purchaseScene = str;
    }

    public String getPurchaseId() {
        return this.purchaseId;
    }

    public void setPurchaseId(String str) {
        this.purchaseId = str;
    }

    public int getGoodsType() {
        return this.goodsType;
    }

    public boolean isDouyinLiveAndCloud() {
        return this.isDouyinLiveAndCloud;
    }

    public void setDouyinLiveAndCloud(boolean z) {
        this.isDouyinLiveAndCloud = z;
    }

    public String getExtraParams() {
        return this.extraParams;
    }

    public void setExtraParams(String str) {
        this.extraParams = str;
    }
}
