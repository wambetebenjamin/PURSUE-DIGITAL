package com.bytedance.ttgame.module.database.api;

import android.text.TextUtils;
import com.bytedance.sdk.account.platform.base.i;
import com.bytedance.ttgame.library.luffy.db.PrimaryKeyName;
import com.bytedance.ttgame.library.luffy.db.TableName;
import com.bytedance.ttgame.module.account.api.Constant;
import com.bytedance.ttnet.cronet.b;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
@TableName(Constant.TABLE_NAME_FUSION_USERINFO)
@NeedEncryption
/* loaded from: classes5.dex */
public class FusionUserInfoData implements Serializable, Cloneable {
    public static final long serialVersionUID = 3769536434612285333L;
    @SerializedName(i.CT_KEY_ACCESS_TOKEN)
    @NeedEncryption
    public String accessToken;
    @PrimaryKeyName
    @SerializedName("channel_userId")
    public String channelUserId;
    @SerializedName("create_time")
    public String createTime;
    @SerializedName("sdkOpenId")
    public String sdkOpenId;
    @SerializedName("encrypted")
    @EncryptionMark
    public boolean encrypted = false;
    @SerializedName(b.f15234b)
    public String storeRegion = "";

    public String toString() {
        return "FusionUserInfoData{channelUserId='" + this.channelUserId + "', sdkOpenId='" + this.sdkOpenId + "', accessToken='" + this.accessToken + "', storeRegion='" + this.storeRegion + "', createTime='" + this.createTime + "', encrypted='" + this.encrypted + "'}";
    }

    public Object clone() {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FusionUserInfoData) {
            FusionUserInfoData fusionUserInfoData = (FusionUserInfoData) obj;
            return this.encrypted == fusionUserInfoData.encrypted && TextUtils.equals(this.channelUserId, fusionUserInfoData.channelUserId) && TextUtils.equals(this.sdkOpenId, fusionUserInfoData.sdkOpenId) && TextUtils.equals(this.accessToken, fusionUserInfoData.accessToken) && TextUtils.equals(this.storeRegion, fusionUserInfoData.storeRegion) && TextUtils.equals(this.createTime, fusionUserInfoData.createTime);
        }
        return false;
    }
}
