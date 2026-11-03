package com.bytedance.ttgame.rocketapi.account.internal;
/* loaded from: classes7.dex */
public class BindInfo {
    public boolean force;
    public String password;
    public int type;

    public BindInfo(int i2, boolean z, String str) {
        this.type = i2;
        this.force = z;
        this.password = str;
    }

    public String toString() {
        return "BindInfo{type=" + this.type + ", force=" + this.force + ", password='" + this.password + "'}";
    }
}
