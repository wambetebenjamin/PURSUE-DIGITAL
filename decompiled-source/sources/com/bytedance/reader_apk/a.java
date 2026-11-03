package com.bytedance.reader_apk;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.Map;
/* compiled from: ApkUtil.java */
/* loaded from: classes2.dex */
public final class a {
    public static final int ANDROID_COMMON_PAGE_ALIGNMENT_BYTES = 4096;
    public static final int APK_SIGNATURE_SCHEME_V2_BLOCK_ID = 1896449818;
    public static final long APK_SIG_BLOCK_MAGIC_HI = 3617552046287187010L;
    public static final long APK_SIG_BLOCK_MAGIC_LO = 2334950737559900225L;
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final int VERITY_PADDING_BLOCK_ID = 1114793335;

    /* renamed from: a  reason: collision with root package name */
    private static final int f12088a = 32;

    /* renamed from: b  reason: collision with root package name */
    private static final int f12089b = 22;

    /* renamed from: c  reason: collision with root package name */
    private static final int f12090c = 101010256;

    /* renamed from: d  reason: collision with root package name */
    private static final int f12091d = 65535;

    /* renamed from: e  reason: collision with root package name */
    private static final int f12092e = 20;

    private a() {
    }

    public static long getCommentLength(FileChannel fileChannel) {
        long size = fileChannel.size();
        if (size < 22) {
            throw new IOException("APK too small for ZIP End of Central Directory (EOCD) record");
        }
        long j2 = size - 22;
        long min = Math.min(j2, 65535L);
        int i2 = 0;
        while (true) {
            long j3 = i2;
            if (j3 <= min) {
                long j4 = j2 - j3;
                ByteBuffer allocate = ByteBuffer.allocate(4);
                fileChannel.position(j4);
                fileChannel.read(allocate);
                allocate.order(ByteOrder.LITTLE_ENDIAN);
                if (allocate.getInt(0) == f12090c) {
                    ByteBuffer allocate2 = ByteBuffer.allocate(2);
                    fileChannel.position(j4 + 20);
                    fileChannel.read(allocate2);
                    allocate2.order(ByteOrder.LITTLE_ENDIAN);
                    short s = allocate2.getShort(0);
                    if (s == i2) {
                        return s;
                    }
                }
                i2++;
            } else {
                throw new IOException("ZIP End of Central Directory (EOCD) record not found");
            }
        }
    }

    public static long findCentralDirStartOffset(FileChannel fileChannel) {
        return findCentralDirStartOffset(fileChannel, getCommentLength(fileChannel));
    }

    public static long findCentralDirStartOffset(FileChannel fileChannel, long j2) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        fileChannel.position((fileChannel.size() - j2) - 6);
        fileChannel.read(allocate);
        long j3 = allocate.getInt(0);
        if (j3 < 0) {
            fileChannel.read(allocate);
            byte[] bArr = new byte[8];
            for (int i2 = 4; i2 < 8; i2++) {
                bArr[i2] = allocate.get((8 - i2) - 1);
            }
            return ByteBuffer.wrap(bArr).getLong();
        }
        return j3;
    }

    public static c<ByteBuffer, Long> findApkSigningBlock(FileChannel fileChannel) {
        return findApkSigningBlock(fileChannel, findCentralDirStartOffset(fileChannel));
    }

    public static c<ByteBuffer, Long> findApkSigningBlock(FileChannel fileChannel, long j2) {
        if (j2 < 32) {
            throw new f("APK too small for APK Signing Block. ZIP Central Directory offset: " + j2);
        }
        fileChannel.position(j2 - 24);
        ByteBuffer allocate = ByteBuffer.allocate(24);
        fileChannel.read(allocate);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (allocate.getLong(8) != APK_SIG_BLOCK_MAGIC_LO || allocate.getLong(16) != APK_SIG_BLOCK_MAGIC_HI) {
            throw new f("No APK Signing Block before ZIP Central Directory");
        }
        long j3 = allocate.getLong(0);
        if (j3 < allocate.capacity() || j3 > 2147483639) {
            throw new f("APK Signing Block size out of range: " + j3);
        }
        int i2 = (int) (8 + j3);
        long j4 = j2 - i2;
        if (j4 < 0) {
            throw new f("APK Signing Block offset out of range: " + j4);
        }
        fileChannel.position(j4);
        ByteBuffer allocate2 = ByteBuffer.allocate(i2);
        fileChannel.read(allocate2);
        allocate2.order(ByteOrder.LITTLE_ENDIAN);
        long j5 = allocate2.getLong(0);
        if (j5 != j3) {
            throw new f("APK Signing Block sizes in header and footer do not match: " + j5 + " vs " + j3);
        }
        return c.of(allocate2, Long.valueOf(j4));
    }

    public static Map<Integer, ByteBuffer> findIdValues(ByteBuffer byteBuffer) {
        a(byteBuffer);
        ByteBuffer a2 = a(byteBuffer, 8, byteBuffer.capacity() - 24);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i2 = 0;
        while (a2.hasRemaining()) {
            i2++;
            if (a2.remaining() < 8) {
                throw new f("Insufficient data to read size of APK Signing Block entry #" + i2);
            }
            long j2 = a2.getLong();
            if (j2 < 4 || j2 > 2147483647L) {
                throw new f("APK Signing Block entry #" + i2 + " size out of range: " + j2);
            }
            int i3 = (int) j2;
            int position = a2.position() + i3;
            if (i3 > a2.remaining()) {
                throw new f("APK Signing Block entry #" + i2 + " size out of range: " + i3 + ", available: " + a2.remaining());
            }
            linkedHashMap.put(Integer.valueOf(a2.getInt()), a(a2, i3 - 4));
            a2.position(position);
        }
        return linkedHashMap;
    }

    private static ByteBuffer a(ByteBuffer byteBuffer, int i2, int i3) {
        if (i2 >= 0) {
            if (i3 < i2) {
                throw new IllegalArgumentException("end < start: " + i3 + " < " + i2);
            }
            int capacity = byteBuffer.capacity();
            if (i3 > byteBuffer.capacity()) {
                throw new IllegalArgumentException("end > capacity: " + i3 + " > " + capacity);
            }
            int limit = byteBuffer.limit();
            int position = byteBuffer.position();
            try {
                byteBuffer.position(0);
                byteBuffer.limit(i3);
                byteBuffer.position(i2);
                ByteBuffer slice = byteBuffer.slice();
                slice.order(byteBuffer.order());
                return slice;
            } finally {
                byteBuffer.position(0);
                byteBuffer.limit(limit);
                byteBuffer.position(position);
            }
        }
        throw new IllegalArgumentException("start: " + i2);
    }

    private static ByteBuffer a(ByteBuffer byteBuffer, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("size: " + i2);
        }
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        int i3 = i2 + position;
        if (i3 < position || i3 > limit) {
            throw new BufferUnderflowException();
        }
        byteBuffer.limit(i3);
        try {
            ByteBuffer slice = byteBuffer.slice();
            slice.order(byteBuffer.order());
            byteBuffer.position(i3);
            return slice;
        } finally {
            byteBuffer.limit(limit);
        }
    }

    private static void a(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }
}
