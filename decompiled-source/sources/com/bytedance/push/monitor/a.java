package com.bytedance.push.monitor;

import org.json.JSONObject;
/* compiled from: IPushMonitor.java */
/* loaded from: classes2.dex */
public interface a {
    public static final int DETAIL_CODE_UNKNOWN = -1;
    public static final String EVENT_APPLOG_CALLBACK_TIMEOUT = "push_monitor_applog_timeout";
    public static final String EVENT_APPLOG_REGISTER_RESULT = "push_monitor_register_result";
    public static final String EVENT_APPLOG_UPDATE_SENDER = "push_monitor_update_sender";
    public static final String EVENT_COMMON_EVENT = "push_monitor_common_event";
    public static final String EVENT_DECRYPT_RESULT = "push_monitor_decrypt_result";
    public static final String EVENT_OUTER_SWITCH_STATUS_RESULT = "outer_switch_sync_result";
    public static final String EVENT_OUTER_SWITCH_UPLOAD_RESULT = "outer_switch_upload_result";
    public static final int MSG_WHAT_REGISTER_PUSH = 1001;
    public static final int STATUS_FAILED_EMPTY_RESPONSE = 304;
    public static final int STATUS_FAILED_ERROR_SENDER = 303;
    public static final int STATUS_FAILED_EXCEPTION = 301;
    public static final int STATUS_FAILED_NOT_SUCCESS = 302;
    public static final int STATUS_REGISTER_CHECK_BEFORE_REGISTER = 101;
    public static final int STATUS_REGISTER_EMPTY_CONFIG = 106;
    public static final int STATUS_REGISTER_ERROR_ALIAS = 105;
    public static final int STATUS_REGISTER_ERROR_TOKEN = 102;
    public static final int STATUS_REGISTER_FAILED = 104;
    public static final int STATUS_REGISTER_SENDER_NOT_SUPPORT = 107;
    public static final int STATUS_REGISTER_SENDER_TIMEOUT = 108;
    public static final int STATUS_REGISTER_SEND_TOKEN = 103;
    public static final int STATUS_SUCCESS = 0;

    /* renamed from: a  reason: collision with root package name */
    public static final String f11453a = "push_monitor_show_empty";

    /* renamed from: b  reason: collision with root package name */
    public static final String f11454b = "push_monitor_harmony_os4_compliance";

    void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3);
}
