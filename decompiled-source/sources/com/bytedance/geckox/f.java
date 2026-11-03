package com.bytedance.geckox;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.geckox.utils.p;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
/* compiled from: GeckoConfig.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private final Context f9039a;

    /* renamed from: b  reason: collision with root package name */
    private final Executor f9040b;

    /* renamed from: c  reason: collision with root package name */
    private final Executor f9041c;

    /* renamed from: d  reason: collision with root package name */
    private final com.bytedance.geckox.statistic.a f9042d;

    /* renamed from: e  reason: collision with root package name */
    private final com.bytedance.geckox.net.c f9043e;

    /* renamed from: f  reason: collision with root package name */
    private final List<String> f9044f;

    /* renamed from: g  reason: collision with root package name */
    private final List<String> f9045g;

    /* renamed from: h  reason: collision with root package name */
    private final com.bytedance.geckox.clean.cache.a f9046h;

    /* renamed from: i  reason: collision with root package name */
    private final Long f9047i;

    /* renamed from: j  reason: collision with root package name */
    private final String f9048j;
    private String k;
    private final String l;
    private final String m;
    private final String n;
    private final File o;
    private final boolean p;
    private final boolean q;
    private final boolean r;
    private final boolean s;
    private final int t;

    private f(a aVar) {
        int i2;
        Context context = aVar.f9052d;
        this.f9039a = context;
        if (context == null) {
            throw new IllegalArgumentException("context == null");
        }
        List<String> list = aVar.f9050b;
        this.f9044f = list;
        List<String> list2 = aVar.f9051c;
        this.f9045g = list2;
        this.f9046h = aVar.f9057i;
        Long l = aVar.f9058j;
        this.f9047i = l;
        if (TextUtils.isEmpty(aVar.k)) {
            this.f9048j = com.bytedance.geckox.utils.a.getVersion(context);
        } else {
            this.f9048j = aVar.k;
        }
        this.k = aVar.l;
        this.m = aVar.o;
        this.n = aVar.p;
        if (aVar.n == null) {
            this.o = new File(context.getFilesDir(), d.GECKO_ROOT_DIR);
        } else {
            this.o = aVar.n;
        }
        String str = aVar.m;
        this.l = str;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("host is null");
        }
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("accessKey is empty");
        }
        if (list2 == null || list2.isEmpty()) {
            throw new IllegalArgumentException("local accessKey is empty");
        }
        if (!list2.containsAll(list)) {
            throw new IllegalArgumentException("local accessKey must contain accessKey");
        }
        if (l == null) {
            throw new IllegalArgumentException("appId is null");
        }
        if (TextUtils.isEmpty(this.k)) {
            throw new IllegalArgumentException("deviceId key empty");
        }
        if (aVar.s > 10) {
            i2 = 7;
        } else {
            i2 = aVar.s <= 0 ? 3 : aVar.s;
        }
        if (aVar.f9053e == null) {
            this.f9040b = p.a(i2);
        } else {
            this.f9040b = aVar.f9053e;
        }
        if (aVar.f9054f == null) {
            this.f9041c = p.c();
        } else {
            this.f9041c = aVar.f9054f;
        }
        if (aVar.f9049a == null) {
            this.f9043e = new com.bytedance.geckox.net.b();
        } else {
            this.f9043e = aVar.f9049a;
        }
        this.f9042d = aVar.f9055g;
        this.p = aVar.f9056h;
        this.q = aVar.q;
        this.s = aVar.r;
        this.t = aVar.t;
        this.r = aVar.u;
    }

    public Context a() {
        return this.f9039a;
    }

    public com.bytedance.geckox.clean.cache.a getCacheConfig() {
        return this.f9046h;
    }

    public boolean b() {
        return this.p;
    }

    public List<String> c() {
        return this.f9045g;
    }

    public List<String> getAccessKeys() {
        return this.f9044f;
    }

    public String getAccessKey() {
        return this.f9044f.get(0);
    }

    public Executor d() {
        return this.f9040b;
    }

    public Executor e() {
        return this.f9041c;
    }

    public com.bytedance.geckox.net.c f() {
        return this.f9043e;
    }

    public String g() {
        return this.l;
    }

    public long h() {
        return this.f9047i.longValue();
    }

    public String i() {
        return this.n;
    }

    public String j() {
        return this.m;
    }

    public File getResRootDir() {
        return this.o;
    }

    public String k() {
        return this.f9048j;
    }

    public com.bytedance.geckox.statistic.a l() {
        return this.f9042d;
    }

    public String m() {
        return this.k;
    }

    public boolean n() {
        return this.q;
    }

    public boolean o() {
        return this.s;
    }

    public void a(String str) {
        this.k = str;
    }

    public int p() {
        return this.t;
    }

    public boolean q() {
        return this.r;
    }

    /* compiled from: GeckoConfig.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private com.bytedance.geckox.net.c f9049a;

        /* renamed from: b  reason: collision with root package name */
        private List<String> f9050b;

        /* renamed from: c  reason: collision with root package name */
        private List<String> f9051c;

        /* renamed from: d  reason: collision with root package name */
        private Context f9052d;

        /* renamed from: e  reason: collision with root package name */
        private Executor f9053e;

        /* renamed from: f  reason: collision with root package name */
        private Executor f9054f;

        /* renamed from: g  reason: collision with root package name */
        private com.bytedance.geckox.statistic.a f9055g;

        /* renamed from: i  reason: collision with root package name */
        private com.bytedance.geckox.clean.cache.a f9057i;

        /* renamed from: j  reason: collision with root package name */
        private Long f9058j;
        private String k;
        private String l;
        private String m;
        private File n;
        private String o;
        private String p;
        private boolean r;
        private int s;

        /* renamed from: h  reason: collision with root package name */
        private boolean f9056h = true;
        private boolean q = false;
        private int t = 1;
        private boolean u = true;

        public a(Context context) {
            this.f9052d = context.getApplicationContext();
        }

        public a allLocalAccessKeys(String... strArr) {
            if (strArr != null && strArr.length >= 1) {
                this.f9051c = Arrays.asList(strArr);
            }
            return this;
        }

        public a accessKey(String... strArr) {
            if (strArr != null && strArr.length >= 1) {
                this.f9050b = Arrays.asList(strArr);
            }
            return this;
        }

        public a netStack(com.bytedance.geckox.net.c cVar) {
            this.f9049a = cVar;
            return this;
        }

        public a statisticMonitor(com.bytedance.geckox.statistic.a aVar) {
            this.f9055g = aVar;
            return this;
        }

        public a needServerMonitor(boolean z) {
            this.f9056h = z;
            return this;
        }

        public a updateExecutor(Executor executor) {
            this.f9053e = executor;
            return this;
        }

        public a checkUpdateExecutor(Executor executor) {
            this.f9054f = executor;
            return this;
        }

        public a cacheConfig(com.bytedance.geckox.clean.cache.a aVar) {
            this.f9057i = aVar;
            return this;
        }

        public a appId(long j2) {
            this.f9058j = Long.valueOf(j2);
            return this;
        }

        public a appVersion(String str) {
            this.k = str;
            return this;
        }

        public a deviceId(String str) {
            this.l = str;
            return this;
        }

        public a host(String str) {
            this.m = str;
            return this;
        }

        public a uid(String str) {
            this.p = str;
            return this;
        }

        public a region(String str) {
            this.o = str;
            return this;
        }

        public a resRootDir(File file) {
            this.n = file;
            return this;
        }

        public a isLoopCheck(boolean z) {
            this.q = z;
            return this;
        }

        public a setEnableSync(boolean z) {
            this.r = z;
            return this;
        }

        public a a(int i2) {
            this.s = i2;
            return this;
        }

        public a b(int i2) {
            this.t = i2;
            return this;
        }

        public a a(boolean z) {
            this.u = z;
            return this;
        }

        public f build() {
            return new f(this);
        }
    }
}
