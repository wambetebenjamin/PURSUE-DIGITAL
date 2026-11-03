package com.bytedance.ttgame.module.asr.api.voice;

import com.bytedance.ttgame.base.GSDKError;
import java.io.File;
/* loaded from: classes4.dex */
public interface IPlayVoiceListener {
    default void onComplete(String str) {
    }

    default void onDownLoadSuccess(String str, File file) {
    }

    default void onError(GSDKError gSDKError) {
    }

    default void onStart(String str) {
    }
}
