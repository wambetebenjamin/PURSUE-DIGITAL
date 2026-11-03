package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Objects;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class dr extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private final Enumeration f16709a;

    /* renamed from: b  reason: collision with root package name */
    private InputStream f16710b;

    public dr(Enumeration enumeration) throws IOException {
        this.f16709a = enumeration;
        a();
    }

    final void a() throws IOException {
        InputStream inputStream = this.f16710b;
        if (inputStream != null) {
            inputStream.close();
        }
        this.f16710b = this.f16709a.hasMoreElements() ? new FileInputStream((File) this.f16709a.nextElement()) : null;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        super.close();
        InputStream inputStream = this.f16710b;
        if (inputStream != null) {
            inputStream.close();
            this.f16710b = null;
        }
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        while (true) {
            InputStream inputStream = this.f16710b;
            if (inputStream == null) {
                return -1;
            }
            int read = inputStream.read();
            if (read != -1) {
                return read;
            }
            a();
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i2, int i3) throws IOException {
        if (this.f16710b == null) {
            return -1;
        }
        Objects.requireNonNull(bArr);
        if (i2 < 0 || i3 < 0 || i3 > bArr.length - i2) {
            throw new IndexOutOfBoundsException();
        }
        if (i3 != 0) {
            do {
                int read = this.f16710b.read(bArr, i2, i3);
                if (read > 0) {
                    return read;
                }
                a();
            } while (this.f16710b != null);
            return -1;
        }
        return 0;
    }
}
