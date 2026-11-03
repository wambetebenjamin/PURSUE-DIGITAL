package com.bytedance.apm.event;

import com.bytedance.apm.util.j;
import com.bytedance.apm6.perf.base.e;
import org.json.JSONObject;
/* compiled from: FpsEvent.java */
/* loaded from: classes.dex */
public class b extends com.bytedance.apm6.perf.base.model.b {

    /* renamed from: j  reason: collision with root package name */
    private static final String f1057j = "fps";
    private static final String k = "fps";
    private static final String l = "refresh_rate";
    private float m;
    private String n;
    private int o;
    private boolean p;

    @Override // com.bytedance.apm6.perf.base.model.b
    protected String a() {
        return "fps";
    }

    @Override // com.bytedance.apm6.monitor.c
    public boolean isValid() {
        return false;
    }

    public b(float f2, String str, int i2, boolean z) {
        this.m = f2;
        this.n = str;
        this.o = i2;
        this.p = z;
    }

    @Override // com.bytedance.apm6.perf.base.model.b
    protected JSONObject b() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("fps", this.m);
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.apm6.perf.base.model.b
    public JSONObject c() {
        try {
            new JSONObject().put("scene", this.n);
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.bytedance.apm6.perf.base.model.b
    protected JSONObject getFilters() {
        try {
            JSONObject perfFiltersJson = e.getInstance().getPerfFiltersJson();
            perfFiltersJson.put("refresh_rate", this.o);
            if (this.p) {
                this.p = false;
                perfFiltersJson.put("device_max_refresh_rate", j.getDeviceMaxRefreshRate());
                perfFiltersJson.put("refresh_rate_restricted", j.isUsingMaxRefreshRate() ? false : true);
            }
            return perfFiltersJson;
        } catch (Throwable unused) {
            return null;
        }
    }
}
