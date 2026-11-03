package com.ss.android.socialbase.downloader.downloader;

import com.ss.android.socialbase.downloader.exception.DownloadTTNetException;
import com.ss.android.socialbase.downloader.network.IDownloadHeadHttpService;
import com.ss.android.socialbase.downloader.network.IDownloadHttpService;
/* loaded from: classes9.dex */
public interface ITTNetHandler {

    /* loaded from: classes9.dex */
    public static class DefaultTTNetHandler implements ITTNetHandler {
        @Override // com.ss.android.socialbase.downloader.downloader.ITTNetHandler
        public int getResponseCode(Throwable th) {
            return -1;
        }

        @Override // com.ss.android.socialbase.downloader.downloader.ITTNetHandler
        public IDownloadHeadHttpService getTTNetDownloadHeadHttpService() {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.downloader.ITTNetHandler
        public IDownloadHttpService getTTNetDownloadHttpService() {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.downloader.ITTNetHandler
        public boolean isTTNetEnable() {
            return false;
        }

        @Override // com.ss.android.socialbase.downloader.downloader.ITTNetHandler
        public DownloadTTNetException translateTTNetException(Throwable th, String str) {
            return null;
        }
    }

    int getResponseCode(Throwable th);

    IDownloadHeadHttpService getTTNetDownloadHeadHttpService();

    IDownloadHttpService getTTNetDownloadHttpService();

    boolean isTTNetEnable();

    DownloadTTNetException translateTTNetException(Throwable th, String str);
}
