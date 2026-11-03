package com.google.android.play.core.assetpacks;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class bw extends FilterInputStream {

    /* renamed from: a  reason: collision with root package name */
    private final ds f16546a;

    /* renamed from: b  reason: collision with root package name */
    private byte[] f16547b;

    /* renamed from: c  reason: collision with root package name */
    private long f16548c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f16549d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f16550e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bw(InputStream inputStream) {
        super(inputStream);
        this.f16546a = new ds();
        this.f16547b = new byte[4096];
        this.f16549d = false;
        this.f16550e = false;
    }

    private final int e(byte[] bArr, int i2, int i3) throws IOException {
        return Math.max(0, super.read(bArr, i2, i3));
    }

    private final boolean f(int i2) throws IOException {
        int e2 = e(this.f16547b, 0, i2);
        if (e2 != i2) {
            int i3 = i2 - e2;
            if (e(this.f16547b, e2, i3) != i3) {
                this.f16546a.b(this.f16547b, 0, e2);
                return false;
            }
        }
        this.f16546a.b(this.f16547b, 0, i2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long a() {
        return this.f16548c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final eu b() throws IOException {
        byte[] bArr;
        if (this.f16548c <= 0) {
            if (!this.f16549d) {
            }
            return new bq(null, -1L, -1, false, false, null);
        }
        do {
            bArr = this.f16547b;
        } while (read(bArr, 0, bArr.length) != -1);
        if (!this.f16549d || this.f16550e) {
            return new bq(null, -1L, -1, false, false, null);
        }
        if (!f(30)) {
            this.f16549d = true;
            return this.f16546a.c();
        }
        eu c2 = this.f16546a.c();
        if (c2.d()) {
            this.f16550e = true;
            return c2;
        } else if (c2.b() != 4294967295L) {
            int a2 = this.f16546a.a() - 30;
            int length = this.f16547b.length;
            long j2 = a2;
            if (j2 > length) {
                do {
                    length += length;
                } while (length < j2);
                this.f16547b = Arrays.copyOf(this.f16547b, length);
            }
            if (!f(a2)) {
                this.f16549d = true;
                return this.f16546a.c();
            }
            eu c3 = this.f16546a.c();
            this.f16548c = c3.b();
            return c3;
        } else {
            throw new ck("Files bigger than 4GiB are not supported.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean c() {
        return this.f16550e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean d() {
        return this.f16549d;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i2, int i3) throws IOException {
        long j2 = this.f16548c;
        if (j2 <= 0 || this.f16549d) {
            return -1;
        }
        int e2 = e(bArr, i2, (int) Math.min(j2, i3));
        this.f16548c -= e2;
        if (e2 == 0) {
            this.f16549d = true;
            return 0;
        }
        return e2;
    }
}
