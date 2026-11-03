package com.bytedance.ttgame.module.rn.api;

import com.bytedance.ttgame.base.GSDKError;
import java.util.HashMap;
/* loaded from: classes6.dex */
public interface IRNPageListener {
    void onClose(String str, String str2);

    void onFailed(String str);

    void onMessage(String str, String str2, HashMap<String, Object> hashMap, String str3);

    void onNetworkError(GSDKError gSDKError);

    void onPageCreated();

    void onWindowCreated(String str);
}
