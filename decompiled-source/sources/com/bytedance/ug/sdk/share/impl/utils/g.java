package com.bytedance.ug.sdk.share.impl.utils;

import android.text.TextUtils;
import java.security.MessageDigest;
/* compiled from: IDUtils.java */
/* loaded from: classes8.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f16176a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static int generate(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return 0;
        }
        String md5Hex = md5Hex(String.format("%s_%s", str, str2));
        if (TextUtils.isEmpty(md5Hex)) {
            return 0;
        }
        return md5Hex.hashCode();
    }

    public static String md5Hex(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.update(str.getBytes("UTF-8"));
                    return toHexString(messageDigest.digest());
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static String toHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return toHexString(bArr, 0, bArr.length);
    }

    public static String toHexString(byte[] bArr, int i2, int i3) {
        if (bArr != null && i2 >= 0 && i2 + i3 <= bArr.length) {
            int i4 = i3 * 2;
            char[] cArr = new char[i4];
            int i5 = 0;
            for (int i6 = 0; i6 < i3; i6++) {
                int i7 = bArr[i6 + i2] & 255;
                int i8 = i5 + 1;
                char[] cArr2 = f16176a;
                cArr[i5] = cArr2[i7 >> 4];
                i5 = i8 + 1;
                cArr[i8] = cArr2[i7 & 15];
            }
            return new String(cArr, 0, i4);
        }
        return null;
    }
}
