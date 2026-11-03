package com.bytedance.retrofit2.mime;

import com.google.common.base.Ascii;
import java.io.InputStream;
import java.security.MessageDigest;
/* loaded from: classes2.dex */
public class DigestUtil {
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final int STREAM_BUFFER_LENGTH = 1024;

    private static char[] encodeHex(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length << 1];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i2 + 1;
            char[] cArr2 = DIGITS_UPPER;
            cArr[i2] = cArr2[(bArr[i3] & 240) >>> 4];
            i2 = i4 + 1;
            cArr[i4] = cArr2[bArr[i3] & Ascii.SI];
        }
        return cArr;
    }

    public static String md5Hex(byte[] bArr) {
        try {
            return new String(encodeHex(MessageDigest.getInstance("MD5").digest(bArr)));
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
            return new String(encodeHex(messageDigest.digest()));
        } catch (Throwable unused) {
            return null;
        }
    }
}
