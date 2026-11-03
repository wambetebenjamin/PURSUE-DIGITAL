package com.bytedance.apm6.commonevent.model;

import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: TracingData.java */
/* loaded from: classes.dex */
public class d implements com.bytedance.apm6.monitor.c {

    /* renamed from: a  reason: collision with root package name */
    private JSONArray f1744a;

    @Override // com.bytedance.apm6.monitor.c
    public String getLogType() {
        return "tracing";
    }

    @Override // com.bytedance.apm6.monitor.c
    public boolean isValid() {
        return true;
    }

    public d(JSONArray jSONArray) {
        this.f1744a = jSONArray;
    }

    public d(JSONObject jSONObject) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject);
        this.f1744a = jSONArray;
    }

    @Override // com.bytedance.apm6.monitor.c
    public final JSONObject toJsonObject() {
        return com.bytedance.apm6.util.b.wrapData(getLogType(), this.f1744a);
    }
}
