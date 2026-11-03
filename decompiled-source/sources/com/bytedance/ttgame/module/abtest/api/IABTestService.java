package com.bytedance.ttgame.module.abtest.api;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
/* loaded from: classes.dex */
public interface IABTestService extends IModuleApi {
    public static final String TAG = "gsdk_abtest_service";

    String getExperimentValue(String str);

    void registerExperiment(String str, String str2, String str3, String str4);

    void registerExperiment(String str, String str2, String str3, String str4, boolean z);

    void registerListener(ABSettingUpdateListener aBSettingUpdateListener);
}
