package com.bytedance.geckox.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Objects;
/* compiled from: MD5Utils.java */
/* loaded from: classes2.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    static final char[] f9367a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static void a(InputStream inputStream, String str) {
        if (str == null) {
            throw new RuntimeException("md5 check failed: md5 == null");
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            try {
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = inputStream.read(bArr, 0, 4096);
                        if (read == -1) {
                            break;
                        }
                        messageDigest.update(bArr, 0, read);
                    }
                    try {
                        byte[] digest = messageDigest.digest();
                        String a2 = a(digest, 0, digest.length);
                        if (!str.equals(a2)) {
                            throw new RuntimeException("md5 check failed file: local md5:" + a2 + " expect md5:" + str);
                        }
                    } catch (Exception e2) {
                        throw new RuntimeException("md5 check failed:" + e2.getMessage(), e2);
                    }
                } catch (Exception e3) {
                    throw new RuntimeException("md5 check failed:" + e3.getMessage(), e3);
                }
            } finally {
                d.close(inputStream);
            }
        } catch (Exception e4) {
            throw new RuntimeException("md5 check failed:" + e4.getMessage(), e4);
        }
    }

    public static void a(File file, String str) {
        FileInputStream fileInputStream;
        if (!file.isFile() || str == null) {
            throw new RuntimeException("md5 check failed: file.isFile() == false || md5 == null");
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream2 = null;
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = fileInputStream.read(bArr, 0, 4096);
                    if (read == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                }
                d.close(fileInputStream);
                try {
                    byte[] digest = messageDigest.digest();
                    String a2 = a(digest, 0, digest.length);
                    if (!str.equals(a2)) {
                        throw new RuntimeException("md5 check failed file:" + file.getAbsolutePath() + " local md5:" + a2 + " expect md5:" + str);
                    }
                } catch (Exception e3) {
                    throw new RuntimeException("md5 check failed:" + e3.getMessage(), e3);
                }
            } catch (Exception e4) {
                e = e4;
                fileInputStream2 = fileInputStream;
                throw new RuntimeException("md5 check failed:" + e.getMessage(), e);
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                d.close(fileInputStream2);
                throw th;
            }
        } catch (Exception e5) {
            throw new RuntimeException("md5 check failed:" + e5.getMessage(), e5);
        }
    }

    private static String a(byte[] bArr, int i2, int i3) {
        Objects.requireNonNull(bArr, "bytes is null");
        if (i2 < 0 || i2 + i3 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        int i4 = i3 * 2;
        char[] cArr = new char[i4];
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = bArr[i6 + i2] & 255;
            int i8 = i5 + 1;
            char[] cArr2 = f9367a;
            cArr[i5] = cArr2[i7 >> 4];
            i5 = i8 + 1;
            cArr[i8] = cArr2[i7 & 15];
        }
        return new String(cArr, 0, i4);
    }
}
