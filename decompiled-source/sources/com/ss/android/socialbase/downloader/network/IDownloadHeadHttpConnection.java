package com.ss.android.socialbase.downloader.network;
/* loaded from: classes9.dex */
public interface IDownloadHeadHttpConnection {
    void cancel();

    int getResponseCode();

    String getResponseHeaderField(String str);
}
