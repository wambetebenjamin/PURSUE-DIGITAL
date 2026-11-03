package com.ss.android.message.log;

import android.content.Context;
import android.os.Bundle;
import com.bytedance.common.utility.concurrent.e;
import com.bytedance.common.utility.o;
import com.bytedance.push.utils.h;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: PushLog.java */
/* loaded from: classes9.dex */
public class c {
    public static final String CATEGORY = "event_v1";
    public static final String EVENT_LABEL_TEST = "click_headline";
    public static final String EVENT_SIGN = "_event_v3";
    public static final String EVENT_TAG_TEST1 = "go_detail";
    public static final String EVENT_TAG_TEST2 = "video_play";
    public static final String EVENT_V3_CATEGORY = "event_v3";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_EXT_JSON = "ext_json";
    public static final String KEY_EXT_VALUE = "ext_value";
    public static final String KEY_LABEL = "label";
    public static final String KEY_TAG = "tag";
    public static final String KEY_VALUE = "value";

    public static void onEvent(final Context context, final String str, final String str2, final String str3, final long j2, final long j3, final JSONObject jSONObject) {
        if (o.isEmpty(str) || o.isEmpty(str2)) {
            return;
        }
        if (com.ss.android.message.util.b.isMainProcess(context)) {
            com.ss.android.pushmanager.app.a.getPushHook().onEvent(context, str, str2, str3, j2, j3, jSONObject);
        } else {
            new e() { // from class: com.ss.android.message.log.c.1
                @Override // com.bytedance.common.utility.concurrent.e, java.lang.Runnable
                public void run() {
                    try {
                        b bVar = new b();
                        bVar.category = str;
                        bVar.tag = str2;
                        bVar.label = str3;
                        bVar.value = j2;
                        bVar.ext_value = j3;
                        JSONObject jSONObject2 = jSONObject;
                        if (jSONObject2 != null) {
                            bVar.ext_json = jSONObject2.toString();
                        }
                        if (h.debug()) {
                            h.d("PushLog", "category = " + bVar.category + " tag = " + bVar.tag + " label = " + bVar.label + " value = " + bVar.value + " ext_value = " + j3 + " ext_json = " + bVar.ext_json);
                        }
                        a aVar = a.getInstance(context);
                        if (aVar != null) {
                            aVar.insertEvent(bVar);
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }.start();
        }
    }

    public static void onEvent(Context context, String str, String str2, String str3, long j2, long j3) {
        onEvent(context, str, str2, str3, j2, j3, null);
    }

    public static void onEvent(Context context, String str, String str2, JSONObject... jSONObjectArr) {
        if (jSONObjectArr != null && jSONObjectArr.length > 0) {
            onEvent(context, "event_v1", str, str2, 0L, 0L, jSONObjectArr[0]);
        } else {
            onEvent(context, "event_v1", str, str2, 0L, 0L, null);
        }
    }

    public static void onEvent(Context context, String str, JSONObject... jSONObjectArr) {
        if (jSONObjectArr != null && jSONObjectArr.length > 0) {
            onEvent(context, "event_v1", str, null, 0L, 0L, jSONObjectArr[0]);
        } else {
            onEvent(context, "event_v1", str, null, 0L, 0L, null);
        }
    }

    public static void onEventV3(String str, JSONObject jSONObject) {
        if (o.isEmpty(str)) {
            return;
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        JSONObject jSONObject2 = jSONObject;
        try {
            jSONObject2.put("_event_v3", 1);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        onEvent(null, "event_v3", str, null, 0L, 0L, jSONObject2);
    }

    public static void onEventV3(Context context, String str, JSONObject jSONObject) {
        if (o.isEmpty(str)) {
            return;
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        JSONObject jSONObject2 = jSONObject;
        try {
            jSONObject2.put("_event_v3", 1);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        onEvent(context, "event_v3", str, null, 0L, 0L, jSONObject2);
    }

    public static void onEventV3Bundle(String str, Bundle bundle) {
        if (o.isEmpty(str)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("_event_v3", 1);
            if (bundle != null) {
                for (String str2 : bundle.keySet()) {
                    jSONObject.put(str2, bundle.get(str2));
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        onEvent(null, "event_v3", str, null, 0L, 0L, jSONObject);
    }

    public static void onEventV3Bundle(Context context, String str, Bundle bundle) {
        if (o.isEmpty(str)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("_event_v3", 1);
            if (bundle != null) {
                for (String str2 : bundle.keySet()) {
                    jSONObject.put(str2, bundle.get(str2));
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        onEvent(context, "event_v3", str, null, 0L, 0L, jSONObject);
    }
}
