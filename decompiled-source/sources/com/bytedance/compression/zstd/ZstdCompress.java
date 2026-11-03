package com.bytedance.compression.zstd;
/* loaded from: classes2.dex */
public class ZstdCompress {
    public static native long compressBound(long j2);

    public static native int defaultCompressionLevel();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int loadDictCompress(long j2, byte[] bArr, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int loadFastDictCompress(long j2, ZstdDictCompress zstdDictCompress);

    public static native int maxCompressionLevel();

    public static native int minCompressionLevel();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int setCompressionChecksums(long j2, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int setCompressionLevel(long j2, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int setCompressionLong(long j2, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int setCompressionWorkers(long j2, int i2);

    static {
        com.bytedance.compression.zstd.util.a.a();
    }

    public static byte[] compress(byte[] bArr) {
        return compress(bArr, defaultCompressionLevel());
    }

    public static byte[] compress(byte[] bArr, int i2) {
        ZstdCompressCtx zstdCompressCtx = new ZstdCompressCtx();
        try {
            zstdCompressCtx.setLevel(i2);
            return zstdCompressCtx.compress(bArr);
        } finally {
            zstdCompressCtx.close();
        }
    }

    public static int compress(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5, int i6) {
        ZstdCompressCtx zstdCompressCtx = new ZstdCompressCtx();
        try {
            zstdCompressCtx.setLevel(i6);
            return zstdCompressCtx.compressByteArray(bArr, i2, i3, bArr2, i4, i5);
        } finally {
            zstdCompressCtx.close();
        }
    }

    public static byte[] compress(byte[] bArr, ZstdDictCompress zstdDictCompress) {
        ZstdCompressCtx zstdCompressCtx = new ZstdCompressCtx();
        try {
            zstdCompressCtx.loadDict(zstdDictCompress);
            zstdCompressCtx.setLevel(zstdDictCompress.level());
            return zstdCompressCtx.compress(bArr);
        } finally {
            zstdCompressCtx.close();
        }
    }

    public static int compress(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5, ZstdDictCompress zstdDictCompress) {
        ZstdCompressCtx zstdCompressCtx = new ZstdCompressCtx();
        try {
            zstdCompressCtx.loadDict(zstdDictCompress);
            zstdCompressCtx.setLevel(zstdDictCompress.level());
            return zstdCompressCtx.compressByteArray(bArr, i2, i3, bArr2, i4, i5);
        } finally {
            zstdCompressCtx.close();
        }
    }

    public static byte[] compress(byte[] bArr, byte[] bArr2, int i2) {
        ZstdCompressCtx zstdCompressCtx = new ZstdCompressCtx();
        try {
            zstdCompressCtx.loadDict(bArr2);
            zstdCompressCtx.setLevel(i2);
            return zstdCompressCtx.compress(bArr);
        } finally {
            zstdCompressCtx.close();
        }
    }
}
