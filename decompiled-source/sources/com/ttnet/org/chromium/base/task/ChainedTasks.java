package com.ttnet.org.chromium.base.task;

import android.util.Pair;
import com.ttnet.org.chromium.base.TraceEvent;
import java.util.Iterator;
import java.util.LinkedList;
/* loaded from: classes9.dex */
public class ChainedTasks {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private volatile boolean mCanceled;
    private boolean mFinalized;
    private final LinkedList<Pair<TaskTraits, Runnable>> mTasks = new LinkedList<>();
    private final Runnable mRunAndPost = new Runnable() { // from class: com.ttnet.org.chromium.base.task.ChainedTasks.1
        @Override // java.lang.Runnable
        public void run() {
            if (ChainedTasks.this.mCanceled) {
                return;
            }
            Pair pair = (Pair) ChainedTasks.this.mTasks.pop();
            TraceEvent scoped = TraceEvent.scoped("ChainedTask.run: " + ((Runnable) pair.second).getClass().getName());
            try {
                ((Runnable) pair.second).run();
                if (scoped != null) {
                    scoped.close();
                }
                if (ChainedTasks.this.mTasks.isEmpty()) {
                    return;
                }
                PostTask.postTask((TaskTraits) ((Pair) ChainedTasks.this.mTasks.peek()).first, this);
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
    };

    public void add(TaskTraits taskTraits, Runnable runnable) {
        synchronized (this.mTasks) {
            this.mTasks.add(new Pair<>(taskTraits, runnable));
        }
    }

    public void cancel() {
        synchronized (this.mTasks) {
            this.mFinalized = true;
            this.mCanceled = true;
        }
    }

    public void start(boolean z) {
        synchronized (this.mTasks) {
            this.mFinalized = true;
        }
        if (this.mTasks.isEmpty()) {
            return;
        }
        if (z) {
            PostTask.runOrPostTask((TaskTraits) this.mTasks.peek().first, new Runnable() { // from class: com.ttnet.org.chromium.base.task.ChainedTasks$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ChainedTasks.this.m716lambda$start$0$comttnetorgchromiumbasetaskChainedTasks();
                }
            });
        } else {
            PostTask.postTask((TaskTraits) this.mTasks.peek().first, this.mRunAndPost);
        }
    }

    /* renamed from: lambda$start$0$com-ttnet-org-chromium-base-task-ChainedTasks  reason: not valid java name */
    public /* synthetic */ void m716lambda$start$0$comttnetorgchromiumbasetaskChainedTasks() {
        Iterator<Pair<TaskTraits, Runnable>> it = this.mTasks.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next().second).run();
            if (this.mCanceled) {
                return;
            }
        }
    }
}
