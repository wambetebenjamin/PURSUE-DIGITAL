package com.bytedance.ttgame.module.dynamic.bridge;

import com.bytedance.ttgame.framework.gbridge.GBridgeManager;
import com.bytedance.ttgame.framework.gbridge.IApplicationProvider;
import com.bytedance.ttgame.framework.gbridge.module.BaseModule;
/* loaded from: classes5.dex */
public class DynamicModule implements BaseModule {
    private static final String GMRNUnityNotification = "GMDynamicUnityNotification";
    private static final String TAG = "DynamicModule";
    private IApplicationProvider mGameApplication;
    private String mTunnel;

    public DynamicModule(String str, IApplicationProvider iApplicationProvider) {
        this.mTunnel = str;
        this.mGameApplication = iApplicationProvider;
        GBridgeManager.registerEvent(str, GMRNUnityNotification);
    }

    @Override // com.bytedance.ttgame.framework.gbridge.IBridgeModule
    public String getBridgeTunnel() {
        return this.mTunnel;
    }
}
