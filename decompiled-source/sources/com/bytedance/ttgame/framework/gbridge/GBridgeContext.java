package com.bytedance.ttgame.framework.gbridge;

import com.bytedance.ttgame.framework.gbridge.model.BridgeMsg;
import com.bytedance.ttgame.framework.gbridge.utils.EngineUtil;
import com.bytedance.ttgame.framework.gbridge.utils.LogUtil;
import com.bytedance.ttgame.framework.gbridge.utils.MsgPacker;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class GBridgeContext {
    private BridgeMsg bridgeMsg;
    private String mCurrentTunnel;

    public GBridgeContext(String str, BridgeMsg bridgeMsg) {
        this.mCurrentTunnel = str;
        this.bridgeMsg = bridgeMsg;
    }

    public BridgeMsg getBridgeMsg() {
        return this.bridgeMsg;
    }

    @Deprecated
    public void callBackResult(JSONObject jSONObject) {
        BridgeMsg bridgeMsg = this.bridgeMsg;
        if (bridgeMsg != null) {
            EngineUtil.callEngineBridge(this.mCurrentTunnel, MsgPacker.packetCallbackMsg(bridgeMsg, jSONObject));
        } else {
            LogUtil.w("callBackResult：bridgeMsg null.");
        }
    }

    public void callBackResult(Map<String, Object> map) {
        BridgeMsg bridgeMsg = this.bridgeMsg;
        if (bridgeMsg != null) {
            EngineUtil.callEngineBridge(this.mCurrentTunnel, MsgPacker.packetCallbackMsg(bridgeMsg, map));
        } else {
            LogUtil.w("callBackResult：bridgeMsg null.");
        }
    }

    public void callBackFailed(int i2, String str) {
        BridgeMsg bridgeMsg = this.bridgeMsg;
        if (bridgeMsg != null) {
            EngineUtil.callEngineBridge(this.mCurrentTunnel, MsgPacker.packetCallbackFailMsg(bridgeMsg, i2, str));
        } else {
            LogUtil.w("callBackFailed：bridgeMsg null.");
        }
    }
}
