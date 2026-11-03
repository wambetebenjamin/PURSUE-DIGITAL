package com.bytedance.compression.zstd;

import java.io.FilterOutputStream;
import java.io.OutputStream;
/* loaded from: classes2.dex */
public class ZstdOutputStream extends FilterOutputStream {
    private ZstdOutputStreamNoFinalizer inner;

    @Deprecated
    public void setFinalize(boolean z) {
    }

    public ZstdOutputStream(OutputStream outputStream, int i2) {
        this(outputStream, e.f6035a);
        this.inner.setLevel(i2);
    }

    public ZstdOutputStream(OutputStream outputStream) {
        this(outputStream, e.f6035a);
    }

    private ZstdOutputStream(OutputStream outputStream, b bVar) {
        super(outputStream);
        this.inner = new ZstdOutputStreamNoFinalizer(outputStream, bVar);
    }

    protected void finalize() {
        close();
    }

    public static long recommendedCOutSize() {
        return ZstdOutputStreamNoFinalizer.recommendedCOutSize();
    }

    public ZstdOutputStream setChecksum(boolean z) {
        this.inner.setChecksum(z);
        return this;
    }

    public ZstdOutputStream setLevel(int i2) {
        this.inner.setLevel(i2);
        return this;
    }

    public ZstdOutputStream setLong(int i2) {
        this.inner.setLong(i2);
        return this;
    }

    public ZstdOutputStream setWorkers(int i2) {
        this.inner.setWorkers(i2);
        return this;
    }

    public ZstdOutputStream setCloseFrameOnFlush(boolean z) {
        this.inner.setCloseFrameOnFlush(z);
        return this;
    }

    public ZstdOutputStream setDict(byte[] bArr) {
        this.inner.setDict(bArr);
        return this;
    }

    public ZstdOutputStream setDict(ZstdDictCompress zstdDictCompress) {
        this.inner.setDict(zstdDictCompress);
        return this;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        this.inner.write(bArr, i2, i3);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i2) {
        this.inner.write(i2);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() {
        this.inner.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.inner.close();
    }
}
