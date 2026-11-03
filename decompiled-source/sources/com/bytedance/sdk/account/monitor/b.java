package com.bytedance.sdk.account.monitor;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.sdk.account.api.call.d;
import com.bytedance.sdk.account.impl.h;
import com.bytedance.sdk.account.monitor.a;
import com.bytedance.sdk.account.platform.base.g;
import com.bytedance.ttgame.sdk.module.core.internal.Constants;
import com.ss.android.account.f;
import java.net.URI;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: AccountMonitorUtil.java */
/* loaded from: classes2.dex */
public class b {
    public static void eventFinalize(String str, String str2, boolean z, int i2, String str3, JSONObject jSONObject) {
        event(a.EVENT_LOGIN_FINALIZE, null, str, str2, z, i2, str3, jSONObject);
    }

    public static void authCallback(String str, boolean z, int i2, String str2, JSONObject jSONObject) {
        event("passport_oauth_callback", null, str, "auth", z, i2, str2, jSONObject);
    }

    public static void eventSwitchBindCancel(String str, JSONObject jSONObject) {
        event(a.d.OAUTH_SWITCH_CLICK, null, str, "cancel", false, 0, null, jSONObject);
    }

    public static void loginUiShow(String str, String str2, boolean z, int i2, JSONObject jSONObject) {
        event(a.d.DIALOG_SHOW, null, str, str2, z, i2, null, jSONObject);
    }

    public static void loginEntranceShowEvent(String str, JSONObject jSONObject) {
        onLoginEvent(a.d.LOGIN_ENTRANCE_EVENT, str, null, null, null, jSONObject);
    }

    public static void loginPageShowEvent(String str, String str2, String str3, JSONObject jSONObject) {
        onLoginEvent(a.d.LOGIN_PAGE_SHOW_EVENT, str, str2, str3, null, jSONObject);
    }

    public static void loginClickEvent(String str, String str2, String str3, JSONObject jSONObject) {
        onLoginEvent(a.d.LOGIN_CLICK_EVENT, str, str2, null, str3, jSONObject);
    }

    public static void loginResultEvent(String str, String str2, String str3, boolean z, int i2, String str4, JSONObject jSONObject) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e2) {
                e2.printStackTrace();
                return;
            }
        }
        jSONObject.put(a.InterfaceC0193a.LOGIN_POSITION, str);
        jSONObject.put(a.InterfaceC0193a.LOGIN_PAGE, str2);
        jSONObject.put(a.InterfaceC0193a.LOGIN_PLATFORM, str3);
        if (z) {
            jSONObject.put(a.InterfaceC0193a.LOGIN_RESULT, "success");
        } else {
            jSONObject.put(a.InterfaceC0193a.LOGIN_RESULT, a.InterfaceC0193a.RESULT_FAIL);
            jSONObject.put("login_error_code", i2);
            jSONObject.put(a.InterfaceC0193a.LOGIN_ERROR_MSG, str4);
        }
        jSONObject.put("passport-sdk-version", 5040090);
        jSONObject.put("sdkVersion", 2);
        onEventV3(a.d.LOGIN_CLICK_RESULT_EVENT, jSONObject);
    }

    public static void sendCodeClickEvent(String str, boolean z, int i2, String str2, JSONObject jSONObject) {
        onSendCodeEvent(a.d.MOBILE_CODE_CLICK_EVENT, str, z, i2, str2, jSONObject);
    }

    public static void inputCodeEvent(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e2) {
                e2.printStackTrace();
                return;
            }
        }
        jSONObject.put(a.InterfaceC0193a.SENDCODE_POSITION, str);
        jSONObject.put("passport-sdk-version", 5040090);
        jSONObject.put("sdkVersion", 2);
        onEventV3(a.d.MOBILE_CODE_INPUT_EVENT, jSONObject);
    }

    public static void submitCodeEvent(String str, boolean z, int i2, String str2, JSONObject jSONObject) {
        onSendCodeEvent(a.d.MOBILE_CODE_SUBMIT_EVENT, str, z, i2, str2, jSONObject);
    }

    public static void onLoginEvent(String str, String str2, String str3, String str4, String str5, JSONObject jSONObject) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e2) {
                e2.printStackTrace();
                return;
            }
        }
        jSONObject.put(a.InterfaceC0193a.LOGIN_POSITION, str2);
        jSONObject.put(a.InterfaceC0193a.LOGIN_PAGE, str3);
        jSONObject.put(a.InterfaceC0193a.LOGIN_PLATFORM, str5);
        jSONObject.put(a.InterfaceC0193a.LOGIN_PLATFORM_LIST, str4);
        jSONObject.put("passport-sdk-version", 5040090);
        jSONObject.put("sdkVersion", 2);
        onEventV3(str, jSONObject);
    }

    public static void onSendCodeEvent(String str, String str2, boolean z, int i2, String str3, JSONObject jSONObject) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e2) {
                e2.printStackTrace();
                return;
            }
        }
        jSONObject.put(a.InterfaceC0193a.SENDCODE_POSITION, str2);
        if (z) {
            jSONObject.put(a.InterfaceC0193a.SENDCODE_RESULT, 1);
        } else {
            jSONObject.put(a.InterfaceC0193a.SENDCODE_RESULT, 0);
            jSONObject.put(a.InterfaceC0193a.SENDCODE_ERROR_CODE, i2);
            jSONObject.put(a.InterfaceC0193a.SENDCODE_ERROR_MSG, str3);
        }
        jSONObject.put("passport-sdk-version", 5040090);
        jSONObject.put("sdkVersion", 2);
        onEventV3(str, jSONObject);
    }

    public static void event(String str, String str2, String str3, String str4, boolean z, int i2, String str5, JSONObject jSONObject) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e2) {
                e2.printStackTrace();
                return;
            }
        }
        jSONObject.put("platform", str3);
        jSONObject.put("scene", str4);
        if (!TextUtils.isEmpty(str2)) {
            jSONObject.put(a.InterfaceC0193a.URLPATH, str2);
        }
        if (z) {
            jSONObject.put("result", 1);
        } else {
            jSONObject.put("result", 0);
            jSONObject.put("errorCode", i2);
            if (str5 != null) {
                jSONObject.put("errDesc", str5);
            }
        }
        jSONObject.put("passport-sdk-version", 5040090);
        jSONObject.put("sdkVersion", 2);
        onEventV3(str, jSONObject);
    }

    public static void onEvent(String str, String str2, String str3, com.bytedance.sdk.account.api.call.b bVar, com.bytedance.sdk.account.api.call.a aVar) {
        event(str, str2, str3, bVar, aVar != null ? aVar.onSendEvent(str) : null);
    }

    public static void event(String str, String str2, String str3, com.bytedance.sdk.account.api.call.b bVar, JSONObject jSONObject) {
        JSONObject jSONObject2;
        if (bVar != null) {
            String str4 = bVar.errorMsg;
            if (TextUtils.isEmpty(str4)) {
                str4 = bVar.mDetailErrorMsg;
            }
            String str5 = str4;
            if (jSONObject == null) {
                try {
                    jSONObject2 = new JSONObject();
                } catch (JSONException e2) {
                    e = e2;
                    jSONObject2 = jSONObject;
                    e.printStackTrace();
                    event(str, bVar.mRequestUrl, str2, str3, bVar.success, bVar.mDetailErrorCode, str5, jSONObject2);
                    return;
                }
            } else {
                jSONObject2 = jSONObject;
            }
            try {
                jSONObject2.put("logid", bVar.logId);
                if (bVar instanceof d) {
                    String str6 = ((d) bVar).mLogoutScene;
                    if (!TextUtils.isEmpty(str6)) {
                        jSONObject2.put("logout_from", str6);
                    }
                }
            } catch (JSONException e3) {
                e = e3;
                e.printStackTrace();
                event(str, bVar.mRequestUrl, str2, str3, bVar.success, bVar.mDetailErrorCode, str5, jSONObject2);
                return;
            }
            event(str, bVar.mRequestUrl, str2, str3, bVar.success, bVar.mDetailErrorCode, str5, jSONObject2);
            return;
        }
        event(str, null, str2, str3, false, -1, null, jSONObject);
    }

    public static void platformEvent(String str, String str2, String str3, int i2, String str4, String str5, boolean z, JSONObject jSONObject) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e2) {
                e2.printStackTrace();
                return;
            }
        }
        jSONObject.put("platform", str2);
        jSONObject.put("result", i2);
        jSONObject.put("scene", str3);
        if (str4 != null) {
            jSONObject.put("errCode", str4);
        }
        if (str5 != null) {
            jSONObject.put("errDesc", str5);
        }
        if (i2 == 0 && z) {
            jSONObject.put("errorCancel", z);
        }
        jSONObject.put("passport-sdk-version", 5040090);
        jSONObject.put("sdkVersion", 2);
        onEventV3(str, jSONObject);
    }

    public static void platformAuthEvent(String str, String str2, int i2, String str3, String str4, boolean z, JSONObject jSONObject) {
        platformEvent("passport_oauth_callback", str, str2, i2, str3, str4, z, jSONObject);
    }

    public static void onGetLoginInfoEvent(String str, com.bytedance.sdk.account.api.call.b bVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (bVar.success) {
            jSONObject.put("result", 1);
        } else {
            jSONObject.put("result", 0);
            jSONObject.put("errCode", bVar.error);
            jSONObject.put("errDesc", bVar.errorMsg);
        }
        onEventV3(str, jSONObject);
    }

    public static void onSessionExpriedEvent(String str, String str2, String str3, String str4) {
        onSessionExpiredEvent(str, str2, str3, str4, h.instance(f.getConfig().getApplicationContext()).isLogin());
    }

    public static void onSessionExpiredEvent(String str, String str2, String str3, String str4, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("logid", str3);
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put(a.InterfaceC0193a.URLPATH, str2);
            }
            jSONObject.put("sdkVersion", 2);
            if (!TextUtils.isEmpty(str4)) {
                jSONObject.put("extra", str4);
            }
            jSONObject.put("passport-sdk-version", 5040090);
            jSONObject.put(a.InterfaceC0193a.USER_WAS_LOGIN, z ? "1" : "0");
            jSONObject.put("result", 0);
            onEventV3(str, jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void onTokenEvent(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e2) {
                e2.printStackTrace();
                return;
            }
        }
        jSONObject.put("passport-sdk-version", 5040090);
        jSONObject.put("result", 0);
        onEventV3(str, jSONObject);
    }

    public static void sessionEvent(String str, String str2, String str3, JSONObject jSONObject) {
        event(str, str2, null, null, false, -1, str3, jSONObject);
    }

    public static void onRecentOneLoginEvent(String str, com.bytedance.sdk.account.api.call.b bVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (bVar.success) {
            jSONObject.put("result", 1);
        } else {
            jSONObject.put("result", 0);
            jSONObject.put("errCode", bVar.error);
            jSONObject.put("errDesc", bVar.errorMsg);
        }
        onEventV3(str, jSONObject);
    }

    public static void onSyncLoginStatusError() {
        onEventV3(a.b.SYNC_LOGIN_STATUS_ERROR, null);
    }

    public static void onNeverCallTokenInit() {
        onEventV3(a.b.NEVER_CALL_TOKEN_INIT, null);
    }

    public static void onEventV3(String str, JSONObject jSONObject) {
        com.bytedance.sdk.account.utils.d monitor = f.getConfig().getMonitor();
        if (monitor != null) {
            if (jSONObject != null) {
                try {
                    if (!jSONObject.has("passport-sdk-version")) {
                        jSONObject.put("passport-sdk-version", 5040090);
                    }
                    jSONObject.put("params_for_special", g.SPECIAL);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            monitor.onEvent(str, jSONObject);
        }
    }

    public static void netExceptionEvent(String str, Throwable th, int i2, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(a.InterfaceC0193a.URLPATH, new URI(str).getPath().toLowerCase());
            if (th != null) {
                jSONObject.put("exception_stack_trace", Log.getStackTraceString(th));
                jSONObject.put("exception_msg", th.getMessage());
            } else if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("exception_msg", str2);
            }
            jSONObject.put("errorCode", i2);
            jSONObject.put("passport-sdk-version", 5040090);
            jSONObject.put("sdkVersion", 2);
            onEventV3("passport_net_err", jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void a(String str, String str2, long j2, int i2, String str3, String str4, boolean z, int i3, boolean z2, boolean z3, boolean z4) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.bytedance.bdturing.setting.g.HOST_KEY, str);
            jSONObject.put("path", str2);
            jSONObject.put("duration", j2);
            jSONObject.put("error_code", i2);
            jSONObject.put("error_desc", str3);
            jSONObject.put("logid", str4);
            int i4 = 1;
            jSONObject.put("request_x_tt_token", z ? 1 : 0);
            jSONObject.put("request_csrf_token", i3);
            jSONObject.put("response_x_tt_token", z2 ? 1 : 0);
            jSONObject.put("response_csrf_token", z3 ? 1 : 0);
            if (!z4) {
                i4 = 0;
            }
            jSONObject.put("csrf_token_status", i4);
            onEventV3("passport_net_request_result", jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(boolean z, boolean z2, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            int i2 = 1;
            jSONObject.put(Constants.IS_LOGIN, z ? 1 : 0);
            if (!z2) {
                i2 = 0;
            }
            jSONObject.put("user_info", i2);
            jSONObject.put("user_id", str);
            onEventV3("passport_load_user_info", jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.bytedance.bdturing.setting.g.HOST_KEY, str);
            jSONObject.put("path", str2);
            jSONObject.put("logid", str3);
            onEventV3("passport_xtttoken_error", jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void commonExceptionEvent(Throwable th) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (th != null) {
                jSONObject.put("exception_stack_trace", Log.getStackTraceString(th));
                jSONObject.put("exception_msg", th.getMessage());
            }
            jSONObject.put("passport-sdk-version", 5040090);
            jSONObject.put("sdkVersion", 2);
            onEventV3("passport_common_err", jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
