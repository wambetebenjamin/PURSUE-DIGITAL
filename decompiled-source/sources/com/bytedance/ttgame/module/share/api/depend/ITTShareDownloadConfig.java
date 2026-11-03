package com.bytedance.ttgame.module.share.api.depend;

import com.bytedance.ttgame.module.share.api.callback.OnTTDownloadListener;
/* loaded from: classes7.dex */
public interface ITTShareDownloadConfig {
    void onCancelDownload(String str, String str2, String str3);

    void onDownloadFile(String str, String str2, String str3, OnTTDownloadListener onTTDownloadListener);
}
