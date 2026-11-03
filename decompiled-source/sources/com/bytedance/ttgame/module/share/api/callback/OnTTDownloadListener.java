package com.bytedance.ttgame.module.share.api.callback;
/* loaded from: classes7.dex */
public interface OnTTDownloadListener {
    void onCanceled();

    void onFailed(Throwable th);

    void onProgress(int i2);

    void onStart();

    void onSuccessed();
}
