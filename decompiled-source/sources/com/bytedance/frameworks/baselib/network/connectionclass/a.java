package com.bytedance.frameworks.baselib.network.connectionclass;

import com.bytedance.librarian.c;
import java.util.NoSuchElementException;
/* compiled from: ByteArrayScanner.java */
/* loaded from: classes2.dex */
class a {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f7468a;

    /* renamed from: b  reason: collision with root package name */
    private int f7469b;

    /* renamed from: c  reason: collision with root package name */
    private int f7470c;

    /* renamed from: d  reason: collision with root package name */
    private char f7471d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f7472e;

    a() {
    }

    public a a(byte[] bArr, int i2) {
        this.f7468a = bArr;
        this.f7469b = 0;
        this.f7470c = i2;
        this.f7472e = false;
        return this;
    }

    public a a(char c2) {
        d();
        this.f7471d = c2;
        this.f7472e = true;
        return this;
    }

    private void d() {
        if (this.f7468a == null) {
            throw new IllegalStateException("Must call reset first");
        }
    }

    private void e() {
        if (!this.f7472e) {
            throw new IllegalStateException("Must call useDelimiter first");
        }
    }

    public String a() {
        d();
        e();
        return new String(this.f7468a, this.f7469b, f());
    }

    public boolean a(String str) {
        int i2 = this.f7469b;
        if (str.length() != f()) {
            return false;
        }
        for (int i3 = 0; i3 < str.length(); i3++) {
            if (str.charAt(i3) != this.f7468a[i2]) {
                return false;
            }
            i2++;
        }
        return true;
    }

    public int b() {
        d();
        e();
        int i2 = this.f7469b;
        return a(this.f7468a, i2, f() + i2);
    }

    public void c() {
        d();
        e();
        f();
    }

    private int f() {
        d();
        e();
        int i2 = this.f7470c;
        int i3 = this.f7469b;
        if (i2 <= i3) {
            throw new NoSuchElementException("Reading past end of input stream at " + this.f7469b + c.a.DOT);
        }
        int a2 = a(this.f7468a, i3, i2, this.f7471d);
        if (a2 == -1) {
            int i4 = this.f7470c;
            int i5 = i4 - this.f7469b;
            this.f7469b = i4;
            return i5;
        }
        int i6 = a2 - this.f7469b;
        this.f7469b = a2 + 1;
        return i6;
    }

    private static int a(byte[] bArr, int i2, int i3) {
        int i4 = 0;
        while (i2 < i3) {
            int i5 = i2 + 1;
            int i6 = bArr[i2] - 48;
            if (i6 < 0 || i6 > 9) {
                throw new NumberFormatException("Invalid int in buffer at " + (i5 - 1) + c.a.DOT);
            }
            i4 = (i4 * 10) + i6;
            i2 = i5;
        }
        return i4;
    }

    private static int a(byte[] bArr, int i2, int i3, char c2) {
        while (i2 < i3) {
            if (bArr[i2] == c2) {
                return i2;
            }
            i2++;
        }
        return -1;
    }
}
