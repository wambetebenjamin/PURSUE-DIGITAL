package com.bytedance.pipo.game.api;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: GoogleResultInfo.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private String f10095a;

    /* renamed from: b  reason: collision with root package name */
    private String f10096b;

    /* renamed from: c  reason: collision with root package name */
    private String f10097c;

    /* renamed from: d  reason: collision with root package name */
    private String f10098d;

    /* renamed from: e  reason: collision with root package name */
    private String f10099e;

    /* renamed from: f  reason: collision with root package name */
    private String f10100f;

    /* renamed from: g  reason: collision with root package name */
    private String f10101g;

    /* renamed from: h  reason: collision with root package name */
    private String f10102h;

    public String a() {
        return this.f10098d;
    }

    public d a(String str) {
        this.f10098d = str;
        return this;
    }

    public String b() {
        return this.f10099e;
    }

    public d b(String str) {
        this.f10099e = str;
        return this;
    }

    public String c() {
        return this.f10100f;
    }

    public d c(String str) {
        this.f10100f = str;
        return this;
    }

    public String d() {
        return this.f10101g;
    }

    public d d(String str) {
        this.f10101g = str;
        return this;
    }

    public String e() {
        return this.f10095a;
    }

    public d e(String str) {
        this.f10095a = str;
        return this;
    }

    public String f() {
        return this.f10096b;
    }

    public d f(String str) {
        this.f10096b = str;
        return this;
    }

    public String g() {
        return this.f10097c;
    }

    public d g(String str) {
        this.f10097c = str;
        return this;
    }

    public String h() {
        return this.f10102h;
    }

    public d h(String str) {
        this.f10102h = str;
        return this;
    }

    public JSONObject i() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("productId", this.f10095a);
            jSONObject.put("orderId", this.f10096b);
            jSONObject.put(gsdk.impl.account.toutiao.f.v, this.f10097c);
            jSONObject.put("extraPayload", this.f10102h);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return "{productId='" + this.f10095a + "', orderId='" + this.f10096b + "', userId='" + this.f10097c + "', extraPayload='" + this.f10102h + "', googleOrderId=" + this.f10101g + "'}";
    }
}
