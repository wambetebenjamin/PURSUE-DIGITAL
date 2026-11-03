package com.bytedance.apm6.service.lifecycle;
/* compiled from: ActivityLifecycleService.java */
/* loaded from: classes.dex */
public interface a {
    String getTopActivityClassName();

    boolean isForeground();

    void register(c cVar);

    void unregister(c cVar);
}
