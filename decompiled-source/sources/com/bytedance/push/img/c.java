package com.bytedance.push.img;

import android.graphics.Bitmap;
import android.net.Uri;
/* compiled from: Request.java */
/* loaded from: classes2.dex */
public class c {
    public final Bitmap.Config config;
    public final int targetHeight;
    public final int targetWidth;
    public final Uri uri;

    public c(Uri uri, int i2, int i3, Bitmap.Config config) {
        this.uri = uri;
        this.targetWidth = i2;
        this.targetHeight = i3;
        this.config = config;
    }

    public boolean hasSize() {
        return this.targetWidth != 0;
    }
}
