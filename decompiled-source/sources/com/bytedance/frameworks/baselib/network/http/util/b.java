package com.bytedance.frameworks.baselib.network.http.util;
/* compiled from: ByteArrayBuffer.java */
/* loaded from: classes2.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f8314a;

    /* renamed from: b  reason: collision with root package name */
    private int f8315b;

    public b(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Buffer capacity may not be negative");
        }
        this.f8314a = new byte[i2];
    }

    private void d(int i2) {
        byte[] bArr = new byte[Math.max(this.f8314a.length << 1, i2)];
        System.arraycopy(this.f8314a, 0, bArr, 0, this.f8315b);
        this.f8314a = bArr;
    }

    public void a(byte[] bArr, int i2, int i3) {
        int i4;
        if (bArr == null) {
            return;
        }
        if (i2 < 0 || i2 > bArr.length || i3 < 0 || (i4 = i2 + i3) < 0 || i4 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (i3 == 0) {
            return;
        }
        int i5 = this.f8315b + i3;
        if (i5 > this.f8314a.length) {
            d(i5);
        }
        System.arraycopy(bArr, i2, this.f8314a, this.f8315b, i3);
        this.f8315b = i5;
    }

    public void a(int i2) {
        int i3 = this.f8315b + 1;
        if (i3 > this.f8314a.length) {
            d(i3);
        }
        this.f8314a[this.f8315b] = (byte) i2;
        this.f8315b = i3;
    }

    public void a(char[] cArr, int i2, int i3) {
        int i4;
        if (cArr == null) {
            return;
        }
        if (i2 < 0 || i2 > cArr.length || i3 < 0 || (i4 = i2 + i3) < 0 || i4 > cArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (i3 == 0) {
            return;
        }
        int i5 = this.f8315b;
        int i6 = i3 + i5;
        if (i6 > this.f8314a.length) {
            d(i6);
        }
        while (i5 < i6) {
            this.f8314a[i5] = (byte) cArr[i2];
            i2++;
            i5++;
        }
        this.f8315b = i6;
    }

    public void a(c cVar, int i2, int i3) {
        if (cVar == null) {
            return;
        }
        a(cVar.c(), i2, i3);
    }

    public void a() {
        this.f8315b = 0;
    }

    public byte[] b() {
        int i2 = this.f8315b;
        byte[] bArr = new byte[i2];
        if (i2 > 0) {
            System.arraycopy(this.f8314a, 0, bArr, 0, i2);
        }
        return bArr;
    }

    public int b(int i2) {
        return this.f8314a[i2];
    }

    public int c() {
        return this.f8314a.length;
    }

    public int d() {
        return this.f8315b;
    }

    public byte[] e() {
        return this.f8314a;
    }

    public void c(int i2) {
        if (i2 < 0 || i2 > this.f8314a.length) {
            throw new IndexOutOfBoundsException();
        }
        this.f8315b = i2;
    }

    public boolean f() {
        return this.f8315b == 0;
    }

    public boolean g() {
        return this.f8315b == this.f8314a.length;
    }
}
