package com.oasis.rtc;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.R;
import g.a;
/* loaded from: classes9.dex */
public abstract class NewRTCAgent {
    public static String TAG = "NewRTCAgent";

    /* renamed from: a  reason: collision with root package name */
    private static NewRTCAgent f17610a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17611b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17612c = true;

    public static NewRTCAgent createInstance(Context context) {
        if (f17612c || f17610a == null) {
            try {
                String string = context.getString(R.string.rtc_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i(TAG, "Create the NewRTCAgent:" + string);
                    f17610a = (NewRTCAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e(TAG, "Create rtc voice agent failed.", e2);
            }
            if (f17610a == null) {
                Logger.i(TAG, "Create default NewRTCAgent.");
                f17610a = new a();
                f17611b = true;
            }
            TAG = f17610a.getClass().getSimpleName();
            return f17610a;
        }
        throw new AssertionError();
    }

    public static NewRTCAgent getInstance() {
        if (f17612c || f17610a != null) {
            return f17610a;
        }
        throw new AssertionError();
    }

    public void destoryRTC() {
        Logger.i(TAG, "destoryRTC");
    }

    public int enableAudioReceive(String str, String str2, boolean z) {
        Logger.i(TAG, "enableAudioReceive");
        return -1;
    }

    public int enableAudioSend(String str, boolean z) {
        Logger.i(TAG, "enableAudioSend,roomId:" + str + ",enable:" + String.valueOf(z));
        return -1;
    }

    public int enableMicrophone(String str, boolean z) {
        Logger.i(TAG, "enableMicrophone,roomId:" + str + ",enable:" + String.valueOf(z));
        return -1;
    }

    public int enableRequestAudioFocus() {
        Logger.i(TAG, "enableRequestAudioFocus");
        return -1;
    }

    public int enableSpeakerphone(String str, boolean z) {
        Logger.i(TAG, "enableSpeakerphone,roomId:" + str + ",enable:" + String.valueOf(z));
        return -1;
    }

    public String getVersion() {
        Logger.i(TAG, "getVersion");
        return "";
    }

    public void initRTVoice(String str, RTCListener rTCListener) {
        Logger.i(TAG, "initRTVoice: " + str);
    }

    public boolean isUseDefault() {
        return f17611b;
    }

    public int joinRoom(String str) {
        Logger.i(TAG, "joinRoom: " + str);
        return -1;
    }

    public int leaveRoom(String str) {
        Logger.i(TAG, "leaveRoom");
        return -1;
    }

    public int setAudioScenario(int i2) {
        Logger.i(TAG, "setAudioScenario");
        return -1;
    }

    public int setPlaybackVolume(int i2) {
        Logger.i(TAG, "setPlaybackVolume,volume:" + String.valueOf(i2));
        return -1;
    }

    public int setRecordingVolume(int i2) {
        Logger.i(TAG, "setRecordingVolume, volume:" + String.valueOf(i2));
        return -1;
    }

    public int setRemoteAudioPlaybackVolume(String str, String str2, int i2) {
        Logger.i(TAG, "setRemoteAudioPlaybackVolume");
        return -1;
    }

    public void setRtcNativeLibraryLoader() {
        Logger.i(TAG, "setRtcNativeLibraryLoader");
    }

    public int updateOrientation(String str, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        Logger.i(TAG, "updateOrientation, roomId:" + str);
        return -1;
    }

    public int updatePosition(String str, int i2, int i3, int i4) {
        Logger.i(TAG, "updatePosition");
        return -1;
    }

    public int updateReceiveRange(String str, int i2, int i3) {
        Logger.i(TAG, "updateReceiveRange");
        return -1;
    }

    public int updateToken(String str, String str2) {
        Logger.i(TAG, "updateToken " + str2);
        return -1;
    }
}
