package com.bytedance.tracing.internal;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: LogData.java */
/* loaded from: classes3.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private final long f14053a;

    /* renamed from: b  reason: collision with root package name */
    private final String f14054b;

    /* renamed from: c  reason: collision with root package name */
    private final Map<String, String> f14055c;

    public b(long j2, String str, Map<String, String> map) {
        this.f14053a = j2;
        this.f14054b = str;
        this.f14055c = map;
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("timestamp", this.f14053a);
            jSONObject.put("message", this.f14054b);
            Map<String, String> map = this.f14055c;
            if (map != null && map.size() > 0) {
                jSONObject.put("fields", new JSONObject(this.f14055c));
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}
