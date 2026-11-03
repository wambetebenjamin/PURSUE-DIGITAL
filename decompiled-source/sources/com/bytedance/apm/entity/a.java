package com.bytedance.apm.entity;

import com.bytedance.apm.constant.s;
import org.json.JSONObject;
/* compiled from: ApiAllLocalLog.java */
/* loaded from: classes.dex */
public class a extends f {
    public int front;
    public int hitRules;

    /* renamed from: net  reason: collision with root package name */
    public int f1024net;
    public long sid;
    public long trafficValue;

    public a() {
    }

    public a(long j2, String str, long j3, JSONObject jSONObject) {
        super(j2, str, j3, jSONObject);
    }

    public a(long j2, String str, long j3, String str2) {
        super(j2, str, j3, str2);
    }

    @Override // com.bytedance.apm.entity.f
    public f setData(JSONObject jSONObject) {
        this.front = jSONObject.optInt("front");
        this.sid = jSONObject.optLong("sid");
        this.f1024net = jSONObject.optInt("network_type");
        this.hitRules = jSONObject.optInt("hit_rules");
        this.trafficValue = jSONObject.optLong(s.KEY_SENT_BYTES, 0L) + jSONObject.optLong(s.KEY_RECEIVED_BYTES, 0L);
        return super.setData(jSONObject);
    }
}
