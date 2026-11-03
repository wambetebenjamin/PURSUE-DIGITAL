package com.ss.android.common.lib;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.applog.util.BlockHelper;
import com.bytedance.common.utility.o;
import com.ss.android.common.applog.AppLog;
import com.ss.android.common.applog.TeaThread;
import com.ss.android.common.util.b;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class AppLogNewUtils {
    public static final String EVENT_LABEL_TEST = "click_headline";
    public static final String EVENT_RESERVED_FIELD_TIME_STAMP = "event_v3_reserved_field_time_stamp";
    public static final String EVENT_SIGN = "_event_v3";
    public static final String EVENT_TAG_TEST1 = "go_detail";
    public static final String EVENT_TAG_TEST2 = "video_play";
    public static final String EVENT_V3_CATEGORY = "event_v3";
    private static final String SECOND_APP_ID_KEY = "second_appid";
    private static final String SECOND_APP_NAME_KEY = "second_appname";
    private static final String SECOND_APP_PREFIX = "second_app_";
    private static final String SECOND_APP_PRODUCT_TYPE_KEY = "product_type";
    private static final String SECOND_APP_SPECIAL_KEY = "params_for_special";
    private static final String SECOND_APP_VALUE = "second_app";

    public static void onEventV3(String str, JSONObject jSONObject) {
        onEventV3ByConstParams(str, copyJson(jSONObject));
    }

    public static void onEventV3ByConstParams(final String str, final JSONObject jSONObject) {
        final long currentTimeMillis = System.currentTimeMillis();
        TeaThread.getInst().ensureTeaThreadLite(new Runnable() { // from class: com.ss.android.common.lib.AppLogNewUtils.1
            @Override // java.lang.Runnable
            public void run() {
                BlockHelper.tryBlock();
                if (o.isEmpty(str)) {
                    return;
                }
                JSONObject jSONObject2 = jSONObject;
                if (jSONObject2 == null) {
                    jSONObject2 = new JSONObject();
                }
                JSONObject jSONObject3 = jSONObject2;
                try {
                    jSONObject3.put("_event_v3", 1);
                    jSONObject3.put(AppLogNewUtils.EVENT_RESERVED_FIELD_TIME_STAMP, currentTimeMillis);
                    String abSDKVersion = AppLog.getAbSDKVersion();
                    if (!TextUtils.isEmpty(abSDKVersion)) {
                        jSONObject3.put("ab_sdk_version", abSDKVersion);
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                AppLog.onEvent((Context) null, "event_v3", str, (String) null, 0L, 0L, jSONObject3);
            }
        });
    }

    public static void onEventV3Map(final String str, Map<String, String> map) {
        if (o.isEmpty(str)) {
            return;
        }
        final JSONObject jSONObject = isEmptyMap(map) ? new JSONObject() : new JSONObject(map);
        final long currentTimeMillis = System.currentTimeMillis();
        TeaThread.getInst().ensureTeaThreadLite(new Runnable() { // from class: com.ss.android.common.lib.AppLogNewUtils.2
            @Override // java.lang.Runnable
            public void run() {
                BlockHelper.tryBlock();
                try {
                    jSONObject.put("_event_v3", 1);
                    jSONObject.put(AppLogNewUtils.EVENT_RESERVED_FIELD_TIME_STAMP, currentTimeMillis);
                    String abSDKVersion = AppLog.getAbSDKVersion();
                    if (!TextUtils.isEmpty(abSDKVersion)) {
                        jSONObject.put("ab_sdk_version", abSDKVersion);
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                AppLog.onEvent((Context) null, "event_v3", str, (String) null, 0L, 0L, jSONObject);
            }
        });
    }

    private static boolean isEmptyMap(Map<String, String> map) {
        return map == null || map.isEmpty();
    }

    public static JSONObject copyJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            linkedList.add(keys.next());
        }
        try {
            return new JSONObject(jSONObject, (String[]) linkedList.toArray(new String[0]));
        } catch (Exception e2) {
            e2.printStackTrace();
            return jSONObject;
        }
    }

    public static void onEventV3Bundle(final String str, final Bundle bundle) {
        final long currentTimeMillis = System.currentTimeMillis();
        TeaThread.getInst().ensureTeaThreadLite(new Runnable() { // from class: com.ss.android.common.lib.AppLogNewUtils.3
            @Override // java.lang.Runnable
            public void run() {
                if (o.isEmpty(str)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("_event_v3", 1);
                    jSONObject.put(AppLogNewUtils.EVENT_RESERVED_FIELD_TIME_STAMP, currentTimeMillis);
                    Bundle bundle2 = bundle;
                    if (bundle2 != null) {
                        for (String str2 : bundle2.keySet()) {
                            jSONObject.put(str2, bundle.get(str2));
                        }
                    }
                    String abSDKVersion = AppLog.getAbSDKVersion();
                    if (!TextUtils.isEmpty(abSDKVersion)) {
                        jSONObject.put("ab_sdk_version", abSDKVersion);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                AppLog.onEvent((Context) null, "event_v3", str, (String) null, 0L, 0L, jSONObject);
            }
        });
    }

    public static void onInternalEventV3(String str, JSONObject jSONObject, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            b.w("both second appid and second app name is empty, return");
            return;
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String str5 = SECOND_APP_PREFIX + str;
        try {
            jSONObject.put("params_for_special", SECOND_APP_VALUE);
            jSONObject.put(SECOND_APP_ID_KEY, str2);
            jSONObject.put(SECOND_APP_NAME_KEY, str3);
            jSONObject.put(SECOND_APP_PRODUCT_TYPE_KEY, str4);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        onEventV3(str5, jSONObject);
    }

    public static void onInternalEventV3(String str, Bundle bundle, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            b.w("both second appid and second app name is empty, return");
            return;
        }
        JSONObject jSONObject = null;
        String str5 = SECOND_APP_PREFIX + str;
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        for (String str6 : bundle.keySet()) {
                            jSONObject2.put(str6, bundle.get(str6));
                        }
                        jSONObject2.put("params_for_special", SECOND_APP_VALUE);
                        jSONObject2.put(SECOND_APP_ID_KEY, str2);
                        jSONObject2.put(SECOND_APP_NAME_KEY, str3);
                        jSONObject2.put(SECOND_APP_PRODUCT_TYPE_KEY, str4);
                        jSONObject = jSONObject2;
                    } catch (Throwable th) {
                        th = th;
                        jSONObject = jSONObject2;
                        th.printStackTrace();
                        onEventV3(str5, jSONObject);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        onEventV3(str5, jSONObject);
    }
}
