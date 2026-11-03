package com.bytedance.ttgame.rocketapi.account;
/* loaded from: classes7.dex */
public class AccountManagerResult {
    public static final int BIND_USER = 1;
    public static final int LOGOUT_USER = 2;
    public int eventType;
    public UserInfoResult userInfoResult;

    public AccountManagerResult(int i2, UserInfoResult userInfoResult) {
        this.eventType = i2;
        this.userInfoResult = userInfoResult;
    }
}
