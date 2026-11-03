package com.bytedance.ttgame.gsdk;
/* loaded from: classes3.dex */
public class PingUtil {
    public static native String pingNative(String str, int i2, int i3);

    static {
        System.loadLibrary("ping");
    }
}
