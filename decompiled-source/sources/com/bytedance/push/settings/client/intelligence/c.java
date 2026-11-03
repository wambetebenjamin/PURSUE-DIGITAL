package com.bytedance.push.settings.client.intelligence;

import org.json.JSONObject;
/* compiled from: LocalPushClientIntelligenceSettingsModel.java */
/* loaded from: classes2.dex */
public class c extends com.bytedance.common.push.c {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11757a;

    /* renamed from: b  reason: collision with root package name */
    public int f11758b;

    /* renamed from: c  reason: collision with root package name */
    public int f11759c;

    /* renamed from: d  reason: collision with root package name */
    private final String f11760d;

    /* renamed from: e  reason: collision with root package name */
    private final String f11761e;

    /* renamed from: f  reason: collision with root package name */
    private final String f11762f;

    public c() {
        this.f11760d = "enable_client_intelligence_local_push";
        this.f11761e = "max_try_pull_times";
        this.f11762f = "check_client_feature_interval_in_mill";
        this.f11757a = false;
        this.f11758b = 5;
        this.f11759c = 5000;
    }

    public c(JSONObject jSONObject) {
        this.f11760d = "enable_client_intelligence_local_push";
        this.f11761e = "max_try_pull_times";
        this.f11762f = "check_client_feature_interval_in_mill";
        if (jSONObject == null) {
            this.f11757a = false;
            this.f11758b = 5;
            this.f11759c = 5000;
            return;
        }
        this.f11757a = jSONObject.optBoolean("enable_client_intelligence_local_push", false);
        this.f11758b = jSONObject.optInt("max_try_pull_times", 5);
        this.f11759c = jSONObject.optInt("check_client_feature_interval_in_mill", 5000);
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        add(jSONObject, "enable_client_intelligence_local_push", this.f11757a);
        add(jSONObject, "max_try_pull_times", this.f11758b);
        add(jSONObject, "check_client_feature_interval_in_mill", this.f11759c);
        return jSONObject;
    }
}
