package com.ss.android.socialbase.downloader.exception;
/* loaded from: classes9.dex */
public class DownloadHttpException extends BaseException {
    private final int httpStatusCode;

    public DownloadHttpException(int i2, int i3, String str) {
        super(i2, str);
        this.httpStatusCode = i3;
    }

    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }
}
