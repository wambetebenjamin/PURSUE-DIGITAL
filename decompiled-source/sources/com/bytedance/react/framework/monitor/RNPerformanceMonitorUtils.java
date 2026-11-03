package com.bytedance.react.framework.monitor;

import android.os.Bundle;
import com.bytedance.apm.constant.r;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.react.framework.core.BRNManager;
import com.bytedance.react.framework.core.MonitorEventConstant;
import com.bytedance.ttgame.engine.utils.a;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class RNPerformanceMonitorUtils {
    public static void reportBundleExist(String str, String str2, String str3, String str4, int i2) {
        JSONObject createCommonParams = createCommonParams(str, str2, str3, str4);
        try {
            createCommonParams.put("status", "" + i2);
        } catch (JSONException unused) {
        }
        sendLog(MonitorEventConstant.BUNDLE_EXIST, createCommonParams);
    }

    public static void reportOpenUrlBegin(int i2, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", "" + i2);
            jSONObject.put("url", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        sendLog(RNMonitorUtils.DYNAMIC_RN_OPENED_BEGIN, jSONObject);
    }

    public static void reportOpenUrl(String str, String str2, String str3, String str4, int i2, boolean z) {
        if (z) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("status", "" + i2);
                jSONObject.put("url", str);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            sendLog(RNMonitorUtils.DYNAMIC_RN_OPENED_SUCCESS, jSONObject);
            return;
        }
        JSONObject createCommonParams = createCommonParams(str4, str3, str2, str);
        try {
            createCommonParams.put("status", "" + i2);
        } catch (JSONException unused) {
        }
        sendLog(MonitorEventConstant.OPEN_URL, createCommonParams);
    }

    public static void reportOpenUrlError(String str, String str2, String str3, String str4, int i2, int i3, String str5, boolean z) {
        if (z) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("status", "" + i2);
                jSONObject.put("errorCode", i3);
                jSONObject.put("error", "" + i3);
                jSONObject.put("url", str);
            } catch (JSONException unused) {
            }
            sendLog(RNMonitorUtils.DYNAMIC_RN_OPENED_FAILED, jSONObject);
            return;
        }
        JSONObject createCommonParams = createCommonParams(str4, str3, str2, str);
        try {
            createCommonParams.put("status", "" + i2);
            createCommonParams.put("error_code", "" + i3);
            createCommonParams.put("error_msg", str5);
        } catch (JSONException unused2) {
        }
        sendLog(MonitorEventConstant.OPEN_URL, createCommonParams);
    }

    public static void reportRNViewInitFailed(String str, String str2, String str3, String str4, String str5, int i2, String str6) {
        JSONObject createCommonParams = createCommonParams(str, str2, str3, str4);
        try {
            createCommonParams.put("error_code", "" + i2);
            createCommonParams.put("error_msg", str6);
            createCommonParams.put("window_id", str5);
        } catch (JSONException unused) {
        }
        sendLog(MonitorEventConstant.REACT_VIEW_INIT_FAILED, createCommonParams);
    }

    public static void reportBundleLoad(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(r.STATS_KEY_MODULE_NAME, str2);
            jSONObject.put("module_version", str);
        } catch (JSONException unused) {
        }
        sendLog(MonitorEventConstant.BUNDLE_LOAD_START, jSONObject);
    }

    public static void reportBundleLoadFinish(String str, String str2, long j2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(r.STATS_KEY_MODULE_NAME, str2);
            jSONObject.put("module_version", str);
            jSONObject.put("duration", j2);
        } catch (JSONException unused) {
        }
        sendLog(MonitorEventConstant.BUNDLE_LOAD_END, jSONObject);
    }

    public static void reportBundlePreload(String str, String str2, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(r.STATS_KEY_MODULE_NAME, str2);
            jSONObject.put("module_version", str);
            jSONObject.put("is_preload", "" + i2);
        } catch (JSONException unused) {
        }
        sendLog(MonitorEventConstant.BUNDLE_PRELOAD, jSONObject);
    }

    public static void reportRNViewInitStart(String str, String str2, String str3, String str4, String str5, long j2) {
        JSONObject createCommonParams = createCommonParams(str, str2, str3, str4);
        try {
            createCommonParams.put("duration", j2);
            createCommonParams.put("window_id", str5);
        } catch (JSONException unused) {
        }
        sendLog(MonitorEventConstant.REACT_VIEW_INIT, createCommonParams);
    }

    public static void reportRNViewInitSuccess(String str, String str2, String str3, String str4, String str5, long j2, boolean z) {
        JSONObject createCommonParams = createCommonParams(str, str2, str3, str4);
        try {
            createCommonParams.put("duration", j2);
            createCommonParams.put("window_id", str5);
            createCommonParams.put("is_dynamic", z ? 1 : 0);
        } catch (JSONException unused) {
        }
        sendLog(MonitorEventConstant.REACT_VIEW_INIT_SUCCESS, createCommonParams);
    }

    public static void reportRNViewInitTTI(String str, String str2, String str3, String str4, String str5, long j2) {
        JSONObject createCommonParams = createCommonParams(str, str2, str3, str4);
        try {
            createCommonParams.put("duration", j2);
            createCommonParams.put("window_id", str5);
        } catch (JSONException unused) {
        }
        sendLog(MonitorEventConstant.REACT_VIEW_TTI, createCommonParams);
    }

    public static void reportRNViewClose(String str, String str2, String str3, String str4, String str5) {
        JSONObject createCommonParams = createCommonParams(str, str2, str3, str4);
        try {
            createCommonParams.put("window_id", str5);
        } catch (JSONException unused) {
        }
        sendLog(MonitorEventConstant.REACT_VIEW_CLOSE, createCommonParams);
    }

    public static JSONObject createCommonParams(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("scene", "" + str);
            jSONObject.put("module_version", str2);
            jSONObject.put(r.STATS_KEY_MODULE_NAME, str3);
            jSONObject.put("url", str4);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public static void reportGeckoDownloadStart(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(r.STATS_KEY_MODULE_NAME, str);
            jSONObject.put("module_version", str2);
        } catch (JSONException unused) {
        }
        sendLog(MonitorEventConstant.GECKO_DOWNLOAD_START, jSONObject);
    }

    public static void reportGeckoDownloadFinish(String str, String str2, long j2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(r.STATS_KEY_MODULE_NAME, str);
            jSONObject.put("duration", j2);
            jSONObject.put("module_version", str2);
        } catch (JSONException unused) {
        }
        sendLog(MonitorEventConstant.GECKO_DOWNLOAD_FINISH, jSONObject);
    }

    public static void reportGeckoDownloadFailed(String str, String str2, int i2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(r.STATS_KEY_MODULE_NAME, str);
            jSONObject.put("error_code", "" + i2);
            jSONObject.put("error_msg", str3);
            jSONObject.put("module_version", str2);
        } catch (JSONException unused) {
        }
        sendLog(MonitorEventConstant.GECKO_DOWNLOAD_FAILED, jSONObject);
    }

    public static void sendLog(String str, JSONObject jSONObject) {
        if (RNConfig.USE_RELEASE_BUNDLE) {
            if (jSONObject != null) {
                try {
                    jSONObject.put(RNConfig.GUMIHO_VERSION, BRNManager.newInstance().getGumihoVersion());
                    jSONObject.put("is_split", "" + (RNConfig.BUNDLE_MODE == 3 ? 1 : 0));
                } catch (JSONException unused) {
                }
            }
            BRNManager.newInstance().sendLog(str, jSONObject);
        }
    }

    public static void reportSubBundleLoad(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sub_name", str);
            jSONObject.put("sub_version", str2);
        } catch (JSONException unused) {
        }
        sendLog(MonitorEventConstant.SUB_BUNDLE_LOAD_START, jSONObject);
    }

    public static void reportSubBundleLoadFinish(String str, String str2, long j2, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sub_name", str);
            jSONObject.put("duration", j2);
            jSONObject.put("sub_version", str2);
            jSONObject.put("status", i2);
        } catch (JSONException unused) {
        }
        sendLog(MonitorEventConstant.SUB_BUNDLE_LOAD_FINISH, jSONObject);
    }

    public static void reportPageTTIFromLaunchOptions(String str, long j2, Bundle bundle) {
        String string = bundle.getString(RNConfig.RN_SCENE_TYPE, "0");
        String string2 = bundle.getString(RNConfig.MODULE_VERSION, "");
        String string3 = bundle.getString(RNConfig.RN_URL, "");
        long j3 = bundle.getLong(RNConfig.RN_PAGE_START_TIME, -1L);
        long j4 = j3 != -1 ? j2 - j3 : -1L;
        JSONObject createCommonParams = createCommonParams(string, string2, str, string3);
        a.a(createCommonParams, "duration", j4);
        sendLog(MonitorEventConstant.REACT_VIEW_TTI, createCommonParams);
    }

    public static void reportPageCloseFromLaunchOptions(String str, Bundle bundle) {
        sendLog(MonitorEventConstant.REACT_VIEW_CLOSE, createCommonParams(bundle.getString(RNConfig.RN_SCENE_TYPE, "0"), bundle.getString(RNConfig.MODULE_VERSION, ""), str, bundle.getString(RNConfig.RN_URL, "")));
    }

    public static void reportPageLoadStartFromLaunchOptions(String str, Bundle bundle) {
        JSONObject createCommonParams = createCommonParams(bundle.getString(RNConfig.RN_SCENE_TYPE, "0"), bundle.getString(RNConfig.MODULE_VERSION, ""), str, bundle.getString(RNConfig.RN_URL, ""));
        a.a(createCommonParams, "duration", 0);
        sendLog(MonitorEventConstant.REACT_VIEW_INIT, createCommonParams);
    }

    public static void reportPageLoadFailFromLaunchOptions(String str, long j2, Bundle bundle) {
        JSONObject createCommonParams = createCommonParams(bundle.getString(RNConfig.RN_SCENE_TYPE, "0"), bundle.getString(RNConfig.MODULE_VERSION, ""), str, bundle.getString(RNConfig.RN_URL, ""));
        long j3 = bundle.getLong(RNConfig.RN_PAGE_START_TIME, -1L);
        a.a(createCommonParams, "duration", j3 != -1 ? j2 - j3 : -1L);
        sendLog(MonitorEventConstant.REACT_VIEW_INIT_FAILED, createCommonParams);
    }

    public static void reportPageLoadFinishFromLaunchOptions(String str, long j2, Bundle bundle) {
        JSONObject createCommonParams = createCommonParams(bundle.getString(RNConfig.RN_SCENE_TYPE, "0"), bundle.getString(RNConfig.MODULE_VERSION, ""), str, bundle.getString(RNConfig.RN_URL, ""));
        long j3 = bundle.getLong(RNConfig.RN_PAGE_START_TIME, -1L);
        a.a(createCommonParams, "duration", j3 != -1 ? j2 - j3 : -1L);
        sendLog(MonitorEventConstant.REACT_VIEW_INIT_SUCCESS, createCommonParams);
    }
}
