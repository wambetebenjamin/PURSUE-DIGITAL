package com.facebook.common.executors;
/* loaded from: classes8.dex */
public abstract class AbstractPriorityRunnable implements PriorityRunnable {
    private int mPriority;

    public AbstractPriorityRunnable() {
        this.mPriority = 1;
    }

    public AbstractPriorityRunnable(int i2) {
        this.mPriority = i2;
    }

    @Override // com.facebook.common.executors.PriorityRunnable
    public int getPriority() {
        return this.mPriority;
    }
}
