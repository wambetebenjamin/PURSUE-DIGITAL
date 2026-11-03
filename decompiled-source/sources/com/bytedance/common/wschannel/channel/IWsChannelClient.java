package com.bytedance.common.wschannel.channel;

import android.content.Context;
import com.bytedance.common.wschannel.model.i;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public interface IWsChannelClient {
    void destroy();

    void init(Context context, IWsChannelClient iWsChannelClient, String str);

    boolean isConnected();

    void onAppStateChanged(int i2);

    void onConnection(JSONObject jSONObject);

    void onMessage(i iVar);

    void onMessage(byte[] bArr);

    void onNetworkStateChanged(int i2);

    void onParameterChange(Map<String, Object> map, List<String> list);

    void onServiceConnectEvent(int i2, boolean z, String str);

    void openConnection(Map<String, Object> map, List<String> list);

    boolean privateProtocolEnabled();

    boolean privateProtocolProxyEnabled();

    void registerService(int i2);

    boolean sendMessage(i iVar);

    boolean sendMessage(byte[] bArr);

    void stopConnection();

    void unregisterService(int i2);
}
