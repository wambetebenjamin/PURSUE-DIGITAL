package com.bytedance.sdk.account.save;

import org.json.JSONObject;
/* compiled from: SaveSettingManager.java */
/* loaded from: classes2.dex */
public class e {
    public static String MAX_CACHE_NUMBER = "max_cache";

    public static int getMaxCacheNumber() {
        JSONObject loginInfoConfig = ((com.bytedance.sdk.account.platform.api.a) com.bytedance.sdk.account.platform.base.e.getService(com.bytedance.sdk.account.platform.api.a.class)).getLoginInfoConfig();
        int i2 = c.CACHE_NUMBER;
        if (loginInfoConfig != null) {
            i2 = loginInfoConfig.optInt(MAX_CACHE_NUMBER, c.CACHE_NUMBER);
        }
        if (i2 >= 0) {
            return i2;
        }
        return 0;
    }
}
