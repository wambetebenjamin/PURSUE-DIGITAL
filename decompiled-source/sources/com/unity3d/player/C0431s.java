package com.unity3d.player;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.util.Range;
import android.view.Surface;
/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unity3d.player.s  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
public final class C0431s extends CameraCaptureSession.StateCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ C0439w f19048a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0431s(C0439w c0439w) {
        this.f19048a = c0439w;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public final void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
        AbstractC0445z.Log(6, "Camera2: CaptureSession configuration failed.");
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public void onConfigured(CameraCaptureSession cameraCaptureSession) {
        CameraDevice cameraDevice;
        Object obj;
        StringBuilder append;
        CameraDevice cameraDevice2;
        CaptureRequest.Builder builder;
        Surface surface;
        CaptureRequest.Builder builder2;
        Range range;
        C0439w c0439w = this.f19048a;
        cameraDevice = c0439w.f19064b;
        if (cameraDevice == null) {
            return;
        }
        obj = c0439w.s;
        synchronized (obj) {
            C0439w c0439w2 = this.f19048a;
            c0439w2.r = cameraCaptureSession;
            try {
                try {
                    cameraDevice2 = c0439w2.f19064b;
                    c0439w2.q = cameraDevice2.createCaptureRequest(1);
                    C0439w c0439w3 = this.f19048a;
                    builder = c0439w3.q;
                    surface = c0439w3.v;
                    builder.addTarget(surface);
                    C0439w c0439w4 = this.f19048a;
                    builder2 = c0439w4.q;
                    CaptureRequest.Key key = CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE;
                    range = c0439w4.n;
                    builder2.set(key, range);
                    this.f19048a.g();
                } catch (IllegalStateException e2) {
                    append = new StringBuilder("Camera2: IllegalStateException ").append(e2);
                    AbstractC0445z.Log(6, append.toString());
                }
            } catch (CameraAccessException e3) {
                append = new StringBuilder("Camera2: CameraAccessException ").append(e3);
                AbstractC0445z.Log(6, append.toString());
            }
        }
    }
}
