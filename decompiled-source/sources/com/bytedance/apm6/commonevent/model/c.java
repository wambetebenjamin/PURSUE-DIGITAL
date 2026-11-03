package com.bytedance.apm6.commonevent.model;

import android.text.TextUtils;
import org.json.JSONObject;
/* compiled from: GeneralLegacyEvent.java */
/* loaded from: classes.dex */
public final class c implements com.bytedance.apm6.monitor.c {

    /* renamed from: a  reason: collision with root package name */
    private String f1742a;

    /* renamed from: b  reason: collision with root package name */
    private JSONObject f1743b;

    public c(String str, JSONObject jSONObject) {
        this.f1742a = str;
        this.f1743b = jSONObject;
    }

    @Override // com.bytedance.apm6.monitor.c
    public String getLogType() {
        return this.f1742a;
    }

    public JSONObject getLogJson() {
        return this.f1743b;
    }

    @Override // com.bytedance.apm6.monitor.c
    public boolean isValid() {
        return !TextUtils.isEmpty(this.f1742a);
    }

    @Override // com.bytedance.apm6.monitor.c
    public JSONObject toJsonObject() {
        try {
            JSONObject logJson = getLogJson();
            if (logJson == null) {
                logJson = new JSONObject();
            }
            logJson.put("log_type", getLogType());
            return logJson;
        } catch (Exception e2) {
            if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                com.bytedance.apm6.util.log.b.w(com.bytedance.apm6.commonevent.c.TAG, "toJsonObject Error.", e2);
                return null;
            }
            return null;
        }
    }

    public String toString() {
        return "Apm5LegacyEvent{logType='" + this.f1742a + "'}";
    }
}
