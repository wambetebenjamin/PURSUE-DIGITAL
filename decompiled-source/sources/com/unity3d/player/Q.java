package com.unity3d.player;

import android.view.KeyEvent;
import android.widget.TextView;
/* loaded from: classes10.dex */
final class Q implements TextView.OnEditorActionListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ S f18875a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Q(S s) {
        this.f18875a = s;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
        if (i2 == 6) {
            S s = this.f18875a;
            s.a(s.b(), false);
        }
        return false;
    }
}
