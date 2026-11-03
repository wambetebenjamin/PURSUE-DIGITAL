package com.bytedance.apm6.disk;

import com.bytedance.apm6.util.f;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: DiskReportEvent.java */
/* loaded from: classes.dex */
public class d extends com.bytedance.apm6.perf.base.model.b {

    /* renamed from: j  reason: collision with root package name */
    private static final String f2143j = "disk";
    private static final String k = "data";
    private static final String l = "cache";
    private static final String m = "total";
    private static final String n = "rom_free";
    private static final String o = "app_usage";
    private static final String p = "total_capacity";
    private static final String q = "free_capacity";
    private static final String r = "app_occupied_rate";
    private static final String s = "top_usage";
    private static final String t = "exception_folders";
    private static final String u = "outdated_files";
    private static final String v = "disk_info";
    private long A;
    private long B;
    private long C;
    private double D;
    private JSONArray E;
    private JSONArray F;
    private JSONArray G;
    private JSONArray H;
    private long w;
    private long x;
    private long y;
    private long z;

    @Override // com.bytedance.apm6.perf.base.model.b
    protected String a() {
        return "disk";
    }

    @Override // com.bytedance.apm6.monitor.c
    public boolean isValid() {
        return true;
    }

    public d(long j2, long j3, long j4, long j5, long j6, long j7, long j8, double d2, JSONArray jSONArray, JSONArray jSONArray2, JSONArray jSONArray3, JSONArray jSONArray4) {
        this.w = j2;
        this.x = j3;
        this.y = j4;
        this.z = j5;
        this.A = j6;
        this.B = j7;
        this.C = j8;
        this.D = d2;
        this.E = jSONArray;
        this.F = jSONArray2;
        this.G = jSONArray3;
        this.H = jSONArray4;
    }

    @Override // com.bytedance.apm6.perf.base.model.b
    protected JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            long j2 = this.w;
            if (j2 > 0) {
                jSONObject.put("data", j2);
            }
            long j3 = this.x;
            if (j3 > 0) {
                jSONObject.put("cache", j3);
            }
            long j4 = this.y;
            if (j4 > 0) {
                jSONObject.put("total", j4);
            }
            long j5 = this.z;
            if (j5 > 0) {
                jSONObject.put("rom_free", j5);
            }
            long j6 = this.A;
            if (j6 > 0) {
                jSONObject.put(o, j6);
            }
            long j7 = this.B;
            if (j7 > 0) {
                jSONObject.put(p, j7);
            }
            long j8 = this.C;
            if (j8 > 0) {
                jSONObject.put(q, j8);
            }
            double d2 = this.D;
            if (d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                jSONObject.put(r, d2);
            }
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.apm6.perf.base.model.b
    public JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = this.H;
            if (jSONArray != null) {
                jSONObject.put("disk_info", jSONArray);
            }
            JSONArray jSONArray2 = this.E;
            if (jSONArray2 != null) {
                jSONObject.put("top_usage", jSONArray2);
            }
            JSONArray jSONArray3 = this.F;
            if (jSONArray3 != null) {
                jSONObject.put("exception_folders", jSONArray3);
            }
            JSONArray jSONArray4 = this.G;
            if (jSONArray4 != null) {
                jSONObject.put("outdated_files", jSONArray4);
            }
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.apm6.perf.base.model.b
    public JSONObject c() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("scene", com.bytedance.apm6.perf.base.e.getInstance().getSceneString());
            jSONObject.put("process_name", com.bytedance.apm6.foundation.context.a.getCurrentProcessName());
            jSONObject.put(com.bytedance.apm.constant.c.KEY_IS_FRONT, false);
            jSONObject.put("is_main_process", com.bytedance.apm6.foundation.context.a.isMainProcess());
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.bytedance.apm6.perf.base.model.b
    protected JSONObject getFilters() {
        JSONObject perfFiltersJson = com.bytedance.apm6.perf.base.e.getInstance().getPerfFiltersJson();
        try {
            f.copyJson2(perfFiltersJson, com.bytedance.apm6.perf.base.e.getInstance().getRealTimeMemInfo());
        } catch (Exception unused) {
        }
        return perfFiltersJson;
    }
}
