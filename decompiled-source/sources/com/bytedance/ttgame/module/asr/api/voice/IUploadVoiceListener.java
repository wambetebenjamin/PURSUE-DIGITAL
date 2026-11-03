package com.bytedance.ttgame.module.asr.api.voice;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes4.dex */
public interface IUploadVoiceListener {
    void onError(GSDKError gSDKError);

    void onUploadSuccess(String str);
}
