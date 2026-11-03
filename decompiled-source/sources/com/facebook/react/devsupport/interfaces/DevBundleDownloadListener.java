package com.facebook.react.devsupport.interfaces;
/* loaded from: classes8.dex */
public interface DevBundleDownloadListener {
    void onFailure(Exception exc);

    void onProgress(String str, Integer num, Integer num2);

    void onSuccess();
}
