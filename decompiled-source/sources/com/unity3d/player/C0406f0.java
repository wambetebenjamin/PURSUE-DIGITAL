package com.unity3d.player;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unity3d.player.f0  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
public final class C0406f0 {

    /* renamed from: a  reason: collision with root package name */
    private Context f18969a;

    /* renamed from: b  reason: collision with root package name */
    private C0404e0 f18970b;

    public C0406f0(Context context) {
        this.f18969a = context;
    }

    public final void a() {
        if (this.f18970b != null) {
            this.f18969a.getContentResolver().unregisterContentObserver(this.f18970b);
            this.f18970b = null;
        }
    }

    public final void a(InterfaceC0402d0 interfaceC0402d0) {
        this.f18970b = new C0404e0(new Handler(Looper.getMainLooper()), interfaceC0402d0);
        this.f18969a.getContentResolver().registerContentObserver(Settings.System.getUriFor("accelerometer_rotation"), true, this.f18970b);
    }
}
