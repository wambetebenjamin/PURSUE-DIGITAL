package com.bytedance.apm.trace.api.wrapper;
/* compiled from: ITracingWrapper.java */
/* loaded from: classes.dex */
public interface a {
    void addTracingTag(String str, String str2);

    void cancel();

    void createAndEndWindowSpan(String str, long j2, long j3);

    com.bytedance.apm.trace.api.c createWindowSpan(String str);

    void end();

    void end(long j2);

    void endSpan(String str);

    void endWindowSpan(String str, long j2, long j3);

    void start();

    com.bytedance.apm.trace.api.a startSpan(String str);

    void startWithCross();
}
