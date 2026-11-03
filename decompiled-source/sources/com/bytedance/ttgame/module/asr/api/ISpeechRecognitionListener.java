package com.bytedance.ttgame.module.asr.api;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes4.dex */
public interface ISpeechRecognitionListener {
    default void cancelRecordFailed(GSDKError gSDKError) {
    }

    default void cancelRecordSuccess() {
    }

    void engineError(GSDKError gSDKError);

    default void engineStart(String str) {
    }

    default void engineStop(String str) {
    }

    void finalResultFailed(ASRResult aSRResult, GSDKError gSDKError);

    void finalResultSuccess(ASRResult aSRResult);

    void initFiled(GSDKError gSDKError);

    void initSuccess(String str);

    default void partialResult(String str, String str2) {
    }

    default void startRecordFailed(GSDKError gSDKError) {
    }

    default void startRecordSuccess() {
    }

    default void stopRecordFailed(GSDKError gSDKError) {
    }

    default void stopRecordSuccess() {
    }

    default void translateResult(String str) {
    }

    default void volumeLevel(double d2) {
    }
}
