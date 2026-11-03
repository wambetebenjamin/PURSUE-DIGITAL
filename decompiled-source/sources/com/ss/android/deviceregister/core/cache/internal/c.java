package com.ss.android.deviceregister.core.cache.internal;

import android.text.TextUtils;
import android.util.Base64;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/* compiled from: EncryptUtils.java */
/* loaded from: classes9.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private static String f18184a;

    public static byte[] encrypt(String str, String str2) {
        SecureRandom secureRandom = new SecureRandom();
        Key a2 = a(str2.getBytes());
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(1, a2, secureRandom);
        return Base64.encode(cipher.doFinal(str.getBytes("UTF-8")), 0);
    }

    public static String decrypt(byte[] bArr, String str) {
        byte[] decode = Base64.decode(bArr, 0);
        SecureRandom secureRandom = new SecureRandom();
        Key a2 = a(str.getBytes());
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, a2, secureRandom);
        return new String(cipher.doFinal(decode), "UTF-8");
    }

    private static Key a(byte[] bArr) {
        return SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(bArr));
    }

    private static String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; bArr != null && i2 < bArr.length; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    private static byte[] a(String str) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        byte[] bArr = new byte[length / 2];
        for (int i2 = 0; i2 < length; i2 += 2) {
            bArr[i2 / 2] = (byte) Integer.parseInt(new String(bytes, i2, 2), 16);
        }
        return bArr;
    }

    public static String getBytedanceString() {
        if (TextUtils.isEmpty(f18184a)) {
            f18184a = base64DecodeToString("Ynl0ZWRhbmNl");
        }
        return f18184a;
    }

    public static String base64DecodeToString(String str) {
        try {
            return new String(Base64.decode(str.getBytes("UTF-8"), 2));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String[] genRandomKeyAndIv() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = new SecureRandom();
            keyGenerator.init(128, secureRandom);
            byte[] bArr = new byte[8];
            secureRandom.nextBytes(bArr);
            String[] strArr = {b(keyGenerator.generateKey().getEncoded()), b(bArr)};
            if (isValidKeyIv(strArr)) {
                return strArr;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean isValidKeyIv(String[] strArr) {
        return strArr != null && strArr.length == 2 && !TextUtils.isEmpty(strArr[0]) && strArr[0].length() == 32 && !TextUtils.isEmpty(strArr[1]) && strArr[1].length() == 16;
    }

    private static byte[] b(String str) {
        int length = str.length();
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i2] = (byte) str.charAt(i2);
        }
        return bArr;
    }

    public static byte[] decryptAesCbc(byte[] bArr, String str, String str2) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING");
            cipher.init(2, new SecretKeySpec(b(str), "AES"), new IvParameterSpec(b(str2)));
            return cipher.doFinal(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
        if (r2 == null) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] gzipUncompress(byte[] r6) {
        /*
            r0 = 0
            if (r6 == 0) goto L52
            int r1 = r6.length
            if (r1 > 0) goto L7
            goto L52
        L7:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L44
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L44
            java.util.zip.GZIPInputStream r6 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L45
            r6.<init>(r2)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L45
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L2e
        L1a:
            int r3 = r6.read(r0)     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L2e
            if (r3 < 0) goto L25
            r4 = 0
            r1.write(r0, r4, r3)     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L2e
            goto L1a
        L25:
            r6.close()     // Catch: java.io.IOException -> L28
        L28:
            r2.close()     // Catch: java.io.IOException -> L4d
            goto L4d
        L2c:
            r0 = move-exception
            goto L39
        L2e:
            r0 = r6
            goto L45
        L30:
            r6 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L39
        L35:
            r6 = move-exception
            r2 = r0
            r0 = r6
            r6 = r2
        L39:
            if (r6 == 0) goto L3e
            r6.close()     // Catch: java.io.IOException -> L3e
        L3e:
            if (r2 == 0) goto L43
            r2.close()     // Catch: java.io.IOException -> L43
        L43:
            throw r0
        L44:
            r2 = r0
        L45:
            if (r0 == 0) goto L4a
            r0.close()     // Catch: java.io.IOException -> L4a
        L4a:
            if (r2 == 0) goto L4d
            goto L28
        L4d:
            byte[] r6 = r1.toByteArray()
            return r6
        L52:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.deviceregister.core.cache.internal.c.gzipUncompress(byte[]):byte[]");
    }
}
