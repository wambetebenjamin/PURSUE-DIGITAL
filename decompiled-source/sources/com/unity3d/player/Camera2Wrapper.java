package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.view.Surface;
import java.nio.ByteBuffer;
/* loaded from: classes10.dex */
public class Camera2Wrapper implements com.unity3d.player.a.b {

    /* renamed from: a  reason: collision with root package name */
    private Context f18809a;

    /* renamed from: b  reason: collision with root package name */
    private C0439w f18810b = null;

    public Camera2Wrapper(Context context) {
        this.f18809a = context;
        initCamera2Jni();
    }

    private final native void deinitCamera2Jni();

    private final native void initCamera2Jni();

    private final native void nativeFrameReady(Object obj, Object obj2, Object obj3, int i2, int i3, int i4);

    private final native void nativeSurfaceTextureReady(Object obj);

    public final void a() {
        deinitCamera2Jni();
        closeCamera2();
    }

    public final void a(Object obj) {
        nativeSurfaceTextureReady(obj);
    }

    public final void a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i2, int i3, int i4) {
        nativeFrameReady(byteBuffer, byteBuffer2, byteBuffer3, i2, i3, i4);
    }

    protected void closeCamera2() {
        C0439w c0439w = this.f18810b;
        if (c0439w != null) {
            c0439w.a();
        }
        this.f18810b = null;
    }

    protected int getCamera2Count() {
        return C0439w.a(this.f18809a);
    }

    protected int getCamera2FocalLengthEquivalent(int i2) {
        return C0439w.a(this.f18809a, i2);
    }

    protected int[] getCamera2Resolutions(int i2) {
        return C0439w.b(this.f18809a, i2);
    }

    protected int getCamera2SensorOrientation(int i2) {
        return C0439w.c(this.f18809a, i2);
    }

    protected Rect getFrameSizeCamera2() {
        C0439w c0439w = this.f18810b;
        return c0439w != null ? c0439w.c() : new Rect();
    }

    protected boolean initializeCamera2(int i2, int i3, int i4, int i5, int i6, Surface surface) {
        if (this.f18810b != null || UnityPlayer.currentActivity == null) {
            return false;
        }
        C0439w c0439w = new C0439w(this);
        this.f18810b = c0439w;
        return c0439w.a(this.f18809a, i2, i3, i4, i5, i6, surface);
    }

    protected boolean isCamera2AutoFocusPointSupported(int i2) {
        return C0439w.d(this.f18809a, i2);
    }

    protected boolean isCamera2FrontFacing(int i2) {
        return C0439w.e(this.f18809a, i2);
    }

    protected void pauseCamera2() {
        C0439w c0439w = this.f18810b;
        if (c0439w != null) {
            c0439w.d();
        }
    }

    protected boolean setAutoFocusPoint(float f2, float f3) {
        C0439w c0439w = this.f18810b;
        if (c0439w != null) {
            return c0439w.a(f2, f3);
        }
        return false;
    }

    protected void startCamera2() {
        C0439w c0439w = this.f18810b;
        if (c0439w != null) {
            c0439w.h();
        }
    }

    protected void stopCamera2() {
        C0439w c0439w = this.f18810b;
        if (c0439w != null) {
            c0439w.i();
        }
    }
}
