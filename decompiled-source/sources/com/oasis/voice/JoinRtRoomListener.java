package com.oasis.voice;
/* loaded from: classes9.dex */
public interface JoinRtRoomListener {
    void onAudioVolumeIndication(String str);

    void onConnectionLost();

    void onMuteAllRemoteAudio(String str, boolean z);

    void onNetworkQuality(String str, int i2, int i3);

    void onSelfJoinResult(boolean z, int i2, String str);

    void onSelfLeaveRoom();

    void onUserJoined(String str, int i2);

    void onUserMuteAudio(boolean z, String str);

    void onUserOffline(int i2, String str);
}
