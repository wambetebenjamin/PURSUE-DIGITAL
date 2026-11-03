package com.bytedance.ttgame.framework.gbridge.rn;

import android.text.TextUtils;
import com.bytedance.ttgame.framework.gbridge.GBridge;
import com.bytedance.ttgame.framework.gbridge.GBridgeManager;
import com.bytedance.ttgame.framework.gbridge.IApplicationProvider;
import com.bytedance.ttgame.framework.gbridge.IBridgeModule;
import com.bytedance.ttgame.framework.gbridge.IEngineCall2;
import com.bytedance.ttgame.framework.gbridge.model.BridgeMsg;
import com.bytedance.ttgame.framework.gbridge.plugin.SdkLog;
import com.bytedance.ttgame.framework.gbridge.utils.LogUtil;
import com.bytedance.ttgame.framework.gbridge.utils.TypeUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class RNBridgeManager {
    private static final String COMMON_RN_RETURN_KEY = "ReturnData";
    private static Map<Long, IResultCallback> sCallbackMap;
    private static GBridge sInstance;

    public static void init(final IRNEventHandler iRNEventHandler) {
        if (sInstance == null) {
            GBridge gBridge = GBridge.getInstance("tunnel_rn");
            sInstance = gBridge;
            gBridge.setEngineCall(new IEngineCall2() { // from class: com.bytedance.ttgame.framework.gbridge.rn.RNBridgeManager.1
                @Override // com.bytedance.ttgame.framework.gbridge.IEngineCall
                public void callEngine(String str) {
                    try {
                        RNBridgeManager.handleEngineCall(new JSONObject(str), IRNEventHandler.this);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        LogUtil.e("handleEngineCall exception, msg: ".concat(String.valueOf(str)), e2);
                    }
                }

                @Override // com.bytedance.ttgame.framework.gbridge.IEngineCall2
                public void callEngine(JSONObject jSONObject) {
                    RNBridgeManager.handleEngineCall(jSONObject, IRNEventHandler.this);
                }
            });
            sCallbackMap = new ConcurrentHashMap();
        }
    }

    public static void registerModule(IBridgeModule iBridgeModule) {
        GBridgeManager.registerModule(iBridgeModule);
    }

    public static void handleMethodCall(String str, Map map, IResultCallback iResultCallback) {
        GBridge gBridge = sInstance;
        if (gBridge == null) {
            LogUtil.i("should init first!!!");
        } else if (!gBridge.hasNativeAPI(str)) {
            HashMap hashMap = new HashMap();
            hashMap.put((Integer) RNBridgeConstant.ERROR_METHOD_NO_EXIST.first, (String) RNBridgeConstant.ERROR_METHOD_NO_EXIST.second);
            iResultCallback.onFailed(hashMap);
        } else {
            boolean isSyncMethod = sInstance.isSyncMethod(str);
            boolean isContainsCallBack = sInstance.isContainsCallBack(str);
            String constructGBridgeMsg = constructGBridgeMsg(str, map, isContainsCallBack, iResultCallback);
            if (isSyncMethod) {
                String handleMsgFromEngineSync = sInstance.handleMsgFromEngineSync(constructGBridgeMsg);
                LogUtil.i("handleMethodCall sync result: ".concat(String.valueOf(handleMsgFromEngineSync)));
                if (TextUtils.isEmpty(handleMsgFromEngineSync)) {
                    iResultCallback.onFailed(new HashMap());
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(handleMsgFromEngineSync);
                    int optInt = jSONObject.optInt("code");
                    if (optInt != 0) {
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put(Integer.valueOf(optInt), jSONObject.optString(BridgeMsg.MSG_FAIL_MSG));
                        iResultCallback.onFailed(hashMap2);
                        return;
                    }
                    Object opt = jSONObject.opt("value");
                    LogUtil.i("handleMethodCall, return value: ".concat(String.valueOf(opt)));
                    if (opt != null) {
                        LogUtil.i("handleMethodCall, return value type: " + opt.getClass());
                    }
                    if (opt != null && TypeUtil.isJsonType(opt.toString())) {
                        iResultCallback.onSuccess(TypeUtil.convertToMapFromJson(opt));
                        return;
                    }
                    HashMap hashMap3 = new HashMap();
                    hashMap3.put(COMMON_RN_RETURN_KEY, opt);
                    iResultCallback.onSuccess(hashMap3);
                    return;
                } catch (Exception e2) {
                    LogUtil.e("parse sync result exception, method:".concat(String.valueOf(str)), e2);
                    e2.printStackTrace();
                    iResultCallback.onFailed(new HashMap());
                    return;
                }
            }
            sInstance.handleMsgFromEngine(constructGBridgeMsg);
            if (isContainsCallBack) {
                return;
            }
            iResultCallback.onSuccess(new HashMap());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleEngineCall(JSONObject jSONObject, IRNEventHandler iRNEventHandler) {
        try {
            int optInt = jSONObject.optInt("type");
            int optInt2 = jSONObject.optInt("source");
            if (optInt == 3 && optInt2 == 2) {
                if (iRNEventHandler != null) {
                    iRNEventHandler.sendMessage(jSONObject.optString("target"), TypeUtil.convertToMapFromJson(jSONObject.optJSONObject("data")));
                    return;
                } else {
                    LogUtil.i("handleEngineCall, should set eventHandler!!!");
                    return;
                }
            }
            long optLong = jSONObject.optLong(BridgeMsg.CALLBACK_ID);
            IResultCallback iResultCallback = sCallbackMap.get(Long.valueOf(optLong));
            if (iResultCallback == null) {
                LogUtil.i("handleEngineCall, callback is null, detail: ".concat(String.valueOf(jSONObject)));
                return;
            }
            int optInt3 = jSONObject.optInt("code");
            if (optInt3 == 0) {
                iResultCallback.onSuccess(TypeUtil.convertToMapFromJson(jSONObject.optJSONObject("data")));
            } else {
                HashMap hashMap = new HashMap();
                hashMap.put(Integer.valueOf(optInt3), jSONObject.optString(BridgeMsg.MSG_FAIL_MSG));
                iResultCallback.onFailed(hashMap);
            }
            sCallbackMap.remove(Long.valueOf(optLong));
        } catch (Exception e2) {
            e2.printStackTrace();
            LogUtil.e("handleEngineCall exception, msg: " + jSONObject.toString(), e2);
        }
    }

    private static String constructGBridgeMsg(String str, Map map, boolean z, IResultCallback iResultCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("msg_id", System.currentTimeMillis());
            jSONObject.put("source", 1);
            jSONObject.put("type", 0);
            jSONObject.put("target", str);
            if (z) {
                long currentTimeMillis = System.currentTimeMillis();
                sCallbackMap.put(Long.valueOf(currentTimeMillis), iResultCallback);
                jSONObject.put(BridgeMsg.CALLBACK_ID, currentTimeMillis);
            }
            if (map != null && !map.isEmpty()) {
                jSONObject.put(BridgeMsg.MSG_PARAM, new JSONObject(map));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            LogUtil.e("constructGBridgeMsg exception, method: ".concat(String.valueOf(str)), e2);
        }
        return jSONObject.toString();
    }

    public static IBridgeModule getBridgeInstance(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return (IBridgeModule) Class.forName(str).getConstructor(String.class).newInstance("tunnel_rn");
        } catch (Exception e2) {
            e2.printStackTrace();
            SdkLog.e("getBridgeInstance", e2.getMessage());
            return null;
        }
    }

    public static IBridgeModule getBridgeInstance(String str, IApplicationProvider iApplicationProvider) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return (IBridgeModule) Class.forName(str).getConstructor(String.class, IApplicationProvider.class).newInstance("tunnel_rn", iApplicationProvider);
        } catch (Exception e2) {
            e2.printStackTrace();
            SdkLog.e("getBridgeInstance", e2.getMessage());
            return null;
        }
    }
}
