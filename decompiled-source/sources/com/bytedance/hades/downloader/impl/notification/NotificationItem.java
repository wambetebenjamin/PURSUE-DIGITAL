package com.bytedance.hades.downloader.impl.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;
import com.bytedance.hades.downloader.R;
import com.bytedance.hades.downloader.impl.settings.DownloadSetting;
import com.bytedance.hades.downloader.impl.settings.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import g.hades.downloader.k;
/* loaded from: classes2.dex */
class NotificationItem {
    private static final String NOTIFICATION_GROUP = "hades";
    private Context mContext;
    private long mCurBytes;
    private int mId;
    private long mLastTime;
    private int mNotifyDuration = 500;
    private int mStatus;
    private String mTitle;
    private long mTotalBytes;

    public NotificationItem(Context context, int i2, String str) {
        this.mContext = context;
        this.mId = i2;
        this.mTitle = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.app.Notification createNotification() {
        /*
            Method dump skipped, instructions count: 515
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.hades.downloader.impl.notification.NotificationItem.createNotification():android.app.Notification");
    }

    private RemoteViews createRemoteViews() {
        RemoteViews remoteViews = new RemoteViews(this.mContext.getPackageName(), R.layout.download_notification_layout);
        if (Build.VERSION.SDK_INT > 20) {
            try {
                if (k.a(this.mContext)) {
                    remoteViews.setInt(R.id.download_root, "setBackgroundColor", this.mContext.getResources().getColor(R.color.download_notification_material_background_color));
                }
            } catch (Throwable unused) {
            }
        }
        return remoteViews;
    }

    private PendingIntent getClickBtnIntent() {
        Intent intent = new Intent(this.mContext, NotificationService.class);
        intent.setAction("com.bytedance.hades.CLICK_BTN");
        intent.putExtra("id", this.mId);
        intent.putExtra("status", this.mStatus);
        return PendingIntent.getService(this.mContext, this.mId, intent, Build.VERSION.SDK_INT >= 23 ? 201326592 : DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
    }

    private PendingIntent getClickContentIntent() {
        Intent launchIntentForPackage;
        if (DownloadSetting.getInstance().optInt(DownloadSettingKeys.NOTIFICATION_CLICK, 1) >= 0) {
            launchIntentForPackage = new Intent(this.mContext, NotificationClickActivity.class);
        } else {
            launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName());
        }
        return PendingIntent.getActivity(this.mContext, this.mId, launchIntentForPackage, Build.VERSION.SDK_INT >= 23 ? 201326592 : DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
    }

    private int getSmallIcon() {
        return 17301634;
    }

    private String getString(int i2) {
        return this.mContext.getResources().getString(i2);
    }

    private void notify(Notification notification) {
        if (notification == null) {
            return;
        }
        DownloadNotificationManager.getInstance().notify(this.mContext, this.mId, this.mStatus, notification);
    }

    public int getId() {
        return this.mId;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void refreshProgress(long j2, long j3) {
        long currentTimeMillis = System.currentTimeMillis();
        long j4 = this.mLastTime;
        if (j4 == 0 || currentTimeMillis - j4 >= this.mNotifyDuration) {
            this.mLastTime = currentTimeMillis;
            this.mCurBytes = j2;
            this.mTotalBytes = j3;
            this.mStatus = 4;
            updateNotification();
        }
    }

    public void refreshStatus(int i2) {
        this.mStatus = i2;
        updateNotification();
    }

    public void setCurBytes(long j2) {
        this.mCurBytes = j2;
    }

    public void setTotalBytes(long j2) {
        this.mTotalBytes = j2;
    }

    public void updateNotification() {
        if (this.mContext == null) {
            return;
        }
        try {
            notify(createNotification());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
