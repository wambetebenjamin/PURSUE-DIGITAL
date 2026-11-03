package com.bytedance.ug.sdk.share.api.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* compiled from: TokenImageInfoBean.java */
/* loaded from: classes8.dex */
public class p implements Serializable {
    @SerializedName("width")

    /* renamed from: a  reason: collision with root package name */
    private int f15600a;
    @SerializedName("height")

    /* renamed from: b  reason: collision with root package name */
    private int f15601b;
    @SerializedName("uri")

    /* renamed from: c  reason: collision with root package name */
    private String f15602c;
    @SerializedName("url")

    /* renamed from: d  reason: collision with root package name */
    private String f15603d;
    @SerializedName("url_list")

    /* renamed from: e  reason: collision with root package name */
    private List<com.bytedance.ug.sdk.share.impl.model.c> f15604e;

    public int getWidth() {
        return this.f15600a;
    }

    public void setWidth(int i2) {
        this.f15600a = i2;
    }

    public int getHeight() {
        return this.f15601b;
    }

    public void setHeight(int i2) {
        this.f15601b = i2;
    }

    public String getUri() {
        return this.f15602c;
    }

    public void setUri(String str) {
        this.f15602c = str;
    }

    public String getUrl() {
        return this.f15603d;
    }

    public void setUrl(String str) {
        this.f15603d = str;
    }

    public List<com.bytedance.ug.sdk.share.impl.model.c> getUrlList() {
        return this.f15604e;
    }

    public void setUrlList(List<com.bytedance.ug.sdk.share.impl.model.c> list) {
        this.f15604e = list;
    }
}
