package com.bytedance.apm.data.type;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: ExceptionLogData.java */
/* loaded from: classes.dex */
public class d implements com.bytedance.apm.data.b {

    /* renamed from: a  reason: collision with root package name */
    private String f999a;

    /* renamed from: b  reason: collision with root package name */
    private JSONObject f1000b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f1001c;

    /* renamed from: d  reason: collision with root package name */
    private long f1002d;

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

    public d(String str, JSONObject jSONObject) {
        this(str, jSONObject, System.currentTimeMillis());
    }

    public d(String str, JSONObject jSONObject, long j2) {
        this.f999a = str;
        this.f1000b = jSONObject;
        this.f1002d = j2;
    }

    @Override // com.bytedance.apm.data.b
    public JSONObject packLog() {
        JSONObject jSONObject = this.f1000b;
        if (jSONObject != null) {
            try {
                jSONObject.put("timestamp", this.f1002d);
                this.f1000b.put("crash_time", this.f1002d);
                this.f1000b.put("is_main_process", com.bytedance.apm.c.isMainProcess());
                this.f1000b.put("process_name", com.bytedance.apm.c.getCurrentProcessName());
                this.f1000b.put("log_type", this.f999a);
                if (com.bytedance.apm.c.getAppLaunchStartTimestamp() <= com.bytedance.apm.c.getStartId() && com.bytedance.apm.c.getAppLaunchStartTimestamp() != 0) {
                    this.f1000b.put(com.bytedance.apm.constant.e.KEY_APP_START_TIME, com.bytedance.apm.c.getAppLaunchStartTimestamp());
                } else {
                    this.f1000b.put(com.bytedance.apm.constant.e.KEY_APP_START_TIME, com.bytedance.apm.c.getStartId());
                }
            } catch (JSONException unused) {
            }
            return this.f1000b;
        }
        return null;
    }

    public void forceSample() {
        this.f1001c = true;
    }

    @Override // com.bytedance.apm.data.b
    public boolean isSampled(JSONObject jSONObject) {
        return this.f1001c || com.bytedance.apm.samplers.c.getPerfSecondStageSwitch(this.f999a);
    }

    @Override // com.bytedance.apm.data.b
    public String getTypeLabel() {
        return this.f999a;
    }

    @Override // com.bytedance.apm.data.b
    public String getSubTypeLabel() {
        return this.f999a;
    }

    public long crashTime() {
        return this.f1002d;
    }

    public JSONObject getLogJson() {
        return this.f1000b;
    }

    public String toString() {
        return "ExceptionLogData{eventType='" + this.f999a + "', logJson=" + this.f1000b + ", forceSampled=" + this.f1001c + ", time=" + this.f1002d + '}';
    }
}
