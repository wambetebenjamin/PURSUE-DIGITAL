package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
public class ThreadHandoffProducerQueue {
    private final Executor mExecutor;
    private boolean mQueueing = false;
    private final Deque<Runnable> mRunnableList = new ArrayDeque<Runnable>() { // from class: com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue.1
        @Override // java.util.ArrayDeque, java.util.AbstractCollection, java.util.Collection, java.util.Deque, java.util.Queue
        public boolean add(Runnable runnable) {
            if (runnable instanceof StatefulHandoffProducerRunnable) {
                ((StatefulHandoffProducerRunnable) runnable).enqueue();
            }
            return super.add((AnonymousClass1) runnable);
        }

        @Override // java.util.ArrayDeque, java.util.Deque
        public Runnable pop() {
            Runnable runnable = (Runnable) super.pop();
            if (runnable instanceof StatefulHandoffProducerRunnable) {
                ((StatefulHandoffProducerRunnable) runnable).dequeue();
            }
            return runnable;
        }
    };

    public ThreadHandoffProducerQueue(Executor executor) {
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
    }

    public synchronized void addToQueueOrExecute(Runnable runnable) {
        if (this.mQueueing) {
            this.mRunnableList.add(runnable);
        } else {
            this.mExecutor.execute(runnable);
        }
    }

    public synchronized void startQueueing() {
        this.mQueueing = true;
    }

    public synchronized void stopQueuing() {
        this.mQueueing = false;
        execInQueue();
    }

    private void execInQueue() {
        while (!this.mRunnableList.isEmpty()) {
            this.mExecutor.execute(this.mRunnableList.pop());
        }
        this.mRunnableList.clear();
    }

    public synchronized void remove(Runnable runnable) {
        this.mRunnableList.remove(runnable);
    }

    public synchronized boolean isQueueing() {
        return this.mQueueing;
    }
}
