package com.bytedance.ttgame.module.appsflyer.api;

import android.app.Activity;
import android.content.Context;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
/* loaded from: classes4.dex */
public interface IAppsFlyerService extends IModuleApi {
    String getAppsFlyerUID(Context context);

    void setAFDataCallback(AppsFlyerDataListener appsFlyerDataListener);

    void setAFDeepLinkDataCallback(AppsFlyerDeepLinkCallback appsFlyerDeepLinkCallback);

    void setConsent(Context context, boolean z, boolean z2);

    void setCustomId(String str);

    void startAppsflyer(Activity activity);

    void updateServerUninstallToken(Context context, String str);
}
