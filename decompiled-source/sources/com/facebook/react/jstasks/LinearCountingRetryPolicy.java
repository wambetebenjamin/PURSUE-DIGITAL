package com.facebook.react.jstasks;
/* loaded from: classes8.dex */
public class LinearCountingRetryPolicy implements HeadlessJsTaskRetryPolicy {
    private final int mDelayBetweenAttemptsInMs;
    private final int mRetryAttempts;

    public LinearCountingRetryPolicy(int i2, int i3) {
        this.mRetryAttempts = i2;
        this.mDelayBetweenAttemptsInMs = i3;
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public boolean canRetry() {
        return this.mRetryAttempts > 0;
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public int getDelay() {
        return this.mDelayBetweenAttemptsInMs;
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public HeadlessJsTaskRetryPolicy update() {
        int i2 = this.mRetryAttempts - 1;
        if (i2 > 0) {
            return new LinearCountingRetryPolicy(i2, this.mDelayBetweenAttemptsInMs);
        }
        return NoRetryPolicy.INSTANCE;
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public HeadlessJsTaskRetryPolicy copy() {
        return new LinearCountingRetryPolicy(this.mRetryAttempts, this.mDelayBetweenAttemptsInMs);
    }
}
