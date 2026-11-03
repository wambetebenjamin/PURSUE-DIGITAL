package com.oasis.voice;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class RTCVoiceAgent {
    public static String TAG = "RTCVoiceAgent";

    /* renamed from: a  reason: collision with root package name */
    private static RTCVoiceAgent f17772a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17773b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17774c = true;

    public static RTCVoiceAgent createInstance(Context context) {
        if (f17774c || f17772a == null) {
            try {
                String string = context.getString(R.string.rtc_voice_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i(TAG, "Create the RTCVoiceAgent:" + string);
                    f17772a = (RTCVoiceAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e(TAG, "Create rtc voice agent failed.", e2);
            }
            if (f17772a == null) {
                Logger.i(TAG, "Create default RTCVoiceAgent.");
                f17772a = new DefaultRTCVoiceAgent();
                f17773b = true;
            }
            TAG = f17772a.getClass().getSimpleName();
            return f17772a;
        }
        throw new AssertionError();
    }

    public static RTCVoiceAgent getInstance() {
        if (f17774c || f17772a != null) {
            return f17772a;
        }
        throw new AssertionError();
    }

    public int adjustPlaybackSignalVolume(int i2) {
        Logger.i(TAG, "adjustPlaybackSignalVolume " + i2);
        return 0;
    }

    public int adjustRemoteAudioVolume(String str, int i2) {
        Logger.i(TAG, "adjustRemoteAudioVolume" + str + ",with volume: " + i2);
        return 0;
    }

    public int configTeamId(String str) {
        Logger.i(TAG, "configTeamId" + str);
        return -1;
    }

    public int disableMicrophone(boolean z) {
        Logger.i(TAG, "disableMicrophone");
        return 0;
    }

    public int enableLocalAudio(boolean z) {
        Logger.i(TAG, "enableLocalAudio " + String.valueOf(z));
        return -1;
    }

    public int enableRangeAudio(boolean z) {
        Logger.i(TAG, "enableRangeAudio: " + z);
        return 0;
    }

    public void initRTVoice(int i2) {
        Logger.i(TAG, "initRTVoice: " + i2);
    }

    public final boolean isUseDefault() {
        return f17773b;
    }

    public void joinRoom(String str, JoinRtRoomListener joinRtRoomListener) {
        Logger.i(TAG, "joinRoom: " + str);
    }

    public void leaveRoom() {
        Logger.i(TAG, "leaveRoom");
    }

    public int muteAllUsers(boolean z) {
        Logger.i(TAG, "muteAllUsers");
        return 0;
    }

    public int muteUserByUid(String str, boolean z) {
        Logger.i(TAG, "muteUserByUid" + str);
        return 0;
    }

    public int renewToken(String str) {
        Logger.i(TAG, "renewToken " + str);
        return -1;
    }

    public void setAudioPerfProfile(int i2) {
        Logger.i(TAG, "setAudioPerfProfile " + i2);
    }

    public int setAudioReceiveMode(int i2) {
        Logger.i(TAG, "setAudioReceiveMode: " + i2);
        return -1;
    }

    public int setAudioSendMode(int i2) {
        Logger.i(TAG, "setAudioSendMode: " + i2);
        return -1;
    }

    public boolean setClientRole(int i2) {
        Logger.i(TAG, "setClientRole " + i2);
        return true;
    }

    public int setDefaultLocalAudioEnable(boolean z) {
        Logger.i(TAG, "setDefaultLocalAudioEnable " + String.valueOf(z));
        return -1;
    }

    public int updateAudioReceiveRange(int i2, int i3) {
        Logger.i(TAG, "updateAudioReceiveRange: " + i2 + " " + i3);
        return -1;
    }

    public int updateSelfPosition(int i2, int i3, int i4) {
        Logger.i(TAG, "updateSelfPosition: " + i2 + " " + i3 + " " + i4);
        return -1;
    }
}
