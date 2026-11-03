package com.bytedance.android.service.manager.push.notification;

import com.bytedance.push.img.a;
import com.bytedance.push.img.c;
/* loaded from: classes.dex */
public interface AsyncImageDownloader extends a {
    void asyncDownloadImage(c cVar, ImageDownloadCallback imageDownloadCallback);
}
