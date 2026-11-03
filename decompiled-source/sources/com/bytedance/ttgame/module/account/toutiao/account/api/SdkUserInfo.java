package com.bytedance.ttgame.module.account.toutiao.account.api;
/* loaded from: classes4.dex */
public class SdkUserInfo {
    private int code;
    private UserSdkData data;
    private String message;
    private String ttLogId;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i2) {
        this.code = i2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public UserSdkData getData() {
        return this.data;
    }

    public void setData(UserSdkData userSdkData) {
        this.data = userSdkData;
    }

    public String getTtLogId() {
        return this.ttLogId;
    }

    public void setTtLogId(String str) {
        this.ttLogId = str;
    }
}
