package com.facebook.common.streams;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
/* loaded from: classes8.dex */
public class LimitedInputStream extends FilterInputStream {
    private int mBytesToRead;
    private int mBytesToReadWhenMarked;

    public LimitedInputStream(InputStream inputStream, int i2) {
        super(inputStream);
        Objects.requireNonNull(inputStream);
        if (i2 < 0) {
            throw new IllegalArgumentException("limit must be >= 0");
        }
        this.mBytesToRead = i2;
        this.mBytesToReadWhenMarked = -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        if (this.mBytesToRead == 0) {
            return -1;
        }
        int read = this.in.read();
        if (read != -1) {
            this.mBytesToRead--;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        int i4 = this.mBytesToRead;
        if (i4 == 0) {
            return -1;
        }
        int read = this.in.read(bArr, i2, Math.min(i3, i4));
        if (read > 0) {
            this.mBytesToRead -= read;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j2) {
        long skip = this.in.skip(Math.min(j2, this.mBytesToRead));
        this.mBytesToRead = (int) (this.mBytesToRead - skip);
        return skip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        return Math.min(this.in.available(), this.mBytesToRead);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i2) {
        if (this.in.markSupported()) {
            this.in.mark(i2);
            this.mBytesToReadWhenMarked = this.mBytesToRead;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() {
        if (!this.in.markSupported()) {
            throw new IOException("mark is not supported");
        }
        if (this.mBytesToReadWhenMarked == -1) {
            throw new IOException("mark not set");
        }
        this.in.reset();
        this.mBytesToRead = this.mBytesToReadWhenMarked;
    }
}
