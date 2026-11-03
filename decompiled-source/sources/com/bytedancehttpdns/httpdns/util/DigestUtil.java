package com.bytedancehttpdns.httpdns.util;

import com.google.common.base.Ascii;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Arrays;
/* loaded from: classes8.dex */
public class DigestUtil {
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final int STREAM_BUFFER_LENGTH = 1024;

    private static char[] encodeHex(byte[] bArr, char[] cArr) {
        int length = bArr.length;
        char[] cArr2 = new char[length << 1];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i2 + 1;
            cArr2[i2] = cArr[(bArr[i3] & 240) >>> 4];
            i2 = i4 + 1;
            cArr2[i4] = cArr[bArr[i3] & Ascii.SI];
        }
        return cArr2;
    }

    public static String md5Hex(byte[] bArr) {
        try {
            return new String(encodeHex(MessageDigest.getInstance("MD5").digest(bArr), DIGITS_LOWER));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String md5Hex(InputStream inputStream) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bArr = new byte[1024];
            int read = inputStream.read(bArr, 0, 1024);
            while (read > -1) {
                messageDigest.update(bArr, 0, read);
                read = inputStream.read(bArr, 0, 1024);
            }
            return new String(encodeHex(messageDigest.digest(), DIGITS_LOWER));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String calculateSign(String str, String str2, long j2, String str3, String str4, String str5) {
        String[] strArr = {str, str2, String.valueOf(j2), str3, str4, str5};
        Arrays.sort(strArr);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 6; i2++) {
            sb.append(strArr[i2]);
            if (i2 != 5) {
                sb.append("_");
            }
        }
        return md5Hex(new ByteArrayInputStream(sb.toString().getBytes()));
    }
}
