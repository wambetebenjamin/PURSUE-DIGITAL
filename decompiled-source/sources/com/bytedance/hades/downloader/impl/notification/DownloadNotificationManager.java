package com.bytedance.hades.downloader.impl.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.SparseArray;
import com.bytedance.hades.downloader.api.data.DownloadStatus;
/* loaded from: classes2.dex */
public class DownloadNotificationManager {
    public static final int NOTIFICATION_FLAG__ONLY_BACKGROUND = 2;
    public static final int NOTIFICATION_FLAG__SHOW_NORMAL = 1;
    public static final int NOTIFICATION_FLAG__SHOW_TOAST = 4;
    private static volatile DownloadNotificationManager instance;
    private final SparseArray<NotificationItem> notificationItemArray = new SparseArray<>();
    private final a mDownloadNotificationHandler = new a();
    private Boolean notificationEnabled = null;

    private DownloadNotificationManager() {
    }

    public static DownloadNotificationManager getInstance() {
        if (instance == null) {
            synchronized (DownloadNotificationManager.class) {
                if (instance == null) {
                    instance = new DownloadNotificationManager();
                }
            }
        }
        return instance;
    }

    public void addNotification(int i2, NotificationItem notificationItem) {
        if (i2 == 0 || notificationItem == null) {
            return;
        }
        synchronized (this.notificationItemArray) {
            this.notificationItemArray.put(i2, notificationItem);
        }
    }

    public boolean areNotificationsEnabled(Context context) {
        Boolean bool = this.notificationEnabled;
        if (bool == null) {
            a aVar = this.mDownloadNotificationHandler;
            aVar.getClass();
            bool = Boolean.valueOf(Build.VERSION.SDK_INT >= 24 ? aVar.a(context).areNotificationsEnabled() : true);
            this.notificationEnabled = bool;
        }
        return bool.booleanValue();
    }

    public void cancel(Context context, int i2) {
        if (context == null || i2 == 0) {
            return;
        }
        this.mDownloadNotificationHandler.a(context, i2);
    }

    public void cancelAllNotification(Context context) {
        int size;
        int[] iArr;
        int i2;
        synchronized (this.notificationItemArray) {
            size = this.notificationItemArray.size();
            iArr = new int[size];
            for (int i3 = 0; i3 < size; i3++) {
                iArr[i3] = this.notificationItemArray.keyAt(i3);
            }
            this.notificationItemArray.clear();
        }
        a aVar = this.mDownloadNotificationHandler;
        if (aVar.f9412a == null || context == null || size == 0) {
            return;
        }
        NotificationManager a2 = aVar.a(context);
        synchronized (aVar.f9413b) {
            for (int i4 = 0; i4 < size; i4++) {
                aVar.f9413b.remove(iArr[i4]);
            }
        }
        for (i2 = 0; i2 < size; i2++) {
            int i5 = iArr[i2];
            if (a2 != null) {
                aVar.a(a2, i5, true);
            }
        }
    }

    public void cancelNotification(Context context, int i2) {
        removeNotification(i2);
        cancel(context, i2);
    }

    public void flushNotificationEnabled() {
        this.notificationEnabled = null;
    }

    public SparseArray<NotificationItem> getAllNotificationItems() {
        SparseArray<NotificationItem> sparseArray;
        synchronized (this.notificationItemArray) {
            sparseArray = this.notificationItemArray;
        }
        return sparseArray;
    }

    public NotificationItem getNotificationItem(int i2) {
        NotificationItem notificationItem;
        if (i2 == 0) {
            return null;
        }
        synchronized (this.notificationItemArray) {
            notificationItem = this.notificationItemArray.get(i2);
        }
        return notificationItem;
    }

    public boolean hasDownloadingNotification() {
        synchronized (this.notificationItemArray) {
            SparseArray<NotificationItem> sparseArray = this.notificationItemArray;
            if (sparseArray != null && sparseArray.size() != 0) {
                for (int i2 = 0; i2 < this.notificationItemArray.size(); i2++) {
                    NotificationItem valueAt = this.notificationItemArray.valueAt(i2);
                    if (valueAt != null && DownloadStatus.isDownloadingStatus(valueAt.getStatus())) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
    }

    public void notify(Context context, int i2, int i3, Notification notification) {
        if (context == null || i2 == 0 || notification == null) {
            return;
        }
        this.mDownloadNotificationHandler.a(context, i2, notification, i3);
    }

    public NotificationItem removeNotification(int i2) {
        NotificationItem notificationItem;
        if (i2 == 0) {
            return null;
        }
        synchronized (this.notificationItemArray) {
            notificationItem = this.notificationItemArray.get(i2);
            if (notificationItem != null) {
                this.notificationItemArray.remove(i2);
            }
        }
        return notificationItem;
    }
}
