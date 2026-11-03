package com.bytedance.ug.sdk.share.impl.network.model;

import com.bytedance.bdturing.localstorage.b;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class InitShareResponse implements Serializable {
    @SerializedName("ak_share")
    private String mAppKey;
    @SerializedName(b.KEY_SETTINGS)
    private InitShareSettings mInitSettings;
    @SerializedName("panel_list")
    private List<PanelInfo> mPanelList;
    @SerializedName("token_activity_reg")
    private List<TokenRefluxInfo> mTokenActivityRegex;
    @SerializedName("token_pic_reg")
    private List<TokenRefluxInfo> mTokenPicRegex;
    @SerializedName("token_reg")
    private String mTokenRegex;
    @SerializedName("token_strategy")
    private List<Integer> mTokenStrategy;
    @SerializedName("token_video_reg")
    private List<TokenRefluxInfo> mTokenVideoRegex;

    public List<PanelInfo> getPanelList() {
        return this.mPanelList;
    }

    public void setPanelList(List<PanelInfo> list) {
        this.mPanelList = list;
    }

    public String getTokenRegex() {
        return this.mTokenRegex;
    }

    public void setTokenRegex(String str) {
        this.mTokenRegex = str;
    }

    public List<TokenRefluxInfo> getTokenActivityRegex() {
        return this.mTokenActivityRegex;
    }

    public void setTokenActivityRegex(List<TokenRefluxInfo> list) {
        this.mTokenActivityRegex = list;
    }

    public List<TokenRefluxInfo> getTokenPicRegex() {
        return this.mTokenPicRegex;
    }

    public void setTokenPicRegex(List<TokenRefluxInfo> list) {
        this.mTokenPicRegex = list;
    }

    public List<TokenRefluxInfo> getTokenVideoRegex() {
        return this.mTokenVideoRegex;
    }

    public void setTokenVideoRegex(List<TokenRefluxInfo> list) {
        this.mTokenVideoRegex = list;
    }

    public List<Integer> getTokenStrategy() {
        return this.mTokenStrategy;
    }

    public void setTokenRuleList(List<Integer> list) {
        this.mTokenStrategy = list;
    }

    public String getAppKey() {
        return this.mAppKey;
    }

    public void setAppKey(String str) {
        this.mAppKey = str;
    }

    public InitShareSettings getInitSettings() {
        return this.mInitSettings;
    }

    public void setInitSettings(InitShareSettings initShareSettings) {
        this.mInitSettings = initShareSettings;
    }
}
