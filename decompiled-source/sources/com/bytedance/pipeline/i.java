package com.bytedance.pipeline;
/* compiled from: Pipe.java */
/* loaded from: classes2.dex */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    Class<? extends d> f10021a;

    /* renamed from: b  reason: collision with root package name */
    private com.bytedance.pipeline.listener.a f10022b;

    /* renamed from: c  reason: collision with root package name */
    private Object[] f10023c;

    private i(a aVar) {
        this.f10021a = aVar.f10024a;
        this.f10022b = aVar.f10025b;
        this.f10023c = aVar.f10026c;
        if (this.f10021a == null) {
            throw new IllegalArgumentException("Interceptor class == null");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class<? extends d> a() {
        return this.f10021a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.bytedance.pipeline.listener.a b() {
        return this.f10022b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object[] c() {
        return this.f10023c;
    }

    /* compiled from: Pipe.java */
    /* loaded from: classes2.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private Class<? extends d> f10024a;

        /* renamed from: b  reason: collision with root package name */
        private com.bytedance.pipeline.listener.a f10025b;

        /* renamed from: c  reason: collision with root package name */
        private Object[] f10026c;

        private a() {
        }

        public static a a() {
            return new a();
        }

        public a a(Class<? extends d> cls) {
            if (cls == null) {
                throw new IllegalArgumentException("interceptor class == null");
            }
            this.f10024a = cls;
            return this;
        }

        public a a(com.bytedance.pipeline.listener.a aVar) {
            this.f10025b = aVar;
            return this;
        }

        public a a(Object... objArr) {
            if (objArr == null) {
                throw new IllegalArgumentException("args == null");
            }
            this.f10026c = objArr;
            return this;
        }

        public i b() {
            return new i(this);
        }
    }
}
