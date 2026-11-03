package com.bytedance.apm.block.trace;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.view.Choreographer;
import android.view.FrameMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import com.bytedance.apm.core.ActivityLifeObserver;
import com.bytedance.monitor.collector.o;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* compiled from: MainThreadMonitor.java */
/* loaded from: classes.dex */
public class g implements b, com.bytedance.apm.core.h {
    private static com.bytedance.apm.block.a A = null;
    public static final int CALLBACK_INPUT = 0;

    /* renamed from: c  reason: collision with root package name */
    private static final String f785c = "MainThreadMonitor";

    /* renamed from: d  reason: collision with root package name */
    private static final String f786d = "addCallbackLocked";

    /* renamed from: e  reason: collision with root package name */
    private static final String f787e = "FRAME_CALLBACK_TOKEN";
    private static final g s = new g();
    private static boolean y;

    /* renamed from: b  reason: collision with root package name */
    Runnable f789b;

    /* renamed from: f  reason: collision with root package name */
    private volatile boolean f790f;
    private Object l;
    private Object[] m;
    private long[] n;
    private Method o;
    private Choreographer p;
    private boolean t;
    public boolean v;
    private boolean z;
    public final f mainThreadInfo = new f();

    /* renamed from: g  reason: collision with root package name */
    private long[] f791g = new long[4];

    /* renamed from: h  reason: collision with root package name */
    private final List<com.bytedance.apm.block.a> f792h = new CopyOnWriteArrayList();
    private boolean w = false;

    /* renamed from: i  reason: collision with root package name */
    private boolean f793i = false;

    /* renamed from: j  reason: collision with root package name */
    private boolean f794j = false;
    private boolean k = false;
    private boolean q = false;
    final com.bytedance.apm.thread.e u = new com.bytedance.apm.thread.e(com.bytedance.apm.constant.b.APM_INNER_LOOPER_COST);

    /* renamed from: a  reason: collision with root package name */
    final com.bytedance.apm.thread.d f788a = new com.bytedance.apm.thread.d(com.bytedance.apm.constant.b.APM_INNER_LOOPER_COST);
    private HashMap<String, Window.OnFrameMetricsAvailableListener> x = new HashMap<>();
    private long r = -1;

    @Override // com.bytedance.apm.core.h
    public void onBackground(Activity activity) {
    }

    public static g getMonitor() {
        return s;
    }

    private g() {
    }

    public boolean isInit() {
        return this.t;
    }

    public void d() {
        this.u.a();
        this.f788a.start();
    }

    public void init() {
        if (this.t) {
            return;
        }
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new AssertionError("must be init in main thread!");
        }
        ActivityLifeObserver.getInstance().register(this);
        com.bytedance.monitor.collector.j.init();
        com.bytedance.monitor.collector.j.setFirstListener(new com.bytedance.monitor.collector.a() { // from class: com.bytedance.apm.block.trace.g.1
            @Override // com.bytedance.monitor.collector.a
            public boolean isValid() {
                return g.this.f790f;
            }

            @Override // com.bytedance.monitor.collector.a
            public void dispatchStart(String str) {
                super.dispatchStart(str);
                g.this.a(str);
            }

            @Override // com.bytedance.monitor.collector.a
            public void a(String str, Message message) {
                super.a(str, message);
                g.this.a();
            }
        });
        this.t = true;
    }

    public void setFullFpsTracer(boolean z) {
        this.f793i = z;
    }

    public void a(boolean z) {
        this.w = z;
    }

    public com.bytedance.apm.thread.d getThreadWithHandler() {
        return this.f788a;
    }

    private boolean k() {
        return this.f793i && Build.VERSION.SDK_INT < 24;
    }

    public com.bytedance.apm.thread.e e() {
        return this.u;
    }

    public void addObserver(com.bytedance.apm.block.a aVar) {
        if (!this.f790f) {
            onStart();
        }
        if (this.f792h.contains(aVar)) {
            return;
        }
        this.f792h.add(aVar);
        if (this.v) {
            com.bytedance.monitor.collector.j.d();
        }
    }

    public void f() {
        y = true;
    }

    public boolean g() {
        return y;
    }

    public void removeObserver(com.bytedance.apm.block.a aVar) {
        this.f792h.remove(aVar);
        if (this.v) {
            if (h() == 0 && com.bytedance.monitor.collector.j.c() == 0) {
                com.bytedance.monitor.collector.j.b();
            }
        } else if (this.f792h.isEmpty()) {
            onStop();
        }
    }

    public int h() {
        int size = this.f792h.size();
        return (!g() || com.bytedance.apm.samplers.c.getPerfSecondStageSwitch("block_monitor")) ? size : size - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.k = true;
        this.f791g[0] = com.bytedance.monitor.collector.a.uptime;
        this.f791g[2] = com.bytedance.monitor.collector.a.cpuTime;
        List<com.bytedance.apm.block.a> list = this.f792h;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            com.bytedance.apm.block.a aVar = list.get(i2);
            if (!aVar.isDispatchBegin()) {
                aVar.dispatchBegin(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        boolean z;
        boolean z2 = this.f794j;
        if (k() && this.f794j) {
            c();
            final long j2 = com.bytedance.monitor.collector.a.uptime;
            final long j3 = this.r;
            if (this.n != null) {
                if (o.getInstance().getFrameCallback() != null) {
                    o.getInstance().getFrameCallback().doFrame(this.n);
                }
                e.getInstance().doFrame(this.n, j2);
            }
            if (Build.VERSION.SDK_INT < 24) {
                this.u.a(new Runnable() { // from class: com.bytedance.apm.block.trace.g.2
                    @Override // java.lang.Runnable
                    public void run() {
                        for (com.bytedance.apm.block.a aVar : g.this.f792h) {
                            aVar.doFrame(ActivityLifeObserver.getInstance().getTopActivityClassName(), j3, j2);
                        }
                    }
                });
            }
        }
        this.f791g[1] = com.bytedance.monitor.collector.a.uptime;
        this.f791g[3] = com.bytedance.monitor.collector.a.cpuTime;
        List<com.bytedance.apm.block.a> list = this.f792h;
        int size = list.size();
        int i2 = 0;
        while (i2 < size) {
            com.bytedance.apm.block.a aVar = list.get(i2);
            if (aVar.isDispatchBegin()) {
                long[] jArr = this.f791g;
                z = z2;
                aVar.dispatchEnd(jArr[0], jArr[2], jArr[1], jArr[3], z);
            } else {
                z = z2;
            }
            i2++;
            z2 = z;
        }
        this.mainThreadInfo.reset();
        this.k = false;
    }

    @Override // com.bytedance.apm.block.trace.b
    public synchronized void onStart() {
        if (com.bytedance.apm.c.isMainProcessSimple()) {
            if (!this.t) {
                throw new RuntimeException("never init!");
            }
            if (!this.f790f) {
                this.f790f = true;
            }
            if (k() && Build.VERSION.SDK_INT >= 16) {
                a(this.f789b);
            }
        }
    }

    @Override // com.bytedance.apm.block.trace.b
    public synchronized void onStop() {
        if (com.bytedance.apm.c.isMainProcess()) {
            if (!this.t) {
                throw new RuntimeException("MainThreadMonitor is never init!");
            }
            if (this.f790f) {
                this.f790f = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(Runnable runnable) {
        if (this.f790f) {
            if (this.q) {
                return;
            }
            try {
                synchronized (this.l) {
                    Method method = this.o;
                    if (method != null) {
                        method.invoke(this.m[0], -1L, runnable, null);
                        this.q = true;
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    private void b() {
        this.f794j = true;
    }

    private void c() {
        if (Build.VERSION.SDK_INT >= 16) {
            a(this.f789b);
        }
        this.f794j = false;
    }

    public void doInputCallbackHook() {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (this.n == null) {
                    this.r = com.bytedance.monitor.collector.a.uptime;
                } else if (Build.VERSION.SDK_INT >= 31) {
                    this.r = this.n[2] / 1000000;
                } else {
                    this.r = this.n[1] / 1000000;
                }
            } else {
                this.r = com.bytedance.monitor.collector.a.uptime;
            }
            b();
        } finally {
            this.q = false;
        }
    }

    @Override // com.bytedance.apm.block.trace.b
    public boolean isAlive() {
        return this.f790f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T a(Object obj, String str) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return (T) declaredField.get(obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T b(Object obj, String str) {
        try {
            Field field = (Field) Class.class.getDeclaredMethod("getDeclaredField", String.class).invoke(obj.getClass(), str);
            field.setAccessible(true);
            return (T) field.get(obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Method a(Object obj, String str, Class<?>... clsArr) {
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Exception unused) {
            return null;
        }
    }

    public static Method reflectHideMethod(Class cls, String str, Class<?>... clsArr) {
        try {
            Method method = (Method) Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class).invoke(cls, str, clsArr);
            method.setAccessible(true);
            return method;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.bytedance.apm.core.h
    public void onFront(Activity activity) {
        if (Build.VERSION.SDK_INT < 16 || this.p != null) {
            return;
        }
        if (k()) {
            try {
                this.p = Choreographer.getInstance();
            } catch (Exception unused) {
            }
            com.bytedance.apm.thread.b.getInstance().post(new Runnable() { // from class: com.bytedance.apm.block.trace.g.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        g.this.f789b = new Runnable() { // from class: com.bytedance.apm.block.trace.g.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    g.this.doInputCallbackHook();
                                } catch (Throwable unused2) {
                                }
                            }
                        };
                        g gVar = g.this;
                        gVar.l = gVar.a(gVar.p, "mLock");
                        if (g.this.l == null) {
                            g gVar2 = g.this;
                            gVar2.l = gVar2.b(gVar2.p, "mLock");
                        }
                        g gVar3 = g.this;
                        gVar3.m = (Object[]) gVar3.a(gVar3.p, "mCallbackQueues");
                        if (g.this.m == null) {
                            g gVar4 = g.this;
                            gVar4.m = (Object[]) gVar4.b(gVar4.p, "mCallbackQueues");
                        }
                        if (Build.VERSION.SDK_INT == 28) {
                            g gVar5 = g.this;
                            gVar5.n = (long[]) gVar5.b(gVar5.b(gVar5.p, "mFrameInfo"), "mFrameInfo");
                        } else if (Build.VERSION.SDK_INT > 28) {
                            g gVar6 = g.this;
                            gVar6.n = (long[]) gVar6.b(gVar6.b(gVar6.p, "mFrameInfo"), "frameInfo");
                        } else if (Build.VERSION.SDK_INT > 22) {
                            g gVar7 = g.this;
                            gVar7.n = (long[]) gVar7.a(gVar7.a(gVar7.p, "mFrameInfo"), "mFrameInfo");
                        }
                        if (g.this.n == null && Build.VERSION.SDK_INT > 22) {
                            com.bytedance.services.apm.api.b.ensureNotReachHere("FrameInfoIsNull");
                        }
                        if (g.this.l == null) {
                            com.bytedance.services.apm.api.b.ensureNotReachHere("CallbackQueueLockIsNull");
                        }
                        if (g.this.m == null) {
                            com.bytedance.services.apm.api.b.ensureNotReachHere("callbackQueuesIsNull");
                        }
                        g gVar8 = g.this;
                        gVar8.o = gVar8.a(gVar8.m[0], g.f786d, Long.TYPE, Object.class, Object.class);
                        if (Build.VERSION.SDK_INT >= 16) {
                            g gVar9 = g.this;
                            gVar9.a(gVar9.f789b);
                        }
                    } catch (Exception e2) {
                        com.bytedance.services.apm.api.b.ensureNotReachHere(e2, "MainThreadMonitor_fullFps");
                    }
                }
            });
        }
        if (this.w) {
            try {
                if (Build.VERSION.SDK_INT >= 24) {
                    Window window = activity.getWindow();
                    if (this.f788a.isReady()) {
                        AnonymousClass4 anonymousClass4 = new AnonymousClass4(activity);
                        this.x.put(activity.toString(), anonymousClass4);
                        window.addOnFrameMetricsAvailableListener(anonymousClass4, this.f788a.f1478f);
                    }
                }
            } catch (Exception unused2) {
            }
        }
    }

    /* compiled from: MainThreadMonitor.java */
    /* renamed from: com.bytedance.apm.block.trace.g$4  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass4 implements Window.OnFrameMetricsAvailableListener {

        /* renamed from: b  reason: collision with root package name */
        String f802b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Activity f803c;

        AnonymousClass4(Activity activity) {
            this.f803c = activity;
            this.f802b = activity.getClass().getName();
        }

        @Override // android.view.Window.OnFrameMetricsAvailableListener
        public void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, final int i2) {
            if (g.this.z) {
                final long metric = (((((frameMetrics.getMetric(0) + frameMetrics.getMetric(1)) + frameMetrics.getMetric(2)) + frameMetrics.getMetric(3)) + frameMetrics.getMetric(4)) + frameMetrics.getMetric(5)) / 1000000;
                final long metric2 = frameMetrics.getMetric(9);
                g.this.u.a(new Runnable() { // from class: com.bytedance.apm.block.trace.g.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (metric2 == 0) {
                            if (g.A == null || !g.this.v) {
                                for (com.bytedance.apm.block.a aVar : g.this.f792h) {
                                    aVar.a(AnonymousClass4.this.f802b, metric, i2);
                                }
                                return;
                            }
                            g.A.a(AnonymousClass4.this.f802b, metric, i2);
                        }
                    }
                });
                return;
            }
            final FrameMetrics frameMetrics2 = new FrameMetrics(frameMetrics);
            g.this.u.a(new Runnable() { // from class: com.bytedance.apm.block.trace.g.4.2
                @Override // java.lang.Runnable
                public void run() {
                    if (frameMetrics2.getMetric(9) == 0) {
                        if (g.A == null || !g.this.v) {
                            for (com.bytedance.apm.block.a aVar : g.this.f792h) {
                                aVar.a(AnonymousClass4.this.f802b, frameMetrics2, i2);
                            }
                            return;
                        }
                        g.A.a(AnonymousClass4.this.f802b, frameMetrics2, i2);
                    }
                }
            });
        }
    }

    @Override // com.bytedance.apm.core.h
    public void onActivityCreated(Activity activity, Bundle bundle) {
        try {
            Window window = activity.getWindow();
            if (Build.VERSION.SDK_INT >= 23) {
                window.setCallback(new j(window.getCallback()) { // from class: com.bytedance.apm.block.trace.g.5
                    @Override // android.view.Window.Callback
                    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
                        if (!g.this.k) {
                            g.this.mainThreadInfo.addInputEventInfo(keyEvent.getEventTime());
                        }
                        return this.f836b.dispatchKeyEvent(keyEvent);
                    }

                    @Override // android.view.Window.Callback
                    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
                        if (!g.this.k) {
                            g.this.mainThreadInfo.addInputEventInfo(motionEvent.getEventTime());
                        }
                        return this.f836b.dispatchTouchEvent(motionEvent);
                    }
                });
            }
            final String name = activity.getClass().getName();
            String obj = activity.toString();
            com.bytedance.monitor.collector.j.e();
            if (!this.w && Build.VERSION.SDK_INT >= 24 && this.f788a.isReady()) {
                Window.OnFrameMetricsAvailableListener onFrameMetricsAvailableListener = new Window.OnFrameMetricsAvailableListener() { // from class: com.bytedance.apm.block.trace.g.6
                    @Override // android.view.Window.OnFrameMetricsAvailableListener
                    public void onFrameMetricsAvailable(Window window2, FrameMetrics frameMetrics, final int i2) {
                        if (g.this.z) {
                            final long metric = (((((frameMetrics.getMetric(0) + frameMetrics.getMetric(1)) + frameMetrics.getMetric(2)) + frameMetrics.getMetric(3)) + frameMetrics.getMetric(4)) + frameMetrics.getMetric(5)) / 1000000;
                            final long metric2 = frameMetrics.getMetric(9);
                            g.this.u.a(new Runnable() { // from class: com.bytedance.apm.block.trace.g.6.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (metric2 == 0) {
                                        if (g.A == null || !g.this.v) {
                                            for (com.bytedance.apm.block.a aVar : g.this.f792h) {
                                                aVar.a(name, metric, i2);
                                            }
                                            return;
                                        }
                                        g.A.a(name, metric, i2);
                                    }
                                }
                            });
                            return;
                        }
                        final FrameMetrics frameMetrics2 = new FrameMetrics(frameMetrics);
                        g.this.u.a(new Runnable() { // from class: com.bytedance.apm.block.trace.g.6.2
                            @Override // java.lang.Runnable
                            public void run() {
                                if (frameMetrics2.getMetric(9) == 0) {
                                    if (g.A == null || !g.this.v) {
                                        for (com.bytedance.apm.block.a aVar : g.this.f792h) {
                                            aVar.a(name, frameMetrics2, i2);
                                        }
                                        return;
                                    }
                                    g.A.a(name, frameMetrics2, i2);
                                }
                            }
                        });
                    }
                };
                this.x.put(obj, onFrameMetricsAvailableListener);
                window.addOnFrameMetricsAvailableListener(onFrameMetricsAvailableListener, this.f788a.f1478f);
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.bytedance.apm.core.h
    public void a(Activity activity) {
        Window.OnFrameMetricsAvailableListener remove;
        try {
            if (Build.VERSION.SDK_INT < 24 || (remove = this.x.remove(activity.toString())) == null) {
                return;
            }
            activity.getWindow().removeOnFrameMetricsAvailableListener(remove);
        } catch (Exception unused) {
        }
    }

    public void b(boolean z) {
        this.z = z;
    }

    public void c(boolean z) {
        this.v = z;
    }

    public boolean i() {
        return this.v;
    }

    public void a(com.bytedance.apm.block.a aVar) {
        A = aVar;
    }
}
