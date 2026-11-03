package com.ttnet.org.chromium.base.task;

import android.os.Process;
import com.ttnet.org.chromium.base.task.AsyncTask;
import com.ttnet.org.chromium.base.task.ChromeThreadPoolExecutor;
import com.ttnet.org.chromium.build.BuildConfig;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes9.dex */
class ChromeThreadPoolExecutor extends ThreadPoolExecutor {
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT;
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final int MAXIMUM_POOL_SIZE;
    private static final int RUNNABLE_WARNING_COUNT = 32;
    private static final BlockingQueue<Runnable> sPoolWorkQueue;
    private static final ThreadFactory sThreadFactory;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = availableProcessors;
        CORE_POOL_SIZE = Math.max(2, Math.min(availableProcessors - 1, 4));
        MAXIMUM_POOL_SIZE = (availableProcessors * 2) + 1;
        sThreadFactory = new AnonymousClass1();
        sPoolWorkQueue = new ArrayBlockingQueue(128);
    }

    /* renamed from: com.ttnet.org.chromium.base.task.ChromeThreadPoolExecutor$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    class AnonymousClass1 implements ThreadFactory {
        private final AtomicInteger mCount = new AtomicInteger(1);

        AnonymousClass1() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(final Runnable runnable) {
            return new Thread(new Runnable() { // from class: com.ttnet.org.chromium.base.task.ChromeThreadPoolExecutor$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ChromeThreadPoolExecutor.AnonymousClass1.lambda$newThread$0(runnable);
                }
            }, "CrAsyncTask #" + this.mCount.getAndIncrement());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$newThread$0(Runnable runnable) {
            Process.setThreadPriority(10);
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChromeThreadPoolExecutor() {
        this(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 30L, TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
    }

    ChromeThreadPoolExecutor(int i2, int i3, long j2, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i2, i3, j2, timeUnit, blockingQueue, threadFactory);
        allowCoreThreadTimeOut(true);
    }

    private static String getClassName(Runnable runnable) {
        Class cls;
        Class cls2 = runnable.getClass();
        try {
        } catch (IllegalAccessException e2) {
            if (BuildConfig.ENABLE_ASSERTS) {
                throw new RuntimeException(e2);
            }
        } catch (NoSuchFieldException e3) {
            if (BuildConfig.ENABLE_ASSERTS) {
                throw new RuntimeException(e3);
            }
        }
        if (cls2 == AsyncTask.NamedFutureTask.class) {
            cls = ((AsyncTask.NamedFutureTask) runnable).getBlamedClass();
        } else {
            if (cls2.getEnclosingClass() == android.os.AsyncTask.class) {
                Field declaredField = cls2.getDeclaredField("this$0");
                declaredField.setAccessible(true);
                cls = declaredField.get(runnable).getClass();
            }
            return cls2.getName();
        }
        cls2 = cls;
        return cls2.getName();
    }

    private Map<String, Integer> getNumberOfClassNameOccurrencesInQueue() {
        HashMap hashMap = new HashMap();
        for (Runnable runnable : (Runnable[]) getQueue().toArray(new Runnable[0])) {
            String className = getClassName(runnable);
            hashMap.put(className, Integer.valueOf((hashMap.containsKey(className) ? ((Integer) hashMap.get(className)).intValue() : 0) + 1));
        }
        return hashMap;
    }

    private String findClassNamesWithTooManyRunnables(Map<String, Integer> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue().intValue() > 32) {
                sb.append(entry.getKey()).append(' ');
            }
        }
        return sb.length() == 0 ? "NO CLASSES FOUND" : sb.toString();
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        try {
            super.execute(runnable);
        } catch (RejectedExecutionException e2) {
            throw new RejectedExecutionException("Prominent classes in AsyncTask: " + findClassNamesWithTooManyRunnables(getNumberOfClassNameOccurrencesInQueue()), e2);
        }
    }
}
