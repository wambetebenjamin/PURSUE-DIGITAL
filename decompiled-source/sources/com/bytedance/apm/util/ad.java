package com.bytedance.apm.util;

import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Locale;
/* compiled from: RomUtils.java */
/* loaded from: classes.dex */
public class ad {
    public static final String COLOROS = "coloros";
    public static final String EMUI = "emotionui";
    public static final String FLYME = "flyme";
    public static final String MIUI = "miui";
    public static final String RUNTIME_MIUI = "ro.miui.ui.version.name";
    public static final String RUNTIME_OPPO = "ro.build.version.opporom";
    public static final String SEPARATOR = "_";

    /* renamed from: a  reason: collision with root package name */
    private static final String f1658a = "eui";

    /* renamed from: b  reason: collision with root package name */
    private static final String f1659b = "ro.letv.release.version";

    /* renamed from: c  reason: collision with root package name */
    private static final CharSequence f1660c = "sony";

    /* renamed from: d  reason: collision with root package name */
    private static final CharSequence f1661d = "amigo";

    /* renamed from: e  reason: collision with root package name */
    private static final CharSequence f1662e = "funtouch";

    /* renamed from: f  reason: collision with root package name */
    private static final String f1663f = "ro.vivo.os.build.display.id";

    /* renamed from: g  reason: collision with root package name */
    private static final String f1664g = "ro.vivo.product.version";

    /* renamed from: h  reason: collision with root package name */
    private static final String f1665h = "ro.build.uiversion";

    /* renamed from: i  reason: collision with root package name */
    private static String f1666i = null;

    /* renamed from: j  reason: collision with root package name */
    private static Method f1667j = null;
    public static boolean sIsInited = false;
    public static boolean sIsMiui = false;

    private ad() {
    }

    public static String getRomInfo() {
        if (sIsInited && !TextUtils.isEmpty(f1666i)) {
            return f1666i;
        }
        String a2 = a();
        f1666i = a2;
        return a2;
    }

    private static String a() {
        if (isMiui()) {
            return getMIUIVersion();
        }
        if (isFlyme()) {
            return getFlymeVersion();
        }
        if (isColorOS()) {
            return getColorOsVersion();
        }
        String eMUVersion = getEMUVersion();
        if (TextUtils.isEmpty(eMUVersion)) {
            if (isFunTouchOS()) {
                return getFuntouchOSVersion();
            }
            if (isAmigo()) {
                return getAmigoVersion();
            }
            if (is360OS()) {
                return get360OSVersion();
            }
            String eUIVersion = getEUIVersion();
            if (TextUtils.isEmpty(eUIVersion)) {
                sIsInited = true;
                return Build.DISPLAY;
            }
            return eUIVersion;
        }
        return eMUVersion;
    }

    public static String get360OSVersion() {
        return a(f1665h) + "_" + Build.DISPLAY;
    }

    public static boolean is360OS() {
        String str = Build.MANUFACTURER + Build.BRAND;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.getDefault());
        return lowerCase.contains("360") || lowerCase.contains("qiku");
    }

    public static String getFuntouchOSVersion() {
        return a(f1663f) + "_" + a(f1664g);
    }

    public static boolean isFunTouchOS() {
        String a2 = a(f1663f);
        return !TextUtils.isEmpty(a2) && a2.toLowerCase(Locale.getDefault()).contains(f1662e);
    }

    public static boolean isAmigo() {
        return !TextUtils.isEmpty(Build.DISPLAY) && Build.DISPLAY.toLowerCase(Locale.getDefault()).contains(f1661d);
    }

    public static String getAmigoVersion() {
        return Build.DISPLAY + "_" + a("ro.gn.sv.version");
    }

    public static String getEUIVersion() {
        return isEUI() ? "eui_" + a(f1659b) + "_" + Build.DISPLAY : "";
    }

    public static boolean isEUI() {
        return !TextUtils.isEmpty(a(f1659b));
    }

    public static boolean isSony() {
        String str = Build.BRAND + Build.MANUFACTURER;
        return !TextUtils.isEmpty(str) || str.toLowerCase(Locale.getDefault()).contains(f1660c);
    }

    public static String getMIUIVersion() {
        return isMiui() ? "miui_" + a("ro.miui.ui.version.name") + "_" + Build.VERSION.INCREMENTAL : "";
    }

    public static String getEMUVersion() {
        String emuiInfo = getEmuiInfo();
        return (emuiInfo == null || !emuiInfo.toLowerCase(Locale.getDefault()).contains("emotionui")) ? "" : emuiInfo + "_" + Build.DISPLAY;
    }

    public static String getFlymeVersion() {
        String str = Build.DISPLAY;
        return (str == null || !str.toLowerCase(Locale.getDefault()).contains("flyme")) ? "" : str;
    }

    public static boolean isColorOS() {
        String str = Build.MANUFACTURER;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.toLowerCase(Locale.getDefault()).contains("oppo");
    }

    public static String getColorOsVersion() {
        return isColorOS() ? "coloros_" + a("ro.build.version.opporom") + "_" + Build.DISPLAY : "";
    }

    private static String a(String str) {
        String b2 = b(str);
        if (!TextUtils.isEmpty(b2)) {
            return b2;
        }
        String str2 = "";
        BufferedReader bufferedReader = null;
        try {
            Process exec = Runtime.getRuntime().exec("getprop " + str);
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getInputStream()), 1024);
            try {
                str2 = bufferedReader2.readLine();
                exec.destroy();
                com.bytedance.monitor.util.c.safeClose(bufferedReader2);
                return str2;
            } catch (Throwable unused) {
                bufferedReader = bufferedReader2;
                com.bytedance.monitor.util.c.safeClose(bufferedReader);
                return str2;
            }
        } catch (Throwable unused2) {
        }
    }

    private static String b(String str) {
        try {
            if (f1667j == null) {
                f1667j = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
            }
            return (String) f1667j.invoke(null, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static boolean isFlyme() {
        return Build.DISPLAY.contains("Flyme") || "flyme".equals(Build.USER);
    }

    public static boolean isHwDevice() {
        try {
            if (TextUtils.isEmpty(Build.BRAND) || !Build.BRAND.toLowerCase().startsWith("hua")) {
                if (TextUtils.isEmpty(Build.MANUFACTURER)) {
                    return false;
                }
                if (!Build.MANUFACTURER.toLowerCase().startsWith("hua")) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isEmui(String str) {
        if (TextUtils.isEmpty(str)) {
            str = getEmuiInfo();
        }
        return (!TextUtils.isEmpty(str) && str.toLowerCase().startsWith("emotionui")) || isHwDevice();
    }

    public static String getEmuiInfo() {
        if (Build.VERSION.SDK_INT >= 21) {
            return a("ro.build.version.emui");
        }
        return getEMUI();
    }

    public static String getEMUI() {
        try {
            Method declaredMethod = Build.class.getDeclaredMethod("getString", String.class);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, "ro.build.version.emui");
            if (invoke instanceof String) {
                return (String) invoke;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static boolean isMiui() {
        try {
            if (Class.forName("miui.os.Build") != null) {
                sIsMiui = true;
                return true;
            }
        } catch (Exception unused) {
        }
        return sIsMiui;
    }
}
