package com.linecorp.a.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes9.dex */
public final class b {

    /* renamed from: b  reason: collision with root package name */
    private final String f17231b;

    /* renamed from: d  reason: collision with root package name */
    private final SecureRandom f17233d;

    /* renamed from: e  reason: collision with root package name */
    private final SecretKeyFactory f17234e;

    /* renamed from: f  reason: collision with root package name */
    private final Cipher f17235f;

    /* renamed from: g  reason: collision with root package name */
    private final Mac f17236g;

    /* renamed from: h  reason: collision with root package name */
    private a f17237h;

    /* renamed from: a  reason: collision with root package name */
    private final Object f17230a = new Object();

    /* renamed from: c  reason: collision with root package name */
    private final int f17232c = 5000;

    public b(String str) {
        this.f17231b = str;
        try {
            this.f17233d = new SecureRandom();
            this.f17234e = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            this.f17235f = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.f17236g = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e2) {
            throw new RuntimeException(e2);
        }
    }

    public final void a(Context context) {
        synchronized (this.f17230a) {
            if (this.f17237h == null) {
                this.f17237h = b(context);
            }
        }
    }

    public final String a(Context context, String str) {
        String encodeToString;
        synchronized (this.f17230a) {
            a(context);
            try {
                int blockSize = this.f17235f.getBlockSize();
                byte[] bArr = new byte[blockSize];
                this.f17233d.nextBytes(bArr);
                this.f17235f.init(1, this.f17237h.f17238a, new IvParameterSpec(bArr));
                byte[] doFinal = this.f17235f.doFinal(str.getBytes("UTF-8"));
                byte[] bArr2 = new byte[doFinal.length + blockSize + 32];
                System.arraycopy(bArr, 0, bArr2, 0, blockSize);
                int i2 = blockSize + 0;
                System.arraycopy(doFinal, 0, bArr2, i2, doFinal.length);
                this.f17236g.init(this.f17237h.f17239b);
                this.f17236g.update(bArr2, 0, blockSize + doFinal.length);
                byte[] doFinal2 = this.f17236g.doFinal();
                System.arraycopy(doFinal2, 0, bArr2, i2 + doFinal.length, doFinal2.length);
                encodeToString = Base64.encodeToString(bArr2, 0);
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                throw new RuntimeException(e);
            } catch (InvalidAlgorithmParameterException e3) {
                e = e3;
                throw new RuntimeException(e);
            } catch (InvalidKeyException e4) {
                e = e4;
                throw new RuntimeException(e);
            } catch (BadPaddingException e5) {
                throw new com.linecorp.a.a.a.a(e5);
            } catch (IllegalBlockSizeException e6) {
                e = e6;
                throw new RuntimeException(e);
            }
        }
        return encodeToString;
    }

    public final String b(Context context, String str) {
        String str2;
        synchronized (this.f17230a) {
            a(context);
            try {
                byte[] decode = Base64.decode(str, 0);
                byte[] copyOfRange = Arrays.copyOfRange(decode, decode.length - 32, decode.length);
                this.f17236g.init(this.f17237h.f17239b);
                this.f17236g.update(decode, 0, decode.length - 32);
                if (!MessageDigest.isEqual(this.f17236g.doFinal(), copyOfRange)) {
                    throw new com.linecorp.a.a.a.a("Cipher text has been tampered with.");
                }
                this.f17235f.init(2, this.f17237h.f17238a, new IvParameterSpec(decode, 0, 16));
                str2 = new String(this.f17235f.doFinal(decode, 16, (decode.length - 16) - 32), "UTF-8");
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                throw new RuntimeException(e);
            } catch (InvalidAlgorithmParameterException e3) {
                e = e3;
                throw new RuntimeException(e);
            } catch (InvalidKeyException e4) {
                e = e4;
                throw new RuntimeException(e);
            } catch (BadPaddingException e5) {
                throw new com.linecorp.a.a.a.a(e5);
            } catch (IllegalBlockSizeException e6) {
                e = e6;
                throw new RuntimeException(e);
            }
        }
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        final SecretKey f17238a;

        /* renamed from: b  reason: collision with root package name */
        final SecretKey f17239b;

        a(SecretKey secretKey, SecretKey secretKey2) {
            this.f17238a = secretKey;
            this.f17239b = secretKey2;
        }
    }

    private a b(Context context) {
        byte[] bArr;
        String str = Build.MODEL + Build.MANUFACTURER + Build.SERIAL + Settings.Secure.getString(context.getContentResolver(), "android_id") + context.getPackageName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.f17231b, 0);
        String string = sharedPreferences.getString("salt", null);
        if (!TextUtils.isEmpty(string)) {
            bArr = Base64.decode(string, 0);
        } else {
            byte[] bArr2 = new byte[16];
            this.f17233d.nextBytes(bArr2);
            sharedPreferences.edit().putString("salt", Base64.encodeToString(bArr2, 0)).apply();
            bArr = bArr2;
        }
        try {
            byte[] encoded = this.f17234e.generateSecret(new PBEKeySpec(str.toCharArray(), bArr, this.f17232c, 512)).getEncoded();
            return new a(new SecretKeySpec(Arrays.copyOfRange(encoded, 0, 32), "AES"), new SecretKeySpec(Arrays.copyOfRange(encoded, 32, encoded.length), "HmacSHA256"));
        } catch (InvalidKeySpecException e2) {
            throw new RuntimeException(e2);
        }
    }
}
