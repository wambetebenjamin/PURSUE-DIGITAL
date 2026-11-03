package com.unity3d.player;

import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
/* renamed from: com.unity3d.player.o  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
final class C0423o extends ContentObserver {

    /* renamed from: a  reason: collision with root package name */
    private final InterfaceC0425p f19031a;

    /* renamed from: b  reason: collision with root package name */
    private final AudioManager f19032b;

    /* renamed from: c  reason: collision with root package name */
    private final int f19033c;

    /* renamed from: d  reason: collision with root package name */
    private int f19034d;

    public C0423o(Handler handler, AudioManager audioManager, InterfaceC0425p interfaceC0425p) {
        super(handler);
        this.f19032b = audioManager;
        this.f19033c = 3;
        this.f19031a = interfaceC0425p;
        this.f19034d = audioManager.getStreamVolume(3);
    }

    @Override // android.database.ContentObserver
    public final boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z, Uri uri) {
        int streamVolume;
        AudioManager audioManager = this.f19032b;
        if (audioManager == null || this.f19031a == null || (streamVolume = audioManager.getStreamVolume(this.f19033c)) == this.f19034d) {
            return;
        }
        this.f19034d = streamVolume;
        this.f19031a.onAudioVolumeChanged(streamVolume);
    }
}
