package com.bytedance.crash.runtime.assembly;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: ActivityDataManager.java */
@Deprecated
/* loaded from: classes2.dex */
public class a {
    private static volatile a z;

    public void setCustomActivity(com.bytedance.crash.outer.a aVar) {
        com.bytedance.crash.tracker.a.a(aVar);
    }

    private a() {
    }

    public static a getInstance() {
        if (z == null) {
            synchronized (a.class) {
                if (z == null) {
                    z = new a();
                }
            }
        }
        return z;
    }

    public static int getLaunchMode() {
        return com.bytedance.crash.tracker.a.d();
    }

    public static long getLaunchTime() {
        return com.bytedance.crash.tracker.a.e();
    }

    public long backgroundTime() {
        return com.bytedance.crash.tracker.a.f();
    }

    public boolean isForeground() {
        return !com.bytedance.crash.tracker.a.h();
    }

    public ArrayList<WeakReference<Activity>> getAllActivities() {
        return com.bytedance.crash.tracker.a.a();
    }

    public JSONArray getActivityLife() {
        return com.bytedance.crash.tracker.a.i();
    }

    public JSONObject getActivityTrace() {
        JSONObject jSONObject = new JSONObject();
        com.bytedance.crash.tracker.a.a(jSONObject);
        return jSONObject.has(com.bytedance.crash.tracker.a.f7151a) ? jSONObject.optJSONObject(com.bytedance.crash.tracker.a.f7151a) : jSONObject;
    }

    public String getLastResumeActivity() {
        return com.bytedance.crash.tracker.a.j();
    }
}
