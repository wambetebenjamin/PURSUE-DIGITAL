package com.unity3d.player;

import android.telephony.PhoneStateListener;
/* loaded from: classes10.dex */
final class R0 extends PhoneStateListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ UnityPlayer f18876a;

    private R0(UnityPlayer unityPlayer) {
        this.f18876a = unityPlayer;
    }

    @Override // android.telephony.PhoneStateListener
    public final void onCallStateChanged(int i2, String str) {
        UnityPlayer.m780$$Nest$mnativeMuteMasterAudio(this.f18876a, i2 == 1);
    }
}
