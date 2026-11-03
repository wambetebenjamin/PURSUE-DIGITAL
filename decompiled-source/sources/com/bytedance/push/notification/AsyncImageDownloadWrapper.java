package com.bytedance.push.notification;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.bytedance.android.service.manager.push.notification.AsyncImageDownloader;
import com.bytedance.android.service.manager.push.notification.ImageDownloadCallback;
/* loaded from: classes2.dex */
public class AsyncImageDownloadWrapper implements Handler.Callback, AsyncImageDownloader {
    private Handler mHandler;
    private final com.bytedance.push.img.a mImageDownloader;

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        return false;
    }

    public AsyncImageDownloadWrapper(com.bytedance.push.img.a aVar) {
        this.mImageDownloader = aVar;
    }

    @Override // com.bytedance.android.service.manager.push.notification.AsyncImageDownloader
    public void asyncDownloadImage(final com.bytedance.push.img.c cVar, final ImageDownloadCallback imageDownloadCallback) {
        if (this.mHandler == null) {
            this.mHandler = new Handler(Looper.getMainLooper(), this);
        }
        com.bytedance.common.push.d.a(new Runnable() { // from class: com.bytedance.push.notification.AsyncImageDownloadWrapper.1
            @Override // java.lang.Runnable
            public void run() {
                final Bitmap bitmap;
                try {
                    bitmap = AsyncImageDownloadWrapper.this.mImageDownloader.downloadImage(cVar);
                } catch (Throwable th) {
                    th.printStackTrace();
                    bitmap = null;
                }
                AsyncImageDownloadWrapper.this.mHandler.post(new Runnable() { // from class: com.bytedance.push.notification.AsyncImageDownloadWrapper.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Bitmap bitmap2 = bitmap;
                        if (bitmap2 != null && !bitmap2.isRecycled()) {
                            imageDownloadCallback.onSuccess(bitmap);
                        } else {
                            imageDownloadCallback.onFailed();
                        }
                    }
                });
            }
        });
    }

    @Override // com.bytedance.push.img.a
    public Bitmap downloadImage(com.bytedance.push.img.c cVar) {
        return this.mImageDownloader.downloadImage(cVar);
    }
}
