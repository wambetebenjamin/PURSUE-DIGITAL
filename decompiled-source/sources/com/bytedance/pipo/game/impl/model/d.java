package com.bytedance.pipo.game.impl.model;

import android.text.TextUtils;
import com.bytedance.pipo.game.api.l;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: PayRequest.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private String f10350a;

    /* renamed from: b  reason: collision with root package name */
    private com.bytedance.pipo.game.api.c f10351b;

    /* renamed from: c  reason: collision with root package name */
    private String f10352c;

    /* renamed from: d  reason: collision with root package name */
    private String f10353d;

    /* renamed from: e  reason: collision with root package name */
    private String f10354e;

    /* renamed from: f  reason: collision with root package name */
    private String f10355f;

    /* renamed from: g  reason: collision with root package name */
    private String f10356g;

    /* renamed from: h  reason: collision with root package name */
    private String f10357h;

    /* renamed from: i  reason: collision with root package name */
    private String f10358i;

    /* renamed from: j  reason: collision with root package name */
    private c f10359j;
    private b k;
    private com.bytedance.pipo.game.impl.monitor.e l;
    private l m;
    private String n;
    private volatile boolean o;
    private volatile boolean p;
    private volatile boolean q;
    private volatile boolean r;
    private volatile boolean s;
    private long t;
    private boolean u;
    private String v;
    private String w;

    public d() {
        this.m = l.NOMAL;
        this.u = false;
        this.v = "";
        this.w = "";
        this.t = 0L;
    }

    public d(com.bytedance.pipo.game.api.c cVar) {
        this(cVar, l.NOMAL);
    }

    public d(com.bytedance.pipo.game.api.c cVar, l lVar) {
        this.m = l.NOMAL;
        this.u = false;
        this.v = "";
        this.w = "";
        this.t = 0L;
        this.t = cVar.k();
        this.m = lVar;
        this.f10351b = cVar;
        this.f10352c = cVar.d();
        this.f10353d = cVar.h();
        this.f10354e = cVar.b();
        this.f10350a = cVar.e();
    }

    public String a() {
        return this.n;
    }

    public d a(String str) {
        this.n = str;
        return this;
    }

    public com.bytedance.pipo.game.api.c b() {
        return this.f10351b;
    }

    public String c() {
        return this.f10356g;
    }

    public d b(String str) {
        this.f10356g = str;
        return this;
    }

    public String d() {
        return this.f10355f;
    }

    public d c(String str) {
        this.f10355f = str;
        return this;
    }

    public String e() {
        return this.f10357h;
    }

    public d d(String str) {
        this.f10357h = str;
        return this;
    }

    public String f() {
        return this.f10358i;
    }

    public d e(String str) {
        this.f10358i = str;
        return this;
    }

    public String g() {
        return this.f10352c;
    }

    public d f(String str) {
        this.f10352c = str;
        return this;
    }

    public String h() {
        return this.f10350a;
    }

    public d g(String str) {
        this.f10350a = str;
        return this;
    }

    public String i() {
        return this.f10353d;
    }

    public d h(String str) {
        this.f10353d = str;
        return this;
    }

    public String j() {
        return this.f10354e;
    }

    public d i(String str) {
        this.f10354e = str;
        return this;
    }

    public c k() {
        return this.f10359j;
    }

    public d a(c cVar) {
        this.f10359j = cVar;
        return this;
    }

    public b l() {
        return this.k;
    }

    public d a(b bVar) {
        this.k = bVar;
        return this;
    }

    public com.bytedance.pipo.game.impl.monitor.e m() {
        return this.l;
    }

    public d a(com.bytedance.pipo.game.impl.monitor.e eVar) {
        this.l = eVar;
        return this;
    }

    public void n() {
        this.o = true;
    }

    public void o() {
        this.p = true;
    }

    public void p() {
        this.q = true;
    }

    public boolean q() {
        return this.r;
    }

    public void r() {
        this.r = true;
    }

    public boolean s() {
        return this.s;
    }

    public void t() {
        this.s = true;
    }

    public boolean u() {
        return this.o;
    }

    public boolean v() {
        return this.p;
    }

    public boolean w() {
        return this.q;
    }

    public boolean x() {
        return this.o && this.r;
    }

    public l y() {
        return this.m;
    }

    public com.bytedance.pipo.game.api.d z() {
        com.bytedance.pipo.game.api.d g2 = new com.bytedance.pipo.game.api.d().e(g()).f(j()).g(i());
        String str = null;
        try {
            if (!TextUtils.isEmpty(d())) {
                str = d();
            } else {
                c cVar = this.f10359j;
                if (cVar != null) {
                    str = cVar.m();
                }
            }
            if (!TextUtils.isEmpty(str)) {
                g2.d(new JSONObject(str).optString("orderId"));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        com.bytedance.pipo.game.api.c cVar2 = this.f10351b;
        if (cVar2 != null) {
            g2.c(cVar2.a()).a(this.f10351b.c()).b(this.f10351b.e()).h(this.f10351b.j());
        }
        return g2;
    }

    public long A() {
        return this.t;
    }

    public d a(long j2) {
        this.t = j2;
        return this;
    }

    public String toString() {
        return "PayRequest{mServerId='" + this.f10350a + "', mGoogleRequest=" + this.f10351b + ", mProductId='" + this.f10352c + "', mOpenId='" + this.f10353d + "', mOrderId='" + this.f10354e + "', mToken='" + this.f10355f + "', mPurchaseToken='" + this.f10356g + "', mAmount='" + this.f10357h + "', mCurrency='" + this.f10358i + "', mPurchase=" + this.f10359j + ", mProductDetails=" + this.k + ", mPayMonitor=" + this.l + ", mPayType=" + this.m + ", mExtraPayScene='" + this.n + "', isPromoCode=" + this.u + ", regionCode='" + this.v + "', extra='" + this.w + "', mExecuted=" + this.o + ", mCanceled=" + this.p + ", mFinished=" + this.q + ", mConsumed=" + this.r + ", mQuerySucceed=" + this.s + ", mStartPayTimeStamp=" + this.t + '}';
    }

    public boolean B() {
        return this.u;
    }

    public void a(boolean z) {
        this.u = z;
    }

    public String C() {
        return this.v;
    }

    public void j(String str) {
        this.v = str;
    }

    public String D() {
        return this.w;
    }

    public void k(String str) {
        this.w = str;
    }
}
