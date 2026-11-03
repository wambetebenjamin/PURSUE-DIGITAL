package com.bytedance.pipeline;
/* compiled from: InterceptorFactory.java */
/* loaded from: classes2.dex */
public interface f {
    <T> T a(Class<T> cls);

    /* compiled from: InterceptorFactory.java */
    /* loaded from: classes2.dex */
    public static class a implements f {
        @Override // com.bytedance.pipeline.f
        public <T> T a(Class<T> cls) {
            try {
                return cls.newInstance();
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }
}
