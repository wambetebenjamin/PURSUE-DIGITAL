package com.ss.android.socialbase.downloader.reader;

import com.ss.android.socialbase.downloader.segment.Buffer;
/* loaded from: classes9.dex */
public interface IStreamReader {
    void close();

    Buffer read();

    void recycle(Buffer buffer);
}
