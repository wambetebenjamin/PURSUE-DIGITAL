package com.bytedance.compression.zstd;
/* loaded from: classes2.dex */
public class ZstdDictDecompress extends f {
    private long nativePtr;

    private native void free();

    private native void init(byte[] bArr, int i2, int i3);

    @Override // com.bytedance.compression.zstd.a, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    static {
        com.bytedance.compression.zstd.util.a.a();
    }

    public ZstdDictDecompress(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public ZstdDictDecompress(byte[] bArr, int i2, int i3) {
        this.nativePtr = 0L;
        init(bArr, i2, i3);
        if (this.nativePtr == 0) {
            throw new IllegalStateException("ZSTD_createDDict failed");
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
}
