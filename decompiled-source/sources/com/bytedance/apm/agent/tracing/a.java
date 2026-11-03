package com.bytedance.apm.agent.tracing;

import com.bytedance.apm.constant.r;
import com.bytedance.apm.data.type.f;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: AutoLaunchTraceHelper.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static long f332a = 0;

    /* renamed from: b  reason: collision with root package name */
    private static long f333b = 0;

    /* renamed from: c  reason: collision with root package name */
    private static long f334c = 0;

    /* renamed from: d  reason: collision with root package name */
    private static long f335d = 0;

    /* renamed from: e  reason: collision with root package name */
    private static long f336e = 0;

    /* renamed from: f  reason: collision with root package name */
    private static long f337f = 0;

    /* renamed from: g  reason: collision with root package name */
    private static long f338g = 0;

    /* renamed from: h  reason: collision with root package name */
    private static long f339h = 0;

    /* renamed from: i  reason: collision with root package name */
    private static long f340i = 0;

    /* renamed from: j  reason: collision with root package name */
    private static long f341j = 0;
    private static long k = 0;
    private static long l = 0;
    private static long m = 0;
    private static boolean n = false;
    private static long o = 15000;
    public static String sLauncherActivityName;

    public static void assignAppTime(long j2, long j3, long j4, long j5, long j6, long j7) {
        com.bytedance.apm.launch.b.getInstance().logD("assignAppTime: " + f337f);
        f332a = j2;
        f333b = j3;
        f334c = j4;
        f335d = j5;
        f336e = j6;
        f337f = j7;
        com.bytedance.apm.c.setAppLaunchStartTimestamp(j2);
    }

    public static void launcherActivityOnCreateStart(String str) {
        com.bytedance.apm.launch.b.getInstance().logD(str + " onCreateStart " + f338g + " " + (f338g - f337f < 800));
        if (f338g == 0) {
            f338g = System.currentTimeMillis();
            com.bytedance.apm.launch.b.getInstance().logD(str + " onCreateStart " + f338g + "  " + f337f + " " + (f338g - f337f < 800));
            if (f338g - f337f < 800) {
                n = true;
                sLauncherActivityName = str;
            }
        }
    }

    public static void launcherActivityOnCreateEnd() {
        com.bytedance.apm.launch.b.getInstance().logD("onCreateEnd " + f338g + " " + f339h);
        if (f339h == 0 && n) {
            f339h = System.currentTimeMillis();
        }
    }

    public static void launcherActivityOnStartStart(String str) {
        com.bytedance.apm.launch.b.getInstance().logD(str + " onStart " + f340i);
        if (f340i == 0 && n) {
            f340i = System.currentTimeMillis();
        }
    }

    public static void launcherActivityOnStartEnd(String str) {
        com.bytedance.apm.launch.b.getInstance().logD("onStartEnd " + f340i + " " + f341j);
        if (f341j == 0 && n) {
            f341j = System.currentTimeMillis();
        }
    }

    public static void launcherActivityOnResumeStart(String str) {
        com.bytedance.apm.launch.b.getInstance().logD(str + " onResume " + k);
        if (k == 0 && n) {
            k = System.currentTimeMillis();
        }
    }

    public static void launcherActivityOnResumeEnd() {
        com.bytedance.apm.launch.b.getInstance().logD("onResumeEnd " + k + " " + l);
        if (l == 0 && n) {
            l = System.currentTimeMillis();
        }
    }

    public static void launcherActivityOnWindowFocusChangedStart(String str) {
        com.bytedance.apm.launch.b.getInstance().logD("OnWindowFocusChanged " + str + " " + m + " " + f336e + " " + n);
        if (m == 0 && f336e > 0 && n) {
            m = System.currentTimeMillis();
            sLauncherActivityName = str;
            n = false;
        }
        com.bytedance.apm.thread.b.getInstance().post(new Runnable() { // from class: com.bytedance.apm.agent.tracing.a.1
            @Override // java.lang.Runnable
            public void run() {
                a.reportStats();
            }
        });
    }

    public static JSONArray assemblySpan() {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(r.STATS_KEY_MODULE_NAME, r.STATS_BASE_MODULE);
        jSONObject.put(r.STATS_KEY_SPAN_NAME, "app_constructor");
        jSONObject.put("start", f332a);
        jSONObject.put("end", f333b);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(r.STATS_KEY_MODULE_NAME, r.STATS_BASE_MODULE);
        jSONObject2.put(r.STATS_KEY_SPAN_NAME, "app_attachBaseContext");
        jSONObject2.put("start", f334c);
        jSONObject2.put("end", f335d);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(r.STATS_KEY_MODULE_NAME, r.STATS_BASE_MODULE);
        jSONObject3.put(r.STATS_KEY_SPAN_NAME, "app_onCreate");
        jSONObject3.put("start", f336e);
        jSONObject3.put("end", f337f);
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put(r.STATS_KEY_MODULE_NAME, r.STATS_BASE_MODULE);
        jSONObject4.put(r.STATS_KEY_SPAN_NAME, "activity_onCreate");
        jSONObject4.put("start", f338g);
        jSONObject4.put("end", f339h);
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put(r.STATS_KEY_MODULE_NAME, r.STATS_BASE_MODULE);
        jSONObject5.put(r.STATS_KEY_SPAN_NAME, "activity_onResume");
        jSONObject5.put("start", k);
        jSONObject5.put("end", l);
        JSONObject jSONObject6 = new JSONObject();
        jSONObject6.put(r.STATS_KEY_MODULE_NAME, r.STATS_BASE_MODULE);
        jSONObject6.put(r.STATS_KEY_SPAN_NAME, "activity_onStart");
        jSONObject6.put("start", f340i);
        jSONObject6.put("end", f341j);
        if (m > 0) {
            JSONObject jSONObject7 = new JSONObject();
            jSONObject7.put(r.STATS_KEY_MODULE_NAME, r.STATS_BASE_MODULE);
            jSONObject7.put(r.STATS_KEY_SPAN_NAME, "activity_onWindowFocusChanged");
            jSONObject7.put("start", m);
            jSONArray.put(jSONObject7);
        }
        jSONArray.put(jSONObject);
        jSONArray.put(jSONObject2);
        jSONArray.put(jSONObject3);
        jSONArray.put(jSONObject4);
        jSONArray.put(jSONObject5);
        jSONArray.put(jSONObject6);
        return jSONArray;
    }

    public static void setMaxValidTimeMs(long j2) {
        o = j2;
    }

    public static void reportStats() {
        try {
            if (com.bytedance.apm.c.getLaunchMode() == -1) {
                com.bytedance.apm.launch.b.getInstance().logD("auto launch mode not init");
                return;
            }
            long j2 = m - f332a;
            if (j2 > 0 && j2 <= o) {
                JSONArray assemblySpan = assemblySpan();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("name", r.START_STATS_NAME);
                jSONObject.put("start", f332a);
                jSONObject.put("end", m);
                jSONObject.put(r.STATS_KEY_SPANS, assemblySpan);
                jSONObject.put(r.START_STATS_KEY_COLLECT_FROM, 1);
                jSONObject.put("page_name", sLauncherActivityName);
                jSONObject.put("launch_mode", com.bytedance.apm.c.getLaunchMode());
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(r.STATS_KEY_TRACE, jSONObject);
                if (com.bytedance.apm.c.isDebugMode()) {
                    com.bytedance.apm.launch.b.getInstance().logD("auto span: " + jSONObject);
                }
                com.bytedance.apm.data.pipeline.a.getInstance().handle(new f("start_trace", "", null, null, jSONObject2));
            }
        } catch (JSONException unused) {
        }
    }
}
