package com.bytedance.common.utility;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: JsonUtils.java */
/* loaded from: classes2.dex */
public final class h {
    private h() {
    }

    public static void optPut(JSONObject jSONObject, String str, Object obj) {
        if (jSONObject == null || o.isEmpty(str)) {
            return;
        }
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static int queryInt(JSONObject jSONObject, String str, int i2) {
        return (jSONObject == null || o.isEmpty(str) || !jSONObject.has(str)) ? i2 : jSONObject.optInt(str, i2);
    }

    public static String queryString(JSONObject jSONObject, String str, String str2) {
        return (jSONObject == null || o.isEmpty(str) || !jSONObject.has(str)) ? str2 : jSONObject.optString(str, str2);
    }

    public static long queryLong(JSONObject jSONObject, String str, long j2) {
        return (jSONObject == null || o.isEmpty(str) || !jSONObject.has(str)) ? j2 : jSONObject.optLong(str, j2);
    }

    public static boolean queryBoolean(JSONObject jSONObject, String str, boolean z) {
        return (jSONObject == null || o.isEmpty(str) || !jSONObject.has(str)) ? z : jSONObject.optBoolean(str, z);
    }
}
