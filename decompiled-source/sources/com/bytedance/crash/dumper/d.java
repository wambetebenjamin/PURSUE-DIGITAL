package com.bytedance.crash.dumper;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import com.bytedance.crash.Global;
import com.facebook.internal.ServerProtocol;
import java.io.File;
import java.util.Iterator;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: BytestConfig.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6508a = "bytest_automation_info";

    /* renamed from: b  reason: collision with root package name */
    private static d f6509b;
    private static volatile boolean k;

    /* renamed from: c  reason: collision with root package name */
    private final JSONObject f6510c;

    /* renamed from: d  reason: collision with root package name */
    private final JSONObject f6511d;

    /* renamed from: e  reason: collision with root package name */
    private String f6512e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f6513f;

    /* renamed from: g  reason: collision with root package name */
    private final boolean f6514g;

    /* renamed from: h  reason: collision with root package name */
    private final boolean f6515h;

    /* renamed from: i  reason: collision with root package name */
    private final boolean f6516i;

    /* renamed from: j  reason: collision with root package name */
    private final String f6517j;

    private static d f() {
        if (f6509b == null) {
            f6509b = new d();
        }
        return f6509b;
    }

    d() {
        JSONObject g2 = g();
        this.f6510c = g2;
        if (g2.length() != 0) {
            this.f6513f = true;
            this.f6511d = g2.optJSONObject("slardar_filter");
        } else {
            this.f6513f = false;
            this.f6511d = null;
        }
        JSONObject jSONObject = this.f6511d;
        if (jSONObject != null) {
            this.f6515h = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(jSONObject.optString("gwp_asan_switch"));
            this.f6514g = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(this.f6511d.optString("core_dump_switch"));
            this.f6516i = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(this.f6511d.optString("is_all_exception_collected"));
            com.bytedance.crash.config.i.a(true);
        } else {
            this.f6515h = false;
            this.f6514g = false;
            this.f6516i = false;
        }
        String optString = g2.optString("bytrace_id_prefix");
        this.f6517j = TextUtils.isEmpty(optString) ? UUID.randomUUID() + "-" + Process.myPid() : optString;
    }

    public static String a() {
        return f().f6517j;
    }

    private JSONObject g() {
        try {
            Context c2 = Global.c();
            if (c2 != null) {
                ContentResolver contentResolver = c2.getContentResolver();
                if (Build.VERSION.SDK_INT >= 17) {
                    this.f6512e = Settings.Global.getString(contentResolver, f6508a);
                    return new JSONObject(this.f6512e);
                }
            }
        } catch (Throwable unused) {
        }
        return new JSONObject();
    }

    public static void a(File file) {
        if (k) {
            return;
        }
        k = true;
        try {
            String str = f().f6512e;
            if (str == null) {
                return;
            }
            com.bytedance.crash.util.m.writeFile(file, str, false);
        } catch (Throwable unused) {
        }
    }

    public static boolean b() {
        return f().f6514g;
    }

    public static boolean c() {
        return f().f6515h;
    }

    public static boolean d() {
        return f().f6516i;
    }

    public static boolean e() {
        return f().f6513f;
    }

    public static void a(JSONObject jSONObject) {
        Object opt;
        JSONObject jSONObject2 = f().f6510c;
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (!"slardar_filter".equals(next) && (opt = jSONObject2.opt(next)) != null) {
                try {
                    jSONObject.put(next, opt);
                } catch (JSONException unused) {
                }
            }
        }
    }

    public static void b(JSONObject jSONObject) {
        JSONObject jSONObject2 = f().f6511d;
        if (jSONObject2 == null || jSONObject2.length() == 0) {
            return;
        }
        com.bytedance.crash.util.q.a(jSONObject, jSONObject2);
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        Object opt;
        if (jSONObject == null) {
            return;
        }
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!"slardar_filter".equals(next) && (opt = jSONObject.opt(next)) != null) {
                    try {
                        jSONObject2.put(next, opt);
                    } catch (JSONException unused) {
                    }
                }
            }
        } catch (Exception unused2) {
        }
    }

    public static JSONObject a(File file, JSONObject jSONObject) {
        try {
            if (file.isFile() && file.exists()) {
                JSONObject jSONObject2 = new JSONObject(com.bytedance.crash.util.m.readFile(file));
                JSONObject optJSONObject = jSONObject2.optJSONObject("slardar_filter");
                JSONObject optJSONObject2 = jSONObject.optJSONObject("filters");
                if (optJSONObject2 == null) {
                    optJSONObject2 = new JSONObject();
                    jSONObject.put("filters", optJSONObject2);
                }
                if (optJSONObject != null && optJSONObject.length() != 0) {
                    if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(optJSONObject.optString("core_dump_switch"))) {
                        optJSONObject2.put("coredump_bytest", true);
                    }
                    com.bytedance.crash.util.q.a(optJSONObject2, optJSONObject);
                    return jSONObject2;
                }
                optJSONObject2.put("coredump_bytest", false);
                return jSONObject2;
            }
        } catch (Exception unused) {
        }
        return null;
    }
}
