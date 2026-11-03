package com.unity3d.player;

import android.media.Image;
import android.media.ImageReader;
import java.util.concurrent.Semaphore;
/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unity3d.player.u  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
public final class C0435u implements ImageReader.OnImageAvailableListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ C0439w f19054a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0435u(C0439w c0439w) {
        this.f19054a = c0439w;
    }

    @Override // android.media.ImageReader.OnImageAvailableListener
    public final void onImageAvailable(ImageReader imageReader) {
        Semaphore semaphore;
        Semaphore semaphore2;
        Image image;
        com.unity3d.player.a.b bVar;
        semaphore = C0439w.D;
        if (semaphore.tryAcquire()) {
            Image acquireNextImage = imageReader.acquireNextImage();
            if (acquireNextImage != null) {
                Image.Plane[] planes = acquireNextImage.getPlanes();
                if (acquireNextImage.getFormat() == 35 && planes != null && planes.length == 3) {
                    bVar = this.f19054a.f19063a;
                    ((Camera2Wrapper) bVar).a(planes[0].getBuffer(), planes[1].getBuffer(), planes[2].getBuffer(), planes[0].getRowStride(), planes[1].getRowStride(), planes[1].getPixelStride());
                } else {
                    AbstractC0445z.Log(6, "Camera2: Wrong image format.");
                }
                image = this.f19054a.p;
                if (image != null) {
                    image.close();
                }
                this.f19054a.p = acquireNextImage;
            }
            semaphore2 = C0439w.D;
            semaphore2.release();
        }
    }
}
