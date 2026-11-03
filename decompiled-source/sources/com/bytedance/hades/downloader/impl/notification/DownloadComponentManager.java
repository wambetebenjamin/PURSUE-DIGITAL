package com.bytedance.hades.downloader.impl.notification;

import android.content.Context;
import com.bytedance.hades.downloader.impl.service.DefaultDownloadServiceHandler;
import com.bytedance.hades.downloader.impl.service.IDownloadServiceHandler;
/* loaded from: classes2.dex */
public class DownloadComponentManager {
    private static volatile Context appContext;
    private static volatile IDownloadServiceHandler downloadServiceHandler;

    public static synchronized Context getAppContext() {
        Context context;
        synchronized (DownloadComponentManager.class) {
            context = appContext;
        }
        return context;
    }

    public static IDownloadServiceHandler getDownloadServiceHandler() {
        if (downloadServiceHandler == null) {
            synchronized (DownloadComponentManager.class) {
                if (downloadServiceHandler == null) {
                    downloadServiceHandler = new DefaultDownloadServiceHandler();
                }
            }
        }
        return downloadServiceHandler;
    }

    public static synchronized void setAppContext(Context context) {
        synchronized (DownloadComponentManager.class) {
            if (context != null) {
                if (appContext == null) {
                    appContext = context.getApplicationContext();
                }
            }
        }
    }
}
