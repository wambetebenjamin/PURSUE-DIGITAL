package com.bytedance.crash.upload;

import com.bytedance.crash.util.m;
import com.bytedance.crash.util.y;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FilterOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;
/* compiled from: HttpOutputStream.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final OutputStream f7210a;

    /* renamed from: b  reason: collision with root package name */
    private final a f7211b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f7212c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(OutputStream outputStream, boolean z) {
        a aVar = new a(outputStream);
        this.f7211b = aVar;
        if (z) {
            this.f7210a = new c(aVar);
        } else {
            this.f7210a = new C0103b(aVar);
        }
        this.f7212c = z;
    }

    public void a(byte[] bArr) {
        this.f7210a.write(bArr);
    }

    public void a(String str) {
        this.f7210a.write(str.getBytes());
    }

    public void a(byte[] bArr, int i2, int i3) {
        this.f7210a.write(bArr, i2, i3);
    }

    public void a() {
        if (this.f7212c) {
            c cVar = (c) this.f7210a;
            cVar.b();
            cVar.a();
            return;
        }
        C0103b c0103b = (C0103b) this.f7210a;
        c0103b.flush();
        c0103b.a();
    }

    public long b() {
        return this.f7211b.a();
    }

    public void a(List<File> list) {
        m.a(this.f7210a, list);
    }

    /* compiled from: HttpOutputStream.java */
    /* loaded from: classes2.dex */
    static class a extends FilterOutputStream {

        /* renamed from: a  reason: collision with root package name */
        private long f7213a;

        public a(OutputStream outputStream) {
            super(outputStream);
            this.f7213a = 0L;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i2) {
            this.f7213a++;
            this.out.write(i2);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) {
            this.f7213a += i3;
            this.out.write(bArr, i2, i3);
        }

        long a() {
            return this.f7213a;
        }
    }

    /* compiled from: HttpOutputStream.java */
    /* loaded from: classes2.dex */
    static class c extends GZIPOutputStream {
        @Override // java.util.zip.DeflaterOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.util.zip.GZIPOutputStream, java.util.zip.DeflaterOutputStream
        public void finish() {
        }

        public c(OutputStream outputStream) {
            super(outputStream);
        }

        public void a() {
            super.close();
        }

        public void b() {
            super.finish();
        }
    }

    /* compiled from: HttpOutputStream.java */
    /* renamed from: com.bytedance.crash.upload.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    static class C0103b extends DataOutputStream {
        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        public C0103b(OutputStream outputStream) {
            super(outputStream);
        }

        public void a() {
            super.close();
        }
    }

    static byte[] b(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
        Deflater deflater = new Deflater();
        deflater.setInput(bArr);
        deflater.finish();
        byte[] bArr2 = new byte[8192];
        while (!deflater.finished()) {
            byteArrayOutputStream.write(bArr2, 0, deflater.deflate(bArr2));
        }
        deflater.end();
        return byteArrayOutputStream.toByteArray();
    }

    static byte[] c(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        try {
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                y.w(th);
                return null;
            } finally {
                gZIPOutputStream.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 != read) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                inputStream.close();
                try {
                    return byteArrayOutputStream.toByteArray();
                } finally {
                    com.bytedance.crash.util.k.a(byteArrayOutputStream);
                }
            }
        }
    }
}
