package com.bytedance.router.dynamic;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: RouterConfig.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private String f12181a;

    /* renamed from: b  reason: collision with root package name */
    private Map<String, String> f12182b = Collections.emptyMap();

    /* renamed from: c  reason: collision with root package name */
    private Map<String, String> f12183c = Collections.emptyMap();

    public a(String str) {
        this.f12181a = "";
        this.f12181a = str;
    }

    public void setIncMapping(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            this.f12182b = Collections.emptyMap();
        } else {
            this.f12182b = map;
        }
    }

    public void setRewriteMapping(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            this.f12183c = Collections.emptyMap();
        } else {
            this.f12183c = map;
        }
    }

    public Map<String, String> getIncMapping() {
        return this.f12182b;
    }

    public Map<String, String> getRewriteMapping() {
        return this.f12183c;
    }

    public static a load(String str) {
        return a(str);
    }

    private static a a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            a aVar = new a(jSONObject.optString("tag"));
            aVar.setIncMapping(parseJsonArray(jSONObject.optJSONArray("incMapping")));
            aVar.setRewriteMapping(parseJsonArray(jSONObject.optJSONArray("rewriteMapping")));
            return aVar;
        } catch (JSONException e2) {
            e2.printStackTrace();
            com.bytedance.router.util.b.e("DynamicMapping#load failed: " + str);
            return null;
        }
    }

    public static Map<String, String> parseJsonArray(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            String optString = jSONArray.optString(i2);
            if (!TextUtils.isEmpty(optString)) {
                String[] split = optString.split(",");
                if (split.length != 2) {
                    com.bytedance.router.util.b.e("DynamicMapping#parseJsonArray illegal schema mapping: " + optString);
                } else if (split[0] == null || split[1] == null) {
                    com.bytedance.router.util.b.e("DynamicMapping#parseJsonArray illegal schema mapping(null): " + optString);
                } else {
                    split[0] = split[0].trim();
                    split[1] = split[1].trim();
                    if (TextUtils.isEmpty(split[0]) || TextUtils.isEmpty(split[1])) {
                        com.bytedance.router.util.b.e("DynamicMapping#parseJsonArray illegal schema mapping empty: " + optString);
                    } else {
                        hashMap.put(split[0], split[1]);
                    }
                }
            }
        }
        return hashMap;
    }

    public static boolean isExpired(Context context, a aVar) {
        Bundle bundle;
        if (context == null || aVar == null) {
            return true;
        }
        String str = "";
        try {
            str = "" + context.getPackageManager().getPackageInfo(context.getPackageName(), 128).versionCode;
            if (context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData != null) {
                str = str + "_" + String.valueOf(bundle.getInt("UPDATE_VERSION_CODE"));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return TextUtils.isEmpty(str) || !str.equals(aVar.f12181a);
    }

    public String toString() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tag", this.f12181a);
            JSONArray jSONArray = new JSONArray();
            for (Map.Entry<String, String> entry : this.f12182b.entrySet()) {
                jSONArray.put(entry.getKey() + "," + entry.getValue());
            }
            jSONObject.put("incMapping", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            for (Map.Entry<String, String> entry2 : this.f12183c.entrySet()) {
                jSONArray2.put(entry2.getKey() + "," + entry2.getValue());
            }
            jSONObject.put("rewriteMapping", jSONArray2);
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
