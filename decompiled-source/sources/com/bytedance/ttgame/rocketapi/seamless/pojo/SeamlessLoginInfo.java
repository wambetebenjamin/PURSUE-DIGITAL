package com.bytedance.ttgame.rocketapi.seamless.pojo;

import com.bytedance.sdk.account.save.database.a;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class SeamlessLoginInfo {
    @SerializedName(a.FIELD_AVATAR_URL)
    public String avatarUrl;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("token")
    public String token;
    @SerializedName("user_id")
    public long userId;
    @SerializedName("user_type")
    public int userType;

    public String toString() {
        return "SeamlessLoginInfo{userId=" + this.userId + ", nickname='" + this.nickname + "', avatarUrl='" + this.avatarUrl + "', token='" + this.token + "', userType=" + this.userType + '}';
    }
}
