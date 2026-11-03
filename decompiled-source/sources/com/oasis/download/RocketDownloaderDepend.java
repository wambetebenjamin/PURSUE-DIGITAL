package com.oasis.download;

import android.content.Context;
import com.bytedance.hades.tgrp.api.TGRPDepend;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.oasis.android.OasisApplication;
import com.oasis.channel.ChannelAgent;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class RocketDownloaderDepend extends TGRPDepend {
    protected String Tag;
    private String appChannel;
    private String appId;
    private final Context context;
    private int serverRegion;
    private String setting;

    public RocketDownloaderDepend() {
        this.Tag = "RocketDownloaderDepend";
        this.appId = null;
        this.appChannel = null;
        this.setting = "";
        this.serverRegion = -1;
        this.context = OasisApplication.getOasisApplicationContext();
    }

    public RocketDownloaderDepend(JSONObject jSONObject) {
        this();
        this.appId = jSONObject.optString(RemoteConfigConstants.RequestFieldKey.APP_ID);
        this.appChannel = jSONObject.optString("appChannel");
        this.setting = jSONObject.optString("setting");
        this.serverRegion = jSONObject.optInt("serverRegion", -999999);
    }

    @Override // com.bytedance.hades.downloader.api.DownloaderDepend
    public Context getContext() {
        return this.context;
    }

    @Override // com.bytedance.hades.downloader.api.DownloaderDepend
    public String getAppId() {
        String str = this.appId;
        if (str == null || str.isEmpty()) {
            this.appId = ChannelAgent.getInstance().getAppId();
        }
        return this.appId;
    }

    @Override // com.bytedance.hades.downloader.api.DownloaderDepend
    public String getAppChannel() {
        String str = this.appChannel;
        if (str == null || str.isEmpty()) {
            return ChannelAgent.getInstance().getChannelOp();
        }
        return this.appChannel;
    }

    @Override // com.bytedance.hades.downloader.api.DownloaderDepend
    public String getDeviceId() {
        return ChannelAgent.getInstance().getDeviceID();
    }

    @Override // com.bytedance.hades.downloader.api.DownloaderDepend
    public int getServerRegion() {
        if (this.serverRegion < 0) {
            this.serverRegion = ChannelAgent.getInstance().getServerRegion();
        }
        return this.serverRegion;
    }

    @Override // com.bytedance.hades.downloader.api.DownloaderDepend
    public String getDownloadSetting() {
        String str = this.setting;
        if (str == null || str.isEmpty()) {
            this.setting = "";
        }
        return this.setting;
    }
}
