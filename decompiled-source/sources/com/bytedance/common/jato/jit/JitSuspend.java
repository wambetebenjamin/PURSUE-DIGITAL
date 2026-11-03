package com.bytedance.common.jato.jit;

import android.os.Looper;
import android.util.Log;
import com.bytedance.common.jato.d;
import java.io.File;
/* loaded from: classes.dex */
public class JitSuspend {

    /* renamed from: a  reason: collision with root package name */
    private static volatile boolean f4772a = false;

    public static native long getJitDumpNativeMethod();

    private static native void nativeBegin();

    private static native void nativeEnableJitDump(int i2);

    private static native void nativeEnd();

    private static native boolean nativeInit();

    public static synchronized void a() {
        synchronized (JitSuspend.class) {
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                if (!d.loadLibrary()) {
                    return;
                }
                if (f4772a) {
                    return;
                }
                f4772a = nativeInit();
            } else {
                Log.e("jato", "jit need invoked in worker thread");
            }
        }
    }

    public static synchronized void b() {
        synchronized (JitSuspend.class) {
            if (f4772a && !d()) {
                nativeBegin();
            }
        }
    }

    public static synchronized void c() {
        synchronized (JitSuspend.class) {
            if (f4772a && !d()) {
                nativeEnd();
            }
        }
    }

    public static synchronized void a(int i2) {
        synchronized (JitSuspend.class) {
            if (f4772a && !d()) {
                nativeEnableJitDump(i2);
            }
        }
    }

    private static boolean d() {
        return new File("/data/local/tmp/disable-scoped-jit").exists();
    }
}
