package com.facebook.jni;

import com.facebook.soloader.nativeloader.NativeLoader;
/* loaded from: classes8.dex */
public class ThreadScopeSupport {
    private static native void runStdFunctionImpl(long j2);

    static {
        NativeLoader.loadLibrary("fbjni");
    }

    private static void runStdFunction(long j2) {
        runStdFunctionImpl(j2);
    }
}
