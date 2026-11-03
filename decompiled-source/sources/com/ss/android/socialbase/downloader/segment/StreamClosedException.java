package com.ss.android.socialbase.downloader.segment;

import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.ss.android.socialbase.downloader.exception.BaseException;
/* loaded from: classes9.dex */
public class StreamClosedException extends BaseException {
    public StreamClosedException(String str) {
        super((int) DownloadErrorCode.ERROR_STREAM_CLOSED, str);
    }
}
