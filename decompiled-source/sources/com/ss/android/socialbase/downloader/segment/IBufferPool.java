package com.ss.android.socialbase.downloader.segment;
/* loaded from: classes9.dex */
public interface IBufferPool {
    Buffer obtain();

    void recycle(Buffer buffer);
}
