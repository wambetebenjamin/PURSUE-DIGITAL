package com.bytedance.ttgame.framework.gbridge;

import org.json.JSONObject;
/* loaded from: classes3.dex */
public interface GBridgeCallback {
    void onFailed(int i2, String str);

    void onSuccess(JSONObject jSONObject);

    void onTimeout();
}
