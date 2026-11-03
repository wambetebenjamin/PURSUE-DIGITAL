package com.vk.api.sdk.utils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: VKValidationLocker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/vk/api/sdk/utils/VKValidationLocker;", "", "()V", "lockCondition", "Ljava/util/concurrent/locks/Condition;", "locker", "Ljava/util/concurrent/locks/ReentrantLock;", "await", "", "signal", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class VKValidationLocker {
    public static final VKValidationLocker INSTANCE = new VKValidationLocker();
    private static final Condition lockCondition;
    private static final ReentrantLock locker;

    static {
        ReentrantLock reentrantLock = new ReentrantLock();
        locker = reentrantLock;
        Condition newCondition = reentrantLock.newCondition();
        Intrinsics.checkExpressionValueIsNotNull(newCondition, "locker.newCondition()");
        lockCondition = newCondition;
    }

    private VKValidationLocker() {
    }

    public final void await() {
        try {
            ReentrantLock reentrantLock = locker;
            reentrantLock.lock();
            lockCondition.await();
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
        } catch (InterruptedException unused) {
        }
    }

    public final void signal() {
        ReentrantLock reentrantLock = locker;
        reentrantLock.lock();
        try {
            lockCondition.signalAll();
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }
}
