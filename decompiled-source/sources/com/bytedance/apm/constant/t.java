package com.bytedance.apm.constant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* compiled from: UploadTypeInf.java */
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface t {
    public static final String API_ALL = "api_all";
    public static final String API_ERROR = "api_error";
    public static final String BATCH_TRACING = "batch_tracing";
    public static final String BATTERY_EXCEPTION_TRACE = "battery_trace";
    public static final String BLOCK_MONITOR = "block_monitor";
    public static final String COMMON_LOG = "common_log";
    public static final String COUNT = "count";
    public static final String CPU = "cpu";
    public static final String CPU_EXCEPTION_TRACE = "cpu_trace";
    public static final String DISK = "disk";
    public static final String DROP_FRAME_STACK = "drop_frame_stack";
    public static final String FPS = "fps";
    public static final String IMAGE_MONITOR = "image_monitor";
    public static final String MEMORY = "memory";
    public static final String MEMORY_OBJ_MONITOR = "memory_object_monitor";
    public static final String PAGE_LOAD = "page_load";
    public static final String PERFORMANCE = "performance_monitor";
    public static final String SERIOUS_BLOCK_MONITOR = "serious_block_monitor";
    public static final String SERVICE_MONITOR = "service_monitor";
    public static final String START = "start";
    public static final String TIMER = "timer";
    public static final String TRACING = "tracing";
    public static final String TRAFFIC = "traffic";
    public static final String UI_ACTION = "ui_action";
}
