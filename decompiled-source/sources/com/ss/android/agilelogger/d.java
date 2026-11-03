package com.ss.android.agilelogger;

import java.util.Queue;
/* compiled from: ILogCacheCallback.java */
/* loaded from: classes9.dex */
public interface d {
    Queue<f> getCachedLog();

    void notifyCacheLogConsumed();
}
