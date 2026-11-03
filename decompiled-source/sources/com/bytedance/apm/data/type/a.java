package com.bytedance.apm.data.type;

import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.apm.constant.r;
import com.bytedance.apm.constant.s;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: ApiData.java */
/* loaded from: classes.dex */
public class a implements com.bytedance.apm.data.b {
    public String apiType;
    public long duration;
    public JSONObject extJson;
    public String sendIp;
    public long sendTime;
    public String sendUrl;
    public int status;
    public String traceCode;

    @Override // com.bytedance.apm.data.b
    public String getSubTypeLabel() {
        return null;
    }

    @Override // com.bytedance.apm.data.b
    public String getTypeLabel() {
        return null;
    }

    @Override // com.bytedance.apm.data.b
    public boolean isSampled(JSONObject jSONObject) {
        return false;
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
        return true;
    }

    public a(String str, long j2, long j3, String str2, String str3, String str4, int i2, JSONObject jSONObject) {
        this.apiType = str;
        this.duration = j2;
        this.sendTime = j3;
        this.sendUrl = str2;
        this.sendIp = str3;
        this.traceCode = str4;
        this.status = i2;
        if (jSONObject == null) {
            this.extJson = new JSONObject();
        } else {
            this.extJson = jSONObject;
        }
    }

    public void appendFront(boolean z) {
        if (this.extJson.isNull("front")) {
            this.extJson.put("front", z ? 1 : 0);
        }
    }

    public void appendConsumeType(String str) {
        if (this.extJson.isNull(s.KEY_NET_CONSUME_TYPE)) {
            this.extJson.put(s.KEY_NET_CONSUME_TYPE, str);
        }
    }

    public void appendLaunchTraceInfo() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(r.STATS_KEY_TRACE_BASE, com.bytedance.apm.c.getAppLaunchStartTimestamp());
            this.extJson.put("relate_start_trace", jSONObject);
        } catch (JSONException unused) {
        }
    }

    @Override // com.bytedance.apm.data.b
    public JSONObject packLog() {
        if (TextUtils.isEmpty(this.apiType)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("log_type", this.apiType);
            jSONObject.put("duration", this.duration);
            jSONObject.put("uri", Uri.parse(this.sendUrl));
            long j2 = this.sendTime;
            if (j2 > 0) {
                jSONObject.put("timestamp", j2);
            }
            jSONObject.put("status", this.status);
            if (!TextUtils.isEmpty(this.sendIp)) {
                jSONObject.put("ip", this.sendIp);
            }
            if (!TextUtils.isEmpty(this.traceCode)) {
                jSONObject.put("trace_code", this.traceCode);
            } else {
                jSONObject.put("trace_code", "");
            }
            return jSONObject;
        } catch (Exception unused) {
            return null;
        }
    }
}
