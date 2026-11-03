package com.bytedance.crash;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import com.bytedance.crash.dumper.a;
import com.bytedance.crash.jni.NativeBridge;
import com.bytedance.crash.util.k;
import java.io.File;
/* loaded from: classes2.dex */
public class Global {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6060a = "hasCrash";

    /* renamed from: b  reason: collision with root package name */
    private static volatile Global f6061b = null;

    /* renamed from: j  reason: collision with root package name */
    private static String f6062j = "default";
    private static volatile boolean k = false;
    private static String l = null;
    private static ICrashMonitorService m = null;
    private static boolean n = true;

    /* renamed from: c  reason: collision with root package name */
    private Context f6063c;

    /* renamed from: d  reason: collision with root package name */
    private File f6064d;

    /* renamed from: e  reason: collision with root package name */
    private Application f6065e;

    /* renamed from: i  reason: collision with root package name */
    private int f6069i;

    /* renamed from: f  reason: collision with root package name */
    private long f6066f = System.currentTimeMillis();

    /* renamed from: h  reason: collision with root package name */
    private final long f6068h = SystemClock.uptimeMillis();

    /* renamed from: g  reason: collision with root package name */
    private long f6067g = SystemClock.elapsedRealtime();

    private static Global n() {
        if (f6061b == null) {
            synchronized (Global.class) {
                if (f6061b == null) {
                    f6061b = new Global();
                }
            }
        }
        return f6061b;
    }

    private Global() {
        if (Build.VERSION.SDK_INT >= 24) {
            this.f6066f -= this.f6067g - Process.getStartElapsedRealtime();
        }
    }

    public static String a() {
        return f6062j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(String str) {
        f6062j = str;
    }

    public static boolean b() {
        return k;
    }

    public static void a(boolean z) {
        k = z;
    }

    public static Context c() {
        return n().f6063c;
    }

    public static Application d() {
        return n().f6065e;
    }

    public static File e() {
        return n().f6064d;
    }

    public static File a(Context context) {
        File e2 = e();
        return e2 == null ? k.a(context.getFilesDir(), "npth") : e2;
    }

    public static String f() {
        File file = new File(e(), "configs");
        if (!file.exists()) {
            k.b(file);
        }
        return file.getAbsolutePath();
    }

    public static long g() {
        return n().f6066f;
    }

    public static long h() {
        return n().f6067g;
    }

    public static void a(Long l2) {
        n().f6066f = l2.longValue() < n().f6066f ? l2.longValue() : n().f6066f;
    }

    public static long i() {
        return n().f6068h;
    }

    public static void b(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        n().c(context);
    }

    private void c(Context context) {
        d(context);
        if (this.f6063c == null) {
            synchronized (this) {
                if (this.f6063c == null) {
                    this.f6063c = context;
                }
            }
        }
        if (this.f6064d == null) {
            synchronized (this) {
                if (this.f6064d == null) {
                    this.f6064d = k.a(context.getFilesDir(), "npth");
                }
            }
        }
    }

    private void d(Context context) {
        if (this.f6065e == null) {
            if (context instanceof Application) {
                this.f6065e = (Application) context;
            } else {
                this.f6065e = (Application) context.getApplicationContext();
            }
        }
    }

    public static void a(Application application) {
        if (application == null || n().f6065e != null) {
            return;
        }
        n().f6065e = application;
    }

    public static void a(long j2, long j3, long j4, String str) {
        if (l == null) {
            String a2 = a.a(j2, j3, j4, str);
            l = a2;
            if (a2 != null) {
                NativeBridge.l(a2);
            }
        }
    }

    public static String j() {
        return l;
    }

    public static void a(int i2) {
        n().f6069i = i2;
    }

    public static int k() {
        return n().f6069i;
    }

    public static void a(ICrashMonitorService iCrashMonitorService) {
        m = iCrashMonitorService;
    }

    public static ICrashMonitorService l() {
        return m;
    }

    public static void b(boolean z) {
        n = z;
    }

    public static boolean m() {
        return n;
    }
}
