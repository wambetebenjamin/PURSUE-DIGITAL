package com.ttnet.org.chromium.net;
/* loaded from: classes9.dex */
public class ApiVersion {
    private static final int API_LEVEL = 16;
    private static final String CRONET_VERSION = "107.0.5273.2";
    private static final String LAST_CHANGE = "aaaa579f85d02dcbdc52fdfa939fa20d27c82a22d1be";
    private static final int MIN_COMPATIBLE_API_LEVEL = 3;

    public static int getApiLevel() {
        return 3;
    }

    public static String getCronetVersion() {
        return CRONET_VERSION;
    }

    public static String getLastChange() {
        return LAST_CHANGE;
    }

    public static int getMaximumAvailableApiLevel() {
        return 16;
    }

    private ApiVersion() {
    }

    public static String getCronetVersionWithLastChange() {
        return "107.0.5273.2@aaaa579f";
    }
}
