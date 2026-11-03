package com.bytedance.ug.sdk.share.impl.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import java.io.ByteArrayOutputStream;
/* compiled from: ImageUtils.java */
/* loaded from: classes8.dex */
public class h {
    public static Bitmap compressBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        float sqrt = (float) Math.sqrt(512000.0f / byteArrayOutputStream.toByteArray().length);
        Matrix matrix = new Matrix();
        if (sqrt >= 1.0f) {
            return bitmap;
        }
        matrix.setScale(sqrt, sqrt);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x00e4: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:52:0x00e4 */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00e7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] compressBitmapToByteArray(android.graphics.Bitmap r12, int r13) {
        /*
            Method dump skipped, instructions count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.ug.sdk.share.impl.utils.h.compressBitmapToByteArray(android.graphics.Bitmap, int):byte[]");
    }
}
