package com.bytedance.ttgame.module.asr.api.voice;

import android.app.Activity;
import java.util.List;
/* loaded from: classes4.dex */
public interface IVoiceMessageService {
    public static final String TAG = "{VoiceMessage}";

    void initWithConfig(Activity activity, VoiceMessageConfig voiceMessageConfig);

    void play(String str);

    void play(List<String> list);

    void release();

    void stop();

    void uploadAudioFile(String str, IUploadVoiceListener iUploadVoiceListener);
}
