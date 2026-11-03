package com.bytedance.notification.utils;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.notification.NotificationDeleteBroadcastReceiver;
/* compiled from: NotificationUtils.java */
/* loaded from: classes2.dex */
public class e {
    public static boolean a(Context context, String str) {
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationDeleteBroadcastReceiver.VALUE_MSG_TYPE_NOTIFICATION);
                if (notificationManager != null && !TextUtils.isEmpty(str)) {
                    return notificationManager.getNotificationChannel(str) != null;
                }
                return false;
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
