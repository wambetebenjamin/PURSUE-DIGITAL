package com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.l;
import com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.a;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: HostResolveManager.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8025a = 20;

    /* renamed from: b  reason: collision with root package name */
    private static final String f8026b = "d";

    /* renamed from: c  reason: collision with root package name */
    private com.bytedance.frameworks.baselib.network.http.util.i<String, a> f8027c = new com.bytedance.frameworks.baselib.network.http.util.i<>(100);

    /* renamed from: d  reason: collision with root package name */
    private ConcurrentMap<String, a> f8028d = new ConcurrentHashMap();

    /* renamed from: e  reason: collision with root package name */
    private ConcurrentMap<String, Future<Void>> f8029e = new ConcurrentHashMap();

    /* renamed from: f  reason: collision with root package name */
    private ConcurrentMap<String, Future<Void>> f8030f = new ConcurrentHashMap();

    /* renamed from: g  reason: collision with root package name */
    private ConcurrentMap<String, ConcurrentSkipListSet<c>> f8031g = new ConcurrentHashMap();

    /* renamed from: h  reason: collision with root package name */
    private ConcurrentMap<String, ConcurrentSkipListSet<c>> f8032h = new ConcurrentHashMap();

    /* renamed from: i  reason: collision with root package name */
    private ConcurrentSkipListSet<String> f8033i = new ConcurrentSkipListSet<>();

    /* renamed from: j  reason: collision with root package name */
    private AtomicInteger f8034j = new AtomicInteger(0);
    private AtomicInteger k = new AtomicInteger(0);
    private l.b l = l.b.NONE;
    private final long m = 180000;
    private final int n = 10;
    private final Handler o;

    public d(Handler handler) {
        this.o = handler;
    }

    public void a(Context context) {
        l.b networkType = l.getNetworkType(context);
        if (networkType != this.l) {
            if (Logger.debug()) {
                Logger.d(f8026b, "network changed, clear cache and re-batch-preload preload hosts, current net type is " + networkType);
            }
            synchronized (this.f8027c.j()) {
                for (Map.Entry<String, a> entry : this.f8027c.j().entrySet()) {
                    if (entry != null) {
                        if (e.a().h().get()) {
                            entry.getValue().c();
                        } else {
                            entry.getValue().d();
                        }
                    }
                }
                this.f8027c.a();
            }
            for (Map.Entry<String, a> entry2 : this.f8028d.entrySet()) {
                if (entry2 != null) {
                    entry2.getValue().f();
                }
            }
            this.f8028d.clear();
            if (networkType != l.b.NONE) {
                e.a().a(a.EnumC0114a.CACHE_STALE_NETCHANGED);
                e.a().k();
            }
        }
        this.l = networkType;
    }

    public void a(String str, a aVar) {
        a b2 = b(str);
        if (b2 != null) {
            if (e.a().h().get()) {
                b2.c();
            } else {
                b2.d();
            }
        }
        if (e.a().h().get()) {
            aVar.a();
        } else {
            aVar.b();
        }
        synchronized (this.f8027c.j()) {
            this.f8027c.a(str, aVar);
        }
    }

    public void a(String str) {
        a b2 = b(str);
        if (b2 != null) {
            if (e.a().h().get()) {
                b2.c();
            } else {
                b2.d();
            }
            synchronized (this.f8027c.j()) {
                this.f8027c.b(str);
            }
        }
    }

    public a b(String str) {
        a a2;
        synchronized (this.f8027c.j()) {
            a2 = this.f8027c.a((com.bytedance.frameworks.baselib.network.http.util.i<String, a>) str);
        }
        return a2;
    }

    public void b(String str, a aVar) {
        a d2 = d(str);
        if (d2 != null) {
            d2.f();
        }
        aVar.e();
        this.f8028d.put(str, aVar);
    }

    public void c(String str) {
        a d2 = d(str);
        if (d2 != null) {
            d2.f();
            this.f8028d.remove(str);
        }
    }

    public a d(String str) {
        if (this.f8028d.containsKey(str)) {
            return this.f8028d.get(str);
        }
        return null;
    }

    public boolean e(String str) {
        return this.f8029e.containsKey(str);
    }

    public void a(String str, Future<Void> future) {
        this.f8029e.put(str, future);
    }

    public void f(String str) {
        this.f8029e.remove(str);
    }

    public Future<Void> g(String str) {
        if (this.f8029e.containsKey(str)) {
            return this.f8029e.get(str);
        }
        return null;
    }

    public void b(String str, Future<Void> future) {
        this.f8030f.put(str, future);
    }

    public void h(String str) {
        this.f8030f.remove(str);
    }

    public boolean i(String str) {
        return this.f8030f.containsKey(str);
    }

    public Future<Void> j(String str) {
        if (this.f8030f.containsKey(str)) {
            return this.f8030f.get(str);
        }
        return null;
    }

    public synchronized void a(String str, c cVar) {
        if (this.f8031g.containsKey(str)) {
            this.f8031g.get(str).add(cVar);
        } else {
            ConcurrentSkipListSet<c> concurrentSkipListSet = new ConcurrentSkipListSet<>();
            concurrentSkipListSet.add(cVar);
            this.f8031g.put(str, concurrentSkipListSet);
        }
    }

    public ConcurrentSkipListSet<c> k(String str) {
        return this.f8031g.get(str);
    }

    public boolean l(String str) {
        return this.f8031g.containsKey(str);
    }

    public boolean a(c cVar) {
        return this.f8031g.containsKey(cVar.a()) && this.f8031g.get(cVar.a()).contains(cVar);
    }

    public void b(c cVar) {
        String a2 = cVar.a();
        if (this.f8031g.containsKey(a2)) {
            this.f8031g.get(a2).remove(cVar);
            if (this.f8031g.get(a2).isEmpty()) {
                this.f8031g.remove(a2);
            }
        }
    }

    public synchronized void b(String str, c cVar) {
        if (this.f8032h.containsKey(str)) {
            this.f8032h.get(str).add(cVar);
        } else {
            ConcurrentSkipListSet<c> concurrentSkipListSet = new ConcurrentSkipListSet<>();
            concurrentSkipListSet.add(cVar);
            this.f8032h.put(str, concurrentSkipListSet);
        }
    }

    public ConcurrentSkipListSet<c> m(String str) {
        return this.f8032h.get(str);
    }

    public boolean n(String str) {
        return this.f8032h.containsKey(str);
    }

    public boolean c(c cVar) {
        return this.f8032h.containsKey(cVar.a()) && this.f8032h.get(cVar.a()).contains(cVar);
    }

    public void d(c cVar) {
        String a2 = cVar.a();
        if (this.f8032h.containsKey(a2)) {
            this.f8032h.get(a2).remove(cVar);
            if (this.f8032h.get(a2).isEmpty()) {
                this.f8032h.remove(a2);
            }
        }
    }

    public void o(String str) {
        this.f8033i.add(str);
        if (this.f8033i.size() < 10) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f8033i);
        e.a().a(arrayList);
    }

    public void p(String str) {
        if (this.f8033i.contains(str)) {
            this.f8033i.remove(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AtomicInteger a() {
        return this.f8034j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        this.f8034j.getAndIncrement();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AtomicInteger c() {
        return this.k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        this.k.getAndIncrement();
        if (this.k.get() >= 2) {
            Message obtain = Message.obtain();
            obtain.what = 20;
            obtain.obj = this;
            this.o.sendMessageDelayed(obtain, 180000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        this.k.set(0);
    }
}
