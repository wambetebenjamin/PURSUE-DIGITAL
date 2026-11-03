package com.bytedance.compression.zstd;

import java.io.Closeable;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
/* compiled from: AutoCloseBase.java */
/* loaded from: classes2.dex */
abstract class a implements Closeable {
    private static final int SHARED_LOCK_CLOSED = -1;
    private static final AtomicIntegerFieldUpdater<a> SHARED_LOCK_UPDATER = AtomicIntegerFieldUpdater.newUpdater(a.class, "sharedLock");
    private volatile int sharedLock;

    abstract void doClose();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void storeFence() {
        this.sharedLock = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void acquireSharedLock() {
        int i2;
        do {
            i2 = this.sharedLock;
            if (i2 < 0) {
                throw new IllegalStateException("Closed");
            }
            if (i2 == Integer.MAX_VALUE) {
                throw new IllegalStateException("Shared lock overflow");
            }
        } while (!SHARED_LOCK_UPDATER.compareAndSet(this, i2, i2 + 1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releaseSharedLock() {
        int i2;
        do {
            i2 = this.sharedLock;
            if (i2 < 0) {
                throw new IllegalStateException("Closed");
            }
            if (i2 == 0) {
                throw new IllegalStateException("Shared lock underflow");
            }
        } while (!SHARED_LOCK_UPDATER.compareAndSet(this, i2, i2 - 1));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            if (this.sharedLock == -1) {
                return;
            }
            if (!SHARED_LOCK_UPDATER.compareAndSet(this, 0, -1)) {
                throw new IllegalStateException("Attempt to close while in use");
            }
            doClose();
        }
    }
}
