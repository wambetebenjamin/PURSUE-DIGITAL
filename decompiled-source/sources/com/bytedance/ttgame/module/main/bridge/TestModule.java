package com.bytedance.ttgame.module.main.bridge;

import android.app.Activity;
import com.bytedance.ttgame.framework.gbridge.IApplicationProvider;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeMethod;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeParam;
import com.bytedance.ttgame.framework.gbridge.module.BaseModule;
import com.bytedance.ttgame.framework.module.util.SpUtil;
import com.bytedance.ttgame.sdk.module.core.internal.RocketConstants;
/* loaded from: classes6.dex */
public class TestModule implements BaseModule {
    private IApplicationProvider mApplication;
    private String mTunnel;

    public TestModule(String str, IApplicationProvider iApplicationProvider) {
        this.mTunnel = str;
        this.mApplication = iApplicationProvider;
    }

    @GBridgeMethod(callName = "requestSetDebugMode")
    public void changeEventHost(@GBridgeParam("debug") boolean z) {
        Activity currentActivity = this.mApplication.getCurrentActivity();
        if (z) {
            currentActivity.getSharedPreferences(SpUtil.SP_NAME, 4).edit().putString(RocketConstants.KEY_EVENT_SEND_HOST, "test").apply();
        } else {
            currentActivity.getSharedPreferences(SpUtil.SP_NAME, 4).edit().putString(RocketConstants.KEY_EVENT_SEND_HOST, "").apply();
        }
    }

    @Override // com.bytedance.ttgame.framework.gbridge.IBridgeModule
    public String getBridgeTunnel() {
        return this.mTunnel;
    }
}
