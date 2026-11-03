package com.bytedance.common.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* compiled from: StringEncryptUtils.java */
/* loaded from: classes2.dex */
public final class n {
    public static final String MD5 = "MD5";
    public static final String SHA_1 = "SHA-1";
    public static final String SHA_256 = "SHA-256";

    private n() {
    }

    public static String encryptBySHA256(String str) {
        return encrypt(str, SHA_256);
    }

    public static String encrypt(String str, String str2) {
        byte[] bytes = str.getBytes();
        try {
            if (o.isEmpty(str2)) {
                str2 = SHA_256;
            }
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(bytes);
            return bytes2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException | Exception unused) {
            return null;
        }
    }

    public static String bytes2Hex(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            int length = bArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                sb.append(String.format("%02x", Byte.valueOf(bArr[i2])));
            }
            return sb.toString();
        } catch (Throwable unused) {
            return null;
        }
    }
}
