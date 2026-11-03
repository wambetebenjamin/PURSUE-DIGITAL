package com.bytedance.frameworks.gpm.util;

import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;
/* compiled from: ROMModel.java */
/* loaded from: classes2.dex */
class g {

    /* renamed from: a  reason: collision with root package name */
    private final String f8558a = "_";

    /* renamed from: b  reason: collision with root package name */
    private final String f8559b = "miui";

    /* renamed from: c  reason: collision with root package name */
    private final String f8560c = "emotionui";

    /* renamed from: d  reason: collision with root package name */
    private final String f8561d = "flyme";

    /* renamed from: e  reason: collision with root package name */
    private final String f8562e = "coloros";

    /* renamed from: f  reason: collision with root package name */
    private final String f8563f = "ro.miui.ui.version.name";

    /* renamed from: g  reason: collision with root package name */
    private final String f8564g = "ro.build.version.opporom";

    /* renamed from: h  reason: collision with root package name */
    private final String f8565h = "eui";

    /* renamed from: i  reason: collision with root package name */
    private final String f8566i = "ro.letv.release.version";

    /* renamed from: j  reason: collision with root package name */
    private final CharSequence f8567j = "sony";
    private final CharSequence k = "amigo";
    private final CharSequence l = "funtouch";
    private final String m = "ro.vivo.os.build.display.id";
    private final String n = "ro.vivo.product.version";
    private final String o = "ro.build.uiversion";
    private boolean p;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g() {
        this.p = false;
        try {
            Class.forName("miui.os.Build");
            this.p = true;
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a() {
        if (b()) {
            return c();
        }
        if (d()) {
            return e();
        }
        if (q()) {
            return r();
        }
        String f2 = f();
        if (TextUtils.isEmpty(f2)) {
            if (j()) {
                return k();
            }
            if (l()) {
                return m();
            }
            if (h()) {
                return i();
            }
            String o = o();
            return !TextUtils.isEmpty(o) ? o : Build.DISPLAY;
        }
        return f2;
    }

    private boolean b() {
        return this.p;
    }

    private String c() {
        return b() ? "miui_" + a("ro.miui.ui.version.name") + "_" + Build.VERSION.INCREMENTAL : "";
    }

    private boolean d() {
        return Build.DISPLAY.contains("Flyme") || Build.USER.equals("flyme");
    }

    private String e() {
        return Build.DISPLAY;
    }

    private String f() {
        String g2 = g();
        return g2.toLowerCase(Locale.getDefault()).contains("emotionui") ? g2 + "_" + Build.DISPLAY : "";
    }

    private String g() {
        return a("ro.build.version.emui");
    }

    private boolean h() {
        String lowerCase = (Build.MANUFACTURER + Build.BRAND).toLowerCase(Locale.getDefault());
        return lowerCase.contains("360") || lowerCase.contains("qiku");
    }

    private String i() {
        return a("ro.build.uiversion") + "_" + Build.DISPLAY;
    }

    private boolean j() {
        return a("ro.vivo.os.build.display.id").toLowerCase(Locale.getDefault()).contains(this.l);
    }

    private String k() {
        return a("ro.vivo.os.build.display.id") + "_" + a("ro.vivo.product.version");
    }

    private boolean l() {
        return Build.DISPLAY.toLowerCase(Locale.getDefault()).contains(this.k);
    }

    private String m() {
        return Build.DISPLAY + "_" + a("ro.gn.sv.version");
    }

    private boolean n() {
        return !TextUtils.isEmpty(a("ro.letv.release.version"));
    }

    private String o() {
        return n() ? "eui_" + a("ro.letv.release.version") + "_" + Build.DISPLAY : "";
    }

    private boolean p() {
        return (Build.BRAND + Build.MANUFACTURER).toLowerCase(Locale.getDefault()).contains(this.f8567j);
    }

    private boolean q() {
        return Build.MANUFACTURER.toLowerCase(Locale.getDefault()).contains("oppo");
    }

    private String r() {
        return q() ? "coloros_" + a("ro.build.version.opporom") + "_" + Build.DISPLAY : "";
    }

    private String a(String str) {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        Throwable th;
        Process process;
        Process process2 = null;
        try {
            process = Runtime.getRuntime().exec("getprop " + str);
            try {
                inputStreamReader = new InputStreamReader(process.getInputStream());
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                    try {
                        String readLine = bufferedReader.readLine();
                        if (process != null) {
                            process.destroy();
                        }
                        c.a(bufferedReader);
                        c.a(inputStreamReader);
                        return readLine;
                    } catch (Exception unused) {
                        process2 = process;
                        if (process2 != null) {
                            process2.destroy();
                        }
                        c.a(bufferedReader);
                        c.a(inputStreamReader);
                        return "";
                    } catch (Throwable th2) {
                        th = th2;
                        if (process != null) {
                            process.destroy();
                        }
                        c.a(bufferedReader);
                        c.a(inputStreamReader);
                        throw th;
                    }
                } catch (Exception unused2) {
                    bufferedReader = null;
                } catch (Throwable th3) {
                    bufferedReader = null;
                    th = th3;
                }
            } catch (Exception unused3) {
                inputStreamReader = null;
                bufferedReader = null;
            } catch (Throwable th4) {
                bufferedReader = null;
                th = th4;
                inputStreamReader = null;
            }
        } catch (Exception unused4) {
            inputStreamReader = null;
            bufferedReader = null;
        } catch (Throwable th5) {
            inputStreamReader = null;
            bufferedReader = null;
            th = th5;
            process = null;
        }
    }
}
