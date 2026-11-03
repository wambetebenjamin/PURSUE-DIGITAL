package com.bytedance.apm6.perf.base;

import org.json.JSONObject;
/* compiled from: BasePerfConfigParser.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2343a = "performance_modules";

    public static JSONObject parseData(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return jSONObject.optJSONObject("performance_modules").optJSONObject(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
