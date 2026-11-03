package com.bytedance.common.jato.jit;

import android.os.Build;
import android.util.Log;
import com.bytedance.common.jato.c;
import com.bytedance.common.jato.d;
import com.bytedance.common.jato.util.b;
/* loaded from: classes.dex */
public class JitBlock {

    /* renamed from: a  reason: collision with root package name */
    private static String f4760a = "JitBlock";

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f4761b = false;

    /* renamed from: c  reason: collision with root package name */
    private static volatile boolean f4762c = false;

    /* renamed from: d  reason: collision with root package name */
    private static volatile boolean f4763d = false;

    /* renamed from: e  reason: collision with root package name */
    private static volatile boolean f4764e = false;

    /* renamed from: f  reason: collision with root package name */
    private static c f4765f;

    private static native int initJitBlockInternal(int i2, boolean z);

    private static native void lightJitBlockStartInternal();

    private static native void lightJitBlockStopInternal();

    private static native void nativeSetInterval(long j2);

    private static native void nativeSetPriority(int i2);

    public static void a(boolean z) {
        f4764e = z;
    }

    public static void b(boolean z) {
        f4762c = z;
    }

    public static void c(boolean z) {
        if (f4764e || !f4762c || f4761b || f4763d) {
            return;
        }
        if (Build.VERSION.SDK_INT < 25) {
            Log.d(f4760a, "features.jit block support greater than android N");
            return;
        }
        int c2 = b.c();
        if (c2 <= 35 && d.loadLibrary()) {
            try {
                int initJitBlockInternal = initJitBlockInternal(c2, z);
                if (initJitBlockInternal != 0) {
                    c cVar = f4765f;
                    if (cVar != null) {
                        cVar.onDebugInfo("init jit block failed:" + initJitBlockInternal);
                    }
                    f4763d = true;
                }
                f4761b = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void lightJitBlockStart() {
        if (a()) {
            lightJitBlockStartInternal();
        }
    }

    public static void lightJitBlockStop() {
        if (a()) {
            lightJitBlockStopInternal();
        }
    }

    public static void a(long j2) {
        if (a()) {
            nativeSetInterval(j2);
        }
    }

    public static void a(int i2) {
        if (!a() || i2 == -1) {
            return;
        }
        nativeSetPriority(i2);
    }

    private static boolean a() {
        if (f4764e || !f4761b || f4763d) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 25) {
            return b.c() <= 35;
        }
        Log.d(f4760a, "features.jit block support greater than android N");
        return false;
    }

    public static void a(c cVar) {
        f4765f = cVar;
    }
}
