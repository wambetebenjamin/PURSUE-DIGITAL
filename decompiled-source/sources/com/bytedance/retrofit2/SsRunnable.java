package com.bytedance.retrofit2;
/* loaded from: classes2.dex */
public interface SsRunnable extends Runnable {
    int getRequestDelayTime();

    RetrofitMetrics getRetrofitMetrics();

    boolean isStreaming();

    int priority();
}
