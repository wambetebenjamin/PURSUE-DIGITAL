package com.unity3d.player;
/* loaded from: classes10.dex */
final class V implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ X f18914a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public V(X x) {
        this.f18914a = x;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f18914a.f18883c.requestFocus();
        this.f18914a.f();
    }
}
