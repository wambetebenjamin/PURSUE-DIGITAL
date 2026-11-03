package com.oasis.rtc;
/* loaded from: classes9.dex */
public interface RTCListener {
    void onAudioSendEnabled(String str, String str2, boolean z);

    void onAudioVolumeIndication(String str, String str2);

    void onConnectionStateChanged(int i2);

    void onMicrophoneEnabled(String str, boolean z, String str2);

    void onNetworkQuality(String str, String str2, int i2, int i3);

    void onSelfJoinResult(boolean z, int i2, String str);

    void onSelfLeaveRoom(String str);

    void onSpeakerphoneEnabled(String str, String str2, boolean z);

    void onUserJoined(String str, String str2);

    void onUserLeave(String str, int i2, String str2);
}
