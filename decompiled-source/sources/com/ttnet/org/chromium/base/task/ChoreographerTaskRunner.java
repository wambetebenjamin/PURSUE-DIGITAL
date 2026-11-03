package com.ttnet.org.chromium.base.task;

import android.view.Choreographer;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class ChoreographerTaskRunner implements SingleThreadTaskRunner {
    private final Choreographer mChoreographer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChoreographerTaskRunner(Choreographer choreographer) {
        this.mChoreographer = choreographer;
    }

    @Override // com.ttnet.org.chromium.base.task.SingleThreadTaskRunner
    public boolean belongsToCurrentThread() {
        try {
            return this.mChoreographer == Choreographer.getInstance();
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    @Override // com.ttnet.org.chromium.base.task.TaskRunner
    public void postTask(final Runnable runnable) {
        this.mChoreographer.postFrameCallback(new Choreographer.FrameCallback() { // from class: com.ttnet.org.chromium.base.task.ChoreographerTaskRunner.1
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j2) {
                runnable.run();
            }
        });
    }

    @Override // com.ttnet.org.chromium.base.task.TaskRunner
    public void postDelayedTask(final Runnable runnable, long j2) {
        this.mChoreographer.postFrameCallbackDelayed(new Choreographer.FrameCallback() { // from class: com.ttnet.org.chromium.base.task.ChoreographerTaskRunner.2
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j3) {
                runnable.run();
            }
        }, j2);
    }
}
