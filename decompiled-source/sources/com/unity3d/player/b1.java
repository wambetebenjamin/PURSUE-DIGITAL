package com.unity3d.player;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class b1 implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ c1 f18949a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b1(c1 c1Var) {
        this.f18949a = c1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a1 a1Var;
        UnityPlayer unityPlayer;
        UnityPlayer unityPlayer2;
        a1 a1Var2;
        h1 h1Var;
        i1 i1Var = this.f18949a.f18955a.f18964h;
        a1Var = i1Var.f18996f;
        if (a1Var != null) {
            unityPlayer2 = i1Var.f18991a;
            unityPlayer2.removeViewFromPlayer(a1Var);
            i1Var.f18999i = false;
            a1Var2 = i1Var.f18996f;
            a1Var2.destroyPlayer();
            i1Var.f18996f = null;
            h1Var = i1Var.f18993c;
            if (h1Var != null) {
                ((A0) h1Var).a();
            }
        }
        unityPlayer = this.f18949a.f18955a.f18964h.f18991a;
        unityPlayer.onResume();
    }
}
