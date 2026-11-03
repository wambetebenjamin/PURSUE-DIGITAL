package com.oasis.thanos;
/* loaded from: classes9.dex */
public interface ThanosDownloadListener {
    void onDownloadProgress(int i2);

    void onFailed(String str, String str2);

    void onHotUpdateProgress(int i2);

    void onReadyToReplaceInstall(String str);

    void onUnZipProgress(int i2);
}
