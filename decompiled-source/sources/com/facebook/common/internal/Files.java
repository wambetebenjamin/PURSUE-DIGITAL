package com.facebook.common.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
/* loaded from: classes8.dex */
public class Files {
    private Files() {
    }

    static byte[] readFile(InputStream inputStream, long j2) {
        if (j2 <= 2147483647L) {
            if (j2 == 0) {
                return ByteStreams.toByteArray(inputStream);
            }
            return ByteStreams.toByteArray(inputStream, (int) j2);
        }
        throw new OutOfMemoryError("file is too large to fit in a byte array: " + j2 + " bytes");
    }

    public static byte[] toByteArray(File file) {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                byte[] readFile = readFile(fileInputStream2, fileInputStream2.getChannel().size());
                fileInputStream2.close();
                return readFile;
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
