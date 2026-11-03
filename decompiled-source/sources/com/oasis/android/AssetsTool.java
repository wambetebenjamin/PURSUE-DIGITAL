package com.oasis.android;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class AssetsTool {

    /* renamed from: b  reason: collision with root package name */
    private static AssetsTool f17353b = null;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17354c = true;

    /* renamed from: a  reason: collision with root package name */
    private final Context f17355a;

    private AssetsTool(Context context) {
        this.f17355a = context;
    }

    private List<Object> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            Object obj = jSONArray.get(i2);
            if (obj instanceof JSONArray) {
                obj = a((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = a((JSONObject) obj);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }

    private Map<String, Object> a(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONArray) {
                obj = a((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = a((JSONObject) obj);
            }
            hashMap.put(next, obj);
        }
        return hashMap;
    }

    public static AssetsTool createInstance(Context context) {
        if (f17354c || f17353b == null) {
            AssetsTool assetsTool = new AssetsTool(context);
            f17353b = assetsTool;
            return assetsTool;
        }
        throw new AssertionError();
    }

    public static AssetsTool getInstance() {
        if (f17354c || f17353b != null) {
            return f17353b;
        }
        throw new AssertionError();
    }

    public Map<String, Object> loadJsonObject(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.f17355a.getAssets().open(str)));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return a(new JSONObject(sb.toString()));
                }
                sb.append(readLine);
            }
        } catch (IOException | JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public Map<String, Object> loadJsonObjectFromString(String str) {
        try {
            return a(new JSONObject(str));
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
