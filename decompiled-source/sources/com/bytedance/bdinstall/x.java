package com.bytedance.bdinstall;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* compiled from: ExecutorUtil.java */
/* loaded from: classes.dex */
public class x {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, Executor> f4110a = new ConcurrentHashMap(4);

    /* renamed from: b  reason: collision with root package name */
    private static Map<String, ExecutorService> f4111b = new ConcurrentHashMap(4);

    /* renamed from: c  reason: collision with root package name */
    private static Map<String, Looper> f4112c = new ConcurrentHashMap(4);

    /* renamed from: d  reason: collision with root package name */
    private static Map<String, Handler> f4113d = new ConcurrentHashMap(4);

    /* renamed from: e  reason: collision with root package name */
    private static final com.bytedance.bdinstall.util.r<Executor> f4114e = new com.bytedance.bdinstall.util.r<Executor>() { // from class: com.bytedance.bdinstall.x.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.bdinstall.util.r
        /* renamed from: a */
        public Executor b(Object... objArr) {
            return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS, new SynchronousQueue());
        }
    };

    /* renamed from: f  reason: collision with root package name */
    private static final com.bytedance.bdinstall.util.r<ExecutorService> f4115f = new com.bytedance.bdinstall.util.r<ExecutorService>() { // from class: com.bytedance.bdinstall.x.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.bdinstall.util.r
        /* renamed from: a */
        public ExecutorService b(Object... objArr) {
            return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 5L, TimeUnit.SECONDS, new SynchronousQueue());
        }
    };

    /* renamed from: g  reason: collision with root package name */
    private static final com.bytedance.bdinstall.util.r<Looper> f4116g = new com.bytedance.bdinstall.util.r<Looper>() { // from class: com.bytedance.bdinstall.x.3
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.bdinstall.util.r
        /* renamed from: a */
        public Looper b(Object... objArr) {
            try {
                HandlerThread handlerThread = new HandlerThread("bd_install");
                handlerThread.start();
                return handlerThread.getLooper();
            } catch (Exception unused) {
                return Looper.getMainLooper();
            }
        }
    };

    /* renamed from: h  reason: collision with root package name */
    private static final com.bytedance.bdinstall.util.r<Handler> f4117h = new com.bytedance.bdinstall.util.r<Handler>() { // from class: com.bytedance.bdinstall.x.4
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.bdinstall.util.r
        /* renamed from: a */
        public Handler b(Object... objArr) {
            return new Handler((Looper) x.f4116g.c(new Object[0]));
        }
    };

    private static Executor c(String str) {
        if (str == null) {
            return f4114e.c(new Object[0]);
        }
        Executor executor = f4110a.get(str);
        return executor == null ? f4114e.c(new Object[0]) : executor;
    }

    private static ExecutorService d(String str) {
        if (str == null) {
            return f4115f.c(new Object[0]);
        }
        ExecutorService executorService = f4111b.get(str);
        return executorService == null ? f4115f.c(new Object[0]) : executorService;
    }

    public static Looper a(String str) {
        if (str == null) {
            return f4116g.c(new Object[0]);
        }
        Looper looper = f4112c.get(str);
        return looper == null ? f4116g.c(new Object[0]) : looper;
    }

    public static Handler a() {
        return b(a(f4113d));
    }

    public static Handler b(String str) {
        if (str == null) {
            return f4117h.c(new Object[0]);
        }
        Handler handler = f4113d.get(str);
        if (handler == null) {
            Looper a2 = a(str);
            if (a2 != null) {
                Handler handler2 = new Handler(a2);
                f4113d.put(str, handler2);
                return handler2;
            }
            u.c("getLooper return null");
            return handler;
        }
        return handler;
    }

    public static void a(String str, Runnable runnable) {
        c(str).execute(runnable);
    }

    public static void b(String str, Runnable runnable) {
        d(str).execute(runnable);
    }

    public static <T> Future<T> a(String str, Callable<T> callable) {
        return d(str).submit(callable);
    }

    public static <T> Future<T> a(Callable<T> callable) {
        return a(a(f4111b), callable);
    }

    public static void c(String str, Runnable runnable) {
        if (Looper.myLooper() == a(str)) {
            runnable.run();
            return;
        }
        Handler b2 = b(str);
        if (b2 == null) {
            b2 = f4117h.c(new Object[0]);
        }
        b2.post(runnable);
    }

    public static void a(Runnable runnable) {
        c(a(f4113d), runnable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(String str, w wVar) {
        if (wVar == null) {
            return;
        }
        Executor executor = wVar.f4107a;
        if (executor != null) {
            f4110a.put(str, executor);
        }
        ExecutorService executorService = wVar.f4108b;
        if (executorService != null) {
            f4111b.put(str, executorService);
        }
        Looper looper = wVar.f4109c;
        if (looper != null) {
            f4112c.put(str, looper);
        }
    }

    private static String a(Map map) {
        Set keySet;
        if (map == null || map.size() == 0 || (keySet = map.keySet()) == null || keySet.size() <= 0) {
            return null;
        }
        return (String) keySet.iterator().next();
    }
}
