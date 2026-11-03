package com.bytedance.ttgame.module.database.api;

import android.text.TextUtils;
import com.bytedance.sdk.account.save.database.a;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
@Deprecated
/* loaded from: classes5.dex */
public class ConnectInfoData implements Serializable, Cloneable {
    @SerializedName(a.FIELD_AVATAR_URL)
    public String avatar_url;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("puid")
    public long puid;
    @SerializedName("user_type")
    public int user_type;

    public String toString() {
        return "ConnectInfoData {puid =" + this.puid + ", user_type ='" + this.user_type + "', nickname ='" + this.nickname + "', avatar_url =" + this.avatar_url + '}';
    }

    /* renamed from: clone */
    public ConnectInfoData m198clone() {
        return (ConnectInfoData) super.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ConnectInfoData connectInfoData = (ConnectInfoData) obj;
        return this.puid == connectInfoData.puid && this.user_type == connectInfoData.user_type && TextUtils.equals(this.nickname, connectInfoData.nickname) && TextUtils.equals(this.avatar_url, connectInfoData.avatar_url);
    }
}
