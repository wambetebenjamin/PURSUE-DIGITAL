package com.bytedance.ttgame.rocketapi.account;
/* loaded from: classes7.dex */
public class SuccessionCodeInfo {
    public String succession_code;
    public String succession_id;

    public SuccessionCodeInfo(String str, String str2) {
        this.succession_id = str;
        this.succession_code = str2;
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
        return "SuccessionCodeInfo {succession_id =" + this.succession_id + ", succession_code =" + this.succession_code + '}';
    }
}
