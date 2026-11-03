package com.bytedance.ttgame.module.dynamic.api;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.rn.api.model.UnityMessage;
import java.util.HashMap;
/* loaded from: classes5.dex */
public interface IDynamicListener {
    void onDegrade(HashMap<String, Object> hashMap);

    void onError(GSDKError gSDKError);

    void onFailed(String str);

    void onMessage(UnityMessage unityMessage);

    void onPageCreated();

    void onWindowCreated(String str);
}
