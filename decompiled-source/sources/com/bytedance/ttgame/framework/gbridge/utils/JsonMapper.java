package com.bytedance.ttgame.framework.gbridge.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class JsonMapper {
    private JsonMapper() {
    }

    public static JSONObject toJson(Object obj) {
        Field[] declaredFields;
        if (obj == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field != null) {
                field.setAccessible(true);
                try {
                    Object obj2 = field.get(obj);
                    if (obj2 != null) {
                        if (isBasicData(obj2)) {
                            jSONObject.put(field.getName(), obj2);
                        } else if (obj2 instanceof List) {
                            jSONObject.put(field.getName(), toJson((List<?>) obj2));
                        } else if (obj2 instanceof Map) {
                            jSONObject.put(field.getName(), toJson((Map<String, Object>) obj2));
                        } else if (obj2.getClass().isArray()) {
                            jSONObject.put(field.getName(), toJson((Object[]) obj2));
                        } else {
                            jSONObject.put(field.getName(), toJson(obj2));
                        }
                    }
                } catch (IllegalAccessException | IllegalArgumentException | JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return jSONObject;
    }

    public static JSONObject toJson(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry != null) {
                try {
                    Object value = entry.getValue();
                    if (isBasicData(value)) {
                        jSONObject.put(entry.getKey(), value);
                    } else if (value instanceof List) {
                        jSONObject.put(entry.getKey(), toJson((List<?>) value));
                    } else if (value instanceof Map) {
                        jSONObject.put(entry.getKey(), toJson((Map<String, Object>) value));
                    } else if (value.getClass().isArray()) {
                        jSONObject.put(entry.getKey(), toJson((Object[]) value));
                    } else {
                        jSONObject.put(entry.getKey(), toJson(value));
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return jSONObject;
    }

    public static JSONArray toJson(List<?> list) {
        return toJson(list.toArray());
    }

    public static JSONArray toJson(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Object obj : objArr) {
            if (obj != null) {
                if (isBasicData(obj)) {
                    jSONArray.put(obj);
                } else if (obj instanceof List) {
                    jSONArray.put(toJson((List<?>) obj));
                } else if (obj instanceof Map) {
                    jSONArray.put(toJson((Map<String, Object>) obj));
                } else {
                    jSONArray.put(toJson(obj));
                }
            }
        }
        return jSONArray;
    }

    private static boolean isBasicData(Object obj) {
        if (obj == null) {
            return false;
        }
        return (obj instanceof String) || (obj instanceof Integer) || (obj instanceof Boolean) || (obj instanceof Character) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Double);
    }
}
