package com.bytedance.crash.entity;

import com.bytedance.crash.event.e;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: EventBody.java */
@Deprecated
/* loaded from: classes2.dex */
public class d extends b {
    public static final String BLOCK_INTERVAL = "caton_interval";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_METRIC = "metric";
    public static final String KEY_SERVICE = "service";
    public static final String KEY_START_ID = "sid";
    public static final String KEY_STATUS = "status";
    public static final String KEY_VALUE = "value";
    public static final String LOG_TYPE = "log_type";
    public static final String LOG_TYPE_SERVICE_MONITOR = "service_monitor";
    public static final String MESSAGE = "message";
    public static final String TYPE_ENSURE_MONITOR = "core_exception_monitor";

    /* renamed from: a  reason: collision with root package name */
    private static final String f6649a = "event_type";

    /* renamed from: b  reason: collision with root package name */
    public static final String f6650b = "timestamp";

    /* renamed from: c  reason: collision with root package name */
    private static final String f6651c = "class_ref";

    /* renamed from: d  reason: collision with root package name */
    private static final String f6652d = "method";

    /* renamed from: e  reason: collision with root package name */
    private static final String f6653e = "line_num";

    /* renamed from: f  reason: collision with root package name */
    public static final String f6654f = "stack";

    /* renamed from: g  reason: collision with root package name */
    private static final String f6655g = "exception_type";

    /* renamed from: h  reason: collision with root package name */
    private static final String f6656h = "ensure_type";

    /* renamed from: i  reason: collision with root package name */
    private static final String f6657i = "is_core";

    /* renamed from: j  reason: collision with root package name */
    private static final String f6658j = "caton_monitor";
    private static final String k = "lag";
    private static final String l = "exception";
    private static final String m = "native_exception";

    public boolean isEvent() {
        return false;
    }

    private d(String str) {
    }

    public static d wrapEnsure(StackTraceElement stackTraceElement, String str, String str2, String str3, boolean z, String str4) {
        d dVar = new d("core_exception_monitor");
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        int lineNumber = stackTraceElement.getLineNumber();
        dVar.put("event_type", "exception");
        dVar.put("log_type", "core_exception_monitor");
        dVar.put("timestamp", Long.valueOf(System.currentTimeMillis()));
        dVar.put("crash_time", Long.valueOf(System.currentTimeMillis()));
        dVar.put("class_ref", className);
        dVar.put("method", methodName);
        dVar.put("line_num", Integer.valueOf(lineNumber));
        dVar.put("stack", str);
        dVar.put("exception_type", 1);
        dVar.put("ensure_type", str4);
        dVar.put(f6657i, Integer.valueOf(z ? 1 : 0));
        dVar.put("message", str2);
        dVar.put("process_name", com.bytedance.crash.util.b.a());
        dVar.put(b.CRASH_THREAD_NAME, str3);
        JSONObject optJSONObject = dVar.getJson().optJSONObject("filters");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
            try {
                dVar.getJson().put("filters", optJSONObject);
            } catch (JSONException unused) {
            }
        }
        com.bytedance.crash.dumper.d.b(optJSONObject);
        return dVar;
    }

    public static d wrapNativeEnsure(String str, String str2, String str3, String str4, String str5) {
        d wrapEnsure = wrapEnsure(new StackTraceElement("Native", "Native", "Native.java", -1), str2, str3, str4, true, e.f6696a, str5);
        wrapEnsure.put("event_type", m);
        wrapEnsure.put("java_data", str);
        return wrapEnsure;
    }

    public b a(com.bytedance.crash.runtime.assembly.a aVar) {
        put(com.bytedance.crash.tracker.a.f7151a, aVar.getActivityTrace());
        a(com.bytedance.crash.tracker.a.f7152b, aVar.getActivityLife());
        return this;
    }

    public static d wrapEnsure(StackTraceElement stackTraceElement, String str, String str2, String str3, boolean z, String str4, String str5) {
        d dVar = new d(str5);
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        int lineNumber = stackTraceElement.getLineNumber();
        dVar.put("event_type", "exception");
        dVar.put("log_type", str5);
        dVar.put("timestamp", Long.valueOf(System.currentTimeMillis()));
        dVar.put("crash_time", Long.valueOf(System.currentTimeMillis()));
        dVar.put("class_ref", className);
        dVar.put("method", methodName);
        dVar.put("line_num", Integer.valueOf(lineNumber));
        dVar.put("stack", str);
        dVar.put("crash_md5", com.bytedance.crash.util.d.a(str));
        dVar.put("exception_type", 1);
        dVar.put("ensure_type", str4);
        dVar.put(f6657i, Integer.valueOf(z ? 1 : 0));
        dVar.put("message", str2);
        dVar.put("process_name", com.bytedance.crash.util.b.a());
        dVar.put(b.CRASH_THREAD_NAME, str3);
        JSONObject optJSONObject = dVar.getJson().optJSONObject("filters");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
            try {
                dVar.getJson().put("filters", optJSONObject);
            } catch (JSONException unused) {
            }
        }
        com.bytedance.crash.dumper.d.b(optJSONObject);
        return dVar;
    }

    public static d wrapBlock(String str) {
        d dVar = new d("caton_monitor");
        dVar.put("event_type", "lag");
        dVar.put("log_type", "caton_monitor");
        dVar.put("timestamp", Long.valueOf(System.currentTimeMillis()));
        dVar.put("crash_time", Long.valueOf(System.currentTimeMillis()));
        dVar.put("process_name", com.bytedance.crash.util.b.a());
        dVar.put(b.CRASH_THREAD_NAME, "main");
        dVar.put("stack", str);
        return dVar;
    }

    public JSONObject a() {
        Object opt = getJson().opt("data");
        if (opt instanceof JSONArray) {
            return ((JSONArray) opt).optJSONObject(0);
        }
        return getJson();
    }
}
