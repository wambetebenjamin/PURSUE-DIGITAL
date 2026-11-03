package com.bytedance.ttgame.module.voice.api;

import android.app.Activity;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import java.util.List;
/* loaded from: classes7.dex */
public interface IVoiceMessageService extends IModuleApi {
    public static final String TAG = "{VoiceMessage}";

    void beginRecord();

    void beginRecord(String str, String str2);

    void cancelRecord();

    void endRecordAndUpload();

    void endRecordWithoutUpload();

    void fetchVoiceLocalPathByVid(String str);

    void initWithConfig(Activity activity, VoiceMessageConfig voiceMessageConfig);

    void play(String str);

    void play(List<String> list);

    void release();

    void stop();

    void uploadAudioFile(String str, IUploadVoiceListener iUploadVoiceListener);
}
