package com.bytedance.apm.constant;
/* compiled from: SlardarSettingsConsts.java */
/* loaded from: classes.dex */
public interface p {
    public static final String ALL_PROCESSES_SAMPLE_ENABLED = "all_processes_sample_enabled";
    public static final String BACKGROUND_TASK_ENABLED = "background_task_enabled";
    public static final String BASE_KEY_ALLOW_LOG_TYPE = "allow_log_type";
    public static final String BASE_KEY_ALLOW_METRIC_TYPE = "allow_metric_type";
    public static final String BASE_KEY_ALLOW_SERVICE_NAME = "allow_service_name";
    public static final String BASE_KEY_BASE_POLLING_INTERVAL_SECONDS = "base_polling_interval_seconds";
    public static final String BASE_KEY_LOG_REMOVE_SWITCH = "log_remove_switch";
    public static final String BASE_KEY_LOG_SEND_SWITCH = "log_send_switch";
    public static final String BASE_KEY_LOW_MEM_THRESHOLD_KB = "low_memory_threshold_kb";
    public static final String BASE_KEY_MAX_RETRY_COUNT = "max_retry_count";
    public static final String BASE_KEY_MORE_CHANNEL_STOP_INTERVAL = "more_channel_stop_interval";
    public static final String BASE_KEY_ONCE_MAX_COUNT = "once_max_count";
    public static final String BASE_KEY_ONCE_MAX_SIZE_KB = "once_max_size_kb";
    public static final String BASE_KEY_REPORT_FAIL_BASE_TIME = "report_fail_base_time";
    public static final String BASE_MEMORY_STORE_CACHE_MAX_COUNT = "memory_store_cache_max_count";
    public static final String ENABLE_ACTIVE_UNSAMPLE_ALOG = "enable_alog_save_unsamplelog";
    public static final String ENABLE_ACTIVE_UPLOAD_ALOG = "enable_active_upload_alog";
    public static final String ENABLE_MONITOR_APM6 = "apm6_switch";
    public static final String ENABLE_SALVAGE_LOG = "enable_salvage_log";
    public static final String EXT_REQUEST_HEADER = "requestHeader";
    public static final String EXT_RESPONSE_HEADER = "responseHeader";
    public static final String EXT_SUBSTATUS = "substatus";
    public static final String INSTANT_RATE_ENABLED = "enable_cpu_v2";
    public static final String KEY_QUERY_MINOR_VERSION = "minor_version";
    public static final String LOCAL_MONITOR_MIN_FREE_DISK_MB = "local_monitor_min_free_disk_mb";
    public static final String LOCAL_MONITOR_SWITCH = "local_monitor_switch";
    public static final String LOG_MAX_SIZE_MB = "log_max_size_mb";
    public static final String LOG_RESERVE_DAYS = "log_reserve_days";
    public static final String MAIN_THREAD_COLLECT_ENABLED = "main_thread_collect_enabled";
    public static final String MAX_PROCESS_USAGE = "max_process_usage";
    public static final String MAX_STAT_USE_SPEED = "max_stat_use_speed";
    public static final String PERF_APM6_COLLECT_ENABLE = "enable_collect_apm6";
    public static final String PERF_ATRACE_TAG = "atrace_tag";
    public static final String PERF_BACK_MINUTE_LIMIT = "back_minute_limit";
    public static final String PERF_BATTERY_ENABLE_UPLOAD = "enable_upload";
    public static final String PERF_BATTERY_ENERGY_ENABLE_UPLOAD = "energy_enable";
    public static final String PERF_BATTERY_ENERGY_SAMPLE_INTERVAL = "battery_energy_sample_interval";
    public static final String PERF_BATTERY_ENERGY_UPLOAD_INTERVAL = "battery_energy_upload_interval";
    public static final String PERF_BATTERY_EXCEPTION = "exception_enable_upload";
    public static final String PERF_BATTERY_RECORD_INTERVAL = "battery_record_interval";
    public static final String PERF_BATTERY_TRACE_ENABLE = "trace_enable";
    public static final String PERF_CPU_EXCEPTION = "exception";
    public static final String PERF_CPU_SAMPLE_METRIC = "cpu_monitor";
    public static final String PERF_CPU_SAMPLE_SERVICE = "cpu";
    public static final String PERF_DISK_KEY_ABNORMAL_FOLDER_SIZE = "abnormal_folder_size";
    public static final String PERF_DISK_KEY_DISK_CUSTOMED_PATHS = "disk_customed_paths";
    public static final String PERF_DISK_KEY_DUMP_THRESHOLD = "dump_threshold";
    public static final String PERF_DISK_KEY_DUMP_TOP_COUNT = "dump_top_count";
    public static final String PERF_DISK_KEY_EXCEPTION_SINK = "exception_disk";
    public static final String PERF_DISK_KEY_IGNORED_RELATIVE_PATHS = "ignored_relative_paths";
    public static final String PERF_DISK_KEY_OUTDATED_DAYS = "outdated_days";
    public static final String PERF_DISK_SAMPLE_METRIC = "storageUsed";
    public static final String PERF_DISK_SAMPLE_SERVICE = "disk";
    public static final String PERF_DROP_FRAME_BLOCK_THRESHOLD_MS = "drop_frame_block_threshold";
    public static final String PERF_DROP_FRAME_REPORT_STACK_SWITCH = "drop_frame_report_stack_switch";
    public static final String PERF_ENABLE_CLEAR_MEMORY = "enable_clear_memory";
    public static final String PERF_ENABLE_EXCEPTION_TRAFFIC = "enable_exception_traffic";
    public static final String PERF_FD_KEY_COUNT_THRESHOLD = "fd_count_threshold";
    public static final String PERF_FPS = "fps";
    public static final String PERF_FPS_DROP = "fps_drop";
    public static final String PERF_FRONT_MINUTE_LIMIT = "front_minute_limit";
    public static final String PERF_KEY_BATTERY = "battery";
    public static final String PERF_KEY_BATTERY_TEMPERATURE_ENABLE_UPLOAD = "temperature_enable_upload";
    public static final String PERF_KEY_COLLECT_INTERVAL = "collect_interval";
    public static final String PERF_KEY_CPU = "cpu";
    public static final String PERF_KEY_DISK = "disk";
    public static final String PERF_KEY_DISK_DUMP_SWITCH = "dump_switch";
    public static final String PERF_KEY_ENABLE_COLLECT = "enable_collect";
    public static final String PERF_KEY_ENABLE_THREAD_COLLECT = "enable_thread_collect";
    public static final String PERF_KEY_ENABLE_UPLOAD = "enable_upload";
    public static final String PERF_KEY_FD = "fd";
    public static final String PERF_KEY_LOCK_DATA_COLLECT = "enable_lock_data_collect";
    public static final String PERF_KEY_LONG_SLEEP_DATA_COLLECT = "enable_long_sleep_data_collect";
    public static final String PERF_KEY_MEMORY = "memory";
    public static final String PERF_KEY_MONITOR_INTERVAL = "monitor_interval";
    public static final String PERF_KEY_PAGE_LOAD = "page_load";
    public static final String PERF_KEY_PAGE_LOAD_TRACE = "page_load_trace";
    public static final String PERF_KEY_PERF_DATA_COLLECT = "enable_perf_data_collect";
    public static final String PERF_KEY_SMOOTH = "smooth";
    public static final String PERF_KEY_START = "start";
    public static final String PERF_KEY_START_TRACE = "start_trace";
    public static final String PERF_KEY_THREAD = "thread";
    public static final String PERF_KEY_THREAD_COUNT_INTERVAL = "thread_count_threshold";
    public static final String PERF_KEY_TRAFFIC = "traffic";
    public static final String PERF_KEY_UI = "ui";
    public static final String PERF_MEMORY_OBJECT_MONITOR = "memory_object_monitor";
    public static final String PERF_MEMORY_REACHTOP_INTERVAL = "memory_reachtop_check_interval";
    public static final String PERF_MEMORY_REACHTOP_RATE = "reach_top_memory_rate";
    public static final String PERF_MEM_SAMPLE_METRIC = "mem_monitor";
    public static final String PERF_MEM_SAMPLE_SERVICE = "memory";
    public static final String PERF_MOBILE_TRAFFIC_ABNORMAL_BG_TRAFFIC = "abnormal_bg_mobile_traffic";
    public static final String PERF_SMOOTH_BLOCK_DUMP_STACK = "block_dump_stack_enable";
    public static final String PERF_SMOOTH_BLOCK_ENABLE_UPLOAD = "block_enable_upload";
    public static final String PERF_SMOOTH_BLOCK_MONITOR_MODE = "block_monitor_mode";
    public static final String PERF_SMOOTH_BLOCK_THRESHOLD = "block_threshold";
    public static final String PERF_SMOOTH_DROP_BLOCK_THRESHOLD_MS = "drop_block_threshold";
    public static final String PERF_SMOOTH_DROP_ENABLE_UPLOAD = "drop_enable_upload";
    public static final String PERF_SMOOTH_DROP_SLOW_METHOD_SWITCH = "drop_slow_method_switch";
    public static final String PERF_SMOOTH_DROP_THRESHOLD_MS = "drop_threshold";
    public static final String PERF_SMOOTH_DROP_UPLOAD_STACK_SWITCH = "drop_upload_stack_switch";
    public static final String PERF_SMOOTH_ENABLE_GFX_MONITOR = "enable_gfx_monitor";
    public static final String PERF_SMOOTH_SCENE_ENABLE_UPLOAD = "scene_enable_upload";
    public static final String PERF_SMOOTH_SERIOUS_BLOCK_ENABLE_UPLOAD = "serious_block_enable_upload";
    public static final String PERF_SMOOTH_SERIOUS_BLOCK_THRESHOLD = "serious_block_threshold";
    public static final String PERF_SMOOTH_SLOW_METHOD_ENABLE_UPLOAD = "slow_method_enable_upload";
    public static final String PERF_SMOOTH_STACK_SAMPLING = "enable_stack_sampling";
    public static final String PERF_TRACE_ENABLE = "enable_trace";
    public static final String PERF_TRAFFIC_ABNORMAL_BG_TRAFFIC = "abnormal_bg_traffic";
    public static final String PERF_TRAFFIC_ABNORMAL_SWITCH = "abnormal_switch";
    public static final String PERF_TRAFFIC_ALOG_RECORD_THRESHOLD_KB = "alog_record_threshold";
    public static final String PERF_TRAFFIC_CAUSE_ANALYSIS_ENABLE = "cause_analysis";
    public static final String PERF_TRAFFIC_EXCEPTION_HIGH_FREQ_THRESHOLD = "high_freq_threshold";
    public static final String PERF_TRAFFIC_EXCEPTION_LARGE_USAGE_THRESHOLD_MB = "large_usage_threshold_mb";
    public static final String PERF_TRAFFIC_EXCEPTION_THRESHOLD_10_MINUTES_BG_MB = "exception_threshold_bg_mb";
    public static final String PERF_TRAFFIC_EXCEPTION_THRESHOLD_10_MINUTES_MB = "exception_threshold_mb";
    public static final String PERF_TRAFFIC_RECORD_USAGE_THRESHOLD_KB = "record_usage_kb";
    public static final String PERF_TRAFFIC_SAMPLE_METRIC = "traffic_monitor";
    public static final String PERF_TRAFFIC_SAMPLE_SERVICE = "traffic";
    public static final String PERF_UNIVERSAL_VALUE = "traffic_monitor_warn_threshold";
    public static final String PERF_UNIVERSAL_VALUE_MOBILE = "traffic_monitor_warn_threshold_mobile";
    public static final String SETTING_CUSTOM_EVENT = "custom_event_settings";
    public static final String SETTING_ENABLE_BASE_API_ALL = "enable_base_api_all";
    public static final String SETTING_ENABLE_CANCEL_ERROR_REPORT = "enable_cancel_error_report";
    public static final String SETTING_ENABLE_UPLOAD = "enable_upload";
    public static final String SETTING_EXCEPTION = "exception";
    public static final String SETTING_EXCEPTION_CUSTOM_EVENT = "exception_modules";
    public static final String SETTING_EXCEPTION_ENABLE_UPLOAD = "enable_upload";
    public static final String SETTING_GENERAL = "general";
    public static final String SETTING_GENERAL_API = "slardar_api_settings";
    public static final String SETTING_GENERAL_API_FETCH = "fetch_setting";
    public static final String SETTING_GENERAL_API_FETCH_INTERVAL = "fetch_setting_interval";
    public static final String SETTING_GENERAL_API_REPORT = "report_setting";
    public static final String SETTING_GENERAL_API_REPORT_BG_UOLOADING_INTERVAL = "uploading_interval_background";
    public static final String SETTING_GENERAL_API_REPORT_ENCRYPT = "enable_encrypt";
    public static final String SETTING_GENERAL_API_REPORT_HOSTS = "hosts";
    public static final String SETTING_GENERAL_API_REPORT_UOLOADING_INTERVAL = "uploading_interval";
    public static final String SETTING_GENERAL_CLEANUP = "cleanup";
    public static final String SETTING_IMAGE = "image";
    public static final String SETTING_IMAGE_ALLOW_LIST = "image_allow_list";
    public static final String SETTING_IMAGE_SAMPLE_INTERVAL = "image_sample_interval";
    public static final String SETTING_IMAGE_SLA_SWITCH = "image_sla_switch";
    public static final String SETTING_NETWORK = "network";
    public static final String SETTING_NETWORK_IMAGE_MODULES = "network_image_modules";
    public static final String SETTING_NET_API_ALLOW_LIST = "api_allow_list";
    public static final String SETTING_NET_API_BLOCK_LIST = "api_block_list";
    public static final String SETTING_NET_ENABLE_API_ALL_UPLOAD = "enable_api_all_upload";
    public static final String SETTING_NET_ENABLE_API_ERROR_UPLOAD = "enable_api_error_upload";
    public static final String SETTING_NET_ENABLE_HOOK_NET_SAMPLE = "enable_trace_log";
    public static final String SETTING_PERFORMANCE_MODULES = "performance_modules";
    public static final String SETTING_REQUEST_ALLOW_HEADER = "request_allow_header";
    public static final String SETTING_RESPONSE_ALLOW_HEADER = "response_allow_header";
    public static final String SETTING_TRACING = "tracing";
    public static final String SETTING_TRACING_ALLOW_LIST = "allow_service_list";
    public static final String SETTING_TRACING_ENABLE = "enable_open";
    public static final String SETTING_TRACING_ERROR_LIST = "allow_error_list";
    public static final String TYPE_ENSURE_MONITOR = "core_exception_monitor";

    /* renamed from: a  reason: collision with root package name */
    public static final String f950a = "compliance_relative_paths";

    /* renamed from: b  reason: collision with root package name */
    public static final String f951b = "temperature_upload_interval";

    /* renamed from: c  reason: collision with root package name */
    public static final String f952c = "support_max_api_level";

    /* renamed from: d  reason: collision with root package name */
    public static final String f953d = "battery_report_interval";

    /* renamed from: e  reason: collision with root package name */
    public static final String f954e = "battery_net_for_all_interface_enable";

    /* renamed from: f  reason: collision with root package name */
    public static final String f955f = "scene_black_list";

    /* renamed from: g  reason: collision with root package name */
    public static final String f956g = "cpu_monitor_config";

    /* renamed from: h  reason: collision with root package name */
    public static final String f957h = "cpu_monitor_enable";

    /* renamed from: i  reason: collision with root package name */
    public static final String f958i = "cpu_monitor_front_valid_duration";

    /* renamed from: j  reason: collision with root package name */
    public static final String f959j = "cpu_monitor_background_duration";
    public static final String k = "cpu_monitor_thread_count";
    public static final String l = "cpu_monitor_thread_front_capture_period";
    public static final String m = "cpu_monitor_thread_background_capture_period";
    public static final String n = "cpu_monitor_function_front_threshold";
    public static final String o = "cpu_monitor_function_background_threshold";
    public static final String p = "power_monitor_enable";
    public static final String q = "power_cpu_monitor_interval";
    public static final String r = "power_battery_monior_interval";
    public static final String s = "power_proc_cpu_upload_interval";
}
