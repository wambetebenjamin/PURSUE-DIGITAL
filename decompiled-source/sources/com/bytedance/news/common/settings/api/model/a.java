package com.bytedance.news.common.settings.api.model;

import org.json.JSONObject;
/* compiled from: DiffSettingsData.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private JSONObject f9763a;

    /* renamed from: b  reason: collision with root package name */
    private long f9764b;

    public a(JSONObject jSONObject, long j2) {
        this.f9763a = jSONObject;
        this.f9764b = j2;
    }

    public JSONObject getAppSettings() {
        return this.f9763a;
    }

    public long getTimestamp() {
        return this.f9764b;
    }
}
