package com.bytedance.ttgame.framework.gbridge;

import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public interface IGBridge {
    void call(String str);

    void call(String str, Map<String, Object> map);

    void call(String str, Map<String, Object> map, GBridgeCallback gBridgeCallback);

    void call(String str, Map<String, Object> map, GBridgeCallback gBridgeCallback, long j2);

    @Deprecated
    void call(String str, JSONObject jSONObject);

    @Deprecated
    void call(String str, JSONObject jSONObject, GBridgeCallback gBridgeCallback);

    @Deprecated
    void call(String str, JSONObject jSONObject, GBridgeCallback gBridgeCallback, long j2);

    String handleEngineMixCall(String str);

    JSONObject handleJSONFromEngine(JSONObject jSONObject, boolean z);

    void handleMsgFromEngine(String str);

    String handleMsgFromEngineSync(String str);

    boolean hasNativeAPI(String str);

    boolean isContainsCallBack(String str);

    boolean isSyncMethod(String str);

    void listen(String str, GBridgeCallback gBridgeCallback);

    void registerEvent(String str);

    void registerModule(IBridgeModule iBridgeModule);

    void sendEvent(String str, Map<String, Object> map);

    @Deprecated
    void sendEvent(String str, JSONObject jSONObject);

    void sendLocalEvent(String str, Map<String, Object> map);

    void setCallbackTimeout(long j2);

    void setCheckParamNull(boolean z);

    void unListen(String str);
}
