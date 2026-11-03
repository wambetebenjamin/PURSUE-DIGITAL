package com.ss.android.token;

import android.os.Build;
import android.util.Base64;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
/* compiled from: RSAUtils.java */
/* loaded from: classes9.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static String f18525a = "RSA";

    /* renamed from: b  reason: collision with root package name */
    private static String f18526b = "RSA/ECB/PKCS1Padding";

    public static KeyPair generateRSAKeyPair() {
        return generateRSAKeyPair(1024);
    }

    public static KeyPair generateRSAKeyPair(int i2) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(f18525a);
            keyPairGenerator.initialize(i2);
            return keyPairGenerator.genKeyPair();
        } catch (NoSuchAlgorithmException e2) {
            e.monitorError(e2);
            return null;
        }
    }

    public static byte[] encryptData(byte[] bArr, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(f18526b);
            cipher.init(1, publicKey);
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            e.monitorError(e2);
            return null;
        }
    }

    public static byte[] decryptData(byte[] bArr, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(f18526b);
            cipher.init(2, privateKey);
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            e.monitorError(e2);
            return null;
        }
    }

    public static PublicKey getPublicKey(byte[] bArr) {
        return KeyFactory.getInstance(f18525a).generatePublic(new X509EncodedKeySpec(bArr));
    }

    public static PrivateKey getPrivateKey(byte[] bArr) {
        return KeyFactory.getInstance(f18525a).generatePrivate(new PKCS8EncodedKeySpec(bArr));
    }

    public static PublicKey getPublicKey(String str, String str2) {
        return KeyFactory.getInstance(f18525a).generatePublic(new RSAPublicKeySpec(new BigInteger(str), new BigInteger(str2)));
    }

    public static PrivateKey getPrivateKey(String str, String str2) {
        return KeyFactory.getInstance(f18525a).generatePrivate(new RSAPublicKeySpec(new BigInteger(str), new BigInteger(str2)));
    }

    public static PublicKey loadPublicKey(String str) {
        try {
            return (RSAPublicKey) KeyFactory.getInstance(f18525a).generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NullPointerException unused) {
            throw new Exception("key data is null");
        } catch (NoSuchAlgorithmException unused2) {
            throw new Exception("no such algorithm");
        } catch (InvalidKeySpecException unused3) {
            throw new Exception("invalid keySpec");
        }
    }

    public static PrivateKey loadPrivateKey(String str) {
        KeyFactory keyFactory;
        try {
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decode(str, 0));
            if (Build.VERSION.SDK_INT >= 28) {
                keyFactory = KeyFactory.getInstance("RSA");
            } else {
                keyFactory = KeyFactory.getInstance("RSA", "BC");
            }
            return (RSAPrivateKey) keyFactory.generatePrivate(pKCS8EncodedKeySpec);
        } catch (NullPointerException unused) {
            throw new Exception("key data is null");
        } catch (NoSuchAlgorithmException unused2) {
            throw new Exception("no such algorithm");
        } catch (InvalidKeySpecException unused3) {
            throw new Exception("invalid key spec");
        }
    }

    private static String a(InputStream inputStream) {
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    } else if (readLine.charAt(0) != '-') {
                        sb.append(readLine);
                        sb.append('\r');
                    }
                }
                String sb2 = sb.toString();
                try {
                    bufferedReader2.close();
                } catch (Exception e2) {
                    e.monitorError(e2);
                    e2.printStackTrace();
                }
                return sb2;
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e3) {
                        e.monitorError(e3);
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void printPublicKeyInfo(PublicKey publicKey) {
        RSAPublicKey rSAPublicKey = (RSAPublicKey) publicKey;
        System.out.println("----------RSAPublicKey----------");
        System.out.println("Modulus.length=" + rSAPublicKey.getModulus().bitLength());
        System.out.println("Modulus=" + rSAPublicKey.getModulus().toString());
        System.out.println("PublicExponent.length=" + rSAPublicKey.getPublicExponent().bitLength());
        System.out.println("PublicExponent=" + rSAPublicKey.getPublicExponent().toString());
    }

    public static void printPrivateKeyInfo(PrivateKey privateKey) {
        RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) privateKey;
        System.out.println("----------RSAPrivateKey ----------");
        System.out.println("Modulus.length=" + rSAPrivateKey.getModulus().bitLength());
        System.out.println("Modulus=" + rSAPrivateKey.getModulus().toString());
        System.out.println("PrivateExponent.length=" + rSAPrivateKey.getPrivateExponent().bitLength());
        System.out.println("PrivatecExponent=" + rSAPrivateKey.getPrivateExponent().toString());
    }

    public static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i2 = 0; i2 < length; i2 += 2) {
            bArr[i2 / 2] = (byte) ((Character.digit(str.charAt(i2), 16) << 4) + Character.digit(str.charAt(i2 + 1), 16));
        }
        return bArr;
    }

    public static String byteArrayToHex(byte[] bArr) {
        char[] charArray = "0123456789abcdef".toCharArray();
        char[] cArr = new char[bArr.length * 2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = bArr[i2] & 255;
            int i4 = i2 * 2;
            cArr[i4] = charArray[i3 >>> 4];
            cArr[i4 + 1] = charArray[i3 & 15];
        }
        return new String(cArr);
    }
}
