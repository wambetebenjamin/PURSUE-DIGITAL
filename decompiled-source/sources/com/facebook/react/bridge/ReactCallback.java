package com.facebook.react.bridge;
/* loaded from: classes8.dex */
interface ReactCallback {
    void decrementPendingJSCalls();

    void incrementPendingJSCalls();

    void onBatchComplete();
}
