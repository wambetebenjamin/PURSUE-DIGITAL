package com.unity3d.player;

import android.view.ViewTreeObserver;
/* loaded from: classes10.dex */
final class Z implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ C0400c0 f18927a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Z(C0400c0 c0400c0) {
        this.f18927a = c0400c0;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        this.f18927a.reportSoftInputArea();
        this.f18927a.f18954h.b();
    }
}
