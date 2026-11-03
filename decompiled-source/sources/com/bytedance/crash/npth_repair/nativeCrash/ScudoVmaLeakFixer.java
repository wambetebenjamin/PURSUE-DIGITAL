package com.bytedance.crash.npth_repair.nativeCrash;

import com.bytedance.crash.npth_repair.NpthRepair;
import com.bytedance.crash.npth_repair.utils.b;
/* loaded from: classes2.dex */
public class ScudoVmaLeakFixer {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6952a = "ScudoVmaLeakFixer";

    /* renamed from: b  reason: collision with root package name */
    private static boolean f6953b;

    /* renamed from: c  reason: collision with root package name */
    private static int f6954c;

    private static native int nFixScudoVmaLeak();

    public static synchronized int a() {
        synchronized (ScudoVmaLeakFixer.class) {
            if (!NpthRepair.a()) {
                b.a(f6952a, "not init yet, please make sure invoke NpthRepair.init first");
                return -1;
            } else if (f6953b) {
                b.a(f6952a, "has fixed");
                return -2;
            } else {
                f6953b = true;
                f6954c = nFixScudoVmaLeak();
                return f6954c;
            }
        }
    }

    public static synchronized void b() {
        synchronized (ScudoVmaLeakFixer.class) {
        }
    }
}
