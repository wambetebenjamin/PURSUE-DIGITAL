package com.bytedance.frameworks.baselib.network.dispatcher;

import java.util.concurrent.ThreadPoolExecutor;
/* compiled from: ThreadPoolConfig.java */
/* loaded from: classes2.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private ThreadPoolExecutor f7555a;

    /* renamed from: b  reason: collision with root package name */
    private ThreadPoolExecutor f7556b;

    /* renamed from: c  reason: collision with root package name */
    private ThreadPoolExecutor f7557c;

    /* renamed from: d  reason: collision with root package name */
    private ThreadPoolExecutor f7558d;

    /* renamed from: e  reason: collision with root package name */
    private int f7559e;

    /* renamed from: f  reason: collision with root package name */
    private int f7560f;

    /* renamed from: g  reason: collision with root package name */
    private int f7561g;

    /* renamed from: h  reason: collision with root package name */
    private int f7562h;

    /* renamed from: i  reason: collision with root package name */
    private long f7563i;

    /* renamed from: j  reason: collision with root package name */
    private long f7564j;
    private long k;
    private boolean l;
    private ThreadPoolExecutor m;
    private int n;
    private int o;
    private long p;

    private j(a aVar) {
        this.n = 0;
        this.f7561g = 8;
        this.f7562h = 8;
        this.o = 1;
        this.f7559e = 8;
        this.f7560f = 8;
        this.k = 10L;
        this.f7563i = 30L;
        this.f7564j = 10L;
        this.p = 10L;
        this.l = true;
        if (aVar.f7566b != null) {
            this.f7555a = aVar.f7566b;
        }
        if (aVar.f7565a != null) {
            this.f7556b = aVar.f7565a;
        }
        if (aVar.f7567c != null) {
            this.f7557c = aVar.f7567c;
        }
        if (aVar.f7568d != null) {
            this.f7558d = aVar.f7568d;
        }
        if (aVar.m != null) {
            this.m = aVar.m;
        }
        if (aVar.f7569e > 0) {
            this.f7559e = aVar.f7569e;
        }
        if (aVar.f7570f > 0) {
            this.f7560f = aVar.f7570f;
        }
        if (aVar.f7571g > 0) {
            this.f7561g = aVar.f7571g;
        }
        if (aVar.f7572h > 0) {
            this.f7562h = aVar.f7572h;
        }
        if (aVar.f7573i > 0) {
            this.f7563i = aVar.f7573i;
        }
        if (aVar.f7574j > 0) {
            this.f7564j = aVar.f7574j;
        }
        if (aVar.k > 0) {
            this.k = aVar.k;
        }
        if (aVar.n >= 0) {
            this.n = aVar.n;
        }
        if (aVar.o > 0) {
            this.o = aVar.o;
        }
        if (aVar.p > 0) {
            this.p = aVar.p;
        }
        this.l = aVar.l;
    }

    public void a(boolean z) {
        this.l = z;
    }

    public ThreadPoolExecutor a() {
        return this.f7555a;
    }

    public ThreadPoolExecutor b() {
        return this.f7556b;
    }

    public ThreadPoolExecutor c() {
        return this.f7557c;
    }

    public ThreadPoolExecutor d() {
        return this.f7558d;
    }

    public ThreadPoolExecutor n() {
        return this.m;
    }

    public int e() {
        return this.f7559e;
    }

    public int f() {
        return this.f7560f;
    }

    public int o() {
        return this.o;
    }

    public long p() {
        return this.p;
    }

    public int g() {
        return this.f7561g;
    }

    public int h() {
        return this.f7562h;
    }

    public long i() {
        return this.f7563i;
    }

    public long j() {
        return this.f7564j;
    }

    public long k() {
        return this.k;
    }

    public int q() {
        return this.n;
    }

    public boolean l() {
        return this.l;
    }

    public static a m() {
        return new a();
    }

    /* compiled from: ThreadPoolConfig.java */
    /* loaded from: classes2.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private ThreadPoolExecutor f7565a;

        /* renamed from: b  reason: collision with root package name */
        private ThreadPoolExecutor f7566b;

        /* renamed from: c  reason: collision with root package name */
        private ThreadPoolExecutor f7567c;

        /* renamed from: d  reason: collision with root package name */
        private ThreadPoolExecutor f7568d;

        /* renamed from: e  reason: collision with root package name */
        private int f7569e;

        /* renamed from: f  reason: collision with root package name */
        private int f7570f;

        /* renamed from: g  reason: collision with root package name */
        private int f7571g;

        /* renamed from: h  reason: collision with root package name */
        private int f7572h;

        /* renamed from: i  reason: collision with root package name */
        private long f7573i;

        /* renamed from: j  reason: collision with root package name */
        private long f7574j;
        private long k;
        private boolean l = true;
        private ThreadPoolExecutor m;
        private int n;
        private int o;
        private long p;

        public a a(ThreadPoolExecutor threadPoolExecutor) {
            this.f7566b = threadPoolExecutor;
            return this;
        }

        public a b(ThreadPoolExecutor threadPoolExecutor) {
            this.f7565a = threadPoolExecutor;
            return this;
        }

        public a c(ThreadPoolExecutor threadPoolExecutor) {
            this.f7567c = threadPoolExecutor;
            return this;
        }

        public a d(ThreadPoolExecutor threadPoolExecutor) {
            this.f7568d = threadPoolExecutor;
            return this;
        }

        public a e(ThreadPoolExecutor threadPoolExecutor) {
            this.m = threadPoolExecutor;
            return this;
        }

        public a a(int i2, int i3) {
            if (i3 <= 0 || i2 <= 0) {
                throw new IllegalArgumentException("Api thread size must set > 0.");
            }
            if (i2 > i3) {
                throw new IllegalArgumentException("Core thread size must set less equal than Max thread size.");
            }
            this.f7569e = i3;
            this.f7571g = i2;
            return this;
        }

        public a b(int i2, int i3) {
            if (i3 <= 0 || i2 <= 0) {
                throw new IllegalArgumentException("Download thread size must set > 0.");
            }
            if (i2 > i3) {
                throw new IllegalArgumentException("Core thread size must set less equal than Max thread size.");
            }
            this.f7570f = i3;
            this.f7572h = i2;
            return this;
        }

        public a a(int i2) {
            if (i2 <= 0) {
                throw new IllegalArgumentException("Lowest thread size must set > 0.");
            }
            this.o = i2;
            return this;
        }

        public a a(long j2) {
            if (j2 <= 0) {
                throw new IllegalArgumentException("Api keep alive time must set > 0 when allow core thread timeout.");
            }
            this.f7573i = j2;
            return this;
        }

        public a b(long j2) {
            if (j2 <= 0) {
                throw new IllegalArgumentException("Download keep alive time must set > 0 when allow core thread timeout.");
            }
            this.f7574j = j2;
            return this;
        }

        public a c(long j2) {
            if (j2 <= 0) {
                throw new IllegalArgumentException("Immediate keep alive time must set > 0 when allow core thread timeout.");
            }
            this.k = j2;
            return this;
        }

        public a d(long j2) {
            if (j2 <= 0) {
                throw new IllegalArgumentException("Immediate keep alive time must set > 0 when allow core thread timeout.");
            }
            this.p = j2;
            return this;
        }

        public a b(int i2) {
            if (i2 < 0) {
                throw new IllegalArgumentException("Immdeiate core size must set > 0");
            }
            this.n = i2;
            return this;
        }

        public a a(boolean z) {
            this.l = z;
            return this;
        }

        public j a() {
            return new j(this);
        }
    }
}
