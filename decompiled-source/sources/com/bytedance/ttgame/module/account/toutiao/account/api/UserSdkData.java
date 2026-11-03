package com.bytedance.ttgame.module.account.toutiao.account.api;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes4.dex */
class UserSdkData {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("channel_uid")
    private String channel_uid;
    @SerializedName("create_time")
    private String createTime;
    @SerializedName("is_verified")
    private boolean isVerified;
    @SerializedName("sdk_open_id")
    private String sdkOpenId;

    UserSdkData() {
    }

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
}
