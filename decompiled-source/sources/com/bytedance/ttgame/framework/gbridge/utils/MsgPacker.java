package com.bytedance.ttgame.framework.gbridge.utils;

import android.text.TextUtils;
import com.bytedance.ttgame.framework.gbridge.model.BridgeMsg;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class MsgPacker {
    public static JSONObject packetEventMsg(String str, Map<String, Object> map) {
        return packetEventMsg(str, new JSONObject(map));
    }

    @Deprecated
    public static JSONObject packetEventMsg(String str, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(BridgeMsg.MSG_NATIVE_SDK_VER, 3);
            jSONObject2.put("msg_id", System.currentTimeMillis());
            jSONObject2.put("type", 3);
            jSONObject2.put("target", str);
            jSONObject2.put("source", 2);
            jSONObject2.put("data", jSONObject);
            jSONObject2.put(BridgeMsg.CALLBACK_ID, 0);
            jSONObject2.put("code", 0);
            jSONObject2.put(BridgeMsg.MSG_FAIL_MSG, (Object) null);
            jSONObject2.put(BridgeMsg.MSG_PARAM, (Object) null);
        } catch (Exception e2) {
            LogUtil.e("packetEventMsg", e2);
        }
        return jSONObject2;
    }

    public static JSONObject packetCallMsg(String str, Map<String, Object> map, long j2) {
        return packetCallMsg(str, new JSONObject(map), j2);
    }

    @Deprecated
    public static JSONObject packetCallMsg(String str, JSONObject jSONObject, long j2) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(BridgeMsg.MSG_NATIVE_SDK_VER, 3);
            jSONObject2.put("msg_id", System.currentTimeMillis());
            jSONObject2.put("type", 0);
            jSONObject2.put("source", 2);
            jSONObject2.put("target", str);
            jSONObject2.put(BridgeMsg.CALLBACK_ID, j2);
            jSONObject2.put(BridgeMsg.MSG_PARAM, jSONObject);
            jSONObject2.put("code", 0);
            jSONObject2.put(BridgeMsg.MSG_FAIL_MSG, (Object) null);
            jSONObject2.put("data", (Object) null);
        } catch (Exception e2) {
            LogUtil.e("packetCallMsg", e2);
        }
        return jSONObject2;
    }

    public static JSONObject packetListenMsg(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(BridgeMsg.MSG_NATIVE_SDK_VER, 3);
            jSONObject.put("msg_id", System.currentTimeMillis());
            jSONObject.put("type", 1);
            jSONObject.put("source", 2);
            jSONObject.put("target", str);
            jSONObject.put(BridgeMsg.CALLBACK_ID, System.currentTimeMillis());
            jSONObject.put(BridgeMsg.MSG_PARAM, (Object) null);
            jSONObject.put("code", 0);
            jSONObject.put(BridgeMsg.MSG_FAIL_MSG, (Object) null);
            jSONObject.put("data", (Object) null);
        } catch (Exception e2) {
            LogUtil.e("packetListenMsg", e2);
        }
        return jSONObject;
    }

    public static JSONObject packUnListenMsg(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(BridgeMsg.MSG_NATIVE_SDK_VER, 3);
            jSONObject.put("msg_id", System.currentTimeMillis());
            jSONObject.put("type", 2);
            jSONObject.put("source", 2);
            jSONObject.put("target", str);
            jSONObject.put(BridgeMsg.CALLBACK_ID, 0);
            jSONObject.put(BridgeMsg.MSG_PARAM, (Object) null);
            jSONObject.put("code", 0);
            jSONObject.put(BridgeMsg.MSG_FAIL_MSG, (Object) null);
            jSONObject.put("data", (Object) null);
        } catch (Exception e2) {
            LogUtil.e("packUnListenMsg", e2);
        }
        return jSONObject;
    }

    public static JSONObject packetCallbackMsg(BridgeMsg bridgeMsg, Map<String, Object> map) {
        return map != null ? packetCallbackMsg(bridgeMsg, new JSONObject(map)) : packetCallbackMsg(bridgeMsg, (JSONObject) null);
    }

    @Deprecated
    public static JSONObject packetCallbackMsg(BridgeMsg bridgeMsg, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(BridgeMsg.MSG_NATIVE_SDK_VER, 3);
            jSONObject2.put("msg_id", System.currentTimeMillis());
            jSONObject2.put("type", bridgeMsg.getType());
            jSONObject2.put("target", bridgeMsg.getTarget());
            jSONObject2.put(BridgeMsg.CALLBACK_ID, bridgeMsg.getCallbackId());
            jSONObject2.put("source", bridgeMsg.getSource());
            jSONObject2.put("data", jSONObject);
            jSONObject2.put("code", 0);
            jSONObject2.put(BridgeMsg.MSG_FAIL_MSG, (Object) null);
            jSONObject2.put(BridgeMsg.MSG_PARAM, (Object) null);
        } catch (Exception e2) {
            LogUtil.e("packetCallbackMsg", e2);
        }
        return jSONObject2;
    }

    public static JSONObject packetCallbackFailMsg(BridgeMsg bridgeMsg, int i2, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(BridgeMsg.MSG_NATIVE_SDK_VER, 3);
            jSONObject.put("msg_id", System.currentTimeMillis());
            jSONObject.put("type", bridgeMsg.getType());
            jSONObject.put("target", bridgeMsg.getTarget());
            jSONObject.put(BridgeMsg.CALLBACK_ID, bridgeMsg.getCallbackId());
            jSONObject.put("source", bridgeMsg.getSource());
            jSONObject.put("code", i2);
            jSONObject.put(BridgeMsg.MSG_FAIL_MSG, str);
            jSONObject.put("data", (Object) null);
            jSONObject.put(BridgeMsg.MSG_PARAM, (Object) null);
        } catch (Exception e2) {
            LogUtil.e("packetCallbackFailMsg", e2);
        }
        return jSONObject;
    }

    public static BridgeMsg unPacketMsg(JSONObject jSONObject) {
        BridgeMsg bridgeMsg = new BridgeMsg();
        try {
            bridgeMsg.setMsgId(jSONObject.optLong("msg_id"));
            bridgeMsg.setCallbackId(jSONObject.optLong(BridgeMsg.CALLBACK_ID));
            bridgeMsg.setType(jSONObject.optInt("type"));
            bridgeMsg.setTarget(jSONObject.optString("target"));
            Object opt = jSONObject.opt(BridgeMsg.MSG_PARAM);
            if (opt instanceof String) {
                if (!TextUtils.isEmpty(String.valueOf(opt))) {
                    bridgeMsg.setParam(new JSONObject(String.valueOf(opt)));
                }
            } else if (opt instanceof JSONObject) {
                bridgeMsg.setParam((JSONObject) opt);
            }
            Object opt2 = jSONObject.opt("data");
            if (opt2 instanceof String) {
                if (!TextUtils.isEmpty(String.valueOf(opt2))) {
                    bridgeMsg.setData(new JSONObject(String.valueOf(opt2)));
                }
            } else if (opt2 instanceof JSONObject) {
                bridgeMsg.setData((JSONObject) opt2);
            }
            bridgeMsg.setCode(jSONObject.optInt("code"));
            bridgeMsg.setFailMsg(jSONObject.optString(BridgeMsg.MSG_FAIL_MSG));
            bridgeMsg.setSource(jSONObject.optInt("source"));
            bridgeMsg.setNativeSdkVer(3);
            bridgeMsg.setUnitySdkVer(jSONObject.optInt(BridgeMsg.MSG_UNITY_SDK_VER));
        } catch (Exception e2) {
            LogUtil.e("unPacketMsg", e2);
        }
        return bridgeMsg;
    }

    public static String getMsgId() {
        return UUID.randomUUID().toString();
    }

    public static String getCallbackId() {
        return UUID.randomUUID().toString();
    }
}
