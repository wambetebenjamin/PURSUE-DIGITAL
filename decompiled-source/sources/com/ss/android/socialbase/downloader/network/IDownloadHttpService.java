package com.ss.android.socialbase.downloader.network;

import com.ss.android.socialbase.downloader.model.HttpHeader;
import java.util.List;
/* loaded from: classes9.dex */
public interface IDownloadHttpService {
    IDownloadHttpConnection downloadWithConnection(int i2, String str, List<HttpHeader> list);
}
