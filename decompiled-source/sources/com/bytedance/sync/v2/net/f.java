package com.bytedance.sync.v2.net;

import com.bytedance.sync.m;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* compiled from: NetThreadPool.java */
/* loaded from: classes3.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static final m<ExecutorService> f13837a = new m<ExecutorService>() { // from class: com.bytedance.sync.v2.net.f.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.sync.m
        /* renamed from: a */
        public ExecutorService b(Object... objArr) {
            return Executors.newSingleThreadExecutor();
        }
    };

    public static void submit(Runnable runnable) {
        f13837a.get(new Object[0]).submit(runnable);
    }
}
