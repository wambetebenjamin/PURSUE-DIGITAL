package com.oasis.voice;
/* loaded from: classes9.dex */
public interface RecordVoiceListener {
    void onAudioVolume(double d2);

    void onComplete(String str);

    void onError(String str);

    void onStart();

    void onTick(String str);

    void onTickFinish();

    void onUploadSuccess(String str);
}
