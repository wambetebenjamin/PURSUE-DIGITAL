package com.bytedance.monitor.collector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* compiled from: MonitorType.java */
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes2.dex */
public @interface n {
    public static final String ATRACE_MONITOR = "atrace_event";
    public static final String BINDER_MONITOR = "binder_monitor";
    public static final String LOOPER_MONITOR = "block_looper_info";
    public static final String MEMORY_MONITOR = "memory_monitor";
    public static final String PROC_MONITOR = "proc_monitor";
    public static final String PROFILER_MONITOR = "profiler_monitor";

    /* renamed from: a  reason: collision with root package name */
    public static final String f9618a = "io_monitor";

    /* renamed from: b  reason: collision with root package name */
    public static final String f9619b = "lock_monitor";
}
