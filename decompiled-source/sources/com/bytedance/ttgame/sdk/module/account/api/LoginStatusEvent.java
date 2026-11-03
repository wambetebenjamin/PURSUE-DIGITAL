package com.bytedance.ttgame.sdk.module.account.api;

import java.io.Serializable;
/* loaded from: classes3.dex */
public class LoginStatusEvent implements Serializable {
    public static final int BIND_WITHOUT_UI_TYPE = 12;
    public static final int CONNECT_ACCOUNT = 8;
    public static final int FORCER_EBIND_I_WITHOUT_UI_TYPE = 11;
    public static final int LOG_OFF = 9;
    public static final int OPEN_AUTH_I_WITHOUT_UI_TYPE = 7;
    public static final int OPEN_BIND_TYPE = 3;
    public static final int OPEN_LOGIN_WITHOUT_UI_TYPE = 6;
    public static final int OPEN_PANEL_TYPE = 1;
    public static final int OPEN_SWITCH_TYPE = 2;
    public static final int OPEN_USERCENTER_TYPE = 5;
    public static final int OPEN_VERIFY_TYPE = 4;
    public static final int UNBIND_I_WITHOUT_UI_TYPE = 10;
    public static final int USERINFO_MIGRATE_SUCCESS = 80;
    private int eventType;
    private TTUserInfoResult userInfo;

    public TTUserInfoResult getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(TTUserInfoResult tTUserInfoResult) {
        this.userInfo = tTUserInfoResult;
    }

    public int getEventType() {
        return this.eventType;
    }

    public void setEventType(int i2) {
        this.eventType = i2;
    }

    public LoginStatusEvent(TTUserInfoResult tTUserInfoResult, int i2) {
        this.userInfo = tTUserInfoResult;
        this.eventType = i2;
    }

    public String toString() {
        TTUserInfoResult tTUserInfoResult = this.userInfo;
        return (tTUserInfoResult == null || tTUserInfoResult.data == null) ? "" : "UserInfoData{userId=" + this.userInfo.data.getUserId() + ", nickname='" + this.userInfo.data.getNickname() + "', encryptedNickname='" + this.userInfo.data.getEncryptedNickname() + "', avatarUrl='" + this.userInfo.data.getAvatarUrl() + "', eternalAvatar='" + this.userInfo.data.getEternalAvatar() + "', token='" + this.userInfo.data.getToken() + "', isVerified=" + this.userInfo.data.isVerified() + ", userType=" + this.userInfo.data.getUserType() + ", isPay=" + this.userInfo.data.isPay() + ", isBound=" + this.userInfo.data.isBound() + ", loginTime=" + this.userInfo.data.getLoginTime() + '}';
    }
}
