package com.bytedance.reader_apk;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;
/* compiled from: PayloadReader.java */
/* loaded from: classes2.dex */
public final class e {
    private e() {
    }

    public static String[] getString(File file, int[] iArr) {
        byte[][] bArr = get(file, iArr);
        if (bArr == null) {
            return null;
        }
        String[] strArr = new String[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            try {
                if (bArr[i2] != null) {
                    strArr[i2] = new String(bArr[i2], "UTF-8");
                } else {
                    strArr[i2] = "";
                }
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        return strArr;
    }

    public static byte[][] get(File file, int[] iArr) {
        Map<Integer, ByteBuffer> a2 = a(file);
        if (a2 == null || iArr.length <= 0) {
            return null;
        }
        byte[][] bArr = new byte[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            ByteBuffer byteBuffer = a2.get(Integer.valueOf(iArr[i2]));
            if (byteBuffer != null) {
                bArr[i2] = a(byteBuffer);
            }
        }
        return bArr;
    }

    private static byte[] a(ByteBuffer byteBuffer) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        return Arrays.copyOfRange(array, byteBuffer.position() + arrayOffset, arrayOffset + byteBuffer.limit());
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x003f, code lost:
        if (r1 == null) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.Map<java.lang.Integer, java.nio.ByteBuffer> a(java.io.File r3) {
        /*
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L2a java.io.IOException -> L38
            java.lang.String r2 = "r"
            r1.<init>(r3, r2)     // Catch: java.lang.Throwable -> L2a java.io.IOException -> L38
            java.nio.channels.FileChannel r3 = r1.getChannel()     // Catch: java.lang.Throwable -> L25 java.io.IOException -> L28
            com.bytedance.reader_apk.c r2 = com.bytedance.reader_apk.a.findApkSigningBlock(r3)     // Catch: java.lang.Throwable -> L23 java.io.IOException -> L3a
            java.lang.Object r2 = r2.getFirst()     // Catch: java.lang.Throwable -> L23 java.io.IOException -> L3a
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2     // Catch: java.lang.Throwable -> L23 java.io.IOException -> L3a
            java.util.Map r0 = com.bytedance.reader_apk.a.findIdValues(r2)     // Catch: java.lang.Throwable -> L23 java.io.IOException -> L3a
            if (r3 == 0) goto L1f
            r3.close()     // Catch: java.io.IOException -> L1f java.lang.Throwable -> L42
        L1f:
            r1.close()     // Catch: java.lang.Throwable -> L42
            goto L42
        L23:
            r2 = move-exception
            goto L2d
        L25:
            r2 = move-exception
            r3 = r0
            goto L2d
        L28:
            r3 = r0
            goto L3a
        L2a:
            r2 = move-exception
            r3 = r0
            r1 = r3
        L2d:
            if (r3 == 0) goto L32
            r3.close()     // Catch: java.io.IOException -> L32
        L32:
            if (r1 == 0) goto L37
            r1.close()     // Catch: java.io.IOException -> L37
        L37:
            throw r2     // Catch: java.lang.Throwable -> L42
        L38:
            r3 = r0
            r1 = r3
        L3a:
            if (r3 == 0) goto L3f
            r3.close()     // Catch: java.io.IOException -> L3f
        L3f:
            if (r1 == 0) goto L42
            goto L1f
        L42:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.reader_apk.e.a(java.io.File):java.util.Map");
    }
}
