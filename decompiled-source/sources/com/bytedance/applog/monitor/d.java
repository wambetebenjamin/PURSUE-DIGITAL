package com.bytedance.applog.monitor;

import org.json.JSONObject;
/* compiled from: ReportData.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    static final String f2825a = "applog_trace";
    public String event = f2825a;
    public JSONObject jsonObject;

    public String getEvent() {
        return this.event;
    }

    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    public d(JSONObject jSONObject) {
        this.jsonObject = jSONObject;
    }
}
