package com.bytedance.ttgame.module.asr.api;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.module.asr.api.voice.IVoiceMessageService;
import java.util.List;
/* loaded from: classes4.dex */
public interface ISpeechRecognitionService extends IModuleApi {
    void cancelRecord();

    IVoiceMessageService getVoiceMessageService();

    void init(ASRInitConfig aSRInitConfig);

    void playVoice(List<String> list);

    void startRecord();

    void stopRecord();

    void stopVoice();

    void unInit();
}
