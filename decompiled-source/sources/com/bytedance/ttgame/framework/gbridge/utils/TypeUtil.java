package com.bytedance.ttgame.framework.gbridge.utils;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class TypeUtil {
    public static List convertToList(Object obj) {
        JSONArray jSONArray = (JSONArray) obj;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                arrayList.add(jSONArray.get(i2));
            } catch (JSONException e2) {
                LogUtil.e("convertToList", e2);
            }
        }
        return arrayList;
    }

    public static Map convertToMap(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                hashMap.put(next, jSONObject.get(next));
            } catch (JSONException e2) {
                LogUtil.e("convertToMap", e2);
            }
        }
        return hashMap;
    }

    public static boolean isJsonType(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            new JSONObject(str);
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    public static HashMap convertToMapFromJson(Object obj) {
        HashMap hashMap = new HashMap();
        if (obj == null) {
            return hashMap;
        }
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(obj.toString());
        } catch (JSONException e2) {
            LogUtil.e("1-convertToMapFromJson", e2);
        }
        if (jSONObject == null) {
            return hashMap;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                hashMap.put(next, jSONObject.get(next));
            } catch (JSONException e3) {
                LogUtil.e("2-convertToMapFromJson", e3);
            }
        }
        return hashMap;
    }
}
