package com.bytedance.applog.monitor.v3;

import android.text.TextUtils;
import org.json.JSONObject;
/* compiled from: StageEventBasic.java */
/* loaded from: classes.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    public String f3016a;

    /* renamed from: b  reason: collision with root package name */
    public final String f3017b;

    /* renamed from: c  reason: collision with root package name */
    public long f3018c;

    /* renamed from: d  reason: collision with root package name */
    public m f3019d;

    /* renamed from: e  reason: collision with root package name */
    public String f3020e;

    /* renamed from: f  reason: collision with root package name */
    public int f3021f;

    public l(String str, String str2, long j2, m mVar) {
        this(null, -1, str, str2, j2, mVar);
    }

    public l(JSONObject jSONObject) {
        this(jSONObject.optString("priority_name"), jSONObject.optInt("group_id", -1), jSONObject.optString(com.bytedance.applog.priority.original.m.f3287h), jSONObject.optString(com.bytedance.applog.priority.original.m.f3283d), jSONObject.optLong("local_time_ms"), m.a(jSONObject.optString("type")));
    }

    public l(String str, int i2, String str2, String str3, long j2, m mVar) {
        this.f3021f = -1;
        this.f3020e = str;
        this.f3021f = i2;
        this.f3016a = str2;
        this.f3017b = str3;
        this.f3018c = j2;
        this.f3019d = mVar;
    }

    public void a(l lVar) {
        m mVar;
        int i2;
        if (TextUtils.equals(this.f3017b, lVar.f3017b)) {
            if (TextUtils.isEmpty(this.f3020e) && !TextUtils.isEmpty(lVar.f3020e)) {
                this.f3020e = lVar.f3020e;
            }
            if (this.f3021f < 0 && (i2 = lVar.f3021f) >= 0) {
                this.f3021f = i2;
            }
            if (TextUtils.isEmpty(this.f3016a) && !TextUtils.isEmpty(lVar.f3016a)) {
                this.f3016a = lVar.f3016a;
            }
            if (this.f3018c <= 0) {
                long j2 = lVar.f3018c;
                if (j2 > 0) {
                    this.f3018c = j2;
                }
            }
            if (this.f3019d != null || (mVar = lVar.f3019d) == null) {
                return;
            }
            this.f3019d = mVar;
        }
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(com.bytedance.applog.priority.original.m.f3287h, this.f3016a);
            jSONObject.put(com.bytedance.applog.priority.original.m.f3283d, this.f3017b);
            m mVar = this.f3019d;
            if (mVar != null) {
                jSONObject.put("type", mVar.name());
            }
            long j2 = this.f3018c;
            if (j2 > 0) {
                jSONObject.put("local_time_ms", j2);
            }
            String str = this.f3020e;
            if (str != null && !str.isEmpty()) {
                jSONObject.put("priority_name", this.f3020e);
            }
            int i2 = this.f3021f;
            if (i2 >= 0) {
                jSONObject.put("group_id", i2);
            }
        } catch (Throwable unused) {
        }
        return jSONObject;
    }
}
