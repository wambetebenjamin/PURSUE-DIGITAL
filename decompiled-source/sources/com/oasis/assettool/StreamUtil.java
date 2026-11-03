package com.oasis.assettool;

import android.util.Log;
import java.io.InputStream;
/* loaded from: classes9.dex */
public final class StreamUtil {
    public static byte[] readFromStream(InputStream inputStream, int i2) {
        int read;
        byte[] bArr = new byte[i2];
        try {
            read = inputStream.read(bArr, 0, i2);
        } catch (Exception e2) {
            Log.e("unity", "read file from stream:", e2);
        }
        if (read <= 0) {
            return new byte[0];
        }
        if (read != i2) {
            byte[] bArr2 = new byte[read];
            System.arraycopy(bArr, 0, bArr2, 0, read);
            return bArr2;
        }
        return bArr;
    }

    public static byte[] readFromStream(InputStream inputStream, int i2, long j2) {
        try {
            inputStream.reset();
            inputStream.skip(j2);
            return readFromStream(inputStream, i2);
        } catch (Exception e2) {
            Log.e("unity", "read file from stream :", e2);
            return new byte[0];
        }
    }
}
