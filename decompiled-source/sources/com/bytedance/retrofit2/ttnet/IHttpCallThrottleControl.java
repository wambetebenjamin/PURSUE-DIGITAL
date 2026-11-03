package com.bytedance.retrofit2.ttnet;

import com.bytedance.retrofit2.client.Request;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public interface IHttpCallThrottleControl {
    boolean maybeDropRequestOrAsyncDelay(Request request, Executor executor, Runnable runnable);

    void maybeDropRequestOrBlockingDelay(Request request);

    void notifyRequestCompleted(Request request);

    default Request setOriginalRequestProperties(Request request) {
        return request;
    }
}
