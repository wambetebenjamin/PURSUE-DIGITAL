package com.ss.android.socialbase.downloader.depend;

import java.util.List;
/* loaded from: classes9.dex */
public abstract class AbsDownloadForbiddenCallback implements IDownloadForbiddenCallback {
    private boolean hasCallback = false;

    @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenCallback
    public void onCallback(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.hasCallback = true;
    }

    @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenCallback
    public boolean hasCallback() {
        return this.hasCallback;
    }
}
