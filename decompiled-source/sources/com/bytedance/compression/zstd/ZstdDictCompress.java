package com.bytedance.compression.zstd;
/* loaded from: classes2.dex */
public class ZstdDictCompress extends f {
    private int level;
    private long nativePtr;

    private native void free();

    private native void init(byte[] bArr, int i2, int i3, int i4);

    @Override // com.bytedance.compression.zstd.a, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    static {
        com.bytedance.compression.zstd.util.a.a();
    }

    public ZstdDictCompress(byte[] bArr, int i2) {
        this(bArr, 0, bArr.length, i2);
    }

    public ZstdDictCompress(byte[] bArr, int i2, int i3, int i4) {
        this.nativePtr = 0L;
        this.level = ZstdCompress.defaultCompressionLevel();
        this.level = i4;
        if (bArr.length - i2 < 0) {
            throw new IllegalArgumentException("Dictionary buffer is to short");
        }
        init(bArr, i2, i3, i4);
        if (0 == this.nativePtr) {
            throw new IllegalStateException("ZSTD_createCDict failed");
        }
        storeFence();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int level() {
        return this.level;
    }

    @Override // com.bytedance.compression.zstd.a
    void doClose() {
        if (this.nativePtr != 0) {
            free();
            this.nativePtr = 0L;
        }
    }
}
