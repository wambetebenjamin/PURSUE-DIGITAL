package com.bytedance.crash.entity;

import com.bytedance.crash.util.q;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: CrashBody.java */
/* loaded from: classes2.dex */
public class b {
    public static final String APP_START_UP_TIME = "app_start_up_time";
    public static final String CRASH_LIB_UUID = "crash_lib_uuid";
    public static final String CRASH_THREAD_NAME = "crash_thread_name";
    public static final String CRASH_TIME = "crash_time";
    public static final String CUSTOM = "custom";
    public static final String DATA = "data";
    public static final String FILTERS = "filters";
    public static final String HEADER = "header";
    public static final String IS_NATIVE_CRASH = "is_native_crash";
    public static final String LAUNCH_MODE = "launch_mode";
    public static final String LAUNCH_TIME = "launch_time";
    public static final String LIB_NAME = "lib_name";
    public static final String LIB_UUID = "lib_uuid";
    public static final String PID = "pid";
    public static final String STACK = "stack";
    public static final String STACK_MD5 = "crash_md5";
    public static final String START_PID = "start_pid";
    public static final String TID = "tid";
    public static final String n = "crash_body.json";
    public static final String o = "process_name";
    public static final String p = "app_start_time";
    public static final String q = "crash_time";
    public static final String r = "abort_msg";
    public static final String s = "logcat_anr_info";
    public static final String t = "logcat_gc_info";

    /* renamed from: h  reason: collision with root package name */
    private JSONObject f6646h;

    /* renamed from: i  reason: collision with root package name */
    private Header f6647i;

    public b() {
        this.f6646h = new JSONObject();
        this.f6647i = new Header();
    }

    @Deprecated
    public b(JSONObject jSONObject) {
        this.f6646h = jSONObject;
    }

    public Header getHeader() {
        return this.f6647i;
    }

    public JSONObject getHeaderJson() {
        return this.f6646h.optJSONObject("header");
    }

    public JSONObject getJson() {
        return this.f6646h;
    }

    public void a(JSONObject jSONObject) {
        this.f6646h = jSONObject;
    }

    public b setHeader(Header header) {
        put("header", header.b());
        this.f6647i = header;
        return this;
    }

    public void put(String str, Object obj) {
        q.a(this.f6646h, str, obj);
    }

    @Deprecated
    public b setAppStartTime(long j2) {
        return setAppStartTime(j2, 0L);
    }

    @Deprecated
    public b setAppStartTime(long j2, long j3) {
        try {
            put("app_start_time", Long.valueOf(j2));
            if (j3 != 0) {
                put(APP_START_UP_TIME, Long.valueOf(j3));
            }
            put("app_start_time_readable", new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(new Date(j2)));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return this;
    }

    @Deprecated
    public b addCustom(String str, String str2) {
        putInJson(this.f6646h, "custom", str, str2);
        return this;
    }

    @Deprecated
    public b addFilter(String str, String str2) {
        putInJson(this.f6646h, "filters", str, str2);
        return this;
    }

    @Deprecated
    public static void putInJson(JSONObject jSONObject, String str, String str2, Object obj) {
        if (jSONObject == null) {
            return;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
            q.a(jSONObject, str, optJSONObject);
        }
        q.a(optJSONObject, str2, obj);
    }

    public b addCustomLong(String str, List<String> list) {
        if (list == null) {
            return this;
        }
        JSONArray jSONArray = new JSONArray();
        for (String str2 : list) {
            jSONArray.put(str2);
        }
        a(str, jSONArray);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b a(String str, JSONArray jSONArray) {
        c.a(getJson(), str, jSONArray);
        return this;
    }
}
