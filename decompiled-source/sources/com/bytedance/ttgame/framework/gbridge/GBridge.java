package com.bytedance.ttgame.framework.gbridge;

import com.bytedance.ttgame.framework.gbridge.internal.NBridgeImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class GBridge {
    private static Map<String, GBridge> mBridgeMap = new ConcurrentHashMap();
    private static Map<String, IEngineCall> mEngineCallMap = new ConcurrentHashMap();
    private IGBridge bridgeImpl;
    private String mCurrentTunnel;

    public int getSdkVerInt() {
        return 3;
    }

    public static GBridge getInstance(String str) {
        if (!mBridgeMap.containsKey(str)) {
            synchronized (GBridge.class) {
                if (!mBridgeMap.containsKey(str)) {
                    mBridgeMap.put(str, new GBridge(str));
                }
            }
        }
        return mBridgeMap.get(str);
    }

    public static List<GBridge> getAllGBridge() {
        return new ArrayList(mBridgeMap.values());
    }

    public String getTunnel() {
        return this.mCurrentTunnel;
    }

    private GBridge(String str) {
        this.mCurrentTunnel = str;
        this.bridgeImpl = new NBridgeImpl(str);
    }

    public void setEngineCall(IEngineCall iEngineCall) {
        mEngineCallMap.put(this.mCurrentTunnel, iEngineCall);
    }

    public static IEngineCall getCurrentEngineCall(String str) {
        return mEngineCallMap.get(str);
    }

    public void setCheckParamNull(boolean z) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.setCheckParamNull(z);
        }
    }

    public void setCallbackTimeout(long j2) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.setCallbackTimeout(j2);
        }
    }

    public void registerModule(IBridgeModule iBridgeModule) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.registerModule(iBridgeModule);
        }
    }

    public void registerEvent(String str) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.registerEvent(str);
        }
    }

    @Deprecated
    public void sendEvent(String str, JSONObject jSONObject) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.sendEvent(str, jSONObject);
        }
    }

    public void sendEvent(String str, Map<String, Object> map) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.sendEvent(str, map);
        }
    }

    public void call(String str) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.call(str);
        }
    }

    @Deprecated
    public void call(String str, JSONObject jSONObject) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.call(str, jSONObject);
        }
    }

    public void call(String str, Map<String, Object> map) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.call(str, map);
        }
    }

    @Deprecated
    public void call(String str, JSONObject jSONObject, GBridgeCallback gBridgeCallback) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.call(str, jSONObject, gBridgeCallback);
        }
    }

    public void call(String str, Map<String, Object> map, GBridgeCallback gBridgeCallback) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.call(str, map, gBridgeCallback);
        }
    }

    @Deprecated
    public void call(String str, JSONObject jSONObject, GBridgeCallback gBridgeCallback, long j2) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.call(str, jSONObject, gBridgeCallback, j2);
        }
    }

    public void call(String str, Map<String, Object> map, GBridgeCallback gBridgeCallback, long j2) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.call(str, map, gBridgeCallback, j2);
        }
    }

    public void listen(String str, GBridgeCallback gBridgeCallback) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.listen(str, gBridgeCallback);
        }
    }

    public void unListen(String str) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.unListen(str);
        }
    }

    public JSONObject handleJSONFromEngine(JSONObject jSONObject, boolean z) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            return iGBridge.handleJSONFromEngine(jSONObject, z);
        }
        return null;
    }

    public void handleMsgFromEngine(String str) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.handleMsgFromEngine(str);
        }
    }

    public String handleMsgFromEngineSync(String str) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            return iGBridge.handleMsgFromEngineSync(str);
        }
        return null;
    }

    public boolean hasNativeAPI(String str) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            return iGBridge.hasNativeAPI(str);
        }
        return false;
    }

    public String handleEngineMixCall(String str) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            return iGBridge.handleEngineMixCall(str);
        }
        return null;
    }

    public void sendLocalEvent(String str, Map<String, Object> map) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            iGBridge.sendLocalEvent(str, map);
        }
    }

    public boolean isSyncMethod(String str) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            return iGBridge.isSyncMethod(str);
        }
        return false;
    }

    public boolean isContainsCallBack(String str) {
        IGBridge iGBridge = this.bridgeImpl;
        if (iGBridge != null) {
            return iGBridge.isContainsCallBack(str);
        }
        return false;
    }
}
