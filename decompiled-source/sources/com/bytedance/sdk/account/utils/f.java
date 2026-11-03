package com.bytedance.sdk.account.utils;

import com.google.common.base.Ascii;
import java.security.MessageDigest;
/* compiled from: Md5Utils.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f13087a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String hexDigest(String str) {
        try {
            return hexDigest(str.getBytes());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String hexDigest(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            char[] cArr = new char[32];
            int i2 = 0;
            for (int i3 = 0; i3 < 16; i3++) {
                byte b2 = digest[i3];
                int i4 = i2 + 1;
                char[] cArr2 = f13087a;
                cArr[i2] = cArr2[(b2 >>> 4) & 15];
                i2 = i4 + 1;
                cArr[i4] = cArr2[b2 & Ascii.SI];
            }
            return new String(cArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
