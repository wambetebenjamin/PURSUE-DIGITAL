package com.bytedance.common.jato.memory;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import com.bytedance.common.jato.memory.a;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: PhysicalMemoryMonitor.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4915a = "PhysicalMemoryMonitor";

    /* renamed from: b  reason: collision with root package name */
    private static final boolean f4916b = false;

    /* renamed from: c  reason: collision with root package name */
    private static volatile d f4917c;

    /* renamed from: d  reason: collision with root package name */
    private final a f4918d;

    /* renamed from: e  reason: collision with root package name */
    private final Context f4919e;

    /* renamed from: g  reason: collision with root package name */
    private final Executor f4921g;

    /* renamed from: f  reason: collision with root package name */
    private Executor f4920f = null;

    /* renamed from: h  reason: collision with root package name */
    private ComponentCallbacks2 f4922h = null;

    /* renamed from: i  reason: collision with root package name */
    private a.InterfaceC0064a f4923i = null;

    /* renamed from: j  reason: collision with root package name */
    private final AtomicBoolean f4924j = new AtomicBoolean(false);
    private final AtomicInteger k = new AtomicInteger(0);
    private long l = 0;
    private long m = 0;

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
    }

    /* compiled from: PhysicalMemoryMonitor.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final Context f4926a;

        /* renamed from: b  reason: collision with root package name */
        private final Executor f4927b;

        /* renamed from: c  reason: collision with root package name */
        private final boolean f4928c;

        /* renamed from: d  reason: collision with root package name */
        private final long f4929d;

        /* renamed from: e  reason: collision with root package name */
        private final long f4930e;

        /* renamed from: f  reason: collision with root package name */
        private final long f4931f;

        /* renamed from: g  reason: collision with root package name */
        private final int f4932g;

        /* renamed from: h  reason: collision with root package name */
        private final float f4933h;

        /* renamed from: i  reason: collision with root package name */
        private final boolean f4934i;

        /* renamed from: j  reason: collision with root package name */
        private final boolean f4935j;
        private final boolean k;
        private final boolean l;

        private a(C0065a c0065a) {
            this.f4926a = c0065a.f4936a;
            this.f4927b = c0065a.f4937b;
            this.f4930e = c0065a.f4940e;
            this.f4931f = c0065a.f4941f;
            this.f4932g = c0065a.f4942g;
            this.f4933h = c0065a.f4943h;
            this.f4934i = c0065a.f4944i;
            this.f4935j = c0065a.f4945j;
            this.k = c0065a.k;
            this.f4928c = c0065a.f4938c;
            this.f4929d = c0065a.f4939d;
            this.l = c0065a.l;
        }

        public boolean a() {
            return this.f4928c;
        }

        public long b() {
            return this.f4929d;
        }

        public long c() {
            return this.f4930e;
        }

        public long d() {
            return this.f4931f;
        }

        public int e() {
            return this.f4932g;
        }

        public float f() {
            return this.f4933h;
        }

        public boolean g() {
            return this.f4934i;
        }

        public boolean h() {
            return this.f4935j;
        }

        public boolean i() {
            return this.k;
        }

        public boolean j() {
            return this.l;
        }

        /* compiled from: PhysicalMemoryMonitor.java */
        /* renamed from: com.bytedance.common.jato.memory.d$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0065a {

            /* renamed from: a  reason: collision with root package name */
            private final Context f4936a;

            /* renamed from: b  reason: collision with root package name */
            private final Executor f4937b;

            /* renamed from: c  reason: collision with root package name */
            private boolean f4938c = false;

            /* renamed from: d  reason: collision with root package name */
            private long f4939d = 104857600;

            /* renamed from: e  reason: collision with root package name */
            private long f4940e = 5000;

            /* renamed from: f  reason: collision with root package name */
            private long f4941f = 30000;

            /* renamed from: g  reason: collision with root package name */
            private int f4942g = 10;

            /* renamed from: h  reason: collision with root package name */
            private float f4943h = 0.5f;

            /* renamed from: i  reason: collision with root package name */
            private boolean f4944i = true;

            /* renamed from: j  reason: collision with root package name */
            private boolean f4945j = false;
            private boolean k = true;
            private boolean l;

            public C0065a(Context context, Executor executor) {
                this.f4936a = context.getApplicationContext();
                this.f4937b = executor;
            }

            public C0065a a(boolean z) {
                this.f4938c = z;
                return this;
            }

            public C0065a a(long j2) {
                this.f4939d = j2 * 1024 * 1024;
                return this;
            }

            public C0065a b(long j2) {
                this.f4940e = j2;
                return this;
            }

            public C0065a c(long j2) {
                this.f4941f = j2;
                return this;
            }

            public C0065a a(int i2) {
                this.f4942g = i2;
                return this;
            }

            public C0065a a(float f2) {
                this.f4943h = Math.max(0.0f, Math.min(1.0f, f2));
                return this;
            }

            public C0065a b(boolean z) {
                this.f4944i = z;
                return this;
            }

            public C0065a c(boolean z) {
                this.f4945j = z;
                return this;
            }

            public C0065a d(boolean z) {
                this.k = z;
                return this;
            }

            public C0065a e(boolean z) {
                this.l = z;
                return this;
            }

            public a a() {
                return new a(this);
            }
        }
    }

    private d(a aVar) {
        this.f4918d = aVar;
        this.f4919e = aVar.f4926a;
        this.f4921g = aVar.f4927b;
    }

    public static d a(a aVar) {
        if (f4917c == null) {
            synchronized (d.class) {
                if (f4917c == null) {
                    f4917c = new d(aVar);
                }
            }
        }
        return f4917c;
    }

    public void a() {
        b();
        if (this.f4924j.compareAndSet(false, true)) {
            if (this.f4921g == null) {
                this.f4920f = Executors.newSingleThreadExecutor();
            }
            this.m = System.currentTimeMillis();
            if (this.f4918d.a() && Build.VERSION.SDK_INT <= 33) {
                d();
            } else {
                e();
            }
        }
    }

    public void b() {
        this.f4924j.set(false);
        if (this.f4923i != null) {
            com.bytedance.common.jato.memory.a.a().b(this.f4923i);
            this.f4923i = null;
        }
        ComponentCallbacks2 componentCallbacks2 = this.f4922h;
        if (componentCallbacks2 != null) {
            this.f4919e.unregisterComponentCallbacks(componentCallbacks2);
            this.f4922h = null;
        }
        Executor executor = this.f4920f;
        if (executor == null || !(executor instanceof ExecutorService)) {
            return;
        }
        ((ExecutorService) executor).shutdown();
        this.f4920f = null;
    }

    private void d() {
        ComponentCallbacks2 componentCallbacks2 = new ComponentCallbacks2() { // from class: com.bytedance.common.jato.memory.d.1
            @Override // android.content.ComponentCallbacks
            public void onConfigurationChanged(Configuration configuration) {
            }

            @Override // android.content.ComponentCallbacks
            public void onLowMemory() {
            }

            @Override // android.content.ComponentCallbacks2
            public void onTrimMemory(int i2) {
                d.this.a(d.f4915a, "onTrimMemory called, level: " + i2);
                if (i2 == 15 || i2 > 60) {
                    d.this.a(false);
                }
            }
        };
        this.f4922h = componentCallbacks2;
        this.f4919e.registerComponentCallbacks(componentCallbacks2);
    }

    private void e() {
        this.f4923i = new a.InterfaceC0064a() { // from class: com.bytedance.common.jato.memory.d$$ExternalSyntheticLambda0
            public final void onGCPerformed(long j2, long j3, long j4) {
                d.this.a(j2, j3, j4);
            }
        };
        com.bytedance.common.jato.memory.a.a().a(this.f4923i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(long j2, long j3, long j4) {
        a(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z) {
        if (this.f4924j.get()) {
            if (this.k.get() >= this.f4918d.e()) {
                a(f4915a, "Reached max execution count: " + this.f4918d.e());
                b();
                return;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.l < this.f4918d.c()) {
                a(f4915a, "Too fast to do another memory reclaim.");
            } else if (currentTimeMillis - this.m < this.f4918d.d()) {
                a(f4915a, "Too early to start memory reclaim.");
            } else {
                Executor executor = this.f4921g;
                if (executor == null) {
                    executor = this.f4920f;
                }
                executor.execute(new Runnable() { // from class: com.bytedance.common.jato.memory.d$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        d.this.a(z, currentTimeMillis);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(boolean z, long j2) {
        long c2 = c() * 1024;
        long j3 = (c2 / 1024) / 1024;
        a(f4915a, String.format("Memory check - Available: %d MB, Threshold: %d MB", Long.valueOf(j3), Long.valueOf((this.f4918d.b() / 1024) / 1024)));
        if (z && (c2 > this.f4918d.b() || c2 < 0)) {
            a(f4915a, " Memory is enough, availMem = " + j3);
            return;
        }
        MemoryTrimmer.a(this.f4919e, this.f4918d.f(), this.f4918d.g(), this.f4918d.h(), this.f4918d.i(), this.f4918d.j());
        this.l = j2;
        this.k.incrementAndGet();
    }

    public static long c() {
        String readLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"));
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    return -1L;
                }
            } while (!readLine.startsWith("MemAvailable:"));
            long parseLong = Long.parseLong(readLine.split("\\s+")[1]);
            bufferedReader.close();
            return parseLong;
        } catch (IOException e2) {
            e2.printStackTrace();
            return -1L;
        }
    }
}
