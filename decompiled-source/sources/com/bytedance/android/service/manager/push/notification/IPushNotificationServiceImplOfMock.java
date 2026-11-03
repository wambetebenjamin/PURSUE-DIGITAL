package com.bytedance.android.service.manager.push.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class IPushNotificationServiceImplOfMock implements IPushNotificationService {
    @Override // com.bytedance.android.service.manager.push.notification.IPushNotificationService
    public void setAsyncImageDownloader(AsyncImageDownloader asyncImageDownloader) {
    }

    @Override // com.bytedance.android.service.manager.push.notification.IPushNotificationService
    public void showNotification(Context context, Intent intent, NotificationBody notificationBody) {
    }

    @Override // com.bytedance.android.service.manager.push.notification.IPushNotificationService
    public IPushNotification buildNotification(Context context, Intent intent, NotificationBody notificationBody, Notification notification) {
        Log.w("IPushNotificationService", "cur buildNotification method is empty impl in IPushNotificationServiceImplOfMockClass");
        return new IPushNotificationImplOfMock();
    }

    @Override // com.bytedance.android.service.manager.push.notification.IPushNotificationService
    public IPushNotification buildNotification(Context context, Intent intent, Notification.Builder builder, NotificationBody notificationBody, Notification notification) {
        Log.w("IPushNotificationService", "cur buildNotification method is empty impl in IPushNotificationServiceImplOfMockClass");
        return new IPushNotificationImplOfMock();
    }

    @Override // com.bytedance.android.service.manager.push.notification.IPushNotificationService
    public PendingIntent getNotificationDeleteIntent(long j2, JSONObject jSONObject) {
        Log.w("IPushNotificationService", "cur getNotificationDeleteIntent method is empty impl in IPushNotificationServiceImplOfMockClass");
        return null;
    }

    @Override // com.bytedance.android.service.manager.push.notification.IPushNotificationService
    public PendingIntent getNotificationDeleteIntent(long j2) {
        Log.w("IPushNotificationService", "cur getNotificationDeleteIntent method is empty impl in IPushNotificationServiceImplOfMockClass");
        return null;
    }

    @Override // com.bytedance.android.service.manager.push.notification.IPushNotificationService
    public IPushNotification buildNotification(Context context, Intent intent, Notification.Builder builder, NotificationBody notificationBody) {
        Log.w("IPushNotificationService", "cur buildNotification method is empty impl in IPushNotificationServiceImplOfMockClass");
        return new IPushNotificationImplOfMock();
    }

    @Override // com.bytedance.android.service.manager.push.notification.IPushNotificationService
    public boolean tryShowPushNotification(Context context, Intent intent, NotificationBody notificationBody) {
        Log.w("IPushNotificationService", "cur tryShowPushNotification method is empty impl in IPushNotificationServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.push.notification.IPushNotificationService
    public boolean isSupportProxyNotification() {
        Log.w("IPushNotificationService", "cur isSupportProxyNotification method is empty impl in IPushNotificationServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.push.notification.IPushNotificationService
    public boolean isClickByBanner(long j2) {
        Log.w("IPushNotificationService", "cur isClickByBanner method is empty impl in IPushNotificationServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.push.notification.IPushNotificationService
    public int getNotificationNum(Context context) {
        Log.w("IPushNotificationService", "cur getNotificationNum method is empty impl in IPushNotificationServiceImplOfMockClass");
        return 0;
    }
}
