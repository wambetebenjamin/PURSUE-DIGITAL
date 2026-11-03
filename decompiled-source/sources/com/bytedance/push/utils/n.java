package com.bytedance.push.utils;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.geckox.b;
import org.json.JSONObject;
/* compiled from: RomVersionParamHelper.java */
/* loaded from: classes2.dex */
public class n {
    public static final String COLOROS = "coloros";
    public static final String HARMONY_API_VERSION = "hw_sc.build.os.apiversion";
    public static final String HARMONY_RELEASE_TYPE = "hw_sc.build.os.releasetype";
    public static final String HARMONY_VERSION = "hw_sc.build.platform.version";
    public static final String MIUI = "miui";
    public static final String RUNTIME_MIUI = "ro.miui.ui.version.name";
    public static final String RUNTIME_OP = "ro.build.version.opporom";
    public static final String SEPARATOR = "_";

    /* renamed from: a  reason: collision with root package name */
    private static final String f12060a;

    /* renamed from: b  reason: collision with root package name */
    private static final String f12061b = "RomVersionParamHelper";

    /* renamed from: c  reason: collision with root package name */
    private static final q f12062c;

    /* renamed from: d  reason: collision with root package name */
    private static final String f12063d = "ro.build.version.emui";

    /* renamed from: e  reason: collision with root package name */
    private static final String f12064e = "oppo";

    /* renamed from: f  reason: collision with root package name */
    private static final String f12065f = "funtouch";

    /* renamed from: g  reason: collision with root package name */
    private static final String f12066g = "origin";

    /* renamed from: h  reason: collision with root package name */
    private static final String f12067h = "ro.vivo.os.build.display.id";

    /* renamed from: i  reason: collision with root package name */
    private static final String f12068i = "ohos.system.version.SystemVersion";

    /* renamed from: j  reason: collision with root package name */
    private static final String f12069j = "harmony";
    private static String k = null;
    private static String l = null;
    private static String m = null;
    private static boolean n = false;
    private static boolean o = false;
    private static final JSONObject p;
    private static final String q = "ro.vivo.product.version";
    public static final String r = "ro.build.version.oplusrom";
    private static Boolean s;

    static {
        String str;
        String valueOf = String.valueOf(Build.VERSION.SDK);
        f12060a = valueOf;
        f12062c = new q();
        k = valueOf;
        try {
            str = a();
        } catch (Throwable th) {
            th.printStackTrace();
            str = null;
        }
        if (com.bytedance.common.utility.o.isEmpty(str)) {
            str = f12060a;
        }
        k = str;
        p = new JSONObject();
        s = null;
    }

    public static String getParameter() {
        return k;
    }

    private static String a() {
        if (h()) {
            return j();
        }
        if (isMagic()) {
            return i();
        }
        if (d()) {
            return e();
        }
        if (k()) {
            return l();
        }
        if (b()) {
            return c();
        }
        if (com.ss.android.common.util.d.isMiui()) {
            return f();
        }
        return f12060a;
    }

    private static boolean b() {
        String str = Build.MANUFACTURER;
        if (com.bytedance.common.utility.o.isEmpty(str)) {
            return false;
        }
        return str.toLowerCase().contains(f12064e);
    }

    private static String c() {
        if (b()) {
            String a2 = a("ro.build.version.opporom");
            if (TextUtils.isEmpty(a2)) {
                a2 = a(r);
            }
            return ("coloros_" + a2 + "_" + Build.DISPLAY).toLowerCase();
        }
        return f12060a;
    }

    private static boolean d() {
        String a2 = a(f12067h);
        return !com.bytedance.common.utility.o.isEmpty(a2) && a2.toLowerCase().contains(f12065f);
    }

    public static boolean k() {
        String a2 = a(f12067h);
        return !com.bytedance.common.utility.o.isEmpty(a2) && a2.toLowerCase().contains("origin");
    }

    private static String e() {
        return (a(f12067h) + "_" + a(q)).toLowerCase();
    }

    private static String l() {
        return (a(f12067h) + "_" + a(q)).toLowerCase();
    }

    private static String f() {
        return ("miui_" + a("ro.miui.ui.version.name") + "_" + Build.VERSION.INCREMENTAL).toLowerCase();
    }

    public static boolean isMiui12() {
        if (com.ss.android.common.util.d.isMiui()) {
            try {
                return Integer.parseInt(a("ro.miui.ui.version.name").substring(1)) >= 12;
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    private static String a(String str) {
        return f12062c.get(str);
    }

    public static boolean isMagic() {
        try {
            String a2 = a(f12063d);
            l = a2;
            if (com.bytedance.common.utility.o.isEmpty(a2) || !l.toLowerCase().startsWith("magic")) {
                return false;
            }
            m = l.toLowerCase();
            return true;
        } catch (Exception e2) {
            h.e(e2.getMessage());
            return false;
        }
    }

    public static boolean isHarmonyOs() {
        try {
            Boolean bool = s;
            if (bool != null) {
                return bool.booleanValue();
            }
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            Boolean valueOf = Boolean.valueOf(f12069j.equals(cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0])));
            s = valueOf;
            return valueOf.booleanValue();
        } catch (Throwable unused) {
            h.v("isn't harmony");
            return false;
        }
    }

    public static String getHarmonyOsVersion() {
        JSONObject jSONObject = p;
        synchronized (jSONObject) {
            if (!n) {
                g();
            }
            if (jSONObject.keys().hasNext()) {
                return jSONObject.toString();
            }
            return "";
        }
    }

    public static boolean m() {
        getHarmonyOsVersion();
        try {
            String optString = p.optString("version", "");
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            return Integer.parseInt(optString.split("\\.")[0]) >= 4;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static void g() {
        if (n) {
            return;
        }
        try {
            boolean isHarmonyOs = isHarmonyOs();
            o = isHarmonyOs;
            if (isHarmonyOs) {
                String a2 = a(HARMONY_API_VERSION);
                String a3 = a(HARMONY_RELEASE_TYPE);
                String a4 = a(HARMONY_VERSION);
                JSONObject jSONObject = p;
                jSONObject.put(b.g.f8910c, a2);
                jSONObject.put("release_type", a3);
                jSONObject.put("version", a4);
                Log.d(f12061b, "initHarmonyOsVersion: apiVersion is " + a2 + " releaseType is " + a3 + " version is " + a4);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        n = true;
    }

    private static boolean h() {
        try {
            String a2 = a(f12063d);
            l = a2;
            boolean isEmpty = com.bytedance.common.utility.o.isEmpty(a2);
            if (!isEmpty) {
                if (l.toLowerCase().startsWith("magic")) {
                    m = l.toLowerCase();
                    return false;
                }
                l = l.toLowerCase();
            }
            return !isEmpty;
        } catch (Exception e2) {
            h.e(e2.getMessage());
            return false;
        }
    }

    private static String i() {
        if (com.bytedance.common.utility.o.isEmpty(m)) {
            m = a(f12063d);
        }
        String lowerCase = (m + "_" + Build.DISPLAY).toLowerCase();
        if (!com.bytedance.common.utility.o.isEmpty(lowerCase)) {
            return lowerCase.toLowerCase();
        }
        return f12060a;
    }

    private static String j() {
        if (com.bytedance.common.utility.o.isEmpty(l)) {
            l = a(f12063d);
        }
        String lowerCase = (l + "_" + Build.DISPLAY).toLowerCase();
        if (!com.bytedance.common.utility.o.isEmpty(lowerCase)) {
            return lowerCase.toLowerCase();
        }
        return f12060a;
    }

    public static boolean isColorOS() {
        try {
            return !TextUtils.isEmpty(a("ro.build.version.opporom"));
        } catch (Throwable unused) {
            return false;
        }
    }
}
