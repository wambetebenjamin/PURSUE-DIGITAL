package com.bytedance.frameworks.baselib.network.http.util;
/* compiled from: CharArrayBuffer.java */
/* loaded from: classes2.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private char[] f8316a;

    /* renamed from: b  reason: collision with root package name */
    private int f8317b;

    public c(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Buffer capacity may not be negative");
        }
        this.f8316a = new char[i2];
    }

    private void e(int i2) {
        char[] cArr = new char[Math.max(this.f8316a.length << 1, i2)];
        System.arraycopy(this.f8316a, 0, cArr, 0, this.f8317b);
        this.f8316a = cArr;
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
        int i5 = this.f8317b + i3;
        if (i5 > this.f8316a.length) {
            e(i5);
        }
        System.arraycopy(cArr, i2, this.f8316a, this.f8317b, i3);
        this.f8317b = i5;
    }

    public void a(String str) {
        if (str == null) {
            str = "null";
        }
        int length = str.length();
        int i2 = this.f8317b + length;
        if (i2 > this.f8316a.length) {
            e(i2);
        }
        str.getChars(0, length, this.f8316a, this.f8317b);
        this.f8317b = i2;
    }

    public void a(c cVar, int i2, int i3) {
        if (cVar == null) {
            return;
        }
        a(cVar.f8316a, i2, i3);
    }

    public void a(c cVar) {
        if (cVar == null) {
            return;
        }
        a(cVar.f8316a, 0, cVar.f8317b);
    }

    public void a(char c2) {
        int i2 = this.f8317b + 1;
        if (i2 > this.f8316a.length) {
            e(i2);
        }
        this.f8316a[this.f8317b] = c2;
        this.f8317b = i2;
    }

    public void a(byte[] bArr, int i2, int i3) {
        int i4;
        if (bArr == 0) {
            return;
        }
        if (i2 < 0 || i2 > bArr.length || i3 < 0 || (i4 = i2 + i3) < 0 || i4 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (i3 == 0) {
            return;
        }
        int i5 = this.f8317b;
        int i6 = i3 + i5;
        if (i6 > this.f8316a.length) {
            e(i6);
        }
        while (i5 < i6) {
            int i7 = bArr[i2];
            if (i7 < 0) {
                i7 += 256;
            }
            this.f8316a[i5] = (char) i7;
            i2++;
            i5++;
        }
        this.f8317b = i6;
    }

    public void a(b bVar, int i2, int i3) {
        if (bVar == null) {
            return;
        }
        a(bVar.e(), i2, i3);
    }

    public void a(Object obj) {
        a(String.valueOf(obj));
    }

    public void a() {
        this.f8317b = 0;
    }

    public char[] b() {
        int i2 = this.f8317b;
        char[] cArr = new char[i2];
        if (i2 > 0) {
            System.arraycopy(this.f8316a, 0, cArr, 0, i2);
        }
        return cArr;
    }

    public char a(int i2) {
        return this.f8316a[i2];
    }

    public char[] c() {
        return this.f8316a;
    }

    public int d() {
        return this.f8316a.length;
    }

    public int e() {
        return this.f8317b;
    }

    public void b(int i2) {
        int length = this.f8316a.length;
        int i3 = this.f8317b;
        if (i2 > length - i3) {
            e(i3 + i2);
        }
    }

    public void c(int i2) {
        if (i2 < 0 || i2 > this.f8316a.length) {
            throw new IndexOutOfBoundsException();
        }
        this.f8317b = i2;
    }

    public boolean f() {
        return this.f8317b == 0;
    }

    public boolean g() {
        return this.f8317b == this.f8316a.length;
    }

    public int a(int i2, int i3, int i4) {
        if (i3 < 0) {
            i3 = 0;
        }
        int i5 = this.f8317b;
        if (i4 > i5) {
            i4 = i5;
        }
        if (i3 > i4) {
            return -1;
        }
        while (i3 < i4) {
            if (this.f8316a[i3] == i2) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    public int d(int i2) {
        return a(i2, 0, this.f8317b);
    }

    public String a(int i2, int i3) {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i3 <= this.f8317b) {
            if (i2 > i3) {
                throw new IndexOutOfBoundsException();
            }
            return new String(this.f8316a, i2, i3 - i2);
        }
        throw new IndexOutOfBoundsException();
    }

    public String b(int i2, int i3) {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i3 <= this.f8317b) {
            if (i2 > i3) {
                throw new IndexOutOfBoundsException();
            }
            while (i2 < i3 && p.a(this.f8316a[i2])) {
                i2++;
            }
            while (i3 > i2 && p.a(this.f8316a[i3 - 1])) {
                i3--;
            }
            return new String(this.f8316a, i2, i3 - i2);
        }
        throw new IndexOutOfBoundsException();
    }

    public String toString() {
        return new String(this.f8316a, 0, this.f8317b);
    }
}
