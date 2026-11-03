package com.bytedance.ttgame.module.webview.api.aidl;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public interface IWebAidlResultCallback {
    void onError(String str, GSDKError gSDKError);

    void onResult(String str, Object obj);
}
