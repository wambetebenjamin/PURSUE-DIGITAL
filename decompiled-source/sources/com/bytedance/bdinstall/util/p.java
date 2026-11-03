package com.bytedance.bdinstall.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.util.Locale;
/* compiled from: RomUtils.java */
/* loaded from: classes.dex */
public class p {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4067a = "_";

    /* renamed from: b  reason: collision with root package name */
    public static final String f4068b = "miui";

    /* renamed from: c  reason: collision with root package name */
    public static final String f4069c = "emotionui";

    /* renamed from: d  reason: collision with root package name */
    public static final String f4070d = "flyme";

    /* renamed from: e  reason: collision with root package name */
    public static final String f4071e = "coloros";

    /* renamed from: f  reason: collision with root package name */
    public static final String f4072f = "ro.miui.ui.version.name";

    /* renamed from: g  reason: collision with root package name */
    public static final String f4073g = "ro.build.version.opporom";

    /* renamed from: h  reason: collision with root package name */
    private static final String f4074h = "magicui";

    /* renamed from: i  reason: collision with root package name */
    private static final String f4075i = "eui";

    /* renamed from: j  reason: collision with root package name */
    private static final String f4076j = "ro.letv.release.version";
    private static final String m = "ro.vivo.os.build.display.id";
    private static final String n = "ro.vivo.product.version";
    private static final String o = "ro.build.uiversion";
    private static final String p = "harmony";
    private static final CharSequence k = "sony";
    private static final CharSequence l = "amigo";
    private static final r<Boolean> q = new r<Boolean>() { // from class: com.bytedance.bdinstall.util.p.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.bdinstall.util.r
        /* renamed from: a */
        public Boolean b(Object... objArr) {
            try {
                Class<?> cls = Class.forName("com.huawei.system.BuildEx");
                return Boolean.valueOf(p.p.equals(cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0])));
            } catch (Throwable unused) {
                com.bytedance.bdinstall.u.b("isn't harmony");
                return false;
            }
        }
    };

    public static String a() {
        return b("ro.build.version.emui");
    }

    public static String b() {
        if (n()) {
            return o();
        }
        if (s()) {
            return v();
        }
        String q2 = q();
        if (TextUtils.isEmpty(q2)) {
            if (h()) {
                return f();
            }
            if (g()) {
                return r();
            }
            if (i()) {
                return j();
            }
            if (e()) {
                return d();
            }
            String k2 = k();
            return !TextUtils.isEmpty(k2) ? k2 : Build.DISPLAY;
        }
        return q2;
    }

    public static String c() {
        String a2 = a("persist.pxr.product.forcename", "");
        if (TextUtils.isEmpty(a2)) {
            a2 = a("pxr.vendorhw.product.name", "");
        }
        return TextUtils.isEmpty(a2) ? Build.PRODUCT : a2;
    }

    public static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }

    public static String d() {
        return b(o) + "_" + Build.DISPLAY;
    }

    public static boolean e() {
        String str = Build.MANUFACTURER + Build.BRAND;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.contains("360") || lowerCase.contains("qiku");
    }

    public static String f() {
        return b(m) + "_" + b(n);
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = a();
        }
        return (!TextUtils.isEmpty(str) && (str.toLowerCase().contains("emotionui") || str.toLowerCase().contains(f4074h))) || H() || m();
    }

    public static boolean g() {
        return (!TextUtils.isEmpty(Build.DISPLAY) && Build.DISPLAY.contains("Flyme")) || "flyme".equals(Build.USER);
    }

    public static boolean h() {
        return !TextUtils.isEmpty(b(m));
    }

    public static boolean i() {
        return !TextUtils.isEmpty(Build.DISPLAY) && Build.DISPLAY.toLowerCase().contains(l);
    }

    public static String j() {
        return Build.DISPLAY + "_" + b("ro.gn.sv.version");
    }

    public static String k() {
        return l() ? "eui_" + b(f4076j) + "_" + Build.DISPLAY : "";
    }

    public static boolean l() {
        return !TextUtils.isEmpty(b(f4076j));
    }

    private static boolean H() {
        return (!TextUtils.isEmpty(Build.BRAND) && Build.BRAND.toLowerCase().startsWith("huawei")) || (!TextUtils.isEmpty(Build.MANUFACTURER) && Build.MANUFACTURER.toLowerCase().startsWith("huawei"));
    }

    public static boolean m() {
        return (!TextUtils.isEmpty(Build.BRAND) && Build.BRAND.toLowerCase().startsWith("honor")) || (!TextUtils.isEmpty(Build.MANUFACTURER) && Build.MANUFACTURER.toLowerCase().startsWith("honor"));
    }

    public static boolean n() {
        try {
            return Class.forName("miui.os.Build").getName().length() > 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String o() {
        return n() ? "miui_" + b("ro.miui.ui.version.name") + "_" + Build.VERSION.INCREMENTAL : "";
    }

    public static boolean p() {
        String str = Build.BRAND + Build.MANUFACTURER;
        return !TextUtils.isEmpty(str) || str.toLowerCase().contains(k);
    }

    public static String q() {
        String a2 = a();
        return a2 != null ? (a2.toLowerCase().contains("emotionui") || a2.toLowerCase().contains(f4074h)) ? a2 + "_" + Build.DISPLAY : "" : "";
    }

    public static String r() {
        String str = Build.DISPLAY;
        return (str == null || !str.toLowerCase().contains("flyme")) ? "" : str;
    }

    public static boolean s() {
        return "oppo".equalsIgnoreCase(Build.MANUFACTURER) || "oppo".equalsIgnoreCase(Build.BRAND) || "realme".equalsIgnoreCase(Build.BRAND);
    }

    public static boolean t() {
        return Build.MANUFACTURER.equalsIgnoreCase("XIAOMI") || Build.BRAND.equalsIgnoreCase("XIAOMI") || Build.BRAND.equalsIgnoreCase("REDMI");
    }

    public static boolean u() {
        return "BlackShark".equalsIgnoreCase(Build.MANUFACTURER) || "BlackShark".equalsIgnoreCase(Build.BRAND);
    }

    public static String v() {
        return s() ? "coloros_" + b("ro.build.version.opporom") + "_" + Build.DISPLAY : "";
    }

    private static String b(String str) {
        return (String) u.a(str);
    }

    public static boolean w() {
        String str = Build.BRAND;
        if (str == null) {
            return false;
        }
        return str.toLowerCase(Locale.ENGLISH).contains("meizu");
    }

    public static boolean x() {
        return "OnePlus".equalsIgnoreCase(Build.MANUFACTURER) || "OnePlus".equalsIgnoreCase(Build.BRAND);
    }

    public static boolean y() {
        return com.bytedance.common.utility.e.ROM_SAMSUNG.equalsIgnoreCase(Build.BRAND) || com.bytedance.common.utility.e.ROM_SAMSUNG.equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean z() {
        return I().toUpperCase().contains("ZTE");
    }

    private static String I() {
        return Build.MANUFACTURER == null ? "" : Build.MANUFACTURER.trim();
    }

    public static boolean A() {
        String str = Build.FINGERPRINT;
        if (!TextUtils.isEmpty(str)) {
            return str.contains("VIBEUI_V2");
        }
        String b2 = b("ro.build.version.incremental");
        return !TextUtils.isEmpty(b2) && b2.contains("VIBEUI_V2");
    }

    public static boolean B() {
        return I().toUpperCase().contains("NUBIA");
    }

    public static boolean C() {
        return I().toUpperCase().contains("ASUS");
    }

    public static boolean D() {
        return "HUAWEI".equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean E() {
        return q.c(new Object[0]).booleanValue();
    }

    public static boolean a(Context context) {
        return context.getApplicationInfo().targetSdkVersion >= 30;
    }

    public static boolean F() {
        return Build.VERSION.SDK_INT >= 30 || (Build.VERSION.SDK_INT == 29 && Build.VERSION.PREVIEW_SDK_INT > 0);
    }

    public static boolean G() {
        return Build.VERSION.SDK_INT >= 29 || (Build.VERSION.SDK_INT == 28 && Build.VERSION.PREVIEW_SDK_INT > 0);
    }

    public static boolean b(Context context) {
        return context.getApplicationInfo().targetSdkVersion >= 29;
    }
}
