package com.bytedance.keva;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
/* loaded from: classes2.dex */
class KevaFutureTask<V> extends FutureTask<V> {
    public KevaFutureTask(Callable<V> callable) {
        super(callable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.util.concurrent.FutureTask
    public void set(V v) {
        super.set(v);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.util.concurrent.FutureTask
    public void setException(Throwable th) {
        super.setException(th);
    }
}
