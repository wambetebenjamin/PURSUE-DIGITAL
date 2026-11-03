package com.unity3d.player;
/* renamed from: com.unity3d.player.p0  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
final class C0426p0 implements G {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ RunnableC0428q0 f19036a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0426p0(RunnableC0428q0 runnableC0428q0) {
        this.f19036a = runnableC0428q0;
    }

    public final void a() {
        RunnableC0428q0 runnableC0428q0 = this.f19036a;
        runnableC0428q0.f19040a = true;
        if (runnableC0428q0.f19041b) {
            runnableC0428q0.f19042c.release();
        }
    }
}
