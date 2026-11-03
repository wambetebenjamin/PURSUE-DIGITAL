package com.bytedance.hades.downloader.impl.service;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import java.lang.ref.WeakReference;
/* loaded from: classes2.dex */
public interface IDownloadServiceHandler {
    boolean isServiceAlive();

    boolean isServiceForeground();

    IBinder onBind(Intent intent);

    void onDestroy();

    void onStartCommand(Intent intent, int i2, int i3);

    void onStartCommandOnMainThread();

    void setDownloadService(WeakReference<DownloadService> weakReference);

    void startForeground(int i2, Notification notification);

    void startService(Context context);

    void stopForeground(boolean z);
}
