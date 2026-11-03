package com.bytedance.android.service.manager.push.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.bytedance.android.push.service.manager.annotation.a;
import org.json.JSONObject;
@a
/* loaded from: classes.dex */
public interface IPushNotificationService {
    IPushNotification buildNotification(Context context, Intent intent, Notification.Builder builder, NotificationBody notificationBody);

    IPushNotification buildNotification(Context context, Intent intent, Notification.Builder builder, NotificationBody notificationBody, Notification notification);

    IPushNotification buildNotification(Context context, Intent intent, NotificationBody notificationBody, Notification notification);

    PendingIntent getNotificationDeleteIntent(long j2);

    PendingIntent getNotificationDeleteIntent(long j2, JSONObject jSONObject);

    int getNotificationNum(Context context);

    boolean isClickByBanner(long j2);

    boolean isSupportProxyNotification();

    void setAsyncImageDownloader(AsyncImageDownloader asyncImageDownloader);

    void showNotification(Context context, Intent intent, NotificationBody notificationBody);

    boolean tryShowPushNotification(Context context, Intent intent, NotificationBody notificationBody);
}
