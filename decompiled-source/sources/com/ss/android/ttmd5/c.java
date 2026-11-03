package com.ss.android.ttmd5;

import android.support.v4.media.session.PlaybackStateCompat;
import com.facebook.internal.security.CertificateUtil;
import com.meituan.robust.Constants;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.MessageDigest;
import java.util.Objects;
/* compiled from: TTMd5.java */
/* loaded from: classes9.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18600a = "TTMd5";

    /* renamed from: b  reason: collision with root package name */
    private static final String f18601b = "ttmd5";

    /* renamed from: c  reason: collision with root package name */
    private static final int f18602c = 1;

    /* renamed from: d  reason: collision with root package name */
    private static final int f18603d = 1;

    /* renamed from: e  reason: collision with root package name */
    private static final char[] f18604e = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: f  reason: collision with root package name */
    private static final int f18605f = 9;

    /* renamed from: g  reason: collision with root package name */
    private static final int f18606g = 8192;

    /* compiled from: TTMd5.java */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface a {
        public static final int FILE_NOT_EXIST = 5;
        public static final int GET_FILE_MD5_ERROR = 6;
        public static final int MD5_EMPTY = 2;
        public static final int MD5_MATCH = 0;
        public static final int MD5_NOT_MATCH = 1;
        public static final int TTMD5_TAG_PARSER_ERROR = 4;
        public static final int TTMD5_VERSION_NOT_SUPPORT = 3;
        public static final int UNKNOWN_ERROR = 99;
    }

    public static String ttmd5(File file) {
        return ttmd5(file, 9, (long) PlaybackStateCompat.ACTION_PLAY_FROM_URI);
    }

    public static String ttmd5(File file, int i2) {
        return ttmd5(file, i2, (long) PlaybackStateCompat.ACTION_PLAY_FROM_URI);
    }

    public static String ttmd5(File file, int i2, long j2) {
        if (file != null) {
            try {
                if (file.exists()) {
                    return a(file, i2, j2);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return "";
    }

    public static String md5(File file) {
        return ttmd5(file, -1, -1L);
    }

    public static String ttmd5(com.ss.android.ttmd5.b bVar) {
        return ttmd5(bVar, 9, (long) PlaybackStateCompat.ACTION_PLAY_FROM_URI);
    }

    public static String ttmd5(com.ss.android.ttmd5.b bVar, int i2) {
        return ttmd5(bVar, i2, (long) PlaybackStateCompat.ACTION_PLAY_FROM_URI);
    }

    public static String ttmd5(com.ss.android.ttmd5.b bVar, int i2, long j2) {
        if (bVar == null) {
            return "";
        }
        try {
            return a(bVar, i2, j2);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static int checkMd5(String str, File file) {
        return checkMd5(str, file, null);
    }

    public static int checkMd5(String str, File file, com.ss.android.ttmd5.b bVar) {
        String a2;
        if (str == null || str.length() == 0) {
            return 2;
        }
        try {
            if (bVar != null) {
                if (bVar.length() <= 0) {
                    try {
                        bVar.close();
                    } catch (Throwable unused) {
                    }
                    return 5;
                }
            } else if (file == null || !file.exists()) {
                return 5;
            }
            int i2 = -1;
            long j2 = -1;
            try {
                b a3 = a(str);
                if (a3 != null) {
                    if (a3.f18607a > 1) {
                        return 3;
                    }
                    i2 = a3.f18609c;
                    j2 = a3.f18610d;
                }
                b bVar2 = null;
                if (bVar != null) {
                    a2 = a(bVar, i2, j2);
                } else {
                    a2 = a(file, i2, j2);
                }
                if (a2 != null && a2.length() != 0) {
                    if (a3 != null && (a3.f18607a != 1 || a3.f18608b != 1)) {
                        if (a3.f18611e != null) {
                            try {
                                bVar2 = a(a2);
                            } catch (Throwable unused2) {
                            }
                            if (bVar2 != null && a3.f18609c == bVar2.f18609c && a3.f18610d == bVar2.f18610d && a3.f18611e.equals(bVar2.f18611e)) {
                                return 0;
                            }
                        }
                    }
                    return a2.equals(str) ? 0 : 1;
                }
                return 6;
            } catch (Throwable unused3) {
                return 4;
            }
        } catch (Throwable unused4) {
            return 99;
        }
    }

    public static int checkMd5(File file, File file2) {
        if (file == null || file2 == null) {
            return 5;
        }
        try {
            if (file.exists() && file2.exists()) {
                if (file == file2) {
                    return 0;
                }
                return a(file, 9, (long) PlaybackStateCompat.ACTION_PLAY_FROM_URI).equals(a(file2, 9, (long) PlaybackStateCompat.ACTION_PLAY_FROM_URI)) ? 0 : 1;
            }
            return 5;
        } catch (Throwable th) {
            th.printStackTrace();
            return 99;
        }
    }

    private static String a(File file, int i2, long j2) {
        return a(new com.ss.android.ttmd5.a(file), i2, j2);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:5|6|(6:13|14|(3:16|(1:18)|19)|(1:21)|22|(4:31|32|33|34)(3:26|27|28))|38|14|(0)|(0)|22|(1:24)|31|32|33|34) */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003f A[Catch: all -> 0x009d, TryCatch #3 {all -> 0x009d, blocks: (B:6:0x000d, B:10:0x001a, B:15:0x002d, B:17:0x003f, B:19:0x004b, B:21:0x0062, B:22:0x006c, B:28:0x007e), top: B:43:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0062 A[Catch: all -> 0x009d, TryCatch #3 {all -> 0x009d, blocks: (B:6:0x000d, B:10:0x001a, B:15:0x002d, B:17:0x003f, B:19:0x004b, B:21:0x0062, B:22:0x006c, B:28:0x007e), top: B:43:0x000d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(com.ss.android.ttmd5.b r21, int r22, long r23) {
        /*
            r0 = r22
            java.lang.String r1 = "MD5"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)
            if (r1 != 0) goto Ld
            java.lang.String r0 = ""
            return r0
        Ld:
            long r9 = r21.length()     // Catch: java.lang.Throwable -> L9d
            r11 = 1
            if (r0 <= 0) goto L2b
            r2 = 0
            int r2 = (r23 > r2 ? 1 : (r23 == r2 ? 0 : -1))
            if (r2 <= 0) goto L2b
            long r2 = (long) r0     // Catch: java.lang.Throwable -> L9d
            long r2 = r2 * r23
            r4 = 8
            long r4 = r4 * r9
            r6 = 10
            long r4 = r4 / r6
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L28
            goto L2b
        L28:
            r12 = r23
            goto L2d
        L2b:
            r12 = r9
            r0 = r11
        L2d:
            r2 = 8192(0x2000, float:1.148E-41)
            byte[] r14 = new byte[r2]     // Catch: java.lang.Throwable -> L9d
            r15 = 0
            r2 = r21
            r3 = r1
            r4 = r14
            r5 = r15
            r7 = r12
            a(r2, r3, r4, r5, r7)     // Catch: java.lang.Throwable -> L9d
            r2 = 2
            if (r0 <= r2) goto L60
            long r2 = (long) r0     // Catch: java.lang.Throwable -> L9d
            long r2 = r2 * r12
            long r2 = r9 - r2
            int r7 = r0 + (-1)
            long r4 = (long) r7     // Catch: java.lang.Throwable -> L9d
            long r17 = r2 / r4
            r8 = r11
        L49:
            if (r8 >= r7) goto L60
            long r2 = r12 + r17
            long r15 = r15 + r2
            r2 = r21
            r3 = r1
            r4 = r14
            r5 = r15
            r19 = r7
            r20 = r8
            r7 = r12
            a(r2, r3, r4, r5, r7)     // Catch: java.lang.Throwable -> L9d
            int r8 = r20 + 1
            r7 = r19
            goto L49
        L60:
            if (r0 <= r11) goto L6c
            long r5 = r9 - r12
            r2 = r21
            r3 = r1
            r4 = r14
            r7 = r12
            a(r2, r3, r4, r5, r7)     // Catch: java.lang.Throwable -> L9d
        L6c:
            byte[] r1 = r1.digest()     // Catch: java.lang.Throwable -> L9d
            java.lang.String r1 = a(r1)     // Catch: java.lang.Throwable -> L9d
            if (r0 != r11) goto L7e
            int r2 = (r12 > r9 ? 1 : (r12 == r9 ? 0 : -1))
            if (r2 != 0) goto L7e
            r21.close()     // Catch: java.lang.Throwable -> L7d
        L7d:
            return r1
        L7e:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9d
            r2.<init>()     // Catch: java.lang.Throwable -> L9d
            java.lang.String r0 = a(r0, r12)     // Catch: java.lang.Throwable -> L9d
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch: java.lang.Throwable -> L9d
            java.lang.String r2 = ";"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch: java.lang.Throwable -> L9d
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> L9d
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L9d
            r21.close()     // Catch: java.lang.Throwable -> L9c
        L9c:
            return r0
        L9d:
            r0 = move-exception
            r21.close()     // Catch: java.lang.Throwable -> La1
        La1:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.ttmd5.c.a(com.ss.android.ttmd5.b, int, long):java.lang.String");
    }

    private static void a(com.ss.android.ttmd5.b bVar, MessageDigest messageDigest, byte[] bArr, long j2, long j3) {
        bVar.seek(j2, j3);
        long j4 = 0;
        while (j4 < j3) {
            int read = bVar.read(bArr, 0, (int) Math.min(j3 - j4, bArr.length));
            if (read <= 0) {
                throw new IOException("updateSample unexpected readCount <= 0, readCount = " + read + ", readTotalCount = " + j4 + ", sampleSize = " + j3);
            }
            messageDigest.update(bArr, 0, read);
            j4 += read;
        }
    }

    private static String a(byte[] bArr) {
        Objects.requireNonNull(bArr, "bytes is null");
        int length = bArr.length;
        int i2 = length * 2;
        char[] cArr = new char[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = bArr[i4 + 0] & 255;
            int i6 = i3 + 1;
            char[] cArr2 = f18604e;
            cArr[i3] = cArr2[i5 >> 4];
            i3 = i6 + 1;
            cArr[i6] = cArr2[i5 & 15];
        }
        return new String(cArr, 0, i2);
    }

    private static String a(int i2, long j2) {
        return "ttmd5:1:1:" + a(i2) + "g" + a(j2);
    }

    private static b a(String str) {
        if (str.startsWith("ttmd5:")) {
            String[] split = str.split(Constants.PACKNAME_END);
            String[] split2 = split[0].split(CertificateUtil.DELIMITER);
            b bVar = new b();
            bVar.f18607a = Integer.parseInt(split2[1]);
            if (bVar.f18607a > 1) {
                return bVar;
            }
            bVar.f18608b = Integer.parseInt(split2[2]);
            String[] split3 = split2[3].split("g");
            bVar.f18609c = (int) b(split3[0]);
            bVar.f18610d = b(split3[1]);
            bVar.f18611e = split[1];
            return bVar;
        }
        return null;
    }

    private static String a(long j2) {
        return Long.toHexString((j2 << 4) + 31);
    }

    private static long b(String str) {
        return (Long.parseLong(str, 16) - 31) >> 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TTMd5.java */
    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private int f18607a;

        /* renamed from: b  reason: collision with root package name */
        private int f18608b;

        /* renamed from: c  reason: collision with root package name */
        private int f18609c;

        /* renamed from: d  reason: collision with root package name */
        private long f18610d;

        /* renamed from: e  reason: collision with root package name */
        private String f18611e;

        private b() {
        }
    }
}
