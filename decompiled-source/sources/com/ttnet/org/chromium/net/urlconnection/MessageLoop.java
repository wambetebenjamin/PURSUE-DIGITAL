package com.ttnet.org.chromium.net.urlconnection;

import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes3.dex */
public class MessageLoop implements Executor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long INVALID_THREAD_ID = -1;
    private static final AtomicBoolean mDisableValidThreadAssert = new AtomicBoolean(false);
    private boolean mLoopFailed;
    private boolean mLoopRunning;
    private InterruptedIOException mPriorInterruptedIOException;
    private RuntimeException mPriorRuntimeException;
    private long mThreadId = -1;
    private final BlockingQueue<Runnable> mQueue = new LinkedBlockingQueue();

    private boolean calledOnValidThread() {
        if (mDisableValidThreadAssert.get()) {
            return true;
        }
        long j2 = this.mThreadId;
        if (j2 != -1) {
            return j2 == Thread.currentThread().getId();
        }
        this.mThreadId = Thread.currentThread().getId();
        return true;
    }

    public boolean onValidThread() {
        long j2 = this.mThreadId;
        return j2 == -1 || j2 == Thread.currentThread().getId();
    }

    private Runnable take(boolean z, long j2) {
        Runnable poll;
        try {
            if (!z) {
                poll = this.mQueue.take();
            } else {
                poll = this.mQueue.poll(j2, TimeUnit.NANOSECONDS);
            }
            if (poll != null) {
                return poll;
            }
            throw new SocketTimeoutException("ttnet");
        } catch (InterruptedException e2) {
            InterruptedIOException interruptedIOException = new InterruptedIOException();
            interruptedIOException.initCause(e2);
            throw interruptedIOException;
        }
    }

    public void loop() {
        loop(0);
    }

    public void loop(int i2) {
        long nanoTime = System.nanoTime();
        long convert = TimeUnit.NANOSECONDS.convert(i2, TimeUnit.MILLISECONDS);
        if (this.mLoopFailed) {
            InterruptedIOException interruptedIOException = this.mPriorInterruptedIOException;
            if (interruptedIOException != null) {
                throw interruptedIOException;
            }
            throw this.mPriorRuntimeException;
        } else if (this.mLoopRunning) {
            throw new IllegalStateException("Cannot run loop when it is already running.");
        } else {
            this.mLoopRunning = true;
            while (this.mLoopRunning) {
                if (i2 == 0) {
                    try {
                        take(false, 0L).run();
                    } catch (SocketTimeoutException e2) {
                        this.mLoopRunning = false;
                        this.mLoopFailed = true;
                        throw e2;
                    } catch (InterruptedIOException e3) {
                        this.mLoopRunning = false;
                        this.mLoopFailed = true;
                        this.mPriorInterruptedIOException = e3;
                        throw e3;
                    } catch (RuntimeException e4) {
                        this.mLoopRunning = false;
                        this.mLoopFailed = true;
                        this.mPriorRuntimeException = e4;
                        throw e4;
                    }
                } else {
                    take(true, (convert - System.nanoTime()) + nanoTime).run();
                }
            }
        }
    }

    public void reset() {
        this.mLoopFailed = false;
    }

    public static void disableVaildThreadAssert(boolean z) {
        mDisableValidThreadAssert.set(z);
    }

    public void quit() {
        this.mLoopRunning = false;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException();
        }
        try {
            this.mQueue.put(runnable);
        } catch (InterruptedException e2) {
            throw new RejectedExecutionException(e2);
        }
    }

    public boolean isRunning() {
        return this.mLoopRunning;
    }

    public boolean hasLoopFailed() {
        return this.mLoopFailed;
    }
}
