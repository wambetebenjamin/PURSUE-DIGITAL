package com.bytedance.ug.sdk.share.api.entity;

import org.json.JSONObject;
/* compiled from: ShareMonitorEvent.java */
/* loaded from: classes8.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private String f15586a;

    /* renamed from: b  reason: collision with root package name */
    private JSONObject f15587b;

    /* renamed from: c  reason: collision with root package name */
    private JSONObject f15588c;

    /* renamed from: d  reason: collision with root package name */
    private int f15589d;

    /* renamed from: e  reason: collision with root package name */
    private JSONObject f15590e;

    /* renamed from: f  reason: collision with root package name */
    private JSONObject f15591f;

    public JSONObject getDuration() {
        return this.f15591f;
    }

    public void setDuration(JSONObject jSONObject) {
        this.f15591f = jSONObject;
    }

    public JSONObject getLogExtra() {
        return this.f15590e;
    }

    public void setLogExtra(JSONObject jSONObject) {
        this.f15590e = jSONObject;
    }

    public String getServiceName() {
        return this.f15586a;
    }

    public void setServiceName(String str) {
        this.f15586a = str;
    }

    public JSONObject getCategory() {
        return this.f15587b;
    }

    public void setCategory(JSONObject jSONObject) {
        this.f15587b = jSONObject;
    }

    public JSONObject getMetric() {
        return this.f15588c;
    }

    public void setMetric(JSONObject jSONObject) {
        this.f15588c = jSONObject;
    }

    public int getStatus() {
        return this.f15589d;
    }

    public void setStatus(int i2) {
        this.f15589d = i2;
    }
}
