package com.bytedance.react.framework.web;

import android.content.Context;
import android.content.pm.PackageManager;
import com.bytedance.react.framework.core.BRNManager;
import com.bytedance.ttgame.engine.utils.a;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class RNGeckoMonitorUtil {
    public static final String RN_GECKO_DOWNLOAD_BEGIN = "gumiho_resource_download_event_start";
    public static final String RN_GECKO_DOWNLOAD_FAIL = "gumiho_resource_download_event_end";
    public static final String RN_GECKO_DOWNLOAD_SUCCESS = "gumiho_resource_download_event_end";

    public static int getAppVersionCode() {
        try {
            Context appContext = BRNManager.newInstance().getAppContext();
            return appContext.getApplicationContext().getPackageManager().getPackageInfo(appContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static void monitorRnGeckoDownloadEvent(String str, String str2, String str3, int i2, String str4) {
        JSONObject jSONObject = new JSONObject();
        a.a(jSONObject, "channel_name", str2);
        a.a(jSONObject, "app_id", BRNManager.newInstance().getAppID());
        a.a(jSONObject, "sdk_version", BRNManager.newInstance().getSDKVersion());
        a.a(jSONObject, "game_version", getAppVersionCode());
        a.a(jSONObject, "region", BRNManager.newInstance().getServerRegion());
        a.a(jSONObject, "status", str3);
        a.a(jSONObject, MonitorConstants.STATUS_CODE, i2);
        JSONObject jSONObject2 = new JSONObject();
        a.a(jSONObject2, "device_id", BRNManager.newInstance().getServerDeviceId());
        a.a(jSONObject2, "message", str4);
        BRNManager.newInstance().reportMonitor(str, jSONObject, new JSONObject(), jSONObject2);
    }
}
