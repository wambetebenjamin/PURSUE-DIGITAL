package com.bytedance.ttgame.core;

import android.text.TextUtils;
import com.bytedance.framwork.core.sdkmonitor.h;
import com.bytedance.ttgame.core.applog.ApplogUtils;
import com.google.gson.annotations.SerializedName;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class SdkConfig {
    public static final String APPLOG_CHANNEL = "GooglePlay";
    public static final String BSDK_CHANNEL_ID = "bsdkintl";
    public static final String BYTE_CN_CHANNEL_ID = "bsdk";
    @SerializedName("account_use_privatization_host")
    public boolean account_use_privatization_host;
    @SerializedName("app_id")
    public String appId;
    @SerializedName("app_name")
    public String appName;
    @SerializedName("channel")
    public String channel;
    @SerializedName("channel_data")
    public String channel_data;
    @SerializedName("clean_app")
    public boolean cleanApp;
    @SerializedName("device_register_retry_count")
    public int deviceRegisterRetryCount;
    @SerializedName("disable_device_identifier_collection")
    public boolean disableDeviceIdentifierCollection;
    @SerializedName("download_source")
    public String downloadSource;
    @SerializedName("fake_app_id")
    public String fakeAppId;
    @SerializedName("gaid_get_timeout")
    public long gaidGetTimeout;
    @SerializedName(ApplogUtils.GAME_ID)
    public String gameId;
    @SerializedName("gpm_id")
    public String gpmId;
    @SerializedName("gsdk_init_optimization")
    public boolean gsdkInitOptimization;
    @SerializedName("gsdk_init_timeout")
    public long gsdkInitTimeout;
    @SerializedName("has_splash")
    public boolean hasSplash;
    @SerializedName("is_need_privacy_protection")
    public boolean isNeedPrivacyProtection;
    @SerializedName("is_need_service_mouth")
    public String isNeedServiceMouth;
    @SerializedName("is_private")
    public boolean isPrivate;
    @SerializedName("launch_activity")
    public String launchActivityName;
    @SerializedName("login_channel")
    public String loginChannel;
    @SerializedName("is_boe")
    public boolean mIsBoe;
    @SerializedName("is_debug")
    public boolean mIsDebug;
    @SerializedName("is_sandbox")
    public boolean mIsSandBox;
    @SerializedName("use_packet_channel")
    public boolean mUsePacketChannel;
    @SerializedName("main_activity")
    public String mainActivityName;
    @SerializedName("open_launch_trace")
    public boolean openLaunchTrace;
    @SerializedName(h.HOST_APP_PACKAGE_NAME)
    public String packageName;
    @SerializedName("pay_channel")
    public String payChannel;
    public JSONObject rawConfig;
    @SerializedName("screen_orientation")
    public String screenOrientation;
    @SerializedName("server_region")
    public int serverRegion;
    @SerializedName("splash_interval")
    public int splashInterval;
    @SerializedName("sub_channel")
    public String subChannel;
    @SerializedName("update_channel")
    public String updateChannel;
    @SerializedName("update_version_code")
    public int updateVersionCode;
    @SerializedName("device_register_optimization")
    public boolean deviceRegisterOptimization = true;
    @SerializedName("ttnet_params_opt")
    public boolean ttnetParamsOpt = false;
    @SerializedName("is_need_visitor")
    public boolean isNeedVisitor = true;
    @SerializedName("gsdk_request_cloud_no_db_default")
    public boolean gsdk_request_cloud_no_db_default = true;
    @SerializedName("db_data_need_migrated")
    public boolean db_data_need_migrated = true;

    public String getChannelOp() {
        return APPLOG_CHANNEL.equals(this.channel) ? "bsdkintl" : this.channel;
    }

    public String getLoginChannelOp() {
        return TextUtils.isEmpty(this.loginChannel) ? getChannelOp() : this.loginChannel;
    }

    public String getPayChannelOp() {
        if (TextUtils.isEmpty(this.payChannel)) {
            return getChannelOp();
        }
        return this.payChannel;
    }

    public boolean isBsdkChannel() {
        return BYTE_CN_CHANNEL_ID.equals(getChannelOp()) || "bsdkintl".equals(getChannelOp());
    }
}
