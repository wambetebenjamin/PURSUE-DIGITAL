package com.bytedance.android.service.manager.push.notification;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Intent;
/* loaded from: classes.dex */
public interface INotificationMonitorService {
    boolean enableMonitorNotificationShow();

    boolean onForeGroundNotificationShow(ComponentName componentName, Notification notification);

    void onPendingIntent(Object obj, Intent intent);
}
