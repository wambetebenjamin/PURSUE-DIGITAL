package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.annotations.JNINamespace;
@JNINamespace("cronet")
/* loaded from: classes9.dex */
public abstract class TTThreadConfigInfoProvider {
    public static final int INVALID_THREAD_PRIORITY = 20;

    /* loaded from: classes9.dex */
    public enum BindCore {
        DEFAULT,
        BIG_CORE,
        LITTLE_CORE,
        CANCEL_BIND
    }

    /* loaded from: classes9.dex */
    public interface Callback {
        void bindBigCore(int i2);

        void bindLittleCore(int i2);

        void resetCoreBind(int i2);
    }

    /* loaded from: classes9.dex */
    public enum ThreadType {
        INIT_THREAD,
        NETWORK_THREAD,
        FILE_THREAD,
        THREAD_POOL_SERVICE_THREAD,
        WORKER_THREAD
    }

    /* loaded from: classes9.dex */
    public static class ThreadConfigInfo {
        private BindCore mBindCore;
        private int mThreadPriority = 20;
        private int mThreadStackSize;
        private ThreadType mThreadType;

        public void setThreadType(ThreadType threadType) {
            this.mThreadType = threadType;
        }

        public int getThreadType() {
            return this.mThreadType.ordinal();
        }

        public void setThreadPriority(int i2) {
            this.mThreadPriority = i2;
        }

        public int getThreadPriority() {
            return this.mThreadPriority;
        }

        public void setThreadStackSize(int i2) {
            this.mThreadStackSize = i2;
        }

        public int getThreadStackSize() {
            return this.mThreadStackSize;
        }
    }
}
