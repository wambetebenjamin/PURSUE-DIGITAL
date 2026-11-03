package com.bytedance.crash;

import android.text.TextUtils;
import com.bytedance.crash.anr.d;
import com.bytedance.crash.tracker.a;
import com.bytedance.news.common.service.manager.e;
import com.bytedance.services.apm.api.IApmAgent;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CrashAgent {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6040a = "hmd_app_exit_reason";

    public static void a(CrashType crashType, long j2, JSONObject jSONObject) {
        IApmAgent iApmAgent;
        boolean z = crashType == CrashType.ANR ? !TextUtils.isEmpty(jSONObject.optString(d.f6199a)) : false;
        String c2 = a.c(jSONObject);
        try {
            ICrashMonitorService l = Global.l();
            if (l == null) {
                iApmAgent = (IApmAgent) e.getService(IApmAgent.class);
                if (iApmAgent == null) {
                    return;
                }
            } else {
                iApmAgent = null;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("app_exit_metrics", crashType.getName());
            if (!TextUtils.isEmpty(c2)) {
                jSONObject2.put("scene", c2);
            }
            if (crashType == CrashType.ANR) {
                jSONObject2.put("has_anr_info", z);
            }
            jSONObject2.put("crash_time", j2);
            if (l != null) {
                l.a(f6040a, jSONObject2, null, null);
            } else {
                iApmAgent.monitorEvent(f6040a, jSONObject2, null, null);
            }
        } catch (Exception unused) {
        }
    }

    public static void a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        try {
            ICrashMonitorService l = Global.l();
            IApmAgent iApmAgent = null;
            if (l == null && (iApmAgent = (IApmAgent) e.getService(IApmAgent.class)) == null) {
                return;
            }
            if (l != null) {
                l.a(str, jSONObject, jSONObject2, jSONObject3);
            } else {
                iApmAgent.monitorEvent(str, jSONObject, jSONObject2, jSONObject3);
            }
        } catch (Exception unused) {
        }
    }
}
