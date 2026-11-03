package com.bytedance.applog.util;

import com.meituan.robust.Constants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: JsonUtils.java */
/* loaded from: classes.dex */
public class k {
    private static boolean b(Object obj, Object obj2, String str) {
        return (obj != null || obj2 == null) && (obj == null || obj2 != null);
    }

    public static boolean a(JSONObject jSONObject, JSONObject jSONObject2, String str) {
        if (b(jSONObject, jSONObject2, str)) {
            if (jSONObject == null || jSONObject.length() == jSONObject2.length()) {
                Iterator<String> keys = jSONObject.keys();
                boolean z = true;
                while (keys.hasNext()) {
                    String next = keys.next();
                    z = a(jSONObject.get(next), jSONObject2.get(next), next);
                    if (!z) {
                        break;
                    }
                }
                return z;
            }
            return false;
        }
        return false;
    }

    private static boolean a(Object obj, Object obj2, String str) {
        if (b(obj, obj2, str)) {
            if (obj instanceof JSONObject) {
                return a((JSONObject) obj, (JSONObject) obj2, str);
            }
            if (obj instanceof JSONArray) {
                return a((JSONArray) obj, (JSONArray) obj2, str);
            }
            if (obj.getClass() != obj2.getClass()) {
                return false;
            }
            return a(obj.toString(), obj2.toString(), str);
        }
        return false;
    }

    private static boolean a(JSONArray jSONArray, JSONArray jSONArray2, String str) {
        if (b(jSONArray, jSONArray2, str)) {
            HashMap hashMap = new HashMap();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                Object obj = jSONArray.get(i2);
                if (hashMap.containsKey(obj) && hashMap.get(obj) != null) {
                    hashMap.put(obj, Integer.valueOf(((Integer) hashMap.get(obj)).intValue() + 1));
                } else {
                    hashMap.put(obj, 1);
                }
            }
            HashMap hashMap2 = new HashMap();
            for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                Object obj2 = jSONArray2.get(i3);
                if (hashMap2.containsKey(obj2) && hashMap2.get(obj2) != null) {
                    hashMap2.put(obj2, Integer.valueOf(((Integer) hashMap2.get(obj2)).intValue() + 1));
                } else {
                    hashMap2.put(obj2, 1);
                }
            }
            if (hashMap.size() != hashMap2.size()) {
                return false;
            }
            for (Map.Entry entry : hashMap.entrySet()) {
                if (!((Integer) entry.getValue()).equals((Integer) hashMap2.get(entry.getKey()))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean a(String str, String str2, String str3) {
        return b(str, str2, str3) && str.equals(str2);
    }

    public static boolean a(JSONObject jSONObject, Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (jSONObject == null) {
            return false;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            Object obj = jSONObject.get(keys.next());
            if (obj == null) {
                return false;
            }
            if (obj instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj;
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    Object obj2 = jSONArray.get(i2);
                    if (clsArr2 != null && !a(clsArr2, obj2.getClass())) {
                        return false;
                    }
                }
                continue;
            } else if (clsArr != null && !a(clsArr, obj.getClass())) {
                return false;
            }
        }
        return true;
    }

    private static <T> boolean a(T[] tArr, T t) {
        for (T t2 : tArr) {
            if (t2 == t) {
                return true;
            }
        }
        return false;
    }

    public static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null) {
            return jSONObject2;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String next = keys.next();
                jSONObject2.put(next, jSONObject.opt(next));
            } catch (JSONException e2) {
                com.bytedance.applog.log.o.a().a(Collections.singletonList("JsonUtils"), "Merge json interrupted.", e2, new Object[0]);
            }
        }
        return jSONObject2;
    }

    public static boolean a(JSONArray jSONArray) {
        return jSONArray == null || jSONArray.length() == 0;
    }

    public static boolean a(JSONObject jSONObject, Set<String> set) {
        for (String str : set) {
            if (!jSONObject.has(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(String str) {
        if (q.b(str)) {
            return false;
        }
        return (str.startsWith("{") && str.startsWith("}")) || (str.startsWith(Constants.ARRAY_TYPE) && str.endsWith("]"));
    }
}
