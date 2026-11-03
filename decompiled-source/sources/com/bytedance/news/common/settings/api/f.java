package com.bytedance.news.common.settings.api;

import java.util.UUID;
import org.json.JSONObject;
/* compiled from: SettingsData.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private JSONObject f9759a;

    /* renamed from: b  reason: collision with root package name */
    private JSONObject f9760b;

    /* renamed from: c  reason: collision with root package name */
    private String f9761c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f9762d;

    public f(JSONObject jSONObject, JSONObject jSONObject2) {
        this(jSONObject, jSONObject2, UUID.randomUUID().toString(), true);
    }

    public f(JSONObject jSONObject, JSONObject jSONObject2, String str, boolean z) {
        this.f9762d = true;
        this.f9759a = jSONObject;
        this.f9760b = jSONObject2;
        this.f9761c = str;
        this.f9762d = z;
    }

    public JSONObject getAppSettings() {
        return this.f9759a;
    }

    public JSONObject getUserSettings() {
        return this.f9760b;
    }

    public String getToken() {
        return this.f9761c;
    }

    public boolean isFromServer() {
        return this.f9762d;
    }
}
