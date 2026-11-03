package com.unity3d.player;

import android.window.OnBackInvokedCallback;
/* renamed from: com.unity3d.player.d  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
final class C0401d implements OnBackInvokedCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ com.unity3d.player.a.c f18956a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0401d(com.unity3d.player.a.c cVar) {
        this.f18956a = cVar;
    }

    public final void onBackInvoked() {
        Runnable runnable = ((B) this.f18956a).f18803a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
