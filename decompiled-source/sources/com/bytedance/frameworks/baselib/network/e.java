package com.bytedance.frameworks.baselib.network;

import android.content.Context;
import com.bytedance.common.utility.Logger;
import com.bytedance.sdk.account.platform.base.i;
import com.bytedance.ttgame.core.event.Constant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: TTNetInitMetrics.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7575a = "e";
    public static volatile e ah = null;
    static final /* synthetic */ boolean ai = true;
    private static final String aj = "com.bytedance.mobsec.metasec.ml.MSB";
    private static final String ak = "com.bytedance.mobsec.metasec.ov.MSB";
    private static final String al = "a";
    private static final String am = "http_callback";
    private static final String an = "ws_callback";
    private static final int ao = 1;
    private static volatile CopyOnWriteArrayList<b> ap = new CopyOnWriteArrayList<>();
    private static AtomicBoolean aq = new AtomicBoolean(false);

    /* renamed from: b  reason: collision with root package name */
    public static final String f7576b = "ttnet_init";

    /* renamed from: c  reason: collision with root package name */
    public static final String f7577c = "ttnet_init_v2";
    public String O;
    public String Q;

    /* renamed from: f  reason: collision with root package name */
    public int f7581f;

    /* renamed from: d  reason: collision with root package name */
    public a f7579d = a.NONE;

    /* renamed from: e  reason: collision with root package name */
    public boolean f7580e = true;

    /* renamed from: g  reason: collision with root package name */
    public long f7582g = 0;

    /* renamed from: h  reason: collision with root package name */
    public long f7583h = 0;

    /* renamed from: i  reason: collision with root package name */
    public long f7584i = 0;

    /* renamed from: j  reason: collision with root package name */
    public long f7585j = 0;
    public long k = 0;
    public long l = 0;
    public long m = 0;
    public long n = 0;
    public long o = 0;
    public long p = 0;
    public long q = 0;
    public long r = 0;
    public long s = 0;
    public long t = 0;
    public long u = 0;
    public long v = 0;
    public long w = 0;
    public long x = 0;
    public long y = 0;
    public long z = 0;
    public long A = 0;
    public long B = 0;
    public long C = 0;
    public long D = 0;
    public long E = 0;
    public long F = 0;
    public long G = 0;
    public long H = 0;
    public long I = 0;

    /* renamed from: J  reason: collision with root package name */
    public long f7578J = 0;
    public long K = 0;
    public long L = 0;
    public long M = 0;
    public long N = 0;
    public long P = 0;
    public long R = 0;
    public long S = 0;
    public long T = 0;
    public long U = 0;
    public long V = 0;
    public long W = 0;
    public long X = 0;
    public long Y = 0;
    public long Z = 0;
    public long aa = 0;
    public long ab = 0;
    public long ac = 0;
    public long ad = 0;
    public long ae = 0;
    public long af = 0;
    public long ag = 0;

    /* compiled from: TTNetInitMetrics.java */
    /* loaded from: classes2.dex */
    public interface b {
        void a();
    }

    /* compiled from: TTNetInitMetrics.java */
    /* loaded from: classes2.dex */
    public enum a {
        NONE(0),
        LAZY_INIT(1),
        PRE_INIT(2),
        FORCE_INIT(3),
        DEPRECATED_API(4),
        BIZ_HTTPDNS_API(5),
        ZSTD_API(6),
        COOKIE_INIT_COMPLETE_API(7);
        

        /* renamed from: i  reason: collision with root package name */
        final int f7595i;

        a(int i2) {
            this.f7595i = i2;
        }
    }

    public static void a(b bVar) {
        ap.add(bVar);
    }

    public static void a(boolean z) {
        aq.set(z);
        if (ap.isEmpty()) {
            return;
        }
        new com.bytedance.common.utility.concurrent.e("ttnet-init") { // from class: com.bytedance.frameworks.baselib.network.e.1
            @Override // com.bytedance.common.utility.concurrent.e, java.lang.Runnable
            public void run() {
                Iterator it = e.ap.iterator();
                while (it.hasNext()) {
                    ((b) it.next()).a();
                }
            }
        }.start();
    }

    public static boolean a() {
        return aq.get();
    }

    public boolean b() {
        return this.f7579d == a.PRE_INIT || this.f7579d == a.FORCE_INIT;
    }

    public static e c() {
        if (ah == null) {
            synchronized (e.class) {
                if (ah == null) {
                    ah = new e();
                }
            }
        }
        return ah;
    }

    public void a(Context context, List<Long> list) {
        if (!ai && list.size() != 2) {
            throw new AssertionError();
        }
        if (list == null || list.size() != 2) {
            return;
        }
        if (Logger.debug()) {
            Logger.d(f7575a, "http address: " + list.get(0) + " ws address: " + list.get(1));
        }
        this.H = System.nanoTime();
        if (!b(context, list) && Logger.debug()) {
            Logger.d(f7575a, "init mssdk failed.");
        }
        this.I = System.nanoTime();
    }

    public void a(Context context) {
        this.f7584i = System.nanoTime();
        b(context, (List<Long>) null);
        this.f7585j = System.nanoTime();
    }

    private boolean b(Context context, List<Long> list) {
        Class<?> cls;
        HashMap hashMap = null;
        try {
            cls = Class.forName(aj);
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls == null) {
            try {
                cls = Class.forName(ak);
            } catch (ClassNotFoundException unused2) {
            }
        }
        if (cls == null) {
            return false;
        }
        if (list != null) {
            try {
                if (list.size() == 2) {
                    hashMap = new HashMap();
                    hashMap.put(am, String.valueOf(list.get(0)));
                    hashMap.put(an, String.valueOf(list.get(1)));
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
        cls.getMethod(al, Context.class, Integer.TYPE, Map.class).invoke(cls, context, 1, hashMap);
        return true;
    }

    public JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.put("mode", this.f7579d.f7595i);
            jSONObject.put("is_main_process", this.f7580e);
            jSONObject.put("net_thread_priority", this.f7581f);
            jSONObject2.put("ttnet_start", this.f7582g);
            jSONObject2.put("ttnet_end", b(this.k));
            jSONObject2.put("engine_start", b(this.q));
            jSONObject2.put("engine_end", b(this.f7578J));
            jSONObject2.put(Constant.INIT_START, b(this.K));
            jSONObject2.put(Constant.INIT_END, b(this.L));
            jSONObject2.put("preconnect_start", this.R);
            jSONObject3.put("builder_build", c(this.F, this.G));
            jSONObject3.put("load_cronet", c(this.M, this.N));
            jSONObject3.put("init_metasec", c(this.f7584i, this.f7585j));
            jSONObject3.put("init_ttnet", c(this.f7583h, this.k));
            jSONObject3.put("init_mssdk", c(this.H, this.I));
            jSONObject3.put("create_engine", c(this.q, this.f7578J));
            jSONObject3.put("init_thread", c(this.K, this.L));
            jSONObject3.put("init_total", c(Math.min(this.f7582g, this.p) * 1000000, this.T * 1000000));
            jSONObject3.put("init_preconnect", c(this.f7582g, this.R));
            long j2 = this.S;
            long j3 = this.T;
            long j4 = -1;
            jSONObject3.put("network_thread", j2 > j3 ? -1L : j3 - j2);
            long j5 = this.T;
            long j6 = this.U;
            if (j5 <= j6) {
                j4 = j6 - j5;
            }
            jSONObject3.put("exec_tasks", j4);
            jSONObject3.put("nqe_init", this.V);
            jSONObject3.put("prefs_init", this.W);
            jSONObject3.put("channel_init", this.X);
            jSONObject3.put("context_build", this.Y);
            jSONObject3.put(com.bytedance.ttnet.utils.b.k, this.Z);
            jSONObject3.put("update_appinfo", this.aa);
            jSONObject3.put("netlog_init", this.ab);
            jSONObject3.put("nqe_detect", this.ac);
            jSONObject3.put("preconnect", this.ad);
            jSONObject3.put("ssl_session", this.ae);
            jSONObject3.put("ttnet_config", this.af);
            jSONObject3.put("install_cert", this.ag);
            jSONObject.put("ttnet_timestamp", jSONObject2);
            jSONObject.put("ttnet_duration", jSONObject3);
            jSONObject.put(com.bytedance.frameworks.baselib.network.http.a.U, this.Q);
        } catch (JSONException unused) {
        }
        if (Logger.debug()) {
            Logger.d(f7575a, "cronet init metrics json: " + jSONObject.toString());
        }
        return jSONObject;
    }

    public JSONObject a(long j2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("mssdk", a(this.H, this.I));
            jSONObject4.put("app_info_subset", a(this.t, this.u));
            jSONObject4.put("thread_config_list", a(this.v, this.w));
            jSONObject4.put("sla_samp_setting", a(this.z, this.A));
            jSONObject4.put("build_ctx_config", a(this.B, this.C));
            jSONObject4.put("oec_init", a(this.D, this.E));
            jSONObject4.put("init_total", a(Math.min(this.f7582g, this.p) * 1000000, this.T * 1000000));
            String str = i.a.ERROR_CODE_UNKNOW;
            if (j2 != -1) {
                str = a(j2, this.f7583h);
            }
            jSONObject4.put("app_init_time", str);
            jSONObject3.put("cross_plfm", jSONObject4);
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put(f7576b, a(this.f7583h, this.k));
            jSONObject5.put("init_stack_config", a(this.n, this.o));
            jSONObject5.put("get_stack_config", a(this.r, this.s));
            jSONObject5.put("init_cookie_mgr", a(this.l, this.m));
            jSONObject5.put("pre_init_mssdk", a(this.f7584i, this.f7585j));
            jSONObject5.put("builder_build", a(this.F, this.G));
            jSONObject5.put("create_engine", a(this.q, this.f7578J));
            jSONObject5.put("cronet_init_thread", a(this.K, this.L));
            jSONObject5.put("cronet_load_so", a(this.M, this.N));
            jSONObject5.put("thread_config_callback", a(this.x, this.y));
            jSONObject3.put("android_spec", jSONObject5);
            jSONObject2.put("durations_us", jSONObject3);
            jSONObject2.put("cronet_init_mode", this.f7579d.f7595i);
            jSONObject.put("ttnet_layer", jSONObject2);
            jSONObject.put("cronet_layer", new JSONObject(this.O));
            jSONObject.put(com.bytedance.frameworks.baselib.network.http.a.U, this.Q);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public boolean e() {
        return (this.f7582g == 0 || this.f7583h == 0 || this.f7584i == 0 || this.f7585j == 0 || this.k == 0 || this.l == 0 || this.m == 0 || this.n == 0 || this.o == 0 || this.q == 0 || this.r == 0 || this.s == 0 || this.t == 0 || this.u == 0 || this.v == 0 || this.w == 0 || this.x == 0 || this.y == 0 || this.z == 0 || this.A == 0 || this.B == 0 || this.C == 0 || this.D == 0 || this.E == 0 || this.F == 0 || this.G == 0 || this.H == 0 || this.I == 0 || this.f7578J == 0) ? false : true;
    }

    private long b(long j2) {
        return (this.f7582g + j2) - this.f7583h;
    }

    private static String a(long j2, long j3) {
        return String.valueOf(b(j2, j3));
    }

    private static long b(long j2, long j3) {
        if (j2 == 0 || j3 == 0 || j2 > j3) {
            return -1L;
        }
        return (j3 - j2) / 1000;
    }

    private static long c(long j2, long j3) {
        long b2 = b(j2, j3);
        if (b2 == -1) {
            return -1L;
        }
        return b2 / 1000;
    }
}
