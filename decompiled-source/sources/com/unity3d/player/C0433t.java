package com.unity3d.player;

import android.hardware.camera2.CameraDevice;
import java.util.concurrent.Semaphore;
/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unity3d.player.t  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
public final class C0433t extends CameraDevice.StateCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ C0439w f19051a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0433t(C0439w c0439w) {
        this.f19051a = c0439w;
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public final void onClosed(CameraDevice cameraDevice) {
        Semaphore semaphore;
        semaphore = C0439w.D;
        semaphore.release();
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public final void onDisconnected(CameraDevice cameraDevice) {
        Semaphore semaphore;
        AbstractC0445z.Log(5, "Camera2: CameraDevice disconnected.");
        this.f19051a.a(cameraDevice);
        semaphore = C0439w.D;
        semaphore.release();
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public final void onError(CameraDevice cameraDevice, int i2) {
        Semaphore semaphore;
        AbstractC0445z.Log(6, "Camera2: Error opeining CameraDevice " + i2);
        this.f19051a.a(cameraDevice);
        semaphore = C0439w.D;
        semaphore.release();
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public final void onOpened(CameraDevice cameraDevice) {
        Semaphore semaphore;
        this.f19051a.f19064b = cameraDevice;
        semaphore = C0439w.D;
        semaphore.release();
    }
}
