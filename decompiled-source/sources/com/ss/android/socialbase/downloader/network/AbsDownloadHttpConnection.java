package com.ss.android.socialbase.downloader.network;

import org.json.JSONObject;
/* loaded from: classes9.dex */
public abstract class AbsDownloadHttpConnection implements IDownloadHttpConnection {
    public String getHostIp() {
        return "";
    }

    public String getRequestLog() {
        return "";
    }

    public void monitorNetworkInfo(JSONObject jSONObject, boolean z) {
    }

    public void onThrowable(Throwable th) {
    }

    public void setThrottleNetSpeedWhenRunning(long j2) {
    }
}
