package com.bytedance.ttgame.engine.utils;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: JSONUtil.java */
/* loaded from: classes3.dex */
public class a {
    public static HashMap<String, Object> a(JSONObject jSONObject) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                Object obj = jSONObject.get(next);
                if (obj instanceof JSONArray) {
                    ArrayList arrayList = new ArrayList();
                    JSONArray jSONArray = (JSONArray) obj;
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        Object obj2 = jSONArray.get(i2);
                        if (obj2 instanceof JSONObject) {
                            arrayList.add(a((JSONObject) obj2));
                        }
                    }
                    hashMap.put(next, arrayList);
                } else if (obj instanceof JSONObject) {
                    hashMap.put(next.toString(), a((JSONObject) obj));
                } else if ((obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Short) || (obj instanceof String)) {
                    hashMap.put(next, obj);
                }
            } catch (JSONException e2) {
                Log.d("KV_Config", "parse to hashmap failed" + e2.toString());
                e2.printStackTrace();
            }
        }
        return hashMap;
    }

    public static HashMap<String, Object> a(String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e2) {
            Log.d("KV_Config", "parse exception happened" + e2.toString());
            e2.printStackTrace();
            jSONObject = null;
        }
        if (jSONObject == null) {
            Log.d("KV_Config", "parse exception happened, return empty map");
            return new HashMap<>();
        }
        return a(jSONObject);
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void a(JSONObject jSONObject, String str, long j2) {
        try {
            jSONObject.put(str, j2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void a(JSONObject jSONObject, String str, int i2) {
        try {
            jSONObject.put(str, i2);
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

    public static void a(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
