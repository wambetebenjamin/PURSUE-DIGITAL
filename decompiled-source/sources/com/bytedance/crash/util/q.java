package com.bytedance.crash.util;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: JSONUtils.java */
/* loaded from: classes2.dex */
public final class q {
    public static boolean isEmpty(JSONObject jSONObject) {
        return jSONObject == null || jSONObject.length() == 0;
    }

    public static boolean isEmpty(JSONArray jSONArray) {
        return jSONArray == null || jSONArray.length() == 0;
    }

    public static void addKVs(JSONObject jSONObject, Object... objArr) {
        if (jSONObject == null || objArr == null || objArr.length % 2 != 0 || objArr.length == 0) {
            y.e("JSONUtils", "Err use of addKVs!!!!!");
            return;
        }
        for (int i2 = 0; i2 < objArr.length; i2 += 2) {
            a(jSONObject, String.valueOf(objArr[i2]), objArr[i2 + 1]);
        }
    }

    public static JSONArray splitArray(int i2, int i3, JSONArray jSONArray) {
        int i4 = i3 + i2;
        if (jSONArray.length() <= i4) {
            return jSONArray;
        }
        JSONArray jSONArray2 = new JSONArray();
        for (int i5 = 0; i5 < i2; i5++) {
            jSONArray2.put(jSONArray.opt(i5));
        }
        jSONArray2.put("... skip " + ((jSONArray.length() - i3) - i2) + " lines");
        while (i2 < i4) {
            jSONArray2.put(jSONArray.opt(jSONArray.length() - (i4 - i2)));
            i2++;
        }
        return jSONArray2;
    }

    public static void assignIfNoExist(JSONObject jSONObject, JSONObject jSONObject2) {
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            try {
                String next = keys.next();
                if (!jSONObject.has(next)) {
                    jSONObject.put(next, jSONObject2.opt(next));
                }
            } catch (Throwable unused) {
                return;
            }
        }
    }

    public static JSONArray getArrayFromParent(JSONObject jSONObject, String... strArr) {
        JSONObject jsonFromParent = getJsonFromParent(jSONObject, strArr);
        if (jsonFromParent == null) {
            return null;
        }
        return jsonFromParent.optJSONArray(strArr[strArr.length - 1]);
    }

    public static String getStringFromParent(JSONObject jSONObject, String... strArr) {
        JSONObject jsonFromParent = getJsonFromParent(jSONObject, strArr);
        if (jsonFromParent == null) {
            return null;
        }
        return jsonFromParent.optString(strArr[strArr.length - 1]);
    }

    public static int getIntFromParent(JSONObject jSONObject, int i2, String... strArr) {
        JSONObject jsonFromParent = getJsonFromParent(jSONObject, strArr);
        return jsonFromParent == null ? i2 : jsonFromParent.optInt(strArr[strArr.length - 1], i2);
    }

    public static JSONObject getJsonFromParent(JSONObject jSONObject, String... strArr) {
        if (jSONObject == null) {
            return null;
        }
        for (int i2 = 0; i2 < strArr.length - 1; i2++) {
            jSONObject = jSONObject.optJSONObject(strArr[i2]);
            if (jSONObject == null) {
                y.i("JSONUtil", "err get json: not found node:" + strArr[i2]);
                return null;
            }
        }
        return jSONObject;
    }

    public static HashMap<String, Object> toMap(JSONObject jSONObject) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (jSONObject == null) {
            return hashMap;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.opt(next));
        }
        return hashMap;
    }

    public static Map<String, Object> a(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject == null) {
            return hashMap;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.opt(next));
        }
        return hashMap;
    }

    public static JSONObject a(JSONObject jSONObject, String str, Object obj) {
        if (jSONObject != null && obj != null && !TextUtils.isEmpty(str)) {
            try {
                jSONObject.put(str, obj);
            } catch (JSONException unused) {
            }
        }
        return jSONObject;
    }

    public static void a(JSONObject jSONObject, Map<? extends String, ?> map) {
        if (jSONObject == null || map == null) {
            return;
        }
        try {
            for (Map.Entry<? extends String, ?> entry : map.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        } catch (Throwable unused) {
        }
    }

    public static JSONObject a(JSONObject jSONObject, String str) {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return jSONObject;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
            try {
                jSONObject.put(str, optJSONObject);
            } catch (JSONException unused) {
            }
        }
        return optJSONObject;
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null || jSONObject2.length() <= 0) {
            return;
        }
        try {
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object opt = jSONObject.opt(next);
                if (opt == null) {
                    jSONObject.put(next, jSONObject2.opt(next));
                } else if (opt instanceof JSONObject) {
                    a(jSONObject.getJSONObject(next), jSONObject2.getJSONObject(next));
                } else if (opt instanceof JSONArray) {
                    JSONArray optJSONArray = jSONObject2.optJSONArray(next);
                    if (optJSONArray != null) {
                        JSONArray jSONArray = (JSONArray) opt;
                        if (jSONArray.length() == 1 && (jSONArray.opt(0) instanceof JSONObject) && (optJSONArray.opt(0) instanceof JSONObject)) {
                            a(jSONArray.getJSONObject(0), optJSONArray.getJSONObject(0));
                        } else {
                            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                jSONArray.put(optJSONArray.get(i2));
                            }
                        }
                    }
                } else {
                    jSONObject.put(next, jSONObject2.opt(next));
                }
            }
        } catch (JSONException unused) {
        }
    }

    public static JSONArray a(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (String str : strArr) {
            jSONArray.put(str);
        }
        return jSONArray;
    }

    public static void jsonPutAll(JSONObject jSONObject, Map<String, ? extends Object> map) {
        if (jSONObject == null || map == null) {
            return;
        }
        try {
            for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        } catch (Throwable unused) {
        }
    }

    public static JSONObject a(JSONObject jSONObject, String str, String str2) {
        JSONObject optJSONObject;
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject(str)) == null) {
            return null;
        }
        return optJSONObject.optJSONObject(str2);
    }

    public static void b(JSONObject jSONObject, String str, String str2) {
        if (str2 != null) {
            try {
                jSONObject.put(str, str2);
            } catch (JSONException unused) {
            }
        }
    }

    public static void b(JSONObject jSONObject, String str, Object obj) {
        if (obj != null) {
            try {
                jSONObject.put(str, String.valueOf(obj));
            } catch (JSONException unused) {
            }
        }
    }

    public static void a(JSONObject jSONObject, String str, int i2) {
        try {
            jSONObject.put(str, i2);
        } catch (JSONException unused) {
        }
    }

    public static void a(JSONObject jSONObject, String str, long j2) {
        try {
            jSONObject.put(str, j2);
        } catch (JSONException unused) {
        }
    }

    public static boolean jsonEquals(JSONObject jSONObject, JSONObject jSONObject2) {
        String next;
        if (jSONObject.length() != jSONObject2.length()) {
            return false;
        }
        Iterator<String> keys = jSONObject2.keys();
        do {
            try {
                if (!keys.hasNext()) {
                    return true;
                }
                next = keys.next();
                if (!jSONObject.has(next)) {
                    break;
                }
            } catch (Throwable unused) {
                return true;
            }
        } while (objectEquals(jSONObject.opt(next), jSONObject2.opt(next)));
        return false;
    }

    public static boolean objectEquals(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        return obj != null && obj.equals(obj2);
    }
}
