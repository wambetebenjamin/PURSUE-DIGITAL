package com.bytedance.ug.sdk.share.api.callback;
/* compiled from: OnDownloadListener.java */
/* loaded from: classes8.dex */
public interface h {
    void onCanceled();

    void onFailed(Throwable th);

    void onProgress(int i2);

    void onStart();

    void onSuccessed();
}
