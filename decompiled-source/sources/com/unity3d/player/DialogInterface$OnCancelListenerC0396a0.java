package com.unity3d.player;

import android.content.DialogInterface;
/* renamed from: com.unity3d.player.a0  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
final class DialogInterface$OnCancelListenerC0396a0 implements DialogInterface.OnCancelListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ C0400c0 f18936a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnCancelListenerC0396a0(C0400c0 c0400c0) {
        this.f18936a = c0400c0;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        F f2 = this.f18936a.f18886f;
        if (f2 != null) {
            ((C0418l0) f2).a();
        }
    }
}
