package com.bytedance.hades.downloader.api;

import android.content.Context;
import com.ss.android.common.lib.AppLogNewUtils;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public abstract class DownloaderDepend {
    public boolean canLoadSo() {
        return true;
    }

    public abstract String getAppChannel();

    public abstract String getAppId();

    public abstract Context getContext();

    public abstract String getDeviceId();

    public String getDownloadSetting() {
        return "";
    }

    public String getHadesSoPath() {
        return null;
    }

    public abstract int getServerRegion();

    public void reportEvent(String str, JSONObject jSONObject) {
        try {
            AppLogNewUtils.onEventV3(str, jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
