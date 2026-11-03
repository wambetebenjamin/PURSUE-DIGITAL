package com.bytedance.compression.zstd;
/* loaded from: classes2.dex */
public class ZstdDecompress {
    private static native long decompressedSize0(byte[] bArr, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int loadDictDecompress(long j2, byte[] bArr, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int loadFastDictDecompress(long j2, ZstdDictDecompress zstdDictDecompress);

    static {
        com.bytedance.compression.zstd.util.a.a();
    }

    public static byte[] decompress(byte[] bArr) {
        long decompressedSize = decompressedSize(bArr);
        if (decompressedSize > 2147483647L) {
            throw new g(Zstd.errGeneric(), "original size is greater than MAX_INT");
        }
        return decompress(bArr, (int) decompressedSize);
    }

    public static byte[] decompress(byte[] bArr, int i2) {
        ZstdDecompressCtx zstdDecompressCtx = new ZstdDecompressCtx();
        try {
            return zstdDecompressCtx.decompress(bArr, i2);
        } finally {
            zstdDecompressCtx.close();
        }
    }

    public static byte[] decompress(byte[] bArr, ZstdDictDecompress zstdDictDecompress) {
        long decompressedSize = decompressedSize(bArr);
        if (decompressedSize > 2147483647L) {
            throw new g(Zstd.errGeneric(), "original size is greater than MAX_INT");
        }
        return decompress(bArr, zstdDictDecompress, (int) decompressedSize);
    }

    public static byte[] decompress(byte[] bArr, ZstdDictDecompress zstdDictDecompress, int i2) {
        ZstdDecompressCtx zstdDecompressCtx = new ZstdDecompressCtx();
        try {
            zstdDecompressCtx.loadDict(zstdDictDecompress);
            return zstdDecompressCtx.decompress(bArr, i2);
        } finally {
            zstdDecompressCtx.close();
        }
    }

    public static byte[] decompress(byte[] bArr, byte[] bArr2) {
        long decompressedSize = decompressedSize(bArr);
        if (decompressedSize > 2147483647L) {
            throw new g(Zstd.errGeneric(), "original size is greater than MAX_INT");
        }
        return decompress(bArr, bArr2, (int) decompressedSize);
    }

    public static byte[] decompress(byte[] bArr, byte[] bArr2, int i2) {
        ZstdDecompressCtx zstdDecompressCtx = new ZstdDecompressCtx();
        try {
            zstdDecompressCtx.loadDict(bArr2);
            return zstdDecompressCtx.decompress(bArr, i2);
        } finally {
            zstdDecompressCtx.close();
        }
    }

    public static long decompressedSize(byte[] bArr, int i2, int i3) {
        if (i2 >= bArr.length) {
            throw new ArrayIndexOutOfBoundsException(i2);
        }
        int i4 = i2 + i3;
        if (i4 > bArr.length) {
            throw new ArrayIndexOutOfBoundsException(i4);
        }
        return decompressedSize0(bArr, i2, i3);
    }

    public static long decompressedSize(byte[] bArr, int i2) {
        return decompressedSize(bArr, i2, bArr.length - i2);
    }

    public static long decompressedSize(byte[] bArr) {
        return decompressedSize(bArr, 0);
    }
}
