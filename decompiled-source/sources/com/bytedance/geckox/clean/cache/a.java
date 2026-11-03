package com.bytedance.geckox.clean.cache;
/* compiled from: CacheConfig.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    final int f8975a;

    /* renamed from: b  reason: collision with root package name */
    final c f8976b;

    /* renamed from: c  reason: collision with root package name */
    private final b f8977c;

    private a(C0130a c0130a) {
        this.f8975a = c0130a.f8978a;
        this.f8977c = c0130a.f8979b;
        this.f8976b = c0130a.f8980c;
    }

    public b getCachePolicy() {
        return this.f8977c;
    }

    public int getLimitCount() {
        return this.f8975a;
    }

    public c getCleanListener() {
        return this.f8976b;
    }

    /* compiled from: CacheConfig.java */
    /* renamed from: com.bytedance.geckox.clean.cache.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static final class C0130a {

        /* renamed from: a  reason: collision with root package name */
        private int f8978a;

        /* renamed from: b  reason: collision with root package name */
        private b f8979b = b.NONE;

        /* renamed from: c  reason: collision with root package name */
        private c f8980c;

        public C0130a limitCount(int i2) {
            this.f8978a = i2;
            return this;
        }

        public C0130a cachePolicy(b bVar) {
            if (bVar == null) {
                bVar = b.NONE;
            }
            this.f8979b = bVar;
            return this;
        }

        public C0130a cleanListener(c cVar) {
            this.f8980c = cVar;
            return this;
        }

        public a build() {
            return new a(this);
        }
    }
}
