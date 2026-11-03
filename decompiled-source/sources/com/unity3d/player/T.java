package com.unity3d.player;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class T implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ U f18898a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public T(U u) {
        this.f18898a = u;
    }

    @Override // java.lang.Runnable
    public final void run() {
        S s = this.f18898a.f18903d;
        s.a(s.b(), true);
    }
}
