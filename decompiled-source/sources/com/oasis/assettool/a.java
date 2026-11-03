package com.oasis.assettool;

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
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.UShort;
/* loaded from: classes9.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    HashMap<String, C0316a> f17477a = new HashMap<>();

    /* renamed from: b  reason: collision with root package name */
    public HashMap<File, ZipFile> f17478b = new HashMap<>();

    /* renamed from: c  reason: collision with root package name */
    ByteBuffer f17479c = ByteBuffer.allocate(4);

    /* renamed from: com.oasis.assettool.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static final class C0316a {

        /* renamed from: a  reason: collision with root package name */
        public final File f17480a;

        /* renamed from: b  reason: collision with root package name */
        public final String f17481b;

        /* renamed from: c  reason: collision with root package name */
        public final String f17482c;

        /* renamed from: d  reason: collision with root package name */
        public long f17483d;

        /* renamed from: e  reason: collision with root package name */
        public int f17484e;

        /* renamed from: f  reason: collision with root package name */
        public long f17485f;

        /* renamed from: g  reason: collision with root package name */
        public long f17486g;

        /* renamed from: h  reason: collision with root package name */
        public long f17487h;

        /* renamed from: i  reason: collision with root package name */
        public long f17488i;

        /* renamed from: j  reason: collision with root package name */
        public long f17489j = -1;

        public C0316a(String str, File file, String str2) {
            this.f17481b = str2;
            this.f17482c = str;
            this.f17480a = file;
        }

        public final boolean a() {
            return this.f17484e == 0;
        }

        public final AssetFileDescriptor b() {
            if (this.f17484e == 0) {
                try {
                    return new AssetFileDescriptor(ParcelFileDescriptor.open(this.f17480a, DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP), this.f17489j, this.f17488i);
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
            return null;
        }
    }

    public a(String str) {
        b(str);
    }

    private void b(String str) {
        File file;
        RandomAccessFile randomAccessFile;
        short s;
        byte[] bArr;
        short s2;
        String str2 = str;
        File file2 = new File(str2);
        RandomAccessFile randomAccessFile2 = new RandomAccessFile(file2, DownloadFileUtils.MODE_READ);
        long length = randomAccessFile2.length();
        if (length < 22) {
            throw new IOException();
        }
        long j2 = 65557 > length ? length : 65557L;
        randomAccessFile2.seek(0L);
        int readInt = randomAccessFile2.readInt();
        if (((readInt & 255) << 24) + ((65280 & readInt) << 8) + ((16711680 & readInt) >>> 8) + ((readInt >>> 24) & 255) == 101010256) {
            Log.i("zipro", "Found Zip archive, but it looks empty");
            throw new IOException();
        }
        randomAccessFile2.seek(length - j2);
        ByteBuffer allocate = ByteBuffer.allocate((int) j2);
        byte[] array = allocate.array();
        randomAccessFile2.readFully(array);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        int length2 = array.length - 22;
        while (length2 >= 0 && (array[length2] != 80 || allocate.getInt(length2) != 101010256)) {
            length2--;
        }
        if (length2 < 0) {
            Log.d("zipro", "Zip: EOCD not found, " + str2 + " is not zip");
        }
        short s3 = allocate.getShort(length2 + 8);
        long j3 = allocate.getInt(length2 + 12) & 4294967295L;
        long j4 = allocate.getInt(length2 + 16) & 4294967295L;
        if (j4 + j3 > length) {
            Log.w("zipro", "bad offsets (dir " + j4 + ", size " + j3 + ", eocd " + length2 + ")");
            throw new IOException();
        } else if (s3 == 0) {
            Log.w("zipro", "empty archive?");
            throw new IOException();
        } else {
            MappedByteBuffer map = randomAccessFile2.getChannel().map(FileChannel.MapMode.READ_ONLY, j4, j3);
            map.order(ByteOrder.LITTLE_ENDIAN);
            short s4 = UShort.MAX_VALUE;
            byte[] bArr2 = new byte[65535];
            ByteBuffer allocate2 = ByteBuffer.allocate(30);
            allocate2.order(ByteOrder.LITTLE_ENDIAN);
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i3 < s3) {
                if (map.getInt(i4) != 33639248) {
                    Log.w("zipro", "Missed a central dir sig (at " + i4 + ")");
                    throw new IOException();
                }
                int i5 = map.getShort(i4 + 28) & s4;
                int i6 = map.getShort(i4 + 30) & s4;
                int i7 = map.getShort(i4 + 32) & s4;
                map.position(i4 + 46);
                map.get(bArr2, i2, i5);
                map.position(i2);
                String str3 = new String(bArr2, i2, i5);
                C0316a c0316a = new C0316a(str2, file2, str3);
                c0316a.f17484e = map.getShort(i4 + 10) & UShort.MAX_VALUE;
                c0316a.f17485f = map.getInt(i4 + 12) & 4294967295L;
                c0316a.f17486g = map.getLong(i4 + 16) & 4294967295L;
                c0316a.f17487h = map.getLong(i4 + 20) & 4294967295L;
                c0316a.f17488i = map.getLong(i4 + 24) & 4294967295L;
                c0316a.f17483d = map.getInt(i4 + 42) & 4294967295L;
                allocate2.clear();
                long j5 = c0316a.f17483d;
                try {
                    randomAccessFile2.seek(j5);
                    file = file2;
                } catch (FileNotFoundException e2) {
                    e = e2;
                    file = file2;
                } catch (IOException e3) {
                    e = e3;
                    file = file2;
                }
                try {
                    randomAccessFile2.readFully(allocate2.array());
                    randomAccessFile = randomAccessFile2;
                    try {
                    } catch (FileNotFoundException e4) {
                        e = e4;
                        s = s3;
                        bArr = bArr2;
                        s2 = UShort.MAX_VALUE;
                        e.printStackTrace();
                        this.f17477a.put(str3, c0316a);
                        i4 += i5 + 46 + i6 + i7;
                        i3++;
                        str2 = str;
                        s4 = s2;
                        randomAccessFile2 = randomAccessFile;
                        file2 = file;
                        s3 = s;
                        bArr2 = bArr;
                        i2 = 0;
                    } catch (IOException e5) {
                        e = e5;
                        s = s3;
                        bArr = bArr2;
                        s2 = UShort.MAX_VALUE;
                        e.printStackTrace();
                        this.f17477a.put(str3, c0316a);
                        i4 += i5 + 46 + i6 + i7;
                        i3++;
                        str2 = str;
                        s4 = s2;
                        randomAccessFile2 = randomAccessFile;
                        file2 = file;
                        s3 = s;
                        bArr2 = bArr;
                        i2 = 0;
                    }
                } catch (FileNotFoundException e6) {
                    e = e6;
                    randomAccessFile = randomAccessFile2;
                    s = s3;
                    bArr = bArr2;
                    s2 = UShort.MAX_VALUE;
                    e.printStackTrace();
                    this.f17477a.put(str3, c0316a);
                    i4 += i5 + 46 + i6 + i7;
                    i3++;
                    str2 = str;
                    s4 = s2;
                    randomAccessFile2 = randomAccessFile;
                    file2 = file;
                    s3 = s;
                    bArr2 = bArr;
                    i2 = 0;
                } catch (IOException e7) {
                    e = e7;
                    randomAccessFile = randomAccessFile2;
                    s = s3;
                    bArr = bArr2;
                    s2 = UShort.MAX_VALUE;
                    e.printStackTrace();
                    this.f17477a.put(str3, c0316a);
                    i4 += i5 + 46 + i6 + i7;
                    i3++;
                    str2 = str;
                    s4 = s2;
                    randomAccessFile2 = randomAccessFile;
                    file2 = file;
                    s3 = s;
                    bArr2 = bArr;
                    i2 = 0;
                }
                if (allocate2.getInt(0) != 67324752) {
                    s = s3;
                    bArr = bArr2;
                    s2 = UShort.MAX_VALUE;
                    Log.w("zipro", "didn't find signature at start of lfh");
                    throw new IOException();
                    break;
                }
                int i8 = allocate2.getShort(26) & UShort.MAX_VALUE;
                short s5 = allocate2.getShort(28);
                s2 = UShort.MAX_VALUE;
                s = s3;
                bArr = bArr2;
                try {
                    c0316a.f17489j = j5 + 30 + i8 + (s5 & UShort.MAX_VALUE);
                } catch (FileNotFoundException e8) {
                    e = e8;
                    e.printStackTrace();
                    this.f17477a.put(str3, c0316a);
                    i4 += i5 + 46 + i6 + i7;
                    i3++;
                    str2 = str;
                    s4 = s2;
                    randomAccessFile2 = randomAccessFile;
                    file2 = file;
                    s3 = s;
                    bArr2 = bArr;
                    i2 = 0;
                } catch (IOException e9) {
                    e = e9;
                    e.printStackTrace();
                    this.f17477a.put(str3, c0316a);
                    i4 += i5 + 46 + i6 + i7;
                    i3++;
                    str2 = str;
                    s4 = s2;
                    randomAccessFile2 = randomAccessFile;
                    file2 = file;
                    s3 = s;
                    bArr2 = bArr;
                    i2 = 0;
                }
                this.f17477a.put(str3, c0316a);
                i4 += i5 + 46 + i6 + i7;
                i3++;
                str2 = str;
                s4 = s2;
                randomAccessFile2 = randomAccessFile;
                file2 = file;
                s3 = s;
                bArr2 = bArr;
                i2 = 0;
            }
        }
    }

    public final InputStream a(String str) {
        C0316a c0316a = this.f17477a.get(str);
        if (c0316a != null) {
            if (c0316a.a()) {
                return c0316a.b().createInputStream();
            }
            ZipFile zipFile = this.f17478b.get(c0316a.f17480a);
            if (zipFile == null) {
                zipFile = new ZipFile(c0316a.f17480a, 1);
                this.f17478b.put(c0316a.f17480a, zipFile);
            }
            ZipEntry entry = zipFile.getEntry(str);
            if (entry != null) {
                return zipFile.getInputStream(entry);
            }
            return null;
        }
        return null;
    }
}
