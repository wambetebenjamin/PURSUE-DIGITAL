package com.bytedance.android.service.manager.client.ai;

import com.bytedance.android.push.service.manager.annotation.a;
import org.json.JSONObject;
@a
/* loaded from: classes.dex */
public interface IClientAiExternalService {
    void init();

    boolean isClientAiReady();

    void registerClientAICallback(IClientAICallback iClientAICallback);

    void runTask(JSONObject jSONObject);
}
