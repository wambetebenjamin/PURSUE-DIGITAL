package com.unity3d.player;
/* loaded from: classes10.dex */
final class c1 implements Y0 {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ d1 f18955a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c1(d1 d1Var) {
        this.f18955a = d1Var;
    }

    public final void a(int i2) {
        i1.m807$$Nest$fgete(this.f18955a.f18964h).lock();
        i1 i1Var = this.f18955a.f18964h;
        i1.m811$$Nest$fputg(i1Var, i2);
        if (i2 == 3 && i1.m809$$Nest$fgeti(i1Var)) {
            i1Var.runOnUiThread(new b1(this));
        }
        if (i2 != 0) {
            i1.m806$$Nest$fgetd(this.f18955a.f18964h).release();
        }
        i1.m807$$Nest$fgete(this.f18955a.f18964h).unlock();
    }
}
