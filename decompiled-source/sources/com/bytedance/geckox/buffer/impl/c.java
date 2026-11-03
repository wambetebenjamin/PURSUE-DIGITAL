package com.bytedance.geckox.buffer.impl;

import com.bytedance.geckox.utils.d;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: RAFBuffer.java */
/* loaded from: classes2.dex */
class c implements com.bytedance.geckox.buffer.a {

    /* renamed from: a  reason: collision with root package name */
    private long f8931a;

    /* renamed from: b  reason: collision with root package name */
    private long f8932b;

    /* renamed from: c  reason: collision with root package name */
    private RandomAccessFile f8933c;

    /* renamed from: d  reason: collision with root package name */
    private AtomicBoolean f8934d = new AtomicBoolean(false);

    /* renamed from: e  reason: collision with root package name */
    private File f8935e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(long j2, File file) {
        this.f8931a = j2;
        this.f8935e = file;
        boolean mkdirs = file.getParentFile().mkdirs();
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            this.f8933c = randomAccessFile;
            randomAccessFile.setLength(j2);
        } catch (Exception e2) {
            boolean exists = file.getParentFile().exists();
            d.close(this.f8933c);
            if (e2 instanceof FileNotFoundException) {
                boolean mkdirs2 = file.getParentFile().mkdirs();
                boolean exists2 = file.getParentFile().exists();
                try {
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
                    this.f8933c = randomAccessFile2;
                    randomAccessFile2.setLength(j2);
                    return;
                } catch (Exception unused) {
                    d.close(this.f8933c);
                    throw new IOException("create raf swap failed[2 dir mk:" + mkdirs2 + ",dir exist:+" + exists2 + ", dir mk:" + mkdirs + ",dir exist:+" + exists + "]! path: " + file.getAbsolutePath() + " caused by: " + e2.getMessage(), e2);
                }
            }
            throw new IOException("create raf swap failed[1 dir mk:" + mkdirs + ",dir exist:+" + exists + "]! path: " + file.getAbsolutePath() + " caused by: " + e2.getMessage(), e2);
        }
    }

    @Override // com.bytedance.geckox.buffer.a
    public void persistence() {
        if (this.f8934d.get()) {
            throw new IOException("released!");
        }
    }

    @Override // com.bytedance.geckox.buffer.a
    public long length() {
        return this.f8931a;
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
        int skipBytes;
        if (this.f8934d.get()) {
            throw new IOException("released!");
        }
        int i2 = (int) j2;
        if (i2 != j2) {
            throw new IOException("too large:" + j2);
        }
        skipBytes = this.f8933c.skipBytes(i2);
        this.f8932b = this.f8933c.getFilePointer();
        return skipBytes;
    }

    @Override // com.bytedance.geckox.buffer.a
    public int write(byte[] bArr, int i2, int i3) {
        if (this.f8934d.get()) {
            throw new IOException("released!");
        }
        if (bArr == null || bArr.length == 0 || i3 < 1 || i2 < 0 || i2 >= bArr.length) {
            return 0;
        }
        if (i2 + i3 > bArr.length) {
            i3 = bArr.length - i2;
        }
        synchronized (this) {
            long j2 = this.f8932b;
            long j3 = this.f8931a;
            if (j2 == j3) {
                return 0;
            }
            if (i3 + j2 > j3) {
                i3 = (int) (j3 - j2);
            }
            this.f8933c.write(bArr, i2, i3);
            this.f8932b += i3;
            return i3;
        }
    }

    @Override // com.bytedance.geckox.buffer.a
    public long position() {
        if (this.f8934d.get()) {
            throw new IOException("released!");
        }
        return this.f8932b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0014, code lost:
        if (r4 > r0) goto L5;
     */
    @Override // com.bytedance.geckox.buffer.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void position(long r4) {
        /*
            r3 = this;
            java.util.concurrent.atomic.AtomicBoolean r0 = r3.f8934d
            boolean r0 = r0.get()
            if (r0 != 0) goto L1f
            r0 = 0
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 >= 0) goto L10
        Le:
            r4 = r0
            goto L17
        L10:
            long r0 = r3.f8931a
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 <= 0) goto L17
            goto Le
        L17:
            r3.f8932b = r4
            java.io.RandomAccessFile r0 = r3.f8933c
            r0.seek(r4)
            return
        L1f:
            java.io.IOException r4 = new java.io.IOException
            java.lang.String r5 = "released!"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.geckox.buffer.impl.c.position(long):void");
    }

    @Override // com.bytedance.geckox.buffer.a
    public int read() {
        byte[] bArr = new byte[1];
        if (read(bArr) == 0) {
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
        if (this.f8934d.get()) {
            throw new IOException("released!");
        }
        if (bArr == null || i3 < 1 || i2 < 0 || i2 >= bArr.length) {
            return 0;
        }
        if (i2 + i3 > bArr.length) {
            i3 = bArr.length - i2;
        }
        synchronized (this) {
            long j2 = this.f8932b;
            long j3 = this.f8931a;
            if (j2 == j3) {
                return -1;
            }
            if (i3 + j2 > j3) {
                i3 = (int) (j3 - j2);
            }
            int read = this.f8933c.read(bArr, i2, i3);
            if (read == -1) {
                return -1;
            }
            this.f8932b += read;
            return read;
        }
    }

    @Override // com.bytedance.geckox.buffer.a
    public void release() {
        if (this.f8934d.getAndSet(true)) {
            return;
        }
        d.close(this.f8933c);
    }

    @Override // com.bytedance.geckox.buffer.a
    public File swap() {
        return this.f8935e;
    }
}
