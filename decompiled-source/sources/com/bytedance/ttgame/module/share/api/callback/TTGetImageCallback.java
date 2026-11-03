package com.bytedance.ttgame.module.share.api.callback;

import android.graphics.Bitmap;
/* loaded from: classes7.dex */
public interface TTGetImageCallback {
    void onFailed();

    void onSuccess(Bitmap bitmap);
}
