package com.oasis.android;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.bytedance.notification.NotificationDeleteBroadcastReceiver;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes9.dex */
public class ProgressBarNotification {

    /* renamed from: a  reason: collision with root package name */
    private static NotificationManager f17405a;

    /* renamed from: b  reason: collision with root package name */
    private static int f17406b;

    /* renamed from: c  reason: collision with root package name */
    private static Map<Integer, NotificationCompat.Builder> f17407c = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    private static long f17408d = 0;

    /* renamed from: e  reason: collision with root package name */
    private static int f17409e = 1000;

    public static void deleteAll() {
        f17405a.cancelAll();
        f17407c.clear();
    }

    public static void deleteNotify(int i2) {
        f17405a.cancel(i2);
        f17407c.remove(Integer.valueOf(i2));
    }

    public static int initProgressBar(Context context, String str, String str2, String str3) {
        f17405a = (NotificationManager) context.getSystemService(NotificationDeleteBroadcastReceiver.VALUE_MSG_TYPE_NOTIFICATION);
        if (Build.VERSION.SDK_INT >= 26) {
            f17405a.createNotificationChannel(new NotificationChannel(str, str2, 3));
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, str);
        builder.setContentTitle(context.getPackageManager().getApplicationLabel(context.getApplicationInfo())).setContentText(str3).setPriority(-1).setSmallIcon(context.getApplicationInfo().icon).setProgress(100, 0, false);
        int i2 = f17406b + 1;
        f17406b = i2;
        f17407c.put(Integer.valueOf(i2), builder);
        f17405a.notify(f17406b, builder.build());
        return f17406b;
    }

    public static void notifyFinished(int i2, String str) {
        NotificationCompat.Builder builder = f17407c.get(Integer.valueOf(i2));
        if (builder != null) {
            builder.setContentText(str).setProgress(0, 0, false);
            f17405a.notify(i2, builder.build());
        }
    }

    public static void updateProgress(int i2, float f2) {
        NotificationCompat.Builder builder = f17407c.get(Integer.valueOf(i2));
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - f17408d < f17409e || builder == null) {
            return;
        }
        builder.setContentText("下载中");
        builder.setProgress(100, (int) (f2 * 100.0f), false);
        f17405a.notify(i2, builder.build());
        f17408d = currentTimeMillis;
    }
}
