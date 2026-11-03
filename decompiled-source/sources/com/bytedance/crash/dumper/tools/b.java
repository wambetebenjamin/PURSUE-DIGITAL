package com.bytedance.crash.dumper.tools;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CachedStringDumper.java */
/* loaded from: classes2.dex */
public class b extends com.bytedance.crash.dumper.tools.a {

    /* renamed from: b  reason: collision with root package name */
    private static final int f6605b = 1024;

    /* renamed from: c  reason: collision with root package name */
    private static final int f6606c = 128;

    /* renamed from: d  reason: collision with root package name */
    private static final char[] f6607d = {'-', '2', '1', '4', '7', '4', '8', '3', '6', '4', '8'};

    /* renamed from: e  reason: collision with root package name */
    private static final char[] f6608e = {'-', '9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '8'};

    /* renamed from: f  reason: collision with root package name */
    private static final int[] f6609f = {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};

    /* renamed from: g  reason: collision with root package name */
    private static final c f6610g = new c();

    /* renamed from: h  reason: collision with root package name */
    private static final C0093b f6611h = new C0093b();

    /* renamed from: i  reason: collision with root package name */
    private static final d f6612i = new d();

    /* renamed from: j  reason: collision with root package name */
    private final char[] f6613j;
    private int k;

    private static int b(long j2) {
        long j3 = 10;
        for (int i2 = 1; i2 < 19; i2++) {
            if (j2 < j3) {
                return i2;
            }
            j3 *= 10;
        }
        return 19;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(String str) {
        char[] cArr;
        int i2 = 1024;
        try {
            cArr = new char[1024];
        } catch (Throwable unused) {
            i2 = 128;
            cArr = new char[128];
        }
        this.f6613j = cArr;
        this.k = 0;
        this.f6604a = a(str, i2);
    }

    char[] c() {
        return this.f6613j;
    }

    int d() {
        return this.k;
    }

    void b(int i2) {
        this.k = i2;
    }

    void e() {
        com.bytedance.crash.dumper.tools.d dVar = this.f6604a;
        char[] cArr = this.f6613j;
        dVar.a(cArr, cArr.length);
    }

    private static int c(int i2) {
        for (int i3 = 0; i3 < 10; i3++) {
            if (i2 <= f6609f[i3]) {
                return i3 + 1;
            }
        }
        return 11;
    }

    private static void a(int i2, char[] cArr, int i3) {
        while (i2 >= 65536) {
            int i4 = i2 / 100;
            int i5 = i2 - (((i4 << 6) + (i4 << 5)) + (i4 << 2));
            int i6 = i5 / 10;
            int i7 = i3 - 1;
            cArr[i7] = (char) ((i5 - (i6 * 10)) + 48);
            i3 = i7 - 1;
            cArr[i3] = (char) (i6 + 48);
            i2 = i4;
        }
        while (i2 != 0) {
            int i8 = (52429 * i2) >>> 19;
            i3--;
            cArr[i3] = (char) ((i2 - ((i8 << 3) + (i8 << 1))) + 48);
            i2 = i8;
        }
    }

    private static void a(long j2, char[] cArr, int i2) {
        while (j2 > 2147483647L) {
            long j3 = j2 / 100;
            int i3 = (int) (j2 - (((j3 << 6) + (j3 << 5)) + (j3 << 2)));
            int i4 = i3 / 10;
            int i5 = i2 - 1;
            cArr[i5] = (char) ((i3 - (i4 * 10)) + 48);
            i2 = i5 - 1;
            cArr[i2] = (char) (i4 + 48);
            j2 = j3;
        }
        a((int) j2, cArr, i2);
    }

    private void b(char c2) {
        char[] cArr = this.f6613j;
        int i2 = this.k;
        cArr[i2] = c2;
        int i3 = i2 + 1;
        this.k = i3;
        if (i3 == cArr.length) {
            com.bytedance.crash.dumper.tools.d dVar = this.f6604a;
            char[] cArr2 = this.f6613j;
            dVar.a(cArr2, cArr2.length);
            this.k = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bytedance.crash.dumper.tools.a
    public void a(int i2) {
        if (i2 == Integer.MIN_VALUE) {
            a(f6607d);
            return;
        }
        if (i2 < 0) {
            i2 = -i2;
            b('-');
        }
        if (i2 < 100) {
            if (i2 < 10) {
                b((char) (i2 + 48));
                return;
            }
            int i3 = i2 / 10;
            b((char) (i3 + 48));
            b((char) ((i2 - (i3 * 10)) + 48));
            return;
        }
        int c2 = c(i2);
        if (this.k + c2 >= this.f6613j.length) {
            this.f6604a.a(this.f6613j, this.k);
            this.k = 0;
        }
        int i4 = this.k + c2;
        this.k = i4;
        a(i2, this.f6613j, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bytedance.crash.dumper.tools.a
    public void a(long j2) {
        if (j2 == Long.MIN_VALUE) {
            a(f6608e);
            return;
        }
        if (j2 < 0) {
            j2 = -j2;
            b('-');
        }
        if (j2 < 100) {
            if (j2 < 10) {
                b((char) (j2 + 48));
                return;
            }
            long j3 = j2 / 10;
            b((char) (j3 + 48));
            b((char) ((j2 - (10 * j3)) + 48));
            return;
        }
        int b2 = b(j2);
        if (this.k + b2 >= this.f6613j.length) {
            this.f6604a.a(this.f6613j, this.k);
            this.k = 0;
        }
        int i2 = this.k + b2;
        this.k = i2;
        a(j2, this.f6613j, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bytedance.crash.dumper.tools.a
    public void a(char c2) {
        b(c2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bytedance.crash.dumper.tools.a
    public void a() {
        if (this.k > 0) {
            this.f6604a.a(this.f6613j, this.k);
            this.k = 0;
        }
        this.f6604a.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bytedance.crash.dumper.tools.a
    public void b() {
        a();
        this.f6604a.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CachedStringDumper.java */
    /* loaded from: classes2.dex */
    public static abstract class a<A> {
        abstract int a(A a2);

        abstract void a(A a2, int i2, char[] cArr, int i3, int i4);

        a() {
        }

        void a(b bVar, A a2) {
            int i2;
            int a3 = a(a2);
            char[] c2 = bVar.c();
            int i3 = a3;
            int d2 = bVar.d();
            int i4 = 0;
            while (true) {
                i2 = i3 + d2;
                if (i2 < c2.length) {
                    break;
                }
                int length = c2.length - d2;
                a(a2, i4, c2, d2, length);
                bVar.e();
                if (d2 != 0) {
                    bVar.b(0);
                    d2 = 0;
                }
                i4 += length;
                i3 -= length;
            }
            if (i3 > 0) {
                a(a2, i4, c2, d2, i3);
                bVar.b(i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CachedStringDumper.java */
    /* loaded from: classes2.dex */
    public static class c extends a<char[]> {
        c() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.bytedance.crash.dumper.tools.b.a
        public void a(char[] cArr, int i2, char[] cArr2, int i3, int i4) {
            System.arraycopy(cArr, i2, cArr2, i3, i4);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.bytedance.crash.dumper.tools.b.a
        public int a(char[] cArr) {
            return cArr.length;
        }
    }

    /* compiled from: CachedStringDumper.java */
    /* renamed from: com.bytedance.crash.dumper.tools.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    static class C0093b extends a<byte[]> {
        C0093b() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.bytedance.crash.dumper.tools.b.a
        public void a(byte[] bArr, int i2, char[] cArr, int i3, int i4) {
            for (int i5 = 0; i5 < i4; i5++) {
                cArr[i3 + i5] = (char) bArr[i2 + i5];
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.bytedance.crash.dumper.tools.b.a
        public int a(byte[] bArr) {
            return bArr.length;
        }
    }

    /* compiled from: CachedStringDumper.java */
    /* loaded from: classes2.dex */
    static class d extends a<String> {
        d() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.bytedance.crash.dumper.tools.b.a
        public void a(String str, int i2, char[] cArr, int i3, int i4) {
            str.getChars(i2, i4 + i2, cArr, i3);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.bytedance.crash.dumper.tools.b.a
        public int a(String str) {
            return str.length();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bytedance.crash.dumper.tools.a
    public void a(char[] cArr) {
        f6610g.a(this, cArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bytedance.crash.dumper.tools.a
    public void a(byte[] bArr) {
        f6611h.a(this, bArr);
    }

    @Override // com.bytedance.crash.dumper.tools.a
    public void a(String str) {
        f6612i.a(this, str);
    }
}
