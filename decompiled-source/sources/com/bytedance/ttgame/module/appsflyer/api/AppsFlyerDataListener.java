package com.bytedance.ttgame.module.appsflyer.api;

import java.util.Map;
/* loaded from: classes4.dex */
public interface AppsFlyerDataListener {
    void onAttributionFailure(String str);

    void onConversionDataFail(String str);

    void onConversionDataSuccess(Map<String, Object> map);
}
