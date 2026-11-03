package com.oasis.voice;
/* loaded from: classes9.dex */
public interface SpeechRecognitionListener {
    void cancelRecordFailed(String str);

    void cancelRecordSuccess();

    void engineError(String str);

    void engineStart(String str);

    void engineStop(String str);

    void finalResultFailed(String str);

    void finalResultSuccess(String str);

    void initFiled(String str);

    void initSuccess(String str);

    void partialResult(String str);

    void startRecordFailed(String str);

    void startRecordSuccess();

    void stopRecordFailed(String str);

    void stopRecordSuccess();

    void translateResult(String str);

    void volumeLevel(double d2);
}
