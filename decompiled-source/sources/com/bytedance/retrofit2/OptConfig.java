package com.bytedance.retrofit2;
/* loaded from: classes2.dex */
public class OptConfig {
    private static volatile boolean sEnableRequestOpt = false;

    public static void setEnableRequestOpt(boolean z) {
        sEnableRequestOpt = z;
    }

    public static boolean isEnableRequestOpt() {
        return sEnableRequestOpt;
    }
}
