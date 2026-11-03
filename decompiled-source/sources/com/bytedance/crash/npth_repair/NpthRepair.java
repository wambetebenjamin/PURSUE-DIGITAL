package com.bytedance.crash.npth_repair;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.bytedance.crash.npth_repair.utils.b;
/* loaded from: classes2.dex */
public class NpthRepair {

    /* renamed from: a  reason: collision with root package name */
    public static Context f6934a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f6935b = false;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f6936c = false;

    /* renamed from: d  reason: collision with root package name */
    private static final String f6937d = "NpthRepair";

    /* renamed from: e  reason: collision with root package name */
    private static int f6938e = -1;

    private static native boolean nGetDebuggable();

    private static native int nInit(int i2, boolean z);

    private static native void nSetDebuggable(boolean z);

    public static synchronized int a(Context context, boolean z) {
        synchronized (NpthRepair.class) {
            if (f6936c) {
                if (z) {
                    Log.d(f6937d, "already do init");
                }
                return f6938e;
            }
            b.a(z);
            f6936c = true;
            f6934a = context;
            if (c()) {
                a(z);
                f6938e = nInit(Build.VERSION.SDK_INT, z);
            } else {
                f6938e = -5;
            }
            return f6938e;
        }
    }

    public static boolean a() {
        return f6938e == 0;
    }

    public static void a(boolean z) {
        b.a(z);
        if (f6935b) {
            nSetDebuggable(z);
        }
    }

    public static boolean b() {
        if (f6935b) {
            return nGetDebuggable();
        }
        return false;
    }

    public static boolean c() {
        if (!f6935b) {
            try {
                com.bytedance.librarian.b.loadLibraryForModule("npth_repair", f6934a);
                f6935b = true;
            } catch (Throwable unused) {
                Log.d(f6937d, "load so fail");
            }
        }
        return f6935b;
    }
}
