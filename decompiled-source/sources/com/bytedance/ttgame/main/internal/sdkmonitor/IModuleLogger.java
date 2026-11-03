package com.bytedance.ttgame.main.internal.sdkmonitor;

import java.util.Map;
/* loaded from: classes3.dex */
public interface IModuleLogger {

    /* loaded from: classes3.dex */
    public enum LogLevel {
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    void d(String str, String str2);

    void e(String str, String str2);

    void e(String str, String str2, Throwable th);

    void i(String str, String str2);

    void monitorEvent(String str, Object obj);

    void monitorEvent(String str, Object obj, Object obj2, long j2, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3);

    void w(String str, String str2);

    void w(String str, String str2, Throwable th);
}
