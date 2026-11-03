package com.bytedance.hades.downloader.api.listener;

import com.bytedance.hades.downloader.api.data.DownloadInfo;
/* loaded from: classes2.dex */
public interface IDownloadListener {
    void OnCancel(DownloadInfo downloadInfo);

    void OnFail(DownloadInfo downloadInfo);

    void OnPause(DownloadInfo downloadInfo);

    void OnPrepare(DownloadInfo downloadInfo);

    void OnProgress(DownloadInfo downloadInfo);

    void OnStart(DownloadInfo downloadInfo);

    void OnSuccess(DownloadInfo downloadInfo);
}
