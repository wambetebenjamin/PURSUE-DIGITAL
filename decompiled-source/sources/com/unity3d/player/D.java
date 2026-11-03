package com.unity3d.player;

import android.app.Activity;
import android.app.Dialog;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class D extends C {

    /* renamed from: d  reason: collision with root package name */
    private OnBackInvokedCallback f18811d;

    /* renamed from: e  reason: collision with root package name */
    private OnBackInvokedDispatcher f18812e;

    /* renamed from: f  reason: collision with root package name */
    private int f18813f;

    private D(OnBackInvokedDispatcher onBackInvokedDispatcher, int i2, Runnable runnable) {
        super(runnable);
        this.f18811d = null;
        this.f18813f = i2;
        this.f18812e = onBackInvokedDispatcher;
    }

    public static C a(Object obj, int i2, Runnable runnable) {
        C d2 = PlatformSupport.TIRAMISU_SUPPORT && ((obj instanceof Activity) || (obj instanceof Dialog)) ? new D(AbstractC0403e.a(obj), i2, runnable) : new C(runnable);
        d2.registerOnBackPressedCallback();
        return d2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.unity3d.player.C
    public void registerOnBackPressedCallback() {
        if (this.f18805a != null) {
            return;
        }
        super.registerOnBackPressedCallback();
        if (PlatformSupport.TIRAMISU_SUPPORT) {
            C0401d c0401d = new C0401d(this.f18805a);
            this.f18811d = c0401d;
            AbstractC0403e.a(this.f18812e, this.f18813f, c0401d);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.unity3d.player.C
    public void unregisterOnBackPressedCallback() {
        if (this.f18805a != null) {
            if (PlatformSupport.TIRAMISU_SUPPORT) {
                AbstractC0403e.a(this.f18812e, this.f18811d);
                this.f18811d = null;
            }
            super.unregisterOnBackPressedCallback();
        }
    }
}
