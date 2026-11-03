package com.bytedance.common.util;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Build;
/* compiled from: GingerBreadV10Compat.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    static final C0070a f5159a;

    /* compiled from: GingerBreadV10Compat.java */
    /* renamed from: com.bytedance.common.util.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    static class C0070a {
        C0070a() {
        }

        public Bitmap createVideoThumbnail(String str, int i2, int i3) {
            return ThumbnailUtils.createVideoThumbnail(str, i2);
        }

        public Bitmap createVideoThumbnail(String str, int i2, int i3, int i4) {
            return ThumbnailUtils.createVideoThumbnail(str, i2);
        }
    }

    /* compiled from: GingerBreadV10Compat.java */
    /* loaded from: classes2.dex */
    static class b extends C0070a {
        b() {
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x001e A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:13:0x001f A[RETURN] */
        @Override // com.bytedance.common.util.a.C0070a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public android.graphics.Bitmap createVideoThumbnail(java.lang.String r3, int r4, int r5) {
            /*
                r2 = this;
                android.media.MediaMetadataRetriever r4 = new android.media.MediaMetadataRetriever
                r4.<init>()
                r5 = 0
                r4.setDataSource(r3)     // Catch: java.lang.Throwable -> L13 java.lang.Throwable -> L18
                r0 = -1
                android.graphics.Bitmap r3 = r4.getFrameAtTime(r0)     // Catch: java.lang.Throwable -> L13 java.lang.Throwable -> L18
                r4.release()     // Catch: java.lang.RuntimeException -> L1c
                goto L1c
            L13:
                r3 = move-exception
                r4.release()     // Catch: java.lang.RuntimeException -> L17
            L17:
                throw r3
            L18:
                r4.release()     // Catch: java.lang.RuntimeException -> L1b
            L1b:
                r3 = r5
            L1c:
                if (r3 != 0) goto L1f
                return r5
            L1f:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.util.a.b.createVideoThumbnail(java.lang.String, int, int):android.graphics.Bitmap");
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x001e A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:13:0x001f  */
        @Override // com.bytedance.common.util.a.C0070a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public android.graphics.Bitmap createVideoThumbnail(java.lang.String r4, int r5, int r6, int r7) {
            /*
                r3 = this;
                android.media.MediaMetadataRetriever r5 = new android.media.MediaMetadataRetriever
                r5.<init>()
                r0 = 0
                r5.setDataSource(r4)     // Catch: java.lang.Throwable -> L13 java.lang.Throwable -> L18
                r1 = -1
                android.graphics.Bitmap r4 = r5.getFrameAtTime(r1)     // Catch: java.lang.Throwable -> L13 java.lang.Throwable -> L18
                r5.release()     // Catch: java.lang.RuntimeException -> L1c
                goto L1c
            L13:
                r4 = move-exception
                r5.release()     // Catch: java.lang.RuntimeException -> L17
            L17:
                throw r4
            L18:
                r5.release()     // Catch: java.lang.RuntimeException -> L1b
            L1b:
                r4 = r0
            L1c:
                if (r4 != 0) goto L1f
                return r0
            L1f:
                r5 = 1
                android.graphics.Bitmap r4 = android.graphics.Bitmap.createScaledBitmap(r4, r6, r7, r5)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.util.a.b.createVideoThumbnail(java.lang.String, int, int, int):android.graphics.Bitmap");
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 10) {
            f5159a = new b();
        } else {
            f5159a = new C0070a();
        }
    }

    public static Bitmap createVideoThumbnail(String str, int i2, int i3) {
        return f5159a.createVideoThumbnail(str, i2, i3);
    }

    public static Bitmap createVideoThumbnail(String str, int i2, int i3, int i4) {
        return f5159a.createVideoThumbnail(str, i2, i3, i4);
    }
}
