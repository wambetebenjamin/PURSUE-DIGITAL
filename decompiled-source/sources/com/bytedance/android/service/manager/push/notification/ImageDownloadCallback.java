package com.bytedance.android.service.manager.push.notification;

import android.graphics.Bitmap;
/* loaded from: classes.dex */
public interface ImageDownloadCallback {
    void onFailed();

    void onSuccess(Bitmap bitmap);
}
