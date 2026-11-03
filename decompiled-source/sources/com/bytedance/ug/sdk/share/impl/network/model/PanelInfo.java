package com.bytedance.ug.sdk.share.impl.network.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class PanelInfo implements Serializable {
    @SerializedName("channel_list")
    private List<String> mChannelList;
    @SerializedName("filtered_channel_list")
    private List<String> mFilteredChannelList;
    @SerializedName("panel_id")
    private String mPanelId;
    @SerializedName("info_share_url")
    private String mShareInfoUrl;

    public String getPanelId() {
        return this.mPanelId;
    }

    public void setPanelId(String str) {
        this.mPanelId = str;
    }

    public List<String> getChannelList() {
        return this.mChannelList;
    }

    public void setChannelList(List<String> list) {
        this.mChannelList = list;
    }

    public List<String> getFilteredChannelList() {
        return this.mFilteredChannelList;
    }

    public void setFilteredChannelList(List<String> list) {
        this.mFilteredChannelList = list;
    }

    public String getShareInfoUrl() {
        return this.mShareInfoUrl;
    }

    public void setShareInfoUrl(String str) {
        this.mShareInfoUrl = str;
    }
}
