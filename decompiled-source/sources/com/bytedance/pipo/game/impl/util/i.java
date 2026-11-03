package com.bytedance.pipo.game.impl.util;

import android.text.TextUtils;
import android.util.Base64;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
/* compiled from: Security.java */
/* loaded from: classes2.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10549a = "IABUtil/Security";

    /* renamed from: b  reason: collision with root package name */
    private static final String f10550b = "RSA";

    /* renamed from: c  reason: collision with root package name */
    private static final String f10551c = "SHA1withRSA";

    public static boolean a(String str, String str2, String str3) {
        d.a(f10549a, "Security: try verifyPurchase ");
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            d.c(f10549a, "Security: purchase verification failed: missing data.");
            return false;
        }
        return a(a(str), str2, str3);
    }

    public static PublicKey a(String str) {
        d.a(f10549a, "Security: try generatePublicKey");
        try {
            return KeyFactory.getInstance(f10550b).generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        } catch (InvalidKeySpecException e3) {
            d.c(f10549a, "Security: invalid key specification.");
            throw new IllegalArgumentException(e3);
        }
    }

    public static boolean a(PublicKey publicKey, String str, String str2) {
        d.a(f10549a, "Security: try verify");
        try {
            byte[] decode = Base64.decode(str2, 0);
            try {
                Signature signature = Signature.getInstance(f10551c);
                signature.initVerify(publicKey);
                signature.update(str.getBytes());
                if (signature.verify(decode)) {
                    return true;
                }
                d.c(f10549a, "Security: signature verification failed.");
                return false;
            } catch (InvalidKeyException unused) {
                d.c(f10549a, "Security: invalid key specification.");
                return false;
            } catch (NoSuchAlgorithmException unused2) {
                d.c(f10549a, "Security: noSuchAlgorithmException.");
                return false;
            } catch (SignatureException unused3) {
                d.c(f10549a, "Security: Signature exception.");
                return false;
            }
        } catch (IllegalArgumentException unused4) {
            d.c(f10549a, "Security: base64 decoding failed.");
            return false;
        }
    }
}
