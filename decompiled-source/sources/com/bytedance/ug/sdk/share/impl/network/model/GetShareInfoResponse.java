package com.bytedance.ug.sdk.share.impl.network.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class GetShareInfoResponse implements Serializable {
    @SerializedName("share_info_list")
    List<ShareInfo> mShareInfoList;
    @SerializedName("err_no")
    private int mStatus;
    @SerializedName("err_tips")
    private String mTips;

    public List<ShareInfo> getShareInfoList() {
        return this.mShareInfoList;
    }

    public void setShareInfoList(List<ShareInfo> list) {
        this.mShareInfoList = list;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int i2) {
        this.mStatus = i2;
    }

    public String getTips() {
        return this.mTips;
    }

    public void setTips(String str) {
        this.mTips = str;
    }
}
