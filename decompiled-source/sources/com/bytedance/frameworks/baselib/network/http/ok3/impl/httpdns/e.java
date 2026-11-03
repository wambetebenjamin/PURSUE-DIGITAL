package com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.collection.WeakHandler;
import com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.a;
import com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.b;
import com.bytedance.frameworks.baselib.network.http.util.l;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: HttpDns.java */
/* loaded from: classes2.dex */
public class e implements WeakHandler.IHandler {
    private static HandlerThread I = null;

    /* renamed from: J  reason: collision with root package name */
    private static WeakHandler f8035J = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f8036a = "callback_dnsresult_key";

    /* renamed from: b  reason: collision with root package name */
    public static final String f8037b = "dispatchersdk_httpdns_hardcodeips";

    /* renamed from: c  reason: collision with root package name */
    public static final String f8038c = "httpdns_hardcodeips";

    /* renamed from: d  reason: collision with root package name */
    private static String f8039d = "e";

    /* renamed from: e  reason: collision with root package name */
    private static final int f8040e = 1;

    /* renamed from: f  reason: collision with root package name */
    private static final int f8041f = 2;

    /* renamed from: g  reason: collision with root package name */
    private static final int f8042g = 3;

    /* renamed from: h  reason: collision with root package name */
    private static final int f8043h = 4;

    /* renamed from: i  reason: collision with root package name */
    private static final int f8044i = 5;

    /* renamed from: j  reason: collision with root package name */
    private static final int f8045j = 6;
    private static final int k = 7;
    private static final String l = "dns_timeout_job_key";
    private static final String m = "httpdns_timeout_job_key";
    private static final String n = "callback_dnsresult_job_key";
    private static final int o = 6;
    private static ExecutorService p = Executors.newFixedThreadPool(6);
    private static volatile e q;
    private static String t;
    private final d E;
    private volatile h G;
    private volatile String u;
    private AtomicBoolean r = new AtomicBoolean(true);
    private AtomicBoolean s = new AtomicBoolean(false);
    private ConcurrentSkipListSet<String> v = new ConcurrentSkipListSet<>();
    private ConcurrentMap<String, CopyOnWriteArrayList<String>> w = new ConcurrentHashMap();
    private ConcurrentSkipListSet<String> x = new ConcurrentSkipListSet<>();
    private AtomicInteger y = new AtomicInteger(30);
    private AtomicInteger z = new AtomicInteger(2000);
    private AtomicInteger A = new AtomicInteger(60);
    private AtomicInteger B = new AtomicInteger(0);
    private AtomicInteger C = new AtomicInteger(5);
    private AtomicInteger D = new AtomicInteger(5);
    private final com.bytedance.frameworks.baselib.network.http.ok3.impl.f F = new com.bytedance.frameworks.baselib.network.http.ok3.impl.f();
    private boolean H = false;
    private Handler K = new Handler(Looper.getMainLooper()) { // from class: com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.e.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null || !(message.obj instanceof e)) {
                return;
            }
            Bundle data = message.getData();
            b bVar = (b) data.getSerializable("callback_dnsresult_key");
            c cVar = (c) data.getSerializable(e.n);
            if (cVar == null) {
                if (Logger.debug()) {
                    Logger.d(e.f8039d, "callback job is null");
                }
            } else if (message.what != 1) {
            } else {
                if (Logger.debug()) {
                    Logger.d(e.f8039d, "callback dns result for host " + cVar.a() + " in thread " + Thread.currentThread().getName());
                }
                cVar.f8021a.a(bVar);
            }
        }
    };

    public static e a() {
        if (q == null) {
            synchronized (e.class) {
                if (q == null) {
                    q = new e();
                }
            }
        }
        return q;
    }

    private e() {
        if (Logger.debug()) {
            Logger.d(f8039d, "HttpDns constructor");
        }
        HandlerThread handlerThread = new HandlerThread("TTOK-HTTPDNS");
        I = handlerThread;
        handlerThread.start();
        f8035J = new WeakHandler(I.getLooper(), this);
        t = "4.2.243.51-gsdk";
        this.E = new d(f8035J);
    }

    public void a(h hVar) {
        if (this.H) {
            if (Logger.debug()) {
                Logger.d(f8039d, "you have set httpdns depend before.");
            }
        } else if (hVar == null || hVar.a() == null || TextUtils.isEmpty(hVar.d()) || hVar.c() == null) {
            this.G = null;
            throw new IllegalArgumentException("you must set correct httpdns depend");
        } else {
            this.G = hVar;
            if (TextUtils.isEmpty(this.u)) {
                this.u = hVar.d();
            }
            this.H = true;
            k();
            this.F.a(hVar.a());
            if (this.x.size() < 1) {
                if (this.G.b() != null && this.G.b().length > 0 && this.G.b().length <= 10) {
                    this.x.addAll(Arrays.asList(this.G.b()));
                    a(a.EnumC0114a.PRELOAD_BATCH);
                } else if (Logger.debug()) {
                    Logger.d(f8039d, "httpdns preload domains's number must less than 10, httpdns preload is skipped.");
                }
            } else {
                a(a.EnumC0114a.PRELOAD_BATCH);
            }
            Message obtain = Message.obtain();
            obtain.what = 5;
            obtain.obj = this;
            f8035J.sendMessage(obtain);
        }
    }

    public b a(String str) {
        Future<Void> i2;
        b a2;
        b b2;
        if (Logger.debug()) {
            Logger.d(f8039d, "getHttpDnsResultForHostSyncBlock for host : " + str);
        }
        if (h(str)) {
            return null;
        }
        boolean equals = str.equals(this.u);
        if (this.s.get() && !equals) {
            b c2 = c(str, false);
            if (c2 != null) {
                return c2;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(0, false);
            a2 = a(a(str, (List<Boolean>) arrayList, true), str, arrayList.get(0).booleanValue());
        } else {
            b b3 = b(str, false);
            if (b3 != null) {
                return b3;
            }
            if (this.E.i(str)) {
                i2 = this.E.j(str);
            } else {
                i2 = i(str);
            }
            if (i2 == null) {
                b a3 = a(str, equals);
                return a3 == null ? g(str) : a3;
            }
            if (i2.isDone()) {
                b2 = b(str, true);
                if (b2 == null) {
                    a2 = a(str, equals);
                }
            } else {
                try {
                    i2.get(this.D.get() * 1000, TimeUnit.MILLISECONDS);
                    b2 = b(str, true);
                    if (b2 == null) {
                        a2 = a(str, equals);
                    }
                } catch (Exception unused) {
                    a2 = a(str, equals);
                }
            }
            a2 = b2;
        }
        return a2 == null ? g(str) : a2;
    }

    private b g(String str) {
        if (Logger.debug()) {
            Logger.d(f8039d, "try to look up hardcode ips");
        }
        if (this.w.containsKey(str)) {
            b bVar = new b();
            Iterator<String> it = this.w.get(str).iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.c.c(next)) {
                    bVar.f8011b.add(next);
                } else if (com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.c.d(next)) {
                    bVar.f8010a.add(next);
                } else if (Logger.debug()) {
                    Logger.d(f8039d, "find a invalid hardcode ip: " + next);
                }
            }
            bVar.f8012c = b.a.HARDCODE_IPS;
            return bVar;
        }
        return null;
    }

    private b a(String str, boolean z) {
        Future<Void> a2;
        if (z) {
            return null;
        }
        if (this.E.e(str)) {
            a2 = this.E.g(str);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            a2 = a((List<String>) arrayList, a.EnumC0114a.CACHE_UNSET, true);
        }
        if (a2 == null) {
            return null;
        }
        if (a2.isDone()) {
            return c(str, true);
        }
        try {
            a2.get(this.C.get() * 1000, TimeUnit.MILLISECONDS);
            return c(str, true);
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            return null;
        }
    }

    public b b(String str) {
        if (Logger.debug()) {
            Logger.d(f8039d, "getHttpDnsResultForHostSyncNonBlock for host : " + str);
        }
        if (h(str)) {
            return null;
        }
        if (h().get()) {
            b c2 = c(str, false);
            if (c2 != null) {
                return c2;
            }
            if (!this.E.e(str)) {
                synchronized (this) {
                    if (!this.E.e(str)) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(str);
                        a((List<String>) arrayList, a.EnumC0114a.CACHE_UNSET, false);
                    }
                }
            }
            return b(str, false);
        }
        b b2 = b(str, false);
        if (b2 != null) {
            return b2;
        }
        if (!this.E.i(str)) {
            synchronized (this) {
                if (!this.E.i(str)) {
                    i(str);
                }
            }
        }
        return c(str, false);
    }

    public b a(String str, g gVar) {
        if (Logger.debug()) {
            Logger.d(f8039d, "HttpDns::getHttpDnsResultForHostAsync for host " + str);
        }
        if (h(str)) {
            return null;
        }
        if (h().get()) {
            b c2 = c(str, false);
            if (c2 != null) {
                if (Logger.debug()) {
                    Logger.d(f8039d, "returned httpdns cache, wouldn't callback");
                }
                return c2;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(0, false);
            a(str, (List<Boolean>) arrayList, false);
            c cVar = new c(str, gVar, arrayList.get(0).booleanValue());
            this.E.a(str, cVar);
            Message obtain = Message.obtain();
            obtain.what = 2;
            obtain.obj = this;
            Bundle bundle = new Bundle();
            bundle.putSerializable(m, cVar);
            obtain.setData(bundle);
            f8035J.sendMessageDelayed(obtain, this.z.get());
        } else {
            b b2 = b(str, false);
            if (b2 != null) {
                return b2;
            }
            if (!this.E.i(str)) {
                i(str);
            }
            c cVar2 = new c(str, gVar, true);
            this.E.b(str, cVar2);
            Message obtain2 = Message.obtain();
            obtain2.what = 3;
            obtain2.obj = this;
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable(l, cVar2);
            obtain2.setData(bundle2);
            f8035J.sendMessageDelayed(obtain2, this.D.get() * 1000);
        }
        return null;
    }

    private boolean h(String str) {
        if (!this.H) {
            if (Logger.debug()) {
                Logger.d(f8039d, "you have not set httpdns depend");
            }
            return true;
        } else if (!this.r.get()) {
            if (Logger.debug()) {
                Logger.d(f8039d, "httpdns was unabled by tnc");
            }
            return true;
        } else if (!com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.a.a(str)) {
            if (Logger.debug()) {
                Logger.d(f8039d, "illegal host");
            }
            return true;
        } else if (com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.c.d(str) || com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.c.c(str)) {
            if (Logger.debug()) {
                Logger.d(f8039d, "host is ipaddress");
            }
            return true;
        } else {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a(a.EnumC0114a enumC0114a) {
        ConcurrentSkipListSet<String> concurrentSkipListSet;
        if (this.G != null && l.b(this.G.a()) && (concurrentSkipListSet = this.x) != null && concurrentSkipListSet.size() != 0 && this.x.size() <= 10) {
            if (Logger.debug()) {
                Logger.d(f8039d, "do httpdns preload");
            }
            Iterator<String> it = this.x.iterator();
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                String next = it.next();
                if (!this.E.e(next)) {
                    arrayList.add(next);
                }
            }
            if (arrayList.size() == 0) {
                return;
            }
            if (Logger.debug()) {
                Logger.d(f8039d, "httpdns batch preload for : " + arrayList);
            }
            a((List<String>) arrayList, enumC0114a, false);
        }
    }

    void c(String str) {
        if (com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.a.a(str) && !this.E.e(str) && this.s.get()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            a((List<String>) arrayList, a.EnumC0114a.CACHE_STALE_EXPIRED, false);
        }
    }

    void d(String str) {
        if (!com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.a.a(str) || this.E.e(str)) {
            return;
        }
        this.E.a(str);
    }

    void e(String str) {
        this.E.o(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(List<String> list) {
        if (Logger.debug()) {
            Logger.d(f8039d, "batchRefreshHttpDnsStaleCache for host " + list);
        }
        a(list, a.EnumC0114a.REFRESH_BATCH, false);
    }

    void f(String str) {
        if (Logger.debug()) {
            Logger.d(f8039d, "removeLocalDnsStaleCache");
        }
        if (com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.a.a(str)) {
            this.E.c(str);
        }
    }

    public void a(JSONObject jSONObject) {
        if (Logger.debug()) {
            Logger.d(f8039d, "onServerConfigChanged");
        }
        if (this.G == null || jSONObject == null) {
            return;
        }
        int optInt = jSONObject.optInt("ttnet_http_dns_enabled", -1);
        if (optInt >= 0) {
            this.r.set(optInt == 1);
        }
        String optString = jSONObject.optString("ttnet_tt_http_dns_domain");
        if (!TextUtils.isEmpty(optString)) {
            this.u = optString;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("ttnet_tt_http_dns_preload_batch_host");
        if (optJSONArray != null) {
            this.x.clear();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                String optString2 = optJSONArray.optString(i2);
                if (!TextUtils.isEmpty(optString2)) {
                    this.x.add(optString2);
                }
            }
        }
        int optInt2 = jSONObject.optInt("localdns_cache_ttl", -1);
        if (optInt2 >= 0) {
            this.y.set(optInt2);
        }
        int optInt3 = jSONObject.optInt("httpdns_prefer_time_ms", -1);
        if (optInt3 >= 0) {
            this.z.set(optInt3);
        }
        int optInt4 = jSONObject.optInt("httpdns_stale_cache_interval", -1);
        if (optInt4 >= 0) {
            this.A.set(optInt4);
        }
        int optInt5 = jSONObject.optInt("enable_compare_localdns_httpdns", -1);
        if (optInt5 >= 0) {
            this.B.set(optInt5);
        }
        int optInt6 = jSONObject.optInt("ttnet_http_dns_prefer", -1);
        if (optInt6 >= 0) {
            this.s.set(optInt6 == 1);
        }
        int optInt7 = jSONObject.optInt("ttnet_http_dns_timeout", -1);
        if (optInt7 >= 0) {
            this.C.set(optInt7);
        }
        int optInt8 = jSONObject.optInt("ttnet_local_dns_time_out", -1);
        if (optInt8 >= 0) {
            this.D.set(optInt8);
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("ttnet_http_dns_addr");
        if (optJSONObject != null) {
            this.w.clear();
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String optString3 = optJSONObject.optString(next);
                if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(optString3)) {
                    String[] split = optString3.split(",");
                    if (next.equals(this.u)) {
                        this.v.clear();
                        this.v.addAll(Arrays.asList(split));
                    } else {
                        this.w.put(next, new CopyOnWriteArrayList<>(Arrays.asList(split)));
                    }
                }
            }
        }
    }

    private Future<Void> a(String str, List<Boolean> list, boolean z) {
        Future<Void> a2;
        a d2 = this.E.d(str);
        if (d2 == null || d2.g() + (d2.h() * 1000) <= System.currentTimeMillis() + this.z.get()) {
            list.set(0, true);
            if (!this.E.i(str)) {
                i(str);
            }
        }
        if (this.E.e(str)) {
            if (Logger.debug()) {
                Logger.d(f8039d, "httpdns is resolving for host " + str);
            }
            return this.E.g(str);
        }
        synchronized (this) {
            if (this.E.e(str)) {
                a2 = this.E.g(str);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(str);
                a2 = a(arrayList, a.EnumC0114a.CACHE_UNSET, z);
            }
        }
        return a2;
    }

    synchronized Future<Void> a(List<String> list, a.EnumC0114a enumC0114a, boolean z) {
        Future<Void> future = null;
        if (list != null) {
            if (list.size() != 0) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (this.E.e(list.get(i2))) {
                        if (z && list.size() == 1 && i2 == 0) {
                            future = this.E.g(list.get(i2));
                            if (Logger.debug()) {
                                Logger.d(f8039d, "sync block request for " + list.get(i2) + " is resolving");
                            }
                        }
                        list.remove(list.get(i2));
                    }
                }
                if (list.size() == 0) {
                    return future;
                }
                if (Logger.debug()) {
                    Logger.d(f8039d, "submit httpdns resolve for host : " + list);
                }
                try {
                    future = p.submit(new f(com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.a.a(list), t, this.E, enumC0114a, f8035J));
                    for (String str : list) {
                        this.E.a(str, future);
                        if (this.s.get()) {
                            this.E.p(str);
                        }
                    }
                } catch (RejectedExecutionException e2) {
                    e2.printStackTrace();
                    if (Logger.debug()) {
                        Logger.d(f8039d, "submit httpdns resolve for host : " + list + " was rejected");
                    }
                }
                return future;
            }
        }
        if (Logger.debug()) {
            Logger.d(f8039d, "httpdns resolve hostlist is null.");
        }
        return null;
    }

    private synchronized Future<Void> i(String str) {
        if (this.E.i(str)) {
            if (Logger.debug()) {
                Logger.d(f8039d, "localdns is resolving for host : " + str);
            }
            return this.E.j(str);
        }
        if (Logger.debug()) {
            Logger.d(f8039d, "submit localdns resolve host : " + str);
        }
        Future<Void> future = null;
        try {
            future = p.submit(new j(str, this.E, f8035J));
            this.E.b(str, future);
        } catch (RejectedExecutionException e2) {
            e2.printStackTrace();
        }
        return future;
    }

    private b a(Future<Void> future, String str, boolean z) {
        if (Logger.debug()) {
            Logger.d(f8039d, "getDnsResultInHttpDnsPreferTime");
        }
        try {
            if (future == null) {
                if (Logger.debug()) {
                    Logger.d(f8039d, "httpdns future is null");
                }
                return b(str, z);
            } else if (future.isDone()) {
                if (Logger.debug()) {
                    Logger.d(f8039d, "httpdns future is done");
                }
                b c2 = c(str, true);
                return c2 == null ? b(str, z) : c2;
            } else {
                future.get(a().b().get(), TimeUnit.MILLISECONDS);
                b c3 = c(str, true);
                return c3 == null ? b(str, z) : c3;
            }
        } catch (InterruptedException unused) {
            if (Logger.debug()) {
                Logger.d(f8039d, "httpdns InterruptedException, look up localdns");
            }
            return b(str, z);
        } catch (ExecutionException unused2) {
            if (Logger.debug()) {
                Logger.d(f8039d, "httpdns ExecutionException, look up localdns");
            }
            return b(str, z);
        } catch (TimeoutException unused3) {
            if (Logger.debug()) {
                Logger.d(f8039d, "httpdns timeout in httpdns prefer time, look up localdns");
            }
            return b(str, z);
        }
    }

    private b b(String str, boolean z) {
        if (Logger.debug()) {
            Logger.d(f8039d, "lookup localdns cache for host " + str);
        }
        b bVar = new b();
        a d2 = this.E.d(str);
        if (d2 == null) {
            return null;
        }
        bVar.f8010a = d2.i();
        bVar.f8011b = d2.j();
        if (z) {
            bVar.f8012c = b.a.LOCALDNS_REQUEST;
        } else {
            bVar.f8012c = b.a.LOCALDNS_CACHE;
        }
        return bVar;
    }

    private b c(String str, boolean z) {
        if (Logger.debug()) {
            Logger.d(f8039d, "look up httpdns cache for host " + str);
        }
        a b2 = this.E.b(str);
        if (b2 == null) {
            return null;
        }
        b bVar = new b();
        bVar.f8010a = b2.i();
        bVar.f8011b = b2.j();
        if (z) {
            if ((b2.h() * 1000) + b2.g() > System.currentTimeMillis()) {
                bVar.f8012c = b.a.HTTPDNS_REQUEST;
                return bVar;
            }
            return null;
        }
        if ((b2.h() * 1000) + b2.g() > System.currentTimeMillis()) {
            bVar.f8012c = b.a.HTTPDNS_CACHE;
        } else {
            bVar.f8012c = b.a.HTTPDNS_STALE_CACHE;
        }
        return bVar;
    }

    @Override // com.bytedance.common.utility.collection.WeakHandler.IHandler
    public void handleMsg(Message message) {
        if (Logger.debug()) {
            Logger.d(f8039d, "httpdns handler handle msg in " + Thread.currentThread().getName());
        }
        if (message != null) {
            if ((message.obj instanceof e) || (message.obj instanceof f) || (message.obj instanceof j) || (message.obj instanceof a) || (message.obj instanceof d)) {
                try {
                    if ((message.obj instanceof e) && message.what == 2) {
                        if (Logger.debug()) {
                            Logger.d(f8039d, "MSG_HTTPDNS_PREFER_TIMEOUT");
                        }
                        c cVar = (c) message.getData().getSerializable(m);
                        if (cVar == null) {
                            if (Logger.debug()) {
                                Logger.d(f8039d, "MSG_HTTPDNS_PREFER_TIMEOUT job is null");
                            }
                        } else if (this.E.a(cVar)) {
                            if (Logger.debug()) {
                                Logger.d(f8039d, "httpdns request not return in httpdns prefer time for host : " + cVar.a());
                            }
                            a(cVar, b(cVar.a(), cVar.b()));
                            this.E.b(cVar);
                        }
                    } else if ((message.obj instanceof f) && message.what == 3) {
                        if (Logger.debug()) {
                            Logger.d(f8039d, "MSG_HTTPDNS_COMPLETED");
                        }
                        ArrayList<String> stringArrayList = message.getData().getStringArrayList("httpdns_completed_hosts");
                        if (stringArrayList == null) {
                            if (Logger.debug()) {
                                Logger.d(f8039d, "MSG_HTTPDNS_COMPLETED hostlist is null");
                                return;
                            }
                            return;
                        }
                        for (String str : stringArrayList) {
                            if (this.E.l(str)) {
                                if (Logger.debug()) {
                                    Logger.d(f8039d, "httpdns returned in httpdns prefer time for host : " + stringArrayList);
                                }
                                Iterator<c> it = this.E.k(str).iterator();
                                while (it.hasNext()) {
                                    c next = it.next();
                                    b c2 = c(str, true);
                                    if (a().h().get() && c2 == null) {
                                        c2 = b(str, next.b());
                                    }
                                    if (Logger.debug()) {
                                        Logger.d(f8039d, "httpdns request is returned in prefer time for " + str);
                                    }
                                    a(next, c2);
                                    this.E.b(next);
                                }
                            }
                        }
                    } else {
                        if ((message.obj instanceof j) && message.what == 1) {
                            if (Logger.debug()) {
                                Logger.d(f8039d, "MSG_LOCALDNS_COMPLETED");
                            }
                            String string = message.getData().getString("localdns_completed_host");
                            if (string == null) {
                                if (Logger.debug()) {
                                    Logger.d(f8039d, "MSG_HTTPDNS_COMPLETED host is null");
                                }
                            } else if (this.E.n(string)) {
                                if (Logger.debug()) {
                                    Logger.d(f8039d, "localdns returned in localdns timeout for host : " + string);
                                }
                                Iterator<c> it2 = this.E.m(string).iterator();
                                while (it2.hasNext()) {
                                    c next2 = it2.next();
                                    b b2 = b(string, true);
                                    if (b2 == null) {
                                        b c3 = c(string, false);
                                        if (c3 == null) {
                                            a(next2);
                                            this.E.a(next2.a(), next2);
                                        } else {
                                            a(next2, c3);
                                        }
                                    } else {
                                        if (Logger.debug()) {
                                            Logger.d(f8039d, "localdns request is returned in timeout for " + string);
                                        }
                                        a(next2, b2);
                                    }
                                    this.E.d(next2);
                                }
                            }
                        } else if ((message.obj instanceof e) && message.what == 3) {
                            if (Logger.debug()) {
                                Logger.d(f8039d, "MSG_LOCALDNS_REQUEST_TIMEOUT");
                            }
                            c cVar2 = (c) message.getData().getSerializable(l);
                            if (cVar2 == null) {
                                if (Logger.debug()) {
                                    Logger.d(f8039d, "MSG_HTTPDNS_PREFER_TIMEOUT job is null");
                                }
                            } else if (this.E.c(cVar2)) {
                                if (Logger.debug()) {
                                    Logger.d(f8039d, "local request not return in localdns timeout for host : " + cVar2.a());
                                }
                                b c4 = c(cVar2.a(), false);
                                if (c4 != null) {
                                    a(cVar2, c4);
                                } else {
                                    if (!this.E.e(cVar2.a())) {
                                        a(cVar2);
                                    }
                                    this.E.a(cVar2.a(), cVar2);
                                }
                                this.E.d(cVar2);
                            }
                        } else if ((message.obj instanceof e) && message.what == 4) {
                            if (Logger.debug()) {
                                Logger.d(f8039d, "MSG_HTTPDNS_REQUEST_TIMEOUT");
                            }
                            c cVar3 = (c) message.getData().getSerializable(l);
                            if (cVar3 == null) {
                                if (Logger.debug()) {
                                    Logger.d(f8039d, "MSG_HTTPDNS_PREFER_TIMEOUT job is null");
                                }
                            } else if (this.E.a(cVar3)) {
                                a(cVar3, (b) null);
                                this.E.b(cVar3);
                            }
                        } else if ((message.obj instanceof e) && message.what == 5) {
                            if (Logger.debug()) {
                                Logger.d(f8039d, "MSG_LOAD_LOCAL_HARDCODEIPS");
                            }
                            String string2 = this.G.a().getSharedPreferences("dispatchersdk_httpdns_hardcodeips", 0).getString("httpdns_hardcodeips", "");
                            if (!TextUtils.isEmpty(string2)) {
                                this.v.clear();
                                try {
                                    JSONArray jSONArray = new JSONArray(string2);
                                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                        String optString = jSONArray.optString(i2);
                                        if (!TextUtils.isEmpty(optString)) {
                                            this.v.add(optString);
                                        }
                                    }
                                } catch (JSONException unused) {
                                    if (Logger.debug()) {
                                        Logger.d(f8039d, "local httpdns hardcode ips not exists, use default");
                                    }
                                }
                            } else if (this.G.e() != null && this.v.size() == 0) {
                                this.v.addAll(Arrays.asList(this.G.e()));
                            }
                        } else if ((message.obj instanceof e) && message.what == 6) {
                            if (Logger.debug()) {
                                Logger.d(f8039d, "network changed");
                            }
                            this.E.a(a().e().a());
                        } else if ((message.obj instanceof e) && message.what == 7) {
                            if (Logger.debug()) {
                                Logger.d(f8039d, "activity resume, detect ipv6 reachable");
                            }
                            k();
                        } else if (message.obj instanceof a) {
                            String string3 = message.getData().getString("dnsrecord_host");
                            if (TextUtils.isEmpty(string3)) {
                                if (Logger.debug()) {
                                    Logger.d(f8039d, "DnsRecord refresh host is null");
                                    return;
                                }
                                return;
                            }
                            switch (message.what) {
                                case 10:
                                    if (Logger.debug()) {
                                        Logger.d(f8039d, "refresh httpdns cache for host : " + string3);
                                    }
                                    a().c(string3);
                                    return;
                                case 11:
                                    if (Logger.debug()) {
                                        Logger.d(f8039d, "remove localdns cache for host : " + string3);
                                    }
                                    a().f(string3);
                                    return;
                                case 12:
                                    if (Logger.debug()) {
                                        Logger.d(f8039d, "add host : " + string3 + " to stale cache host list");
                                    }
                                    a().e(string3);
                                    return;
                                case 13:
                                    if (Logger.debug()) {
                                        Logger.d(f8039d, "remove httpdns cache for host : " + string3);
                                    }
                                    a().d(string3);
                                    return;
                                default:
                                    return;
                            }
                        } else if ((message.obj instanceof d) && message.what == 20) {
                            if (Logger.debug()) {
                                Logger.d(f8039d, "reset httpdns domain failed count");
                            }
                            this.E.e();
                        } else if (Logger.debug()) {
                            Logger.d(f8039d, "HTTPDNS-CollectResult handler thread receive a unknown message : " + message);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    private void a(c cVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(cVar.a());
        a((List<String>) arrayList, a.EnumC0114a.CACHE_UNSET, false);
        Message obtain = Message.obtain();
        obtain.what = 4;
        obtain.obj = this;
        Bundle bundle = new Bundle();
        bundle.putSerializable(l, cVar);
        obtain.setData(bundle);
        f8035J.sendMessageDelayed(obtain, this.C.get() * 1000);
    }

    private void a(c cVar, b bVar) {
        if (Logger.debug()) {
            Logger.d(f8039d, "doDnsResultCallback for host " + cVar.a());
        }
        Message obtain = Message.obtain();
        obtain.obj = this;
        obtain.what = 1;
        Bundle bundle = new Bundle();
        bundle.putSerializable(n, cVar);
        bundle.putSerializable("callback_dnsresult_key", bVar);
        obtain.setData(bundle);
        this.K.sendMessage(obtain);
    }

    AtomicInteger b() {
        return this.z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AtomicInteger c() {
        return this.y;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AtomicInteger d() {
        return this.A;
    }

    public h e() {
        return this.G;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String f() {
        return this.u;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConcurrentSkipListSet<String> g() {
        return this.v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        this.v.clear();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            String optString = jSONArray.optString(i2);
            if (!TextUtils.isEmpty(optString)) {
                this.v.add(optString);
            }
        }
        SharedPreferences.Editor edit = this.G.a().getSharedPreferences("dispatchersdk_httpdns_hardcodeips", 0).edit();
        edit.putString("httpdns_hardcodeips", String.valueOf(jSONArray));
        edit.apply();
    }

    public AtomicBoolean h() {
        return this.s;
    }

    public void i() {
        Message obtain = Message.obtain();
        obtain.obj = this;
        obtain.what = 7;
        f8035J.sendMessage(obtain);
    }

    public void j() {
        Message obtain = Message.obtain();
        obtain.obj = this;
        obtain.what = 6;
        f8035J.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k() {
        try {
            p.submit(new i());
        } catch (RejectedExecutionException e2) {
            e2.printStackTrace();
        }
    }

    public HandlerThread l() {
        return I;
    }
}
