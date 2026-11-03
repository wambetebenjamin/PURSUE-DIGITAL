package com.bytedance.common.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.Proxy;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import com.bytedance.sdk.account.platform.base.i;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* compiled from: DeviceUtils.java */
/* loaded from: classes2.dex */
public final class e {
    public static final String ROM_EMUI = "EMUI";
    public static final String ROM_FLYME = "FLYME";
    public static final String ROM_GIONEE = "QIONEE";
    public static final String ROM_LENOVO = "LENOVO";
    public static final String ROM_MIUI = "MIUI";
    public static final String ROM_OPPO = "OPPO";
    public static final String ROM_QIKU = "QIKU";
    public static final String ROM_SAMSUNG = "samsung";
    public static final String ROM_SMARTISAN = "SMARTISAN";
    public static final String ROM_VIVO = "VIVO";
    public static final String ROM_ZTE = "zte";

    /* renamed from: a  reason: collision with root package name */
    static final String f5254a = "navigation_bar_height";

    /* renamed from: b  reason: collision with root package name */
    private static final int f5255b = 1024;

    /* renamed from: c  reason: collision with root package name */
    private static Boolean f5256c = null;

    /* renamed from: d  reason: collision with root package name */
    private static Boolean f5257d = null;
    public static boolean deviceDataHasInit = false;
    public static int displayMetricsHeightPixels = 0;
    public static int displayMetricsWidthPixels = 0;

    /* renamed from: e  reason: collision with root package name */
    private static final String f5258e = "DeviceUtils";

    /* renamed from: f  reason: collision with root package name */
    private static int f5259f = 0;

    /* renamed from: g  reason: collision with root package name */
    private static boolean f5260g = false;

    /* renamed from: h  reason: collision with root package name */
    private static boolean f5261h = false;

    /* renamed from: i  reason: collision with root package name */
    private static int f5262i = -1;

    /* renamed from: j  reason: collision with root package name */
    private static String f5263j = null;
    private static int k = -1;
    private static final String l = "ro.miui.ui.version.name";
    private static final String m = "ro.build.version.emui";
    private static final String n = "ro.build.version.opporom";
    private static final String o = "ro.smartisan.version";
    private static final String p = "ro.vivo.os.version";
    private static final String q = "ro.gn.sv.version";
    private static final String r = "ro.lenovo.lvp.version";
    private static String s = null;
    private static String t = null;
    private static final String u = "system_properties_cache";
    private static final Map<String, String> v = new HashMap();
    private static final String w = "force_fsg_nav_bar";
    private static final String x = "navigationbar_is_min";

    private e() {
    }

    public static boolean isEmui() {
        Boolean bool = f5256c;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean z = false;
        try {
            Method declaredMethod = Build.class.getDeclaredMethod("getString", String.class);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, m);
            if ((invoke instanceof String) && !o.isEmpty((String) invoke)) {
                if (!"unknown".equals((String) invoke)) {
                    z = true;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        f5256c = Boolean.valueOf(z);
        return z;
    }

    public static boolean isCoolpad() {
        Boolean bool = f5257d;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = Build.BRAND;
        if (!TextUtils.isEmpty(str)) {
            f5257d = Boolean.valueOf(str.toLowerCase(Locale.getDefault()).contains("coolpad"));
        } else {
            f5257d = false;
        }
        return f5257d.booleanValue();
    }

    public static boolean isWifiProxy(Context context) {
        int port;
        String str;
        if (Build.VERSION.SDK_INT >= 14) {
            str = System.getProperty("http.proxyHost");
            String property = System.getProperty("http.proxyPort");
            if (property == null) {
                property = i.a.ERROR_CODE_UNKNOW;
            }
            port = Integer.parseInt(property);
        } else {
            String host = Proxy.getHost(context);
            port = Proxy.getPort(context);
            str = host;
        }
        return (TextUtils.isEmpty(str) || port == -1) ? false : true;
    }

    public static boolean isInstallXposed() {
        try {
            throw new Exception("hook");
        } catch (Exception e2) {
            for (StackTraceElement stackTraceElement : e2.getStackTrace()) {
                if (stackTraceElement.getClassName().contains("de.robv.android.xposed")) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void setNoScreenCap(Activity activity) {
        activity.getWindow().setFlags(8192, 8192);
    }

    private static boolean a(String str) {
        return TextUtils.equals(str, "HWTAH") || TextUtils.equals(str, "unknownRLI") || TextUtils.equals(str, "unknownRHA") || TextUtils.equals(str, "HWTAH-C");
    }

    private static boolean a(Context context) {
        if (context != null) {
            try {
                if (context.getPackageManager() != null) {
                    return context.getPackageManager().hasSystemFeature("com.huawei.hardware.sensor.posture");
                }
                return false;
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private static boolean b(String str) {
        return TextUtils.equals(str, "winner") || TextUtils.equals(str, "zodiac");
    }

    public static boolean isFoldableScreen() {
        int i2 = f5262i;
        if (i2 > -1) {
            return i2 > 0;
        }
        f5262i = 0;
        if (isSamsung() && b(Build.DEVICE)) {
            f5262i = 1;
        } else if (isHuawei() && a(Build.DEVICE)) {
            f5262i = 1;
        }
        return f5262i > 0;
    }

    public static boolean isFoldableScreenV2(Context context) {
        int i2 = f5262i;
        if (i2 > -1) {
            return i2 > 0;
        }
        f5262i = 0;
        if (isSamsung() && b(Build.DEVICE)) {
            f5262i = 1;
        } else if (isHuawei() && (a(Build.DEVICE) || a(context))) {
            f5262i = 1;
        }
        return f5262i > 0;
    }

    public static boolean isMiui() {
        if (!f5261h) {
            try {
                if (Class.forName("miui.os.Build") != null) {
                    f5260g = true;
                }
            } catch (Exception unused) {
            }
            f5261h = true;
        }
        return f5260g;
    }

    public static void setMiuiStatusBarDarkMode(boolean z, Window window) {
        try {
            Class<?> cls = window.getClass();
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(z ? i2 : 0);
            objArr[1] = Integer.valueOf(i2);
            method.invoke(window, objArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static boolean isMiuiV6() {
        a();
        return "V6".equals(f5263j);
    }

    public static boolean isMiuiV7() {
        a();
        return "V7".equals(f5263j);
    }

    public static boolean isMiuiV8() {
        a();
        return "V8".equals(f5263j);
    }

    public static boolean isMiuiV9() {
        a();
        return "V9".equals(f5263j);
    }

    private static void a() {
        if (f5263j == null) {
            String systemProperty = com.bytedance.common.utility.device.c.getSystemProperty("ro.miui.ui.version.name");
            f5263j = systemProperty;
            if (systemProperty == null) {
                systemProperty = "";
            }
            f5263j = systemProperty;
        }
    }

    public static int getEmuiLevel() {
        int i2 = k;
        if (i2 > -1) {
            return i2;
        }
        try {
            k = Integer.parseInt(com.bytedance.common.utility.device.c.getSystemProperty("ro.build.hw_emui_api_level"));
        } catch (Exception unused) {
            k = 0;
        }
        return k;
    }

    public static boolean isHuawei() {
        return Build.MANUFACTURER != null && Build.MANUFACTURER.toUpperCase(Locale.getDefault()).contains("HUAWEI");
    }

    public static boolean isSumsungV4_4_4() {
        if (isSamsung()) {
            if (Build.VERSION.RELEASE.startsWith("4.4.4")) {
                return true;
            }
            return Build.VERSION.RELEASE.startsWith("4.4.2") && Build.DEVICE.startsWith("klte");
        }
        return false;
    }

    public static boolean isSumsungV5() {
        return isSamsung() && Build.VERSION.SDK_INT >= 21;
    }

    public static boolean isSumsungCorePrime() {
        return isSamsung() && Build.DISPLAY.contains("G3608ZMU1AOA4");
    }

    public static boolean isFlyme() {
        return Build.DISPLAY.startsWith("Flyme");
    }

    public static boolean isFlyme2() {
        return Build.DISPLAY.startsWith("Flyme 2");
    }

    public static boolean isFlyme4() {
        return Build.DISPLAY.startsWith("Flyme OS 4");
    }

    public static boolean isFlyme5() {
        return Build.DISPLAY.startsWith("Flyme 5");
    }

    public static boolean isFlyme6() {
        return Build.DISPLAY.startsWith("Flyme 6");
    }

    public static boolean isFlyme7() {
        return Build.DISPLAY.startsWith("Flyme 7");
    }

    public static boolean isOnePlusLOLLIPOP() {
        return Build.BRAND.equals("ONEPLUS") && Build.VERSION.SDK_INT >= 21;
    }

    public static boolean isSamsung() {
        return ROM_SAMSUNG.equalsIgnoreCase(Build.BRAND) || ROM_SAMSUNG.equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean isLG() {
        return ("lge".equalsIgnoreCase(Build.BRAND) || "lge".equalsIgnoreCase(Build.MANUFACTURER)) && Build.MODEL != null && Build.MODEL.toLowerCase(Locale.getDefault()).contains("lg");
    }

    public static boolean isMeizuMx3() {
        if (isMeizu()) {
            return "mx3".equalsIgnoreCase(Build.DEVICE);
        }
        return false;
    }

    public static boolean isHtcOs() {
        return Build.BRAND != null && Build.BRAND.toLowerCase(Locale.getDefault()).contains("htc") && Build.MANUFACTURER != null && Build.MANUFACTURER.toLowerCase(Locale.getDefault()).contains("htc") && Build.MODEL != null && Build.MODEL.toLowerCase(Locale.getDefault()).contains("htc");
    }

    public static boolean isMeizu() {
        String str = Build.BRAND;
        return str != null && str.toLowerCase(Locale.ENGLISH).indexOf("meizu") > -1;
    }

    public static boolean isVivo() {
        String str = Build.BRAND;
        if (TextUtils.isEmpty(str) || !str.toLowerCase(Locale.getDefault()).contains("vivo")) {
            String str2 = Build.MODEL;
            if (TextUtils.isEmpty(str2) || !str2.toLowerCase(Locale.getDefault()).contains("vivo")) {
                String str3 = Build.MANUFACTURER;
                return !TextUtils.isEmpty(str3) && str3.toLowerCase(Locale.getDefault()).contains("vivo");
            }
            return true;
        }
        return true;
    }

    public static boolean isOppo() {
        return check(ROM_OPPO);
    }

    public static boolean isQiku() {
        return check(ROM_QIKU) || check("360");
    }

    public static boolean isSmartisan() {
        return check(ROM_SMARTISAN);
    }

    public static String getName() {
        if (s == null) {
            check("");
        }
        return s;
    }

    public static String getVersion() {
        if (t == null) {
            check("");
        }
        return t;
    }

    public static boolean check(String str) {
        String str2 = s;
        if (str2 != null) {
            return str2.equals(str);
        }
        String prop = getProp("ro.miui.ui.version.name");
        t = prop;
        if (!TextUtils.isEmpty(prop)) {
            s = ROM_MIUI;
        } else {
            String prop2 = getProp(m);
            t = prop2;
            if (!TextUtils.isEmpty(prop2)) {
                s = ROM_EMUI;
            } else {
                String prop3 = getProp("ro.build.version.opporom");
                t = prop3;
                if (!TextUtils.isEmpty(prop3)) {
                    s = ROM_OPPO;
                } else {
                    String prop4 = getProp(p);
                    t = prop4;
                    if (!TextUtils.isEmpty(prop4)) {
                        s = ROM_VIVO;
                    } else {
                        String prop5 = getProp(o);
                        t = prop5;
                        if (!TextUtils.isEmpty(prop5)) {
                            s = ROM_SMARTISAN;
                        } else {
                            String prop6 = getProp(q);
                            t = prop6;
                            if (!TextUtils.isEmpty(prop6)) {
                                s = ROM_GIONEE;
                            } else {
                                String prop7 = getProp(r);
                                t = prop7;
                                if (!TextUtils.isEmpty(prop7)) {
                                    s = ROM_LENOVO;
                                } else if (getManufacturer().toLowerCase(Locale.getDefault()).contains(ROM_SAMSUNG)) {
                                    s = ROM_SAMSUNG;
                                } else if (getManufacturer().toLowerCase(Locale.getDefault()).contains(ROM_ZTE)) {
                                    s = ROM_ZTE;
                                } else {
                                    String str3 = Build.DISPLAY;
                                    t = str3;
                                    if (str3.toUpperCase(Locale.getDefault()).contains(ROM_FLYME)) {
                                        s = ROM_FLYME;
                                    } else {
                                        t = "unknown";
                                        s = Build.MANUFACTURER.toUpperCase(Locale.getDefault());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return s.equals(str);
    }

    public static String getProp(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream(), "UTF-8"), 1024);
        } catch (IOException unused) {
            bufferedReader = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            try {
                bufferedReader.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return readLine;
        } catch (IOException unused2) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER == null ? "" : Build.MANUFACTURER.trim();
    }

    public static String getSystemPropertyInSp(Context context, String str) {
        String str2;
        Map<String, String> map = v;
        synchronized (map) {
            str2 = map.get(str);
            if (o.isEmpty(str2)) {
                try {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(u, 0);
                    str2 = sharedPreferences.getString(str, null);
                    if (o.isEmpty(str2)) {
                        str2 = getSystemProperty(str);
                        if (!o.isEmpty(str2)) {
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.putString(str, str2);
                            edit.apply();
                        }
                    }
                } catch (Exception e2) {
                    Logger.e(f5258e, "getSystemProperties in sp error", e2);
                }
            }
        }
        return str2;
    }

    public static String getSystemProperty(String str) {
        return com.bytedance.common.utility.device.c.getSystemProperty(str);
    }

    public static double getEmuiVersion() {
        try {
            String systemProperty = getSystemProperty(m);
            return Double.parseDouble(systemProperty.substring(systemProperty.indexOf("_") + 1));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 4.0d;
        }
    }

    public static boolean hasSmartBar() {
        if (isMeizu()) {
            try {
                return ((Boolean) Class.forName("android.os.Build").getMethod("hasSmartBar", new Class[0]).invoke(null, new Object[0])).booleanValue();
            } catch (Exception e2) {
                e2.printStackTrace();
                return (Build.DEVICE.equals("mx") || Build.DEVICE.equals("m9")) ? false : true;
            }
        }
        return false;
    }

    public static boolean hasVirtualButtons(Context context) {
        if (Build.VERSION.SDK_INT >= 14) {
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
        return false;
    }

    public static boolean isLenovo() {
        return "lenovo".equalsIgnoreCase(Build.BRAND) || "lenovo".equalsIgnoreCase(Build.MANUFACTURER) || "motorola".equalsIgnoreCase(Build.BRAND) || "motorola".equalsIgnoreCase(Build.MANUFACTURER);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long getTotalMemory() {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L33
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L33
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L33
            r3 = 8192(0x2000, float:1.148E-41)
            r0.<init>(r2, r3)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L33
            java.lang.String r1 = r0.readLine()     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L47
            java.lang.String r2 = "\\s+"
            java.lang.String[] r1 = r1.split(r2)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L47
            r2 = 1
            r1 = r1[r2]     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L47
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L47
            int r1 = r1 * 1024
            long r1 = (long) r1
            r0.close()     // Catch: java.io.IOException -> L27
            goto L46
        L27:
            r0 = move-exception
            r0.printStackTrace()
            goto L46
        L2c:
            r1 = move-exception
            goto L37
        L2e:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L48
        L33:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L37:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L47
            if (r0 == 0) goto L44
            r0.close()     // Catch: java.io.IOException -> L40
            goto L44
        L40:
            r0 = move-exception
            r0.printStackTrace()
        L44:
            r1 = 0
        L46:
            return r1
        L47:
            r1 = move-exception
        L48:
            if (r0 == 0) goto L52
            r0.close()     // Catch: java.io.IOException -> L4e
            goto L52
        L4e:
            r0 = move-exception
            r0.printStackTrace()
        L52:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.utility.e.getTotalMemory():long");
    }

    public static int getStatusBarHeight(Context context) {
        int i2 = f5259f;
        if (i2 > 0) {
            return i2;
        }
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = identifier > 0 ? context.getResources().getDimensionPixelSize(identifier) : 0;
        if (dimensionPixelSize == 0) {
            dimensionPixelSize = (int) p.dip2Px(context, 25.0f);
        }
        f5259f = dimensionPixelSize;
        return dimensionPixelSize;
    }

    public static void initDeviceData(Context context) {
        if (context == null) {
            return;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int orientation = windowManager.getDefaultDisplay().getOrientation();
        if (orientation == 1 || orientation == 3) {
            displayMetricsWidthPixels = displayMetrics.heightPixels;
            displayMetricsHeightPixels = displayMetrics.widthPixels;
        } else {
            displayMetricsWidthPixels = displayMetrics.widthPixels;
            displayMetricsHeightPixels = displayMetrics.heightPixels;
        }
        deviceDataHasInit = true;
    }

    public static int getEquipmentWidth(Context context) {
        if (!deviceDataHasInit || isFoldableScreenV2(context)) {
            initDeviceData(context);
        }
        return displayMetricsWidthPixels;
    }

    public static int getEquipmentHeight(Context context) {
        if (!deviceDataHasInit || isFoldableScreenV2(context)) {
            initDeviceData(context);
        }
        return displayMetricsHeightPixels;
    }

    public static boolean isLargeScreenPad(Context context, int i2, int i3) {
        if (isFoldableScreenV2(context)) {
            return false;
        }
        Resources resources = context.getResources();
        return i2 >= resources.getDimensionPixelSize(R.dimen.mohist_utility_large_pad_min_width) && i3 >= resources.getDimensionPixelSize(R.dimen.mohist_utility_large_pad_min_height);
    }

    public static boolean isLargeScreenPad(Display display, Context context) {
        Point point = new Point();
        com.bytedance.common.util.b.getDisplaySize(display, point);
        return isLargeScreenPad(context, point.x, point.y);
    }

    public static boolean isPad(Context context) {
        if (context == null || context.getResources() == null || context.getResources().getConfiguration() == null) {
            return false;
        }
        return "tablet".equals(b(context));
    }

    public static boolean isHuawei_Os_3x() {
        if (isHuawei()) {
            String systemProperty = getSystemProperty(m);
            return "EmotionUI 3".equals(systemProperty) || "EmotionUI_3.1".contains(systemProperty) || "EmotionUI_3.0".contains(systemProperty);
        }
        return false;
    }

    public static int getNavigationBarHeight(Context context) {
        if (Build.VERSION.SDK_INT < 14 || !hasNavBar((Activity) context)) {
            return 0;
        }
        return getInternalDimensionSize(context, f5254a);
    }

    public static boolean hasNavBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17) {
            if (Settings.Global.getInt(activity.getContentResolver(), w, 0) != 0) {
                return false;
            }
            if (isEmui()) {
                if (isHuawei_Os_3x() || Build.VERSION.SDK_INT < 21) {
                    if (Settings.System.getInt(activity.getContentResolver(), x, 0) != 0) {
                        return false;
                    }
                } else if (Settings.Global.getInt(activity.getContentResolver(), x, 0) != 0) {
                    return false;
                }
            }
        }
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealMetrics(displayMetrics);
        }
        int i2 = displayMetrics.heightPixels;
        int i3 = displayMetrics.widthPixels;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics2);
        return i3 - displayMetrics2.widthPixels > 0 || i2 - displayMetrics2.heightPixels > 0;
    }

    public static int getInternalDimensionSize(Context context, String str) {
        try {
            int identifier = Resources.getSystem().getIdentifier(str, "dimen", "android");
            if (identifier > 0) {
                int dimensionPixelSize = context.getResources().getDimensionPixelSize(identifier);
                int dimensionPixelSize2 = Resources.getSystem().getDimensionPixelSize(identifier);
                if (dimensionPixelSize2 >= dimensionPixelSize) {
                    return dimensionPixelSize2;
                }
                return Math.round((dimensionPixelSize * Resources.getSystem().getDisplayMetrics().density) / context.getResources().getDisplayMetrics().density);
            }
        } catch (Resources.NotFoundException unused) {
        }
        return 0;
    }

    private static String b(Context context) {
        String systemPropertyInSp = getSystemPropertyInSp(context, "ro.build.characteristics");
        return (TextUtils.isEmpty(systemPropertyInSp) || !systemPropertyInSp.equals("tablet")) ? "phone" : systemPropertyInSp;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int orientation = windowManager.getDefaultDisplay().getOrientation();
        if (orientation == 1 || orientation == 3) {
            return displayMetrics.heightPixels;
        }
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int orientation = windowManager.getDefaultDisplay().getOrientation();
        if (orientation == 1 || orientation == 3) {
            return displayMetrics.widthPixels;
        }
        return displayMetrics.heightPixels;
    }
}
