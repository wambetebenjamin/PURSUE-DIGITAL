package com.bytedance.frameworks.baselib.network.http.cronet.websocket;

import org.json.JSONObject;
/* loaded from: classes2.dex */
public interface IMessageReceiveListener {
    void onConnection(int i2, String str, JSONObject jSONObject);

    void onFeedBackLog(String str);

    void onMessage(byte[] bArr, int i2);
}
