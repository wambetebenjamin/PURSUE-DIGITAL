package com.bytedance.crash.npth_repair.nativeCrash;

import android.util.Log;
import com.bytedance.crash.npth_repair.NpthRepair;
/* loaded from: classes2.dex */
public class DvmSuspendTimeOpt {

    /* renamed from: a  reason: collision with root package name */
    protected static boolean f6946a = false;

    /* renamed from: b  reason: collision with root package name */
    protected static int f6947b = 0;

    /* renamed from: c  reason: collision with root package name */
    private static final String f6948c = "DvmSuspendTimeOpt";

    private static native int nSuspendTimeOpt();

    public static synchronized int a() {
        synchronized (DvmSuspendTimeOpt.class) {
            if (!NpthRepair.a()) {
                Log.d(f6948c, "not init yet, please make sure invoke NpthRepair.init first");
                return -1;
            } else if (f6946a) {
                Log.d(f6948c, "has fixed");
                return -2;
            } else {
                f6946a = true;
                f6947b = nSuspendTimeOpt();
                return f6947b;
            }
        }
    }

    public static synchronized void b() {
        synchronized (DvmSuspendTimeOpt.class) {
        }
    }
}
