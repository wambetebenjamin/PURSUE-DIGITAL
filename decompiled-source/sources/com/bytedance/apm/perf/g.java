package com.bytedance.apm.perf;

import android.text.TextUtils;
import com.bytedance.apm.core.ActivityLifeObserver;
import com.bytedance.apm.util.m;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: PerfFilterManager.java */
/* loaded from: classes.dex */
public class g {

    /* renamed from: c  reason: collision with root package name */
    private static volatile g f1281c;

    /* renamed from: a  reason: collision with root package name */
    private CopyOnWriteArraySet<String> f1282a = new CopyOnWriteArraySet<>();

    /* renamed from: b  reason: collision with root package name */
    private ConcurrentHashMap<String, String> f1283b = new ConcurrentHashMap<>();

    private g() {
    }

    public static g getInstance() {
        if (f1281c == null) {
            synchronized (g.class) {
                if (f1281c == null) {
                    f1281c = new g();
                }
            }
        }
        return f1281c;
    }

    public void startScene(String str) {
        this.f1282a.add(str);
    }

    public void stopScene(String str) {
        this.f1282a.remove(str);
    }

    public void addPerfTag(String str, String str2) {
        this.f1283b.put(str, str2);
    }

    public JSONObject getPerfFiltersJson() {
        return getPerfFiltersJson(false);
    }

    public JSONObject getPerfFiltersJson(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : this.f1283b.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
            if (z) {
                com.bytedance.apm.util.g.getInstance().addRealTimeDeviceInfo(jSONObject);
                com.bytedance.apm.util.g.getInstance().addConstantDeviceInfo(jSONObject);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public String getSceneString() {
        String arrayToString = m.arrayToString(this.f1282a.toArray(), "#");
        return TextUtils.isEmpty(arrayToString) ? ActivityLifeObserver.getInstance().getTopActivityClassName() : arrayToString;
    }

    public CopyOnWriteArraySet<String> getSceneSet() {
        return this.f1282a;
    }

    public ConcurrentHashMap<String, String> getPerfFilters() {
        return this.f1283b;
    }

    public void removePerfTag(String str, String str2) {
        this.f1283b.remove(str, str2);
    }
}
