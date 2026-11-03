package com.bytedance.common.jato.boost;

import android.os.Build;
import android.util.Log;
/* loaded from: classes.dex */
public class RemoveWaitingForGCOpt {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4552a = "RemoveWaitingForGCOpt";

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f4553b = false;

    private static native boolean nEnable(boolean z);

    public static boolean a(boolean z) {
        if (!f4553b && Build.VERSION.SDK_INT >= 29 && Build.VERSION.SDK_INT <= 34) {
            com.bytedance.common.jato.d.loadLibrary();
            try {
                f4553b = true;
                return nEnable(z);
            } catch (NoSuchMethodError e2) {
                Log.e(f4552a, "NoSuchMethodError", e2);
            } catch (UnsatisfiedLinkError e3) {
                Log.e(f4552a, "UnsatisfiedLinkError", e3);
            }
        }
        return false;
    }
}
