package com.bytedance.geckox.buffer.stream;

import com.bytedance.geckox.model.UpdatePackage;
/* compiled from: BufferOutputStream.java */
/* loaded from: classes2.dex */
public class b extends c {

    /* renamed from: a  reason: collision with root package name */
    private com.bytedance.geckox.buffer.a f8938a;

    /* renamed from: b  reason: collision with root package name */
    private com.bytedance.geckox.listener.a f8939b;

    /* renamed from: c  reason: collision with root package name */
    private UpdatePackage f8940c;

    /* renamed from: d  reason: collision with root package name */
    private long f8941d;

    /* renamed from: e  reason: collision with root package name */
    private long f8942e;

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
    }

    public b(com.bytedance.geckox.buffer.a aVar) {
        this.f8938a = aVar;
    }

    public b(com.bytedance.geckox.buffer.a aVar, com.bytedance.geckox.listener.a aVar2, UpdatePackage updatePackage, long j2) {
        this(aVar);
        this.f8939b = aVar2;
        this.f8940c = updatePackage;
        this.f8941d = j2;
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        this.f8938a.write(i2);
        a(4);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        this.f8938a.write(bArr);
        a(bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        this.f8938a.write(bArr, i2, i3);
        a(i3);
    }

    @Override // com.bytedance.geckox.buffer.stream.c
    public void write(long j2, int i2) {
        synchronized (this) {
            this.f8938a.position(j2);
            this.f8938a.write(i2);
            a(4);
        }
    }

    @Override // com.bytedance.geckox.buffer.stream.c
    public void write(long j2, byte[] bArr) {
        synchronized (this) {
            this.f8938a.position(j2);
            this.f8938a.write(bArr);
            a(bArr.length);
        }
    }

    @Override // com.bytedance.geckox.buffer.stream.c
    public void write(long j2, byte[] bArr, int i2, int i3) {
        synchronized (this) {
            this.f8938a.position(j2);
            this.f8938a.write(bArr, i2, i3);
            a(i3);
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        flush();
    }

    private void a(int i2) {
        long j2 = this.f8942e + i2;
        this.f8942e = j2;
        com.bytedance.geckox.listener.a aVar = this.f8939b;
        if (aVar == null) {
            return;
        }
        aVar.onDownloadProgress(this.f8940c, this.f8941d, j2);
    }
}
