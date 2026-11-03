package com.bytedance.apm.trace.model;

import android.os.Process;
import com.bytedance.tracing.internal.e;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;
/* compiled from: AbsTracing.java */
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    protected long f1602a;

    /* renamed from: c  reason: collision with root package name */
    protected com.bytedance.apm.trace.api.d f1604c;

    /* renamed from: b  reason: collision with root package name */
    protected Map<String, String> f1603b = new ConcurrentHashMap();

    /* renamed from: d  reason: collision with root package name */
    protected List<String> f1605d = new CopyOnWriteArrayList();

    public abstract void cancelTrace();

    public abstract void endTrace(long j2);

    public abstract void finishSpan(long j2, JSONObject jSONObject, boolean z);

    public a(com.bytedance.apm.trace.api.d dVar) {
        this.f1604c = dVar;
    }

    public void addTag(String str, String str2) {
        this.f1603b.put(str, str2);
    }

    public void startTrace(long j2) {
        this.f1602a = j2;
    }

    public com.bytedance.apm.trace.api.a createSpan(String str) {
        return new d(str, e.VALUE_TRACING, this);
    }

    public com.bytedance.apm.trace.api.c createWindowSpan(String str) {
        return new d(str, e.VALUE_TRACING_WINDOW, this);
    }

    public com.bytedance.apm.trace.api.a createSpan(String str, long j2) {
        return new d(str, e.VALUE_TRACING, this, j2);
    }

    public com.bytedance.apm.trace.api.c createWindowSpan(String str, long j2) {
        return new d(str, e.VALUE_TRACING_WINDOW, this, j2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(JSONObject jSONObject, boolean z) {
        jSONObject.put("service", this.f1604c.getService());
        jSONObject.put("trace_id", this.f1604c.getTraceId() + "");
        if (this.f1604c.isForceTrace()) {
            jSONObject.put("hit_rules", 1);
            jSONObject.put(e.KEY_SAMPLE_RATE, 1);
            return;
        }
        jSONObject.put("hit_rules", com.bytedance.tracing.internal.d.getInstance().isErrorOrSampleHit(z, this.f1604c.getService()));
        jSONObject.put(e.KEY_SAMPLE_RATE, com.bytedance.tracing.internal.d.getInstance().getSampleRate(this.f1604c.getService()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str) {
        if (this.f1605d.contains(str)) {
            return;
        }
        this.f1605d.add(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a() {
        if (com.bytedance.apm.c.isDebugMode()) {
            for (String str : this.f1605d) {
                if (this.f1603b.containsKey(str)) {
                    com.bytedance.apm.launch.b.getInstance().logE("span's tag key can't be same as trace's tag key: " + str);
                    Process.killProcess(Process.myPid());
                }
            }
        }
    }
}
