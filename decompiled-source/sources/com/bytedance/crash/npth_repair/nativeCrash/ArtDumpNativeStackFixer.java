package com.bytedance.crash.npth_repair.nativeCrash;

import com.bytedance.crash.npth_repair.NpthRepair;
import com.bytedance.crash.npth_repair.utils.b;
/* loaded from: classes2.dex */
public class ArtDumpNativeStackFixer {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6943a = "ArtDumpNativeStackFixer";

    /* renamed from: b  reason: collision with root package name */
    private static boolean f6944b;

    /* renamed from: c  reason: collision with root package name */
    private static int f6945c;

    private static native int nStartFixDumpNativeStack();

    private static native void nStopFixDumpNativeStack();

    public static synchronized int a() {
        synchronized (ArtDumpNativeStackFixer.class) {
            if (!NpthRepair.a()) {
                b.a(f6943a, "not init yet, please make sure invoke NpthRepair.init first");
                return -1;
            } else if (f6944b) {
                b.a(f6943a, "has fixed");
                return -2;
            } else {
                f6944b = true;
                f6945c = nStartFixDumpNativeStack();
                return f6945c;
            }
        }
    }

    public static synchronized void b() {
        synchronized (ArtDumpNativeStackFixer.class) {
            if (!NpthRepair.a()) {
                b.a(f6943a, "not init yet, please make sure invoke NpthRepair.init first");
                return;
            }
            if (f6944b && f6945c == 0) {
                f6944b = false;
                nStopFixDumpNativeStack();
            }
        }
    }
}
