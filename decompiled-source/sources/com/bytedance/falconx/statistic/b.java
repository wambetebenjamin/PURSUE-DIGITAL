package com.bytedance.falconx.statistic;

import java.io.IOException;
import java.io.InputStream;
/* compiled from: InputStreamProxy.java */
/* loaded from: classes2.dex */
public class b extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private InputStream f7393a;

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(IOException iOException) {
    }

    public b(InputStream inputStream) {
        this.f7393a = inputStream;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        try {
            return this.f7393a.read(bArr);
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        try {
            return this.f7393a.read(bArr, i2, i3);
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    @Override // java.io.InputStream
    public long skip(long j2) {
        try {
            return this.f7393a.skip(j2);
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    @Override // java.io.InputStream
    public int available() {
        try {
            return this.f7393a.available();
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.f7393a.close();
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i2) {
        this.f7393a.mark(i2);
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
        try {
            this.f7393a.reset();
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f7393a.markSupported();
    }

    @Override // java.io.InputStream
    public int read() {
        try {
            return this.f7393a.read();
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }
}
