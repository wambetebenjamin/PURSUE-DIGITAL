package com.unity3d.player;
/* loaded from: classes10.dex */
public final class Z0 implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private a1 f18928a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f18929b = false;

    public Z0(a1 a1Var) {
        this.f18928a = a1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
        if (this.f18929b) {
            return;
        }
        int i2 = a1.A;
        this.f18928a.cancelOnPrepare();
    }
}
