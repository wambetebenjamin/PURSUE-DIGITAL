package com.bytedance.apm6.perf.base.model;

import com.bytedance.apm6.monitor.c;
import com.bytedance.apm6.perf.base.e;
import com.bytedance.apm6.util.f;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: PerfBaseEvent.java */
/* loaded from: classes.dex */
public abstract class b implements c {

    /* renamed from: g  reason: collision with root package name */
    protected static final String f2356g = "process_name";

    /* renamed from: h  reason: collision with root package name */
    protected static final String f2357h = "is_main_process";

    /* renamed from: i  reason: collision with root package name */
    protected static final String f2358i = "is_front";

    /* renamed from: a  reason: collision with root package name */
    protected final String f2359a = "log_type";

    /* renamed from: b  reason: collision with root package name */
    protected final String f2360b = com.bytedance.apm.constant.c.KEY_EXTRA_STATUS;

    /* renamed from: c  reason: collision with root package name */
    protected final String f2361c = com.bytedance.apm.constant.c.KEY_EXTRA_VALUES;

    /* renamed from: d  reason: collision with root package name */
    protected final String f2362d = "filters";

    /* renamed from: e  reason: collision with root package name */
    protected final String f2363e = "service";

    /* renamed from: f  reason: collision with root package name */
    protected final String f2364f = "scene";

    /* renamed from: j  reason: collision with root package name */
    private JSONObject f2365j;

    protected abstract String a();

    protected abstract JSONObject b();

    protected abstract JSONObject getFilters();

    @Override // com.bytedance.apm6.monitor.c
    public String getLogType() {
        return "performance_monitor";
    }

    @Override // com.bytedance.apm6.monitor.c
    public JSONObject toJsonObject() {
        try {
            if (this.f2365j == null) {
                this.f2365j = d();
            }
            this.f2365j.put("log_type", "performance_monitor");
            this.f2365j.put("service", a());
            JSONObject b2 = b();
            if (!f.isEmpty(b2)) {
                this.f2365j.put(com.bytedance.apm.constant.c.KEY_EXTRA_VALUES, b2);
            }
            JSONObject c2 = c();
            if (!f.isEmpty(c2)) {
                this.f2365j.put(com.bytedance.apm.constant.c.KEY_EXTRA_STATUS, c2);
            }
            JSONObject filters = getFilters();
            if (!f.isEmpty(filters)) {
                this.f2365j.put("filters", filters);
            }
            return this.f2365j;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONObject d() {
        return new JSONObject();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONObject c() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("scene", e.getInstance().getSceneString());
            jSONObject.put("process_name", com.bytedance.apm6.foundation.context.a.getCurrentProcessName());
            jSONObject.put("is_main_process", com.bytedance.apm6.foundation.context.a.isMainProcess());
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }
}
