package com.unity3d.player;

import android.content.Context;
/* loaded from: classes10.dex */
public class AudioVolumeHandler implements InterfaceC0425p {

    /* renamed from: a  reason: collision with root package name */
    private C0427q f18802a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioVolumeHandler(Context context) {
        C0427q c0427q = new C0427q(context);
        this.f18802a = c0427q;
        c0427q.a(this);
    }

    public final void a() {
        this.f18802a.a();
        this.f18802a = null;
    }

    @Override // com.unity3d.player.InterfaceC0425p
    public final native void onAudioVolumeChanged(int i2);
}
