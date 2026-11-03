package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
@JNINamespace("net::android")
/* loaded from: classes9.dex */
public class AndroidKeyStore {
    private static final String TAG = "AndroidKeyStore";

    private static String getPrivateKeyClassName(PrivateKey privateKey) {
        return privateKey.getClass().getName();
    }

    private static boolean privateKeySupportsSignature(PrivateKey privateKey, String str) {
        try {
            Signature.getInstance(str).initSign(privateKey);
            return true;
        } catch (InvalidKeyException | NoSuchAlgorithmException unused) {
            return false;
        } catch (Exception e2) {
            Log.e(TAG, "Exception while checking support for " + str + ": " + e2, new Object[0]);
            return false;
        }
    }

    private static boolean privateKeySupportsCipher(PrivateKey privateKey, String str) {
        try {
            Cipher.getInstance(str).init(1, privateKey);
            return true;
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException unused) {
            return false;
        } catch (Exception e2) {
            Log.e(TAG, "Exception while checking support for " + str + ": " + e2, new Object[0]);
            return false;
        }
    }

    private static byte[] signWithPrivateKey(PrivateKey privateKey, String str, byte[] bArr) {
        try {
            Signature signature = Signature.getInstance(str);
            try {
                signature.initSign(privateKey);
                signature.update(bArr);
                return signature.sign();
            } catch (Exception e2) {
                Log.e(TAG, "Exception while signing message with " + str + " and " + privateKey.getAlgorithm() + " private key (" + privateKey.getClass().getName() + "): " + e2, new Object[0]);
                return null;
            }
        } catch (NoSuchAlgorithmException e3) {
            Log.e(TAG, "Signature algorithm " + str + " not supported: " + e3, new Object[0]);
            return null;
        }
    }

    private static byte[] encryptWithPrivateKey(PrivateKey privateKey, String str, byte[] bArr) {
        try {
            Cipher cipher = Cipher.getInstance(str);
            try {
                cipher.init(1, privateKey);
                return cipher.doFinal(bArr);
            } catch (Exception e2) {
                Log.e(TAG, "Exception while encrypting input with " + str + " and " + privateKey.getAlgorithm() + " private key (" + privateKey.getClass().getName() + "): " + e2, new Object[0]);
                return null;
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e3) {
            Log.e(TAG, "Cipher " + str + " not supported: " + e3, new Object[0]);
            return null;
        }
    }
}
