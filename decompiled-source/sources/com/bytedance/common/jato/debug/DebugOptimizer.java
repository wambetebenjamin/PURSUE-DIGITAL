package com.bytedance.common.jato.debug;

import com.bytedance.common.jato.d;
import compatible.standard.a;
import java.util.Objects;
/* loaded from: classes.dex */
public class DebugOptimizer {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f4651a = false;

    /* renamed from: b  reason: collision with root package name */
    private static final String f4652b = "local_test";

    public static native void closeInstrumentationCrashFix();

    private static native void openInstrumentationCrashFix();

    public static boolean a(String str) {
        if (Objects.equals(str, f4652b)) {
            d.loadLibrary();
            a.b();
            f4651a = true;
            return true;
        }
        return false;
    }

    public static boolean a() {
        if (f4651a) {
            openInstrumentationCrashFix();
            return true;
        }
        return false;
    }
}
