package com.bytedance.push.settings;

import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import java.util.List;
@com.bytedance.push.settings.annotation.f(storageKey = PushMultiProcessSharedProvider.SP_CONFIG_NAME, supportMultiProcess = true)
/* loaded from: classes2.dex */
public interface AliveOnlineSettings extends ISettings {
    public static final String SERVER_KEY_ALARM_WAKE_UP = "ttpush_is_close_alarm_wakeup";
    public static final String SERVER_KEY_JOB_SERVICE = "ttpush_allow_push_job_service";
    public static final String SERVER_KEY_NOTIFY_STICKY = "ttpush_is_notify_service_stick";
    public static final String SERVER_KEY_PUSH_DAEMON = "ttpush_i18n_allow_push_daemon_monitor";

    boolean allowOffALive();

    List<String> getAllowList();

    int getJobScheduleWakeUpIntervalSecond();

    String getUninstallQuestionUrl();

    boolean isAllowCloseBootReceiver();

    boolean isAllowPushDaemonMonitor();

    boolean isAllowPushJobService();

    boolean isCloseAlarmWakeup();

    boolean isMiuiCloseDaemon();

    boolean isNotifyServiceStick();

    boolean isUseCNativeProcessKeepAlive();

    boolean isUseStartForegroundNotification();

    void setAllowCloseBootReceiver(boolean z);

    void setAllowOffAlive(boolean z);

    void setAllowPushDaemonMonitor(boolean z);

    void setAllowPushJobService(boolean z);

    void setCloseAlarmWakeup(boolean z);

    void setIsUseCNativeProcessKeepAlive(boolean z);

    void setJobScheduleWakeUpIntervalSecond(int i2);

    void setMiuiCloseDaemon(boolean z);

    void setNotifyServiceStick(boolean z);

    void setUninstallQuestionUrl(String str);

    void setUseStartForegroundNotification(boolean z);
}
