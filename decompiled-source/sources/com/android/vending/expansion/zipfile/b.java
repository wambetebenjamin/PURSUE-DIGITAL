package com.android.vending.expansion.zipfile;

import android.content.res.AssetFileDescriptor;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.UShort;
/* compiled from: ZipResourceFileV2.java */
/* loaded from: classes.dex */
public class b {
    static final int A = 10000;

    /* renamed from: a  reason: collision with root package name */
    static final String f12a = "zipro";

    /* renamed from: b  reason: collision with root package name */
    static final boolean f13b = false;

    /* renamed from: c  reason: collision with root package name */
    static final int f14c = 101010256;

    /* renamed from: d  reason: collision with root package name */
    static final int f15d = 22;

    /* renamed from: e  reason: collision with root package name */
    static final int f16e = 8;

    /* renamed from: f  reason: collision with root package name */
    static final int f17f = 12;

    /* renamed from: g  reason: collision with root package name */
    static final int f18g = 16;

    /* renamed from: h  reason: collision with root package name */
    static final int f19h = 65535;

    /* renamed from: i  reason: collision with root package name */
    static final int f20i = 65557;

    /* renamed from: j  reason: collision with root package name */
    static final int f21j = 67324752;
    static final int k = 30;
    static final int l = 26;
    static final int m = 28;
    static final int n = 33639248;
    static final int o = 46;
    static final int p = 10;
    static final int q = 12;
    static final int r = 16;
    static final int s = 20;
    static final int t = 24;
    static final int u = 28;
    static final int v = 30;
    static final int w = 32;
    static final int x = 42;
    static final int y = 0;
    static final int z = 8;
    private HashMap<String, a> D = new HashMap<>();
    public HashMap<File, ZipFile> B = new HashMap<>();
    ByteBuffer C = ByteBuffer.allocate(4);

    private static int a(int i2) {
        return ((i2 & 255) << 24) + ((65280 & i2) << 8) + ((16711680 & i2) >>> 8) + ((i2 >>> 24) & 255);
    }

    private static int a(short s2) {
        return ((s2 & 65280) >>> 8) | ((s2 & 255) << 8);
    }

    /* compiled from: ZipResourceFileV2.java */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public final File f22a;

        /* renamed from: b  reason: collision with root package name */
        public final String f23b;

        /* renamed from: c  reason: collision with root package name */
        public final String f24c;

        /* renamed from: d  reason: collision with root package name */
        public long f25d;

        /* renamed from: e  reason: collision with root package name */
        public int f26e;

        /* renamed from: f  reason: collision with root package name */
        public long f27f;

        /* renamed from: g  reason: collision with root package name */
        public long f28g;

        /* renamed from: h  reason: collision with root package name */
        public long f29h;

        /* renamed from: i  reason: collision with root package name */
        public long f30i;

        /* renamed from: j  reason: collision with root package name */
        public long f31j = -1;

        public a(String str, File file, String str2) {
            this.f23b = str2;
            this.f24c = str;
            this.f22a = file;
        }

        public void a(RandomAccessFile randomAccessFile, ByteBuffer byteBuffer) {
            long j2 = this.f25d;
            try {
                randomAccessFile.seek(j2);
                randomAccessFile.readFully(byteBuffer.array());
                if (byteBuffer.getInt(0) != b.f21j) {
                    Log.w(b.f12a, "didn't find signature at start of lfh");
                    throw new IOException();
                }
                this.f31j = j2 + 30 + (byteBuffer.getShort(26) & UShort.MAX_VALUE) + (byteBuffer.getShort(28) & UShort.MAX_VALUE);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }

        public long a() {
            return this.f31j;
        }

        public boolean b() {
            return this.f26e == 0;
        }

        public AssetFileDescriptor c() {
            if (this.f26e == 0) {
                try {
                    return new AssetFileDescriptor(ParcelFileDescriptor.open(this.f22a, DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP), a(), this.f30i);
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
            return null;
        }

        public String d() {
            return this.f24c;
        }

        public File e() {
            return this.f22a;
        }
    }

    public b(String str) {
        d(str);
    }

    a[] a(String str) {
        Vector vector = new Vector();
        Collection<a> values = this.D.values();
        if (str == null) {
            str = "";
        }
        int length = str.length();
        for (a aVar : values) {
            if (aVar.f23b.startsWith(str) && -1 == aVar.f23b.indexOf(47, length)) {
                vector.add(aVar);
            }
        }
        return (a[]) vector.toArray(new a[vector.size()]);
    }

    public a[] a() {
        Collection<a> values = this.D.values();
        return (a[]) values.toArray(new a[values.size()]);
    }

    public AssetFileDescriptor b(String str) {
        a aVar = this.D.get(str);
        if (aVar != null) {
            return aVar.c();
        }
        return null;
    }

    public InputStream c(String str) {
        a aVar = this.D.get(str);
        if (aVar != null) {
            if (aVar.b()) {
                return aVar.c().createInputStream();
            }
            ZipFile zipFile = this.B.get(aVar.e());
            if (zipFile == null) {
                zipFile = new ZipFile(aVar.e(), 1);
                this.B.put(aVar.e(), zipFile);
            }
            ZipEntry entry = zipFile.getEntry(str);
            if (entry != null) {
                return zipFile.getInputStream(entry);
            }
            return null;
        }
        return null;
    }

    private static int a(RandomAccessFile randomAccessFile) {
        return a(randomAccessFile.readInt());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(String str) {
        String str2 = str;
        File file = new File(str2);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, DownloadFileUtils.MODE_READ);
        long length = randomAccessFile.length();
        if (length < 22) {
            throw new IOException();
        }
        long j2 = 65557 > length ? length : 65557L;
        randomAccessFile.seek(0L);
        int a2 = a(randomAccessFile);
        if (a2 == f14c) {
            Log.i(f12a, "Found Zip archive, but it looks empty");
            throw new IOException();
        } else if (a2 != f21j) {
            Log.v(f12a, "Not a Zip archive");
            throw new IOException();
        } else {
            randomAccessFile.seek(length - j2);
            ByteBuffer allocate = ByteBuffer.allocate((int) j2);
            byte[] array = allocate.array();
            randomAccessFile.readFully(array);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            int length2 = array.length - 22;
            while (length2 >= 0 && (array[length2] != 80 || allocate.getInt(length2) != f14c)) {
                length2--;
            }
            if (length2 < 0) {
                Log.d(f12a, "Zip: EOCD not found, " + str2 + " is not zip");
            }
            short s2 = allocate.getShort(length2 + 8);
            long j3 = allocate.getInt(length2 + 12) & 4294967295L;
            long j4 = allocate.getInt(length2 + 16) & 4294967295L;
            if (j4 + j3 > length) {
                Log.w(f12a, "bad offsets (dir " + j4 + ", size " + j3 + ", eocd " + length2 + ")");
                throw new IOException();
            } else if (s2 == 0) {
                Log.w(f12a, "empty archive?");
                throw new IOException();
            } else {
                MappedByteBuffer map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, j4, j3);
                map.order(ByteOrder.LITTLE_ENDIAN);
                short s3 = UShort.MAX_VALUE;
                byte[] bArr = new byte[65535];
                ByteBuffer allocate2 = ByteBuffer.allocate(30);
                allocate2.order(ByteOrder.LITTLE_ENDIAN);
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                while (i3 < s2) {
                    if (map.getInt(i4) != n) {
                        Log.w(f12a, "Missed a central dir sig (at " + i4 + ")");
                        throw new IOException();
                    }
                    int i5 = map.getShort(i4 + 28) & s3;
                    int i6 = map.getShort(i4 + 30) & s3;
                    int i7 = map.getShort(i4 + 32) & s3;
                    map.position(i4 + 46);
                    map.get(bArr, i2, i5);
                    map.position(i2);
                    String str3 = new String(bArr, i2, i5);
                    a aVar = new a(str2, file, str3);
                    aVar.f26e = map.getShort(i4 + 10) & UShort.MAX_VALUE;
                    aVar.f27f = map.getInt(i4 + 12) & 4294967295L;
                    aVar.f28g = map.getLong(i4 + 16) & 4294967295L;
                    aVar.f29h = map.getLong(i4 + 20) & 4294967295L;
                    aVar.f30i = map.getLong(i4 + 24) & 4294967295L;
                    aVar.f25d = map.getInt(i4 + 42) & 4294967295L;
                    allocate2.clear();
                    aVar.a(randomAccessFile, allocate2);
                    this.D.put(str3, aVar);
                    i4 += i5 + 46 + i6 + i7;
                    i3++;
                    str2 = str;
                    s3 = 65535;
                    file = file;
                    i2 = 0;
                }
            }
        }
    }
}
