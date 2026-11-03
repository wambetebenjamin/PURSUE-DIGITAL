package com.bytedance.android.service.manager.push.client.intelligence;

import android.util.Log;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class IClientFeatureServiceImplOfMock implements IClientFeatureService {
    @Override // com.bytedance.android.service.manager.push.client.intelligence.IClientFeatureService
    public void getClientFeatureAsync(IFeatureCallBack iFeatureCallBack) {
    }

    @Override // com.bytedance.android.service.manager.push.client.intelligence.IClientFeatureService
    public JSONObject getClientFeatureSync() {
        Log.w("IClientFeatureService", "cur getClientFeatureSync method is empty impl in IClientFeatureServiceImplOfMockClass");
        return null;
    }
}
