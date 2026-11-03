package com.bytedance.android.service.manager.push.notification;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
/* loaded from: classes.dex */
public class INotificationMonitorServiceImplOfMock implements INotificationMonitorService {
    @Override // com.bytedance.android.service.manager.push.notification.INotificationMonitorService
    public void onPendingIntent(Object obj, Intent intent) {
    }

    @Override // com.bytedance.android.service.manager.push.notification.INotificationMonitorService
    public boolean enableMonitorNotificationShow() {
        Log.w("INotificationMonitorService", "cur enableMonitorNotificationShow method is empty impl in INotificationMonitorServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.push.notification.INotificationMonitorService
    public boolean onForeGroundNotificationShow(ComponentName componentName, Notification notification) {
        Log.w("INotificationMonitorService", "cur onForeGroundNotificationShow method is empty impl in INotificationMonitorServiceImplOfMockClass");
        return false;
    }
}
