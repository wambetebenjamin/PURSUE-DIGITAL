package com.bytedance.tracing.core;

import com.bytedance.tracing.internal.e;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: Tracer.java */
@Deprecated
/* loaded from: classes3.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private String f14046a;

    /* renamed from: b  reason: collision with root package name */
    private long f14047b;

    /* renamed from: c  reason: collision with root package name */
    private long f14048c;

    /* renamed from: d  reason: collision with root package name */
    private long f14049d;

    /* renamed from: e  reason: collision with root package name */
    private Map<String, String> f14050e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(b bVar) {
        this.f14046a = bVar.getService();
        this.f14047b = bVar.getTraceId();
    }

    public void start() {
        this.f14048c = System.currentTimeMillis();
    }

    public void addTag(String str, String str2) {
        if (this.f14050e == null) {
            this.f14050e = new ConcurrentHashMap();
        }
        this.f14050e.put(str, str2);
    }

    public void removeTag(String str) {
        Map<String, String> map = this.f14050e;
        if (map != null) {
            map.remove(str);
        }
    }

    public void finish() {
        this.f14049d = System.currentTimeMillis();
        com.bytedance.apm.thread.b.getInstance().post(new Runnable() { // from class: com.bytedance.tracing.core.d.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("service", d.this.f14046a);
                    jSONObject.put("trace_id", d.this.f14047b);
                    jSONObject.put(e.KEY_START_TIMESTAMP, d.this.f14048c);
                    jSONObject.put(e.KEY_END_TIMESTAMP, d.this.f14049d);
                    jSONObject.put(e.KEY_SAMPLE_RATE, com.bytedance.tracing.internal.d.getInstance().getSampleRate(d.this.f14046a));
                    jSONObject.put(e.KEY_FINISH_FLAG, 1);
                    jSONObject.put(e.KEY_REPORT_MODE, 0);
                    com.bytedance.apm.data.pipeline.a.getInstance().handle(new com.bytedance.apm.data.type.d(e.TRACE_LOG_TYPE, jSONObject));
                } catch (JSONException unused) {
                }
            }
        });
    }
}
