package com.bytedance.sdk.open.tiktok.base;

import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes3.dex */
public class MicroAppInfo {
    @SerializedName(RemoteConfigConstants.RequestFieldKey.APP_ID)
    private String appId;
    @SerializedName("appTitle")
    private String appTitle;
    @SerializedName("appUrl")
    private String appUrl;
    @SerializedName("description")
    private String description;

    public void serialize(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.putString(ParamKeyConstants.ShareParams.SHARE_MICROAPP_INFO, new Gson().toJson(this));
    }

    public static MicroAppInfo unserialize(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString(ParamKeyConstants.ShareParams.SHARE_MICROAPP_INFO);
        try {
            if (!TextUtils.isEmpty(string)) {
                return (MicroAppInfo) new Gson().fromJson(string, (Class<Object>) MicroAppInfo.class);
            }
        } catch (JsonSyntaxException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getAppTitle() {
        return this.appTitle;
    }

    public void setAppTitle(String str) {
        this.appTitle = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getAppUrl() {
        return this.appUrl;
    }

    public void setAppUrl(String str) {
        this.appUrl = str;
    }
}
