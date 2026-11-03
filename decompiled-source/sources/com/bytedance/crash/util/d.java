package com.bytedance.crash.util;

import com.google.common.base.Ascii;
import java.security.MessageDigest;
/* compiled from: Digest.java */
/* loaded from: classes2.dex */
public class d {
    public static String a(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            if (digest.length > 0) {
                char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
                char[] cArr2 = new char[digest.length * 2];
                int i2 = 0;
                for (byte b2 : digest) {
                    int i3 = i2 + 1;
                    cArr2[i2] = cArr[(b2 >>> 4) & 15];
                    i2 = i3 + 1;
                    cArr2[i3] = cArr[b2 & Ascii.SI];
                }
                return new String(cArr2);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
