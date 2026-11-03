package com.bytedance.notification.supporter.service;

import android.content.Context;
import android.graphics.Bitmap;
import com.bytedance.android.service.manager.push.notification.AsyncImageDownloader;
/* compiled from: IImageDownloadService.java */
/* loaded from: classes2.dex */
public interface b {
    Bitmap a(String str);

    void a(Context context, String str, String str2, boolean z, com.bytedance.notification.interfaze.f fVar);

    void a(AsyncImageDownloader asyncImageDownloader);

    void a(String str, com.bytedance.notification.interfaze.f fVar);
}
