package com.bytedance.ttgame.rocketapi.account.pojo;
/* loaded from: classes7.dex */
public class PassInfo {
    public String avatar;
    public String email;
    public int logoffStatus;
    public String nickname;
    public long passId;
    public int subStatus;

    public String toString() {
        return "PassInfo{passId=" + this.passId + ", nickname='" + this.nickname + "', email='" + this.email + "', subStatus=" + this.subStatus + ", logoffStatus=" + this.logoffStatus + ", avatar=" + this.avatar + '}';
    }
}
