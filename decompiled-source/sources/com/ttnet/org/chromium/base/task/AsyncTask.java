package com.ttnet.org.chromium.base.task;

import android.os.Binder;
import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.base.ThreadUtils;
import com.ttnet.org.chromium.base.TraceEvent;
import com.ttnet.org.chromium.base.metrics.RecordHistogram;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes9.dex */
public abstract class AsyncTask<Result> {
    private static final String GET_STATUS_UMA_HISTOGRAM = "Android.Jank.AsyncTaskGetOnUiThreadStatus";
    private static final String TAG = "AsyncTask";
    private final AsyncTask<Result>.NamedFutureTask mFuture;
    private final Callable<Result> mWorker;
    public static final Executor THREAD_POOL_EXECUTOR = new Executor() { // from class: com.ttnet.org.chromium.base.task.AsyncTask$$ExternalSyntheticLambda1
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            PostTask.postTask(TaskTraits.BEST_EFFORT_MAY_BLOCK, runnable);
        }
    };
    public static final Executor SERIAL_EXECUTOR = new SerialExecutor();
    private static final StealRunnableHandler STEAL_RUNNABLE_HANDLER = new StealRunnableHandler();
    private volatile int mStatus = 0;
    private final AtomicBoolean mCancelled = new AtomicBoolean();
    private final AtomicBoolean mTaskInvoked = new AtomicBoolean();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface Status {
        public static final int FINISHED = 2;
        public static final int NUM_ENTRIES = 3;
        public static final int PENDING = 0;
        public static final int RUNNING = 1;
    }

    protected abstract Result doInBackground();

    protected void onCancelled() {
    }

    protected abstract void onPostExecute(Result result);

    protected void onPreExecute() {
    }

    /* loaded from: classes9.dex */
    private static class StealRunnableHandler implements RejectedExecutionHandler {
        private StealRunnableHandler() {
        }

        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            AsyncTask.THREAD_POOL_EXECUTOR.execute(runnable);
        }
    }

    public static void takeOverAndroidThreadPool() {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) android.os.AsyncTask.THREAD_POOL_EXECUTOR;
        threadPoolExecutor.setRejectedExecutionHandler(STEAL_RUNNABLE_HANDLER);
        threadPoolExecutor.shutdown();
    }

    public AsyncTask() {
        Callable<Result> callable = new Callable<Result>() { // from class: com.ttnet.org.chromium.base.task.AsyncTask.1
            @Override // java.util.concurrent.Callable
            public Result call() {
                AsyncTask.this.mTaskInvoked.set(true);
                Result result = null;
                try {
                    result = (Result) AsyncTask.this.doInBackground();
                    Binder.flushPendingCommands();
                    return result;
                } finally {
                }
            }
        };
        this.mWorker = callable;
        this.mFuture = new NamedFutureTask(callable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postResultIfNotInvoked(Result result) {
        if (this.mTaskInvoked.get()) {
            return;
        }
        postResult(result);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postResult(final Result result) {
        if (this instanceof BackgroundOnlyAsyncTask) {
            this.mStatus = 2;
        } else {
            ThreadUtils.postOnUiThread(new Runnable() { // from class: com.ttnet.org.chromium.base.task.AsyncTask$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AsyncTask.this.m715lambda$postResult$1$comttnetorgchromiumbasetaskAsyncTask(result);
                }
            });
        }
    }

    public final int getStatus() {
        return this.mStatus;
    }

    public final int getUmaStatus() {
        if (this.mStatus != 1 || this.mTaskInvoked.get()) {
            return this.mStatus;
        }
        return 0;
    }

    protected void onCancelled(Result result) {
        onCancelled();
    }

    public final boolean isCancelled() {
        return this.mCancelled.get();
    }

    public final boolean cancel(boolean z) {
        this.mCancelled.set(true);
        return this.mFuture.cancel(z);
    }

    public final Result get() {
        int umaStatus = getUmaStatus();
        if (umaStatus != 2 && ThreadUtils.runningOnUiThread()) {
            RecordHistogram.recordEnumeratedHistogram(GET_STATUS_UMA_HISTOGRAM, umaStatus, 3);
            StackTraceElement[] stackTrace = new Exception().getStackTrace();
            TraceEvent scoped = TraceEvent.scoped((stackTrace.length > 1 ? stackTrace[1].getClassName() + '.' + stackTrace[1].getMethodName() + '.' : "") + "AsyncTask.get");
            try {
                Result result = this.mFuture.get();
                if (scoped != null) {
                    scoped.close();
                    return result;
                }
                return result;
            } catch (Throwable th) {
                if (scoped != null) {
                    try {
                        scoped.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        return this.mFuture.get();
    }

    public final Result get(long j2, TimeUnit timeUnit) {
        int umaStatus = getUmaStatus();
        if (umaStatus != 2 && ThreadUtils.runningOnUiThread()) {
            RecordHistogram.recordEnumeratedHistogram(GET_STATUS_UMA_HISTOGRAM, umaStatus, 3);
            StackTraceElement[] stackTrace = new Exception().getStackTrace();
            TraceEvent scoped = TraceEvent.scoped((stackTrace.length > 1 ? stackTrace[1].getClassName() + '.' + stackTrace[1].getMethodName() + '.' : "") + "AsyncTask.get");
            try {
                Result result = this.mFuture.get(j2, timeUnit);
                if (scoped != null) {
                    scoped.close();
                    return result;
                }
                return result;
            } catch (Throwable th) {
                if (scoped != null) {
                    try {
                        scoped.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        return this.mFuture.get(j2, timeUnit);
    }

    private void executionPreamble() {
        if (this.mStatus != 0) {
            int i2 = this.mStatus;
            if (i2 == 1) {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            }
            if (i2 == 2) {
                throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.mStatus = 1;
        onPreExecute();
    }

    public final AsyncTask<Result> executeOnExecutor(Executor executor) {
        executionPreamble();
        executor.execute(this.mFuture);
        return this;
    }

    public final AsyncTask<Result> executeOnTaskRunner(TaskRunner taskRunner) {
        executionPreamble();
        taskRunner.postTask(this.mFuture);
        return this;
    }

    public final AsyncTask<Result> executeWithTaskTraits(TaskTraits taskTraits) {
        executionPreamble();
        PostTask.postTask(taskTraits, this.mFuture);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: finish */
    public void m715lambda$postResult$1$comttnetorgchromiumbasetaskAsyncTask(Result result) {
        if (isCancelled()) {
            onCancelled(result);
        } else {
            onPostExecute(result);
        }
        this.mStatus = 2;
    }

    /* loaded from: classes9.dex */
    class NamedFutureTask extends FutureTask<Result> {
        NamedFutureTask(Callable<Result> callable) {
            super(callable);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Class getBlamedClass() {
            return AsyncTask.this.getClass();
        }

        @Override // java.util.concurrent.FutureTask, java.util.concurrent.RunnableFuture, java.lang.Runnable
        public void run() {
            TraceEvent scoped = TraceEvent.scoped("AsyncTask.run: " + AsyncTask.this.mFuture.getBlamedClass().getName());
            try {
                super.run();
                if (scoped != null) {
                    scoped.close();
                }
            } catch (Throwable th) {
                if (scoped != null) {
                    try {
                        scoped.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        @Override // java.util.concurrent.FutureTask
        protected void done() {
            try {
                AsyncTask.this.postResultIfNotInvoked(get());
            } catch (InterruptedException e2) {
                Log.w(AsyncTask.TAG, e2.toString(), new Object[0]);
            } catch (CancellationException unused) {
                AsyncTask.this.postResultIfNotInvoked(null);
            } catch (ExecutionException e3) {
                throw new RuntimeException("An error occurred while executing doInBackground()", e3.getCause());
            }
        }
    }
}
