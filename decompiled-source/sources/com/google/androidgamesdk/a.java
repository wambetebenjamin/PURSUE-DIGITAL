package com.google.androidgamesdk;

import android.view.Choreographer;
/* loaded from: classes6.dex */
final class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ChoreographerCallback f16887a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(ChoreographerCallback choreographerCallback) {
        this.f16887a = choreographerCallback;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Choreographer.getInstance().postFrameCallback(this.f16887a);
    }
}
