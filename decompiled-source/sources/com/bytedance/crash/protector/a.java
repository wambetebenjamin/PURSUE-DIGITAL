package com.bytedance.crash.protector;
/* compiled from: BufferWriter.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f6984a;

    /* renamed from: b  reason: collision with root package name */
    private int f6985b;

    public a() {
        this(512);
    }

    public a(int i2) {
        this.f6984a = null;
        this.f6985b = 0;
        if (i2 > 0) {
            this.f6984a = new byte[i2];
        }
    }

    public byte[] a() {
        return this.f6984a;
    }

    public int b() {
        return this.f6984a.length - this.f6985b;
    }

    public int c() {
        return this.f6985b;
    }

    public void a(int i2) {
        this.f6985b = i2;
    }

    public void a(byte[] bArr) {
        a(bArr, 0, bArr.length);
    }

    public void a(byte[] bArr, int i2, int i3) {
        g(i3);
        System.arraycopy(bArr, i2, this.f6984a, this.f6985b, i3);
        this.f6985b += i3;
    }

    public void a(int i2, int i3) {
        int i4 = this.f6985b;
        this.f6985b = i2;
        d(i3);
        this.f6985b = i4;
    }

    public void a(long j2) {
        int i2;
        g(10);
        do {
            byte b2 = (byte) (127 & j2);
            j2 >>>= 7;
            i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
            if (i2 != 0) {
                b2 = (byte) (b2 | 128);
            }
            byte[] bArr = this.f6984a;
            int i3 = this.f6985b;
            this.f6985b = i3 + 1;
            bArr[i3] = b2;
        } while (i2 != 0);
    }

    public void a(String str) {
        b(str.getBytes());
    }

    public void b(byte[] bArr) {
        a(bArr.length);
        a(bArr);
        b(0);
    }

    public void b(int i2) {
        g(1);
        byte[] bArr = this.f6984a;
        int i3 = this.f6985b;
        this.f6985b = i3 + 1;
        bArr[i3] = (byte) (i2 & 255);
    }

    public void a(boolean z) {
        b(z ? 1 : 0);
    }

    public void c(int i2) {
        f(2);
        g(2);
        byte[] bArr = this.f6984a;
        int i3 = this.f6985b;
        int i4 = i3 + 1;
        this.f6985b = i4;
        bArr[i3] = (byte) (i2 & 255);
        this.f6985b = i4 + 1;
        bArr[i4] = (byte) ((i2 >>> 8) & 255);
    }

    public void d(int i2) {
        f(4);
        g(4);
        byte[] bArr = this.f6984a;
        int i3 = this.f6985b;
        int i4 = i3 + 1;
        this.f6985b = i4;
        bArr[i3] = (byte) (i2 & 255);
        int i5 = i4 + 1;
        this.f6985b = i5;
        bArr[i4] = (byte) ((i2 >>> 8) & 255);
        int i6 = i5 + 1;
        this.f6985b = i6;
        bArr[i5] = (byte) ((i2 >>> 16) & 255);
        this.f6985b = i6 + 1;
        bArr[i6] = (byte) ((i2 >>> 24) & 255);
    }

    public void b(long j2) {
        f(8);
        d((int) (j2 & 4294967295L));
        d((int) ((j2 >>> 32) & 4294967295L));
    }

    public void a(float f2) {
        d(Float.floatToIntBits(f2));
    }

    public void a(double d2) {
        b(Double.doubleToLongBits(d2));
    }

    public int e(int i2) {
        f(4);
        int c2 = c();
        d(i2);
        return c2;
    }

    public void f(int i2) {
        int i3;
        int i4 = ((((i3 + i2) - 1) / i2) * i2) - this.f6985b;
        g(i4);
        for (int i5 = 0; i5 < i4; i5++) {
            byte[] bArr = this.f6984a;
            int i6 = this.f6985b;
            this.f6985b = i6 + 1;
            bArr[i6] = 0;
        }
    }

    private void g(int i2) {
        if (b() < i2) {
            int length = this.f6984a.length;
            byte[] bArr = new byte[length + Math.max(length, i2)];
            System.arraycopy(this.f6984a, 0, bArr, 0, this.f6985b);
            this.f6984a = bArr;
        }
    }
}
