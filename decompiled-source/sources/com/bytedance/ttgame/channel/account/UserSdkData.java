package com.bytedance.ttgame.channel.account;

import com.bytedance.ttnet.cronet.b;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes3.dex */
public class UserSdkData {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("age_type")
    private int ageType;
    @SerializedName("appeal_url")
    private String appealUrl;
    @SerializedName("channel_uid")
    private String channel_uid;
    @SerializedName("create_time")
    private String createTime;
    @SerializedName("delete_account_status")
    public int deleteAccountStatus;
    @SerializedName("is_register")
    private boolean isRegister;
    @SerializedName("is_verified")
    private boolean isVerified;
    @SerializedName("sdk_open_id")
    private String sdkOpenId;
    @SerializedName(b.f15234b)
    public String storeRegion;

    public String getSdkOpenId() {
        return this.sdkOpenId;
    }

    public void setSdkOpenId(String str) {
        this.sdkOpenId = str;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public boolean isVerified() {
        return this.isVerified;
    }

    public void setVerified(boolean z) {
        this.isVerified = z;
    }

    public String getChannel_uid() {
        return this.channel_uid;
    }

    public void setChannel_uid(String str) {
        this.channel_uid = str;
    }

    public int getAgeType() {
        return this.ageType;
    }

    public void setAgeType(int i2) {
        this.ageType = i2;
    }

    public String getAppealUrl() {
        return this.appealUrl;
    }

    public void setAppealUrl(String str) {
        this.appealUrl = str;
    }

    public int getDeleteAccountStatus() {
        return this.deleteAccountStatus;
    }

    public void setDeleteAccountStatus(int i2) {
        this.deleteAccountStatus = i2;
    }

    public String getStoreRegion() {
        return this.storeRegion;
    }

    public void setStoreRegion(String str) {
        this.storeRegion = str;
    }

    public boolean getIsRegister() {
        return this.isRegister;
    }
}
