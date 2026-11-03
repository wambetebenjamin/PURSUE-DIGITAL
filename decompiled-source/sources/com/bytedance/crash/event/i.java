package com.bytedance.crash.event;

import com.bytedance.crash.Global;
import org.json.JSONObject;
/* compiled from: EventReportData.java */
/* loaded from: classes2.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6734a = "service_monitor";

    /* renamed from: b  reason: collision with root package name */
    private String f6735b;

    /* renamed from: c  reason: collision with root package name */
    private long f6736c;

    /* renamed from: d  reason: collision with root package name */
    private String f6737d;

    /* renamed from: e  reason: collision with root package name */
    private int f6738e;

    /* renamed from: f  reason: collision with root package name */
    private JSONObject f6739f;

    /* renamed from: g  reason: collision with root package name */
    private JSONObject f6740g;

    public i(String str, long j2, String str2, int i2, JSONObject jSONObject, JSONObject jSONObject2) {
        this.f6735b = str;
        this.f6736c = j2;
        this.f6737d = str2;
        this.f6738e = i2;
        this.f6739f = jSONObject;
        this.f6740g = jSONObject2;
    }

    public String a() {
        return this.f6737d;
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("log_type", "service_monitor");
            jSONObject.put("event_type", "exception");
            jSONObject.put("service", this.f6737d);
            jSONObject.put("status", this.f6738e);
            jSONObject.put("category", this.f6739f);
            jSONObject.put("metric", this.f6740g);
            jSONObject.put("sid", Global.g());
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put("crash_time", this.f6736c);
            jSONObject.put("process_name", com.bytedance.crash.util.b.a());
            jSONObject.put(com.bytedance.crash.entity.b.CRASH_THREAD_NAME, this.f6735b);
        } catch (Exception unused) {
        }
        return jSONObject;
    }
}
