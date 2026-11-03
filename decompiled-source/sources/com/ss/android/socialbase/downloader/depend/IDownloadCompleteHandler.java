package com.ss.android.socialbase.downloader.depend;

import com.ss.android.socialbase.downloader.model.DownloadInfo;
/* loaded from: classes9.dex */
public interface IDownloadCompleteHandler {
    void handle(DownloadInfo downloadInfo);

    boolean needHandle(DownloadInfo downloadInfo);
}
