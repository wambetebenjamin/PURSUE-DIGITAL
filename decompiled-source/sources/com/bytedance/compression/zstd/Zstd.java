package com.bytedance.compression.zstd;

import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public class Zstd {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long errGeneric();

    public static native long getErrorCode(long j2);

    public static native String getErrorName(long j2);

    public static native long[] getFunAddressesBySymbols(String[] strArr);

    public static native boolean isError(long j2);

    static {
        com.bytedance.compression.zstd.util.a.a();
    }

    public static long getFunAddressBySymbol(String str) {
        long[] funAddressesBySymbols = getFunAddressesBySymbols(new String[]{str});
        if (funAddressesBySymbols == null || funAddressesBySymbols.length != 1) {
            return 0L;
        }
        return funAddressesBySymbols[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final byte[] extractArray(ByteBuffer byteBuffer) {
        if (!byteBuffer.hasArray() || byteBuffer.arrayOffset() != 0) {
            throw new IllegalArgumentException("provided ByteBuffer lacks array or has non-zero arrayOffset");
        }
        return byteBuffer.array();
    }
}
