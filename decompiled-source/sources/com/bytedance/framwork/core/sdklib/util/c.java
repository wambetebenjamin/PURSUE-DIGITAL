package com.bytedance.framwork.core.sdklib.util;

import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: JsonUtil.java */
/* loaded from: classes2.dex */
public final class c {
    public static boolean isEmpty(JSONObject jSONObject) {
        return jSONObject == null || jSONObject.length() == 0;
    }

    public static boolean isEmpty(JSONArray jSONArray) {
        return jSONArray == null || jSONArray.length() == 0;
    }

    public static JSONObject a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return new JSONObject(jSONObject.toString());
        } catch (Exception unused) {
            return jSONObject;
        }
    }
}
