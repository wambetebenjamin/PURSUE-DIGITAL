package com.bytedance.frameworks.baselib.network.utils;

import com.bytedance.common.utility.reflect.Reflect;
import com.bytedance.retrofit2.RetrofitMetrics;
/* compiled from: ReflectWrap.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static volatile boolean f8513a = false;

    /* compiled from: ReflectWrap.java */
    /* renamed from: com.bytedance.frameworks.baselib.network.utils.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0120b {
        InterfaceC0120b a(String str);

        InterfaceC0120b a(String str, Class<?>[] clsArr, Object... objArr);

        <T> T a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReflectWrap.java */
    /* loaded from: classes2.dex */
    public static class c implements InterfaceC0120b {

        /* renamed from: a  reason: collision with root package name */
        private com.bytedance.frameworks.baselib.network.utils.c f8516a;

        /* renamed from: b  reason: collision with root package name */
        private final RetrofitMetrics f8517b;

        c(com.bytedance.frameworks.baselib.network.utils.c cVar, RetrofitMetrics retrofitMetrics) {
            this.f8516a = cVar;
            this.f8517b = retrofitMetrics;
        }

        @Override // com.bytedance.frameworks.baselib.network.utils.b.InterfaceC0120b
        public InterfaceC0120b a(String str) {
            long nanoTime = System.nanoTime();
            try {
                try {
                    this.f8516a = this.f8516a.d(str);
                    return this;
                } catch (Exception e2) {
                    throw new e(e2.getMessage(), e2.getCause());
                }
            } finally {
                RetrofitMetrics retrofitMetrics = this.f8517b;
                if (retrofitMetrics != null) {
                    if (retrofitMetrics.networklib.innerSpan.reflection == -1) {
                        this.f8517b.networklib.innerSpan.reflection = 0L;
                    }
                    this.f8517b.networklib.innerSpan.reflection += System.nanoTime() - nanoTime;
                }
            }
        }

        @Override // com.bytedance.frameworks.baselib.network.utils.b.InterfaceC0120b
        public InterfaceC0120b a(String str, Class<?>[] clsArr, Object... objArr) {
            long nanoTime = System.nanoTime();
            try {
                try {
                    this.f8516a = this.f8516a.a(str, clsArr, objArr);
                    return this;
                } catch (Exception e2) {
                    throw new e(e2.getMessage(), e2.getCause());
                }
            } finally {
                RetrofitMetrics retrofitMetrics = this.f8517b;
                if (retrofitMetrics != null) {
                    if (retrofitMetrics.networklib.innerSpan.reflection == -1) {
                        this.f8517b.networklib.innerSpan.reflection = 0L;
                    }
                    this.f8517b.networklib.innerSpan.reflection += System.nanoTime() - nanoTime;
                }
            }
        }

        @Override // com.bytedance.frameworks.baselib.network.utils.b.InterfaceC0120b
        public <T> T a() {
            return (T) this.f8516a.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReflectWrap.java */
    /* loaded from: classes2.dex */
    public static class a implements InterfaceC0120b {

        /* renamed from: a  reason: collision with root package name */
        private Reflect f8514a;

        /* renamed from: b  reason: collision with root package name */
        private final RetrofitMetrics f8515b;

        a(Reflect reflect, RetrofitMetrics retrofitMetrics) {
            this.f8514a = reflect;
            this.f8515b = retrofitMetrics;
        }

        @Override // com.bytedance.frameworks.baselib.network.utils.b.InterfaceC0120b
        public InterfaceC0120b a(String str) {
            long nanoTime = System.nanoTime();
            try {
                try {
                    this.f8514a = this.f8514a.call(str);
                    return this;
                } catch (Exception e2) {
                    throw new e(e2.getMessage(), e2.getCause());
                }
            } finally {
                RetrofitMetrics retrofitMetrics = this.f8515b;
                if (retrofitMetrics != null) {
                    if (retrofitMetrics.networklib.innerSpan.reflection == -1) {
                        this.f8515b.networklib.innerSpan.reflection = 0L;
                    }
                    this.f8515b.networklib.innerSpan.reflection += System.nanoTime() - nanoTime;
                }
            }
        }

        @Override // com.bytedance.frameworks.baselib.network.utils.b.InterfaceC0120b
        public InterfaceC0120b a(String str, Class<?>[] clsArr, Object... objArr) {
            long nanoTime = System.nanoTime();
            try {
                try {
                    this.f8514a = this.f8514a.call(str, clsArr, objArr);
                    return this;
                } catch (Exception e2) {
                    throw new e(e2.getMessage(), e2.getCause());
                }
            } finally {
                RetrofitMetrics retrofitMetrics = this.f8515b;
                if (retrofitMetrics != null) {
                    if (retrofitMetrics.networklib.innerSpan.reflection == -1) {
                        this.f8515b.networklib.innerSpan.reflection = 0L;
                    }
                    this.f8515b.networklib.innerSpan.reflection += System.nanoTime() - nanoTime;
                }
            }
        }

        @Override // com.bytedance.frameworks.baselib.network.utils.b.InterfaceC0120b
        public <T> T a() {
            return (T) this.f8514a.get();
        }
    }

    public static void a(boolean z) {
        f8513a = z;
    }

    public static InterfaceC0120b a(String str) {
        try {
            return a(str, (RetrofitMetrics) null);
        } catch (Exception e2) {
            throw new e(e2.getMessage(), e2.getCause());
        }
    }

    public static InterfaceC0120b a(String str, RetrofitMetrics retrofitMetrics) {
        try {
            if (f8513a) {
                return new c(com.bytedance.frameworks.baselib.network.utils.c.a(str), retrofitMetrics);
            }
            return new a(Reflect.on(str), retrofitMetrics);
        } catch (Exception e2) {
            throw new e(e2.getMessage(), e2.getCause());
        }
    }

    public static InterfaceC0120b a(Class<?> cls) {
        try {
            return a(cls, (RetrofitMetrics) null);
        } catch (Exception e2) {
            throw new e(e2.getMessage(), e2.getCause());
        }
    }

    public static InterfaceC0120b a(Class<?> cls, RetrofitMetrics retrofitMetrics) {
        try {
            if (f8513a) {
                return new c(com.bytedance.frameworks.baselib.network.utils.c.a(cls), retrofitMetrics);
            }
            return new a(Reflect.on(cls), retrofitMetrics);
        } catch (Exception e2) {
            throw new e(e2.getMessage(), e2.getCause());
        }
    }

    public static InterfaceC0120b a(Object obj) {
        try {
            return a(obj, (RetrofitMetrics) null);
        } catch (Exception e2) {
            throw new e(e2.getMessage(), e2.getCause());
        }
    }

    public static InterfaceC0120b a(Object obj, RetrofitMetrics retrofitMetrics) {
        try {
            if (f8513a) {
                return new c(com.bytedance.frameworks.baselib.network.utils.c.a(obj), retrofitMetrics);
            }
            return new a(Reflect.on(obj), retrofitMetrics);
        } catch (Exception e2) {
            throw new e(e2.getMessage(), e2.getCause());
        }
    }
}
