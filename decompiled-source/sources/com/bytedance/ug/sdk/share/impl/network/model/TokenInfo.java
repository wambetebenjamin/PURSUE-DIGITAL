package com.bytedance.ug.sdk.share.impl.network.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class TokenInfo implements Serializable {
    @SerializedName("description")
    private String mDescription;
    @SerializedName("tips")
    private String mTips;
    @SerializedName("title")
    private String mTitle;

    public String getTitle() {
        return this.mTitle;
    }

    public void setmTitle(String str) {
        this.mTitle = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public String getTips() {
        return this.mTips;
    }

    public void setTips(String str) {
        this.mTips = str;
    }
}
