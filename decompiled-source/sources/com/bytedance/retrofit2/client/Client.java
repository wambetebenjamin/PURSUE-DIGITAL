package com.bytedance.retrofit2.client;
/* loaded from: classes2.dex */
public interface Client {

    /* loaded from: classes2.dex */
    public interface Provider {
        Client get();
    }

    SsCall newSsCall(Request request);
}
