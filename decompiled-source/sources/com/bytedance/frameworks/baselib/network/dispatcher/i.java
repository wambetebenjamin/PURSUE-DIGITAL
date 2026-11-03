package com.bytedance.frameworks.baselib.network.dispatcher;
/* compiled from: RequestQueue.java */
/* loaded from: classes2.dex */
public class i implements g {

    /* renamed from: a  reason: collision with root package name */
    private static volatile boolean f7550a = true;

    /* renamed from: b  reason: collision with root package name */
    private static final int f7551b = 8;

    /* renamed from: c  reason: collision with root package name */
    private static final int f7552c = 8;

    /* renamed from: d  reason: collision with root package name */
    private static volatile j f7553d;

    /* renamed from: e  reason: collision with root package name */
    private static volatile i f7554e;

    public static synchronized void a(boolean z) {
        synchronized (i.class) {
            f7550a = z;
            if (f7553d != null) {
                f7553d.a(f7550a);
            }
        }
    }

    public static synchronized void a(j jVar) {
        synchronized (i.class) {
            f7553d = jVar;
            f7553d.a(f7550a);
        }
    }

    public static j a() {
        if (f7553d == null) {
            synchronized (i.class) {
                if (f7553d == null) {
                    f7553d = j.m().a(8, 8).b(8, 8).a(30L).c(10L).b(10L).a(true).a();
                }
            }
        }
        return f7553d;
    }

    public static boolean b() {
        return f7550a;
    }

    @Deprecated
    public static i c() {
        if (f7554e == null) {
            synchronized (i.class) {
                if (f7554e == null) {
                    f7554e = new i();
                }
            }
        }
        return f7554e;
    }

    public i() {
        this(8, 8);
    }

    @Deprecated
    public i(int i2, int i3) {
        if (f7553d == null) {
            f7553d = j.m().a(8, 8).b(8, 8).a(30L).c(10L).b(10L).a(true).a();
        }
    }

    @Override // com.bytedance.frameworks.baselib.network.dispatcher.g
    public synchronized void a(e eVar) {
        if (eVar != null) {
            h.f7533a.a(eVar);
        }
    }

    @Override // com.bytedance.frameworks.baselib.network.dispatcher.g
    public synchronized void a(c cVar) {
        if (cVar != null) {
            h.f7533a.a(cVar);
        }
    }

    @Override // com.bytedance.frameworks.baselib.network.dispatcher.g
    public synchronized void b(e eVar) {
        if (eVar != null) {
            h.f7533a.b(eVar);
        }
    }

    @Override // com.bytedance.frameworks.baselib.network.dispatcher.g
    public synchronized void b(c cVar) {
        if (cVar != null) {
            h.f7533a.b(cVar);
        }
    }
}
