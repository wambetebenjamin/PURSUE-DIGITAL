package com.bytedance.apm.data.type;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: MetricsData.java */
/* loaded from: classes.dex */
public class e implements com.bytedance.apm.data.b {
    public String key;
    public String type;
    public float value;

    @Override // com.bytedance.apm.data.b
    public String getSubTypeLabel() {
        return "timer";
    }

    @Override // com.bytedance.apm.data.b
    public String getTypeLabel() {
        return "timer";
    }

    @Override // com.bytedance.apm.data.b
    public boolean isSaveImmediately() {
        return false;
    }

    @Override // com.bytedance.apm.data.b
    public boolean isUploadImmediately() {
        return false;
    }

    @Override // com.bytedance.apm.data.b
    public boolean supportFetch() {
        return false;
    }

    public e(String str, String str2, float f2) {
        this.type = str;
        this.key = str2;
        this.value = f2;
    }

    @Override // com.bytedance.apm.data.b
    public JSONObject packLog() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.type);
            jSONObject.put("key", this.key);
            jSONObject.put("value", this.value);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // com.bytedance.apm.data.b
    public boolean isSampled(JSONObject jSONObject) {
        return com.bytedance.apm.samplers.c.getMetricSwitch(this.type);
    }
}
