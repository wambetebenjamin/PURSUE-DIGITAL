package com.unity3d.player;

import android.view.KeyEvent;
import android.view.View;
/* renamed from: com.unity3d.player.b  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
final class View$OnKeyListenerC0397b implements View.OnKeyListener {
    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i2, KeyEvent keyEvent) {
        return i2 == 4 && keyEvent.getAction() == 1;
    }
}
