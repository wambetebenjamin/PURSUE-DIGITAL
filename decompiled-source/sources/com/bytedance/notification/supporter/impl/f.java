package com.bytedance.notification.supporter.impl;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.bytedance.android.service.manager.push.notification.NotificationBody;
import com.bytedance.notification.NotificationDeleteBroadcastReceiver;
import com.bytedance.push.utils.n;
/* compiled from: NotificationCompliance.java */
/* loaded from: classes2.dex */
public class f implements com.bytedance.notification.supporter.service.f {

    /* renamed from: a  reason: collision with root package name */
    public static final int f9943a = 0;

    /* renamed from: b  reason: collision with root package name */
    public static final int f9944b = 1;

    /* renamed from: c  reason: collision with root package name */
    public static final int f9945c = 2;

    /* renamed from: d  reason: collision with root package name */
    public static final int f9946d = 3;

    /* renamed from: e  reason: collision with root package name */
    public static final int f9947e = 4;

    @Override // com.bytedance.notification.supporter.service.f
    public int a(Context context, NotificationBody notificationBody) {
        return b(context, notificationBody);
    }

    private int b(Context context, NotificationBody notificationBody) {
        NotificationManager notificationManager;
        if (Build.VERSION.SDK_INT < 26 || !n.m() || (notificationManager = (NotificationManager) context.getSystemService(NotificationDeleteBroadcastReceiver.VALUE_MSG_TYPE_NOTIFICATION)) == null) {
            return 0;
        }
        if (TextUtils.isEmpty(notificationBody.notificationCategory) || TextUtils.isEmpty(notificationBody.channelId)) {
            return 1;
        }
        NotificationChannel notificationChannel = notificationManager.getNotificationChannel(notificationBody.channelId);
        if (notificationChannel == null) {
            return 2;
        }
        if (notificationBody.channelImportance != 1 || notificationChannel.getImportance() <= 2) {
            if (notificationChannel.getImportance() <= 2) {
                if (notificationChannel.shouldVibrate()) {
                    return 4;
                }
                if ((notificationChannel.getSound() != null && notificationChannel.getSound() != Settings.System.DEFAULT_NOTIFICATION_URI && !TextUtils.isEmpty(notificationChannel.getSound().toString())) || notificationChannel.getLockscreenVisibility() != -1) {
                    return 4;
                }
            }
            return 0;
        }
        return 3;
    }
}
