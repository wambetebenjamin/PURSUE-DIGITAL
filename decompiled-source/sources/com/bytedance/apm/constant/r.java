package com.bytedance.apm.constant;
/* compiled from: TraceStatsConsts.java */
/* loaded from: classes.dex */
public interface r {
    public static final long APP_START_EXCLUDE_PRE_START_MS = 800;
    public static final long APP_START_MAX_LIMIT_MS = 15000;
    public static final int FIRST_INSTALL_LAUNCH = 1;
    public static final int HOT_LAUNCH = 3;
    public static final int NOT_FIRST_INSTALL_LAUNCH = 2;
    public static final long PAGE_LOAD_MAX_LIMIT_MS = 20000;
    public static final String PAGE_LOAD_STATS_NAME = "page_load_stats";
    public static final String PAGE_LOAD_STATS_SERVICE_NAME = "page_load_trace";
    public static final String PAGE_STATS_KEY_PAGE_NAME = "page_name";
    public static final String PAGE_STATS_KEY_PAGE_TYPE = "page_type";
    public static final String PAGE_STATS_PAGE_TYPE_ACTIVITY = "activity";
    public static final String PAGE_STATS_PAGE_TYPE_FRAGMENT = "fragment";
    public static final int START_STATS_COLLECT_FROM_AUTO = 1;
    public static final int START_STATS_COLLECT_FROM_USER = 2;
    public static final String START_STATS_KEY_COLLECT_FROM = "collect_from";
    public static final String START_STATS_NAME = "launch_stats";
    public static final int START_STATS_PAGE_TYPE_DEFAULT = 1;
    public static final int START_STATS_PAGE_TYPE_FRAGMENT = 2;
    public static final String START_STATS_SERVICE_NAME = "start_trace";
    public static final String STATS_BASE_MODULE = "base";
    public static final String STATS_KEY_CUSTOM_LAUNCH_MODE = "custom_launch_mode";
    public static final String STATS_KEY_END = "end";
    public static final String STATS_KEY_LAUNCH_MODE = "launch_mode";
    public static final String STATS_KEY_MODULE_NAME = "module_name";
    public static final String STATS_KEY_NAME = "name";
    public static final String STATS_KEY_RELATE_SUFFIX = "relate_";
    public static final String STATS_KEY_SPANS = "spans";
    public static final String STATS_KEY_SPAN_NAME = "span_name";
    public static final String STATS_KEY_START = "start";
    public static final String STATS_KEY_THREAD = "thread";
    public static final String STATS_KEY_TRACE = "trace";
    public static final String STATS_KEY_TRACE_BASE = "trace_base";
    public static final int WARM_LAUNCH = 4;
}
