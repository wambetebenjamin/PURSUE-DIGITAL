package com.bytedance.geckox.listener;

import java.util.HashMap;
import java.util.Map;
/* compiled from: ListenerProvider.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<Class<? extends com.bytedance.pipeline.d<?, ?>>, com.bytedance.pipeline.listener.b> f9161a = new HashMap();

    public static void a(Class<? extends com.bytedance.pipeline.d<?, ?>> cls, com.bytedance.pipeline.listener.a aVar) {
        Map<Class<? extends com.bytedance.pipeline.d<?, ?>>, com.bytedance.pipeline.listener.b> map = f9161a;
        synchronized (map) {
            com.bytedance.pipeline.listener.b bVar = map.get(cls);
            if (bVar == null) {
                bVar = new com.bytedance.pipeline.listener.b(new com.bytedance.pipeline.listener.a[0]);
                map.put(cls, bVar);
            }
            bVar.a(aVar);
        }
    }

    public static void b(Class<? extends com.bytedance.pipeline.d<?, ?>> cls, com.bytedance.pipeline.listener.a aVar) {
        Map<Class<? extends com.bytedance.pipeline.d<?, ?>>, com.bytedance.pipeline.listener.b> map = f9161a;
        synchronized (map) {
            com.bytedance.pipeline.listener.b bVar = map.get(cls);
            if (bVar == null) {
                return;
            }
            bVar.b(aVar);
        }
    }

    public static com.bytedance.pipeline.listener.a a(Class<? extends com.bytedance.pipeline.d<?, ?>> cls) {
        com.bytedance.pipeline.listener.b bVar;
        Map<Class<? extends com.bytedance.pipeline.d<?, ?>>, com.bytedance.pipeline.listener.b> map = f9161a;
        synchronized (map) {
            bVar = map.get(cls);
        }
        return bVar;
    }
}
