package com.bytedance.sdk.account.platform.base;

import org.json.JSONObject;
/* compiled from: AuthorizeMonitorUtil.java */
/* loaded from: classes2.dex */
public class g {
    public static final String EVENT = "passport_third_party_oauth_result";
    public static final String SCENE_AUTH = "auth";
    public static final String SPECIAL = "uc_login";

    /* compiled from: AuthorizeMonitorUtil.java */
    /* loaded from: classes2.dex */
    public interface a {
        public static final String AUTH_ERROR_CANCEL = "errorCancel";
        public static final String ERROR_CODE = "errCode";
        public static final String ERROR_DESC = "errDesc";
        public static final String PARAMS_FOR_SPECIAL = "params_for_special";
        public static final String PASSPORT_SDK_VERSION = "passport-sdk-version";
        public static final String PLATFORM = "platform";
        public static final String RESULT = "result";
        public static final String SCENE = "scene";
    }

    /* compiled from: AuthorizeMonitorUtil.java */
    /* loaded from: classes2.dex */
    public interface b {
        public static final int FAILURE = 0;
        public static final int SUCCESS = 1;
    }

    public static void onPlatformAuthEvent(String str, int i2, String str2, String str3, boolean z, JSONObject jSONObject) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        jSONObject.put("platform", str);
        jSONObject.put("scene", "auth");
        jSONObject.put("result", i2);
        if (str2 != null) {
            jSONObject.put("errCode", str2);
        }
        if (str3 != null) {
            jSONObject.put("errDesc", str3);
        }
        if (i2 == 0 && z) {
            jSONObject.put("errorCancel", z);
        }
        jSONObject.put("passport-sdk-version", 5040090);
        jSONObject.put("params_for_special", SPECIAL);
        a(EVENT, jSONObject);
    }

    public static void checkMonitorService() {
        if (((com.bytedance.sdk.account.platform.api.c) e.getService(com.bytedance.sdk.account.platform.api.c.class)) == null) {
            throw new IllegalStateException("need IAuthorizeMonitorService to upload SDK events, please call AuthorizeFramework.registerService(IAuthorizeMonitorService instacne) first");
        }
    }

    private static void a(String str, JSONObject jSONObject) {
        com.bytedance.sdk.account.platform.api.c cVar = (com.bytedance.sdk.account.platform.api.c) e.getService(com.bytedance.sdk.account.platform.api.c.class);
        if (cVar != null) {
            cVar.onEvent(str, jSONObject);
        }
    }
}
