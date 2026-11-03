package com.bytedance.push.interfaze;

import android.app.NotificationChannel;
/* compiled from: IMonitor.java */
/* loaded from: classes2.dex */
public interface i extends com.bytedance.push.e {
    void a(int i2, String str, int i3, int i4);

    void a(long j2);

    void a(NotificationChannel notificationChannel);

    void a(boolean z, int i2);

    void init();

    void markOuterSwitchStatusFailed(int i2, String str);

    void markOuterSwitchStatusSuccess();

    void markOuterSwitchUploadFailed(int i2, String str);

    void markOuterSwitchUploadSuccess();

    void markUpdateSenderFailed(int i2, int i3, String str, String str2);

    void markUpdateSenderSuccess();

    void monitorDecryptResult(int i2, int i3);

    void monitorEvent(String str, String str2);

    void monitorRegisterSender(int i2);

    void monitorRegisterSenderFailed(int i2, int i3, String str, String str2);

    void monitorRegisterSenderSuccess(int i2);

    void monitorSenderSupport(boolean z, String str);

    void monitorStart();
}
