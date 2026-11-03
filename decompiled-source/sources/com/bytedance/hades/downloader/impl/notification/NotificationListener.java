package com.bytedance.hades.downloader.impl.notification;

import android.content.Context;
/* loaded from: classes2.dex */
public class NotificationListener implements INotificationListener {
    private final Context mContext;
    private final int mId;

    public NotificationListener(Context context, int i2) {
        this.mContext = context;
        this.mId = i2;
    }

    private NotificationItem obtainNotificationItem(NotificationInfo notificationInfo) {
        NotificationItem notificationItem = DownloadNotificationManager.getInstance().getNotificationItem(this.mId);
        if (notificationItem == null) {
            Context context = this.mContext;
            NotificationItem notificationItem2 = new NotificationItem(context, this.mId, notificationInfo.getNotificationName(context));
            DownloadNotificationManager.getInstance().addNotification(this.mId, notificationItem2);
            return notificationItem2;
        }
        return notificationItem;
    }

    private void updateNotification(NotificationInfo notificationInfo) {
        if (notificationInfo == null) {
            return;
        }
        NotificationItem obtainNotificationItem = obtainNotificationItem(notificationInfo);
        obtainNotificationItem.setTotalBytes(notificationInfo.totalBytes);
        if (notificationInfo.status == -3) {
            obtainNotificationItem.setCurBytes(notificationInfo.totalBytes);
        } else {
            obtainNotificationItem.setCurBytes(notificationInfo.curBytes);
        }
        obtainNotificationItem.refreshStatus(notificationInfo.status);
    }

    private void updateNotificationProgress(NotificationInfo notificationInfo) {
        if (notificationInfo == null) {
            return;
        }
        obtainNotificationItem(notificationInfo).refreshProgress(notificationInfo.curBytes, notificationInfo.totalBytes);
    }

    @Override // com.bytedance.hades.downloader.impl.notification.INotificationListener
    public void OnCancel(NotificationInfo notificationInfo) {
        DownloadNotificationManager.getInstance().cancelNotification(this.mContext, this.mId);
    }

    @Override // com.bytedance.hades.downloader.impl.notification.INotificationListener
    public void OnFail(NotificationInfo notificationInfo) {
        int i2 = notificationInfo.bizType;
        if (i2 == 0) {
            updateNotification(notificationInfo);
        } else if (i2 == 2 || i2 == 1) {
            DownloadNotificationManager.getInstance().cancelNotification(this.mContext, this.mId);
        }
    }

    @Override // com.bytedance.hades.downloader.impl.notification.INotificationListener
    public void OnPause(NotificationInfo notificationInfo) {
        int i2 = notificationInfo.bizType;
        if (i2 == 0) {
            updateNotification(notificationInfo);
        } else if (i2 == 2 || i2 == 1) {
            DownloadNotificationManager.getInstance().cancelNotification(this.mContext, this.mId);
        }
    }

    @Override // com.bytedance.hades.downloader.impl.notification.INotificationListener
    public void OnProgress(NotificationInfo notificationInfo) {
        updateNotificationProgress(notificationInfo);
    }

    @Override // com.bytedance.hades.downloader.impl.notification.INotificationListener
    public void OnStart(NotificationInfo notificationInfo) {
        updateNotification(notificationInfo);
    }

    @Override // com.bytedance.hades.downloader.impl.notification.INotificationListener
    public void OnSuccess(NotificationInfo notificationInfo) {
        updateNotification(notificationInfo);
    }
}
