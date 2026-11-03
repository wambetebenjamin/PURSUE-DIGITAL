package com.bytedance.ttgame.module.account.bridge.model;
/* loaded from: classes4.dex */
public class GMAccountBindInfo {
    public String avatarUrl;
    public String createTime;
    public String encryptedNickname;
    public boolean hasPwd;
    public String mobile;
    public String nickname;
    public long puid;
    public int userType;

    public String toString() {
        return "AccountBindInfo{puid=" + this.puid + ", userType=" + this.userType + ", nickName='" + this.nickname + "', avatarUrl='" + this.avatarUrl + "', mobile='" + this.mobile + "', hasPwd=" + this.hasPwd + ", createTime=" + this.createTime + ", encryptedNickname=" + this.encryptedNickname + '}';
    }
}
