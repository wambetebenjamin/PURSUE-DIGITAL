package com.bytedance.bdinstall;

import android.accounts.Account;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.bytedance.bdinstall.intf.c;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: Builder.java */
/* loaded from: classes.dex */
public class o {
    String A;
    String B;
    String C;
    String D;
    JSONObject E;
    Map<String, Object> F;
    Account G;
    ah I;

    /* renamed from: J  reason: collision with root package name */
    ad f3949J;
    aj L;
    boolean M;
    com.bytedance.bdinstall.intf.a N;
    com.bytedance.bdinstall.intf.b O;
    af Q;
    boolean W;
    com.bytedance.bdinstall.util.d Z;
    boolean aa;
    com.bytedance.bdinstall.sensitive.a ab;
    TelephonyManager ac;

    /* renamed from: c  reason: collision with root package name */
    at f3952c;

    /* renamed from: d  reason: collision with root package name */
    boolean f3953d;

    /* renamed from: e  reason: collision with root package name */
    ae f3954e;

    /* renamed from: g  reason: collision with root package name */
    boolean f3956g;

    /* renamed from: h  reason: collision with root package name */
    boolean f3957h;

    /* renamed from: i  reason: collision with root package name */
    boolean f3958i;

    /* renamed from: j  reason: collision with root package name */
    ab f3959j;
    boolean k;
    int l;
    ai m;
    Context n;
    String o;
    String p;
    String q;
    String r;
    long s;
    long t;
    long u;
    String v;
    String w;
    String x;
    String y;
    z z;

    /* renamed from: a  reason: collision with root package name */
    boolean f3950a = true;

    /* renamed from: b  reason: collision with root package name */
    boolean f3951b = false;

    /* renamed from: f  reason: collision with root package name */
    boolean f3955f = true;
    boolean H = false;
    boolean K = true;
    com.bytedance.bdinstall.intf.c P = new c.a();
    boolean R = true;
    boolean S = false;
    boolean T = false;
    boolean U = true;
    boolean V = false;
    boolean X = true;
    public boolean Y = false;
    boolean ad = true;
    boolean ae = false;

    @Deprecated
    public o i(boolean z) {
        return this;
    }

    public o() {
    }

    public o(Context context, int i2, String str, boolean z) {
        this.n = context;
        this.l = i2;
        this.v = str;
        this.f3957h = z;
    }

    public o a(Context context) {
        this.n = context;
        return this;
    }

    public o a(int i2) {
        this.l = i2;
        return this;
    }

    public o a(String str) {
        this.v = str;
        return this;
    }

    public o a(boolean z) {
        this.f3957h = z;
        return this;
    }

    public o a(ai aiVar) {
        this.m = aiVar;
        return this;
    }

    public o a(ae aeVar) {
        this.f3954e = aeVar;
        return this;
    }

    public o b(String str) {
        this.w = str;
        return this;
    }

    public o b(boolean z) {
        this.f3953d = z;
        return this;
    }

    public o c(String str) {
        this.r = str;
        return this;
    }

    public o a(long j2) {
        this.t = j2;
        return this;
    }

    public o d(String str) {
        this.y = str;
        return this;
    }

    public o a(z zVar) {
        this.z = zVar;
        return this;
    }

    public o c(boolean z) {
        this.H = z;
        return this;
    }

    public o e(String str) {
        this.A = str;
        return this;
    }

    public o a(Account account) {
        this.G = account;
        return this;
    }

    public o f(String str) {
        this.q = str;
        return this;
    }

    public o b(long j2) {
        this.s = j2;
        return this;
    }

    public o c(long j2) {
        this.u = j2;
        return this;
    }

    public o a(at atVar) {
        this.f3952c = atVar;
        return this;
    }

    public o d(boolean z) {
        this.K = z;
        return this;
    }

    @Deprecated
    public o g(String str) {
        this.x = str;
        return this;
    }

    public o e(boolean z) {
        ax.a(z);
        return this;
    }

    public o h(String str) {
        this.C = str;
        return this;
    }

    public o i(String str) {
        this.D = str;
        return this;
    }

    public o a(JSONObject jSONObject) {
        this.E = jSONObject;
        return this;
    }

    public o j(String str) {
        this.o = str;
        return this;
    }

    public o k(String str) {
        this.p = str;
        return this;
    }

    public o a(ad adVar) {
        this.f3949J = adVar;
        return this;
    }

    public o l(String str) {
        this.B = str;
        return this;
    }

    public o a(Map<String, Object> map) {
        this.F = map;
        return this;
    }

    public o a(ab abVar) {
        this.f3959j = abVar;
        return this;
    }

    public o f(boolean z) {
        this.k = z;
        return this;
    }

    public o g(boolean z) {
        this.f3958i = z;
        return this;
    }

    public o h(boolean z) {
        this.f3956g = z;
        return this;
    }

    public o j(boolean z) {
        this.f3955f = z;
        return this;
    }

    public o k(boolean z) {
        this.W = z;
        return this;
    }

    public o a(ah ahVar) {
        this.I = ahVar;
        return this;
    }

    public o a(aj ajVar) {
        this.L = ajVar;
        return this;
    }

    public o a(com.bytedance.bdinstall.intf.a aVar) {
        this.N = aVar;
        return this;
    }

    public o l(boolean z) {
        this.M = z;
        return this;
    }

    public o m(boolean z) {
        this.f3951b = z;
        return this;
    }

    public o n(boolean z) {
        this.f3950a = z;
        return this;
    }

    public o a(com.bytedance.bdinstall.intf.b bVar) {
        this.O = bVar;
        return this;
    }

    public o a(com.bytedance.bdinstall.intf.c cVar) {
        if (cVar != null) {
            this.P = cVar;
        }
        return this;
    }

    public o a(af afVar) {
        this.Q = afVar;
        return this;
    }

    public o o(boolean z) {
        this.R = z;
        return this;
    }

    public o p(boolean z) {
        this.S = z;
        return this;
    }

    public o q(boolean z) {
        this.T = z;
        return this;
    }

    public o r(boolean z) {
        this.U = z;
        return this;
    }

    public o s(boolean z) {
        this.X = z;
        return this;
    }

    public o t(boolean z) {
        this.Y = z;
        return this;
    }

    public o a(com.bytedance.bdinstall.util.d dVar) {
        this.Z = dVar;
        return this;
    }

    public o u(boolean z) {
        this.aa = z;
        return this;
    }

    public o a(com.bytedance.bdinstall.sensitive.a aVar) {
        this.ab = aVar;
        return this;
    }

    public o v(boolean z) {
        this.ad = z;
        return this;
    }

    public o w(boolean z) {
        this.ae = z;
        return this;
    }

    public am a() {
        if (this.n == null || this.l <= 0 || TextUtils.isEmpty(this.w)) {
            throw new IllegalArgumentException("parameters error:" + (this.n == null) + ", " + this.l + ", " + this.w);
        } else if (TextUtils.isEmpty(this.v)) {
            throw new IllegalArgumentException("appName is empty");
        } else {
            if (!u.a()) {
                if (this.m == null) {
                    throw new IllegalArgumentException("please set network client");
                }
                if (this.f3954e == null) {
                    throw new IllegalArgumentException("please set event depend");
                }
            }
            if (TextUtils.isEmpty(this.x)) {
                this.x = this.w;
            }
            if (this.ac == null) {
                this.ac = (TelephonyManager) this.n.getSystemService("phone");
            }
            com.bytedance.bdinstall.util.f.a().a(this.l);
            return new am(this);
        }
    }
}
