package com.ttnet.org.chromium.base;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes9.dex */
public final class CallbackController {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private ArrayList<WeakReference<Cancelable>> mCancelables = new ArrayList<>();
    private final ReentrantLock mReentrantLock = new ReentrantLock(true);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public interface Cancelable {
        void cancel();
    }

    /* loaded from: classes9.dex */
    private class CancelableCallback<T> implements Callback<T>, Cancelable {
        private Callback<T> mCallback;

        private CancelableCallback(Callback<T> callback) {
            this.mCallback = callback;
        }

        @Override // com.ttnet.org.chromium.base.CallbackController.Cancelable
        public void cancel() {
            this.mCallback = null;
        }

        @Override // com.ttnet.org.chromium.base.Callback
        public void onResult(T t) {
            AutoCloseableLock lock = AutoCloseableLock.lock(CallbackController.this.mReentrantLock);
            try {
                Callback<T> callback = this.mCallback;
                if (callback != null) {
                    callback.onResult(t);
                }
                if (lock != null) {
                    lock.close();
                }
            } catch (Throwable th) {
                if (lock != null) {
                    try {
                        lock.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
    }

    /* loaded from: classes9.dex */
    private class CancelableRunnable implements Cancelable, Runnable {
        private Runnable mRunnable;

        private CancelableRunnable(Runnable runnable) {
            this.mRunnable = runnable;
        }

        @Override // com.ttnet.org.chromium.base.CallbackController.Cancelable
        public void cancel() {
            this.mRunnable = null;
        }

        @Override // java.lang.Runnable
        public void run() {
            AutoCloseableLock lock = AutoCloseableLock.lock(CallbackController.this.mReentrantLock);
            try {
                Runnable runnable = this.mRunnable;
                if (runnable != null) {
                    runnable.run();
                }
                if (lock != null) {
                    lock.close();
                }
            } catch (Throwable th) {
                if (lock != null) {
                    try {
                        lock.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
    }

    /* loaded from: classes9.dex */
    private static class AutoCloseableLock implements AutoCloseable {
        private boolean mIsLocked;
        private final Lock mLock;

        private AutoCloseableLock(Lock lock, boolean z) {
            this.mLock = lock;
            this.mIsLocked = z;
        }

        static AutoCloseableLock lock(Lock lock) {
            lock.lock();
            return new AutoCloseableLock(lock, true);
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            if (!this.mIsLocked) {
                throw new IllegalStateException("mLock isn't locked.");
            }
            this.mIsLocked = false;
            this.mLock.unlock();
        }
    }

    public <T> Callback<T> makeCancelable(Callback<T> callback) {
        AutoCloseableLock lock = AutoCloseableLock.lock(this.mReentrantLock);
        try {
            checkNotCanceled();
            CancelableCallback cancelableCallback = new CancelableCallback(callback);
            this.mCancelables.add(new WeakReference<>(cancelableCallback));
            if (lock != null) {
                lock.close();
            }
            return cancelableCallback;
        } catch (Throwable th) {
            if (lock != null) {
                try {
                    lock.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public Runnable makeCancelable(Runnable runnable) {
        AutoCloseableLock lock = AutoCloseableLock.lock(this.mReentrantLock);
        try {
            checkNotCanceled();
            CancelableRunnable cancelableRunnable = new CancelableRunnable(runnable);
            this.mCancelables.add(new WeakReference<>(cancelableRunnable));
            if (lock != null) {
                lock.close();
            }
            return cancelableRunnable;
        } catch (Throwable th) {
            if (lock != null) {
                try {
                    lock.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void destroy() {
        AutoCloseableLock lock = AutoCloseableLock.lock(this.mReentrantLock);
        try {
            checkNotCanceled();
            for (Cancelable cancelable : CollectionUtil.strengthen(this.mCancelables)) {
                cancelable.cancel();
            }
            this.mCancelables = null;
            if (lock != null) {
                lock.close();
            }
        } catch (Throwable th) {
            if (lock != null) {
                try {
                    lock.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void checkNotCanceled() {
        if (this.mCancelables == null) {
            throw new IllegalStateException("This CallbackController has already been destroyed.");
        }
    }
}
