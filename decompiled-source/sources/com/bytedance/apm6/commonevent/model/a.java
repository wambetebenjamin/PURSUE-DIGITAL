package com.bytedance.apm6.commonevent.model;

import android.text.TextUtils;
import com.bytedance.apm6.util.f;
import org.json.JSONObject;
/* compiled from: CommonEvent.java */
/* loaded from: classes.dex */
public final class a implements com.bytedance.apm6.monitor.c {
    public static final String LOG_TYPE = "service_monitor";

    /* renamed from: a  reason: collision with root package name */
    private String f1734a;

    /* renamed from: b  reason: collision with root package name */
    private int f1735b;

    /* renamed from: c  reason: collision with root package name */
    private JSONObject f1736c;

    /* renamed from: d  reason: collision with root package name */
    private JSONObject f1737d;

    /* renamed from: e  reason: collision with root package name */
    private JSONObject f1738e;

    /* renamed from: f  reason: collision with root package name */
    private JSONObject f1739f;

    @Override // com.bytedance.apm6.monitor.c
    public String getLogType() {
        return "service_monitor";
    }

    public a(String str, int i2, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4) {
        this.f1734a = str;
        this.f1735b = i2;
        this.f1736c = jSONObject;
        this.f1737d = jSONObject2;
        this.f1738e = jSONObject3;
        this.f1739f = jSONObject4;
    }

    public String getServiceName() {
        return this.f1734a;
    }

    public int getStatus() {
        return this.f1735b;
    }

    public JSONObject getValue() {
        return this.f1736c;
    }

    public JSONObject getCategory() {
        return this.f1737d;
    }

    public JSONObject getMetric() {
        return this.f1738e;
    }

    public JSONObject getExtraJson() {
        return this.f1739f;
    }

    @Override // com.bytedance.apm6.monitor.c
    public boolean isValid() {
        return !TextUtils.isEmpty(this.f1734a);
    }

    @Override // com.bytedance.apm6.monitor.c
    public JSONObject toJsonObject() {
        try {
            JSONObject extraJson = getExtraJson();
            if (extraJson == null) {
                extraJson = new JSONObject();
            }
            extraJson.put("log_type", "service_monitor");
            extraJson.put("service", getServiceName());
            extraJson.put("status", getStatus());
            if (getValue() != null) {
                extraJson.put("value", getValue());
            }
            if (getCategory() != null) {
                extraJson.put("category", getCategory());
            }
            if (getMetric() != null) {
                extraJson.put("metric", getMetric());
            }
            return extraJson;
        } catch (Exception e2) {
            if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                com.bytedance.apm6.util.log.b.w(com.bytedance.apm6.commonevent.c.TAG, "toJsonObject Error.", e2);
                return null;
            }
            return null;
        }
    }

    public void a() {
        this.f1736c = f.safeCopyJson(this.f1736c);
        this.f1737d = f.safeCopyJson(this.f1737d);
        this.f1738e = f.safeCopyJson(this.f1738e);
        this.f1739f = f.safeCopyJson(this.f1739f);
    }

    public String toString() {
        return "CommonEvent{serviceName='" + this.f1734a + "'}";
    }
}
