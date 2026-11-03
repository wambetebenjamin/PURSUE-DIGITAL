package com.unity3d.player;

import android.graphics.SurfaceTexture;
/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unity3d.player.v  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
public final class C0437v implements SurfaceTexture.OnFrameAvailableListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ C0439w f19058a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0437v(C0439w c0439w) {
        this.f19058a = c0439w;
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        com.unity3d.player.a.b bVar;
        bVar = this.f19058a.f19063a;
        ((Camera2Wrapper) bVar).a(surfaceTexture);
    }
}
