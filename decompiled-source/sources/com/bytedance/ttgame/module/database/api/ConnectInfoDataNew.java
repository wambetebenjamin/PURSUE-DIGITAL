package com.bytedance.ttgame.module.database.api;

import android.text.TextUtils;
import com.bytedance.sdk.account.save.database.a;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes5.dex */
public class ConnectInfoDataNew implements Serializable, Cloneable {
    private static final long serialVersionUID = 6178568407352647537L;
    @SerializedName(a.FIELD_AVATAR_URL)
    public String avatar_url;
    @SerializedName("encrypted_nickname")
    public String encryptedNickname;
    @SerializedName("eternal_avatar")
    public String eternalAvatar;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("puid")
    public long puid;
    @SerializedName("user_type")
    public int user_type;

    public String toString() {
        return "ConnectInfoData {puid =" + this.puid + ", user_type ='" + this.user_type + "', nickname ='" + this.nickname + "', avatar_url =" + this.avatar_url + ", eternalAvatar =" + this.eternalAvatar + ", encryptedNickname =" + this.encryptedNickname + '}';
    }

    /* renamed from: clone */
    public ConnectInfoDataNew m199clone() {
        return (ConnectInfoDataNew) super.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ConnectInfoDataNew connectInfoDataNew = (ConnectInfoDataNew) obj;
        return this.puid == connectInfoDataNew.puid && this.user_type == connectInfoDataNew.user_type && TextUtils.equals(this.nickname, connectInfoDataNew.nickname) && TextUtils.equals(this.avatar_url, connectInfoDataNew.avatar_url) && TextUtils.equals(this.eternalAvatar, connectInfoDataNew.eternalAvatar) && TextUtils.equals(this.encryptedNickname, connectInfoDataNew.encryptedNickname);
    }
}
