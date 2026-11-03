package com.oasis.downloader;
/* loaded from: classes9.dex */
public class a extends DownloaderAgent {
    @Override // com.oasis.downloader.DownloaderAgent
    public int getWorkerCount() {
        return 0;
    }

    @Override // com.oasis.downloader.DownloaderAgent
    public boolean isUseDefault() {
        return true;
    }

    @Override // com.oasis.downloader.DownloaderAgent
    public void pauseDownload(String str) {
    }

    @Override // com.oasis.downloader.DownloaderAgent
    public void resumeDownload(String str) {
    }

    @Override // com.oasis.downloader.DownloaderAgent
    public void setThrottleNetSpeed(String str, long j2) {
    }

    @Override // com.oasis.downloader.DownloaderAgent
    public void setWorkerCount(int i2) {
    }

    @Override // com.oasis.downloader.DownloaderAgent
    public int startBatchDownload(String str, boolean z, IDownloaderListener iDownloaderListener) {
        return 0;
    }

    @Override // com.oasis.downloader.DownloaderAgent
    public int startDownload(String str, IDownloaderListener iDownloaderListener) {
        return super.startDownload(str, iDownloaderListener);
    }

    @Override // com.oasis.downloader.DownloaderAgent
    public void stopAll(boolean z) {
    }

    @Override // com.oasis.downloader.DownloaderAgent
    public void stopDownload(String str) {
    }
}
