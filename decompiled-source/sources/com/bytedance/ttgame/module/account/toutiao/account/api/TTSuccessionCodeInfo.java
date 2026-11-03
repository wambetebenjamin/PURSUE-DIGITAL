package com.bytedance.ttgame.module.account.toutiao.account.api;

import com.bytedance.sdk.account.platform.base.i;
/* loaded from: classes4.dex */
public class TTSuccessionCodeInfo {
    public String succession_code;
    public String succession_id;

    public TTSuccessionCodeInfo(String str, String str2) {
        this.succession_id = i.a.ERROR_CODE_UNKNOW;
        this.succession_code = i.a.ERROR_CODE_UNKNOW;
        this.succession_id = str;
        this.succession_code = str2;
    }

    public TTSuccessionCodeInfo() {
        this.succession_id = i.a.ERROR_CODE_UNKNOW;
        this.succession_code = i.a.ERROR_CODE_UNKNOW;
    }

    public String getSuccession_id() {
        return this.succession_id;
    }

    public void setSuccession_id(String str) {
        this.succession_id = str;
    }

    public String getSuccession_code() {
        return this.succession_code;
    }

    public void setSuccession_code(String str) {
        this.succession_code = str;
    }

    public String toString() {
        return "TTSuccessionCodeInfo {succession_id =" + this.succession_id + ", succession_code =" + this.succession_code + '}';
    }
}
