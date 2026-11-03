package com.bytedance.sdk.account.platform;
/* compiled from: IPlatformBindAdapter.java */
/* loaded from: classes2.dex */
public interface l extends com.bytedance.sdk.account.platform.base.b {

    /* compiled from: IPlatformBindAdapter.java */
    /* loaded from: classes2.dex */
    public interface a {
        void doNext();
    }

    void onBindError(com.bytedance.sdk.account.api.call.h hVar);

    void onBindExist(com.bytedance.sdk.account.api.call.h hVar, String str, String str2, String str3, a aVar);

    void onBindSuccess(com.bytedance.sdk.account.api.call.h hVar);
}
