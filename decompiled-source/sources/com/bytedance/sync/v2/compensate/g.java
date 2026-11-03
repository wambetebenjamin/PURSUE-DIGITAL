package com.bytedance.sync.v2.compensate;

import android.os.Handler;
import com.bytedance.sync.m;
/* compiled from: Rotation.java */
/* loaded from: classes3.dex */
abstract class g {

    /* renamed from: a  reason: collision with root package name */
    protected final d f13781a;

    /* renamed from: b  reason: collision with root package name */
    protected final com.bytedance.sync.v2.intf.j f13782b;

    /* renamed from: c  reason: collision with root package name */
    protected final String f13783c;

    /* renamed from: d  reason: collision with root package name */
    private final m<Handler> f13784d;

    /* renamed from: e  reason: collision with root package name */
    private a f13785e;

    /* renamed from: f  reason: collision with root package name */
    private final Runnable f13786f = new Runnable() { // from class: com.bytedance.sync.v2.compensate.g.1
        @Override // java.lang.Runnable
        public void run() {
            g.this.b();
            g.this.e();
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Rotation.java */
    /* loaded from: classes3.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        long f13788a;

        /* renamed from: b  reason: collision with root package name */
        long f13789b;
    }

    /* compiled from: Rotation.java */
    /* loaded from: classes3.dex */
    public @interface b {
        public static final int TYPE_POLL = 2;
        public static final int TYPE_SYNC = 1;
    }

    protected abstract String a();

    protected abstract boolean a(com.bytedance.sync.v2.protocal.e eVar);

    protected abstract void b();

    public abstract int getStatus();

    public void tryResetRotate(com.bytedance.sync.v2.protocal.e eVar) {
        if (a(eVar)) {
            d();
        }
    }

    private void d() {
        com.bytedance.sync.logger.c.d(this.f13783c + "receive data, do reset rotate");
        this.f13784d.get(new Object[0]).removeCallbacks(this.f13786f);
        e();
    }

    public g(String str, d dVar, com.bytedance.sync.v2.intf.j jVar, m<Handler> mVar, a aVar) {
        this.f13783c = str;
        this.f13781a = dVar;
        this.f13782b = jVar;
        this.f13784d = mVar;
        this.f13785e = aVar;
    }

    public void start(boolean z) {
        if (!z) {
            b();
        }
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        long c2 = c();
        com.bytedance.sync.logger.c.d(this.f13783c + "next time to request: " + (c2 / 1000) + "s");
        if (c2 < 0) {
            return;
        }
        this.f13784d.get(new Object[0]).postDelayed(this.f13786f, c2);
    }

    protected long c() {
        long j2;
        if (this.f13781a.isForeground()) {
            j2 = this.f13785e.f13788a;
        } else {
            j2 = this.f13785e.f13789b;
        }
        return j2 * 1000;
    }

    public void destroy() {
        this.f13784d.get(new Object[0]).removeCallbacks(this.f13786f);
    }

    public void updateIntervals(a aVar) {
        this.f13785e = aVar;
    }
}
