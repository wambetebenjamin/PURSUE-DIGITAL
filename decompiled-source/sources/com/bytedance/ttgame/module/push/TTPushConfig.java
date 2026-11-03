package com.bytedance.ttgame.module.push;

import com.google.gson.annotations.SerializedName;
import com.ss.android.pushmanager.e;
/* loaded from: classes6.dex */
public class TTPushConfig {
    @SerializedName("app_domestic_host")
    public String appDomesticHost;
    @SerializedName("app_oversea_host")
    public String appOverseaHost;
    @SerializedName("enable")
    public boolean enable;
    @SerializedName("push_app_name")
    public String pushAppName;
    @SerializedName("push_black_list")
    public String pushBlackList;
    @SerializedName("push_channel_id")
    public String pushChannelId;
    @SerializedName("push_channel_name")
    public String pushChannelName;
    @SerializedName(e.EVENT_NAME_RED_BADGE_SHOW)
    public boolean redBadgeShow;
    @SerializedName("red_max_show_times")
    public int redMaxShowTimes;
    @SerializedName("red_show_strategy")
    public String redShowStrategy;
    @SerializedName("auto_request_permission")
    public boolean autoRequestPermission = true;
    @SerializedName("enable_foreground_push")
    public boolean enableForegroundPush = true;
    @SerializedName("disable_push_subprocess")
    public boolean disablePushSubprocess = false;

    public String toString() {
        return "TTPushConfig{enable=" + this.enable + ", pushChannelId='" + this.pushChannelId + "', pushChannelName='" + this.pushChannelName + "', pushAppName='" + this.pushAppName + "', pushBlackList='" + this.pushBlackList + "', appOverseaHost='" + this.appOverseaHost + "', redBadgeShow=" + this.redBadgeShow + ", redMaxShowTimes=" + this.redMaxShowTimes + ", redShowStrategy='" + this.redShowStrategy + "', autoRequestPermission='" + this.autoRequestPermission + "', enableForegroundPush='" + this.enableForegroundPush + "', disablePushSubprocess='" + this.disablePushSubprocess + "'}";
    }
}
