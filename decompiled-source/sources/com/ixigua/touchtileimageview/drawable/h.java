package com.ixigua.touchtileimageview.drawable;

import android.graphics.BitmapRegionDecoder;
import java.io.InputStream;
/* compiled from: PictureRegionDecoderFactory.java */
/* loaded from: classes9.dex */
public interface h {
    public static final h DEFAULT_INSTANCE = new h() { // from class: com.ixigua.touchtileimageview.drawable.h.1
        @Override // com.ixigua.touchtileimageview.drawable.h
        public g create(InputStream inputStream) {
            return new a(BitmapRegionDecoder.newInstance(inputStream, false));
        }
    };

    g create(InputStream inputStream);
}
