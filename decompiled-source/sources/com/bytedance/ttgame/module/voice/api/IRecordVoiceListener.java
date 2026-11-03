package com.bytedance.ttgame.module.voice.api;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public interface IRecordVoiceListener {
    default void onAudioVolume(double d2) {
    }

    default void onComplete(long j2) {
    }

    default void onComplete(long j2, String str) {
    }

    default void onError(GSDKError gSDKError) {
    }

    default void onStart() {
    }

    default void onTick(long j2) {
    }

    default void onTickFinish() {
    }

    @Deprecated
    default void onUploadSuccess(String str) {
    }

    default void onUploadSuccess(VoiceInfo voiceInfo) {
        onUploadSuccess(voiceInfo.getVoiceId());
    }
}
