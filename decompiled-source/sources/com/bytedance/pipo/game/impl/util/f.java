package com.bytedance.pipo.game.impl.util;

import com.google.common.base.Ascii;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* compiled from: MD5Utils.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f10538a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(String str) {
        return (str == null || str.length() == 0) ? "" : a(str.getBytes());
    }

    public static String a(byte[] bArr) {
        return c(b(bArr));
    }

    private static byte[] b(byte[] bArr) {
        return a(bArr, "MD5");
    }

    private static byte[] a(byte[] bArr, String str) {
        if (bArr != null && bArr.length > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(str);
                messageDigest.update(bArr);
                return messageDigest.digest();
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    private static String c(byte[] bArr) {
        int length;
        if (bArr != null && (length = bArr.length) > 0) {
            char[] cArr = new char[length << 1];
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                int i4 = i2 + 1;
                char[] cArr2 = f10538a;
                cArr[i2] = cArr2[(bArr[i3] >> 4) & 15];
                i2 = i4 + 1;
                cArr[i4] = cArr2[bArr[i3] & Ascii.SI];
            }
            return new String(cArr);
        }
        return "";
    }
}
