package com.bytedance.geckox.buffer.impl;

import com.bytedance.geckox.utils.d;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: FileBuffer.java */
/* loaded from: classes2.dex */
class b implements com.bytedance.geckox.buffer.a {

    /* renamed from: a  reason: collision with root package name */
    private RandomAccessFile f8928a;

    /* renamed from: b  reason: collision with root package name */
    private AtomicBoolean f8929b = new AtomicBoolean(false);

    /* renamed from: c  reason: collision with root package name */
    private File f8930c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(File file) {
        this.f8930c = file;
        boolean mkdirs = file.getParentFile().mkdirs();
        try {
            this.f8928a = new RandomAccessFile(file, "rw");
        } catch (Exception e2) {
            boolean exists = file.getParentFile().exists();
            d.close(this.f8928a);
            if (e2 instanceof FileNotFoundException) {
                boolean mkdirs2 = file.getParentFile().mkdirs();
                boolean exists2 = file.getParentFile().exists();
                try {
                    this.f8928a = new RandomAccessFile(file, "rw");
                    return;
                } catch (Exception unused) {
                    d.close(this.f8928a);
                    throw new IOException("create raf mSwap failed![2 dir mk:" + mkdirs2 + ",dir exist:+" + exists2 + ", dir mk:" + mkdirs + ",dir exist:+" + exists + "] path: " + file.getAbsolutePath() + " caused by: " + e2.getMessage(), e2);
                }
            }
            throw new IOException("create raf mSwap failed![1 dir mk:" + mkdirs + ",dir exist:+" + exists + "] path: " + file.getAbsolutePath() + " caused by: " + e2.getMessage(), e2);
        }
    }

    @Override // com.bytedance.geckox.buffer.a
    public void persistence() {
        if (this.f8929b.get()) {
            throw new IOException("released!");
        }
    }

    @Override // com.bytedance.geckox.buffer.a
    public long length() {
        return this.f8928a.length();
    }

    @Override // com.bytedance.geckox.buffer.a
    public void write(int i2) {
        write(new byte[]{(byte) i2});
    }

    @Override // com.bytedance.geckox.buffer.a
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // com.bytedance.geckox.buffer.a
    public synchronized long skip(long j2) {
        int i2;
        if (this.f8929b.get()) {
            throw new IOException("released!");
        }
        i2 = (int) j2;
        if (i2 != j2) {
            throw new IOException("too large:" + j2);
        }
        return this.f8928a.skipBytes(i2);
    }

    @Override // com.bytedance.geckox.buffer.a
    public int write(byte[] bArr, int i2, int i3) {
        if (this.f8929b.get()) {
            throw new IOException("released!");
        }
        if (bArr == null || bArr.length == 0 || i3 < 1 || i2 < 0 || i2 >= bArr.length) {
            return 0;
        }
        if (i2 + i3 > bArr.length) {
            i3 = bArr.length - i2;
        }
        synchronized (this) {
            this.f8928a.write(bArr, i2, i3);
        }
        return i3;
    }

    @Override // com.bytedance.geckox.buffer.a
    public long position() {
        if (this.f8929b.get()) {
            throw new IOException("released!");
        }
        return this.f8928a.getFilePointer();
    }

    @Override // com.bytedance.geckox.buffer.a
    public void position(long j2) {
        if (this.f8929b.get()) {
            throw new IOException("released!");
        }
        if (j2 < 0) {
            j2 = 0;
        }
        this.f8928a.seek(j2);
    }

    @Override // com.bytedance.geckox.buffer.a
    public int read() {
        byte[] bArr = new byte[1];
        if (read(bArr) <= 0) {
            return -1;
        }
        return bArr[0];
    }

    @Override // com.bytedance.geckox.buffer.a
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // com.bytedance.geckox.buffer.a
    public int read(byte[] bArr, int i2, int i3) {
        int read;
        if (this.f8929b.get()) {
            throw new IOException("released!");
        }
        if (bArr == null || i3 < 1 || i2 < 0 || i2 >= bArr.length) {
            return 0;
        }
        if (i2 + i3 > bArr.length) {
            i3 = bArr.length - i2;
        }
        synchronized (this) {
            read = this.f8928a.read(bArr, i2, i3);
        }
        return read;
    }

    @Override // com.bytedance.geckox.buffer.a
    public void release() {
        if (this.f8929b.getAndSet(true)) {
            return;
        }
        d.close(this.f8928a);
    }

    @Override // com.bytedance.geckox.buffer.a
    public File swap() {
        return this.f8930c;
    }
}
