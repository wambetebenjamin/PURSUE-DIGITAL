package com.unity3d.player;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.EditText;
/* renamed from: com.unity3d.player.b0  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
final class C0398b0 extends EditText {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ S f18947a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ C0400c0 f18948b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0398b0(C0400c0 c0400c0, Context context, S s) {
        super(context);
        this.f18948b = c0400c0;
        this.f18947a = s;
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onKeyPreIme(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            if (keyEvent.getAction() == 1) {
                this.f18948b.f18954h.onBackPressed();
            }
            return true;
        } else if (i2 == 84) {
            return true;
        } else {
            if (i2 == 66 && keyEvent.getAction() == 0 && (getInputType() & 131072) == 0) {
                S s = this.f18947a;
                s.a(s.b(), false);
                return true;
            } else if (i2 == 111 && keyEvent.getAction() == 0) {
                S s2 = this.f18947a;
                s2.a(s2.b(), true);
                return true;
            } else {
                return super.onKeyPreIme(i2, keyEvent);
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            requestFocus();
            this.f18947a.f();
        }
    }
}
