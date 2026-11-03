package com.unity3d.player;

import android.os.Build;
/* loaded from: classes10.dex */
final class I implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private IPermissionRequestCallbacks f18831a;

    /* renamed from: b  reason: collision with root package name */
    private String f18832b;

    /* renamed from: c  reason: collision with root package name */
    private int f18833c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f18834d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public I(IPermissionRequestCallbacks iPermissionRequestCallbacks, String str, int i2, boolean z) {
        this.f18831a = iPermissionRequestCallbacks;
        this.f18832b = str;
        this.f18833c = i2;
        this.f18834d = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i2 = this.f18833c;
        if (i2 != -1) {
            if (i2 == 0) {
                this.f18831a.onPermissionGranted(this.f18832b);
            }
        } else if (Build.VERSION.SDK_INT >= 30 || this.f18834d) {
            this.f18831a.onPermissionDenied(this.f18832b);
        } else {
            this.f18831a.onPermissionDeniedAndDontAskAgain(this.f18832b);
        }
    }
}
