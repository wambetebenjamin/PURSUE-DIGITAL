package com.bytedance.compression.zstd;

import java.io.FilterInputStream;
import java.io.InputStream;
/* loaded from: classes2.dex */
public class ZstdInputStream extends FilterInputStream {
    private ZstdInputStreamNoFinalizer inner;

    public ZstdInputStream(InputStream inputStream) {
        super(inputStream);
        this.inner = new ZstdInputStreamNoFinalizer(inputStream);
    }

    protected void finalize() {
        close();
    }

    public static long recommendedDInSize() {
        return ZstdInputStreamNoFinalizer.recommendedDInSize();
    }

    public static long recommendedDOutSize() {
        return ZstdInputStreamNoFinalizer.recommendedDOutSize();
    }

    public ZstdInputStream setContinuous(boolean z) {
        this.inner.setContinuous(z);
        return this;
    }

    public boolean getContinuous() {
        return this.inner.getContinuous();
    }

    public ZstdInputStream setDict(byte[] bArr) {
        this.inner.setDict(bArr);
        return this;
    }

    public ZstdInputStream setDict(ZstdDictDecompress zstdDictDecompress) {
        this.inner.setDict(zstdDictDecompress);
        return this;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        return this.inner.read(bArr, i2, i3);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        return this.inner.read();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        return this.inner.available();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j2) {
        return this.inner.skip(j2);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return this.inner.markSupported();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.inner.close();
    }
}
