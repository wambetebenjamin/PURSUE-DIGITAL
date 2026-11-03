package com.bytedance.ttgame.module.account.toutiao.account.api;
/* loaded from: classes4.dex */
public class TTPassInfo {
    public String avatar;
    public String email;
    public int logoffStatus;
    public String nickname;
    public long passId;
    public int subStatus;

    public String toString() {
        return "PassInfoData{passId=" + this.passId + ", nickname='" + this.nickname + "', email='" + this.email + "', subStatus=" + this.subStatus + ", logoff_status=" + this.logoffStatus + ", avatar=" + this.avatar + '}';
    }
}
