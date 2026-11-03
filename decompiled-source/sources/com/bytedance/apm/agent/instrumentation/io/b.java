package com.bytedance.apm.agent.instrumentation.io;

import java.io.IOException;
import java.io.OutputStream;
/* compiled from: CountingOutputStream.java */
/* loaded from: classes.dex */
public final class b extends OutputStream implements f {

    /* renamed from: a  reason: collision with root package name */
    private final OutputStream f246a;

    /* renamed from: b  reason: collision with root package name */
    private long f247b = 0;

    /* renamed from: c  reason: collision with root package name */
    private final e f248c = new e();

    public b(OutputStream outputStream) {
        this.f246a = outputStream;
    }

    @Override // com.bytedance.apm.agent.instrumentation.io.f
    public void addStreamCompleteListener(d dVar) {
        this.f248c.addStreamCompleteListener(dVar);
    }

    @Override // com.bytedance.apm.agent.instrumentation.io.f
    public void removeStreamCompleteListener(d dVar) {
        this.f248c.removeStreamCompleteListener(dVar);
    }

    public long getCount() {
        return this.f247b;
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        try {
            this.f246a.write(i2);
            this.f247b++;
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        try {
            this.f246a.write(bArr);
            this.f247b += bArr.length;
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        try {
            this.f246a.write(bArr, i2, i3);
            this.f247b += i3;
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        try {
            this.f246a.flush();
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.f246a.close();
            a();
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    private void a() {
        if (this.f248c.isComplete()) {
            return;
        }
        this.f248c.notifyStreamComplete(new c(this, this.f247b));
    }

    private void a(Exception exc) {
        if (this.f248c.isComplete()) {
            return;
        }
        this.f248c.notifyStreamError(new c(this, this.f247b, exc));
    }
}
