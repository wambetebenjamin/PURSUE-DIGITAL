package com.unity3d.player;

import android.content.Context;
import android.widget.EditText;
/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unity3d.player.c0  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
public final class C0400c0 extends S {

    /* renamed from: h  reason: collision with root package name */
    U f18954h;

    public C0400c0(Context context, UnityPlayer unityPlayer) {
        super(context, unityPlayer);
    }

    @Override // com.unity3d.player.S
    public final void a(String str, int i2, boolean z, boolean z2, boolean z3, boolean z4, String str2, int i3, boolean z5, boolean z6) {
        U u = new U(this.f18881a, this.f18882b);
        this.f18954h = u;
        u.a(this, z5, z6);
        this.f18954h.setOnDismissListener(new Y(this));
        super.a(str, i2, z, z2, z3, z4, str2, i3, z5, z6);
        this.f18882b.getViewTreeObserver().addOnGlobalLayoutListener(new Z(this));
        this.f18883c.requestFocus();
        this.f18954h.setOnCancelListener(new DialogInterface$OnCancelListenerC0396a0(this));
    }

    @Override // com.unity3d.player.S
    public final void a(boolean z) {
        this.f18884d = z;
        this.f18954h.a(z);
    }

    @Override // com.unity3d.player.S
    public final void c() {
        this.f18954h.dismiss();
    }

    @Override // com.unity3d.player.S
    protected EditText createEditText(S s) {
        return new C0398b0(this, this.f18881a, s);
    }

    @Override // com.unity3d.player.S
    public final void e() {
        this.f18954h.show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportSoftInputArea() {
        if (this.f18954h.isShowing()) {
            this.f18882b.reportSoftInputArea(this.f18954h.a());
        }
    }
}
