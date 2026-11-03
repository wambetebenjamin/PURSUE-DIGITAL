package com.bytedance.geckox.buffer.impl;

import com.bytedance.geckox.utils.d;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes2.dex */
public class MReMapBuffer extends MMapBuffer {

    /* renamed from: a  reason: collision with root package name */
    private static final int f8920a = 4096;

    /* renamed from: b  reason: collision with root package name */
    private AtomicBoolean f8921b;

    /* renamed from: c  reason: collision with root package name */
    private long f8922c;

    private native int nMReMap(long j2, long j3, long j4);

    @Override // com.bytedance.geckox.buffer.impl.MMapBuffer, com.bytedance.geckox.buffer.a
    public /* bridge */ /* synthetic */ long length() {
        return super.length();
    }

    @Override // com.bytedance.geckox.buffer.impl.MMapBuffer, com.bytedance.geckox.buffer.a
    public /* bridge */ /* synthetic */ long position() {
        return super.position();
    }

    @Override // com.bytedance.geckox.buffer.impl.MMapBuffer, com.bytedance.geckox.buffer.a
    public /* bridge */ /* synthetic */ void position(long j2) {
        super.position(j2);
    }

    @Override // com.bytedance.geckox.buffer.impl.MMapBuffer, com.bytedance.geckox.buffer.a
    public /* bridge */ /* synthetic */ int read() {
        return super.read();
    }

    @Override // com.bytedance.geckox.buffer.impl.MMapBuffer, com.bytedance.geckox.buffer.a
    public /* bridge */ /* synthetic */ int read(byte[] bArr) {
        return super.read(bArr);
    }

    @Override // com.bytedance.geckox.buffer.impl.MMapBuffer, com.bytedance.geckox.buffer.a
    public /* bridge */ /* synthetic */ int read(byte[] bArr, int i2, int i3) {
        return super.read(bArr, i2, i3);
    }

    @Override // com.bytedance.geckox.buffer.impl.MMapBuffer, com.bytedance.geckox.buffer.a
    public /* bridge */ /* synthetic */ long skip(long j2) {
        return super.skip(j2);
    }

    @Override // com.bytedance.geckox.buffer.impl.MMapBuffer, com.bytedance.geckox.buffer.a
    public /* bridge */ /* synthetic */ File swap() {
        return super.swap();
    }

    public MReMapBuffer(File file) {
        super(4096L, file);
        this.f8921b = new AtomicBoolean(false);
        this.f8922c = 0L;
    }

    public MReMapBuffer(long j2, File file) {
        super(j2, file);
        this.f8921b = new AtomicBoolean(false);
        this.f8922c = 0L;
    }

    @Override // com.bytedance.geckox.buffer.impl.MMapBuffer, com.bytedance.geckox.buffer.a
    public void write(int i2) {
        if (this.f8921b.get()) {
            throw new IOException("released!");
        }
        a(1);
        super.write(i2);
    }

    @Override // com.bytedance.geckox.buffer.impl.MMapBuffer, com.bytedance.geckox.buffer.a
    public void write(byte[] bArr) {
        if (this.f8921b.get()) {
            throw new IOException("released!");
        }
        a(bArr == null ? 0 : bArr.length);
        super.write(bArr);
    }

    @Override // com.bytedance.geckox.buffer.impl.MMapBuffer, com.bytedance.geckox.buffer.a
    public int write(byte[] bArr, int i2, int i3) {
        if (this.f8921b.get()) {
            throw new IOException("released!");
        }
        a(i3);
        return super.write(bArr, i2, i3);
    }

    private synchronized void a(int i2) {
        RandomAccessFile randomAccessFile;
        Throwable th;
        Exception e2;
        if (i2 <= 0) {
            return;
        }
        long position = position();
        long b2 = b();
        long j2 = position + i2;
        this.f8922c = Math.max(this.f8922c, j2);
        if (j2 > b2) {
            long j3 = ((j2 / 4096) + 1) * 4096;
            try {
                randomAccessFile = new RandomAccessFile(c(), "rw");
                try {
                    try {
                        randomAccessFile.setLength(j3);
                        d.close(randomAccessFile);
                        a(nMReMap(a(), b2, j3));
                        b(j3);
                    } catch (Exception e3) {
                        e2 = e3;
                        throw new IOException("create remap swap failed! path: " + c().getAbsolutePath() + " caused by: " + e2.getMessage(), e2);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    d.close(randomAccessFile);
                    throw th;
                }
            } catch (Exception e4) {
                randomAccessFile = null;
                e2 = e4;
            } catch (Throwable th3) {
                randomAccessFile = null;
                th = th3;
                d.close(randomAccessFile);
                throw th;
            }
        }
    }

    @Override // com.bytedance.geckox.buffer.impl.MMapBuffer, com.bytedance.geckox.buffer.a
    public void persistence() {
        Throwable th;
        Exception e2;
        super.persistence();
        try {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(c(), "rw");
                try {
                    randomAccessFile.setLength(this.f8922c);
                    d.close(randomAccessFile);
                } catch (Exception e3) {
                    e2 = e3;
                    throw new IOException("reset swap length failed! path: " + c().getAbsolutePath() + " caused by: " + e2.getMessage(), e2);
                }
            } catch (Throwable th2) {
                th = th2;
                d.close(null);
                throw th;
            }
        } catch (Exception e4) {
            e2 = e4;
        } catch (Throwable th3) {
            th = th3;
            d.close(null);
            throw th;
        }
    }

    @Override // com.bytedance.geckox.buffer.impl.MMapBuffer, com.bytedance.geckox.buffer.a
    public void release() {
        super.release();
        this.f8921b.set(true);
    }
}
