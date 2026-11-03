package com.bytedance.tracing.internal;

import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: BatchTracingLogWrapUtils.java */
/* loaded from: classes3.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14052a = "wrapper_array_data";

    public static JSONObject wrapData(JSONArray jSONArray) {
        return new JSONObject().put("wrapper_array_data", jSONArray);
    }

    public static JSONArray stripData(JSONObject jSONObject) {
        JSONArray optJSONArray;
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("wrapper_array_data")) == null) {
            return null;
        }
        return optJSONArray;
    }
}
