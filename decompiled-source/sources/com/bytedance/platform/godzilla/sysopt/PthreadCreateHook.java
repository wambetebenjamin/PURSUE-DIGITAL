package com.bytedance.platform.godzilla.sysopt;
/* loaded from: classes2.dex */
public class PthreadCreateHook {
    public static native void end();

    public static native void start(int i2);

    static {
        System.loadLibrary("godzilla-sysopt");
    }

    private PthreadCreateHook() {
    }
}
