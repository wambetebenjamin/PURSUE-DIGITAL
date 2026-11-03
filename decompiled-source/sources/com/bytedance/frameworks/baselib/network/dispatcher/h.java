package com.bytedance.frameworks.baselib.network.dispatcher;

import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import com.bytedance.common.utility.collection.WeakHandler;
import com.bytedance.frameworks.baselib.network.dispatcher.f;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetThreadPoolManager.java */
/* loaded from: classes2.dex */
public class h implements WeakHandler.IHandler {

    /* renamed from: b  reason: collision with root package name */
    private static final int f7534b = 0;

    /* renamed from: c  reason: collision with root package name */
    private static final int f7535c = 1;

    /* renamed from: d  reason: collision with root package name */
    private WeakHandler f7537d = new WeakHandler(Looper.getMainLooper(), this);

    /* renamed from: e  reason: collision with root package name */
    private volatile ThreadPoolExecutor f7538e;

    /* renamed from: f  reason: collision with root package name */
    private volatile ThreadPoolExecutor f7539f;

    /* renamed from: g  reason: collision with root package name */
    private volatile ThreadPoolExecutor f7540g;

    /* renamed from: h  reason: collision with root package name */
    private volatile ThreadPoolExecutor f7541h;

    /* renamed from: j  reason: collision with root package name */
    private volatile ThreadPoolExecutor f7542j;

    /* renamed from: i  reason: collision with root package name */
    private static AtomicInteger f7536i = new AtomicInteger();

    /* renamed from: a  reason: collision with root package name */
    static h f7533a = new h();

    private static int a() {
        return f7536i.incrementAndGet();
    }

    private h() {
    }

    private j b() {
        return i.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ExecutorService c() {
        if (this.f7538e == null) {
            synchronized (this) {
                if (this.f7538e == null) {
                    this.f7538e = b().a();
                    if (this.f7538e == null) {
                        this.f7538e = new ThreadPoolExecutor(b().q(), Integer.MAX_VALUE, b().k(), TimeUnit.SECONDS, new SynchronousQueue(), new b("NetImmediate"));
                    }
                }
            }
        }
        return this.f7538e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ExecutorService d() {
        if (this.f7539f == null) {
            synchronized (this) {
                if (this.f7539f == null) {
                    this.f7539f = b().c();
                    if (this.f7539f == null) {
                        this.f7539f = new ThreadPoolExecutor(b().g(), b().e(), b().i(), TimeUnit.SECONDS, new PriorityBlockingQueue(), new b("NetNormal"));
                        this.f7539f.allowCoreThreadTimeOut(i.a().l());
                    }
                }
            }
        }
        return this.f7539f;
    }

    private ExecutorService e() {
        if (this.f7540g == null) {
            synchronized (this) {
                if (this.f7540g == null) {
                    this.f7540g = b().d();
                    if (this.f7540g == null) {
                        this.f7540g = new ThreadPoolExecutor(b().h(), b().f(), b().j(), TimeUnit.SECONDS, new PriorityBlockingQueue(), new b("NetDownload"));
                        this.f7540g.allowCoreThreadTimeOut(i.a().l());
                    }
                }
            }
        }
        return this.f7540g;
    }

    private ExecutorService g() {
        if (this.f7542j == null) {
            synchronized (this) {
                if (this.f7542j == null) {
                    this.f7542j = b().n();
                    if (this.f7542j == null) {
                        this.f7542j = new ThreadPoolExecutor(b().o(), b().o(), b().p(), TimeUnit.SECONDS, new PriorityBlockingQueue(), new b("NetLowest"));
                        this.f7542j.allowCoreThreadTimeOut(true);
                    }
                }
            }
        }
        return this.f7542j;
    }

    private ExecutorService f() {
        if (this.f7541h == null) {
            synchronized (this) {
                if (this.f7541h == null) {
                    this.f7541h = b().b();
                    if (this.f7541h == null) {
                        this.f7541h = new ThreadPoolExecutor(1, 1, b().k(), TimeUnit.SECONDS, new PriorityBlockingQueue(), new b("NetLocal"));
                    }
                }
            }
        }
        return this.f7541h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(e eVar) {
        if (eVar == null || eVar.i()) {
            return;
        }
        eVar.b(a());
        if (eVar.c() == f.a.IMMEDIATE) {
            c().execute(eVar);
        } else if (eVar.c() == f.a.LOWEST) {
            g().execute(eVar);
        } else {
            long j2 = eVar.j();
            if (j2 > 0) {
                Message obtain = Message.obtain();
                obtain.what = 1;
                obtain.obj = eVar;
                this.f7537d.sendMessageDelayed(obtain, j2);
                return;
            }
            a(this.f7540g, eVar);
            e().execute(eVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(e eVar) {
        if (eVar == null || eVar.i()) {
            return;
        }
        eVar.b(a());
        if (eVar.g()) {
            f().execute(c(eVar));
        } else if (eVar.c() == f.a.IMMEDIATE) {
            c().execute(eVar);
        } else if (eVar.c() == f.a.LOWEST) {
            g().execute(eVar);
        } else {
            long j2 = eVar.j();
            if (j2 > 0) {
                Message obtain = Message.obtain();
                obtain.what = 0;
                obtain.obj = eVar;
                this.f7537d.sendMessageDelayed(obtain, j2);
                return;
            }
            a(this.f7539f, eVar);
            d().execute(eVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: NetThreadPoolManager.java */
    /* loaded from: classes2.dex */
    public abstract class a implements Comparable<a>, Runnable {

        /* renamed from: a  reason: collision with root package name */
        private e f7545a;

        public a(e eVar) {
            this.f7545a = eVar;
        }
    }

    private a c(final e eVar) {
        return new a(eVar) { // from class: com.bytedance.frameworks.baselib.network.dispatcher.h.1
            @Override // java.lang.Comparable
            /* renamed from: a */
            public int compareTo(a aVar) {
                e eVar2 = eVar;
                if (eVar2 != null) {
                    return eVar2.compareTo(aVar.f7545a);
                }
                return 0;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (eVar.f()) {
                    return;
                }
                if (eVar.c() == f.a.IMMEDIATE) {
                    h.this.c().execute(eVar);
                } else {
                    h.this.d().execute(eVar);
                }
            }
        };
    }

    @Override // com.bytedance.common.utility.collection.WeakHandler.IHandler
    public void handleMsg(Message message) {
        if (message == null || !(message.obj instanceof Runnable)) {
            return;
        }
        try {
            int i2 = message.what;
            if (i2 == 0) {
                a(this.f7539f, (e) message.obj);
                d().execute((Runnable) message.obj);
            } else if (i2 == 1) {
                a(this.f7540g, (e) message.obj);
                c().execute((Runnable) message.obj);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: NetThreadPoolManager.java */
    /* loaded from: classes2.dex */
    public static class b implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f7547a = new AtomicInteger(1);

        /* renamed from: b  reason: collision with root package name */
        private String f7548b;

        b(String str) {
            this.f7548b = "ApiExecutor";
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.f7548b = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, this.f7548b + "#" + this.f7547a.getAndIncrement()) { // from class: com.bytedance.frameworks.baselib.network.dispatcher.h.b.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Process.setThreadPriority(10);
                    super.run();
                }
            };
            thread.setDaemon(false);
            return thread;
        }
    }

    private static void a(ThreadPoolExecutor threadPoolExecutor, e eVar) {
        if (threadPoolExecutor == null || !(eVar instanceof com.bytedance.frameworks.baselib.network.dispatcher.b)) {
            return;
        }
        ((com.bytedance.frameworks.baselib.network.dispatcher.b) eVar).c(threadPoolExecutor.getQueue().size());
    }
}
