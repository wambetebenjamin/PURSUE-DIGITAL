package com.bytedance.ttgame.module.asr.api;

import android.app.Activity;
import com.bytedance.ttgame.module.asr.api.voice.IPlayVoiceListener;
/* loaded from: classes4.dex */
public class ASRInitConfig {
    public static final int LANGUAGE_EN = 1;
    public static final int LANGUAGE_ZH = 0;
    public static final int PLAY_MODE_DEFAULT = 0;
    public static final int PLAY_MODE_SPEAKERPHONE = 1;
    private int language;
    private final Activity mActivity;
    private final ISpeechRecognitionListener mInitListener;
    private final IPlayVoiceListener mPlayVoiceListener;
    private final int playMode;

    public ASRInitConfig(Activity activity, int i2, int i3, ISpeechRecognitionListener iSpeechRecognitionListener, IPlayVoiceListener iPlayVoiceListener) {
        this.language = 0;
        this.mActivity = activity;
        this.playMode = i2;
        this.language = i3;
        this.mInitListener = iSpeechRecognitionListener;
        this.mPlayVoiceListener = iPlayVoiceListener;
    }

    public ASRInitConfig(Activity activity, int i2, ISpeechRecognitionListener iSpeechRecognitionListener, IPlayVoiceListener iPlayVoiceListener) {
        this.language = 0;
        this.mActivity = activity;
        this.playMode = i2;
        this.mInitListener = iSpeechRecognitionListener;
        this.mPlayVoiceListener = iPlayVoiceListener;
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public int getPlayMode() {
        return this.playMode;
    }

    public int getLanguage() {
        return this.language;
    }

    public ISpeechRecognitionListener getInitListener() {
        return this.mInitListener;
    }

    public IPlayVoiceListener getPlayVoiceListener() {
        return this.mPlayVoiceListener;
    }
}
