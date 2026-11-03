package com.bytedance.bdinstall;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.bytedance.bdinstall.ad;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: InstallOptions.java */
/* loaded from: classes.dex */
public class am {

    /* renamed from: c  reason: collision with root package name */
    private static final String f3682c = "applog_stats";
    private final boolean A;
    private final com.bytedance.bdinstall.intf.a B;
    private final com.bytedance.bdinstall.intf.b C;
    private final com.bytedance.bdinstall.intf.c D;
    private final af E;
    private final boolean F;
    private final boolean G;
    private final boolean H;
    private Account I;

    /* renamed from: J  reason: collision with root package name */
    private final ab f3683J;
    private final boolean K;
    private final boolean L;
    private final boolean M;
    private final boolean N;
    private final ae O;
    private final boolean P;
    private final at Q;
    private final boolean R;
    private final boolean S;
    private final boolean T;
    private final boolean U;
    private final boolean V;
    private final boolean W;
    private final boolean X;
    private final boolean Y;
    private final com.bytedance.bdinstall.util.d Z;

    /* renamed from: a  reason: collision with root package name */
    TelephonyManager f3684a;
    private final com.bytedance.bdinstall.sensitive.a aa;
    private final com.bytedance.bdinstall.sensitive.a ab;
    private SharedPreferences ac = null;

    /* renamed from: b  reason: collision with root package name */
    boolean f3685b;

    /* renamed from: d  reason: collision with root package name */
    private final int f3686d;

    /* renamed from: e  reason: collision with root package name */
    private final ai f3687e;

    /* renamed from: f  reason: collision with root package name */
    private final Context f3688f;

    /* renamed from: g  reason: collision with root package name */
    private final String f3689g;

    /* renamed from: h  reason: collision with root package name */
    private final String f3690h;

    /* renamed from: i  reason: collision with root package name */
    private final String f3691i;

    /* renamed from: j  reason: collision with root package name */
    private final String f3692j;
    private final String k;
    private final String l;
    private final z m;
    private final String n;
    private final String o;
    private final String p;
    private final String q;
    private final JSONObject r;
    private final Map<String, Object> s;
    private final boolean t;
    private final i u;
    private final boolean v;
    private final ah w;
    private final ad x;
    private final boolean y;
    private final aj z;

    /* JADX INFO: Access modifiers changed from: package-private */
    public am(o oVar) {
        this.f3688f = oVar.n;
        this.f3686d = oVar.l;
        this.f3687e = oVar.m != null ? oVar.m : new C0387r();
        this.f3689g = TextUtils.isEmpty(oVar.o) ? "applog_stats" : oVar.o;
        this.f3690h = oVar.p;
        this.f3691i = oVar.v;
        this.f3692j = oVar.w;
        this.k = oVar.x;
        this.l = oVar.y;
        this.m = oVar.z;
        this.n = oVar.A;
        this.o = oVar.B;
        this.p = oVar.C;
        this.q = oVar.D;
        this.r = oVar.E;
        this.s = oVar.F;
        this.f3683J = oVar.f3959j;
        this.K = oVar.k;
        this.L = oVar.f3958i;
        this.t = oVar.f3957h;
        this.M = oVar.f3956g;
        this.N = oVar.f3955f;
        this.O = oVar.f3954e;
        this.P = oVar.f3953d;
        this.Q = oVar.f3952c;
        this.u = new i(oVar);
        this.I = oVar.G;
        this.v = oVar.H;
        this.w = oVar.I;
        this.x = oVar.f3949J != null ? oVar.f3949J : new ad.a();
        this.y = oVar.K;
        this.z = oVar.L;
        this.A = oVar.M;
        this.B = oVar.N;
        this.R = oVar.f3951b;
        this.S = oVar.f3950a;
        this.C = oVar.O;
        this.D = oVar.P;
        this.E = oVar.Q;
        this.F = oVar.R;
        this.G = oVar.S;
        this.H = oVar.T;
        this.T = oVar.U;
        this.U = oVar.W;
        this.V = oVar.X;
        this.W = oVar.Y;
        this.Z = oVar.Z;
        com.bytedance.bdinstall.sensitive.a aVar = oVar.ab;
        this.aa = aVar;
        this.X = oVar.ad;
        this.Y = oVar.ae;
        this.f3684a = oVar.ac;
        if (aVar != null) {
            this.ab = aVar;
            aVar.setOptions(this);
            return;
        }
        com.bytedance.bdinstall.sensitive.a aVar2 = new com.bytedance.bdinstall.sensitive.a();
        this.ab = aVar2;
        aVar2.setOptions(this);
    }

    public int a() {
        return this.f3686d;
    }

    public String b() {
        return String.valueOf(this.f3686d);
    }

    public ai c() {
        return this.f3687e;
    }

    public Context d() {
        return this.f3688f;
    }

    public String e() {
        return this.f3690h;
    }

    public String f() {
        return this.u.a();
    }

    public String g() {
        return this.u.b();
    }

    public long h() {
        return this.u.c();
    }

    public long i() {
        return this.u.d();
    }

    public long j() {
        return this.u.e();
    }

    public String k() {
        return this.f3691i;
    }

    public String l() {
        return this.f3692j;
    }

    public String m() {
        return this.k;
    }

    public String n() {
        return this.l;
    }

    public z o() {
        return this.m;
    }

    public boolean p() {
        return this.f3685b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        this.f3685b = z;
    }

    public String q() {
        if (!TextUtils.isEmpty(this.n)) {
            return this.n;
        }
        return com.bytedance.bdinstall.util.j.a(this.f3688f, this).getString("user_agent", null);
    }

    public String r() {
        return this.o;
    }

    public String s() {
        if (!TextUtils.isEmpty(this.p)) {
            return this.p;
        }
        return com.bytedance.bdinstall.util.j.a(this.f3688f, this).getString("app_language", null);
    }

    public String t() {
        if (!TextUtils.isEmpty(this.q)) {
            return this.q;
        }
        return com.bytedance.bdinstall.util.j.a(this.f3688f, this).getString("app_region", null);
    }

    public JSONObject u() {
        JSONObject jSONObject = this.r;
        if (jSONObject != null) {
            return jSONObject;
        }
        String string = com.bytedance.bdinstall.util.j.a(this.f3688f, this).getString(e.S, null);
        if (string != null) {
            try {
                return new JSONObject(string);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public Map<String, Object> v() {
        return this.s;
    }

    public String w() {
        z zVar = this.m;
        if (zVar != null) {
            return zVar.getAbVersion();
        }
        return null;
    }

    public SharedPreferences x() {
        if (this.ac == null) {
            this.ac = this.f3688f.getSharedPreferences(this.f3685b ? this.f3689g : this.f3689g + "_" + this.f3686d, 0);
        }
        return this.ac;
    }

    public String y() {
        return x().getString("app_language", null);
    }

    public String z() {
        return x().getString("app_region", null);
    }

    public boolean A() {
        return this.M;
    }

    public boolean B() {
        return this.N;
    }

    public Map<String, Object> C() {
        ab abVar = this.f3683J;
        if (abVar != null) {
            return abVar.a();
        }
        return null;
    }

    public boolean D() {
        return this.K;
    }

    public boolean E() {
        return this.L;
    }

    public boolean F() {
        return this.t;
    }

    public ae G() {
        return this.O;
    }

    public boolean H() {
        return this.P;
    }

    public at I() {
        return this.Q;
    }

    public Account J() {
        return this.I;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Account account) {
        this.I = account;
    }

    public boolean K() {
        return this.v;
    }

    public boolean L() {
        return this.U;
    }

    public ah M() {
        return this.w;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ad N() {
        return this.x;
    }

    public String a(Context context) {
        String string = com.bytedance.bdinstall.util.j.a(context, this).getString("user_unique_id", null);
        return TextUtils.isEmpty(string) ? context.getSharedPreferences("header_custom", 0).getString("user_unique_id", null) : string;
    }

    public void a(SharedPreferences.Editor editor) {
        String s = s();
        String t = t();
        if (!TextUtils.isEmpty(s)) {
            editor.putString("app_language", s);
        }
        if (TextUtils.isEmpty(t)) {
            return;
        }
        editor.putString("app_region", t);
    }

    public aj O() {
        return this.z;
    }

    public boolean P() {
        return this.A;
    }

    public com.bytedance.bdinstall.intf.a Q() {
        return this.B;
    }

    public boolean R() {
        return this.R;
    }

    public boolean S() {
        return this.S;
    }

    public com.bytedance.bdinstall.intf.c T() {
        return this.D;
    }

    public com.bytedance.bdinstall.intf.b U() {
        return this.C;
    }

    public af V() {
        return this.E;
    }

    public boolean W() {
        return this.F;
    }

    public boolean X() {
        return this.G;
    }

    public boolean Y() {
        return this.H;
    }

    public boolean Z() {
        return this.T;
    }

    public boolean aa() {
        return this.V;
    }

    public boolean ab() {
        return this.W;
    }

    public String ac() {
        com.bytedance.bdinstall.util.d dVar = this.Z;
        if (dVar != null) {
            return dVar.a();
        }
        return null;
    }

    public com.bytedance.bdinstall.sensitive.a ad() {
        return this.ab;
    }

    public TelephonyManager ae() {
        return this.f3684a;
    }

    public boolean af() {
        return this.X;
    }

    public boolean ag() {
        return this.Y;
    }
}
