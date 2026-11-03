package com.ss.android.socialbase.downloader.depend;

import com.ss.android.socialbase.downloader.model.DownloadInfo;
/* loaded from: classes9.dex */
public interface INotificationClickCallback {
    boolean onClickWhenInstalled(DownloadInfo downloadInfo);

    boolean onClickWhenSuccess(DownloadInfo downloadInfo);

    boolean onClickWhenUnSuccess(DownloadInfo downloadInfo);
}
