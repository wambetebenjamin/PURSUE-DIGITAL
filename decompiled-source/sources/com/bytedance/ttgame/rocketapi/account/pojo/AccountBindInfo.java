package com.bytedance.ttgame.rocketapi.account.pojo;

import com.bytedance.sdk.account.save.database.a;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes7.dex */
public class AccountBindInfo implements Serializable {
    private static final long serialVersionUID = 6661314134689547656L;
    @SerializedName(a.FIELD_AVATAR_URL)
    public String avatarUrl;
    @SerializedName("create_time")
    public String createTime;
    @SerializedName("encrypted_nickname")
    public String encryptedNickname;
    @SerializedName("has_pwd")
    public boolean hasPwd;
    @SerializedName("mobile")
    public String mobile;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("puid")
    public long puid;
    @SerializedName("user_type")
    public int userType;

    public String toString() {
        return "AccountBindInfo{puid=" + this.puid + ", userType=" + this.userType + ", nickName='" + this.nickname + "', avatarUrl='" + this.avatarUrl + "', mobile='" + this.mobile + "', hasPwd=" + this.hasPwd + ", createTime=" + this.createTime + ", encryptedNickname=" + this.encryptedNickname + '}';
    }
}
