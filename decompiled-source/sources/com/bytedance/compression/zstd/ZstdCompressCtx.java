package com.bytedance.compression.zstd;

import java.util.Arrays;
/* loaded from: classes2.dex */
public class ZstdCompressCtx extends a {
    private long nativePtr = 0;
    private ZstdDictCompress compression_dict = null;

    private native long compressByteArray0(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5);

    private native void free();

    private native void init();

    private native long loadCDict0(byte[] bArr);

    private native long loadCDictFast0(ZstdDictCompress zstdDictCompress);

    private native long reset0();

    private native void setChecksum0(boolean z);

    private native void setContentSize0(boolean z);

    private native void setDictID0(boolean z);

    private native void setLevel0(int i2);

    private native long setPledgedSrcSize0(long j2);

    @Override // com.bytedance.compression.zstd.a, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    static {
        com.bytedance.compression.zstd.util.a.a();
    }

    public ZstdCompressCtx() {
        init();
        if (0 == this.nativePtr) {
            throw new IllegalStateException("ZSTD_createCompressCtx failed");
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

    public ZstdCompressCtx setLevel(int i2) {
        if (this.nativePtr == 0) {
            throw new IllegalStateException("Compression context is closed");
        }
        acquireSharedLock();
        setLevel0(i2);
        releaseSharedLock();
        return this;
    }

    public ZstdCompressCtx setChecksum(boolean z) {
        if (this.nativePtr == 0) {
            throw new IllegalStateException("Compression context is closed");
        }
        acquireSharedLock();
        setChecksum0(z);
        releaseSharedLock();
        return this;
    }

    public ZstdCompressCtx setWorkers(int i2) {
        acquireSharedLock();
        ZstdCompress.setCompressionWorkers(this.nativePtr, i2);
        releaseSharedLock();
        return this;
    }

    public ZstdCompressCtx setContentSize(boolean z) {
        if (this.nativePtr == 0) {
            throw new IllegalStateException("Compression context is closed");
        }
        acquireSharedLock();
        setContentSize0(z);
        releaseSharedLock();
        return this;
    }

    public ZstdCompressCtx setDictID(boolean z) {
        if (this.nativePtr == 0) {
            throw new IllegalStateException("Compression context is closed");
        }
        acquireSharedLock();
        setDictID0(z);
        releaseSharedLock();
        return this;
    }

    public ZstdCompressCtx setLong(int i2) {
        if (this.nativePtr == 0) {
            throw new IllegalStateException("Compression context is closed");
        }
        acquireSharedLock();
        ZstdCompress.setCompressionLong(this.nativePtr, i2);
        releaseSharedLock();
        return this;
    }

    public ZstdCompressCtx loadDict(ZstdDictCompress zstdDictCompress) {
        if (this.nativePtr == 0) {
            throw new IllegalStateException("Compression context is closed");
        }
        acquireSharedLock();
        zstdDictCompress.acquireSharedLock();
        try {
            long loadCDictFast0 = loadCDictFast0(zstdDictCompress);
            if (Zstd.isError(loadCDictFast0)) {
                throw new g(loadCDictFast0);
            }
            this.compression_dict = zstdDictCompress;
            return this;
        } finally {
            zstdDictCompress.releaseSharedLock();
            releaseSharedLock();
        }
    }

    public ZstdCompressCtx loadDict(byte[] bArr) {
        if (this.nativePtr == 0) {
            throw new IllegalStateException("Compression context is closed");
        }
        acquireSharedLock();
        try {
            long loadCDict0 = loadCDict0(bArr);
            if (Zstd.isError(loadCDict0)) {
                throw new g(loadCDict0);
            }
            this.compression_dict = null;
            return this;
        } finally {
            releaseSharedLock();
        }
    }

    private void ensureOpen() {
        if (this.nativePtr == 0) {
            throw new IllegalStateException("Compression context is closed");
        }
    }

    public void reset() {
        ensureOpen();
        long reset0 = reset0();
        if (Zstd.isError(reset0)) {
            throw new g(reset0);
        }
    }

    public void setPledgedSrcSize(long j2) {
        ensureOpen();
        long pledgedSrcSize0 = setPledgedSrcSize0(j2);
        if (Zstd.isError(pledgedSrcSize0)) {
            throw new g(pledgedSrcSize0);
        }
    }

    public int compressByteArray(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5) {
        if (this.nativePtr == 0) {
            throw new IllegalStateException("Compression context is closed");
        }
        acquireSharedLock();
        try {
            long compressByteArray0 = compressByteArray0(bArr, i2, i3, bArr2, i4, i5);
            if (Zstd.isError(compressByteArray0)) {
                throw new g(compressByteArray0);
            }
            if (compressByteArray0 > 2147483647L) {
                throw new g(Zstd.errGeneric(), "Output size is greater than MAX_INT");
            }
            return (int) compressByteArray0;
        } finally {
            releaseSharedLock();
        }
    }

    public int compress(byte[] bArr, byte[] bArr2) {
        return compressByteArray(bArr, 0, bArr.length, bArr2, 0, bArr2.length);
    }

    public byte[] compress(byte[] bArr) {
        long compressBound = ZstdCompress.compressBound(bArr.length);
        if (compressBound > 2147483647L) {
            throw new g(Zstd.errGeneric(), "Max output size is greater than MAX_INT");
        }
        int i2 = (int) compressBound;
        byte[] bArr2 = new byte[i2];
        return Arrays.copyOfRange(bArr2, 0, compressByteArray(bArr2, 0, i2, bArr, 0, bArr.length));
    }
}
