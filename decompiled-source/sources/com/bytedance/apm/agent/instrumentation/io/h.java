package com.bytedance.apm.agent.instrumentation.io;

import java.io.IOException;
import java.io.InputStream;
/* compiled from: CInputStreamParent.java */
/* loaded from: classes.dex */
public abstract class h extends g {
    private boolean e() {
        return !this.f258c.hasRemaining();
    }

    protected void a(Exception exc) {
        if (this.f257b.isComplete()) {
            return;
        }
        this.f257b.notifyStreamError(new c(this, this.f256a, exc));
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        int length = bArr.length;
        int i2 = 0;
        if (this.f259d) {
            synchronized (this.f258c) {
                if (a(length)) {
                    int a2 = a(bArr);
                    if (a2 >= 0) {
                        this.f256a += a2;
                        return a2;
                    }
                    throw new IOException("CountingInputStream wrong");
                }
                int remaining = this.f258c.remaining();
                if (remaining > 0) {
                    i2 = a(bArr, 0, remaining);
                    if (i2 < 0) {
                        throw new IOException("CountingInputStream wrong2");
                    }
                    length -= i2;
                    this.f256a += i2;
                }
            }
        }
        try {
            int read = this.f260e.read(bArr, i2, length);
            if (read >= 0) {
                this.f256a += read;
                return read + i2;
            } else if (i2 <= 0) {
                c();
                return read;
            } else {
                return i2;
            }
        } catch (IOException e2) {
            f255g.error(e2.toString());
            if (com.bytedance.apm.c.isDebugMode()) {
                com.bytedance.apm6.util.log.b.i("APM-Counting", "notify system err: " + e2);
                e2.printStackTrace();
            }
            a(e2);
            throw e2;
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f260e.markSupported();
    }

    public h(InputStream inputStream) {
        this(inputStream, false);
    }

    protected void c() {
        if (this.f257b.isComplete()) {
            return;
        }
        this.f257b.notifyStreamComplete(new c(this, this.f256a));
    }

    protected int d() {
        if (e()) {
            return -1;
        }
        return this.f258c.get();
    }

    private int a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    private int a(byte[] bArr, int i2, int i3) {
        if (e()) {
            return -1;
        }
        int remaining = this.f258c.remaining();
        this.f258c.get(bArr, i2, i3);
        return remaining - this.f258c.remaining();
    }

    protected boolean a(long j2) {
        return ((long) this.f258c.remaining()) >= j2;
    }

    public h(InputStream inputStream, boolean z) {
        super(inputStream, z);
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.f259d) {
            synchronized (this.f258c) {
                if (a(1L)) {
                    int d2 = d();
                    if (d2 >= 0) {
                        this.f256a++;
                    }
                    return d2;
                }
            }
        }
        try {
            int read = this.f260e.read();
            if (read >= 0) {
                this.f256a++;
            } else {
                c();
            }
            return read;
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    public void a(boolean z) {
        this.f259d = z;
    }

    @Override // com.bytedance.apm.agent.instrumentation.io.f
    public void addStreamCompleteListener(d dVar) {
        this.f257b.addStreamCompleteListener(dVar);
    }
}
