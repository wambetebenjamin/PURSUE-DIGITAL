package com.unity3d.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.PixelCopy;
import android.view.SurfaceView;
import android.view.View;
import androidx.core.view.ViewCompat;
/* loaded from: classes10.dex */
final class J extends View implements PixelCopy.OnPixelCopyFinishedListener {

    /* renamed from: a  reason: collision with root package name */
    Bitmap f18835a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public J(Context context) {
        super(context);
    }

    public final void a(SurfaceView surfaceView) {
        Bitmap createBitmap = Bitmap.createBitmap(surfaceView.getWidth(), surfaceView.getHeight(), Bitmap.Config.ARGB_8888);
        this.f18835a = createBitmap;
        PixelCopy.request(surfaceView, createBitmap, this, new Handler(Looper.getMainLooper()));
    }

    @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
    public final void onPixelCopyFinished(int i2) {
        if (i2 == 0) {
            setBackground(new LayerDrawable(new Drawable[]{new ColorDrawable(ViewCompat.MEASURED_STATE_MASK), new BitmapDrawable(getResources(), this.f18835a)}));
        }
    }
}
