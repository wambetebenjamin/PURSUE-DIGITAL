package com.ttnet.org.chromium.net.impl;
/* loaded from: classes9.dex */
public class ImplVersion {
    private static final int API_LEVEL = 16;
    private static final String CRONET_VERSION = "107.0.5273.2";
    private static final String LAST_CHANGE = "aaaa579f85d02dcbdc52fdfa939fa20d27c82a22d1be";

    public static int getApiLevel() {
        return 16;
    }

    public static String getCronetVersion() {
        return CRONET_VERSION;
    }

    public static String getLastChange() {
        return LAST_CHANGE;
    }

    private ImplVersion() {
    }

    public static String getCronetVersionWithLastChange() {
        return "107.0.5273.2@aaaa579f";
    }
}
