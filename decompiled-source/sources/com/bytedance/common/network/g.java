package com.bytedance.common.network;

import com.bytedance.push.utils.h;
import java.io.Closeable;
import java.io.InputStream;
/* compiled from: StreamParser.java */
/* loaded from: classes2.dex */
public class g {
    public static final String CONTENT_TYPE_OCTET = "application/octet-stream";

    /* renamed from: a  reason: collision with root package name */
    private static final String f5088a = "NetworkUtils";

    /* renamed from: b  reason: collision with root package name */
    private static final int f5089b = 5242880;

    public static boolean testIsSSBinary(String str) {
        if (str == null) {
            return false;
        }
        int indexOf = str.indexOf("application/octet-stream");
        if (indexOf >= 0) {
            indexOf = str.indexOf("ssmix=", indexOf + 24);
        }
        return indexOf > 0;
    }

    public static void decodeSSBinary(byte[] bArr, int i2) {
        if (bArr == null || i2 <= 0) {
            return;
        }
        byte[] bArr2 = {-99, -114, Byte.MAX_VALUE, 90};
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) (bArr[i3] ^ bArr2[i3 % 4]);
        }
    }

    public static void safeClose(Closeable closeable) {
        a(closeable, null);
    }

    private static void a(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e2) {
                if (!h.debug() || str == null) {
                    return;
                }
                h.d(f5088a, str + " " + e2);
            }
        }
    }

    public static byte[] response2buf(boolean z, int i2, InputStream inputStream, int[] iArr, f fVar) {
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] readResponse = readResponse(z, i2, inputStream, iArr);
            if (readResponse == null || iArr[0] <= 0) {
                return null;
            }
            return readResponse;
        } catch (Exception e2) {
            if (fVar != null) {
                try {
                    fVar.abort();
                } catch (Throwable unused) {
                }
            }
            throw e2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x008b, code lost:
        if (r6 <= 0) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x008d, code lost:
        r12[0] = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0092, code lost:
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0096, code lost:
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] readResponse(boolean r9, int r10, java.io.InputStream r11, int[] r12) {
        /*
            java.lang.String r0 = "ungzip got exception "
            java.lang.String r1 = "NetworkUtils"
            if (r10 > 0) goto L8
            r10 = 5242880(0x500000, float:7.34684E-39)
        L8:
            r2 = 1048576(0x100000, float:1.469368E-39)
            if (r10 >= r2) goto Ld
            r10 = r2
        Ld:
            r2 = 0
            if (r11 != 0) goto L11
            return r2
        L11:
            if (r9 == 0) goto L19
            java.util.zip.GZIPInputStream r3 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L98
            r3.<init>(r11)     // Catch: java.lang.Throwable -> L98
            r11 = r3
        L19:
            r3 = 8192(0x2000, float:1.148E-41)
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L98
            r4 = 4096(0x1000, float:5.74E-42)
            r5 = 0
            r6 = r5
        L21:
            int r7 = r6 + 4096
            int r8 = r3.length     // Catch: java.io.IOException -> L43 java.io.EOFException -> L72 java.lang.Throwable -> L98
            if (r7 <= r8) goto L2f
            int r7 = r3.length     // Catch: java.io.IOException -> L43 java.io.EOFException -> L72 java.lang.Throwable -> L98
            int r7 = r7 * 2
            byte[] r7 = new byte[r7]     // Catch: java.io.IOException -> L43 java.io.EOFException -> L72 java.lang.Throwable -> L98
            java.lang.System.arraycopy(r3, r5, r7, r5, r6)     // Catch: java.io.IOException -> L43 java.io.EOFException -> L72 java.lang.Throwable -> L98
            r3 = r7
        L2f:
            int r7 = r11.read(r3, r6, r4)     // Catch: java.io.IOException -> L43 java.io.EOFException -> L72 java.lang.Throwable -> L98
            if (r7 <= 0) goto L8b
            int r6 = r6 + r7
            if (r10 <= 0) goto L21
            if (r6 <= r10) goto L21
            java.lang.String r10 = "entity length did exceed given maxLength"
            com.bytedance.push.utils.h.d(r1, r10)     // Catch: java.io.IOException -> L43 java.io.EOFException -> L72 java.lang.Throwable -> L98
            safeClose(r11)
            return r2
        L43:
            r10 = move-exception
            java.lang.String r4 = r10.getMessage()     // Catch: java.lang.Throwable -> L98
            if (r9 == 0) goto L71
            if (r6 <= 0) goto L71
            java.lang.String r9 = "CRC mismatch"
            boolean r9 = r9.equals(r4)     // Catch: java.lang.Throwable -> L98
            if (r9 != 0) goto L5c
            java.lang.String r9 = "Size mismatch"
            boolean r9 = r9.equals(r4)     // Catch: java.lang.Throwable -> L98
            if (r9 == 0) goto L71
        L5c:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L98
            r9.<init>()     // Catch: java.lang.Throwable -> L98
            java.lang.StringBuilder r9 = r9.append(r0)     // Catch: java.lang.Throwable -> L98
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch: java.lang.Throwable -> L98
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L98
            com.bytedance.push.utils.h.d(r1, r9)     // Catch: java.lang.Throwable -> L98
            goto L8b
        L71:
            throw r10     // Catch: java.lang.Throwable -> L98
        L72:
            r10 = move-exception
            if (r9 == 0) goto L97
            if (r6 <= 0) goto L97
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L98
            r9.<init>()     // Catch: java.lang.Throwable -> L98
            java.lang.StringBuilder r9 = r9.append(r0)     // Catch: java.lang.Throwable -> L98
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch: java.lang.Throwable -> L98
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L98
            com.bytedance.push.utils.h.d(r1, r9)     // Catch: java.lang.Throwable -> L98
        L8b:
            if (r6 <= 0) goto L93
            r12[r5] = r6     // Catch: java.lang.Throwable -> L98
            safeClose(r11)
            return r3
        L93:
            safeClose(r11)
            return r2
        L97:
            throw r10     // Catch: java.lang.Throwable -> L98
        L98:
            r9 = move-exception
            safeClose(r11)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.network.g.readResponse(boolean, int, java.io.InputStream, int[]):byte[]");
    }
}
