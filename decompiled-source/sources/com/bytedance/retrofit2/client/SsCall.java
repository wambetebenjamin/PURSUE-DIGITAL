package com.bytedance.retrofit2.client;
/* loaded from: classes2.dex */
public interface SsCall {
    void cancel();

    Response execute();

    Request getRequest();

    boolean setThrottleNetSpeed(long j2);
}
