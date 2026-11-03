package com.bytedance.ttgame.sdk.module.utils;

import android.content.Context;
import com.bytedance.ttgame.core.SdkConfig;
/* loaded from: classes8.dex */
public class I18nUtils {
    private static Context context;
    private static SdkConfig sdkConfig;

    public static void init(Context context2, SdkConfig sdkConfig2) {
        context = context2;
        sdkConfig = sdkConfig2;
    }

    public static boolean isAmerica() {
        return sdkConfig.serverRegion == 20;
    }
}
