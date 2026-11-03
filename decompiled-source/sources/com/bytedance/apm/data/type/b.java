package com.bytedance.apm.data.type;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: CommonLogData.java */
/* loaded from: classes.dex */
public class b implements com.bytedance.apm.data.b {
    public boolean isSaveDBImmediate;
    public JSONObject logJson;
    public String logType;

    @Override // com.bytedance.apm.data.b
    public String getTypeLabel() {
        return "common_log";
    }

    @Override // com.bytedance.apm.data.b
    public boolean isUploadImmediately() {
        return false;
    }

    @Override // com.bytedance.apm.data.b
    public boolean supportFetch() {
        return true;
    }

    public b(String str, JSONObject jSONObject, boolean z) {
        this.logType = str;
        this.logJson = jSONObject;
        this.isSaveDBImmediate = z;
    }

    @Override // com.bytedance.apm.data.b
    public JSONObject packLog() {
        JSONObject jSONObject = this.logJson;
        if (jSONObject != null) {
            try {
                jSONObject.put("log_type", this.logType);
            } catch (JSONException unused) {
            }
            return this.logJson;
        }
        return null;
    }

    @Override // com.bytedance.apm.data.b
    public boolean isSampled(JSONObject jSONObject) {
        return com.bytedance.apm.samplers.c.getLogTypeSwitch(this.logType);
    }

    @Override // com.bytedance.apm.data.b
    public String getSubTypeLabel() {
        return this.logType;
    }

    @Override // com.bytedance.apm.data.b
    public boolean isSaveImmediately() {
        return this.isSaveDBImmediate;
    }
}
