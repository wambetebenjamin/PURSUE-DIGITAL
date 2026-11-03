package com.bytedance.apm6.perf.base;

import android.os.Debug;
import android.text.TextUtils;
import com.bytedance.apm.util.j;
import com.bytedance.apm6.util.g;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: PerfFilterManager.java */
/* loaded from: classes.dex */
public class e implements com.bytedance.apm6.service.perf.b {

    /* renamed from: g  reason: collision with root package name */
    private static volatile e f2349g;

    /* renamed from: a  reason: collision with root package name */
    private CopyOnWriteArraySet<String> f2350a = new CopyOnWriteArraySet<>();

    /* renamed from: b  reason: collision with root package name */
    private ConcurrentHashMap<String, String> f2351b = new ConcurrentHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private long f2352c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f2353d;

    /* renamed from: e  reason: collision with root package name */
    private long f2354e;

    /* renamed from: f  reason: collision with root package name */
    private long f2355f;

    private e() {
    }

    public static e getInstance() {
        if (f2349g == null) {
            synchronized (e.class) {
                if (f2349g == null) {
                    f2349g = new e();
                }
            }
        }
        return f2349g;
    }

    @Override // com.bytedance.apm6.service.perf.b
    public void startScene(String str) {
        this.f2350a.add(str);
    }

    @Override // com.bytedance.apm6.service.perf.b
    public void stopScene(String str) {
        this.f2350a.remove(str);
    }

    @Override // com.bytedance.apm6.service.perf.b
    public void addPerfTag(String str, String str2) {
        this.f2351b.put(str, str2);
    }

    @Override // com.bytedance.apm6.service.perf.b
    public String getSceneString() {
        String arrayToString = g.arrayToString(this.f2350a.toArray(), "#");
        if (TextUtils.isEmpty(arrayToString)) {
            com.bytedance.apm6.service.lifecycle.a aVar = (com.bytedance.apm6.service.lifecycle.a) com.bytedance.apm6.service.d.getService(com.bytedance.apm6.service.lifecycle.a.class);
            return aVar != null ? aVar.getTopActivityClassName() : "";
        }
        return arrayToString;
    }

    @Override // com.bytedance.apm6.service.perf.b
    public JSONObject getPerfFiltersJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : this.f2351b.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public JSONObject getPerfFiltersJson(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : this.f2351b.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
            jSONObject.put("RealRefreshRate", String.valueOf(j.c()));
            jSONObject.put("RealMaxRefreshRate", String.valueOf(j.c()));
            if (z) {
                com.bytedance.apm.util.g.getInstance().addRealTimeDeviceInfo(jSONObject);
                com.bytedance.apm.util.g.getInstance().addConstantDeviceInfo(jSONObject);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    @Override // com.bytedance.apm6.service.perf.b
    public CopyOnWriteArraySet<String> getSceneSet() {
        return this.f2350a;
    }

    @Override // com.bytedance.apm6.service.perf.b
    public ConcurrentHashMap<String, String> getPerfFilters() {
        return this.f2351b;
    }

    @Override // com.bytedance.apm6.service.perf.b
    public void removePerfTag(String str, String str2) {
        this.f2351b.remove(str, str2);
    }

    @Override // com.bytedance.apm6.service.perf.b
    public JSONObject getRealTimeMemInfo() {
        try {
            JSONObject jSONObject = new JSONObject();
            a();
            jSONObject.put("apm_native_heap_size", this.f2352c);
            jSONObject.put("apm_java_heap_leak", this.f2353d);
            jSONObject.put("apm_java_heap_used", this.f2355f);
            jSONObject.put("apm_java_heap_max", this.f2354e);
            return jSONObject;
        } catch (Exception unused) {
            return null;
        }
    }

    private void a() {
        this.f2352c = Debug.getNativeHeapAllocatedSize() / 1048576;
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long freeMemory = runtime.freeMemory();
        long j2 = runtime.totalMemory();
        this.f2354e = maxMemory / 1048576;
        long j3 = j2 - freeMemory;
        this.f2355f = j3 / 1048576;
        this.f2353d = ((float) j3) > ((float) maxMemory) * 0.95f;
    }
}
