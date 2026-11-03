package com.bytedance.compression.zstd;

import java.util.Arrays;
/* loaded from: classes2.dex */
public class ZstdDecompressCtx extends a {
    private long nativePtr = 0;
    private ZstdDictDecompress decompression_dict = null;

    private native long decompressByteArray0(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5);

    private native void free();

    private native void init();

    private native long loadDDict0(byte[] bArr);

    private native long loadDDictFast0(ZstdDictDecompress zstdDictDecompress);

    private native long reset0();

    @Override // com.bytedance.compression.zstd.a, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    static {
        com.bytedance.compression.zstd.util.a.a();
    }

    public ZstdDecompressCtx() {
        init();
        if (0 == this.nativePtr) {
            throw new IllegalStateException("ZSTD_createDeCompressCtx failed");
        }
        storeFence();
    }

    @Override // com.bytedance.compression.zstd.a
    void doClose() {
        if (this.nativePtr != 0) {
            free();
            this.nativePtr = 0L;
        }
    }

    public ZstdDecompressCtx loadDict(ZstdDictDecompress zstdDictDecompress) {
        if (this.nativePtr == 0) {
            throw new IllegalStateException("Decompression context is closed");
        }
        acquireSharedLock();
        zstdDictDecompress.acquireSharedLock();
        try {
            long loadDDictFast0 = loadDDictFast0(zstdDictDecompress);
            if (Zstd.isError(loadDDictFast0)) {
                throw new g(loadDDictFast0);
            }
            this.decompression_dict = zstdDictDecompress;
            return this;
        } finally {
            zstdDictDecompress.releaseSharedLock();
            releaseSharedLock();
        }
    }

    public ZstdDecompressCtx loadDict(byte[] bArr) {
        if (this.nativePtr == 0) {
            throw new IllegalStateException("Compression context is closed");
        }
        acquireSharedLock();
        try {
            long loadDDict0 = loadDDict0(bArr);
            if (Zstd.isError(loadDDict0)) {
                throw new g(loadDDict0);
            }
            this.decompression_dict = null;
            return this;
        } finally {
            releaseSharedLock();
        }
    }

    public void reset() {
        ensureOpen();
        long reset0 = reset0();
        if (Zstd.isError(reset0)) {
            throw new g(reset0);
        }
    }

    private void ensureOpen() {
        if (this.nativePtr == 0) {
            throw new IllegalStateException("Decompression context is closed");
        }
    }

    public int decompressByteArray(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5) {
        if (this.nativePtr == 0) {
            throw new IllegalStateException("Decompression context is closed");
        }
        acquireSharedLock();
        try {
            long decompressByteArray0 = decompressByteArray0(bArr, i2, i3, bArr2, i4, i5);
            if (Zstd.isError(decompressByteArray0)) {
                throw new g(decompressByteArray0);
            }
            if (decompressByteArray0 > 2147483647L) {
                throw new g(Zstd.errGeneric(), "Output size is greater than MAX_INT");
            }
            return (int) decompressByteArray0;
        } finally {
            releaseSharedLock();
        }
    }

    public int decompress(byte[] bArr, byte[] bArr2) {
        return decompressByteArray(bArr, 0, bArr.length, bArr2, 0, bArr2.length);
    }

    public byte[] decompress(byte[] bArr, int i2) {
        byte[] bArr2 = new byte[i2];
        int decompress = decompress(bArr2, bArr);
        return decompress != i2 ? Arrays.copyOfRange(bArr2, 0, decompress) : bArr2;
    }
}
