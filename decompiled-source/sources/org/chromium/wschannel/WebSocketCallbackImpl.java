package org.chromium.wschannel;

import com.bytedance.common.utility.Logger;
import com.bytedance.common.wschannel.channel.IWsChannelClient;
import com.ttnet.org.chromium.net.NetError;
import com.ttnet.org.chromium.net.TTWebsocketConnection;
import java.nio.ByteBuffer;
import org.json.JSONObject;
/* loaded from: classes10.dex */
final class WebSocketCallbackImpl extends TTWebsocketConnection.Callback {
    private static final String TAG = "CronetFrontierConnection";
    private IWsChannelClient mClient;

    public WebSocketCallbackImpl(IWsChannelClient iWsChannelClient) {
        this.mClient = iWsChannelClient;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Callback
    public void onMessageReceived(TTWebsocketConnection tTWebsocketConnection, ByteBuffer byteBuffer, int i2) {
        byte[] bArr = new byte[byteBuffer.capacity()];
        try {
            byteBuffer.get(bArr);
            this.mClient.onMessage(bArr);
        } catch (Throwable unused) {
        }
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Callback
    public void onConnectionStateChanged(TTWebsocketConnection tTWebsocketConnection, int i2, String str) {
        int cronetToWsStateAdapter = WsUtils.cronetToWsStateAdapter(i2);
        if (Logger.debug()) {
            Logger.d(TAG, "WS onConnectionStateChanged state:" + cronetToWsStateAdapter + " url:" + str);
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", 1);
            jSONObject.put("state", cronetToWsStateAdapter);
            jSONObject.put("url", str);
            this.mClient.onConnection(jSONObject);
        } catch (Throwable unused) {
        }
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Callback
    public void onConnectionError(TTWebsocketConnection tTWebsocketConnection, int i2, String str, String str2) {
        int cronetToWsStateAdapter = WsUtils.cronetToWsStateAdapter(i2);
        if (Logger.debug()) {
            Logger.d(TAG, "WS onConnectionError state:" + cronetToWsStateAdapter + " url:" + str + " error:" + str2);
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", 0);
            jSONObject.put("state", cronetToWsStateAdapter);
            jSONObject.put("url", str);
            jSONObject.put("error", str2);
            if (DestroyTimeoutControl.isConnectionTimeout(str2, true)) {
                jSONObject.put("error_code", NetError.ERR_TTNET_REQUEST_TIMED_OUT);
                if (Logger.debug()) {
                    Logger.d(TAG, "WS connection timeout.");
                }
            }
            this.mClient.onConnection(jSONObject);
        } catch (Throwable unused) {
        }
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection.Callback
    public void onTrafficChanged(TTWebsocketConnection tTWebsocketConnection, String str, long j2, long j3, boolean z) {
        try {
            TrafficChangedObservable.inst().onTrafficChanged(str, j2, j3, z);
        } catch (Throwable unused) {
        }
    }
}
