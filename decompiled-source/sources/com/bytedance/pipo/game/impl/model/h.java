package com.bytedance.pipo.game.impl.model;

import org.json.JSONObject;
/* compiled from: TokenInfo.java */
/* loaded from: classes2.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private String f10389a;

    /* renamed from: b  reason: collision with root package name */
    private String f10390b;

    /* renamed from: c  reason: collision with root package name */
    private String f10391c;

    /* renamed from: e  reason: collision with root package name */
    private String f10393e;

    /* renamed from: f  reason: collision with root package name */
    private String f10394f;

    /* renamed from: g  reason: collision with root package name */
    private String f10395g;

    /* renamed from: h  reason: collision with root package name */
    private String f10396h;

    /* renamed from: i  reason: collision with root package name */
    private String f10397i;

    /* renamed from: j  reason: collision with root package name */
    private String f10398j;
    private String k;
    private String l;
    private String m;

    /* renamed from: d  reason: collision with root package name */
    private String f10392d = com.bytedance.pipo.payment.common.lib.c.Y;
    private boolean o = false;
    private boolean p = false;
    private String q = "";
    private String r = "";
    private boolean n = false;

    public String a() {
        return this.m;
    }

    public h a(String str) {
        this.m = str;
        return this;
    }

    public String b() {
        return this.f10394f;
    }

    public h b(String str) {
        this.f10394f = str;
        return this;
    }

    public String c() {
        return this.l;
    }

    public h c(String str) {
        this.l = str;
        return this;
    }

    public String d() {
        return this.k;
    }

    public h d(String str) {
        this.k = str;
        return this;
    }

    public boolean e() {
        return this.o;
    }

    public h a(boolean z) {
        this.o = z;
        return this;
    }

    public String f() {
        return this.f10398j;
    }

    public h e(String str) {
        this.f10398j = str;
        return this;
    }

    public String g() {
        return this.f10389a;
    }

    public h f(String str) {
        this.f10389a = str;
        return this;
    }

    public String h() {
        return this.f10390b;
    }

    public h g(String str) {
        this.f10390b = str;
        return this;
    }

    public String i() {
        return this.f10391c;
    }

    public h h(String str) {
        this.f10391c = str;
        return this;
    }

    public String j() {
        return this.f10392d;
    }

    public h i(String str) {
        this.f10392d = str;
        return this;
    }

    public String k() {
        return this.f10393e;
    }

    public h j(String str) {
        this.f10393e = str;
        return this;
    }

    public String l() {
        return this.f10395g;
    }

    public h k(String str) {
        this.f10395g = str;
        return this;
    }

    public String m() {
        return this.f10396h;
    }

    public h l(String str) {
        this.f10396h = str;
        return this;
    }

    public String n() {
        return this.f10397i;
    }

    public h m(String str) {
        this.f10397i = str;
        return this;
    }

    public boolean o() {
        return this.n;
    }

    public h b(boolean z) {
        this.n = z;
        return this;
    }

    public JSONObject p() {
        if (this.o) {
            return r();
        }
        return q();
    }

    public JSONObject q() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("order_id", this.f10389a);
        jSONObject.put(com.bytedance.pipo.payment.common.lib.c.am, this.f10390b);
        jSONObject.put(com.bytedance.pipo.payment.common.lib.c.H, this.f10391c);
        jSONObject.put(com.bytedance.pipo.payment.common.lib.c.av, this.f10392d);
        jSONObject.put("token", this.f10393e);
        jSONObject.put("amount_value", this.f10395g);
        jSONObject.put("currency", this.f10396h);
        jSONObject.put(com.bytedance.pipo.payment.common.lib.c.cG, this.f10397i);
        jSONObject.put("order_from_other_system", this.n);
        return jSONObject;
    }

    public JSONObject r() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("MerchantSubscriptionID", this.f10389a);
        jSONObject.put("ChannelType", 4);
        jSONObject.put("Token", this.f10393e);
        return jSONObject;
    }

    public String toString() {
        return "TokenInfo{orderId='" + this.f10389a + "', productId='" + this.f10390b + "', openId='" + this.f10391c + "', paymentMethod='" + this.f10392d + "', token='" + this.f10393e + "', purchaseToken='" + this.f10394f + "', amountValue='" + this.f10395g + "', currency='" + this.f10396h + "', channelOrderId='" + this.f10397i + "', appId='" + this.f10398j + "', roleId='" + this.k + "', serverId='" + this.l + "', purchaseId='" + this.m + "', orderFromOtherSystem=" + this.n + ", isSubscription=" + this.o + ", isPromoCode=" + this.p + ", regionCode='" + this.q + "', extra='" + this.r + "'}";
    }

    public boolean s() {
        return this.p;
    }

    public h c(boolean z) {
        this.p = z;
        return this;
    }

    public String t() {
        return this.q;
    }

    public h n(String str) {
        this.q = str;
        return this;
    }

    public String u() {
        return this.r;
    }

    public h o(String str) {
        this.r = str;
        return this;
    }
}
