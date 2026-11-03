package com.bytedance.apm.trace.api;

import java.util.List;
import java.util.Map;
/* compiled from: ITracingSpanAbility.java */
/* loaded from: classes.dex */
public interface b {
    a addLog(String str);

    a addLog(String str, Map<String, String> map);

    a addTag(String str, String str2);

    long getFinishTime();

    List<com.bytedance.tracing.internal.b> getLogs();

    long getParentId();

    long getReferenceId();

    long getSpanId();

    String getSpanName();

    long getStartTime();

    Map<String, String> getTags();

    String getThreadName();

    d getTracingContext();

    void setErrorTag(String str);

    a setParentId(long j2);

    a setReferenceId(long j2);

    a setThreadName(String str);
}
