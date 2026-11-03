package com.bytedance.ttgame.module.account.toutiao.account.api;

import java.io.Serializable;
/* loaded from: classes4.dex */
public class AccountManageEvent implements Serializable {
    public static final int CLOSE_BIND_MOBILE_TYPE = 3;
    public static final int FAILED = 1;
    public static final int OPEN_BIND_PHONE_TYPE = 0;
    public static final int SUCCESS = 0;
    private int eventType;
    private AccountManageResult result;

    public AccountManageResult getResult() {
        return this.result;
    }

    public void setResult(AccountManageResult accountManageResult) {
        this.result = accountManageResult;
    }

    public int getEventType() {
        return this.eventType;
    }

    public void setEventType(int i2) {
        this.eventType = i2;
    }

    public AccountManageEvent(AccountManageResult accountManageResult, int i2) {
        this.result = accountManageResult;
        this.eventType = i2;
    }

    public String toString() {
        return "AccountManageEvent{result=" + this.result + ", eventType=" + this.eventType + '}';
    }
}
