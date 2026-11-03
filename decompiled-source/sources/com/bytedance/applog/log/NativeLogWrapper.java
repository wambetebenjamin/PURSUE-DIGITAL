package com.bytedance.applog.log;
/* loaded from: classes.dex */
public class NativeLogWrapper {
    private static native void nativeSetLogEnable(boolean z);

    public static void a(boolean z) {
        try {
            nativeSetLogEnable(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
