package com.ttnet.org.chromium.base.task;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;
/* loaded from: classes9.dex */
class SerialExecutor implements Executor {
    Runnable mActive;
    final ArrayDeque<Runnable> mTasks = new ArrayDeque<>();

    @Override // java.util.concurrent.Executor
    public synchronized void execute(final Runnable runnable) {
        this.mTasks.offer(new Runnable() { // from class: com.ttnet.org.chromium.base.task.SerialExecutor.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    runnable.run();
                } finally {
                    SerialExecutor.this.scheduleNext();
                }
            }
        });
        if (this.mActive == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        Runnable poll = this.mTasks.poll();
        this.mActive = poll;
        if (poll != null) {
            AsyncTask.THREAD_POOL_EXECUTOR.execute(this.mActive);
        }
    }
}
