package com.bytedance.sdk.bridge.monitor;

import com.bytedance.news.common.service.manager.e;
import com.bytedance.services.apm.api.IApmAgent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: BridgeMonitor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b&\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020+2\u0006\u0010D\u001a\u00020\u00042\b\u0010E\u001a\u0004\u0018\u00010F2\u0006\u0010G\u001a\u00020FJ\u001e\u0010A\u001a\u00020B2\u0006\u0010H\u001a\u00020F2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020FJ(\u0010I\u001a\u00020B2\u0006\u0010C\u001a\u00020+2\u0006\u0010D\u001a\u00020\u00042\b\u0010E\u001a\u0004\u0018\u00010F2\u0006\u0010G\u001a\u00020FJ\u0016\u0010J\u001a\u00020B2\u0006\u0010K\u001a\u00020\u00042\u0006\u0010L\u001a\u00020FR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020+X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020+X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020+X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020+X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020+X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020+X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020+X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lcom/bytedance/sdk/bridge/monitor/BridgeMonitor;", "", "()V", "AUTH_ERROR", "", "CALL_SUCCESS_SERVICE_NAME", "CONFIG_ERROR", "CONFIG_FAILURE", "CONFIG_SUCCESS", "ERROR_ACTIVITY", "ERROR_MSG", "ERROR_URL", "FLUTTER_CALLSYNC_NOT_FOUND", "FLUTTER_CALLSYNC_NOT_SUPPORT_SYNC", "FLUTTER_CALLSYNC_NO_PARAMS", "FLUTTER_CALLSYNC_NO_PRIVILEGE", "FLUTTER_CALLSYNC_NULL", "FLUTTER_CALL_NOT_FOUND", "FLUTTER_CALL_NO_PARAMS", "FLUTTER_CALL_NO_PRIVILEGE", "FLUTTER_CALL_NULL", "JS_CALLSYNC_NOT_FOUND", "JS_CALLSYNC_NOT_SUPPORT_SYNC", "JS_CALLSYNC_NO_PARAMS", "JS_CALLSYNC_NO_PRIVILEGE", "JS_CALLSYNC_NULL", "JS_CALLSYNC_SUCCESS", "JS_CALL_NOT_FOUND", "JS_CALL_NO_PARAMS", "JS_CALL_NO_PRIVILEGE", "JS_CALL_NULL", "JS_CALL_SUCCESS", "RN_CALLSYNC_NOT_FOUND", "RN_CALLSYNC_NOT_SUPPORT_SYNC", "RN_CALLSYNC_NO_PARAMS", "RN_CALLSYNC_NO_PRIVILEGE", "RN_CALLSYNC_NULL", "RN_CALL_NOT_FOUND", "RN_CALL_NO_PARAMS", "RN_CALL_NO_PRIVILEGE", "RN_CALL_NULL", "SERVICE_NAME", "STATUS_CODE_0", "", "STATUS_CODE_1", "STATUS_CODE_2", "STATUS_CODE_3", "STATUS_CODE_4", "STATUS_CODE_5", "STATUS_CODE_6", "STATUS_CODE_7", "STATUS_MSG_FLUTTER_CALL", "STATUS_MSG_FLUTTER_CALL_SYNC", "STATUS_MSG_GET_INFO_BY_INDEX", "STATUS_MSG_JS_CALL", "STATUS_MSG_JS_CALL_SYNC", "STATUS_MSG_JS_EXCEPTION", "STATUS_MSG_JS_LOAD_URL", "STATUS_MSG_NEW_AUTH", "STATUS_MSG_OLD_JS_CALL", "STATUS_MSG_REQUEST_AUTH", "STATUS_MSG_REQUEST_CONFIG", "STATUS_MSG_RN_CALL", "STATUS_MSG_RN_CALL_SYNC", "TIME_COST", "monitorEvent", "", "statusCode", "statusMsg", "metric", "Lorg/json/JSONObject;", "extraLog", "category", "monitorJsSuccessEvent", "monitorLog", "logType", "logExtr", "bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class a {
    public static final String AUTH_ERROR = "auth_error_time_cost";
    public static final String CONFIG_ERROR = "config_error_time_cost";
    public static final String CONFIG_FAILURE = "config_failure_time_cost";
    public static final String CONFIG_SUCCESS = "config_success_time_cost";
    public static final String ERROR_ACTIVITY = "error_activity";
    public static final String ERROR_MSG = "error_msg";
    public static final String ERROR_URL = "error_url";
    public static final String FLUTTER_CALLSYNC_NOT_FOUND = "flutter_callSync_not_found_time_cost";
    public static final String FLUTTER_CALLSYNC_NOT_SUPPORT_SYNC = "flutter_callSync_not_support_sync_time_cost";
    public static final String FLUTTER_CALLSYNC_NO_PARAMS = "flutter_callSync_no_params_time_cost";
    public static final String FLUTTER_CALLSYNC_NO_PRIVILEGE = "flutter_callSync_no_privilege_time_cost";
    public static final String FLUTTER_CALLSYNC_NULL = "flutter_callSync_null_time_cost";
    public static final String FLUTTER_CALL_NOT_FOUND = "flutter_call_not_found_time_cost";
    public static final String FLUTTER_CALL_NO_PARAMS = "flutter_call_no_params_time_cost";
    public static final String FLUTTER_CALL_NO_PRIVILEGE = "flutter_call_no_privilege_time_cost";
    public static final String FLUTTER_CALL_NULL = "flutter_call_null_time_cost";
    public static final a INSTANCE = new a();
    public static final String JS_CALLSYNC_NOT_FOUND = "js_callSync_not_found_time_cost";
    public static final String JS_CALLSYNC_NOT_SUPPORT_SYNC = "js_callSync_not_support_sync_time_cost";
    public static final String JS_CALLSYNC_NO_PARAMS = "js_callSync_no_params_time_cost";
    public static final String JS_CALLSYNC_NO_PRIVILEGE = "js_callSync_no_privilege_time_cost";
    public static final String JS_CALLSYNC_NULL = "js_callSync_null_time_cost";
    public static final String JS_CALLSYNC_SUCCESS = "_js_callSync_success_time_cost";
    public static final String JS_CALL_NOT_FOUND = "js_call_not_found_time_cost";
    public static final String JS_CALL_NO_PARAMS = "js_call_no_params_time_cost";
    public static final String JS_CALL_NO_PRIVILEGE = "js_call_no_privilege_time_cost";
    public static final String JS_CALL_NULL = "js_call_null_time_cost";
    public static final String JS_CALL_SUCCESS = "_js_call_success_time_cost";
    public static final String RN_CALLSYNC_NOT_FOUND = "rn_callSync_not_found_time_cost";
    public static final String RN_CALLSYNC_NOT_SUPPORT_SYNC = "rn_callSync_not_support_sync_time_cost";
    public static final String RN_CALLSYNC_NO_PARAMS = "rn_callSync_no_params_time_cost";
    public static final String RN_CALLSYNC_NO_PRIVILEGE = "rn_callSync_no_privilege_time_cost";
    public static final String RN_CALLSYNC_NULL = "rn_callSync_null_time_cost";
    public static final String RN_CALL_NOT_FOUND = "rn_call_not_found_time_cost";
    public static final String RN_CALL_NO_PARAMS = "rn_call_no_params_time_cost";
    public static final String RN_CALL_NO_PRIVILEGE = "rn_call_no_privilege_time_cost";
    public static final String RN_CALL_NULL = "rn_call_null_time_cost";
    public static final int STATUS_CODE_0 = 0;
    public static final int STATUS_CODE_1 = 1;
    public static final int STATUS_CODE_2 = 2;
    public static final int STATUS_CODE_3 = 3;
    public static final int STATUS_CODE_4 = 4;
    public static final int STATUS_CODE_5 = 5;
    public static final int STATUS_CODE_6 = 6;
    public static final int STATUS_CODE_7 = 7;
    public static final String STATUS_MSG_FLUTTER_CALL = "flutterCall";
    public static final String STATUS_MSG_FLUTTER_CALL_SYNC = "flutterCallSync";
    public static final String STATUS_MSG_GET_INFO_BY_INDEX = "getInfoByIndex";
    public static final String STATUS_MSG_JS_CALL = "jsCall";
    public static final String STATUS_MSG_JS_CALL_SYNC = "jsCallSync";
    public static final String STATUS_MSG_JS_EXCEPTION = "exception";
    public static final String STATUS_MSG_JS_LOAD_URL = "loadUrl";
    public static final String STATUS_MSG_NEW_AUTH = "newAuth";
    public static final String STATUS_MSG_OLD_JS_CALL = "oldJsCall";
    public static final String STATUS_MSG_REQUEST_AUTH = "requestAuth";
    public static final String STATUS_MSG_REQUEST_CONFIG = "requestConfig";
    public static final String STATUS_MSG_RN_CALL = "rnCall";
    public static final String STATUS_MSG_RN_CALL_SYNC = "rnCallSync";
    public static final String TIME_COST = "_time_cost";

    /* renamed from: a  reason: collision with root package name */
    private static final String f13261a = "component_bridge_sdk_monitor";

    /* renamed from: b  reason: collision with root package name */
    private static final String f13262b = "component_bridge_sdk_monitor_success";

    private a() {
    }

    public final void monitorEvent(JSONObject category, JSONObject metric, JSONObject extraLog) {
        Intrinsics.checkParameterIsNotNull(category, "category");
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        Intrinsics.checkParameterIsNotNull(extraLog, "extraLog");
        IApmAgent iApmAgent = (IApmAgent) e.getService(IApmAgent.class);
        if (iApmAgent != null) {
            iApmAgent.monitorEvent(f13261a, category, metric, extraLog);
        }
    }

    public final void monitorLog(String logType, JSONObject logExtr) {
        Intrinsics.checkParameterIsNotNull(logType, "logType");
        Intrinsics.checkParameterIsNotNull(logExtr, "logExtr");
        IApmAgent iApmAgent = (IApmAgent) e.getService(IApmAgent.class);
        if (iApmAgent != null) {
            iApmAgent.monitorLog(logType, logExtr);
        }
    }

    public final void monitorEvent(int i2, String statusMsg, JSONObject jSONObject, JSONObject extraLog) {
        Intrinsics.checkParameterIsNotNull(statusMsg, "statusMsg");
        Intrinsics.checkParameterIsNotNull(extraLog, "extraLog");
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(statusMsg, i2);
        IApmAgent iApmAgent = (IApmAgent) e.getService(IApmAgent.class);
        if (iApmAgent != null) {
            iApmAgent.monitorEvent(f13261a, jSONObject2, jSONObject, extraLog);
        }
    }

    public final void monitorJsSuccessEvent(int i2, String statusMsg, JSONObject jSONObject, JSONObject extraLog) {
        Intrinsics.checkParameterIsNotNull(statusMsg, "statusMsg");
        Intrinsics.checkParameterIsNotNull(extraLog, "extraLog");
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(statusMsg, i2);
        IApmAgent iApmAgent = (IApmAgent) e.getService(IApmAgent.class);
        if (iApmAgent != null) {
            iApmAgent.monitorEvent(f13262b, jSONObject2, jSONObject, extraLog);
        }
    }
}
