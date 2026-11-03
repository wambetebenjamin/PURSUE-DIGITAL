package com.bytedance.push.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Inflater;
/* compiled from: ZlibUtil.java */
/* loaded from: classes2.dex */
public class r {
    public static final String GZIP_ENCODE_UTF_8 = "UTF-8";

    public static String uncompressToString(byte[] bArr) {
        return uncompressToString(bArr, "UTF-8");
    }

    public static String uncompressToString(byte[] bArr, String str) {
        Inflater inflater = new Inflater();
        inflater.reset();
        inflater.setInput(bArr, 0, bArr.length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        try {
            try {
                try {
                    byte[] bArr2 = new byte[1024];
                    while (!inflater.finished()) {
                        byteArrayOutputStream.write(bArr2, 0, inflater.inflate(bArr2));
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                byteArrayOutputStream.close();
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        inflater.end();
        return new String(bArr);
    }
}
