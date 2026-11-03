package com.bytedance.android.service.manager.client.ai;

import android.util.Log;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class IClientAiExternalServiceImplOfMock implements IClientAiExternalService {
    @Override // com.bytedance.android.service.manager.client.ai.IClientAiExternalService
    public void init() {
    }

    @Override // com.bytedance.android.service.manager.client.ai.IClientAiExternalService
    public void registerClientAICallback(IClientAICallback iClientAICallback) {
    }

    @Override // com.bytedance.android.service.manager.client.ai.IClientAiExternalService
    public void runTask(JSONObject jSONObject) {
    }

    @Override // com.bytedance.android.service.manager.client.ai.IClientAiExternalService
    public boolean isClientAiReady() {
        Log.w("IClientAiExternalService", "cur isClientAiReady method is empty impl in IClientAiExternalServiceImplOfMockClass");
        return false;
    }
}
