package com.ss.android.socialbase.downloader.depend;

import com.ss.android.socialbase.downloader.model.DownloadInfo;
/* loaded from: classes9.dex */
public interface IDownloadNotificationEventListener {
    String getNotifyProcessName();

    boolean interceptAfterNotificationSuccess(boolean z);

    void onNotificationEvent(int i2, DownloadInfo downloadInfo, String str, String str2);
}
