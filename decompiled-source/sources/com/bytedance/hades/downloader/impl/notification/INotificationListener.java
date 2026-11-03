package com.bytedance.hades.downloader.impl.notification;
/* loaded from: classes2.dex */
public interface INotificationListener {
    void OnCancel(NotificationInfo notificationInfo);

    void OnFail(NotificationInfo notificationInfo);

    void OnPause(NotificationInfo notificationInfo);

    void OnProgress(NotificationInfo notificationInfo);

    void OnStart(NotificationInfo notificationInfo);

    void OnSuccess(NotificationInfo notificationInfo);
}
