package gsdk.impl.webview.DEFAULT;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.DisplayCutout;
import android.view.Window;
import android.view.WindowManager;
/* compiled from: ConcaveScreenUtils.java */
/* loaded from: classes10.dex */
public class bp {

    /* renamed from: a  reason: collision with root package name */
    public static final int f20997a = 27;

    /* renamed from: b  reason: collision with root package name */
    private static final int f20998b = 28;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f20999c = false;

    /* renamed from: d  reason: collision with root package name */
    private static int f21000d = -1;

    /* renamed from: e  reason: collision with root package name */
    private static float f21001e = 0.0f;

    /* renamed from: f  reason: collision with root package name */
    private static final int f21002f = -1;

    /* renamed from: g  reason: collision with root package name */
    private static final int f21003g = 256;

    /* renamed from: h  reason: collision with root package name */
    private static final int f21004h = 512;

    /* renamed from: i  reason: collision with root package name */
    private static final int f21005i = 1024;

    /* renamed from: j  reason: collision with root package name */
    private static final String f21006j = "display_notch_status";
    private static final int k = 32;

    /* JADX WARN: Type inference failed for: r0v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v8, types: [boolean] */
    public static int a(Context context) {
        if (context != null && !f20999c) {
            String str = Build.BRAND;
            f21001e = bt.f(context);
            if (str.equalsIgnoreCase("Honor") || str.equalsIgnoreCase("huawei")) {
                ?? k2 = k(context);
                f21000d = k2 == true ? 1 : 0;
                if (k2 > 0) {
                    f21001e = l(context);
                }
                f20999c = true;
            } else if (str.equalsIgnoreCase("Xiaomi")) {
                ?? a2 = a();
                f21000d = a2 == true ? 1 : 0;
                if (a2 > 0) {
                    f21001e = e(context);
                }
                f20999c = true;
            } else {
                if (str.equalsIgnoreCase(com.bytedance.common.utility.e.ROM_OPPO)) {
                    f21000d = b(context, context.getResources().getConfiguration().orientation == 1) ? 1 : 0;
                    f20999c = true;
                } else if (str.equalsIgnoreCase("vivo")) {
                    f21000d = d(context) ? 1 : 0;
                    f20999c = true;
                } else if (str.equalsIgnoreCase(com.bytedance.common.utility.e.ROM_SAMSUNG)) {
                    f21000d = g(context) ? 1 : 0;
                    f20999c = true;
                } else if (str.equalsIgnoreCase("oneplus")) {
                    f20999c = r(context) != -1;
                } else if (str.equalsIgnoreCase(com.bytedance.common.utility.e.ROM_ZTE)) {
                    f21000d = i(context) ? 1 : 0;
                    f20999c = true;
                } else if (str.equalsIgnoreCase("Meizu")) {
                    f21000d = q(context) ? 1 : 0;
                    f20999c = true;
                } else if (str.equalsIgnoreCase("Lenovo")) {
                    f21000d = p(context) ? 1 : 0;
                    f20999c = true;
                } else {
                    f20999c = true;
                }
            }
        }
        return f21000d;
    }

    public static void a(int i2, boolean z) {
        if (f20999c || !z) {
            return;
        }
        f21000d = 1;
        f21001e = i2;
        f20999c = true;
    }

    private static float a(Context context, float f2) {
        return f2 / context.getResources().getDisplayMetrics().density;
    }

    public static float b(Context context) {
        if (context != null && f20999c) {
            return a(context, f21001e);
        }
        if (context != null && !f20999c) {
            return a(context, bt.f(context));
        }
        Log.e("ConcaveScreenUtils", "context is null");
        return 0.0f;
    }

    public static float c(Context context) {
        if (context != null && f20999c) {
            return f21001e;
        }
        if (context != null && !f20999c) {
            return bt.f(context);
        }
        Log.e("ConcaveScreenUtils", "context is null");
        return 0.0f;
    }

    @Deprecated
    public static boolean d(Context context) {
        return b() || h(context);
    }

    @Deprecated
    public static boolean a() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((Integer) cls.getMethod("getInt", String.class, Integer.TYPE).invoke(cls, "ro.miui.notch", 0)).intValue() == 1;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Deprecated
    public static int e(Context context) {
        int identifier;
        if (!a() || (identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android")) <= 0) {
            return 0;
        }
        return context.getResources().getDimensionPixelSize(identifier);
    }

    @Deprecated
    public static boolean a(Context context, boolean z) {
        return b() || b(context, z);
    }

    @Deprecated
    public static boolean f(Context context) {
        return b() || k(context);
    }

    @Deprecated
    public static boolean g(Context context) {
        try {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_mainBuiltInDisplayCutout", "string", "android");
            String string = identifier > 0 ? resources.getString(identifier) : null;
            if (string != null) {
                return !TextUtils.isEmpty(string);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @Deprecated
    public static boolean b() {
        return c() || d();
    }

    @Deprecated
    public static boolean c() {
        try {
            Class<?> cls = Class.forName("android.util.FtFeature");
            return ((Boolean) cls.getDeclaredMethod("isFeatureSupport", Integer.TYPE).invoke(cls, 32)).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    @Deprecated
    public static boolean d() {
        try {
            Class<?> cls = Class.forName("android.util.FtFeature");
            return ((Boolean) cls.getDeclaredMethod("isFeatureSupport", Integer.TYPE).invoke(cls, "vivo.hardware.holescreen")).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    @Deprecated
    public static boolean h(Context context) {
        return b(context, true);
    }

    @Deprecated
    public static boolean b(Context context, boolean z) {
        try {
            return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
        } catch (Throwable unused) {
            return false;
        }
    }

    @Deprecated
    public static boolean i(Context context) {
        try {
            return context.getPackageManager().hasSystemFeature("com.zte.hardware.display.camera_notch");
        } catch (Throwable th) {
            try {
                th.printStackTrace();
            } catch (Throwable unused) {
            }
            return false;
        }
    }

    @Deprecated
    public static boolean j(Context context) {
        return "oneplus".equalsIgnoreCase(Build.BRAND) && r(context) > 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x006c, code lost:
        if (r0.endsWith("A6010") == false) goto L32;
     */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int r(android.content.Context r6) {
        /*
            java.lang.String r0 = android.os.Build.MODEL
            r1 = -1
            java.lang.String r2 = "GM190"
            boolean r2 = r0.startsWith(r2)     // Catch: java.lang.Throwable -> L72
            r3 = 0
            r4 = 1
            if (r2 != 0) goto L71
            java.lang.String r2 = "HD190"
            boolean r2 = r0.startsWith(r2)     // Catch: java.lang.Throwable -> L72
            if (r2 == 0) goto L16
            goto L71
        L16:
            java.lang.String r2 = "GM191"
            boolean r2 = r0.startsWith(r2)     // Catch: java.lang.Throwable -> L72
            if (r2 != 0) goto L6f
            java.lang.String r2 = "HD191"
            boolean r2 = r0.startsWith(r2)     // Catch: java.lang.Throwable -> L72
            if (r2 != 0) goto L6f
            java.lang.String r2 = "GM192"
            boolean r2 = r0.startsWith(r2)     // Catch: java.lang.Throwable -> L72
            if (r2 != 0) goto L6f
            java.lang.String r2 = "HD192"
            boolean r2 = r0.startsWith(r2)     // Catch: java.lang.Throwable -> L72
            if (r2 == 0) goto L37
            goto L6f
        L37:
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L72
            r5 = 28
            if (r2 < r5) goto L5e
            android.app.Activity r6 = u(r6)     // Catch: java.lang.Throwable -> L72
            if (r6 == 0) goto L72
            android.view.Window r6 = r6.getWindow()     // Catch: java.lang.Throwable -> L72
            if (r6 == 0) goto L72
            android.view.View r6 = r6.getDecorView()     // Catch: java.lang.Throwable -> L72
            android.view.WindowInsets r6 = r6.getRootWindowInsets()     // Catch: java.lang.Throwable -> L72
            android.view.DisplayCutout r6 = r6.getDisplayCutout()     // Catch: java.lang.Throwable -> L72
            java.util.List r6 = r6.getBoundingRects()     // Catch: java.lang.Throwable -> L72
            int r1 = r6.size()     // Catch: java.lang.Throwable -> L72
            goto L72
        L5e:
            java.lang.String r6 = "A6000"
            boolean r6 = r0.endsWith(r6)     // Catch: java.lang.Throwable -> L72
            if (r6 != 0) goto L71
            java.lang.String r6 = "A6010"
            boolean r6 = r0.endsWith(r6)     // Catch: java.lang.Throwable -> L72
            if (r6 == 0) goto L6f
            goto L71
        L6f:
            r1 = r3
            goto L72
        L71:
            r1 = r4
        L72:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gsdk.impl.webview.DEFAULT.bp.r(android.content.Context):int");
    }

    @Deprecated
    public static int c(Context context, boolean z) {
        Window window;
        if ("oneplus".equalsIgnoreCase(Build.BRAND)) {
            String str = Build.MODEL;
            try {
                if (Build.VERSION.SDK_INT >= 28) {
                    Activity u = u(context);
                    if (u != null && (window = u.getWindow()) != null) {
                        DisplayCutout displayCutout = window.getDecorView().getRootWindowInsets().getDisplayCutout();
                        return z ? displayCutout.getSafeInsetTop() : displayCutout.getSafeInsetLeft();
                    }
                } else if (str.endsWith("A6000") || str.endsWith("A6010")) {
                    return (int) b(context);
                }
            } catch (Throwable unused) {
            }
            return 0;
        }
        return 0;
    }

    private static int s(Context context) {
        if (context == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
            return displayMetrics.heightPixels;
        }
        return bt.b(context);
    }

    @Deprecated
    public static boolean k(Context context) {
        try {
            try {
                Class<?> loadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
                return ((Boolean) loadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(loadClass, new Object[0])).booleanValue();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
                return false;
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
                return false;
            } catch (Exception e4) {
                e4.printStackTrace();
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    @Deprecated
    public static int l(Context context) {
        int i2 = t(context)[1];
        return i2 <= 0 ? (int) bt.b(context, 28.0f) : i2;
    }

    @Deprecated
    public static int m(Context context) {
        return t(context)[0];
    }

    private static int[] t(Context context) {
        int[] iArr = {0, 0};
        try {
            if (context == null) {
                return iArr;
            }
            try {
                try {
                    try {
                        Class<?> loadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
                        return (int[]) loadClass.getMethod("getNotchSize", new Class[0]).invoke(loadClass, new Object[0]);
                    } catch (NoSuchMethodException e2) {
                        e2.printStackTrace();
                        return iArr;
                    }
                } catch (ClassNotFoundException e3) {
                    e3.printStackTrace();
                    return iArr;
                }
            } catch (Exception e4) {
                e4.printStackTrace();
                return iArr;
            }
        } catch (Throwable unused) {
        }
    }

    private static Activity u(Context context) {
        if (context == null || (context instanceof Application)) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextThemeWrapper) {
            return u(((ContextThemeWrapper) context).getBaseContext());
        }
        return null;
    }

    public static void a(Window window) {
        if (window == null) {
            return;
        }
        c(window);
        b(window);
    }

    private static void c(Window window) {
        String str = Build.BRAND;
        if ("huawei".equalsIgnoreCase(str) || "Honor".equalsIgnoreCase(str)) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            try {
                Class<?> cls = Class.forName("com.huawei.android.view.LayoutParamsEx");
                cls.getMethod("addHwFlags", Integer.TYPE).invoke(cls.getConstructor(WindowManager.LayoutParams.class).newInstance(attributes), 256);
            } catch (Exception e2) {
                Log.d("ConcaveScreenUtils", e2.getLocalizedMessage());
            }
        }
    }

    public static boolean n(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), f21006j, 0) == 0;
    }

    public static final int o(Context context) {
        int identifier;
        if (context != null && (identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android")) > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static void b(Window window) {
        if ("xiaomi".equalsIgnoreCase(Build.BRAND)) {
            try {
                Window.class.getMethod("addExtraFlags", Integer.TYPE).invoke(window, 1792);
            } catch (Exception e2) {
                Log.e("ConcaveScreenUtils", "addExtraFlags not found:" + e2.getLocalizedMessage());
            }
        }
    }

    public static boolean p(Context context) {
        int identifier = context.getResources().getIdentifier("config_screen_has_notch", "bool", "android");
        if (identifier > 0) {
            return context.getResources().getBoolean(identifier);
        }
        return false;
    }

    public static boolean q(Context context) {
        try {
            return ((Boolean) Class.forName("flyme.config.FlymeFeature").getDeclaredField("IS_FRINGE_DEVICE").get(null)).booleanValue();
        } catch (Exception e2) {
            Log.e("ConcaveScreenUtils", e2.toString());
            return false;
        }
    }
}
