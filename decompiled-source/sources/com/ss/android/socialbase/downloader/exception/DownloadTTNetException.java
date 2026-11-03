package com.ss.android.socialbase.downloader.exception;
/* loaded from: classes9.dex */
public class DownloadTTNetException extends BaseException {
    public DownloadTTNetException(int i2, String str) {
        super(i2, str);
    }

    public DownloadTTNetException(int i2, Throwable th) {
        super(i2, th);
    }

    public DownloadTTNetException setRequestLog(String str) {
        setExtraInfo(str);
        return this;
    }

    public String getRequestLog() {
        return getExtraInfo();
    }
}
