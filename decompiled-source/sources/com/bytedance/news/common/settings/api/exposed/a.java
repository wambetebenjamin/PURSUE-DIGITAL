package com.bytedance.news.common.settings.api.exposed;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.news.common.settings.internal.b;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: ExposedManager.java */
/* loaded from: classes2.dex */
public class a {
    public static final String TAG_SETTINGS_AUTO_TEST = "settings_auto_test";

    /* renamed from: a  reason: collision with root package name */
    private static final String f9749a = "ExposedManager";

    /* renamed from: b  reason: collision with root package name */
    private static final String f9750b = "key_vid_info";

    /* renamed from: c  reason: collision with root package name */
    private static final String f9751c = "key_update_version_code";

    /* renamed from: d  reason: collision with root package name */
    private static final String f9752d = "sp_";

    /* renamed from: e  reason: collision with root package name */
    private static final String f9753e = "uid_sp_name_time.sp";

    /* renamed from: f  reason: collision with root package name */
    private static volatile a f9754f = null;
    private static volatile boolean s = false;
    public static volatile com.bytedance.news.common.settings.api.a sDebugTeller = null;
    private static volatile boolean t = false;
    private static volatile long u = -1;
    private static volatile long v = -1;
    private static volatile String w = null;
    private static final long x = 604800000;
    private static ConcurrentHashMap y = new ConcurrentHashMap();
    private static b z;

    /* renamed from: g  reason: collision with root package name */
    private JSONObject f9755g;

    /* renamed from: h  reason: collision with root package name */
    private SharedPreferences f9756h;

    /* renamed from: i  reason: collision with root package name */
    private SharedPreferences f9757i;

    /* renamed from: j  reason: collision with root package name */
    private SharedPreferences f9758j;
    private volatile SharedPreferences k;
    private SharedPreferences.Editor l;
    private SharedPreferences.Editor m;
    private volatile SharedPreferences.Editor n;
    private volatile boolean o;
    private SharedPreferences p;
    private SharedPreferences.Editor q;
    private Context r;

    public static boolean isReportSettingsStack() {
        return false;
    }

    public static a getInstance(Context context) {
        if (f9754f == null) {
            synchronized (a.class) {
                if (f9754f == null) {
                    f9754f = new a(context);
                }
            }
        }
        return f9754f;
    }

    private a(Context context) {
        this.r = context;
        this.f9756h = context.getSharedPreferences("__ab_vid_info.sp", 0);
        this.f9758j = context.getSharedPreferences("__ab_exposed_info.sp", 0);
        this.f9757i = context.getSharedPreferences("__ab_local_exposed_info.sp", 0);
        this.p = context.getSharedPreferences(f9753e, 0);
        this.m = this.f9757i.edit();
        this.l = this.f9758j.edit();
        this.q = this.p.edit();
        String string = this.f9756h.getString(f9750b, "");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        try {
            this.f9755g = new JSONObject(string);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void updateSPForUID() {
        long currentTimeMillis = System.currentTimeMillis();
        String str = w != null ? f9752d + w + ".sp" : "sp_0.sp";
        this.k = this.r.getSharedPreferences(str, 0);
        this.q.putLong(str, currentTimeMillis).apply();
        this.n = this.k.edit();
    }

    public void deleteOutOfDateSP() {
        long currentTimeMillis = System.currentTimeMillis();
        for (String str : this.p.getAll().keySet()) {
            if (currentTimeMillis - this.p.getLong(str, 0L) > 604800000) {
                File file = new File("/data/data/" + this.r.getPackageName().toString() + "/shared_prefs", str + ".xml");
                if (file.exists()) {
                    file.delete();
                }
                this.q.remove(str).apply();
            }
        }
    }

    public static void setDebugTeller(com.bytedance.news.common.settings.api.a aVar) {
        if (sDebugTeller == null) {
            sDebugTeller = aVar;
        }
    }

    public synchronized void updateVidInfo(JSONObject jSONObject) {
        this.f9755g = jSONObject;
        this.f9756h.edit().putString(f9750b, jSONObject.toString()).apply();
        for (String str : this.f9758j.getAll().keySet()) {
            if (!this.f9755g.has(str)) {
                this.l.remove(str);
            } else {
                try {
                    if (this.f9755g.optLong(str) != Long.parseLong(this.f9758j.getString(str, "0"))) {
                        this.l.remove(str);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        updateSPForUID();
        deleteOutOfDateSP();
        for (String str2 : this.k.getAll().keySet()) {
            if (!this.f9755g.has(str2)) {
                this.n.remove(str2);
            } else {
                try {
                    if (this.f9755g.optLong(str2) != Long.parseLong(this.k.getString(str2, "0"))) {
                        this.n.remove(str2);
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        this.l.apply();
        this.n.apply();
    }

    public void markExposed(String str) {
        markExposed(str, false);
    }

    public void markExposed(String str, Boolean bool) {
        b bVar = z;
        if (bVar != null && bVar.getSettingsLogService() != null) {
            z.getSettingsLogService().e(TAG_SETTINGS_AUTO_TEST, str);
        }
        if (this.f9755g != null) {
            synchronized (this) {
                JSONObject jSONObject = this.f9755g;
                if (jSONObject != null) {
                    long optLong = jSONObject.optLong(str);
                    if (optLong > 0) {
                        if (bool.booleanValue()) {
                            updateSPForUID();
                            if (!this.k.contains(str)) {
                                this.n.putString(str, String.valueOf(optLong)).apply();
                            } else {
                                try {
                                    if (optLong != Long.parseLong(this.k.getString(str, "0"))) {
                                        this.n.putString(str, String.valueOf(optLong)).apply();
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        } else if (!this.f9758j.contains(str)) {
                            this.l.putString(str, String.valueOf(optLong)).apply();
                        } else {
                            try {
                                if (optLong != Long.parseLong(this.f9758j.getString(str, "0"))) {
                                    this.l.putString(str, String.valueOf(optLong)).apply();
                                }
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public void markLocalClientExposed(String str) {
        if (TextUtils.isEmpty(str) || this.f9757i == null) {
            return;
        }
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f9757i;
            if (sharedPreferences != null && !sharedPreferences.contains(str)) {
                this.f9757i.edit().putString(str, str).apply();
            }
        }
    }

    public String getExposedVids() {
        StringBuilder sb;
        if (this.f9755g != null) {
            sb = new StringBuilder();
            try {
                for (Object obj : this.f9758j.getAll().values()) {
                    if (sb.length() > 1) {
                        sb.append(",");
                    }
                    sb.append(obj);
                }
                updateSPForUID();
                for (Object obj2 : this.k.getAll().values()) {
                    if (sb.length() > 1) {
                        sb.append(",");
                    }
                    sb.append(obj2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            sb = null;
        }
        if (this.f9757i != null) {
            if (sb == null) {
                sb = new StringBuilder();
            }
            try {
                for (Map.Entry<String, ?> entry : this.f9757i.getAll().entrySet()) {
                    if (!TextUtils.equals(entry.getKey(), f9751c)) {
                        Object value = entry.getValue();
                        if (sb.length() > 1) {
                            sb.append(",");
                        }
                        sb.append(value);
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        if (sb != null) {
            return sb.toString();
        }
        return null;
    }

    public void setUpdateVersionCode(String str) {
        if (this.o) {
            return;
        }
        this.o = true;
        SharedPreferences sharedPreferences = this.f9757i;
        if (sharedPreferences == null || this.m == null) {
            return;
        }
        String string = sharedPreferences.getString(f9751c, "");
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(str)) {
                this.m.putString(f9751c, "").apply();
            } else {
                this.m.putString(f9751c, str).apply();
            }
        } else if (TextUtils.equals(string, str)) {
        } else {
            this.m.clear().apply();
        }
    }

    public static boolean needsReporting(String str) {
        if (!t || y.containsKey(str)) {
            return false;
        }
        y.put(str, "");
        return true;
    }

    public static boolean isUseOneSpForAppSettingsStatic() {
        return s;
    }

    public static void setUseOneSpForAppSettingsStatic(boolean z2) {
        s = z2;
    }

    public boolean isUseOneSpForAppSettings() {
        return s;
    }

    public static void setReportSettingsStack(boolean z2) {
        t = z2;
    }

    public static void setInitSystemTime(long j2) {
        u = j2;
    }

    public static long getSettingsUsingTime() {
        if (u < 0) {
            u = System.currentTimeMillis();
        }
        v = System.currentTimeMillis() - u;
        return v;
    }

    public static void setUID(String str) {
        w = str;
    }

    public static void setIconfig(b bVar) {
        z = bVar;
    }
}
