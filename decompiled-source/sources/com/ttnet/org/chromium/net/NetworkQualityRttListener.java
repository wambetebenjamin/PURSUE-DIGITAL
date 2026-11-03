package com.ttnet.org.chromium.net;

import java.util.concurrent.Executor;
/* loaded from: classes9.dex */
public abstract class NetworkQualityRttListener {
    private final Executor mExecutor;

    public abstract void onRttObservation(int i2, long j2, int i3);

    public NetworkQualityRttListener(Executor executor) {
        if (executor == null) {
            throw new IllegalStateException("Executor must not be null");
        }
        this.mExecutor = executor;
    }

    public Executor getExecutor() {
        return this.mExecutor;
    }
}
