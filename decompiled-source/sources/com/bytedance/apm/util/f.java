package com.bytedance.apm.util;

import android.text.TextUtils;
import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/* compiled from: DecodeUtils.java */
/* loaded from: classes.dex */
public class f {
    public static String sKey = "";

    public static String decodeData(byte[] bArr, String str) {
        try {
            byte[] decode = Base64.decode(bArr, 0);
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            sKey = str;
            return a(str, decode);
        } catch (Exception unused) {
            return "";
        }
    }

    private static String a(String str, byte[] bArr) {
        String str2;
        String str3 = "";
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(2, secretKeySpec);
            str2 = new String(cipher.doFinal(bArr));
        } catch (Exception unused) {
        }
        try {
            int indexOf = str2.indexOf("$");
            return indexOf != -1 ? str2.substring(0, indexOf) : str2;
        } catch (Exception unused2) {
            str3 = str2;
            return str3;
        }
    }

    public static String decodeData(byte[] bArr) {
        return decodeData(bArr, "");
    }

    public static String decodeCloudData(byte[] bArr, String str) {
        if (TextUtils.isEmpty(str)) {
            str = sKey;
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return a(str, Base64.decode(bArr, 0));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
