package com.bytedance.common.jato.boost;

import android.os.Build;
import android.util.Log;
/* loaded from: classes.dex */
public class GCThreadOpt {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4541a = -20;

    /* renamed from: b  reason: collision with root package name */
    private static final String f4542b = "GCThreadOpt";

    /* renamed from: c  reason: collision with root package name */
    private static final int f4543c = -20;

    /* renamed from: d  reason: collision with root package name */
    private static final int f4544d = 19;

    /* renamed from: e  reason: collision with root package name */
    private static volatile GCThreadOpt f4545e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f4546f = false;

    public static native int nIncreaseGCThreadPrio(int i2, int i3, boolean z);

    public static native void nSetGCThreadPriorityWhenBlockGC(int i2, int i3);

    static {
        com.bytedance.common.jato.d.loadLibrary();
        f4545e = null;
    }

    private GCThreadOpt() {
    }

    public static GCThreadOpt a() {
        if (f4545e == null) {
            synchronized (GCThreadOpt.class) {
                if (f4545e == null) {
                    f4545e = new GCThreadOpt();
                }
            }
        }
        return f4545e;
    }

    public synchronized void a(int i2, int i3) {
        if (com.bytedance.common.jato.d.loadLibrary()) {
            nSetGCThreadPriorityWhenBlockGC(i2, i3);
        }
    }

    public synchronized void a(int i2, int i3, boolean z) {
        if (this.f4546f) {
            Log.d(f4542b, "Thread priority has already been increased.");
            return;
        }
        if (com.bytedance.common.jato.d.loadLibrary() && Build.VERSION.SDK_INT >= 21 && i3 >= -20 && i3 <= 19) {
            try {
                if (nIncreaseGCThreadPrio(i2, i3, z) == 1) {
                    this.f4546f = true;
                    Log.i(f4542b, "increaseGCThreadPrio success.");
                } else {
                    Log.e(f4542b, "increaseGCThreadPrio failed.");
                }
            } catch (Exception e2) {
                Log.e(f4542b, "Exception occurred while increasing thread priority: " + e2.getMessage());
            }
        } else {
            Log.w(f4542b, "Cannot increase thread priority due to unsupported API level or library loading failure.");
        }
    }
}
