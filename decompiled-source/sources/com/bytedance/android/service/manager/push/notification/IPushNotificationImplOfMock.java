package com.bytedance.android.service.manager.push.notification;

import android.app.Notification;
import android.util.Log;
/* loaded from: classes.dex */
public class IPushNotificationImplOfMock implements IPushNotification {
    @Override // com.bytedance.android.service.manager.push.notification.IPushNotification
    public void show() {
    }

    @Override // com.bytedance.android.service.manager.push.notification.IPushNotification
    public void show(String str, int i2) {
    }

    @Override // com.bytedance.android.service.manager.push.notification.IPushNotification
    public Notification getNotification() {
        Log.w("IPushNotification", "cur getNotification method is empty impl in IPushNotificationImplOfMockClass");
        return null;
    }
}
