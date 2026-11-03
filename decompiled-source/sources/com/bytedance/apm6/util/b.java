package com.bytedance.apm6.util;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: BatchLogWrapUtils.java */
/* loaded from: classes.dex */
public class b {
    public static final String KEY_WRAPPER_ARRAY_DATA = "wrapper_array_data";

    /* renamed from: a  reason: collision with root package name */
    private static final String f2376a = "wrapper_type_description";

    /* renamed from: b  reason: collision with root package name */
    private static final int f2377b = 1;

    public static JSONObject wrapData(String str, JSONArray jSONArray) {
        try {
            JSONObject put = new JSONObject().put(KEY_WRAPPER_ARRAY_DATA, jSONArray);
            put.put("log_type", str);
            put.put(f2376a, 1);
            return put;
        } catch (Exception unused) {
            return null;
        }
    }

    public static List<Object> stripData(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (jSONObject.optInt(f2376a, -1) != 1) {
            arrayList.add(jSONObject);
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(KEY_WRAPPER_ARRAY_DATA);
        if (optJSONArray == null) {
            return null;
        }
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            try {
                arrayList.add(optJSONArray.get(i2));
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }
}
