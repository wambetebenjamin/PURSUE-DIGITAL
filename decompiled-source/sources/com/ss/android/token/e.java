package com.ss.android.token;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.sdk.account.monitor.a;
import com.bytedance.sdk.account.utils.n;
import com.meituan.robust.Constants;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: TTTokenMonitor.java */
/* loaded from: classes9.dex */
public class e {
    public static final String TT_LOGID_KEY = "X-TT-LOGID";
    public static final String TT_SESSION_EXPIRE = "tt_token_logout";
    public static final String TT_TOKEN_BEAT = "tt_token_beat";
    public static final String TT_TOKEN_CHANGE = "tt_token_change";
    public static final String TT_TOKEN_CONFIG_ERROR = "tt_token_config_error";
    public static final String TT_TOKEN_CONFIG_ERROR_V2 = "token_config_err_v2";
    public static final String TT_TOKEN_DECRYPT_ERR = "tt_token_decrypt_err";
    public static final String TT_TOKEN_EXCEPTION = "tt_token_exception";
    public static final String TT_TOKEN_NOT_ADD_TO_HOST = "tt_account_request_without_x_tt_token";
    public static final String TT_TOKEN_NOT_CALL_ADD_HOST_LIST = "tt_token_not_call_add_host_list";

    /* renamed from: a  reason: collision with root package name */
    private static final String f18551a = "x_tt_token_lost";

    /* renamed from: b  reason: collision with root package name */
    private static HashSet<String> f18552b = new HashSet<>();

    /* renamed from: c  reason: collision with root package name */
    public static final String f18553c = "passport_request_without_x_tt_token";

    /* renamed from: d  reason: collision with root package name */
    public static final String f18554d = "sdk_session_launch";

    /* compiled from: TTTokenMonitor.java */
    /* loaded from: classes9.dex */
    public enum a {
        ignore,
        logout
    }

    public static void monitorTokenLost(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(a.InterfaceC0193a.URLPATH, a(str));
            a(f18551a, jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void monitorDecryptError(String str, String str2, String str3, String str4) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", str);
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("tokenSign", str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("token", str3);
            }
            if (!TextUtils.isEmpty(str4)) {
                jSONObject.put("errorMsg", str4);
            }
            a(TT_TOKEN_DECRYPT_ERR, jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void monitorToken(String str, List<c> list, int i2, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("error_code", i2);
            jSONObject.put("error_code_msg", str2);
            StringBuilder sb = new StringBuilder();
            if (list != null && !list.isEmpty()) {
                for (c cVar : list) {
                    if (cVar instanceof c) {
                        jSONObject.put(cVar.getName(), cVar.getValue());
                    } else if (cVar != null) {
                        sb.append(cVar.toString());
                        sb.append(Constants.PACKNAME_END);
                    }
                }
            }
            jSONObject.put("extra", sb.toString());
            a(str, jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void monitorSessionExpire(String str, List list) {
        monitorSessionExpired(str, list, d.c());
    }

    public static void monitorSessionExpired(String str, List list, boolean z) {
        try {
            String str2 = "";
            StringBuilder sb = new StringBuilder();
            if (list != null && !list.isEmpty()) {
                for (Object obj : list) {
                    if (obj instanceof c) {
                        if (TT_LOGID_KEY.equalsIgnoreCase(((c) obj).getName())) {
                            str2 = ((c) obj).getValue();
                        }
                    } else if (obj != null) {
                        sb.append(obj.toString());
                        sb.append(Constants.PACKNAME_END);
                    }
                }
            }
            a(TT_SESSION_EXPIRE, a(str), str2, sb.toString(), z);
        } catch (Exception e2) {
            monitorError(e2);
        }
    }

    public static void a(String str, String str2, String str3, String str4, a aVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("scene", str);
            jSONObject.put("from_host", str2);
            jSONObject.put("from_path", str3);
            jSONObject.put("from_logid", str4);
            jSONObject.put("handle", aVar.name());
            b("passport_session_expired_result", jSONObject);
        } catch (Exception e2) {
            monitorError(e2);
        }
    }

    public static void a(String str, String str2, String str3, String str4) {
        a(str, str2, str3, str4, d.c());
    }

    public static void a(String str, String str2, String str3, String str4, boolean z) {
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
            jSONObject.put(a.InterfaceC0193a.USER_WAS_LOGIN, z ? "1" : "0");
            jSONObject.put("result", 0);
            b(str, jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private static String a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                URI uri = new URI(str);
                StringBuilder sb = new StringBuilder();
                if (!TextUtils.isEmpty(uri.getHost())) {
                    sb.append(uri.getHost());
                }
                if (!TextUtils.isEmpty(uri.getPath())) {
                    sb.append(uri.getPath());
                }
                return sb.toString();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return str;
    }

    public static void monitorConfigError(String str, String str2, JSONObject jSONObject) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        jSONObject.put("error_name", str);
        jSONObject.put("error_message", str2);
        int i2 = 1;
        jSONObject.put("network_available", d.a() ? 1 : 0);
        if (!n.a()) {
            i2 = 0;
        }
        jSONObject.put("app_in_foreground", i2);
        a(TT_TOKEN_CONFIG_ERROR_V2, jSONObject);
    }

    public static void monitorTokenChange(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("token", g.getMixVal(str));
            jSONObject.put("logid", str2);
            a(TT_TOKEN_CHANGE, jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void monitorError(Throwable th) {
        try {
            th.printStackTrace();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("exception_msg", th.getMessage());
            jSONObject.put("exception_stack_trace", Log.getStackTraceString(th));
            a(TT_TOKEN_EXCEPTION, jSONObject);
            d.a(th);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void monitorNotAddToken(String str, Set<String> set) {
        try {
            JSONObject jSONObject = new JSONObject();
            URI uri = new URI(str);
            String host = uri.getHost();
            if (uri.getPath().startsWith("passport/") && !f18552b.contains(host)) {
                f18552b.add(host);
                jSONObject.put(com.bytedance.bdturing.setting.g.HOST_KEY, host);
                jSONObject.put("tt_account_share_token_domains", set.toString());
                a(f18553c, jSONObject);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void monitorNotCallAddHostList() {
        a(TT_TOKEN_NOT_CALL_ADD_HOST_LIST, null);
    }

    public static void a() {
        b(a.b.SYNC_LOGIN_STATUS_ERROR, null);
    }

    public static void a(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e2) {
                e2.printStackTrace();
                return;
            }
        }
        jSONObject.put("result", 0);
        b(str, jSONObject);
    }

    public static void b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sdk_aid", 71769);
            jSONObject.put("sdk_version", "0.5.4-bugfix.0");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        b(f18554d, jSONObject);
    }

    public static void a(boolean z, boolean z2, boolean z3, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            int i2 = 1;
            jSONObject.put(com.bytedance.ttgame.sdk.module.core.internal.Constants.IS_LOGIN, z ? 1 : 0);
            if (!z2) {
                i2 = 0;
            }
            jSONObject.put(f.B, i2);
            jSONObject.put("xtttoken_location", z3 ? "default" : "none");
            jSONObject.put("csrf_token_location", str);
            b("passport_load_login_token", jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put("passport-sdk-version", 5040090);
            jSONObject.put("params_for_special", com.bytedance.sdk.account.platform.base.g.SPECIAL);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        d.a(str, jSONObject);
    }
}
