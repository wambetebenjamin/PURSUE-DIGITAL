package com.bytedance.apm6.memory.monitorable;

import com.bytedance.apm.constant.c;
import com.bytedance.apm6.perf.base.e;
import com.bytedance.apm6.perf.base.model.b;
import com.bytedance.apm6.service.d;
import com.bytedance.apm6.util.f;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: MemoryPerfDataEvent.java */
/* loaded from: classes.dex */
public final class a extends b {
    private static final String A = "gc_time";
    private static final String B = "block_gc_count";
    private static final String C = "block_gc_time";

    /* renamed from: j  reason: collision with root package name */
    private static final String f2335j = "memory";
    private static final String k = "dalvik_pss_background";
    private static final String l = "native_pss_background";
    private static final String m = "total_pss_background";
    private static final String n = "graphics_background";
    private static final String o = "vm_size_background";
    private static final String p = "dalvik_pss_foreground";
    private static final String q = "native_pss_foreground";
    private static final String r = "total_pss_foreground";
    private static final String s = "graphics_foreground";
    private static final String t = "vm_size_foreground";
    private static final String u = "java_heap_background_used_rate";
    private static final String v = "java_heap_foreground_used_rate";
    private static final String w = "java_heap_background";
    private static final String x = "java_heap_foreground";
    private static final String y = "reach_top_java";
    private static final String z = "gc_count";
    private long D;
    private long E;
    private long F;
    private long G;
    private boolean H;
    private long I;

    /* renamed from: J  reason: collision with root package name */
    private long f2336J;
    private long K;
    private long L;
    private long M;
    private long N;
    private double O;
    private boolean P;
    private boolean Q;
    private Map<Object, Object> R;

    @Override // com.bytedance.apm6.perf.base.model.b
    protected String a() {
        return "memory";
    }

    @Override // com.bytedance.apm6.monitor.c
    public boolean isValid() {
        return true;
    }

    public a(long j2, long j3, long j4, long j5, boolean z2, long j6, long j7, long j8, long j9, long j10, long j11, double d2, boolean z3, boolean z4) {
        this.D = j2;
        this.E = j3;
        this.F = j4;
        this.G = j5;
        this.H = z2;
        this.I = j6;
        this.f2336J = j7;
        this.K = j8;
        this.L = j9;
        this.M = j10;
        this.N = j11;
        this.O = d2;
        this.P = z3;
        this.Q = z4;
    }

    public void setMmapInfo(Map<Object, Object> map) {
        this.R = map;
    }

    @Override // com.bytedance.apm6.perf.base.model.b
    protected JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("gc_count", this.D);
            jSONObject.put("gc_time", this.E);
            jSONObject.put("block_gc_count", this.F);
            jSONObject.put("block_gc_time", this.G);
            if (this.H) {
                jSONObject.put("dalvik_pss_background", this.L);
                jSONObject.put("native_pss_background", this.I);
                jSONObject.put("total_pss_background", this.f2336J);
                jSONObject.put("java_heap_background", this.K);
                jSONObject.put("java_heap_background_used_rate", this.O);
                jSONObject.put("vm_size_background", this.N);
                jSONObject.put("graphics_background", this.M);
            } else {
                jSONObject.put("dalvik_pss_foreground", this.L);
                jSONObject.put("native_pss_foreground", this.I);
                jSONObject.put("total_pss_foreground", this.f2336J);
                jSONObject.put("java_heap_foreground", this.K);
                jSONObject.put("java_heap_foreground_used_rate", this.O);
                jSONObject.put("vm_size_foreground", this.N);
                jSONObject.put("graphics_foreground", this.M);
            }
            if (this.P) {
                jSONObject.put("reach_top_java", 1);
            }
            a(jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            Map<Object, Object> map = this.R;
            if (map == null) {
                return;
            }
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                jSONObject.put((String) entry.getKey(), Long.parseLong((String) entry.getValue()));
            }
        } catch (Exception unused) {
        }
    }

    public double getJavaUsedMemoryRate() {
        return this.O;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.apm6.perf.base.model.b
    public JSONObject c() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("scene", e.getInstance().getSceneString());
            jSONObject.put("process_name", com.bytedance.apm6.foundation.context.a.getCurrentProcessName());
            jSONObject.put(c.KEY_IS_FRONT, !this.H);
            jSONObject.put("is_main_process", com.bytedance.apm6.foundation.context.a.isMainProcess());
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }

    public boolean isBackground() {
        return this.H;
    }

    @Override // com.bytedance.apm6.perf.base.model.b
    public JSONObject getFilters() {
        JSONObject perfFiltersJson = e.getInstance().getPerfFiltersJson();
        if (this.Q) {
            try {
                f.copyJson2(perfFiltersJson, e.getInstance().getRealTimeMemInfo());
            } catch (Exception unused) {
            }
        }
        com.bytedance.apm6.service.device.a aVar = (com.bytedance.apm6.service.device.a) d.getService(com.bytedance.apm6.service.device.a.class);
        if (aVar != null) {
            try {
                f.copyJson2(perfFiltersJson, aVar.getDeviceInfo());
            } catch (Exception unused2) {
            }
        }
        return perfFiltersJson;
    }

    public com.bytedance.apm6.memory.bean.a getMemoryCollectInfo() {
        com.bytedance.apm6.memory.bean.a aVar = new com.bytedance.apm6.memory.bean.a();
        aVar.gcCount = this.D;
        aVar.gcTime = this.E;
        aVar.blockingGcCount = this.F;
        aVar.blockingGcTime = this.G;
        aVar.background = this.H;
        aVar.nativePss = this.I;
        aVar.totalPss = this.f2336J;
        aVar.javaUsedMemory = this.K;
        aVar.dalvikUsedSize = this.L;
        aVar.graphics = this.M;
        aVar.vmSize = this.N;
        aVar.isMemoryReachTop = this.P;
        return aVar;
    }

    public String toString() {
        return "MemoryPerfMonitorable{gcCount=" + this.D + ", gcTime=" + this.E + ", blockingGcCount=" + this.F + ", blockingGcTime=" + this.G + ", background=" + this.H + ", nativePss=" + this.I + ", totalPss=" + this.f2336J + ", javaUsedMemory=" + this.K + ", dalvikUsedSize=" + this.L + ", graphics=" + this.M + ", vmSize=" + this.N + ", javaUsedMemoryRate=" + this.O + ", isMemoryReachTop=" + this.P + '}';
    }
}
