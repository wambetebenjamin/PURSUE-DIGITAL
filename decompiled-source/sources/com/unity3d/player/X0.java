package com.unity3d.player;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class X0 implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a1 f18925a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public X0(a1 a1Var) {
        this.f18925a = a1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f18925a.destroyPlayer();
        this.f18925a.a(3);
    }
}
