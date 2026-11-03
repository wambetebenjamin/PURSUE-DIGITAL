package com.bytedance.sync.model;

import com.google.gson.annotations.SerializedName;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
/* compiled from: SubscribeResponse.java */
/* loaded from: classes3.dex */
public class d {
    @SerializedName(MonitorConstants.STATUS_CODE)

    /* renamed from: a  reason: collision with root package name */
    private int f13473a;
    @SerializedName("msg")

    /* renamed from: b  reason: collision with root package name */
    private String f13474b;
    @SerializedName("data")

    /* renamed from: c  reason: collision with root package name */
    private String f13475c;

    public int getStatusCode() {
        return this.f13473a;
    }

    public void setStatusCode(int i2) {
        this.f13473a = i2;
    }

    public String getMsg() {
        return this.f13474b;
    }

    public void setMsg(String str) {
        this.f13474b = str;
    }

    public String getData() {
        return this.f13475c;
    }

    public void setData(String str) {
        this.f13475c = str;
    }
}
