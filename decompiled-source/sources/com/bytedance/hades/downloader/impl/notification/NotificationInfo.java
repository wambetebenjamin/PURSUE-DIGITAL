package com.bytedance.hades.downloader.impl.notification;

import android.content.Context;
import android.text.TextUtils;
/* loaded from: classes2.dex */
public class NotificationInfo {
    public int bizType;
    public long curBytes;
    public String fileName;
    public String notificationName;
    public int status;
    public long totalBytes;

    public NotificationInfo() {
    }

    public String getNotificationName(Context context) {
        String str;
        if (TextUtils.isEmpty(this.notificationName)) {
            if (!TextUtils.isEmpty(this.fileName)) {
                this.notificationName = this.fileName;
            } else {
                try {
                    str = context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    str = "";
                }
                this.notificationName = str;
            }
        }
        return this.notificationName;
    }

    public NotificationInfo(long j2, long j3, int i2, int i3) {
        this.curBytes = j2;
        this.totalBytes = j3;
        this.status = i2;
        this.bizType = i3;
    }
}
