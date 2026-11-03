package com.facebook.common.internal;

import java.io.FilterOutputStream;
import java.io.OutputStream;
/* loaded from: classes8.dex */
public class CountingOutputStream extends FilterOutputStream {
    private long mCount;

    public CountingOutputStream(OutputStream outputStream) {
        super(outputStream);
        this.mCount = 0L;
    }

    public long getCount() {
        return this.mCount;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        this.out.write(bArr, i2, i3);
        this.mCount += i3;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i2) {
        this.out.write(i2);
        this.mCount++;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.out.close();
    }
}
