package com.bytedance.react.framework.core;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class RNLogUtil {
    public static final String CATEGORY_BEGIN = "begin";
    public static final String CATEGORY_END = "end";
    public static final String CATEGORY_ERROR = "error";
    public static final String CATEGORY_LEVEL = "level";
    public static final String CATEGORY_RESULT = "result";
    public static final String DYNAMIC_OPEN_URL = "gmd_openUrl";
    public static final String RN_ACTIVITY_REQUEST = "rn_activity_request";
    public static final String RN_BRIDGE_CALL = "rn_bridge_call";
    public static final String RN_BUNDLE_COPY_END = "rn_bundle_copy_end";
    public static final String RN_BUNDLE_COPY_FAILED = "rn_bundle_copy_failed";
    public static final String RN_BUNDLE_COPY_START = "rn_bundle_copy_start";
    public static final String RN_GECKO_REQUEST = "rn_gecko_request";
    public static final String RN_IMAGE_LOADED = "rn_image_loaded";
    public static final String RN_INIT = "rn_init";
    public static final String RN_LEVEL_INIT = "rn_level_init";
    public static final String RN_OPEN_URL = "rn_open_url";
    public static final String RN_PAGE_CLOSE = "rn_page_close";
    public static final String RN_PAGE_WILL_APPEAR = "rn_page_will_appear";
    public static final String RN_PAGE_WILL_DISAPPEAR = "rn_page_will_disappear";
    public static final String RN_SEND_TO_UNITY = "rn_send_to_unity";
    public static final String RN_UNITY_BRIDGE_CALL = "rn_unity_bridge_call";

    public static void reportLog(String str, String str2, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("status", str2);
        } catch (JSONException unused) {
        }
        BRNManager.newInstance().reportMonitor(str, jSONObject2, new JSONObject(), jSONObject);
    }

    public static void reportLog(String str, String str2) {
        reportLog(str, str2, new JSONObject());
    }

    public static void reportLog(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("msg", str3);
        } catch (JSONException unused) {
        }
        reportLog(str, str2, jSONObject);
    }
}
