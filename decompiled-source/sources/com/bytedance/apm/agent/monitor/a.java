package com.bytedance.apm.agent.monitor;

import com.bytedance.apm.data.pipeline.d;
import com.bytedance.apm.data.type.f;
import com.bytedance.apm.data.type.g;
import com.bytedance.apm.thread.b;
import org.json.JSONObject;
/* compiled from: MonitorTool.java */
/* loaded from: classes.dex */
public class a {
    public static void monitorUIAction(final String str, final String str2, final JSONObject jSONObject) {
        b.getInstance().post(new Runnable() { // from class: com.bytedance.apm.agent.monitor.a.1
            @Override // java.lang.Runnable
            public void run() {
                com.bytedance.apm.data.pipeline.a.getInstance().handle(new g(str, str2, jSONObject, null));
            }
        });
    }

    public static void monitorPerformance(final String str, final JSONObject jSONObject, final JSONObject jSONObject2, final JSONObject jSONObject3) {
        b.getInstance().post(new Runnable() { // from class: com.bytedance.apm.agent.monitor.a.2
            @Override // java.lang.Runnable
            public void run() {
                com.bytedance.apm.data.pipeline.a.getInstance().handle(new f(str, "", jSONObject, jSONObject2, jSONObject3));
            }
        });
    }

    public static void monitorStart(final JSONObject jSONObject, long j2, long j3) {
        try {
            final JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(com.bytedance.apm.constant.a.BEGIN_TIME, j2);
            jSONObject2.put("end_time", j3);
            jSONObject2.put("from", "monitor-plugin");
            b.getInstance().post(new Runnable() { // from class: com.bytedance.apm.agent.monitor.a.3
                @Override // java.lang.Runnable
                public void run() {
                    com.bytedance.apm.data.pipeline.a.getInstance().handle(new f("start", "", jSONObject, null, jSONObject2));
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void monitorStart(String str, long j2, long j3) {
        if (j3 > j2) {
            try {
                final JSONObject jSONObject = new JSONObject();
                jSONObject.put(str, j3 - j2);
                final JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(com.bytedance.apm.constant.a.BEGIN_TIME, j2);
                jSONObject2.put("end_time", j3);
                b.getInstance().post(new Runnable() { // from class: com.bytedance.apm.agent.monitor.a.4
                    @Override // java.lang.Runnable
                    public void run() {
                        com.bytedance.apm.data.pipeline.a.getInstance().handle(new f("start", "", jSONObject, null, jSONObject2));
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void reportTraceTime(final String str, final String str2, final long j2) {
        b.getInstance().post(new Runnable() { // from class: com.bytedance.apm.agent.monitor.a.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(str2, j2);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("scene", str);
                    com.bytedance.apm.data.pipeline.a.getInstance().handle(new f("page_load", "page_load", jSONObject, jSONObject2, null));
                } catch (Exception unused) {
                }
            }
        });
    }

    public static void monitorSLA(final long j2, final long j3, final String str, final String str2, final String str3, final int i2, final JSONObject jSONObject) {
        b.getInstance().post(new Runnable() { // from class: com.bytedance.apm.agent.monitor.a.6
            @Override // java.lang.Runnable
            public void run() {
                d.getInstance().handle(new com.bytedance.apm.data.type.a("api_all", j2, j3, str, str2, str3, i2, jSONObject));
            }
        });
    }
}
