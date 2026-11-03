package com.bytedance.ug.sdk.share.api.entity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* compiled from: TokenLogInfoBean.java */
/* loaded from: classes8.dex */
public class r implements Serializable {
    @SerializedName("group_id")

    /* renamed from: a  reason: collision with root package name */
    private String f15615a;
    @SerializedName(FirebaseAnalytics.Param.ITEM_ID)

    /* renamed from: b  reason: collision with root package name */
    private String f15616b;
    @SerializedName("user_id")

    /* renamed from: c  reason: collision with root package name */
    private String f15617c;
    @SerializedName("share_user_id")

    /* renamed from: d  reason: collision with root package name */
    private String f15618d;
    @SerializedName("group_type")

    /* renamed from: e  reason: collision with root package name */
    private String f15619e;
    @SerializedName("log_pb")

    /* renamed from: f  reason: collision with root package name */
    private JsonObject f15620f;
    @SerializedName("tma_share")

    /* renamed from: g  reason: collision with root package name */
    private String f15621g;

    public String getGroupId() {
        return this.f15615a;
    }

    public void setGroupId(String str) {
        this.f15615a = str;
    }

    public String getItemId() {
        return this.f15616b;
    }

    public void setItemId(String str) {
        this.f15616b = str;
    }

    public String getUserId() {
        return this.f15617c;
    }

    public void setUserId(String str) {
        this.f15617c = str;
    }

    public String getShareUserId() {
        return this.f15618d;
    }

    public void setShareUserId(String str) {
        this.f15618d = str;
    }

    public String getGroupType() {
        return this.f15619e;
    }

    public void setGroupType(String str) {
        this.f15619e = str;
    }

    public JsonObject getLogPb() {
        return this.f15620f;
    }

    public void setLogPb(JsonObject jsonObject) {
        this.f15620f = jsonObject;
    }

    public String getTmaShare() {
        return this.f15621g;
    }

    public void setTmaShare(String str) {
        this.f15621g = str;
    }
}
