package com.bytedance.common.push;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: BaseJson.java */
/* loaded from: classes2.dex */
public class c {
    public void add(JSONObject jSONObject, String str, long j2) {
        try {
            jSONObject.put(str, j2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void add(JSONObject jSONObject, String str, JSONArray jSONArray) {
        try {
            jSONObject.put(str, jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void add(JSONObject jSONObject, String str, double d2) {
        try {
            jSONObject.put(str, d2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void add(JSONObject jSONObject, String str, float f2) {
        try {
            jSONObject.put(str, f2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void add(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void add(JSONObject jSONObject, String str, boolean z) {
        try {
            jSONObject.put(str, z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void add(JSONObject jSONObject, String str, JSONObject jSONObject2) {
        try {
            jSONObject.put(str, jSONObject2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void add(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null) {
            return;
        }
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                jSONObject.put(next, jSONObject2.get(next));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
