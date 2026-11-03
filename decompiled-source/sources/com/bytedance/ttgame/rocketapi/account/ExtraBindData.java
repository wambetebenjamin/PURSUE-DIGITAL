package com.bytedance.ttgame.rocketapi.account;
/* loaded from: classes7.dex */
public class ExtraBindData {
    public String avatar_url;
    public String encryptedNickname;
    public String eternalAvatar;
    public String nickname;
    public long userId;
    public int user_type;

    public String toString() {
        return "ExtraBindData{user_type=" + this.user_type + ", userId='" + this.userId + "', nickname='" + this.nickname + "', avatar_url='" + this.avatar_url + "', eternalAvatar='" + this.eternalAvatar + "', encryptedNickname='" + this.encryptedNickname + "'}";
    }
}
