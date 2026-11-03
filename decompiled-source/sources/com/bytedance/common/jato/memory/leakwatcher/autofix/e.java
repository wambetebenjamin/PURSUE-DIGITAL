package com.bytedance.common.jato.memory.leakwatcher.autofix;

import android.app.Application;
import android.os.SystemClock;
import android.util.Log;
import com.bytedance.common.jato.memory.a;
import com.bytedance.common.jato.memory.leakwatcher.autofix.e;
import com.bytedance.common.jato.memory.leakwatcher.g;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
/* compiled from: MemoryLeakAutoFixer.java */
/* loaded from: classes2.dex */
public class e implements a.InterfaceC0064a {

    /* renamed from: a */
    private static final String f4969a = "MemoryLeakAutoFixer";

    /* renamed from: b */
    private static volatile e f4970b;

    /* renamed from: c */
    private static final AtomicBoolean f4971c = new AtomicBoolean(false);

    /* renamed from: d */
    private final Executor f4972d;

    /* renamed from: e */
    private final com.bytedance.common.jato.memory.leakwatcher.b f4973e;

    /* renamed from: f */
    private final com.bytedance.common.jato.memory.a f4974f;

    /* renamed from: g */
    private final AtomicLong f4975g;

    /* renamed from: h */
    private final c f4976h;

    /* renamed from: i */
    private final d f4977i;

    /* renamed from: j */
    private final boolean f4978j;
    private final boolean k;
    private final String[] l;

    public static e a() {
        if (f4970b == null) {
            synchronized (e.class) {
                if (f4970b == null) {
                    f4970b = new a().a();
                }
            }
        }
        return f4970b;
    }

    public static synchronized e a(a aVar) {
        e eVar;
        synchronized (e.class) {
            AtomicBoolean atomicBoolean = f4971c;
            if (atomicBoolean.get()) {
                throw new IllegalStateException("MemoryLeakAutoFixer already initialized. getInstance() should be used to access the instance.");
            }
            if (f4970b == null) {
                f4970b = aVar.a();
                atomicBoolean.set(true);
            }
            eVar = f4970b;
        }
        return eVar;
    }

    private e(c cVar, d dVar, Executor executor) {
        this(cVar, dVar, true, true, null, executor);
    }

    private e(c cVar, d dVar, boolean z, boolean z2, String[] strArr, Executor executor) {
        this.f4975g = new AtomicLong(0L);
        this.f4976h = cVar;
        this.f4977i = dVar;
        this.f4978j = z;
        this.k = z2;
        this.f4972d = executor;
        this.f4973e = com.bytedance.common.jato.memory.leakwatcher.b.a();
        this.f4974f = com.bytedance.common.jato.memory.a.a();
        this.l = strArr;
    }

    public void a(Application application) {
        this.f4973e.a(application, this.f4978j, this.k, this.l);
        this.f4974f.a(this);
    }

    @Override // com.bytedance.common.jato.memory.a.InterfaceC0064a
    public void a(long j2, long j3, long j4) {
        d();
    }

    private void d() {
        if (this.f4976h.a(SystemClock.elapsedRealtime() - this.f4975g.get())) {
            this.f4972d.execute(new e$$ExternalSyntheticLambda0(this));
        }
    }

    public void e() {
        this.f4975g.set(SystemClock.elapsedRealtime());
        try {
            List<g> d2 = this.f4973e.d();
            if (d2.size() == 0) {
                Log.d(f4969a, "No leaking objects to report");
            } else {
                this.f4977i.a(d2);
            }
        } catch (Exception e2) {
            Log.e(f4969a, "Error generating leak report", e2);
        }
    }

    public void b() {
        this.f4972d.execute(new e$$ExternalSyntheticLambda0(this));
    }

    public void c() {
        this.f4974f.b(this);
    }

    /* compiled from: MemoryLeakAutoFixer.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a */
        private c f4979a = null;

        /* renamed from: b */
        private d f4980b = null;

        /* renamed from: c */
        private Executor f4981c = null;

        /* renamed from: d */
        private boolean f4982d = true;

        /* renamed from: e */
        private boolean f4983e = true;

        /* renamed from: f */
        private float f4984f = 0.9f;

        /* renamed from: g */
        private long f4985g = 180000;

        /* renamed from: h */
        private int f4986h = 5;

        /* renamed from: i */
        private int f4987i = 300000;

        /* renamed from: j */
        private boolean f4988j = true;
        private String[] k = null;

        public a a(c cVar) {
            this.f4979a = cVar;
            return this;
        }

        public a a(d dVar) {
            this.f4980b = dVar;
            return this;
        }

        public a a(Executor executor) {
            this.f4981c = executor;
            return this;
        }

        public a a(boolean z) {
            this.f4982d = z;
            return this;
        }

        public a b(boolean z) {
            this.f4983e = z;
            return this;
        }

        public a a(float f2) {
            this.f4984f = f2;
            return this;
        }

        public a a(long j2) {
            this.f4985g = j2;
            return this;
        }

        public a a(int i2) {
            this.f4986h = i2;
            return this;
        }

        public a b(int i2) {
            this.f4987i = i2;
            return this;
        }

        public a c(boolean z) {
            this.f4988j = z;
            return this;
        }

        public a a(String[] strArr) {
            this.k = strArr;
            return this;
        }

        public e a() {
            if (this.f4979a == null) {
                this.f4979a = new b(this.f4984f, this.f4985g);
            }
            if (this.f4980b == null) {
                this.f4980b = new com.bytedance.common.jato.memory.leakwatcher.autofix.a(this.f4986h, this.f4987i, this.f4988j);
            }
            if (this.f4981c == null) {
                this.f4981c = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.bytedance.common.jato.memory.leakwatcher.autofix.e$a$$ExternalSyntheticLambda0
                    @Override // java.util.concurrent.ThreadFactory
                    public final Thread newThread(Runnable runnable) {
                        Thread a2;
                        a2 = e.a.a(runnable);
                        return a2;
                    }
                });
            }
            return new e(this.f4979a, this.f4980b, this.f4982d, this.f4983e, this.k, this.f4981c);
        }

        public static /* synthetic */ Thread a(Runnable runnable) {
            Thread thread = new Thread(runnable, "MemLeakFix");
            thread.setPriority(5);
            return thread;
        }
    }
}
