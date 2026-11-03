package com.bytedance.sdk.open.tiktok.base;

import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes3.dex */
public class AnchorObject {
    @SerializedName("anchor_business_type")
    int mAnchorBusinessType;
    @SerializedName("anchor_content")
    String mAnchorContent;
    @SerializedName("anchor_title")
    String mAnchorTitle;

    public int getAnchorBusinessType() {
        return this.mAnchorBusinessType;
    }

    public void setAnchorBusinessType(int i2) {
        this.mAnchorBusinessType = i2;
    }

    public String getAnchorTitle() {
        return this.mAnchorTitle;
    }

    public void setAnchorTitle(String str) {
        this.mAnchorTitle = str;
    }

    public String getAnchorContent() {
        return this.mAnchorContent;
    }

    public void setAnchorContent(String str) {
        this.mAnchorContent = str;
    }

    public void serialize(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.putString(ParamKeyConstants.ShareParams.SHARE_ANCHOR_INFO, new Gson().toJson(this));
    }

    public static AnchorObject unserialize(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString(ParamKeyConstants.ShareParams.SHARE_ANCHOR_INFO);
        try {
            if (!TextUtils.isEmpty(string)) {
                return (AnchorObject) new Gson().fromJson(string, (Class<Object>) AnchorObject.class);
            }
        } catch (JsonSyntaxException e2) {
            e2.printStackTrace();
        }
        return null;
    }
}
