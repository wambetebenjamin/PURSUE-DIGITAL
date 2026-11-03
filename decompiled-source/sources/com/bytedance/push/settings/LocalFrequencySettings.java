package com.bytedance.push.settings;

import java.util.List;
@com.bytedance.push.settings.annotation.f(storageKey = "ttpush_local_setting")
/* loaded from: classes2.dex */
public interface LocalFrequencySettings extends ILocalSettings {
    int a();

    void a(int i2);

    String getLastNotificationChannelStatus();

    long getLastRequestSettingsTime();

    String getLastUpdateSenderAlias();

    String getLastUpdateSenderChannel();

    String getLastUpdateSenderDid();

    long getLastUpdateSenderTime();

    String getLastUpdateSenderUpdateVersionCode();

    String getLastUpdateSenderVersionCode();

    List<com.bytedance.push.model.a> getRevokeRidList();

    String getSupportSender();

    int getSystemPushEnable();

    List<com.bytedance.push.model.d> getTokenCache();

    long getUploadSwitchTs();

    boolean isLastSendNotifyEnableSucc();

    void setLastNotificationChannelStatus(String str);

    void setLastRequestSettingsTime(long j2);

    void setLastSendNotifyEnableSucc(boolean z);

    void setLastUpdateSenderAlias(String str);

    void setLastUpdateSenderDid(String str);

    void setLastUpdateSenderTime(long j2);

    void setLastUpdateSenderUpdateChannel(String str);

    void setLastUpdateSenderUpdateVersionCode(String str);

    void setLastUpdateSenderVersionCode(String str);

    void setRevokeRidList(List<com.bytedance.push.model.a> list);

    void setSupportSender(String str);

    void setSystemPushEnable(int i2);

    void setTokenCache(List<com.bytedance.push.model.d> list);

    void setUploadSwitchTs(long j2);
}
