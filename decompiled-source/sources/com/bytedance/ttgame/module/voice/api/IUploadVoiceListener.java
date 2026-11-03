package com.bytedance.ttgame.module.voice.api;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public interface IUploadVoiceListener {
    void onError(GSDKError gSDKError);

    void onUploadSuccess(String str);
}
