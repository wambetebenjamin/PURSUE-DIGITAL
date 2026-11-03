package com.bytedance.common.jato.dex;

import com.bytedance.common.jato.d;
/* loaded from: classes.dex */
public class DexTricksNativeHolder {
    private static boolean sInited;

    private static native int dvmVerifyEnableNative();

    private static native int dvmVerifyNoneNative();

    private static native int verifyEnableAbove15Native();

    private static native int verifyEnableNative();

    private static native int verifyNoneAbove15Native();

    private static native int verifyNoneNative(int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    public static int verifyNone15() {
        if (ensureInited()) {
            return verifyNoneAbove15Native();
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int verifyEnable15() {
        if (ensureInited()) {
            return verifyEnableAbove15Native();
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int verifyNone(int i2) {
        if (ensureInited()) {
            return verifyNoneNative(i2);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int verifyEnable() {
        if (ensureInited()) {
            return verifyEnableNative();
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int dvmVerifyNone() {
        if (ensureInited()) {
            try {
                return dvmVerifyNoneNative();
            } catch (UnsatisfiedLinkError unused) {
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int dvmVerifyEnable() {
        if (ensureInited()) {
            try {
                return dvmVerifyEnableNative();
            } catch (UnsatisfiedLinkError unused) {
            }
        }
        return -1;
    }

    private static synchronized boolean ensureInited() {
        boolean z;
        synchronized (DexTricksNativeHolder.class) {
            if (!sInited && d.loadLibrary()) {
                sInited = true;
            }
            z = sInited;
        }
        return z;
    }
}
