package com.ss.android.socialbase.downloader.network;

import java.io.InputStream;
/* loaded from: classes9.dex */
public interface IDownloadHttpConnection extends IDownloadHeadHttpConnection {
    void end();

    InputStream getInputStream();
}
