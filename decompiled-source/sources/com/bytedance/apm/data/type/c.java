package com.bytedance.apm.data.type;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: EventData.java */
/* loaded from: classes.dex */
public class c implements com.bytedance.apm.data.b {
    public JSONObject category;
    public JSONObject extrJson;
    public boolean isUploadImmediately;
    public JSONObject metric;
    public String serviceName;
    public int status;
    public JSONObject value;

    @Override // com.bytedance.apm.data.b
    public String getSubTypeLabel() {
        return "service_monitor";
    }

    @Override // com.bytedance.apm.data.b
    public String getTypeLabel() {
        return "service_monitor";
    }

    @Override // com.bytedance.apm.data.b
    public boolean isSaveImmediately() {
        return false;
    }

    @Override // com.bytedance.apm.data.b
    public boolean supportFetch() {
        return true;
    }

    public c(String str, int i2, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4) {
        this.serviceName = str;
        this.status = i2;
        this.value = jSONObject;
        this.category = jSONObject2;
        this.metric = jSONObject3;
        this.extrJson = jSONObject4;
    }

    public c(String str, int i2, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, boolean z) {
        this.serviceName = str;
        this.status = i2;
        this.value = jSONObject;
        this.category = jSONObject2;
        this.metric = jSONObject3;
        this.extrJson = jSONObject4;
        this.isUploadImmediately = z;
    }

    @Override // com.bytedance.apm.data.b
    public JSONObject packLog() {
        if (this.extrJson == null) {
            this.extrJson = new JSONObject();
        }
        try {
            this.extrJson.put("log_type", "service_monitor");
            this.extrJson.put("service", this.serviceName);
            this.extrJson.put("status", this.status);
            JSONObject jSONObject = this.value;
            if (jSONObject != null) {
                this.extrJson.put("value", jSONObject);
            }
            JSONObject jSONObject2 = this.category;
            if (jSONObject2 != null) {
                this.extrJson.put("category", jSONObject2);
            }
            JSONObject jSONObject3 = this.metric;
            if (jSONObject3 != null) {
                this.extrJson.put("metric", jSONObject3);
            }
            return this.extrJson;
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // com.bytedance.apm.data.b
    public boolean isSampled(JSONObject jSONObject) {
        return com.bytedance.apm.samplers.c.getServiceSwitch(this.serviceName);
    }

    @Override // com.bytedance.apm.data.b
    public boolean isUploadImmediately() {
        return this.isUploadImmediately;
    }
}
