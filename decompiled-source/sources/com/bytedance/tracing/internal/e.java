package com.bytedance.tracing.internal;
/* compiled from: TracingConstants.java */
/* loaded from: classes3.dex */
public interface e {
    public static final int BIT_ERROR_HIT = 16;
    public static final int BIT_NOT_HIT = 0;
    public static final int BIT_SAMPLE_HIT = 1;
    public static final int FINISH_VALUE_ERROR = 0;
    public static final int FINISH_VALUE_OK = 1;
    public static final int INSERT_MODE_UPDATE_BATCH = 2;
    public static final int INSERT_MODE_UPDATE_IMMEDIATELY = 0;
    public static final int INSERT_MODE_UPDATE_INDEPENDENT = 1;
    public static final String KEY_END_TIMESTAMP = "finish_timestamp";
    public static final String KEY_FIELDS = "fields";
    public static final String KEY_FINISH_FLAG = "is_finished";
    public static final String KEY_HIT_RULES = "hit_rules";
    public static final String KEY_INSERT_MODE = "insert_mode";
    public static final String KEY_LOGS = "logs";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_OP_NAME = "operation_name";
    public static final String KEY_PARENT_ID = "parent_id";
    public static final String KEY_REF_ID = "reference_id";
    public static final String KEY_REPORT_MODE = "report_mode";
    public static final String KEY_SAMPLE_RATE = "sample_rate";
    public static final String KEY_SPAN_ID = "span_id";
    public static final String KEY_START_TIMESTAMP = "start_timestamp";
    public static final String KEY_TAGS = "tags";
    public static final String KEY_THREAD_NAME = "thread_id";
    public static final String KEY_TRACE_ID = "trace_id";
    public static final String KEY_TRACE_NAME = "service";
    public static final int REPORT_MODE_BATCH = 1;
    public static final int REPORT_MODE_INDEPENDENT = 0;
    public static final String TRACE_LOG_TYPE = "tracer";
    public static final String VALUE_TRACING = "tracer_span";
    public static final String VALUE_TRACING_WINDOW = "tracer_window_span";
}
