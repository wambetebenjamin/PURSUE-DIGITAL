package com.bytedance.applog.monitor.v3;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: MonitorConfigManager.java */
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public static final String f2915a = "sentry_stained";

    /* renamed from: b  reason: collision with root package name */
    public static final String f2916b = "event_stained";

    /* renamed from: c  reason: collision with root package name */
    public static final int f2917c = 10000;

    /* renamed from: d  reason: collision with root package name */
    public static final String f2918d = "uptime";

    /* renamed from: e  reason: collision with root package name */
    public static volatile boolean f2919e = true;

    /* renamed from: f  reason: collision with root package name */
    public static volatile int f2920f = 1024;

    /* renamed from: g  reason: collision with root package name */
    public static volatile int f2921g = 10;

    /* renamed from: h  reason: collision with root package name */
    public static volatile boolean f2922h = true;

    /* renamed from: i  reason: collision with root package name */
    public static volatile long f2923i = 300000;

    /* renamed from: j  reason: collision with root package name */
    public static volatile long f2924j = 30000;
    public static volatile int k = 7;
    public static volatile boolean l = false;
    public static volatile int m = 0;
    public static volatile Map<String, Integer> n = null;
    private static final String p = "enabled";
    private static final String q = "max_request_body_size_kb";
    private static final String r = "max_request_count";
    private static final String s = "error_report_enable";
    private static final String t = "report_interval_ms";
    private static final String u = "coldstart_report_delay_ms";
    private static final String v = "data_keep_alive_days";
    private static final String w = "sentry_enabled";
    private static final String x = "sentry_coldstart_sampling_rate";
    private static final String y = "sentry_events";
    private SharedPreferences o;
    private boolean z = f2919e;
    private int A = f2920f * 1024;
    private int B = f2921g;
    private boolean C = f2922h;
    private long D = f2923i;
    private long E = f2924j;
    private int F = k;
    private boolean G = l;
    private int H = m;
    private Map<String, Integer> I = n;

    /* renamed from: J  reason: collision with root package name */
    private JSONObject f2925J = null;
    private volatile boolean K = false;

    public void a(boolean z) {
        if (!z) {
            this.K = false;
            return;
        }
        this.K = true;
        f2919e = true;
        l = true;
        m = 10000;
        n = null;
        this.z = f2919e;
        this.G = l;
        this.H = m;
        this.I = n;
    }

    public void a(Context context, String str) {
        if (context == null) {
            return;
        }
        this.o = context.getSharedPreferences(str + "@applog_monitor_config", 0);
        l();
    }

    public boolean a() {
        return this.z;
    }

    public int b() {
        return this.A;
    }

    public int c() {
        return this.B;
    }

    public boolean d() {
        return this.C;
    }

    public long e() {
        return this.D;
    }

    public long f() {
        return this.E;
    }

    public int g() {
        return this.F;
    }

    public boolean h() {
        return this.G;
    }

    public int i() {
        return this.H;
    }

    public boolean j() {
        return this.K;
    }

    public Map<String, Integer> k() {
        return this.I;
    }

    public boolean a(String str) {
        return this.I.containsKey(str);
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        SharedPreferences.Editor editor = null;
        SharedPreferences sharedPreferences = this.o;
        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
        } else {
            this.f2925J = jSONObject;
        }
        boolean z = jSONObject.optInt("enabled", f2919e ? 1 : 0) == 1;
        this.z = z;
        if (editor != null) {
            editor.putBoolean("enabled", z);
        }
        if (jSONObject.has(q)) {
            int optInt = jSONObject.optInt(q, f2920f) * 1024;
            this.A = optInt;
            if (editor != null) {
                editor.putInt(q, optInt);
            }
        }
        if (jSONObject.has(r)) {
            int optInt2 = jSONObject.optInt(r, f2921g);
            this.B = optInt2;
            if (editor != null) {
                editor.putInt(r, optInt2);
            }
        }
        if (jSONObject.has(s)) {
            boolean z2 = jSONObject.optInt(s, f2922h ? 1 : 0) == 1;
            this.C = z2;
            if (editor != null) {
                editor.putBoolean(s, z2);
            }
        }
        if (jSONObject.has(t)) {
            long optLong = jSONObject.optLong(t, f2923i);
            this.D = optLong;
            if (editor != null) {
                editor.putLong(t, optLong);
            }
        }
        if (jSONObject.has(u)) {
            long optLong2 = jSONObject.optLong(u, f2924j);
            this.E = optLong2;
            if (editor != null) {
                editor.putLong(u, optLong2);
            }
        }
        if (jSONObject.has(v)) {
            int optInt3 = jSONObject.optInt(v, k);
            this.F = optInt3;
            if (editor != null) {
                editor.putLong(v, optInt3);
            }
        }
        if (jSONObject.has(w)) {
            boolean z3 = jSONObject.optInt(w, l ? 1 : 0) == 1;
            this.G = z3;
            if (editor != null) {
                editor.putBoolean(w, z3);
            }
        }
        if (jSONObject.has(x)) {
            int optInt4 = jSONObject.optInt(x, m);
            this.H = optInt4;
            if (editor != null) {
                editor.putInt(x, optInt4);
            }
        }
        if (jSONObject.has(y)) {
            JSONObject optJSONObject = jSONObject.optJSONObject(y);
            b(optJSONObject);
            if (editor != null) {
                editor.putString(y, optJSONObject.toString());
            }
        }
        if (editor != null) {
            editor.apply();
        }
        if (this.K) {
            a(true);
        }
    }

    private void l() {
        JSONObject jSONObject = this.f2925J;
        if (jSONObject != null && this.o != null) {
            a(jSONObject);
            return;
        }
        if (!this.o.contains("enabled")) {
            this.z = f2919e;
            this.A = f2920f * 1024;
            this.B = f2921g;
            this.C = f2922h;
            this.D = f2923i;
            this.E = f2924j;
            this.F = k;
            this.G = l;
            this.H = m;
            this.I = n;
        } else {
            this.z = this.o.getBoolean("enabled", f2919e);
            this.A = this.o.getInt(q, f2920f * 1024);
            this.B = this.o.getInt(r, f2921g);
            this.C = this.o.getBoolean(s, f2922h);
            this.D = this.o.getLong(t, f2923i);
            this.E = this.o.getLong(u, f2924j);
            this.F = this.o.getInt(v, k);
            this.G = this.o.getBoolean(w, l);
            this.H = this.o.getInt(x, m);
            String string = this.o.getString(y, null);
            if (!TextUtils.isEmpty(string)) {
                try {
                    b(new JSONObject(string));
                } catch (Throwable unused) {
                }
            }
        }
        if (this.K) {
            a(true);
        }
    }

    private void b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.I.clear();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            int optInt = jSONObject.optInt(next, 0);
            if (optInt > 0 && optInt <= 10000) {
                this.I.put(next, Integer.valueOf(optInt));
            }
        }
    }
}
