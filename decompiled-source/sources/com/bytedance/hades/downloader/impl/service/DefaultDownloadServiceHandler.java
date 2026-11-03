package com.bytedance.hades.downloader.impl.service;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
/* loaded from: classes2.dex */
public class DefaultDownloadServiceHandler extends AbsDownloadServiceHandler {
    private static final String TAG = "DefaultDownloadServiceHandler";

    private void startFakeService() {
        onStartCommandOnMainThread();
    }

    @Override // com.bytedance.hades.downloader.impl.service.AbsDownloadServiceHandler, com.bytedance.hades.downloader.impl.service.IDownloadServiceHandler
    public void onStartCommand(Intent intent, int i2, int i3) {
        this.isServiceAlive = true;
    }

    @Override // com.bytedance.hades.downloader.impl.service.AbsDownloadServiceHandler, com.bytedance.hades.downloader.impl.service.IDownloadServiceHandler
    public void onStartCommandOnMainThread() {
        this.isServiceAlive = true;
        this.isInvokeStartService = false;
    }

    @Override // com.bytedance.hades.downloader.impl.service.AbsDownloadServiceHandler
    public void startService(Context context, ServiceConnection serviceConnection) {
        try {
            context.startService(new Intent(context, DownloadService.class));
        } catch (Throwable th) {
            th.printStackTrace();
            startFakeService();
        }
    }
}
