package com.bytedance.ttgame.module.monitor.api;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
/* loaded from: classes6.dex */
public interface IMonitorService extends IModuleApi {
    void cancelTrace();

    void endSpan(String str, String str2);

    void endTrace(String str, String str2, long j2);

    void reportThreadCount(String str);

    void startCollectCurrent(String str);

    void startSpan(String str, String str2);

    void startTrace();

    void stopCollectCurrent(String str);
}
