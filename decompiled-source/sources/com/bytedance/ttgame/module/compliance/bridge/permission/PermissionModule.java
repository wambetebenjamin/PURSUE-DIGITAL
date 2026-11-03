package com.bytedance.ttgame.module.compliance.bridge.permission;

import com.bytedance.ttgame.framework.gbridge.IApplicationProvider;
import com.bytedance.ttgame.framework.gbridge.module.BaseModule;
/* loaded from: classes5.dex */
public class PermissionModule implements BaseModule {
    private static final String GSDK_REQUEST_SHOWPRIVACY_RESULT = "requestShowPermissionResult";
    private IApplicationProvider mApplication;
    private String mTunnel;

    public PermissionModule(String str, IApplicationProvider iApplicationProvider) {
        this.mTunnel = str;
        this.mApplication = iApplicationProvider;
    }

    @Override // com.bytedance.ttgame.framework.gbridge.IBridgeModule
    public String getBridgeTunnel() {
        return this.mTunnel;
    }
}
