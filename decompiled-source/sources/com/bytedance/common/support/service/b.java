package com.bytedance.common.support.service;
/* compiled from: IPushConfigurationService.java */
/* loaded from: classes2.dex */
public interface b {
    long a();

    boolean b();

    com.bytedance.common.model.c getPushCommonConfiguration();

    boolean hasInjectedConfiguration();

    void injectConfiguration(com.bytedance.common.model.c cVar);

    void runAfterInit(Runnable runnable);
}
