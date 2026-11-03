package com.bytedance.geckox.buffer.stream;

import java.io.InputStream;
/* compiled from: BufferInputStream.java */
/* loaded from: classes2.dex */
public class a extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private com.bytedance.geckox.buffer.a f8936a;

    /* renamed from: b  reason: collision with root package name */
    private long f8937b;

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    public a(com.bytedance.geckox.buffer.a aVar) {
        this.f8936a = aVar;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return this.f8936a.read(bArr);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        return this.f8936a.read(bArr, i2, i3);
    }

    @Override // java.io.InputStream
    public long skip(long j2) {
        return this.f8936a.skip(j2);
    }

    @Override // java.io.InputStream
    public int available() {
        long length = this.f8936a.length() - this.f8936a.position();
        if (length > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) length;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i2) {
        this.f8937b = i2;
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
        this.f8936a.position(this.f8937b);
    }

    @Override // java.io.InputStream
    public int read() {
        return this.f8936a.read();
    }
}
