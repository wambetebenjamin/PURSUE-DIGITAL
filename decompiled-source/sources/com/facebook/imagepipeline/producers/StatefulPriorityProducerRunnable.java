package com.facebook.imagepipeline.producers;

import com.facebook.common.executors.PriorityRunnable;
/* loaded from: classes8.dex */
public abstract class StatefulPriorityProducerRunnable<T> extends StatefulProducerRunnable<T> implements PriorityRunnable {
    private int mPriority;

    public StatefulPriorityProducerRunnable(Consumer<T> consumer, ProducerListener producerListener, String str, String str2, int i2) {
        super(consumer, producerListener, str, str2);
        this.mPriority = i2;
    }

    @Override // com.facebook.common.executors.PriorityRunnable
    public int getPriority() {
        return this.mPriority;
    }
}
