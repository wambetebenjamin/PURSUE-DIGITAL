package com.ss.android.deviceregister.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.bdinstall.util.u;
import com.bytedance.common.utility.o;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
/* compiled from: RomUtils.java */
/* loaded from: classes9.dex */
public class e {
    public static final String COLOROS = "coloros";
    public static final String EMUI = "emotionui";
    public static final String FLYME = "flyme";
    public static final String MIUI = "miui";
    public static final String RUNTIME_MIUI = "ro.miui.ui.version.name";
    public static final String RUNTIME_OPPO = "ro.build.version.opporom";
    public static final String SEPARATOR = "_";

    /* renamed from: a  reason: collision with root package name */
    private static final String f18275a = "magicui";

    /* renamed from: b  reason: collision with root package name */
    private static final String f18276b = "eui";

    /* renamed from: c  reason: collision with root package name */
    private static final String f18277c = "ro.letv.release.version";

    /* renamed from: g  reason: collision with root package name */
    private static final String f18281g = "ro.vivo.os.build.display.id";

    /* renamed from: h  reason: collision with root package name */
    private static final String f18282h = "ro.vivo.product.version";

    /* renamed from: i  reason: collision with root package name */
    private static final String f18283i = "ro.build.uiversion";

    /* renamed from: j  reason: collision with root package name */
    private static volatile Boolean f18284j = null;
    private static final String k = "harmony";

    /* renamed from: d  reason: collision with root package name */
    private static final CharSequence f18278d = "sony";

    /* renamed from: e  reason: collision with root package name */
    private static final CharSequence f18279e = "amigo";

    /* renamed from: f  reason: collision with root package name */
    private static final CharSequence f18280f = "funtouch";
    private static final f<Boolean> l = new f<Boolean>() { // from class: com.ss.android.deviceregister.utils.e.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.ss.android.deviceregister.utils.f
        /* renamed from: a */
        public Boolean b(Object... objArr) {
            try {
                Class<?> cls = Class.forName("com.huawei.system.BuildEx");
                return Boolean.valueOf(e.k.equals(cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0])));
            } catch (Throwable unused) {
                return false;
            }
        }
    };

    public static boolean isGmsInstalled(Context context) {
        return false;
    }

    public static String getRomInfo() {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (com.ss.android.common.util.d.isMiui()) {
            return getMIUIVersion();
        }
        if (com.ss.android.common.util.d.isFlyme()) {
            return getFlymeVersion();
        }
        if (isColorOS()) {
            return getColorOsVersion();
        }
        String eMUVersion = getEMUVersion();
        if (TextUtils.isEmpty(eMUVersion)) {
            if (a()) {
                return getFuntouchOSVersion();
            }
            if (isAmigo()) {
                return getAmigoVersion();
            }
            if (is360OS()) {
                return get360OSVersion();
            }
            String eUIVersion = getEUIVersion();
            if (!TextUtils.isEmpty(eUIVersion)) {
                return eUIVersion;
            }
            return Build.DISPLAY;
        }
        return eMUVersion;
    }

    private static boolean a() {
        try {
            return !TextUtils.isEmpty((String) u.a(f18281g));
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String get360OSVersion() {
        return a(f18283i) + "_" + Build.DISPLAY;
    }

    public static boolean is360OS() {
        String str = Build.MANUFACTURER + Build.BRAND;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.contains("360") || lowerCase.contains("qiku");
    }

    public static String getFuntouchOSVersion() {
        return u.a(f18281g) + "_" + u.a(f18282h);
    }

    @Deprecated
    public static boolean isFunTouchOS() {
        String a2 = a(f18281g);
        return !o.isEmpty(a2) && a2.toLowerCase().contains(f18280f);
    }

    public static boolean isAmigo() {
        return !o.isEmpty(Build.DISPLAY) && Build.DISPLAY.toLowerCase().contains(f18279e);
    }

    public static boolean isHwOrHonor(String str) {
        if (TextUtils.isEmpty(str)) {
            str = com.ss.android.common.util.d.getEmuiInfo();
        }
        return (!TextUtils.isEmpty(str) && (str.toLowerCase().contains("emotionui") || str.toLowerCase().contains(f18275a))) || isHuaweiDevice() || isHonorDevice();
    }

    public static String getAmigoVersion() {
        return Build.DISPLAY + "_" + a("ro.gn.sv.version");
    }

    public static String getEUIVersion() {
        return isEUI() ? "eui_" + a(f18277c) + "_" + Build.DISPLAY : "";
    }

    public static boolean isEUI() {
        return !o.isEmpty(a(f18277c));
    }

    public static boolean isHuaweiDevice() {
        return (!TextUtils.isEmpty(Build.BRAND) && Build.BRAND.toLowerCase().startsWith("huawei")) || (!TextUtils.isEmpty(Build.MANUFACTURER) && Build.MANUFACTURER.toLowerCase().startsWith("huawei"));
    }

    public static boolean isHonorDevice() {
        return (!TextUtils.isEmpty(Build.BRAND) && Build.BRAND.toLowerCase().startsWith("honor")) || (!TextUtils.isEmpty(Build.MANUFACTURER) && Build.MANUFACTURER.toLowerCase().startsWith("honor")) || "HONOR".equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean c() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getDeclaredMethod("get", String.class).invoke(cls, "ro.build.version.emui");
            if (!TextUtils.isEmpty(str)) {
                com.ss.android.common.util.b.d("Honor# oldHonor device, version is" + str);
                return true;
            }
        } catch (Exception e2) {
            com.ss.android.common.util.b.e("Honor# " + e2.getMessage(), e2);
        }
        return false;
    }

    public static boolean isSony() {
        String str = Build.BRAND + Build.MANUFACTURER;
        return !o.isEmpty(str) || str.toLowerCase().contains(f18278d);
    }

    public static String getMIUIVersion() {
        return com.ss.android.common.util.d.isMiui() ? "miui_" + a("ro.miui.ui.version.name") + "_" + Build.VERSION.INCREMENTAL : "";
    }

    public static String getEMUVersion() {
        String emuiInfo = com.ss.android.common.util.d.getEmuiInfo();
        return emuiInfo != null ? (emuiInfo.toLowerCase().contains("emotionui") || emuiInfo.toLowerCase().contains(f18275a)) ? emuiInfo + "_" + Build.DISPLAY : "" : "";
    }

    public static String getFlymeVersion() {
        String str = Build.DISPLAY;
        return (str == null || !str.toLowerCase().contains("flyme")) ? "" : str;
    }

    public static boolean isColorOS() {
        String str = Build.MANUFACTURER;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.toLowerCase().contains("oppo");
    }

    public static String getColorOsVersion() {
        return isColorOS() ? "coloros_" + a("ro.build.version.opporom") + "_" + Build.DISPLAY : "";
    }

    private static String a(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        String str2 = "";
        try {
            Process exec = Runtime.getRuntime().exec("getprop " + str);
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()), 1024);
            try {
                str2 = bufferedReader.readLine();
                exec.destroy();
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                    com.ss.android.common.util.b.e("Exception while closing InputStream", e2);
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
                try {
                    com.ss.android.common.util.b.e("Unable to read sysprop " + str, th);
                    return str2;
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            com.ss.android.common.util.b.e("Exception while closing InputStream", e3);
                        }
                    }
                }
            }
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
        }
    }

    public static boolean isMeizu() {
        String str = Build.BRAND;
        if (str == null) {
            return false;
        }
        return str.toLowerCase(Locale.ENGLISH).contains("meizu");
    }

    public static boolean isOnePlus() {
        return "OnePlus".equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean isSamsung() {
        return com.bytedance.common.utility.e.ROM_SAMSUNG.equalsIgnoreCase(Build.BRAND) || com.bytedance.common.utility.e.ROM_SAMSUNG.equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean isZTE() {
        return b().toUpperCase().contains("ZTE");
    }

    private static String b() {
        return Build.MANUFACTURER == null ? "" : Build.MANUFACTURER.trim();
    }

    public static boolean isLenovo() {
        String str = Build.FINGERPRINT;
        if (!TextUtils.isEmpty(str)) {
            return str.contains("VIBEUI_V2");
        }
        String a2 = a("ro.build.version.incremental");
        return !TextUtils.isEmpty(a2) && a2.contains("VIBEUI_V2");
    }

    public static boolean isNubia() {
        return b().toUpperCase().contains("NUBIA");
    }

    public static boolean isASUS() {
        return b().toUpperCase().contains("ASUS");
    }

    public static boolean isHarmonyUI() {
        return l.get(new Object[0]).booleanValue();
    }

    public static boolean isTargetROrHigher(Context context) {
        return context.getApplicationInfo().targetSdkVersion >= 30;
    }

    public static boolean isAndroidROrHigher() {
        return Build.VERSION.SDK_INT >= 30 || (Build.VERSION.SDK_INT == 29 && Build.VERSION.PREVIEW_SDK_INT > 0);
    }

    public static boolean isAndroidQOrHigher() {
        return Build.VERSION.SDK_INT >= 29 || (Build.VERSION.SDK_INT == 28 && Build.VERSION.PREVIEW_SDK_INT > 0);
    }

    public static boolean isTargetQOrHigher(Context context) {
        return context.getApplicationInfo().targetSdkVersion >= 29;
    }
}
