package com.bytedance.apm.constant;

import java.util.Arrays;
import java.util.List;
/* compiled from: ReportTypeConstants.java */
/* loaded from: classes.dex */
public class m {
    public static final List<String> sUploadTypeListInDefaultReportType = Arrays.asList("timer", "count", "disk", "memory", "cpu", "fps", "traffic", "start", "page_load", "image_monitor", "api_all", "api_error", "common_log", "service_monitor", "performance_monitor", "ui_action");
    public static final List<String> sUploadTypeListInExceptionType = Arrays.asList("block_monitor", "serious_block_monitor", "memory_object_monitor", "drop_frame_stack", "cpu_trace", "battery_trace");
    public static final List<String> sUploadTypeListInTraceType = Arrays.asList("tracing", "batch_tracing");
}
