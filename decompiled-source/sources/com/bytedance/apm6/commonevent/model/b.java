package com.bytedance.apm6.commonevent.model;

import android.text.TextUtils;
import com.bytedance.apm6.util.f;
import org.json.JSONObject;
/* compiled from: CommonLog.java */
/* loaded from: classes.dex */
public final class b implements com.bytedance.apm6.monitor.c {

    /* renamed from: a  reason: collision with root package name */
    private String f1740a;

    /* renamed from: b  reason: collision with root package name */
    private JSONObject f1741b;

    public b(String str, JSONObject jSONObject) {
        this.f1740a = str;
        this.f1741b = jSONObject;
    }

    @Override // com.bytedance.apm6.monitor.c
    public String getLogType() {
        return this.f1740a;
    }

    public JSONObject getLogJson() {
        return this.f1741b;
    }

    @Override // com.bytedance.apm6.monitor.c
    public boolean isValid() {
        return !TextUtils.isEmpty(this.f1740a);
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

    public void a() {
        this.f1741b = f.safeCopyJson(this.f1741b);
    }

    public String toString() {
        return "CommonLog{logType='" + this.f1740a + "'}";
    }
}
