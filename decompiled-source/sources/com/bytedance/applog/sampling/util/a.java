package com.bytedance.applog.sampling.util;

import com.bytedance.ttgame.module.dynamic.api.DynamicPageState;
import com.google.common.primitives.Longs;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;
/* compiled from: CityHashUtils.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final long f3383a = -4348849565147123417L;

    /* renamed from: b  reason: collision with root package name */
    public static final long f3384b = -5435081209227447693L;

    /* renamed from: c  reason: collision with root package name */
    public static final long f3385c = -7286425919675154353L;

    /* renamed from: d  reason: collision with root package name */
    public static final long f3386d = -7070675565921424023L;

    /* renamed from: e  reason: collision with root package name */
    public static final int f3387e = -862048943;

    /* renamed from: f  reason: collision with root package name */
    public static final int f3388f = 461845907;

    /* renamed from: g  reason: collision with root package name */
    private static final boolean f3389g = !"little".equals(System.getProperty("sun.cpu.endian"));

    /* renamed from: h  reason: collision with root package name */
    private static a f3390h = new a();

    /* renamed from: i  reason: collision with root package name */
    private static long f3391i = Longs.MAX_POWER_OF_TWO;

    private int a(int i2) {
        int i3 = (i2 ^ (i2 >>> 16)) * (-2048144789);
        int i4 = (i3 ^ (i3 >>> 13)) * (-1028477387);
        return i4 ^ (i4 >>> 16);
    }

    static int a(int i2, int i3) {
        if (i3 == 0) {
            return i2;
        }
        return (i2 << (32 - i3)) | (i2 >>> i3);
    }

    private long a(long j2) {
        return j2 ^ (j2 >>> 47);
    }

    private long a(long j2, int i2) {
        if (i2 == 0) {
            return j2;
        }
        return (j2 << (64 - i2)) | (j2 >>> i2);
    }

    private long a(long j2, long j3, long j4) {
        long j5 = (j2 ^ j3) * j4;
        long j6 = ((j5 ^ (j5 >>> 47)) ^ j3) * j4;
        return (j6 ^ (j6 >>> 47)) * j4;
    }

    public static BigInteger a(String str) {
        if (str == null) {
            str = "NULL";
        }
        return a().c(str);
    }

    public int b(String str) {
        byte[] f2 = f(str);
        int length = f2.length;
        if (length <= 24) {
            return length <= 12 ? length <= 4 ? a(f2) : b(f2) : c(f2);
        }
        int i2 = length * f3387e;
        int i3 = 17;
        int a2 = a(d(f2, length - 4) * f3387e, 17);
        int i4 = f3388f;
        int i5 = a2 * f3388f;
        int a3 = a(d(f2, length - 8) * f3387e, 17) * f3388f;
        int a4 = a(d(f2, length - 16) * f3387e, 17) * f3388f;
        int a5 = a(d(f2, length - 12) * f3387e, 17) * f3388f;
        int a6 = a(d(f2, length - 20) * f3387e, 17) * f3388f;
        int a7 = (a(((a(i5 ^ length, 19) * 5) - 430675100) ^ a4, 19) * 5) - 430675100;
        int a8 = (a(((a(a3 ^ i2, 19) * 5) - 430675100) ^ a5, 19) * 5) - 430675100;
        int a9 = (a(i2 + a6, 19) * 5) - 430675100;
        int i6 = (length - 1) / 20;
        int i7 = 0;
        while (true) {
            int a10 = a(d(f2, i7) * f3387e, i3) * i4;
            int d2 = d(f2, i7 + 4);
            int a11 = a(d(f2, i7 + 8) * f3387e, i3) * i4;
            int a12 = a(d(f2, i7 + 12) * f3387e, i3) * i4;
            int d3 = d(f2, i7 + 16);
            int a13 = a(a9 + d2, 19) * f3387e;
            int reverseBytes = Integer.reverseBytes(((a(a8 + a11, 18) * 5) - 430675100) ^ d3) * 5;
            a8 = Integer.reverseBytes(((a(((a(a7 ^ a10, 18) * 5) - 430675100) ^ (a12 + d2), 19) * 5) - 430675100) + (d3 * 5));
            a7 = a13 + a10;
            i7 += 20;
            i6--;
            if (i6 == 0) {
                return a((a((a((a(a7 + (a(a(a8, 11) * f3387e, 17) * f3387e), 19) * 5) - 430675100, 17) * f3387e) + (a(a(reverseBytes, 11) * f3387e, 17) * f3387e), 19) * 5) - 430675100, 17) * f3387e;
            }
            a9 = reverseBytes;
            i3 = 17;
            i4 = f3388f;
        }
    }

    public BigInteger c(String str) {
        long e2 = e(str);
        if (e2 >= 0) {
            return BigInteger.valueOf(e2);
        }
        return BigInteger.valueOf(e2 & Long.MAX_VALUE).add(BigInteger.valueOf(Long.MAX_VALUE)).add(BigInteger.valueOf(1L));
    }

    private long e(String str) {
        byte[] f2 = f(str);
        int length = f2.length;
        if (length <= 32) {
            if (length <= 16) {
                return d(f2);
            }
            return e(f2);
        } else if (length <= 64) {
            return f(f2);
        } else {
            long c2 = c(f2, length - 40);
            long c3 = c(f2, length - 16) + c(f2, length - 56);
            long j2 = length;
            long a2 = a(c(f2, length - 48) + j2, c(f2, length - 24));
            b a3 = a(f2, length - 64, j2, a2);
            b a4 = a(f2, length - 32, c3 + f3384b, c2);
            long c4 = (c2 * f3384b) + c(f2, 0);
            int i2 = (length - 1) & (-64);
            b bVar = a4;
            int i3 = 0;
            int i4 = i2;
            long j3 = c4;
            while (true) {
                long a5 = a(j3 + c3 + a3.a() + c(f2, i3 + 8), 37) * f3384b;
                long a6 = a(c3 + a3.b() + c(f2, i3 + 48), 42) * f3384b;
                long b2 = a5 ^ bVar.b();
                long a7 = a6 + a3.a() + c(f2, i3 + 40);
                long a8 = a(bVar.a() + a2, 33) * f3384b;
                a3 = a(f2, i3, a3.b() * f3384b, b2 + bVar.a());
                bVar = a(f2, i3 + 32, a8 + bVar.b(), a7 + c(f2, i3 + 16));
                i3 += 64;
                i4 -= 64;
                if (i4 == 0) {
                    return a(a(a3.a(), bVar.a()) + (a(a7) * f3384b) + b2, a(a3.b(), bVar.b()) + a8);
                }
                j3 = a8;
                a2 = b2;
                c3 = a7;
            }
        }
    }

    public long a(String str, long j2, long j3) {
        return a(e(str) - j2, j3);
    }

    public long a(String str, long j2) {
        return a(str, f3385c, j2);
    }

    public b d(String str) {
        byte[] f2 = f(str);
        return f2.length >= 16 ? a(f2, 16, new b(c(f2, 0), c(f2, 8) + f3383a)) : a(f2, 0, new b(f3383a, f3384b));
    }

    public b a(String str, b bVar) {
        return a(f(str), 0, bVar);
    }

    private b a(byte[] bArr, int i2, b bVar) {
        long a2;
        long a3;
        b a4;
        b a5;
        int i3;
        int length = bArr.length - i2;
        if (length < 128) {
            return a(Arrays.copyOfRange(bArr, i2, bArr.length), bVar);
        }
        b bVar2 = new b(0L, 0L);
        b bVar3 = new b(0L, 0L);
        long a6 = bVar.a();
        long b2 = bVar.b();
        long j2 = length * f3384b;
        int i4 = length;
        bVar2.a((a(b2 ^ f3384b, 49) * f3384b) + c(bArr, i2));
        bVar2.b((a(bVar2.a(), 42) * f3384b) + c(bArr, i2 + 8));
        bVar3.a((a(b2 + j2, 35) * f3384b) + a6);
        bVar3.b(a(c(bArr, i2 + 88) + a6, 53) * f3384b);
        int i5 = i2;
        long j3 = b2;
        b bVar4 = bVar3;
        while (true) {
            long a7 = a(a6 + j3 + bVar2.a() + c(bArr, i5 + 8), 37) * f3384b;
            long a8 = a(j3 + bVar2.b() + c(bArr, i5 + 48), 42) * f3384b;
            long b3 = a7 ^ bVar4.b();
            long a9 = a8 + bVar2.a() + c(bArr, i5 + 40);
            long a10 = a(j2 + bVar4.a(), 33) * f3384b;
            b a11 = a(bArr, i5, bVar2.b() * f3384b, b3 + bVar4.a());
            b a12 = a(bArr, i5 + 32, a10 + bVar4.b(), a9 + c(bArr, i5 + 16));
            int i6 = i5 + 64;
            long a13 = a(a10 + a9 + a11.a() + c(bArr, i6 + 8), 37) * f3384b;
            long a14 = a(a9 + a11.b() + c(bArr, i6 + 48), 42) * f3384b;
            j2 = a13 ^ a12.b();
            a2 = a14 + a11.a() + c(bArr, i6 + 40);
            a3 = a(b3 + a12.a(), 33) * f3384b;
            a4 = a(bArr, i6, a11.b() * f3384b, j2 + a12.a());
            a5 = a(bArr, i6 + 32, a3 + a12.b(), a2 + c(bArr, i6 + 16));
            i5 = i6 + 64;
            i3 = i4 - 128;
            if (i3 < 128) {
                break;
            }
            bVar2 = a4;
            a6 = a3;
            j3 = a2;
            bVar4 = a5;
            i4 = i3;
        }
        long a15 = a(a4.a() + j2, 49);
        long j4 = f3383a;
        long j5 = a3 + (a15 * f3383a);
        long a16 = (a2 * f3383a) + a(a5.b(), 37);
        long a17 = (j2 * f3383a) + a(a5.a(), 27);
        a5.a(a5.a() * 9);
        a4.a(a4.a() * f3383a);
        int i7 = 0;
        long j6 = j5;
        while (i7 < i3) {
            int i8 = i7 + 32;
            a16 = (a(j6 + a16, 42) * j4) + a4.b();
            int i9 = (i5 + i3) - i8;
            a5.a(a5.a() + c(bArr, i9 + 16));
            long a18 = (j6 * f3383a) + a5.a();
            a17 += a5.b() + c(bArr, i9);
            a5.b(a5.b() + a4.a());
            a4 = a(bArr, i9, a4.a() + a17, a4.b());
            a4.a(a4.a() * f3383a);
            i7 = i8;
            j6 = a18;
            a5 = a5;
            i3 = i3;
            j4 = -4348849565147123417L;
        }
        b bVar5 = a5;
        long a19 = a(j6, a4.a());
        long a20 = a(a16 + a17, bVar5.a());
        return new b(a(a4.b() + a19, bVar5.b()) + a20, a(a19 + bVar5.b(), a20 + a4.b()));
    }

    private int a(byte[] bArr) {
        int length = bArr.length;
        int i2 = 9;
        int i3 = 0;
        for (byte b2 : bArr) {
            i3 = (i3 * f3387e) + b2;
            i2 ^= i3;
        }
        return a(b(i3, b(length, i2)));
    }

    private int b(byte[] bArr) {
        int length = bArr.length;
        int i2 = length * 5;
        return a(b(d(bArr, (length >>> 1) & 4) + 9, b(d(bArr, length - 4) + i2, b(d(bArr, 0) + length, i2))));
    }

    private int c(byte[] bArr) {
        int length = bArr.length;
        int i2 = length >>> 1;
        int d2 = d(bArr, i2 - 4);
        int d3 = d(bArr, 4);
        int d4 = d(bArr, length - 8);
        int d5 = d(bArr, i2);
        return a(b(d(bArr, length - 4), b(d(bArr, 0), b(d5, b(d4, b(d3, b(d2, length)))))));
    }

    private long d(byte[] bArr) {
        int length = bArr.length;
        if (length >= 8) {
            long j2 = (length * 2) + f3385c;
            long c2 = c(bArr, 0) + f3385c;
            long c3 = c(bArr, length - 8);
            return a((a(c3, 37) * j2) + c2, (a(c2, 25) + c3) * j2, j2);
        } else if (length >= 4) {
            return a(((d(bArr, 0) & 4294967295L) << 3) + length, 4294967295L & d(bArr, length - 4), (length * 2) + f3385c);
        } else if (length > 0) {
            return a(((length + ((bArr[length - 1] & 255) << 2)) * f3383a) ^ (((bArr[0] & 255) + ((bArr[length >>> 1] & 255) << 8)) * f3385c)) * f3385c;
        } else {
            return f3385c;
        }
    }

    private long e(byte[] bArr) {
        int length = bArr.length;
        long j2 = (length * 2) + f3385c;
        long c2 = c(bArr, 0) * f3384b;
        long c3 = c(bArr, 8);
        long c4 = c(bArr, length - 8) * j2;
        return a(a(c2 + c3, 43) + a(c4, 30) + (c(bArr, length - 16) * f3385c), c2 + a(c3 + f3385c, 18) + c4, j2);
    }

    private long f(byte[] bArr) {
        int length = bArr.length;
        long j2 = (length * 2) + f3385c;
        long c2 = c(bArr, 0) * f3385c;
        long c3 = c(bArr, 8);
        long c4 = c(bArr, length - 24);
        long c5 = c(bArr, length - 32);
        long c6 = c(bArr, 16) * f3385c;
        long c7 = c(bArr, 24) * 9;
        long c8 = c(bArr, length - 8);
        long c9 = c(bArr, length - 16) * j2;
        long j3 = c2 + c8;
        long j4 = (j3 ^ c5) + c7 + 1;
        long reverseBytes = Long.reverseBytes((a(j3, 43) + ((a(c3, 30) + c4) * 9) + j4) * j2) + c9;
        long j5 = c6 + c7;
        long a2 = a(j5, 42) + c4;
        long j6 = j5 + c4;
        return (a(((j6 + Long.reverseBytes(((a2 + j6) * j2) + ((Long.reverseBytes((j4 + reverseBytes) * j2) + c8) * j2)) + c3) * j2) + c5 + c9) * j2) + a2;
    }

    private long a(byte[] bArr, int i2) {
        C0044a c0044a = new C0044a(8, f3389g);
        long j2 = 0;
        while (c0044a.a()) {
            int b2 = c0044a.b();
            j2 |= (bArr[b2 + i2] & 255) << (b2 * 8);
        }
        return j2;
    }

    private int b(byte[] bArr, int i2) {
        C0044a c0044a = new C0044a(4, f3389g);
        int i3 = 0;
        while (c0044a.a()) {
            int b2 = c0044a.b();
            i3 |= (bArr[b2 + i2] & 255) << (b2 * 8);
        }
        return i3;
    }

    private long c(byte[] bArr, int i2) {
        return a(bArr, i2);
    }

    private int d(byte[] bArr, int i2) {
        return b(bArr, i2);
    }

    private long a(long j2, long j3) {
        return a(new b(j2, j3));
    }

    private long a(b bVar) {
        long a2 = (bVar.a() ^ bVar.b()) * f3386d;
        long b2 = ((a2 ^ (a2 >>> 47)) ^ bVar.b()) * f3386d;
        return (b2 ^ (b2 >>> 47)) * f3386d;
    }

    private int b(int i2, int i3) {
        return (a((a(i2 * f3387e, 17) * f3388f) ^ i3, 19) * 5) - 430675100;
    }

    private b a(long j2, long j3, long j4, long j5, long j6, long j7) {
        long j8 = j6 + j2;
        long j9 = j3 + j8 + j4;
        return new b(j9 + j5, a(j7 + j8 + j5, 21) + a(j9, 44) + j8);
    }

    private b a(byte[] bArr, int i2, long j2, long j3) {
        return a(c(bArr, i2), c(bArr, i2 + 8), c(bArr, i2 + 16), c(bArr, i2 + 24), j2, j3);
    }

    private b a(byte[] bArr, b bVar) {
        long j2;
        long j3;
        long j4;
        int length = bArr.length;
        long a2 = bVar.a();
        long b2 = bVar.b();
        int i2 = length - 16;
        if (i2 <= 0) {
            j2 = a(a2 * f3384b) * f3384b;
            j4 = (f3384b * b2) + d(bArr);
            j3 = a((length >= 8 ? c(bArr, 0) : j4) + j2);
        } else {
            long a3 = a(c(bArr, length - 8) + f3384b, a2);
            long a4 = a(length + b2, a3 + c(bArr, i2));
            j2 = a2 + a4;
            long j5 = a4;
            int i3 = 0;
            do {
                j2 = (j2 ^ (a(c(bArr, i3) * f3384b) * f3384b)) * f3384b;
                b2 ^= j2;
                a3 = (a3 ^ (a(c(bArr, i3 + 8) * f3384b) * f3384b)) * f3384b;
                j5 ^= a3;
                i3 += 16;
                i2 -= 16;
            } while (i2 > 0);
            j3 = j5;
            j4 = a3;
        }
        long a5 = a(j2, j4);
        long a6 = a(j3, b2);
        return new b(a5 ^ a6, a(a6, a5));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CityHashUtils.java */
    /* renamed from: com.bytedance.applog.sampling.util.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0044a {

        /* renamed from: a  reason: collision with root package name */
        private final int f3392a;

        /* renamed from: b  reason: collision with root package name */
        private final boolean f3393b;

        /* renamed from: c  reason: collision with root package name */
        private int f3394c;

        C0044a(int i2, boolean z) {
            this.f3392a = i2;
            this.f3393b = z;
        }

        boolean a() {
            return this.f3394c < this.f3392a;
        }

        int b() {
            if (!this.f3393b) {
                int i2 = this.f3394c;
                this.f3394c = i2 + 1;
                return i2;
            }
            int i3 = this.f3394c;
            this.f3394c = i3 + 1;
            return (this.f3392a - 1) - i3;
        }
    }

    private byte[] f(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static a a() {
        return f3390h;
    }

    public static int a(BigInteger bigInteger, int i2) {
        long j2 = i2;
        long longValue = bigInteger.mod(BigInteger.valueOf(j2)).longValue();
        if (longValue < 0) {
            longValue += j2;
        }
        return (int) longValue;
    }

    public static void a(String[] strArr) {
        System.out.println(a().e("a"));
        System.out.println(a().e("abcdefghijkl"));
        System.out.println(a().e("abcdefghijklmnopqrstuvwxyz"));
        System.out.println(a().e("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"));
        System.out.println(a().e("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"));
        System.out.println("=======");
        System.out.println(new BigInteger("15778675771769088146").add(new BigInteger("2668068301940463470")).toString());
        System.out.println(new BigInteger("12917804110809363939").add(new BigInteger("5528939962900187677")).toString());
        System.out.println(-1);
        System.out.println(new BigInteger("18446744073709551616").divide(new BigInteger(DynamicPageState.NOT_FOUND)));
        System.out.println(new BigInteger("12917804110809363939").mod(new BigInteger("16000")));
    }
}
