package com.bytedance.services.apm.api;

import android.text.TextUtils;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
/* compiled from: QuotaHelper.java */
/* loaded from: classes3.dex */
public class r {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13284a = "https://";

    /* renamed from: b  reason: collision with root package name */
    private static final String f13285b = "drop data";

    /* renamed from: c  reason: collision with root package name */
    private static final String f13286c = "long escape";

    /* renamed from: d  reason: collision with root package name */
    private static final String f13287d = "/monitor/collect/quota_status";

    /* renamed from: e  reason: collision with root package name */
    private static final int f13288e = 1000;

    /* renamed from: f  reason: collision with root package name */
    private static final int f13289f = 60000;

    /* renamed from: g  reason: collision with root package name */
    private static final int f13290g = 6;

    /* renamed from: h  reason: collision with root package name */
    private static final int f13291h = 5;

    /* renamed from: i  reason: collision with root package name */
    private static final ConcurrentHashMap<String, r> f13292i = new ConcurrentHashMap<>();

    /* renamed from: j  reason: collision with root package name */
    private static String f13293j;
    private static String k;
    private static long l;
    private static long m;
    private final String n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;
    private long s;

    private r(String str) {
        this.n = str;
    }

    public static r a(String str) {
        ConcurrentHashMap<String, r> concurrentHashMap = f13292i;
        if (concurrentHashMap.get(str) == null) {
            synchronized (r.class) {
                if (concurrentHashMap.get(str) == null) {
                    concurrentHashMap.put(str, new r(str));
                }
            }
        }
        return concurrentHashMap.get(str);
    }

    public static void b(String str) {
        k = "https://" + str + f13287d;
    }

    public static void c(String str) {
        f13293j = "https://" + str + f13287d;
    }

    public static void a(long j2) {
        l = j2;
    }

    public static void b(long j2) {
        m = j2;
    }

    public synchronized boolean d(String str) {
        if ("drop data".equals(str)) {
            this.o = true;
            d();
            return true;
        }
        f();
        return false;
    }

    public synchronized boolean a(d dVar) {
        if (dVar != null) {
            if (dVar.getStatusCode() > 0) {
                if (500 <= dVar.getStatusCode() && dVar.getStatusCode() <= 600) {
                    if (this.p) {
                        d();
                    }
                    this.p = true;
                    return true;
                }
                if (dVar.getStatusCode() != 200) {
                    this.p = true;
                }
                return false;
            }
        }
        if (this.p) {
            e();
        }
        this.p = true;
        return true;
    }

    public synchronized void c(long j2) {
        this.s = Math.max(this.s, System.currentTimeMillis() + (j2 * 1000));
    }

    public synchronized boolean a() {
        if (this.o) {
            if (b()) {
                return true;
            }
            return c();
        }
        return false;
    }

    public boolean b() {
        return System.currentTimeMillis() <= this.s;
    }

    private boolean c() {
        IHttpService iHttpService;
        String str = TextUtils.isEmpty(k) ? f13293j : k;
        long j2 = l;
        if (j2 <= 0) {
            j2 = m;
        }
        if (!TextUtils.isEmpty(str) && j2 != 0 && (iHttpService = (IHttpService) com.bytedance.news.common.service.manager.e.getService(IHttpService.class)) != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt("aid", Long.valueOf(j2));
                jSONObject.put("os", com.bytedance.bdturing.utils.b.f4370d);
                jSONObject.put("path", this.n);
                d doPost = iHttpService.doPost(str, jSONObject.toString().getBytes(), null);
                if ((doPost != null) & (doPost.getResponseBytes() != null)) {
                    String optString = new JSONObject(new String(doPost.getResponseBytes())).optString("quota_status");
                    if (TextUtils.isEmpty(optString)) {
                        f();
                        return false;
                    } else if (TextUtils.equals(optString, f13286c)) {
                        f();
                        d();
                        return false;
                    } else {
                        d();
                        return true;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private void d() {
        int i2 = this.q;
        if (i2 < 6) {
            this.q = i2 + 1;
        }
        this.s = Math.max(System.currentTimeMillis() + (this.q * 5 * 60000), this.s);
    }

    private void e() {
        int i2 = this.r;
        if (i2 < 5) {
            this.r = i2 + 1;
        }
        this.s = Math.max(System.currentTimeMillis() + (((int) Math.pow(2.0d, this.r)) * 15 * 1000), this.s);
    }

    private void f() {
        this.o = false;
        this.p = false;
        this.q = 0;
        this.r = 0;
        this.s = 0L;
    }
}
