package com.bytedance.bdinstall.util;

import android.text.TextUtils;
import android.util.Base64;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/* compiled from: StringEncryptUtils.java */
/* loaded from: classes.dex */
public class s {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4080a = "SHA-256";

    /* renamed from: b  reason: collision with root package name */
    private static final String f4081b = "NIfb&95GUY86Gfgh";

    /* renamed from: c  reason: collision with root package name */
    private static String f4082c;

    public static String a(String str) {
        return a(str, "SHA-256");
    }

    public static String a(String str, String str2) {
        byte[] bytes = str.getBytes();
        try {
            if (TextUtils.isEmpty(str2)) {
                str2 = "SHA-256";
            }
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(bytes);
            return a(messageDigest.digest());
        } catch (NoSuchAlgorithmException | Exception unused) {
            return null;
        }
    }

    private static String a(byte[] bArr) {
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

    public static byte[] b(String str, String str2) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] bytes = str.getBytes();
            int length = bytes.length;
            if (length % blockSize != 0) {
                length += blockSize - (length % blockSize);
            }
            byte[] bArr = new byte[length];
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            cipher.init(1, new SecretKeySpec(str2.getBytes(), "AES"), new IvParameterSpec(f4081b.getBytes()));
            return Base64.encode(cipher.doFinal(bArr), 0);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String a(byte[] bArr, String str) {
        try {
            byte[] decode = Base64.decode(bArr, 0);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(2, new SecretKeySpec(str.getBytes(), "AES"), new IvParameterSpec(f4081b.getBytes()));
            return new String(cipher.doFinal(decode)).trim();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static Key b(byte[] bArr) {
        return SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(bArr));
    }

    public static String a() {
        if (TextUtils.isEmpty(f4082c)) {
            f4082c = b("Ynl0ZWRhbmNl");
        }
        return f4082c;
    }

    public static String b(String str) {
        try {
            return new String(Base64.decode(str.getBytes("UTF-8"), 2));
        } catch (Exception unused) {
            return "";
        }
    }
}
