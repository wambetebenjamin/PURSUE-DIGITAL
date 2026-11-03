package com.bytedance.hades.downloader.api.listener;

import com.bytedance.hades.downloader.api.data.DownloadMultiInfo;
/* loaded from: classes2.dex */
public interface IDownloadMultiListener {
    void OnMultiCancel(DownloadMultiInfo downloadMultiInfo);

    void OnMultiFail(DownloadMultiInfo downloadMultiInfo);

    void OnMultiPause(DownloadMultiInfo downloadMultiInfo);

    void OnMultiProgress(DownloadMultiInfo downloadMultiInfo);

    void OnMultiStart(DownloadMultiInfo downloadMultiInfo);

    void OnMultiSuccess(DownloadMultiInfo downloadMultiInfo);
}
