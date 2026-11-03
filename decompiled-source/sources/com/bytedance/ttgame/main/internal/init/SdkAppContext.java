package com.bytedance.ttgame.main.internal.init;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.reader_apk.d;
import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.framework.module.util.StringUtil;
import com.bytedance.ttgame.sdk.module.utils.AppInfoUtil;
import com.bytedance.ttgame.sdk.module.utils.NumberUtils;
import com.ss.android.common.a;
/* loaded from: classes3.dex */
public class SdkAppContext implements a {
    private final Context mContext;
    private SdkConfig sdkConfig;

    @Override // com.ss.android.common.a
    public String getAbClient() {
        return null;
    }

    @Override // com.ss.android.common.a
    public String getAbFeature() {
        return null;
    }

    @Override // com.ss.android.common.a
    public long getAbFlag() {
        return 0L;
    }

    @Override // com.ss.android.common.a
    public String getAbGroup() {
        return null;
    }

    @Override // com.ss.android.common.a
    public String getAbVersion() {
        return null;
    }

    @Override // com.ss.android.common.a
    public String getDeviceId() {
        return "";
    }

    @Override // com.ss.android.common.a
    public String getFeedbackAppKey() {
        return null;
    }

    public SdkAppContext(Context context, SdkConfig sdkConfig) {
        this.mContext = context;
        this.sdkConfig = sdkConfig;
    }

    @Override // com.ss.android.common.a
    public Context getContext() {
        return this.mContext;
    }

    @Override // com.ss.android.common.a
    public String getStringAppName() {
        return AppInfoUtil.getAppName(this.mContext);
    }

    @Override // com.ss.android.common.a
    public String getAppName() {
        return this.sdkConfig.appName;
    }

    @Override // com.ss.android.common.a
    public String getVersion() {
        return AppInfoUtil.getAppVersionName(this.mContext);
    }

    @Override // com.ss.android.common.a
    public String getChannel() {
        String channel = d.getChannel(this.mContext);
        if (TextUtils.isEmpty(channel)) {
            if (!TextUtils.isEmpty(this.sdkConfig.channel_data)) {
                return this.sdkConfig.channel_data;
            }
            if (TextUtils.isEmpty(this.sdkConfig.subChannel)) {
                return StringUtil.isNullOrEmpty(this.sdkConfig.channel) ? SdkConfig.APPLOG_CHANNEL : this.sdkConfig.channel;
            }
            return this.sdkConfig.subChannel;
        }
        return channel;
    }

    @Override // com.ss.android.common.a
    public String getTweakedChannel() {
        String channel = d.getChannel(this.mContext);
        if (TextUtils.isEmpty(channel)) {
            if (!TextUtils.isEmpty(this.sdkConfig.channel_data)) {
                return this.sdkConfig.channel_data;
            }
            if (!TextUtils.isEmpty(this.sdkConfig.subChannel)) {
                return this.sdkConfig.subChannel;
            }
            if (StringUtil.isNullOrEmpty(this.sdkConfig.channel)) {
                return null;
            }
            return this.sdkConfig.channel;
        }
        return channel;
    }

    @Override // com.ss.android.common.a
    public int getVersionCode() {
        return AppInfoUtil.getAppVersionCode(this.mContext);
    }

    @Override // com.ss.android.common.a
    public int getUpdateVersionCode() {
        return this.sdkConfig.updateVersionCode == 0 ? AppInfoUtil.getAppVersionCode(this.mContext) : this.sdkConfig.updateVersionCode;
    }

    @Override // com.ss.android.common.a
    public int getManifestVersionCode() {
        return AppInfoUtil.getAppVersionCode(this.mContext);
    }

    @Override // com.ss.android.common.a
    public String getManifestVersion() {
        return AppInfoUtil.getAppVersionName(this.mContext);
    }

    @Override // com.ss.android.common.a
    public int getAid() {
        return NumberUtils.getInteger(this.sdkConfig.appId, 1807);
    }
}
