package com.bytedance.ttgame.module.database.api;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes5.dex */
public class PassInfoData implements Serializable, Cloneable {
    private static final long serialVersionUID = -6283362518525511523L;
    @SerializedName("avatar")
    public String avatar;
    @SerializedName("email")
    public String email;
    @SerializedName("logoff_status")
    public int logoffStatus;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("pass_id")
    public long passId;
    @SerializedName("sub_status")
    public int subStatus;

    public String toString() {
        return "PassInfoData{passId=" + this.passId + ", nickname='" + this.nickname + "', email='" + this.email + "', subStatus=" + this.subStatus + ", logoff_status=" + this.logoffStatus + ", avatar=" + this.avatar + '}';
    }

    /* renamed from: clone */
    public PassInfoData m200clone() {
        return (PassInfoData) super.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PassInfoData passInfoData = (PassInfoData) obj;
        return this.passId == passInfoData.passId && TextUtils.equals(this.nickname, passInfoData.nickname) && TextUtils.equals(this.email, passInfoData.email);
    }
}
