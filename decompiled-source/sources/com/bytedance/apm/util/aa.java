package com.bytedance.apm.util;

import android.text.TextUtils;
import com.bytedance.librarian.c;
import com.google.android.gms.stats.CodePackage;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Map;
/* compiled from: ReadElf.java */
/* loaded from: classes.dex */
public class aa {

    /* renamed from: a  reason: collision with root package name */
    private static final byte[] f1641a = {Byte.MAX_VALUE, 69, 76, 70};

    /* renamed from: b  reason: collision with root package name */
    private static final int f1642b = 16;

    /* renamed from: c  reason: collision with root package name */
    private static final int f1643c = 4;

    /* renamed from: d  reason: collision with root package name */
    private static final int f1644d = 5;

    /* renamed from: e  reason: collision with root package name */
    private static final int f1645e = 3;

    /* renamed from: f  reason: collision with root package name */
    private static final int f1646f = 8;

    /* renamed from: g  reason: collision with root package name */
    private static final int f1647g = 40;

    /* renamed from: h  reason: collision with root package name */
    private static final int f1648h = 62;

    /* renamed from: i  reason: collision with root package name */
    private static final int f1649i = 164;

    /* renamed from: j  reason: collision with root package name */
    private static final int f1650j = 183;
    private static final int k = 1;
    private static final int l = 2;
    private static final int m = 1;
    private static final int n = 2;
    private static final int o = 1;
    private static final long p = 1;
    private static final int q = 2;
    private static final int r = 3;
    private static final int s = 6;
    private static final int t = 11;
    private int A;
    private int B;
    private long C;
    private long D;
    private long E;
    private long F;
    private long G;
    private long H;
    private long I;

    /* renamed from: J  reason: collision with root package name */
    private long f1651J;
    private long K;
    private long L;
    private Map<String, a> M;
    private Map<String, a> N;
    private final String u;
    private final RandomAccessFile v;
    private final byte[] w = new byte[512];
    private int x;
    private boolean y;
    private boolean z;

    /* compiled from: ReadElf.java */
    /* loaded from: classes.dex */
    public static class a {
        public static final int STB_GLOBAL = 1;
        public static final int STB_HIPROC = 15;
        public static final int STB_LOCAL = 0;
        public static final int STB_LOPROC = 13;
        public static final int STB_WEAK = 2;
        public static final int STT_COMMON = 5;
        public static final int STT_FILE = 4;
        public static final int STT_FUNC = 2;
        public static final int STT_NOTYPE = 0;
        public static final int STT_OBJECT = 1;
        public static final int STT_SECTION = 3;
        public static final int STT_TLS = 6;
        public final int bind;
        public final String name;
        public final int type;

        a(String str, int i2) {
            this.name = str;
            this.bind = (i2 >> 4) & 15;
            this.type = i2 & 15;
        }

        public String toString() {
            return "Symbol[" + this.name + "," + a() + "," + b() + "]";
        }

        private String a() {
            int i2 = this.bind;
            return i2 != 0 ? i2 != 1 ? i2 != 2 ? "STB_??? (" + this.bind + ")" : "WEAK" : "GLOBAL" : "LOCAL";
        }

        private String b() {
            switch (this.type) {
                case 0:
                    return "NOTYPE";
                case 1:
                    return "OBJECT";
                case 2:
                    return "FUNC";
                case 3:
                    return "SECTION";
                case 4:
                    return "FILE";
                case 5:
                    return CodePackage.COMMON;
                case 6:
                    return "TLS";
                default:
                    return "STT_??? (" + this.type + ")";
            }
        }
    }

    public static aa read(File file) {
        return new aa(file);
    }

    private aa(File file) {
        this.u = file.getPath();
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, DownloadFileUtils.MODE_READ);
        this.v = randomAccessFile;
        if (randomAccessFile.length() < 16) {
            throw new IllegalArgumentException("Too small to be an ELF file: " + file);
        }
        a();
    }

    public void close() {
        try {
            this.v.close();
        } catch (IOException unused) {
        }
    }

    protected void finalize() {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    private void a() {
        this.v.seek(0L);
        this.v.readFully(this.w, 0, 16);
        byte[] bArr = this.w;
        byte b2 = bArr[0];
        byte[] bArr2 = f1641a;
        if (b2 != bArr2[0] || bArr[1] != bArr2[1] || bArr[2] != bArr2[2] || bArr[3] != bArr2[3]) {
            throw new IllegalArgumentException("Invalid ELF file: " + this.u);
        }
        byte b3 = bArr[4];
        if (b3 == 1) {
            this.B = 4;
        } else if (b3 == 2) {
            this.B = 8;
        } else {
            throw new IOException("Invalid ELF EI_CLASS: " + ((int) b3) + ": " + this.u);
        }
        byte b4 = bArr[5];
        this.x = b4;
        if (b4 != 1) {
            if (b4 == 2) {
                throw new IOException("Unsupported ELFDATA2MSB file: " + this.u);
            }
            throw new IOException("Invalid ELF EI_DATA: " + this.x + ": " + this.u);
        }
        this.A = b();
        int b5 = b();
        if (b5 != 3 && b5 != 62 && b5 != f1650j && b5 != 40 && b5 != 8 && b5 != f1649i) {
            throw new IOException("Invalid ELF e_machine: " + b5 + ": " + this.u);
        }
        if ((b5 == 3 && b3 != 1) || ((b5 == 62 && b3 != 2) || ((b5 == f1650j && b3 != 2) || ((b5 == 40 && b3 != 1) || (b5 == f1649i && b3 != 1))))) {
            throw new IOException("Invalid e_machine/EI_CLASS ELF combination: " + b5 + c.a.SEPARATOR + ((int) b3) + ": " + this.u);
        }
        long c2 = c();
        if (c2 != 1) {
            throw new IOException("Invalid e_version: " + c2 + ": " + this.u);
        }
        e();
        d();
        long d2 = d();
        c();
        b();
        b();
        b();
        a(d2, b(), b(), b());
    }

    private void a(long j2, int i2, int i3, int i4) {
        this.v.seek(j2 + (i4 * i3));
        c();
        long c2 = c();
        a(this.B);
        e();
        long d2 = d();
        long a2 = a(this.B);
        if (c2 == 3) {
            this.G = d2;
            this.H = a2;
        }
        for (int i5 = 0; i5 < i2; i5++) {
            if (i5 != i4) {
                this.v.seek(j2 + (i5 * i3));
                long c3 = c();
                long c4 = c();
                a(this.B);
                e();
                long d3 = d();
                long a3 = a(this.B);
                if (c4 == 2 || c4 == 11) {
                    String a4 = a(c3);
                    if (".symtab".equals(a4)) {
                        this.C = d3;
                        this.D = a3;
                    } else if (".dynsym".equals(a4)) {
                        this.E = d3;
                        this.F = a3;
                    }
                } else if (c4 == 3) {
                    String a5 = a(c3);
                    if (".strtab".equals(a5)) {
                        this.I = d3;
                        this.f1651J = a3;
                    } else if (".dynstr".equals(a5)) {
                        this.K = d3;
                        this.L = a3;
                    }
                } else if (c4 == 6) {
                    this.y = true;
                }
            }
        }
    }

    private String a(long j2) {
        long j3 = this.G;
        if (j3 == 0 || j2 < 0 || j2 >= this.H) {
            return null;
        }
        return b(j3 + j2);
    }

    private String a(long j2, long j3, long j4) {
        if (j2 == 0 || j4 < 0 || j4 >= j3) {
            return null;
        }
        return b(j2 + j4);
    }

    private int b() {
        return (int) a(2);
    }

    private long c() {
        return a(4);
    }

    private long d() {
        return a(this.B);
    }

    private long e() {
        return a(this.B);
    }

    private long a(int i2) {
        int i3 = 0;
        this.v.readFully(this.w, 0, i2);
        if (this.x == 1) {
            for (int i4 = i2 - 1; i4 >= 0; i4--) {
                i3 = (i3 << 8) | (this.w[i4] & 255);
            }
        } else {
            int i5 = i2 - 1;
            int i6 = 0;
            while (i3 <= i5) {
                i6 = (i6 << 8) | (this.w[i3] & 255);
                i3++;
            }
            i3 = i6;
        }
        return i3;
    }

    private String b(long j2) {
        byte[] bArr;
        long filePointer = this.v.getFilePointer();
        this.v.seek(j2);
        RandomAccessFile randomAccessFile = this.v;
        randomAccessFile.readFully(this.w, 0, (int) Math.min(bArr.length, randomAccessFile.length() - j2));
        this.v.seek(filePointer);
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.w;
            if (i2 >= bArr2.length) {
                return null;
            }
            if (bArr2[i2] == 0) {
                return new String(this.w, 0, i2);
            }
            i2++;
        }
    }

    private int f() {
        return this.v.read() & 255;
    }

    public static boolean hasFuncSymbolInFile(File file, String str) {
        boolean a2;
        aa read = read(file);
        if (Math.max(read.D, read.f1651J) <= 200000) {
            a2 = read.b(str);
        } else {
            a2 = read.a(str);
        }
        read.close();
        return a2;
    }

    private boolean a(String str) {
        int f2;
        this.v.seek(this.C);
        while (this.v.getFilePointer() < this.C + this.D) {
            long c2 = c();
            if (this.B == 8) {
                f2 = f();
                f();
                b();
                e();
                a(this.B);
            } else {
                e();
                c();
                f2 = f();
                f();
                b();
            }
            if (c2 != 0) {
                String a2 = a(this.I, this.f1651J, c2);
                if (TextUtils.equals(str, a2) && new a(a2, f2).type == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean b(String str) {
        int a2;
        HashSet<Long> hashSet = new HashSet();
        byte[] bArr = new byte[(int) Math.max(this.D, this.f1651J)];
        this.v.seek(this.C);
        this.v.readFully(bArr, 0, (int) this.D);
        int i2 = 0;
        while (i2 < this.D) {
            long b2 = b(bArr, i2);
            int i3 = i2 + 4;
            int i4 = this.B;
            if (i4 == 8) {
                a2 = a(bArr, i3);
                int i5 = this.B;
                i2 = i3 + 1 + i5 + 3 + i5;
            } else {
                int i6 = i3 + i4 + 4;
                a2 = a(bArr, i6);
                i2 = i6 + 1 + 3;
            }
            if (b2 != 0 && (a2 & 15) == 2) {
                hashSet.add(Long.valueOf(b2));
            }
        }
        this.v.seek(this.I);
        this.v.readFully(bArr, 0, (int) this.f1651J);
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        for (Long l2 : hashSet) {
            int i7 = 0;
            while (i7 < length) {
                int longValue = (int) (i7 + l2.longValue());
                if (longValue >= this.f1651J || bArr[longValue] != bytes[i7]) {
                    break;
                }
                i7++;
            }
            if (i7 == length && bArr[(int) (i7 + l2.longValue())] == 0) {
                return true;
            }
        }
        return false;
    }

    private int a(byte[] bArr, int i2) {
        return bArr[i2] & 255;
    }

    private long b(byte[] bArr, int i2) {
        return a(4, bArr, i2);
    }

    private long a(int i2, byte[] bArr, int i3) {
        int i4 = 0;
        if (this.x == 1) {
            for (int i5 = i2 - 1; i5 >= 0; i5--) {
                i4 = (bArr[i5 + i3] & 255) | (i4 << 8);
            }
        } else {
            int i6 = i2 - 1;
            int i7 = 0;
            while (i4 <= i6) {
                i7 = (i7 << 8) | (bArr[i4 + i3] & 255);
                i4++;
            }
            i4 = i7;
        }
        return i4;
    }
}
