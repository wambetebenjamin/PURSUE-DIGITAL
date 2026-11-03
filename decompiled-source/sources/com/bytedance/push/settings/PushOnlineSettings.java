package com.bytedance.push.settings;

import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
@com.bytedance.push.settings.annotation.f(storageKey = PushMultiProcessSharedProvider.SP_CONFIG_NAME, supportMultiProcess = true)
/* loaded from: classes2.dex */
public interface PushOnlineSettings extends ISettings {
    public static final String KEY_ENABLE_HW_ANALYTICS = "enable_hw_analytics";
    public static final String KEY_ENABLE_PASS_THROUGH_REDBADGE_SHOW = "enable_pass_through_redbadge_show";
    public static final String KEY_ENABLE_REDBADGE_AUTO_DISMISS = "enable_redbadge_auto_dismiss";
    public static final String KEY_REMOVE_AUTO_BOOT = "remove_auto_boot_v2";
    public static final String KEY_UPDATE_FRONTIER_SETTING_INTERVAL = "frontier_update_setting_interval";
    public static final String KEY_UPLOAD_HW_INFO_INTERVAL = "upload_hw_device_info_interval";
    public static final String KEY_WAKEUP_SUPPORT_STRATEGY = "wakeup_support_strategy";

    com.bytedance.push.settings.pull.b A();

    com.bytedance.push.settings.statistics.b a();

    void a(com.bytedance.push.settings.assosiation.start.b bVar);

    void a(com.bytedance.push.settings.notification.f fVar);

    void a(boolean z);

    com.bytedance.push.settings.client.intelligence.b b();

    com.bytedance.push.settings.message.unduplicate.b c();

    int checkSign();

    com.bytedance.push.settings.delay.start.b d();

    com.bytedance.push.settings.assosiation.start.b e();

    boolean enableHwAnalytics();

    boolean enableMonitorAssociationStart();

    boolean enablePassThroughRedbadgeShow();

    boolean enableRedbadgeAutoDismiss();

    void enableReportClientFeature(boolean z);

    boolean enableReportClientFeature();

    boolean enableRestrictUpdateToken();

    boolean enableStartPushProcess();

    String f();

    int forbidSetAlias();

    boolean g();

    int getPullApiStrategy();

    int getReceiverMessageWakeupScreenTime();

    String getRedBadgeStrategy();

    long getRequestSettingsInterval();

    long getUpdateFrontierSettingIntervalTimeInMinute();

    long getUpdateSenderIntervalTimeInMs();

    long getUpdateTokenIntervalInSecond();

    long getUploadHwDeviceInfoTimeInMinute();

    long getUploadSwitchInterval();

    int getWakeUpStrategy();

    boolean h();

    boolean i();

    boolean isAllowSettingsNotifyEnable();

    boolean isReceiverMessageWakeupScreen();

    long j();

    String k();

    boolean killPushProcessWhenStopService();

    boolean l();

    boolean m();

    int n();

    boolean needControlFlares();

    int notificationSmallIconStyle();

    boolean o();

    boolean p();

    boolean passThoughUseNewActivity();

    boolean q();

    boolean r();

    boolean removeAllAutoBoot();

    boolean removeUmengAutoBoot();

    String s();

    void setAllowSettingsNotifyEnable(boolean z);

    void setEnableHwAnalytics(boolean z);

    void setIsShutPushOnStopService(boolean z);

    void setPullApiStrategy(int i2);

    void setReceiverMessageWakeupScreen(boolean z);

    void setReceiverMessageWakeupScreenTime(int i2);

    void setRedBadgeStrategy(String str);

    void setRequestSenderInterval(long j2);

    void setUpdateFrontierSettingIntervalTimeInMinute(long j2);

    void setUpdateTokenIntervalInSecond(long j2);

    void setUploadHwDeviceInfoIntervalTimeInMinute(long j2);

    com.bytedance.push.settings.notification.e t();

    com.bytedance.push.settings.notification.c u();

    com.bytedance.push.settings.monitor.b v();

    boolean w();

    com.bytedance.push.settings.msg.callback.b x();

    boolean y();

    com.bytedance.push.settings.notification.f z();
}
