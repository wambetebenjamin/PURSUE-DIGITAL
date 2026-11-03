package com.bytedance.android.service.manager.push.client.intelligence;

import org.json.JSONObject;
/* loaded from: classes.dex */
public interface IClientFeatureService {
    void getClientFeatureAsync(IFeatureCallBack iFeatureCallBack);

    JSONObject getClientFeatureSync();
}
