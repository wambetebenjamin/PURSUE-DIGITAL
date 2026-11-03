package com.oasis.download;

import android.content.Context;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class RocketHadesDownloaderDepend extends RocketDownloaderDepend {
    public RocketHadesDownloaderDepend() {
        this.Tag = "RocketHadesDownloaderDepend";
    }

    public RocketHadesDownloaderDepend(JSONObject jSONObject) {
        super(jSONObject);
        this.Tag = "RocketTGRPDownloaderDepend";
    }

    @Override // com.oasis.download.RocketDownloaderDepend, com.bytedance.hades.downloader.api.DownloaderDepend
    public Context getContext() {
        return super.getContext();
    }

    @Override // com.oasis.download.RocketDownloaderDepend, com.bytedance.hades.downloader.api.DownloaderDepend
    public String getAppId() {
        return super.getAppId();
    }

    @Override // com.oasis.download.RocketDownloaderDepend, com.bytedance.hades.downloader.api.DownloaderDepend
    public String getAppChannel() {
        return super.getAppChannel();
    }

    @Override // com.oasis.download.RocketDownloaderDepend, com.bytedance.hades.downloader.api.DownloaderDepend
    public String getDeviceId() {
        return super.getDeviceId();
    }

    @Override // com.oasis.download.RocketDownloaderDepend, com.bytedance.hades.downloader.api.DownloaderDepend
    public int getServerRegion() {
        return super.getServerRegion();
    }

    @Override // com.oasis.download.RocketDownloaderDepend, com.bytedance.hades.downloader.api.DownloaderDepend
    public String getDownloadSetting() {
        return super.getDownloadSetting();
    }
}
