package com.bytedance.apm6.util;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: JsonUtils.java */
/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2385a = "JsonUtils";

    public static JSONObject copyJson2(JSONObject jSONObject, JSONObject jSONObject2) {
        Iterator<String> keys;
        if (jSONObject == null || jSONObject2 == null || (keys = jSONObject2.keys()) == null) {
            return null;
        }
        while (keys.hasNext()) {
            String next = keys.next();
            if (!jSONObject2.isNull(next)) {
                jSONObject.put(next, jSONObject2.opt(next));
            }
        }
        return jSONObject;
    }

    public static boolean isEmpty(JSONObject jSONObject) {
        return jSONObject == null || jSONObject.length() == 0;
    }

    public static boolean isEmpty(JSONArray jSONArray) {
        return jSONArray == null || jSONArray.length() == 0;
    }

    public static boolean hasData(JSONObject jSONObject) {
        return jSONObject != null && jSONObject.length() > 0;
    }

    public static boolean hasData(JSONArray jSONArray) {
        return jSONArray != null && jSONArray.length() > 0;
    }

    public static JSONObject optJSONObject(JSONObject jSONObject, String str, String str2) {
        JSONObject optJSONObject;
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject(str)) == null) {
            return null;
        }
        return optJSONObject.optJSONObject(str2);
    }

    public static JSONObject optJSONObject(JSONObject jSONObject, String str, String str2, String str3) {
        JSONObject optJSONObject = optJSONObject(jSONObject, str, str2);
        if (optJSONObject == null) {
            return null;
        }
        return optJSONObject.optJSONObject(str3);
    }

    public static int optInt(JSONObject jSONObject, String str, String str2, String str3) {
        JSONObject optJSONObject = optJSONObject(jSONObject, str, str2);
        if (optJSONObject == null) {
            return 0;
        }
        return optJSONObject.optInt(str3, 0);
    }

    public static int optInt(JSONObject jSONObject, String str, String str2) {
        JSONObject optJSONObject;
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject(str)) == null) {
            return 0;
        }
        return optJSONObject.optInt(str2, 0);
    }

    public static boolean optBoolean(JSONObject jSONObject, String str, String str2, boolean z) {
        JSONObject optJSONObject;
        return (jSONObject == null || (optJSONObject = jSONObject.optJSONObject(str)) == null) ? z : optJSONObject.optBoolean(str2, z);
    }

    public static String removeString(JSONObject jSONObject, String str) {
        try {
            if (jSONObject.has(str)) {
                String string = jSONObject.getString(str);
                jSONObject.remove(str);
                return string;
            }
            return null;
        } catch (Exception e2) {
            com.bytedance.apm6.util.log.b.e(f2385a, "removeString", e2);
            return null;
        }
    }

    public static int removeInt(JSONObject jSONObject, String str) {
        try {
            if (jSONObject.has(str)) {
                int i2 = jSONObject.getInt(str);
                jSONObject.remove(str);
                return i2;
            }
            return 0;
        } catch (Exception e2) {
            com.bytedance.apm6.util.log.b.e(f2385a, "removeInt", e2);
            return 0;
        }
    }

    public static long removeLong(JSONObject jSONObject, String str) {
        try {
            if (jSONObject.has(str)) {
                long j2 = jSONObject.getLong(str);
                jSONObject.remove(str);
                return j2;
            }
            return 0L;
        } catch (Exception e2) {
            com.bytedance.apm6.util.log.b.e(f2385a, "removeLong", e2);
            return 0L;
        }
    }

    public static JSONObject safeCopyJson(JSONObject jSONObject) {
        Iterator<String> keys;
        if (jSONObject == null || (keys = jSONObject.keys()) == null) {
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            while (keys.hasNext()) {
                String next = keys.next();
                Object opt = jSONObject.opt(next);
                if (opt instanceof JSONObject) {
                    JSONObject safeCopyJson2 = safeCopyJson2((JSONObject) opt);
                    if (safeCopyJson2 != null) {
                        jSONObject2.put(next, safeCopyJson2);
                    }
                } else if (opt instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) opt;
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        jSONArray2.put(jSONArray.get(i2));
                    }
                    jSONObject2.put(next, jSONArray2);
                } else {
                    jSONObject2.put(next, opt);
                }
            }
            return jSONObject2;
        } catch (Exception unused) {
            return jSONObject;
        }
    }

    private static JSONObject safeCopyJson2(JSONObject jSONObject) {
        Iterator<String> keys;
        if (jSONObject == null || (keys = jSONObject.keys()) == null) {
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject2.put(next, jSONObject.opt(next));
            }
            return jSONObject2;
        } catch (Exception unused) {
            return jSONObject;
        }
    }
}
