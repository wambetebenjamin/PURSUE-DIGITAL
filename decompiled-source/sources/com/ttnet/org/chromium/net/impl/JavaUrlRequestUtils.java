package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.net.InlineExecutionProhibitedException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
/* loaded from: classes9.dex */
public final class JavaUrlRequestUtils {

    /* loaded from: classes9.dex */
    public interface CheckedRunnable {
        void run();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface State {
        public static final int AWAITING_FOLLOW_REDIRECT = 3;
        public static final int AWAITING_READ = 4;
        public static final int CANCELLED = 8;
        public static final int COMPLETE = 7;
        public static final int ERROR = 6;
        public static final int NOT_STARTED = 0;
        public static final int READING = 5;
        public static final int REDIRECT_RECEIVED = 2;
        public static final int STARTED = 1;
    }

    /* loaded from: classes9.dex */
    public static final class DirectPreventingExecutor implements Executor {
        private final Executor mDelegate;

        public DirectPreventingExecutor(Executor executor) {
            this.mDelegate = executor;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            InlineCheckingRunnable inlineCheckingRunnable = new InlineCheckingRunnable(runnable, Thread.currentThread());
            this.mDelegate.execute(inlineCheckingRunnable);
            if (inlineCheckingRunnable.mExecutedInline == null) {
                inlineCheckingRunnable.mCallingThread = null;
                return;
            }
            throw inlineCheckingRunnable.mExecutedInline;
        }

        /* loaded from: classes9.dex */
        private static final class InlineCheckingRunnable implements Runnable {
            private Thread mCallingThread;
            private final Runnable mCommand;
            private InlineExecutionProhibitedException mExecutedInline;

            private InlineCheckingRunnable(Runnable runnable, Thread thread) {
                this.mCommand = runnable;
                this.mCallingThread = thread;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (Thread.currentThread() == this.mCallingThread) {
                    this.mExecutedInline = new InlineExecutionProhibitedException();
                } else {
                    this.mCommand.run();
                }
            }
        }
    }
}
