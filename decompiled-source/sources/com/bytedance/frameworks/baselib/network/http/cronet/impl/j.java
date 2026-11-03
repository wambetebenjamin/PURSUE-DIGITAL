package com.bytedance.frameworks.baselib.network.http.cronet.impl;

import android.text.TextUtils;
import gsdk.impl.account.toutiao.da;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: TTNetDetectInfo.java */
/* loaded from: classes2.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    public int f7765a;

    /* renamed from: b  reason: collision with root package name */
    public long f7766b;

    /* renamed from: c  reason: collision with root package name */
    public int f7767c;

    /* renamed from: d  reason: collision with root package name */
    public int f7768d;

    /* renamed from: e  reason: collision with root package name */
    public int f7769e;

    /* renamed from: f  reason: collision with root package name */
    public String f7770f;

    /* renamed from: g  reason: collision with root package name */
    public int f7771g;

    /* renamed from: h  reason: collision with root package name */
    public List<c> f7772h = new ArrayList();

    /* renamed from: i  reason: collision with root package name */
    public String f7773i;

    /* renamed from: j  reason: collision with root package name */
    public String f7774j;
    public String k;
    public String l;
    public String m;

    /* compiled from: TTNetDetectInfo.java */
    /* loaded from: classes2.dex */
    public static class c {
    }

    /* compiled from: TTNetDetectInfo.java */
    /* loaded from: classes2.dex */
    public static class b extends c {

        /* renamed from: a  reason: collision with root package name */
        public String f7779a;

        /* renamed from: b  reason: collision with root package name */
        public int f7780b;

        /* renamed from: c  reason: collision with root package name */
        public int f7781c;

        /* renamed from: d  reason: collision with root package name */
        public int f7782d;

        /* renamed from: e  reason: collision with root package name */
        public String f7783e;

        /* renamed from: f  reason: collision with root package name */
        public int f7784f;

        /* renamed from: g  reason: collision with root package name */
        public int f7785g;

        /* renamed from: h  reason: collision with root package name */
        public int f7786h;

        /* renamed from: i  reason: collision with root package name */
        public int f7787i;

        /* renamed from: j  reason: collision with root package name */
        public int f7788j;
        public int k;
        public int l;
        public int m;
        public int n;
        public int o;
        public int p;

        public static b a(JSONObject jSONObject) {
            try {
                b bVar = new b();
                bVar.f7779a = jSONObject.getString("url");
                bVar.f7780b = jSONObject.getInt("httpcode");
                bVar.f7781c = jSONObject.getInt("error");
                bVar.f7782d = jSONObject.getInt("timeout");
                bVar.f7783e = jSONObject.getString("addr");
                bVar.f7784f = jSONObject.getInt(da.bc);
                bVar.f7785g = jSONObject.getInt("cached");
                bVar.f7786h = jSONObject.getInt("sock_reused");
                bVar.f7787i = jSONObject.getInt("totalcost");
                bVar.f7788j = jSONObject.getInt("dnscost");
                bVar.k = jSONObject.getInt("tcpcost");
                bVar.l = jSONObject.getInt("sslcost");
                bVar.m = jSONObject.getInt("sendcost");
                bVar.n = jSONObject.getInt("waitcost");
                bVar.o = jSONObject.getInt("recvcost");
                bVar.p = jSONObject.getInt("recvd_bytes");
                return bVar;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    /* compiled from: TTNetDetectInfo.java */
    /* loaded from: classes2.dex */
    public static class e extends c {

        /* renamed from: a  reason: collision with root package name */
        public String f7793a;

        /* renamed from: b  reason: collision with root package name */
        public String f7794b;

        /* renamed from: c  reason: collision with root package name */
        public int f7795c;

        /* renamed from: d  reason: collision with root package name */
        public int f7796d;

        /* renamed from: e  reason: collision with root package name */
        public int f7797e;

        /* renamed from: f  reason: collision with root package name */
        public int f7798f;

        public static e a(JSONObject jSONObject) {
            try {
                e eVar = new e();
                eVar.f7793a = jSONObject.getString(com.bytedance.bdturing.setting.g.HOST_KEY);
                eVar.f7794b = jSONObject.getString("ip");
                eVar.f7795c = jSONObject.getInt("error");
                eVar.f7796d = jSONObject.getInt("ping_times");
                eVar.f7797e = jSONObject.getInt("succ_times");
                eVar.f7798f = jSONObject.getInt("avg_cost");
                return eVar;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    /* compiled from: TTNetDetectInfo.java */
    /* loaded from: classes2.dex */
    public static class f extends c {

        /* renamed from: a  reason: collision with root package name */
        public String f7799a;

        /* renamed from: b  reason: collision with root package name */
        public String f7800b;

        /* renamed from: c  reason: collision with root package name */
        public int f7801c;

        /* renamed from: d  reason: collision with root package name */
        public int f7802d;

        /* renamed from: e  reason: collision with root package name */
        public List<g> f7803e = new ArrayList();

        public static f a(JSONObject jSONObject) {
            try {
                f fVar = new f();
                fVar.f7799a = jSONObject.getString(com.bytedance.bdturing.setting.g.HOST_KEY);
                fVar.f7800b = jSONObject.getString("ip");
                fVar.f7801c = jSONObject.getInt("error");
                fVar.f7802d = jSONObject.getInt("hops");
                JSONArray optJSONArray = jSONObject.optJSONArray("detail");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        g a2 = g.a((JSONObject) optJSONArray.get(i2));
                        if (a2 != null) {
                            fVar.f7803e.add(a2);
                        }
                    }
                }
                return fVar;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    /* compiled from: TTNetDetectInfo.java */
    /* loaded from: classes2.dex */
    public static class g {

        /* renamed from: a  reason: collision with root package name */
        public int f7804a;

        /* renamed from: b  reason: collision with root package name */
        public int f7805b;

        /* renamed from: c  reason: collision with root package name */
        public String f7806c;

        /* renamed from: d  reason: collision with root package name */
        public int f7807d;

        public static g a(JSONObject jSONObject) {
            try {
                g gVar = new g();
                gVar.f7806c = jSONObject.getString("ip");
                gVar.f7804a = jSONObject.getInt("sendhops");
                gVar.f7805b = jSONObject.getInt("replyhops");
                gVar.f7807d = jSONObject.getInt("cost");
                return gVar;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    /* compiled from: TTNetDetectInfo.java */
    /* loaded from: classes2.dex */
    public static class d extends c {

        /* renamed from: a  reason: collision with root package name */
        public String f7789a;

        /* renamed from: b  reason: collision with root package name */
        public int f7790b;

        /* renamed from: c  reason: collision with root package name */
        public int f7791c;

        /* renamed from: d  reason: collision with root package name */
        public List<String> f7792d = new ArrayList();

        public static d a(JSONObject jSONObject) {
            try {
                d dVar = new d();
                dVar.f7789a = jSONObject.getString(com.bytedance.bdturing.setting.g.HOST_KEY);
                dVar.f7790b = jSONObject.getInt("error");
                dVar.f7791c = jSONObject.getInt("cost");
                JSONArray optJSONArray = jSONObject.optJSONArray("ips");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        if (optJSONArray.get(i2) != null && (optJSONArray.get(i2) instanceof String)) {
                            String str = (String) optJSONArray.get(i2);
                            if (!TextUtils.isEmpty(str)) {
                                dVar.f7792d.add(str);
                            }
                        }
                    }
                }
                return dVar;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    /* compiled from: TTNetDetectInfo.java */
    /* loaded from: classes2.dex */
    public static class a extends c {

        /* renamed from: a  reason: collision with root package name */
        public String f7775a;

        /* renamed from: b  reason: collision with root package name */
        public int f7776b;

        /* renamed from: c  reason: collision with root package name */
        public int f7777c;

        /* renamed from: d  reason: collision with root package name */
        public List<String> f7778d = new ArrayList();

        public static a a(JSONObject jSONObject) {
            try {
                a aVar = new a();
                aVar.f7775a = jSONObject.getString(com.bytedance.bdturing.setting.g.HOST_KEY);
                aVar.f7776b = jSONObject.getInt("error");
                aVar.f7777c = jSONObject.getInt("cost");
                JSONArray optJSONArray = jSONObject.optJSONArray("ips");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        if (optJSONArray.get(i2) != null && (optJSONArray.get(i2) instanceof String)) {
                            String str = (String) optJSONArray.get(i2);
                            if (!TextUtils.isEmpty(str)) {
                                aVar.f7778d.add(str);
                            }
                        }
                    }
                }
                return aVar;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }
}
