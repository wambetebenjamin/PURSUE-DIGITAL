package com.bytedance.retrofit2;
/* loaded from: classes2.dex */
public class RetrofitConfig {
    private static boolean loadServiceMethodOpt = false;

    public static void enableLoadServiceMethodOpt() {
        loadServiceMethodOpt = true;
    }

    public static boolean isLoadServiceMethodOptOpen() {
        return loadServiceMethodOpt;
    }
}
