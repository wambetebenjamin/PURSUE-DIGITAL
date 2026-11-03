package com.unity3d.player;

import android.content.Context;
import android.provider.Settings;
/* loaded from: classes10.dex */
public class OrientationLockListener implements InterfaceC0402d0 {

    /* renamed from: a  reason: collision with root package name */
    private C0406f0 f18864a;

    /* renamed from: b  reason: collision with root package name */
    private Context f18865b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OrientationLockListener(Context context) {
        this.f18865b = context;
        this.f18864a = new C0406f0(context);
        nativeUpdateOrientationLockState(Settings.System.getInt(context.getContentResolver(), "accelerometer_rotation", 0));
        this.f18864a.a(this);
    }

    public final void a() {
        this.f18864a.a();
        this.f18864a = null;
    }

    public final void b() {
        nativeUpdateOrientationLockState(Settings.System.getInt(this.f18865b.getContentResolver(), "accelerometer_rotation", 0));
    }

    public final native void nativeUpdateOrientationLockState(int i2);
}
