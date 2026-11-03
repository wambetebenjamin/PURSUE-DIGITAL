package com.bytedance.ttgame.framework.gbridge.module;

import com.bytedance.ttgame.framework.gbridge.IBridgeModule;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public interface BaseModule extends IBridgeModule {
    public static final Gson GSON = new Gson();
    public static final String TAG = "SdkInteraction";

    static void add(JSONObject jSONObject, String str, int i2) {
        try {
            jSONObject.put(str, i2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    static void add(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    static void add(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    static <T> T fromJson(String str, Class<T> cls) {
        try {
            return (T) GSON.fromJson(str, (Class<Object>) cls);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    static <T> T fromJson(String str, Type type) {
        try {
            return (T) GSON.fromJson(str, type);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    static String toJson(Object obj) {
        try {
            return GSON.toJson(obj);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
