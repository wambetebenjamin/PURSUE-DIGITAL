package com.bytedance.android.service.manager.alliance;

import android.content.Context;
import android.util.Log;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class IAllianceServiceImplOfMock implements IAllianceService {
    @Override // com.bytedance.android.service.manager.alliance.IAllianceService
    public void doAfterProcessIsolationProtected(Runnable runnable) {
    }

    @Override // com.bytedance.android.service.manager.alliance.IAllianceService
    public void onApplicationInit(String str, Context context) {
    }

    @Override // com.bytedance.android.service.manager.alliance.IAllianceService
    public void onEventV3(boolean z, String str, JSONObject jSONObject) {
    }

    @Override // com.bytedance.android.service.manager.alliance.IAllianceService
    public void onEventV3WithHttp(boolean z, String str, JSONObject jSONObject) {
    }

    @Override // com.bytedance.android.service.manager.alliance.IAllianceService
    public void onWorkerApplicationStart() {
    }

    @Override // com.bytedance.android.service.manager.alliance.IAllianceService
    public void updateSettings(Context context, JSONObject jSONObject) {
    }

    @Override // com.bytedance.android.service.manager.alliance.IAllianceService
    public void wakeUpTargetPartner(JSONObject jSONObject) {
    }

    @Override // com.bytedance.android.service.manager.alliance.IAllianceService
    public IsSupportWakeUp isSupportWakeUp(Context context) {
        Log.w("IAllianceService", "cur isSupportWakeUp method is empty impl in IAllianceServiceImplOfMockClass");
        return null;
    }

    @Override // com.bytedance.android.service.manager.alliance.IAllianceService
    public boolean verifySign(String str, String str2) {
        Log.w("IAllianceService", "cur verifySign method is empty impl in IAllianceServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.alliance.IAllianceService
    public boolean allowStartOthersProcessFromSmp() {
        Log.w("IAllianceService", "cur allowStartOthersProcessFromSmp method is empty impl in IAllianceServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.alliance.IAllianceService
    public boolean hasWaked() {
        Log.w("IAllianceService", "cur hasWaked method is empty impl in IAllianceServiceImplOfMockClass");
        return false;
    }
}
