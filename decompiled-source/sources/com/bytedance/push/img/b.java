package com.bytedance.push.img;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
/* compiled from: MarkableInputStream.java */
/* loaded from: classes2.dex */
final class b extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private static final int f11377a = 4096;

    /* renamed from: b  reason: collision with root package name */
    private final InputStream f11378b;

    /* renamed from: c  reason: collision with root package name */
    private long f11379c;

    /* renamed from: d  reason: collision with root package name */
    private long f11380d;

    /* renamed from: e  reason: collision with root package name */
    private long f11381e;

    /* renamed from: f  reason: collision with root package name */
    private long f11382f;

    public b(InputStream inputStream) {
        this(inputStream, 4096);
    }

    public b(InputStream inputStream, int i2) {
        this.f11382f = -1L;
        this.f11378b = inputStream.markSupported() ? inputStream : new BufferedInputStream(inputStream, i2);
    }

    @Override // java.io.InputStream
    public void mark(int i2) {
        this.f11382f = savePosition(i2);
    }

    public long savePosition(int i2) {
        long j2 = this.f11379c + i2;
        if (this.f11381e < j2) {
            a(j2);
        }
        return this.f11379c;
    }

    private void a(long j2) {
        try {
            long j3 = this.f11380d;
            long j4 = this.f11379c;
            if (j3 < j4 && j4 <= this.f11381e) {
                this.f11378b.reset();
                this.f11378b.mark((int) (j2 - this.f11380d));
                a(this.f11380d, this.f11379c);
            } else {
                this.f11380d = j4;
                this.f11378b.mark((int) (j2 - j4));
            }
            this.f11381e = j2;
        } catch (IOException e2) {
            throw new IllegalStateException("Unable to mark: " + e2);
        }
    }

    @Override // java.io.InputStream
    public void reset() {
        reset(this.f11382f);
    }

    public void reset(long j2) {
        if (this.f11379c > this.f11381e || j2 < this.f11380d) {
            throw new IOException("Cannot reset");
        }
        this.f11378b.reset();
        a(this.f11380d, j2);
        this.f11379c = j2;
    }

    private void a(long j2, long j3) {
        while (j2 < j3) {
            long skip = this.f11378b.skip(j3 - j2);
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
    public int read() {
        int read = this.f11378b.read();
        if (read != -1) {
            this.f11379c++;
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        int read = this.f11378b.read(bArr);
        if (read != -1) {
            this.f11379c += read;
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        int read = this.f11378b.read(bArr, i2, i3);
        if (read != -1) {
            this.f11379c += read;
        }
        return read;
    }

    @Override // java.io.InputStream
    public long skip(long j2) {
        long skip = this.f11378b.skip(j2);
        this.f11379c += skip;
        return skip;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.f11378b.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f11378b.close();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f11378b.markSupported();
    }
}
