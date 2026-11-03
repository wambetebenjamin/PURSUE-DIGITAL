package com.ss.android.socialbase.downloader.depend;

import com.ss.android.socialbase.downloader.model.DownloadInfo;
/* loaded from: classes9.dex */
public abstract class AbsDownloadInterceptor implements IDownloadInterceptor {
    public boolean intercept(DownloadInfo downloadInfo) {
        return false;
    }

    @Override // com.ss.android.socialbase.downloader.depend.IDownloadInterceptor
    @Deprecated
    public boolean intercepte() {
        return false;
    }
}
