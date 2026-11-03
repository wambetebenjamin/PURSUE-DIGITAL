package com.bytedance.ug.sdk.share.api.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* compiled from: TokenUserInfoBean.java */
/* loaded from: classes8.dex */
public class t implements Serializable {
    @SerializedName("name")

    /* renamed from: a  reason: collision with root package name */
    private String f15629a;
    @SerializedName("user_id")

    /* renamed from: b  reason: collision with root package name */
    private long f15630b;
    @SerializedName("source_open_url")

    /* renamed from: c  reason: collision with root package name */
    private String f15631c;

    public String getName() {
        return this.f15629a;
    }

    public void setName(String str) {
        this.f15629a = str;
    }

    public long getUserId() {
        return this.f15630b;
    }

    public void setUserId(long j2) {
        this.f15630b = j2;
    }

    public String getSourceOpenUrl() {
        return this.f15631c;
    }

    public void setSourceOpenUrl(String str) {
        this.f15631c = str;
    }
}
