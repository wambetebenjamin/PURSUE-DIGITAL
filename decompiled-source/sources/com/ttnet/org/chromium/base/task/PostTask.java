package com.ttnet.org.chromium.base.task;

import com.bytedance.apm.constant.r;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReferenceArray;
@JNINamespace(r.STATS_BASE_MODULE)
/* loaded from: classes9.dex */
public class PostTask {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static volatile boolean sNativeInitialized;
    private static volatile Executor sPrenativeThreadPoolExecutorOverride;
    private static final Object sPreNativeTaskRunnerLock = new Object();
    private static List<TaskRunnerImpl> sPreNativeTaskRunners = new ArrayList();
    private static final Executor sPrenativeThreadPoolExecutor = new ChromeThreadPoolExecutor();
    private static AtomicReferenceArray<TaskExecutor> sTaskExecutors = getInitialTaskExecutors();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        void postDelayedTask(int i2, boolean z, boolean z2, byte b2, byte[] bArr, Runnable runnable, long j2, String str);
    }

    private static AtomicReferenceArray<TaskExecutor> getInitialTaskExecutors() {
        AtomicReferenceArray<TaskExecutor> atomicReferenceArray = new AtomicReferenceArray<>(5);
        atomicReferenceArray.set(0, new DefaultTaskExecutor());
        return atomicReferenceArray;
    }

    public static TaskRunner createTaskRunner(TaskTraits taskTraits) {
        return getTaskExecutorForTraits(taskTraits).createTaskRunner(taskTraits);
    }

    public static SequencedTaskRunner createSequencedTaskRunner(TaskTraits taskTraits) {
        return getTaskExecutorForTraits(taskTraits).createSequencedTaskRunner(taskTraits);
    }

    public static SingleThreadTaskRunner createSingleThreadTaskRunner(TaskTraits taskTraits) {
        return getTaskExecutorForTraits(taskTraits).createSingleThreadTaskRunner(taskTraits);
    }

    public static void postTask(TaskTraits taskTraits, Runnable runnable) {
        postDelayedTask(taskTraits, runnable, 0L);
    }

    public static void postDelayedTask(TaskTraits taskTraits, Runnable runnable, long j2) {
        if (!sNativeInitialized || taskTraits.mIsChoreographerFrame) {
            getTaskExecutorForTraits(taskTraits).postDelayedTask(taskTraits, runnable, j2);
            return;
        }
        TaskTraits withExplicitDestination = taskTraits.withExplicitDestination();
        PostTaskJni.get().postDelayedTask(withExplicitDestination.mPriority, withExplicitDestination.mMayBlock, withExplicitDestination.mUseThreadPool, withExplicitDestination.mExtensionId, withExplicitDestination.mExtensionData, runnable, j2, runnable.getClass().getName());
    }

    public static void runOrPostTask(TaskTraits taskTraits, Runnable runnable) {
        if (getTaskExecutorForTraits(taskTraits).canRunTaskImmediately(taskTraits)) {
            runnable.run();
        } else {
            postTask(taskTraits, runnable);
        }
    }

    public static boolean canRunTaskImmediately(TaskTraits taskTraits) {
        return getTaskExecutorForTraits(taskTraits).canRunTaskImmediately(taskTraits);
    }

    @Deprecated
    public static <T> T runSynchronously(TaskTraits taskTraits, Callable<T> callable) {
        return (T) runSynchronouslyInternal(taskTraits, new FutureTask(callable));
    }

    @Deprecated
    public static void runSynchronously(TaskTraits taskTraits, Runnable runnable) {
        runSynchronouslyInternal(taskTraits, new FutureTask(runnable, null));
    }

    private static <T> T runSynchronouslyInternal(TaskTraits taskTraits, FutureTask<T> futureTask) {
        runOrPostTask(taskTraits, futureTask);
        try {
            return futureTask.get();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public static void registerTaskExecutor(int i2, TaskExecutor taskExecutor) {
        sTaskExecutors.set(i2, taskExecutor);
    }

    public static void setPrenativeThreadPoolExecutorForTesting(Executor executor) {
        sPrenativeThreadPoolExecutorOverride = executor;
    }

    public static void resetPrenativeThreadPoolExecutorForTesting() {
        sPrenativeThreadPoolExecutorOverride = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor getPrenativeThreadPoolExecutor() {
        if (sPrenativeThreadPoolExecutorOverride != null) {
            return sPrenativeThreadPoolExecutorOverride;
        }
        return sPrenativeThreadPoolExecutor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean registerPreNativeTaskRunner(TaskRunnerImpl taskRunnerImpl) {
        synchronized (sPreNativeTaskRunnerLock) {
            List<TaskRunnerImpl> list = sPreNativeTaskRunners;
            if (list == null) {
                return false;
            }
            list.add(taskRunnerImpl);
            return true;
        }
    }

    private static TaskExecutor getTaskExecutorForTraits(TaskTraits taskTraits) {
        return sTaskExecutors.get(taskTraits.mExtensionId);
    }

    private static void onNativeSchedulerReady() {
        List<TaskRunnerImpl> list;
        sNativeInitialized = true;
        synchronized (sPreNativeTaskRunnerLock) {
            list = sPreNativeTaskRunners;
            sPreNativeTaskRunners = null;
        }
        for (TaskRunnerImpl taskRunnerImpl : list) {
            taskRunnerImpl.initNativeTaskRunner();
        }
    }

    private static void onNativeSchedulerShutdownForTesting() {
        synchronized (sPreNativeTaskRunnerLock) {
            sPreNativeTaskRunners = new ArrayList();
        }
        sNativeInitialized = false;
        sTaskExecutors.set(0, new DefaultTaskExecutor());
        for (int i2 = 1; i2 < sTaskExecutors.length(); i2++) {
            sTaskExecutors.set(i2, null);
        }
    }
}
