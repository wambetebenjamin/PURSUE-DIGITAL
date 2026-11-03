package com.bytedance.apm.agent.instrumentation.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* compiled from: CInputStreamPParent.java */
/* loaded from: classes.dex */
public abstract class g extends InputStream implements f {

    /* renamed from: f  reason: collision with root package name */
    protected static final int f254f = 2048;

    /* renamed from: g  reason: collision with root package name */
    protected static final com.bytedance.apm.agent.logging.a f255g = com.bytedance.apm.agent.logging.b.getAgentLog();

    /* renamed from: a  reason: collision with root package name */
    protected long f256a;

    /* renamed from: b  reason: collision with root package name */
    protected final e f257b;

    /* renamed from: c  reason: collision with root package name */
    protected final ByteBuffer f258c;

    /* renamed from: d  reason: collision with root package name */
    protected boolean f259d;

    /* renamed from: e  reason: collision with root package name */
    protected final InputStream f260e;

    public String a() {
        String str;
        ByteBuffer byteBuffer = this.f258c;
        if (byteBuffer != null) {
            synchronized (byteBuffer) {
                byte[] bArr = new byte[this.f258c.limit()];
                for (int i2 = 0; i2 < this.f258c.limit(); i2++) {
                    bArr[i2] = this.f258c.get(i2);
                }
                str = new String(bArr);
            }
            return str;
        }
        return "";
    }

    @Override // java.io.InputStream
    public long skip(long j2) {
        if (this.f259d) {
            synchronized (this.f258c) {
                if (a(j2)) {
                    this.f258c.position((int) j2);
                    this.f256a += j2;
                    return j2;
                }
                j2 -= this.f258c.remaining();
                if (j2 > 0) {
                    ByteBuffer byteBuffer = this.f258c;
                    byteBuffer.position(byteBuffer.remaining());
                } else {
                    throw new IOException("Failed to partial read from buffer (skip)");
                }
            }
        }
        try {
            long skip = this.f260e.skip(j2);
            this.f256a += skip;
            return skip;
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    private boolean c() {
        return !this.f258c.hasRemaining();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        int i4;
        int i5 = 0;
        if (this.f259d) {
            synchronized (this.f258c) {
                if (a(i3)) {
                    int a2 = a(bArr, i2, i3);
                    if (a2 >= 0) {
                        this.f256a += a2;
                        return a2;
                    }
                    throw new IOException("Failed to readBuffer()");
                }
                int remaining = this.f258c.remaining();
                if (remaining > 0) {
                    i4 = a(bArr, i2, remaining);
                    if (i4 < 0) {
                        throw new IOException("Failed to partial read from buffer");
                    }
                    i3 -= i4;
                    this.f256a += i4;
                } else {
                    i4 = 0;
                }
            }
        } else {
            i4 = 0;
        }
        try {
            InputStream inputStream = this.f260e;
            if (inputStream != null) {
                i5 = inputStream.read(bArr, i2 + i4, i3);
            }
            if (i5 >= 0) {
                this.f256a += i5;
                return i5 + i4;
            } else if (i4 <= 0) {
                d();
                return i5;
            } else {
                return i4;
            }
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            InputStream inputStream = this.f260e;
            if (inputStream != null) {
                inputStream.close();
            }
            d();
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    private void a(Exception exc) {
        if (this.f257b.isComplete()) {
            return;
        }
        this.f257b.notifyStreamError(new c(this, this.f256a, exc));
    }

    @Override // java.io.InputStream
    public void mark(int i2) {
        if (markSupported()) {
            this.f260e.mark(i2);
        }
    }

    public void b() {
        int i2;
        ByteBuffer byteBuffer = this.f258c;
        if (byteBuffer == null || !byteBuffer.hasArray()) {
            return;
        }
        synchronized (this.f258c) {
            try {
                i2 = this.f260e.read(this.f258c.array(), 0, this.f258c.capacity());
            } catch (IOException e2) {
                f255g.error(e2.toString());
                i2 = 0;
            }
            if (i2 <= 0) {
                this.f258c.limit(0);
            } else if (i2 < this.f258c.capacity()) {
                this.f258c.limit(i2);
            }
        }
    }

    @Override // java.io.InputStream
    public int available() {
        try {
            return (this.f259d ? this.f258c.remaining() : 0) + this.f260e.available();
        } catch (IOException e2) {
            a(e2);
            throw e2;
        }
    }

    public g(InputStream inputStream) {
        this(inputStream, false);
    }

    @Override // java.io.InputStream
    public void reset() {
        if (markSupported()) {
            try {
                this.f260e.reset();
            } catch (IOException e2) {
                a(e2);
                throw e2;
            }
        }
    }

    private void d() {
        if (this.f257b.isComplete()) {
            return;
        }
        this.f257b.notifyStreamComplete(new c(this, this.f256a));
    }

    private int e() {
        if (c()) {
            return -1;
        }
        return this.f258c.get();
    }

    private int a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    private int a(byte[] bArr, int i2, int i3) {
        if (c()) {
            return -1;
        }
        int remaining = this.f258c.remaining();
        this.f258c.get(bArr, i2, i3);
        return remaining - this.f258c.remaining();
    }

    private boolean a(long j2) {
        return ((long) this.f258c.remaining()) >= j2;
    }

    public g(InputStream inputStream, boolean z) {
        this.f256a = 0L;
        this.f257b = new e();
        this.f260e = inputStream;
        this.f259d = z;
        this.f258c = !z ? null : ByteBuffer.allocate(2048);
        b();
    }

    @Override // com.bytedance.apm.agent.instrumentation.io.f
    public void removeStreamCompleteListener(d dVar) {
        this.f257b.removeStreamCompleteListener(dVar);
    }
}
