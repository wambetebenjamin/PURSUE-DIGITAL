package com.bytedance.apm.perf;

import com.bytedance.apm.constant.p;
import com.bytedance.apm.util.l;
import com.bytedance.services.slardar.config.IConfigManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
/* compiled from: PerfConfigManager.java */
/* loaded from: classes.dex */
public class e implements com.bytedance.services.slardar.config.a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1271a = "PerfConfigManager";

    /* renamed from: b  reason: collision with root package name */
    private final Map<String, Boolean> f1272b;

    /* renamed from: c  reason: collision with root package name */
    private final Map<String, Boolean> f1273c;

    /* renamed from: d  reason: collision with root package name */
    private List<String> f1274d;

    /* renamed from: e  reason: collision with root package name */
    private List<String> f1275e;

    /* renamed from: f  reason: collision with root package name */
    private List<String> f1276f;

    /* renamed from: g  reason: collision with root package name */
    private Map<String, String> f1277g;

    /* renamed from: h  reason: collision with root package name */
    private volatile JSONObject f1278h;

    @Override // com.bytedance.services.slardar.config.a
    public void onReady() {
    }

    private e() {
        this.f1272b = new ConcurrentHashMap();
        this.f1273c = new ConcurrentHashMap();
        this.f1277g = new HashMap();
        LinkedList linkedList = new LinkedList();
        this.f1274d = linkedList;
        linkedList.addAll(Arrays.asList("battery", p.PERF_KEY_SMOOTH, "cpu", "disk", "memory", "thread", p.PERF_KEY_FD, "page_load", "page_load_trace", "start", "start_trace", "traffic", p.PERF_KEY_UI));
        ArrayList arrayList = new ArrayList();
        this.f1275e = arrayList;
        arrayList.add("enable_upload");
        this.f1275e.add(p.PERF_SMOOTH_DROP_ENABLE_UPLOAD);
        this.f1275e.add(p.PERF_SMOOTH_SERIOUS_BLOCK_ENABLE_UPLOAD);
        this.f1275e.add(p.PERF_SMOOTH_BLOCK_ENABLE_UPLOAD);
        this.f1275e.add(p.PERF_SMOOTH_SLOW_METHOD_ENABLE_UPLOAD);
        LinkedList linkedList2 = new LinkedList();
        this.f1276f = linkedList2;
        linkedList2.add(p.PERF_KEY_PERF_DATA_COLLECT);
        this.f1277g.put("enable_upload", "fps");
        this.f1277g.put(p.PERF_SMOOTH_DROP_ENABLE_UPLOAD, "fps_drop");
        this.f1277g.put(p.PERF_SMOOTH_BLOCK_ENABLE_UPLOAD, "block_monitor");
        this.f1277g.put(p.PERF_SMOOTH_SLOW_METHOD_ENABLE_UPLOAD, "drop_frame_stack");
        this.f1277g.put(p.PERF_SMOOTH_SERIOUS_BLOCK_ENABLE_UPLOAD, "serious_block_monitor");
        ((IConfigManager) com.bytedance.news.common.service.manager.e.getService(IConfigManager.class)).registerConfigListener(this);
    }

    public static e getInstance() {
        return a.f1279a;
    }

    /* compiled from: PerfConfigManager.java */
    /* loaded from: classes.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final e f1279a = new e();

        private a() {
        }
    }

    @Override // com.bytedance.services.slardar.config.a
    public void onRefresh(JSONObject jSONObject, boolean z) {
        if (jSONObject == null) {
            return;
        }
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("performance_modules");
        if (optJSONObject == null) {
            return;
        }
        for (String str : this.f1274d) {
            JSONObject optJSONObject2 = optJSONObject.optJSONObject(str);
            if (p.PERF_KEY_SMOOTH.equals(str)) {
                c(optJSONObject2);
            } else {
                if ("memory".equals(str)) {
                    d(optJSONObject2);
                }
                if ("battery".equals(str)) {
                    e(optJSONObject2);
                }
                if ("cpu".equals(str)) {
                    f(optJSONObject2);
                }
                if ("start_trace".equals(str)) {
                    b(optJSONObject2);
                }
                if (optJSONObject2 != null && optJSONObject2.optInt("enable_upload", 0) == 1) {
                    this.f1272b.put(str, true);
                } else {
                    this.f1272b.put(str, false);
                }
            }
        }
        this.f1278h = l.optJSONObject(optJSONObject, p.PERF_KEY_SMOOTH, p.PERF_SMOOTH_SCENE_ENABLE_UPLOAD);
    }

    private void b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        for (String str : this.f1276f) {
            try {
                this.f1273c.put(str, Boolean.valueOf(jSONObject.optInt(str, 0) == 1));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void c(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        for (String str : this.f1275e) {
            try {
                this.f1273c.put(this.f1277g.get(str), Boolean.valueOf(jSONObject.optInt(str, 0) == 1));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void d(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.f1273c.put("memory_object_monitor", Boolean.valueOf(jSONObject.optInt("memory_object_monitor", 0) == 1));
    }

    private void e(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.f1273c.put(com.bytedance.apm.constant.d.MONITOR_TYPE_TEMPERATURE, Boolean.valueOf(jSONObject.optInt(p.PERF_KEY_BATTERY_TEMPERATURE_ENABLE_UPLOAD, 0) == 1));
        this.f1273c.put("battery_trace", Boolean.valueOf(jSONObject.optInt(p.PERF_BATTERY_EXCEPTION, 0) == 1));
    }

    private void f(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.f1273c.put("cpu_trace", Boolean.valueOf(jSONObject.optInt("exception", 0) == 1));
    }

    public boolean getPerfAllowSwitch(String str) {
        Boolean bool = this.f1272b.get(str);
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public boolean getPerfFpsAllowSwitch(String str, String str2) {
        Boolean bool = this.f1273c.get(str);
        return (bool != null && bool.booleanValue()) || (this.f1278h != null && this.f1278h.optInt(str2) == 1);
    }

    public boolean getPerfSecondStageSwitch(String str) {
        Boolean bool = this.f1273c.get(str);
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
