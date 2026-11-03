package com.bytedance.monitor.util.thread.inner;

import android.util.Pair;
import com.bytedance.monitor.util.thread.e;
import com.bytedance.monitor.util.thread.f;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* compiled from: ApmInnerThreadPool.java */
/* loaded from: classes2.dex */
public class c extends ScheduledThreadPoolExecutor implements d {
    public final String TAG;

    /* renamed from: a  reason: collision with root package name */
    private boolean f9706a;

    /* renamed from: b  reason: collision with root package name */
    private f f9707b;

    /* renamed from: c  reason: collision with root package name */
    private final Map<Integer, List<ScheduledFuture<?>>> f9708c;

    /* renamed from: d  reason: collision with root package name */
    private final Map<ScheduledFuture<?>, Pair<Integer, Boolean>> f9709d;

    /* renamed from: e  reason: collision with root package name */
    private ExecutorService f9710e;

    /* renamed from: f  reason: collision with root package name */
    private ThreadPoolExecutor f9711f;

    /* renamed from: g  reason: collision with root package name */
    private int f9712g;

    /* renamed from: h  reason: collision with root package name */
    private a f9713h;

    public c(int i2, ThreadFactory threadFactory) {
        super(i2, threadFactory);
        this.TAG = "ApmInnerThreadPool";
        this.f9706a = false;
        this.f9708c = new ConcurrentHashMap();
        this.f9709d = new ConcurrentHashMap();
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        if (this.f9710e != null) {
            if (b()) {
                a("submit task to outer-executor: " + a(runnable));
            }
            return this.f9710e.submit(runnable);
        }
        return super.submit(runnable);
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        if (this.f9710e != null) {
            if (b()) {
                a("submit task to outer-executor: " + callable);
            }
            return this.f9710e.submit(callable);
        }
        return super.submit(callable);
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t) {
        if (this.f9710e != null) {
            if (b()) {
                a("submit task to outer-executor: " + a(runnable));
            }
            return this.f9710e.submit(runnable, t);
        }
        return super.submit(runnable, t);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void beforeExecute(Thread thread, Runnable runnable) {
        a(true, runnable, thread);
        super.beforeExecute(thread, runnable);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void afterExecute(Runnable runnable, Throwable th) {
        ScheduledFuture scheduledFuture;
        Pair<Integer, Boolean> pair;
        super.afterExecute(runnable, th);
        a(false, runnable, (Thread) null);
        if (!(runnable instanceof ScheduledFuture) || (pair = this.f9709d.get((scheduledFuture = (ScheduledFuture) runnable))) == null || pair.first == null) {
            return;
        }
        int intValue = ((Integer) pair.first).intValue();
        Boolean bool = (Boolean) pair.second;
        if (bool == null || bool.booleanValue()) {
            return;
        }
        if (b()) {
            a("z-debug postDelay or scheduled task: " + runnable + ", taskHashCode: " + intValue + ", in map? " + this.f9708c.containsKey(Integer.valueOf(intValue)));
        }
        a(Integer.valueOf(intValue), scheduledFuture);
    }

    private void a(boolean z, Runnable runnable, Thread thread) {
        if (a()) {
            if (z) {
                this.f9713h = new a(runnable, thread, this.f9712g);
            } else {
                this.f9713h = null;
            }
        }
    }

    private boolean a() {
        return this.f9712g > 0;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void terminated() {
        super.terminated();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b() {
        f fVar;
        return this.f9706a && (fVar = this.f9707b) != null && fVar.isDebug();
    }

    public void setOuterExecutor(final ExecutorService executorService) {
        if (executorService == null) {
            this.f9710e = null;
            this.f9711f = null;
            return;
        }
        if (executorService instanceof ThreadPoolExecutor) {
            this.f9711f = (ThreadPoolExecutor) executorService;
        }
        this.f9710e = (ExecutorService) Proxy.newProxyInstance(executorService.getClass().getClassLoader(), new Class[]{ExecutorService.class}, new InvocationHandler() { // from class: com.bytedance.monitor.util.thread.inner.c.1
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) {
                Object invoke = method.invoke(executorService, objArr);
                if (c.this.b()) {
                    String str = "null";
                    if (objArr != null) {
                        for (int i2 = 0; i2 < objArr.length; i2++) {
                            str = str + objArr[i2];
                        }
                    }
                    c.this.a("call outer-executor " + method.getName() + ", args: " + str);
                    if ("submit".equals(method.getName())) {
                        c.this.a("call outer-executor " + method.getName() + ", args: " + str);
                    }
                }
                return invoke;
            }
        });
    }

    private String a(Runnable runnable) {
        if (runnable instanceof e) {
            return ((e) runnable).getTaskName();
        }
        return runnable == null ? "null" : runnable.toString();
    }

    @Override // com.bytedance.monitor.util.thread.inner.d
    public void post(e eVar) {
        if (b()) {
            a("post " + com.bytedance.monitor.util.thread.c.getTaskInfo(eVar));
        }
        submit(eVar);
        c();
        if (this.f9710e == null && b()) {
            a("current task count: " + getQueue().size());
        }
    }

    @Override // com.bytedance.monitor.util.thread.inner.d
    public void removeTask(e eVar) {
        ThreadPoolExecutor threadPoolExecutor = this.f9711f;
        if (threadPoolExecutor != null) {
            threadPoolExecutor.remove(eVar);
        }
        if (a(eVar) || !b()) {
            return;
        }
        a("z-debug-removeTask " + com.bytedance.monitor.util.thread.c.getTaskInfo(eVar) + ", remove failed");
    }

    @Override // com.bytedance.monitor.util.thread.inner.d
    public void release() {
        ExecutorService executorService = this.f9710e;
        if (executorService != null) {
            executorService.shutdown();
        }
        shutdown();
    }

    @Override // com.bytedance.monitor.util.thread.inner.d
    public void postDelayed(e eVar, long j2) {
        if (eVar == null) {
            return;
        }
        if (b()) {
            a("postDelayed " + com.bytedance.monitor.util.thread.c.getTaskInfo(eVar));
        }
        a(schedule(eVar, j2, TimeUnit.MILLISECONDS), eVar.hashCode(), false);
        c();
    }

    @Override // com.bytedance.monitor.util.thread.inner.d
    public void scheduleWithFixedDelay(e eVar, long j2, long j3) {
        if (eVar == null) {
            return;
        }
        if (b()) {
            a("scheduleWithFixedDelay " + com.bytedance.monitor.util.thread.c.getTaskInfo(eVar) + ", initialDelay: " + j2 + ", delayInMillis: " + j3 + "\n task count: " + getQueue().size());
        }
        a(eVar);
        a(scheduleWithFixedDelay(eVar, j2, j3, TimeUnit.MILLISECONDS), eVar.hashCode(), true);
        c();
    }

    @Override // com.bytedance.monitor.util.thread.inner.d
    public void setThreadLogListener(f fVar) {
        this.f9707b = fVar;
    }

    @Override // com.bytedance.monitor.util.thread.inner.d
    public boolean isPending(e eVar) {
        boolean z;
        ThreadPoolExecutor threadPoolExecutor = this.f9711f;
        if (threadPoolExecutor == null || !threadPoolExecutor.getQueue().contains(eVar)) {
            boolean contains = getQueue().contains(eVar);
            List<ScheduledFuture<?>> list = this.f9708c.get(Integer.valueOf(eVar.hashCode()));
            if (list != null && !list.isEmpty()) {
                for (ScheduledFuture<?> scheduledFuture : list) {
                    if (!scheduledFuture.isCancelled()) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
            return contains || z;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        com.bytedance.monitor.util.thread.c.logd(this.f9707b, "ApmInnerThreadPool", str);
    }

    public void setTaskOverTime(int i2) {
        this.f9712g = i2;
    }

    private void c() {
        a aVar = this.f9713h;
        if (aVar == null || !aVar.isOverTime()) {
            return;
        }
        this.f9713h.cancel();
        this.f9713h = null;
    }

    /* compiled from: ApmInnerThreadPool.java */
    /* loaded from: classes2.dex */
    public class a {
        public Runnable runnable;
        public long startTime = System.currentTimeMillis();
        public int taskOverTime;
        public Thread thread;

        public a(Runnable runnable, Thread thread, int i2) {
            this.runnable = runnable;
            this.thread = thread;
            this.taskOverTime = i2;
        }

        public boolean isOverTime() {
            return System.currentTimeMillis() - this.startTime > ((long) this.taskOverTime);
        }

        public void cancel() {
            Runnable runnable = this.runnable;
            if (runnable != null && (runnable instanceof FutureTask)) {
                boolean cancel = ((FutureTask) runnable).cancel(true);
                if (c.this.b()) {
                    c.this.a("TimeSensitive + mCancelOverTimeTaskRunnable run ------------ cancel FutureTask, canceled?" + cancel);
                }
            }
        }
    }

    private boolean a(e eVar) {
        boolean z = false;
        if (eVar == null) {
            return false;
        }
        int hashCode = eVar.hashCode();
        List<ScheduledFuture<?>> list = this.f9708c.get(Integer.valueOf(hashCode));
        a("z-debug scheduledFutures in ?" + (list != null));
        if (list != null && !list.isEmpty()) {
            if (b()) {
                a("z-debug-removeTask from mTaskFutureMap" + com.bytedance.monitor.util.thread.c.getTaskInfo(eVar));
                a("z-debug scheduledFutures size:" + list.size());
            }
            for (ScheduledFuture<?> scheduledFuture : list) {
                if (!scheduledFuture.isCancelled()) {
                    if (scheduledFuture.cancel(true)) {
                        z = true;
                    }
                    a(Integer.valueOf(hashCode), scheduledFuture);
                }
            }
        }
        return z;
    }

    private void a(ScheduledFuture<?> scheduledFuture, int i2, boolean z) {
        List<ScheduledFuture<?>> list = this.f9708c.get(Integer.valueOf(i2));
        if (list == null) {
            list = new CopyOnWriteArrayList<>();
            this.f9708c.put(Integer.valueOf(i2), list);
        }
        list.add(scheduledFuture);
        this.f9709d.put(scheduledFuture, new Pair<>(Integer.valueOf(i2), Boolean.valueOf(z)));
        if (b()) {
            a("z-debug saveToMap -current task count: " + getQueue().size() + ", thread-count; " + getActiveCount());
            a("z-debug saveToMap >>>>>> mTaskFutureMap size: " + this.f9708c.size() + ", mFutureTaskMap size: " + this.f9709d.size());
            a("z-debug saveToMap >>>>>> sameHashCodeFutures size: " + list.size());
        }
    }

    private void a(Integer num, ScheduledFuture scheduledFuture) {
        boolean z;
        List<ScheduledFuture<?>> list = this.f9708c.get(num);
        if (list != null) {
            z = list.remove(scheduledFuture);
            if (list.isEmpty()) {
                this.f9708c.remove(num);
            }
        } else {
            z = false;
        }
        this.f9709d.remove(scheduledFuture);
        if (b()) {
            a("z-debug removeMapRecord: " + scheduledFuture + ", removed: " + z);
        }
    }

    public void setTestDebug(boolean z) {
        this.f9706a = z;
    }
}
