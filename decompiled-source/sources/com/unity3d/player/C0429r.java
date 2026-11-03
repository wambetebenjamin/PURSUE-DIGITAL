package com.unity3d.player;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unity3d.player.r  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
public final class C0429r extends CameraCaptureSession.CaptureCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ C0439w f19045a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0429r(C0439w c0439w) {
        this.f19045a = c0439w;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public final void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
        this.f19045a.a(captureRequest.getTag());
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public final void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
        AbstractC0445z.Log(5, "Camera2: Capture session failed " + captureRequest.getTag() + " reason " + captureFailure.getReason());
        this.f19045a.a(captureRequest.getTag());
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public final void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i2) {
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public final void onCaptureSequenceCompleted(CameraCaptureSession cameraCaptureSession, int i2, long j2) {
    }
}
