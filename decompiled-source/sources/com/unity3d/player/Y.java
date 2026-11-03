package com.unity3d.player;

import android.content.DialogInterface;
/* loaded from: classes10.dex */
final class Y implements DialogInterface.OnDismissListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ C0400c0 f18926a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Y(C0400c0 c0400c0) {
        this.f18926a = c0400c0;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        this.f18926a.invokeOnClose();
    }
}
