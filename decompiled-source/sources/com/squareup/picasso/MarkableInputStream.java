package com.squareup.picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes9.dex */
final class MarkableInputStream extends InputStream {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private long defaultMark;
    private final InputStream in;
    private long limit;
    private long offset;
    private long reset;

    public MarkableInputStream(InputStream inputStream) {
        this(inputStream, 4096);
    }

    public MarkableInputStream(InputStream inputStream, int i2) {
        this.defaultMark = -1L;
        this.in = inputStream.markSupported() ? inputStream : new BufferedInputStream(inputStream, i2);
    }

    @Override // java.io.InputStream
    public void mark(int i2) {
        this.defaultMark = savePosition(i2);
    }

    public long savePosition(int i2) {
        long j2 = this.offset + i2;
        if (this.limit < j2) {
            setLimit(j2);
        }
        return this.offset;
    }

    private void setLimit(long j2) {
        try {
            long j3 = this.reset;
            long j4 = this.offset;
            if (j3 < j4 && j4 <= this.limit) {
                this.in.reset();
                this.in.mark((int) (j2 - this.reset));
                skip(this.reset, this.offset);
            } else {
                this.reset = j4;
                this.in.mark((int) (j2 - j4));
            }
            this.limit = j2;
        } catch (IOException e2) {
            throw new IllegalStateException("Unable to mark: " + e2);
        }
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        reset(this.defaultMark);
    }

    public void reset(long j2) throws IOException {
        if (this.offset > this.limit || j2 < this.reset) {
            throw new IOException("Cannot reset");
        }
        this.in.reset();
        skip(this.reset, j2);
        this.offset = j2;
    }

    private void skip(long j2, long j3) throws IOException {
        while (j2 < j3) {
            long skip = this.in.skip(j3 - j2);
            if (skip == 0) {
                if (read() == -1) {
                    return;
                }
                skip = 1;
            }
            j2 += skip;
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int read = this.in.read();
        if (read != -1) {
            this.offset++;
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        int read = this.in.read(bArr);
        if (read != -1) {
            this.offset += read;
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        int read = this.in.read(bArr, i2, i3);
        if (read != -1) {
            this.offset += read;
        }
        return read;
    }

    @Override // java.io.InputStream
    public long skip(long j2) throws IOException {
        long skip = this.in.skip(j2);
        this.offset += skip;
        return skip;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.in.markSupported();
    }
}
