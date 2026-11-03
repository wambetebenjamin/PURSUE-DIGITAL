package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.base.annotations.JNINamespace;
@JNINamespace("cronet")
/* loaded from: classes9.dex */
public class TTCompressManager {
    private static native byte[] nativeCompressData(byte[] bArr, int i2, int i3, int i4);

    private static native byte[] nativeDecompressData(byte[] bArr, int i2, int i3);

    /* loaded from: classes9.dex */
    public enum CompressType {
        GZIP(1),
        BROTLI(2),
        ZSTD(3);
        
        final int mType;

        CompressType(int i2) {
            this.mType = i2;
        }

        public int getValue() {
            return this.mType;
        }
    }

    public static byte[] compressData(byte[] bArr, int i2, int i3, CompressType compressType) {
        return nativeCompressData(bArr, i2, i3, compressType.getValue());
    }

    public static byte[] decompressData(byte[] bArr, int i2, CompressType compressType) {
        return nativeDecompressData(bArr, i2, compressType.getValue());
    }
}
