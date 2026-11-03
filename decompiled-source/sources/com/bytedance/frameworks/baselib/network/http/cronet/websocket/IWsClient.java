package com.bytedance.frameworks.baselib.network.http.cronet.websocket;

import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public interface IWsClient {
    boolean isConnected();

    void onParameterChange(Map<String, String> map, Map<String, String> map2, List<String> list, boolean z, boolean z2);

    void openConnection(Map<String, String> map, Map<String, String> map2, List<String> list, boolean z, boolean z2);

    boolean sendMessage(byte[] bArr, int i2);

    void stopConnection();
}
