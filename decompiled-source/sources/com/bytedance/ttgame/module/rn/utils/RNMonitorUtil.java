package com.bytedance.ttgame.module.rn.utils;

import android.text.TextUtils;
import com.bytedance.react.framework.core.BRNManager;
import com.bytedance.react.framework.core.MonitorEventConstant;
import com.bytedance.react.framework.monitor.RNPerformanceMonitorUtils;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class RNMonitorUtil {
    public static void reportPrefetchData(String str, String str2, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("request_url", str);
            jSONObject.put("url", convertNullString(str2));
            jSONObject.put("priority", "" + i2);
        } catch (Exception unused) {
        }
        RNPerformanceMonitorUtils.sendLog(MonitorEventConstant.PREFETCH_DATA_START, jSONObject);
    }

    public static void reportPrefetchDataEnd(String str, String str2, int i2, int i3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("request_url", str);
            jSONObject.put("url", convertNullString(str2));
            jSONObject.put("priority", "" + i2);
            jSONObject.put("status", "" + i3);
        } catch (Exception unused) {
        }
        RNPerformanceMonitorUtils.sendLog(MonitorEventConstant.PREFETCH_DATA_END, jSONObject);
    }

    public static void monitorPrefetchImage(String str, String str2, int i2, int i3) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.put("app_id", BRNManager.newInstance().getAppID());
            jSONObject.put("url", convertNullString(str2));
            jSONObject.put("status", i3);
            jSONObject.put("priority", "" + i2);
            jSONObject3.put("img_url", str);
        } catch (Exception unused) {
        }
        BRNManager.newInstance().reportMonitor(MonitorEventConstant.RN_CACHED_IMAGE, jSONObject, jSONObject2, jSONObject3);
    }

    public static void reportSDKInit() {
        RNPerformanceMonitorUtils.sendLog(MonitorEventConstant.SDK_INIT, new JSONObject());
    }

    public static String convertNullString(String str) {
        return TextUtils.isEmpty(str) ? MonitorEventConstant.NULL_STRING : str;
    }
}
