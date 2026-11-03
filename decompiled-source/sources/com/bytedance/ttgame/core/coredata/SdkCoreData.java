package com.bytedance.ttgame.core.coredata;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.ttgame.core.SdkConfig;
import com.ss.android.common.applog.AppLog;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class SdkCoreData extends BaseSdkCoreData implements AppLog.ILogSessionHook {
    private static final SdkCoreData sInstance = new SdkCoreData();
    private volatile String mSessionValue;

    @Override // com.ss.android.common.applog.AppLog.ILogSessionHook
    public void onLogSessionStart(long j2) {
    }

    @Override // com.ss.android.common.applog.AppLog.ILogSessionHook
    public void onLogSessionTerminate(long j2, String str, JSONObject jSONObject) {
    }

    private SdkCoreData() {
    }

    public static SdkCoreData getInstance() {
        return sInstance;
    }

    public void init(Context context, SdkConfig sdkConfig, Map<String, Object> map) {
        super.init(context, sdkConfig);
        this.channelConfig = map;
    }

    public SdkConfig getConfig() {
        return this.config;
    }

    public void setConfig(SdkConfig sdkConfig) {
        this.config = sdkConfig;
        if (TextUtils.isEmpty(sdkConfig.gameId)) {
            sdkConfig.gameId = sdkConfig.appId;
        }
    }

    public Map<String, Object> getChannelConfig() {
        return this.channelConfig;
    }

    public Context getAppContext() {
        return appContext;
    }

    public String getSessionValue() {
        return this.mSessionValue;
    }

    @Override // com.ss.android.common.applog.AppLog.ILogSessionHook
    public void onLogSessionBatchEvent(long j2, String str, JSONObject jSONObject) {
        this.mSessionValue = str;
    }
}
