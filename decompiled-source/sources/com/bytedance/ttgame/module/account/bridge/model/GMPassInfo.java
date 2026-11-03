package com.bytedance.ttgame.module.account.bridge.model;
/* loaded from: classes4.dex */
public class GMPassInfo {
    public String avatar;
    public String email;
    public int logoffStatus;
    public String nickname;
    public String passId;
    public int subStatus;

    public String toString() {
        return "PassInfo{passId=" + this.passId + ", nickname='" + this.nickname + "', email='" + this.email + "', subStatus=" + this.subStatus + ", logoffStatus=" + this.logoffStatus + ", avatar=" + this.avatar + '}';
    }
}
