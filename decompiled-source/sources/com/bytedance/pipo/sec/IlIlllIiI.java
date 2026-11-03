package com.bytedance.pipo.sec;

import com.bytedance.common.utility.n;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import pp.g.p.f.n.d;
/* loaded from: classes2.dex */
public class IlIlllIiI {
    static {
        try {
            System.loadLibrary("ppsec");
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    private static native Object IlIllLIiI(int i2, long j2, Object... objArr);

    private static native String IlIllLIiI();

    private static native void IlIllLIiI(String str);

    private static native void IlIllLIiI(String str, int i2, boolean z);

    private static native void IlIllLIiI(String str, String str2);

    private static native void IlIllLIiII(String str);

    private static native void IlIllLIiII(String str, String str2);

    private static Object IlIllLIiL(int i2, long j2, Object... objArr) {
        return "error";
    }

    private static Object IlIllLIiL(String str, byte[] bArr, Map<String, Object> map, Map<String, Object> map2) {
        d.a.f21122a.getClass();
        HttpNetworkUtils.a(str, bArr, map, map2);
        return "ok";
    }

    public static String a() {
        return IlIllLIiI();
    }

    public static void a(String str) {
        IlIllLIiI(str);
    }

    public static void a(String str, int i2, boolean z) {
        IlIllLIiI(str, i2, z);
    }

    public static void a(String str, String str2) {
        IlIllLIiI(str, str2);
    }

    public static void b(String str) {
        IlIllLIiII(str);
    }

    public static void b(String str, String str2) {
        IlIllLIiII(str, str2);
    }

    private static byte[] IlIllLIiL(int i2, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        Cipher cipher;
        byte[] bArr4 = new byte[0];
        if (bArr.length == 16 && bArr2.length == 16) {
            if (bArr3.length != 0) {
                try {
                    cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    cipher.init(i2, new SecretKeySpec(bArr, "AES"), new IvParameterSpec(bArr2));
                } catch (Exception unused) {
                    return bArr4;
                }
            }
            return cipher.doFinal(bArr3);
        }
        return bArr4;
    }

    private static byte[] IlIllLIiL(byte[] bArr) {
        try {
            return MessageDigest.getInstance(n.SHA_1).digest(bArr);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
