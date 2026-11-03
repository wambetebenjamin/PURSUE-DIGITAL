package com.ttnet.org.chromium.base;

import java.io.FileInputStream;
import java.security.GeneralSecurityException;
/* loaded from: classes9.dex */
public class ByteArrayGenerator {
    public byte[] getBytes(int i2) {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream("/dev/urandom");
            try {
                byte[] bArr = new byte[i2];
                if (i2 != fileInputStream2.read(bArr)) {
                    throw new GeneralSecurityException("Not enough random data available");
                }
                fileInputStream2.close();
                return bArr;
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
