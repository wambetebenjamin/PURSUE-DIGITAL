package com.bytedance.common.jato.boost;

import android.os.Build;
import android.util.Log;
import com.bytedance.android.bytehook.ByteHook;
/* loaded from: classes.dex */
public class MalloptOptimizer {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4547a = "MalloptOptimizer";

    /* renamed from: b  reason: collision with root package name */
    private static boolean f4548b = false;

    private static native boolean nMalloptOptimize(int i2);

    private static native int nMalloptTuning(int i2, int i3);

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }

    public static synchronized void a(int i2) {
        synchronized (MalloptOptimizer.class) {
            if (f4548b) {
                return;
            }
            if (Build.VERSION.SDK_INT < 28) {
                return;
            }
            try {
                try {
                    if (nMalloptOptimize(i2)) {
                        f4548b = true;
                        Log.d(f4547a, "opt success");
                    } else {
                        Log.d(f4547a, "opt failed");
                    }
                } catch (UnsatisfiedLinkError e2) {
                    Log.e(f4547a, "UnsatisfiedLinkError", e2);
                    e2.printStackTrace();
                }
            } catch (NoSuchMethodError e3) {
                Log.e(f4547a, "NoSuchMethodError", e3);
                e3.printStackTrace();
            }
        }
    }

    public static int a(int i2, int i3) {
        if (com.bytedance.common.jato.d.loadLibrary()) {
            ByteHook.init();
            try {
                return nMalloptTuning(i2, i3);
            } catch (NoSuchMethodError e2) {
                Log.e(f4547a, "NoSuchMethodError", e2);
            } catch (UnsatisfiedLinkError e3) {
                Log.e(f4547a, "UnsatisfiedLinkError", e3);
            }
        }
        return -1;
    }
}
