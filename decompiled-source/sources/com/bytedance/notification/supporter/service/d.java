package com.bytedance.notification.supporter.service;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import com.bytedance.android.service.manager.push.notification.NotificationBody;
import com.bytedance.notification.model.PushNotification;
/* compiled from: INotificationStyleService.java */
/* loaded from: classes2.dex */
public interface d {
    PushNotification a(Context context, Intent intent, Notification.Builder builder, NotificationBody notificationBody);

    void a(Context context, Notification notification, NotificationBody notificationBody);
}
