package com.bytedance.ttgame.module.vtranslate.api;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public interface IVoiceTranslateListener {
    void onFail(GSDKError gSDKError);

    void onSuccess(String str);
}
