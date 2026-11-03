package com.bytedance.sync.util;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: JSONUtils.java */
/* loaded from: classes3.dex */
public class a {
    public static void safePutParam(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void safePutParam(JSONObject jSONObject, String str, long j2) {
        try {
            jSONObject.put(str, j2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void safePutParam(JSONObject jSONObject, String str, int i2) {
        try {
            jSONObject.put(str, i2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void safePutParam(JSONObject jSONObject, String str, boolean z) {
        try {
            jSONObject.put(str, z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
