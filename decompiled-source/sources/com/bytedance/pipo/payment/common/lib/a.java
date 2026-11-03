package com.bytedance.pipo.payment.common.lib;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: BaseJson.java */
/* loaded from: classes2.dex */
public class a {
    public void a(JSONObject jSONObject, String str, long j2) {
        try {
            jSONObject.put(str, j2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void a(JSONObject jSONObject, String str, float f2) {
        try {
            jSONObject.put(str, f2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void a(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void a(JSONObject jSONObject, String str, boolean z) {
        try {
            jSONObject.put(str, z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
