package com.bytedance.apm.agent.instrumentation;

import org.json.JSONObject;
/* compiled from: JSONObjectInstrumentation.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static final String f243a = "JSONInstrumentation";

    public static JSONObject init(String str) {
        com.bytedance.apm.agent.tracing.e.enterMethod("JSONObject", "init", com.bytedance.apm.constant.a.TRACE_TYPE_JSON);
        JSONObject jSONObject = new JSONObject(str);
        com.bytedance.apm.agent.tracing.e.exitMethod();
        return jSONObject;
    }
}
