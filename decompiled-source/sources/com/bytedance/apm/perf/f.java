package com.bytedance.apm.perf;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: PerfDataCenter.java */
/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static volatile f f1280a;

    private f() {
    }

    public static f getInstance() {
        if (f1280a == null) {
            synchronized (f.class) {
                if (f1280a == null) {
                    f1280a = new f();
                }
            }
        }
        return f1280a;
    }

    public JSONObject getCpuInfo() {
        JSONObject jSONObject = new JSONObject();
        try {
            com.bytedance.apm6.service.perf.a aVar = (com.bytedance.apm6.service.perf.a) com.bytedance.apm6.service.d.getService(com.bytedance.apm6.service.perf.a.class);
            jSONObject.put(com.bytedance.apm.constant.i.PERF_CPU_PROCESS_USAGE, aVar.getCpuRate());
            jSONObject.put(com.bytedance.apm.constant.i.PERF_CPU_STAT_SPEED, aVar.getCpuSpeed());
            return jSONObject;
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }
}
