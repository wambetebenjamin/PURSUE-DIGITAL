package com.bytedance.sync.compensate;

import android.os.Handler;
import com.bytedance.sync.m;
import com.bytedance.sync.protocal.l;
/* compiled from: Rotation.java */
/* loaded from: classes3.dex */
abstract class g {

    /* renamed from: a  reason: collision with root package name */
    protected final d f13393a;

    /* renamed from: b  reason: collision with root package name */
    protected final l f13394b;

    /* renamed from: c  reason: collision with root package name */
    private final m<Handler> f13395c;

    /* renamed from: d  reason: collision with root package name */
    private a f13396d;

    /* renamed from: e  reason: collision with root package name */
    private final Runnable f13397e = new Runnable() { // from class: com.bytedance.sync.compensate.g.1
        @Override // java.lang.Runnable
        public void run() {
            g.this.a();
            g.this.c();
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Rotation.java */
    /* loaded from: classes3.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        long f13399a;

        /* renamed from: b  reason: collision with root package name */
        long f13400b;
    }

    /* compiled from: Rotation.java */
    /* loaded from: classes3.dex */
    public @interface b {
        public static final int TYPE_HEART_BEAT = 1;
        public static final int TYPE_POLL = 2;
    }

    protected abstract void a();

    public abstract int getStatus();

    public g(d dVar, l lVar, m<Handler> mVar, a aVar) {
        this.f13393a = dVar;
        this.f13394b = lVar;
        this.f13395c = mVar;
        this.f13396d = aVar;
    }

    public void start(boolean z) {
        if (!z) {
            a();
        }
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        long b2 = b();
        com.bytedance.sync.logger.c.d("Compensator: next time to request: " + (b2 / 1000) + "s");
        if (b2 < 0) {
            return;
        }
        this.f13395c.get(new Object[0]).postDelayed(this.f13397e, b2);
    }

    protected long b() {
        long j2;
        if (this.f13393a.isForeground()) {
            j2 = this.f13396d.f13399a;
        } else {
            j2 = this.f13396d.f13400b;
        }
        return j2 * 1000;
    }

    public void destroy() {
        this.f13395c.get(new Object[0]).removeCallbacks(this.f13397e);
    }

    public void updateIntervals(a aVar) {
        this.f13396d = aVar;
    }
}
