package com.bytedance.android.service.manager.push.notification;

import android.app.Notification;
/* loaded from: classes.dex */
public interface IPushNotification {
    Notification getNotification();

    void show();

    void show(String str, int i2);
}
