package com.google.android.play.core.assetpacks;

import java.io.IOException;
import java.io.InputStream;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class bj extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private final InputStream f16509a;

    /* renamed from: b  reason: collision with root package name */
    private long f16510b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bj(InputStream inputStream, long j2) {
        this.f16509a = inputStream;
        this.f16510b = j2;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        super.close();
        this.f16509a.close();
        this.f16510b = 0L;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        long j2 = this.f16510b;
        if (j2 <= 0) {
            return -1;
        }
        this.f16510b = j2 - 1;
        return this.f16509a.read();
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i2, int i3) throws IOException {
        long j2 = this.f16510b;
        if (j2 <= 0) {
            return -1;
        }
        int read = this.f16509a.read(bArr, i2, (int) Math.min(i3, j2));
        if (read != -1) {
            this.f16510b -= read;
        }
        return read;
    }
}
