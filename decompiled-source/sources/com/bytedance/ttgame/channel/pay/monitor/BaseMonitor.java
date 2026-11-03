package com.bytedance.ttgame.channel.pay.monitor;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class BaseMonitor {
    public static void add(JSONObject jSONObject, String str, long j2) {
        try {
            jSONObject.put(str, j2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void add(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static JSONObject generateFinalExtra(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        if (jSONObject != null) {
            try {
                jSONObject2.put("extra", jSONObject);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return jSONObject2;
    }
}
