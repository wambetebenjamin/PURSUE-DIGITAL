package com.bytedance.platform.godzilla.sysopt;
/* loaded from: classes2.dex */
public final class IvimageOpt {
    public static native void end();

    public static native void start();

    static {
        System.loadLibrary("godzilla-sysopt");
    }

    private IvimageOpt() {
    }
}
