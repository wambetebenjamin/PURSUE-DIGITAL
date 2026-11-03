package com.bytedance.react.framework.modules;

import com.bytedance.react.framework.core.RNLogUtil;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class BridgeLogUtil {
    public static void reportBegin(String str, String str2) {
    }

    public static void reportEnd(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("method", str);
            jSONObject.put("params", str2);
        } catch (JSONException unused) {
        }
        RNLogUtil.reportLog(RNLogUtil.RN_BRIDGE_CALL, "end", jSONObject);
    }

    public static void reportError(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("method", str);
            jSONObject.put("params", str2);
            jSONObject.put("msg", str3);
        } catch (JSONException unused) {
        }
        RNLogUtil.reportLog(RNLogUtil.RN_BRIDGE_CALL, "error", jSONObject);
    }
}
