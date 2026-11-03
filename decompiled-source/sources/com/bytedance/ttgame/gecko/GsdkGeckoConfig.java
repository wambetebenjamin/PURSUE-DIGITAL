package com.bytedance.ttgame.gecko;

import android.content.Context;
import java.io.File;
/* loaded from: classes3.dex */
public class GsdkGeckoConfig {
    public static final String GECKO_AD_BOE_ONLINE_KEY_GSDK_CN = "85b6a8d8d213511f0d46d12e28d44e3e";
    public static final String GECKO_AD_BOE_ONLINE_KEY_GSDK_I18N = "483e03efb6e5a730e088b1f1072204b3";
    public static final String GECKO_AD_BOE_SANDBOX_KEY_GSDK_CN = "eaf7a137f433fc5a503a02ef9718a326";
    public static final String GECKO_AD_ONLINE_KEY_GSDK_CN = "d4f4979ca4fa9774d25c3ec3a49fbded";
    public static final String GECKO_AD_ONLINE_KEY_GSDK_I18N = "57068b92f169ff272557eb674882b568";
    public static final String GECKO_AD_TEST_KEY_GSDK_CN = "783b79a961a58fea81c271851de9f948";
    public static final String GECKO_AD_TEST_KEY_GSDK_I18N = "b5d844f82ca278c76b45bcd88c104a75";
    public static final int GECKO_AID_CN = 10123;
    public static final int GECKO_AID_I18N = 34;
    public static final String GECKO_TABLE = "gsdk_gecko";
    public static final int NET_ENV_BOE = 1;
    public static final int NET_ENV_FORMAL = 0;
    public static final int NET_ENV_SANDBOX = 2;
    public static final int REGION_CN = 0;
    public static final int REGION_I18N = 1;
    public static final String RES_DIR = "bundles";
    public static final int SERVER_REGION_CN = 0;
    public static final int SERVER_REGION_NO_SPEC = -1;
    public static final int SERVER_REGION_SG = 10;
    public static final int SERVER_REGION_VA = 20;
    public static boolean isBoe;
    public static boolean isSandbox;
    public static int region;
    public static int serverRegion;
    private static final String GECKO_HOST_CN = "gecko.zijie$$api.com".replace("$$", "");
    private static final String GECKO_HOST_I18N = "gecko-sg.byte$$oversea.com".replace("$$", "");
    private static final String GECKO_HOST_I18N_BOE = "gecko-sg.byte$$oversea.com.boe-gateway.byted.org".replace("$$", "");
    private static final String GECKO_HOST_I18N_AMERICA = "gecko-va.byte$$oversea.com".replace("$$", "");
    private static final String GECKO_HOST_BOE = "gecko.zijie$$api.com.$$boe-$$gateway.$$byted.org".replaceAll("[$$]", "");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(int i2, int i3, boolean z, boolean z2) {
        region = i2;
        serverRegion = i3;
        isBoe = z;
        isSandbox = z2;
    }

    public static String getGeckoAdAccessKey() {
        return (region == 0 || serverRegion == 0) ? isSandbox ? isBoe ? GECKO_AD_BOE_SANDBOX_KEY_GSDK_CN : GECKO_AD_TEST_KEY_GSDK_CN : isBoe ? GECKO_AD_BOE_ONLINE_KEY_GSDK_CN : GECKO_AD_ONLINE_KEY_GSDK_CN : isBoe ? GECKO_AD_BOE_ONLINE_KEY_GSDK_I18N : isSandbox ? GECKO_AD_TEST_KEY_GSDK_I18N : GECKO_AD_ONLINE_KEY_GSDK_I18N;
    }

    public static String offlineRootDir(Context context) {
        return context.getFilesDir() + File.separator + "bundles";
    }

    public static int aid() {
        return region == 0 ? 10123 : 34;
    }

    public static String host() {
        String str = GECKO_HOST_CN;
        int i2 = serverRegion;
        if (i2 != 0 && region != 0) {
            str = i2 == 10 ? GECKO_HOST_I18N : i2 == 20 ? GECKO_HOST_I18N_AMERICA : GECKO_HOST_I18N;
        }
        if (isBoe) {
            if (region == 0) {
                return GECKO_HOST_BOE;
            }
            return GECKO_HOST_I18N_BOE;
        }
        return str;
    }
}
