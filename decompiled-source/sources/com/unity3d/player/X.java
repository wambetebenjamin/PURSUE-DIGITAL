package com.unity3d.player;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class X extends S {

    /* renamed from: h  reason: collision with root package name */
    private boolean f18922h;

    /* renamed from: i  reason: collision with root package name */
    private Handler f18923i;

    /* renamed from: j  reason: collision with root package name */
    private Runnable f18924j;

    public X(Context context, UnityPlayer unityPlayer) {
        super(context, unityPlayer);
        this.f18922h = false;
    }

    @Override // com.unity3d.player.S
    public final void a(boolean z) {
        EditText editText;
        int i2;
        this.f18884d = z;
        if (z) {
            editText = this.f18883c;
            i2 = 4;
        } else {
            editText = this.f18883c;
            i2 = 0;
        }
        editText.setVisibility(i2);
        this.f18883c.invalidate();
        this.f18883c.requestLayout();
    }

    @Override // com.unity3d.player.S
    public final void c() {
        Runnable runnable;
        Handler handler = this.f18923i;
        if (handler != null && (runnable = this.f18924j) != null) {
            handler.removeCallbacks(runnable);
        }
        this.f18882b.removeView(this.f18883c);
        this.f18922h = false;
        invokeOnClose();
    }

    @Override // com.unity3d.player.S
    protected EditText createEditText(S s) {
        return new W(this.f18881a, s);
    }

    @Override // com.unity3d.player.S
    public final boolean d() {
        return false;
    }

    @Override // com.unity3d.player.S
    public final void e() {
        if (this.f18922h) {
            return;
        }
        this.f18882b.addView(this.f18883c);
        this.f18882b.bringChildToFront(this.f18883c);
        this.f18883c.setVisibility(0);
        this.f18883c.requestFocus();
        this.f18924j = new V(this);
        Handler handler = new Handler(Looper.getMainLooper());
        this.f18923i = handler;
        handler.postDelayed(this.f18924j, 400L);
        this.f18922h = true;
    }
}
