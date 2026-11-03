package com.bytedance.ttgame.module.main.bridge;

import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeMethod;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeParam;
import com.bytedance.ttgame.framework.gbridge.module.BaseModule;
import com.bytedance.ttgame.framework.gbridge.plugin.SdkLog;
import com.bytedance.ttgame.framework.gbridge.spi.ServiceManager;
import com.bytedance.ttgame.main.internal.cache.ICacheService;
import com.bytedance.ttgame.module.abtest.api.IABTestService;
import gsdk.impl.main.DEFAULT.g;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class ABTestModule implements BaseModule {
    private String mTunnel;

    public ABTestModule(String str) {
        this.mTunnel = str;
    }

    @GBridgeMethod(callName = "requestRegisterExperiment", sync = true)
    public boolean registerExperiment(@GBridgeParam("data") JSONObject jSONObject) {
        SdkLog.i(BaseModule.TAG, "registerExperiment");
        ((IABTestService) ServiceManager.get().getService(IABTestService.class)).registerExperiment(jSONObject.optString("key"), jSONObject.optString("description"), jSONObject.optString("ower"), jSONObject.optString("defaultValue"), jSONObject.optBoolean("isBindUser", false));
        return true;
    }

    @GBridgeMethod(callName = "requestGetExperiment", sync = true)
    public String getExperimentValue(@GBridgeParam("key") String str) {
        SdkLog.i(BaseModule.TAG, g.a.f20437b);
        return ((IABTestService) ServiceManager.get().getService(IABTestService.class)).getExperimentValue(str);
    }

    @GBridgeMethod(callName = "checkMethodEnable", sync = true)
    public boolean checkMethodEnable(@GBridgeParam("key") String str) {
        SdkLog.i(BaseModule.TAG, "checkMethodEnable");
        if (str.isEmpty()) {
            return true;
        }
        JSONArray optJsonArray = ((ICacheService) ServiceManager.get().getService(ICacheService.class)).optJsonArray("UnityDisasterRecovery");
        if (optJsonArray.length() == 0) {
            return true;
        }
        for (int i2 = 0; i2 < optJsonArray.length(); i2++) {
            if (str.equals(optJsonArray.optString(i2, ""))) {
                SdkLog.w(BaseModule.TAG, "method is blocked by cloud : " + str);
                return false;
            }
        }
        return true;
    }

    @Override // com.bytedance.ttgame.framework.gbridge.IBridgeModule
    public String getBridgeTunnel() {
        return this.mTunnel;
    }
}
