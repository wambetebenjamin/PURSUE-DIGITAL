package com.bytedance.common.utility;
/* loaded from: classes2.dex */
public class NetClientChecker {
    public static boolean isNetworkClientSet() {
        return !(k.getDefault() instanceof DummyNetworkClient);
    }
}
