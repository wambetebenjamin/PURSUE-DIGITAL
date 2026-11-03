package com.oasis.screenrecord;
/* loaded from: classes9.dex */
public interface IDownloadListener {
    void onDeleted(String str);

    void onFailed(String str);

    void onProgress(String str);

    void onSuccess(String str);
}
