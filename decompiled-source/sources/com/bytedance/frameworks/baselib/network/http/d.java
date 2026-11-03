package com.bytedance.frameworks.baselib.network.http;

import com.bytedance.retrofit2.client.SsCall;
import java.io.IOException;
import java.io.InputStream;
/* compiled from: InputStreamWrap.java */
/* loaded from: classes2.dex */
public class d extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private InputStream f7817a;

    /* renamed from: b  reason: collision with root package name */
    private SsCall f7818b;

    public d(InputStream inputStream, SsCall ssCall) {
        this.f7817a = inputStream;
        this.f7818b = ssCall;
    }

    public d() {
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        int read;
        try {
            InputStream inputStream = this.f7817a;
            if (inputStream != null) {
                read = inputStream.read(bArr);
            } else {
                read = super.read(bArr);
            }
            if (read == -1) {
                close();
            }
            return read;
        } catch (IOException e2) {
            throw e2;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        int read;
        try {
            InputStream inputStream = this.f7817a;
            if (inputStream != null) {
                read = inputStream.read(bArr, i2, i3);
            } else {
                read = super.read(bArr, i2, i3);
            }
            if (read == -1) {
                close();
            }
            return read;
        } catch (IOException e2) {
            throw e2;
        }
    }

    @Override // java.io.InputStream
    public int read() {
        try {
            InputStream inputStream = this.f7817a;
            int read = inputStream != null ? inputStream.read() : -1;
            if (read == -1) {
                close();
            }
            return read;
        } catch (IOException e2) {
            throw e2;
        }
    }

    @Override // java.io.InputStream
    public long skip(long j2) {
        InputStream inputStream = this.f7817a;
        if (inputStream != null) {
            return inputStream.skip(j2);
        }
        return super.skip(j2);
    }

    @Override // java.io.InputStream
    public int available() {
        InputStream inputStream = this.f7817a;
        if (inputStream != null) {
            return inputStream.available();
        }
        return super.available();
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i2) {
        InputStream inputStream = this.f7817a;
        if (inputStream != null) {
            inputStream.mark(i2);
        } else {
            super.mark(i2);
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
        InputStream inputStream = this.f7817a;
        if (inputStream != null) {
            inputStream.reset();
        } else {
            super.reset();
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        InputStream inputStream = this.f7817a;
        if (inputStream != null) {
            return inputStream.markSupported();
        }
        return super.markSupported();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            InputStream inputStream = this.f7817a;
            if (inputStream != null) {
                inputStream.close();
            }
            SsCall ssCall = this.f7818b;
            if (ssCall != null) {
                ssCall.cancel();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        super.close();
    }
}
