package com.bytedance.ttnet.http;

import com.bytedance.common.utility.o;
import org.json.JSONObject;
/* compiled from: GetDomainContext.java */
/* loaded from: classes8.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public int f15303a;

    /* renamed from: b  reason: collision with root package name */
    public String f15304b;

    /* renamed from: c  reason: collision with root package name */
    public long f15305c;

    /* renamed from: e  reason: collision with root package name */
    public String f15307e;

    /* renamed from: f  reason: collision with root package name */
    public String f15308f;

    /* renamed from: g  reason: collision with root package name */
    public String f15309g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f15310h;

    /* renamed from: j  reason: collision with root package name */
    public String f15312j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;

    /* renamed from: d  reason: collision with root package name */
    public String f15306d = "";

    /* renamed from: i  reason: collision with root package name */
    public boolean f15311i = true;

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("status", this.f15303a);
        jSONObject.put("url", this.f15304b);
        jSONObject.put("query_time", this.f15305c);
        if (!this.f15310h) {
            jSONObject.put("raw_sign", this.f15307e);
            jSONObject.put("ss_sign", this.f15308f);
            jSONObject.put("local_sign", this.f15309g);
        }
        if (!o.isEmpty(this.f15306d)) {
            jSONObject.put("err_msg", this.f15306d);
        }
        return jSONObject;
    }
}
