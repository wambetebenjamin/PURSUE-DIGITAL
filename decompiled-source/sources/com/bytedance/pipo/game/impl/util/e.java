package com.bytedance.pipo.game.impl.util;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: JSONObjectUtil.java */
/* loaded from: classes2.dex */
public class e {
    public static void a(JSONObject jSONObject, String str, long j2) {
        try {
            jSONObject.put(str, j2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void a(JSONObject jSONObject, String str, boolean z) {
        try {
            jSONObject.put(str, z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void a(JSONObject jSONObject, String str, float f2) {
        try {
            jSONObject.put(str, f2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        try {
            if (TextUtils.isEmpty(str2)) {
                str2 = "unknown";
            }
            jSONObject.put(str, str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void a(JSONObject jSONObject, String str, JSONObject jSONObject2) {
        try {
            jSONObject.put(str, jSONObject2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
