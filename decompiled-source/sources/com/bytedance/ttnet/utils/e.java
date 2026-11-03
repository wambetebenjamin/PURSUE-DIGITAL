package com.bytedance.ttnet.utils;

import android.util.Base64;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/* compiled from: RsaDecoder.java */
/* loaded from: classes8.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15540a = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQwuhlG2hSvAH1pxaCawFQTHQPEa+MLm0muwhkPd6hCTxmodQOCTbwWzaZazk8GDtV4AwBAM8jIeHP7z9bYxmZeT/lKjgvbplEWURevOQ4O6rYahjL8i4vyf431ocW+POx+kQbJ8Tr4EAuqjEUmP2nw6WUQ6/hMjQv/CS6SfqpEQIDAQAB";

    public static String a(String str) {
        PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(f15540a, 0)));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, generatePublic);
        return new String(cipher.doFinal(Base64.decode(str, 0)));
    }

    public static byte[] a(byte[] bArr) {
        PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(f15540a, 0)));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, generatePublic);
        return cipher.doFinal(bArr);
    }

    public static byte[] a(byte[] bArr, String str) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, secretKeySpec);
        byte[] doFinal = cipher.doFinal(str.getBytes("UTF-8"));
        byte[] bArr2 = new byte[doFinal.length + 16];
        System.arraycopy(cipher.getIV(), 0, bArr2, 0, 16);
        System.arraycopy(doFinal, 0, bArr2, 16, doFinal.length);
        return bArr2;
    }
}
