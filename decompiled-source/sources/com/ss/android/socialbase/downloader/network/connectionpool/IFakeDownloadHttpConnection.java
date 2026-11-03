package com.ss.android.socialbase.downloader.network.connectionpool;
/* loaded from: classes9.dex */
public interface IFakeDownloadHttpConnection {
    void execute();

    boolean isRequesting();

    boolean isSuccessful();

    boolean isValid();

    void joinExecute();
}
