package com.bytedance.geckox.buffer.impl;

import com.bytedance.geckox.d;
import com.bytedance.geckox.utils.e;
import com.bytedance.geckox.utils.j;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes2.dex */
class MMapBuffer implements com.bytedance.geckox.buffer.a {

    /* renamed from: a  reason: collision with root package name */
    private long f8915a;

    /* renamed from: b  reason: collision with root package name */
    private long f8916b;

    /* renamed from: c  reason: collision with root package name */
    private long f8917c;

    /* renamed from: d  reason: collision with root package name */
    private AtomicBoolean f8918d = new AtomicBoolean(false);

    /* renamed from: e  reason: collision with root package name */
    private File f8919e;

    private native long nCreate(String str, long j2);

    private native int nFlush(long j2, long j3);

    private native void nRead(long j2, long j3, byte[] bArr, int i2, int i3);

    private native int nRelease(long j2, long j3);

    private native void nWrite(long j2, long j3, byte[] bArr, int i2, int i3);

    static {
        j.loadLib("buffer");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MMapBuffer(long j2, File file) {
        b(j2);
        this.f8919e = file;
        file.getParentFile().mkdirs();
        a(nCreate(file.getAbsolutePath(), j2));
        com.bytedance.geckox.logger.b.a(d.f8984a, "MMap file[" + file.getAbsolutePath() + "],length:" + j2 + ",pid:" + com.bytedance.geckox.utils.a.a() + ",thread id:" + com.bytedance.geckox.utils.a.b());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long a() {
        return this.f8916b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2) {
        this.f8916b = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long b() {
        return this.f8915a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(long j2) {
        this.f8915a = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public File c() {
        return this.f8919e;
    }

    @Override // com.bytedance.geckox.buffer.a
    public void persistence() {
        if (this.f8918d.get()) {
            throw new IOException("released!");
        }
        nFlush(this.f8916b, this.f8915a);
    }

    @Override // com.bytedance.geckox.buffer.a
    public long length() {
        return this.f8915a;
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
        if (this.f8918d.get()) {
            throw new IOException("released!");
        }
        if (j2 <= 0) {
            return 0L;
        }
        long j3 = this.f8917c;
        long j4 = j2 + j3;
        this.f8917c = j4;
        if (j4 < 0) {
            this.f8917c = 0L;
        } else {
            long j5 = this.f8915a;
            if (j4 > j5) {
                this.f8917c = j5;
            }
        }
        return this.f8917c - j3;
    }

    @Override // com.bytedance.geckox.buffer.a
    public int write(byte[] bArr, int i2, int i3) {
        if (this.f8918d.get()) {
            throw new IOException("released!");
        }
        if (bArr == null || bArr.length == 0 || i3 < 1 || i2 < 0 || i2 >= bArr.length) {
            return 0;
        }
        if (i2 + i3 > bArr.length) {
            i3 = bArr.length - i2;
        }
        synchronized (this) {
            long j2 = this.f8917c;
            long j3 = this.f8915a;
            if (j2 == j3) {
                return 0;
            }
            if (i3 + j2 > j3) {
                i3 = (int) (j3 - j2);
            }
            nWrite(this.f8916b, j2, bArr, i2, i3);
            this.f8917c += i3;
            return i3;
        }
    }

    @Override // com.bytedance.geckox.buffer.a
    public long position() {
        if (this.f8918d.get()) {
            throw new IOException("released!");
        }
        return this.f8917c;
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
            java.util.concurrent.atomic.AtomicBoolean r0 = r3.f8918d
            boolean r0 = r0.get()
            if (r0 != 0) goto L1a
            r0 = 0
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 >= 0) goto L10
        Le:
            r4 = r0
            goto L17
        L10:
            long r0 = r3.f8915a
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 <= 0) goto L17
            goto Le
        L17:
            r3.f8917c = r4
            return
        L1a:
            java.io.IOException r4 = new java.io.IOException
            java.lang.String r5 = "released!"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.geckox.buffer.impl.MMapBuffer.position(long):void");
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
        if (this.f8918d.get()) {
            throw new IOException("released!");
        }
        if (bArr == null || i3 < 1 || i2 < 0 || i2 >= bArr.length) {
            return 0;
        }
        if (i2 + i3 > bArr.length) {
            i3 = bArr.length - i2;
        }
        synchronized (this) {
            long j2 = this.f8917c;
            long j3 = this.f8915a;
            if (j2 == j3) {
                return -1;
            }
            if (i3 + j2 > j3) {
                i3 = (int) (j3 - j2);
            }
            nRead(this.f8916b, j2, bArr, i2, i3);
            this.f8917c += i3;
            return i3;
        }
    }

    @Override // com.bytedance.geckox.buffer.a
    public void release() {
        if (this.f8918d.getAndSet(true)) {
            return;
        }
        nRelease(this.f8916b, this.f8915a);
        this.f8916b = 0L;
    }

    @Override // com.bytedance.geckox.buffer.a
    public File swap() {
        return this.f8919e;
    }

    protected void finalize() {
        super.finalize();
        try {
            release();
        } catch (Exception e2) {
            e.a(e2);
        }
    }
}
