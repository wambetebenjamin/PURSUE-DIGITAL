package com.bytedance.robust.common.util;

import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import com.meituan.robust.PatchProxyResult;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: PatchThreadPool.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static ChangeQuickRedirect f12108a;

    /* renamed from: b  reason: collision with root package name */
    private static volatile ExecutorService f12109b;

    public static ExecutorService a() {
        PatchProxyResult proxy = PatchProxy.proxy(new Object[0], null, f12108a, true, "62416fb9b113677e038b55936b8db824");
        if (proxy != null) {
            return (ExecutorService) proxy.result;
        }
        if (f12109b == null) {
            synchronized (a.class) {
                if (f12109b == null) {
                    int availableProcessors = Runtime.getRuntime().availableProcessors() + 1;
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(availableProcessors, availableProcessors, 15L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryC0177a("PatchThreadPool"));
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                    f12109b = threadPoolExecutor;
                }
            }
        }
        return f12109b;
    }

    /* compiled from: PatchThreadPool.java */
    /* renamed from: com.bytedance.robust.common.util.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    static class ThreadFactoryC0177a implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        public static ChangeQuickRedirect f12110a;

        /* renamed from: b  reason: collision with root package name */
        private final String f12111b;

        /* renamed from: c  reason: collision with root package name */
        private final AtomicInteger f12112c = new AtomicInteger();

        ThreadFactoryC0177a(String str) {
            this.f12111b = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            PatchProxyResult proxy = PatchProxy.proxy(new Object[]{runnable}, this, f12110a, false, "015d003e4f2349698e94ca885fecbe22");
            return proxy != null ? (Thread) proxy.result : new Thread(runnable, this.f12111b + "-" + this.f12112c.incrementAndGet());
        }
    }
}
