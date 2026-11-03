package com.bytedance.framwork.core.sdkmonitor;

import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ServiceMonitorData.java */
/* loaded from: classes2.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    String f8827a;

    /* renamed from: b  reason: collision with root package name */
    int f8828b;

    /* renamed from: c  reason: collision with root package name */
    JSONObject f8829c;
    public JSONObject category;

    /* renamed from: d  reason: collision with root package name */
    JSONObject f8830d;

    /* renamed from: e  reason: collision with root package name */
    long f8831e;
    public JSONObject metric;

    public k(String str, int i2, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, long j2) {
        this.f8827a = str;
        this.f8828b = i2;
        this.f8829c = jSONObject;
        this.category = jSONObject2;
        this.metric = jSONObject3;
        this.f8830d = jSONObject4;
        this.f8831e = j2;
    }
}
