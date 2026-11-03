package com.unity3d.player;

import android.graphics.Rect;
import java.util.concurrent.Semaphore;
/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unity3d.player.q0  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
public final class RunnableC0428q0 implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    boolean f19040a = false;

    /* renamed from: b  reason: collision with root package name */
    boolean f19041b = false;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Semaphore f19042c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ UnityPlayer f19043d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ UnityPlayer f19044e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0428q0(UnityPlayer unityPlayer, Semaphore semaphore, UnityPlayer unityPlayer2) {
        this.f19044e = unityPlayer;
        this.f19042c = semaphore;
        this.f19043d = unityPlayer2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f19044e.reportSoftInputArea(new Rect());
        this.f19044e.reportSoftInputIsVisible(false);
        if (this.f19044e.mSoftInput != null) {
            this.f19043d.setOnHandleFocusListener(new C0424o0(this));
            UnityPlayer unityPlayer = this.f19044e;
            unityPlayer.mSoftInput.f18887g = new C0426p0(this);
            unityPlayer.dismissSoftInput();
        }
    }
}
