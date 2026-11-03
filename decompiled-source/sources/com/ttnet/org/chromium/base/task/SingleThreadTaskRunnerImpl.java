package com.ttnet.org.chromium.base.task;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import com.bytedance.apm.constant.r;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.metrics.RecordHistogram;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@JNINamespace(r.STATS_BASE_MODULE)
/* loaded from: classes9.dex */
public class SingleThreadTaskRunnerImpl extends TaskRunnerImpl implements SingleThreadTaskRunner {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Handler mHandler;
    private final boolean mPostPreNativeTasksAtFrontOfQueue;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    private @interface PreNativeTaskPostType {
        public static final int DEFERRED_TO_NATIVE_INIT = 2;
        public static final int NUM_ENTRIES = 3;
        public static final int POSTED_AT_BACK_OF_QUEUE = 0;
        public static final int POSTED_AT_FRONT_OF_QUEUE = 1;
    }

    public SingleThreadTaskRunnerImpl(Handler handler, TaskTraits taskTraits, boolean z) {
        super(taskTraits, "SingleThreadTaskRunnerImpl", 2);
        this.mHandler = handler;
        this.mPostPreNativeTasksAtFrontOfQueue = z;
    }

    public SingleThreadTaskRunnerImpl(Handler handler, TaskTraits taskTraits) {
        this(handler, taskTraits, false);
    }

    @Override // com.ttnet.org.chromium.base.task.SingleThreadTaskRunner
    public boolean belongsToCurrentThread() {
        Boolean belongsToCurrentThreadInternal = belongsToCurrentThreadInternal();
        if (belongsToCurrentThreadInternal != null) {
            return belongsToCurrentThreadInternal.booleanValue();
        }
        return this.mHandler.getLooper().getThread() == Thread.currentThread();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ttnet.org.chromium.base.task.TaskRunnerImpl
    public void schedulePreNativeTask() {
        if (this.mHandler == null) {
            RecordHistogram.recordEnumeratedHistogram("Android.TaskScheduling.PreNativeTaskPostType", 2, 3);
        } else if (this.mPostPreNativeTasksAtFrontOfQueue) {
            RecordHistogram.recordEnumeratedHistogram("Android.TaskScheduling.PreNativeTaskPostType", 1, 3);
            postAtFrontOfQueue();
        } else {
            RecordHistogram.recordEnumeratedHistogram("Android.TaskScheduling.PreNativeTaskPostType", 0, 3);
            this.mHandler.post(this.mRunPreNativeTaskClosure);
        }
    }

    private void postAtFrontOfQueue() {
        if (Build.VERSION.SDK_INT >= 21) {
            Message obtain = Message.obtain(this.mHandler, this.mRunPreNativeTaskClosure);
            obtain.setAsynchronous(true);
            this.mHandler.sendMessageAtFrontOfQueue(obtain);
            return;
        }
        this.mHandler.postAtFrontOfQueue(this.mRunPreNativeTaskClosure);
    }
}
