package com.bytedance.crash.util;

import android.os.Build;
import android.text.TextUtils;
import java.util.Locale;
/* compiled from: RomUtils.java */
@Deprecated
/* loaded from: classes2.dex */
public class ab {
    public static final String COLOROS = "coloros";
    public static final String EMUI = "emotionui";
    public static final String FLYME = "flyme";
    public static final String MIUI = "miui";
    public static final String RUNTIME_MIUI = "ro.miui.ui.version.name";
    public static final String RUNTIME_OPPO = "ro.build.version.opporom";
    public static final String SEPARATOR = "_";

    /* renamed from: a  reason: collision with root package name */
    private static final String f7275a = "eui";

    /* renamed from: b  reason: collision with root package name */
    private static final String f7276b = "ro.letv.release.version";

    /* renamed from: c  reason: collision with root package name */
    private static final CharSequence f7277c = "sony";

    /* renamed from: d  reason: collision with root package name */
    private static final CharSequence f7278d = "amigo";

    /* renamed from: e  reason: collision with root package name */
    private static final CharSequence f7279e = "funtouch";

    /* renamed from: f  reason: collision with root package name */
    private static final String f7280f = "ro.vivo.os.build.display.id";

    /* renamed from: g  reason: collision with root package name */
    private static final String f7281g = "ro.vivo.product.version";

    /* renamed from: h  reason: collision with root package name */
    private static final String f7282h = "ro.build.uiversion";

    /* renamed from: i  reason: collision with root package name */
    private static int f7283i = -1;

    private ab() {
    }

    public static String getRomInfo() {
        if (g.isMiui()) {
            return getMIUIVersion();
        }
        if (g.isFlyme()) {
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
            return !TextUtils.isEmpty(eUIVersion) ? eUIVersion : Build.DISPLAY;
        }
        return eMUVersion;
    }

    public static String get360OSVersion() {
        return getSystemProperty(f7282h) + "_" + Build.DISPLAY;
    }

    public static boolean isHarmonyOs() {
        int i2 = f7283i;
        if (i2 != -1) {
            return i2 == 1;
        }
        try {
            if (Class.forName("ohos.utils.system.SystemCapability") != null) {
                f7283i = 1;
            } else {
                f7283i = 0;
            }
        } catch (Throwable unused) {
            f7283i = 0;
        }
        return f7283i == 1;
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
        return getSystemProperty(f7280f) + "_" + getSystemProperty(f7281g);
    }

    public static boolean isFunTouchOS() {
        String systemProperty = getSystemProperty(f7280f);
        return !TextUtils.isEmpty(systemProperty) && systemProperty.toLowerCase(Locale.getDefault()).contains(f7279e);
    }

    public static boolean isAmigo() {
        return !TextUtils.isEmpty(Build.DISPLAY) && Build.DISPLAY.toLowerCase(Locale.getDefault()).contains(f7278d);
    }

    public static String getAmigoVersion() {
        return Build.DISPLAY + "_" + getSystemProperty("ro.gn.sv.version");
    }

    public static String getEUIVersion() {
        return isEUI() ? "eui_" + getSystemProperty(f7276b) + "_" + Build.DISPLAY : "";
    }

    public static boolean isEUI() {
        return !TextUtils.isEmpty(getSystemProperty(f7276b));
    }

    public static boolean isSony() {
        String str = Build.BRAND + Build.MANUFACTURER;
        return !TextUtils.isEmpty(str) || str.toLowerCase(Locale.getDefault()).contains(f7277c);
    }

    public static String getMIUIVersion() {
        return g.isMiui() ? "miui_" + getSystemProperty("ro.miui.ui.version.name") + "_" + Build.VERSION.INCREMENTAL : "";
    }

    public static String getEMUVersion() {
        String emuiInfo = g.getEmuiInfo();
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
        return isColorOS() ? "coloros_" + getSystemProperty("ro.build.version.opporom") + "_" + Build.DISPLAY : "";
    }

    public static String getSystemProperty(String str) {
        return com.bytedance.crash.general.c.a(str, "");
    }
}
