package com.facebook.soloader;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedByInterruptException;
import kotlin.UShort;
/* loaded from: classes8.dex */
public final class MinElf {
    public static final int DT_NEEDED = 1;
    public static final int DT_NULL = 0;
    public static final int DT_STRTAB = 5;
    public static final int ELF_MAGIC = 1179403647;
    public static final int PN_XNUM = 65535;
    public static final int PT_DYNAMIC = 2;
    public static final int PT_LOAD = 1;
    private static final String TAG = "MinElf";

    /* loaded from: classes8.dex */
    public enum ISA {
        NOT_SO("not_so"),
        X86("x86"),
        ARM("armeabi-v7a"),
        X86_64("x86_64"),
        AARCH64("arm64-v8a"),
        OTHERS("others");
        
        private final String value;

        ISA(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }

    public static String[] extract_DT_NEEDED(File file) throws IOException {
        ElfFileChannel elfFileChannel = new ElfFileChannel(file);
        try {
            String[] extract_DT_NEEDED = extract_DT_NEEDED(elfFileChannel);
            elfFileChannel.close();
            return extract_DT_NEEDED;
        } catch (Throwable th) {
            try {
                elfFileChannel.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static String[] extract_DT_NEEDED_with_retries(ElfFileChannel elfFileChannel) throws IOException {
        int i2 = 0;
        while (true) {
            try {
                return extract_DT_NEEDED_no_retries(elfFileChannel);
            } catch (ClosedByInterruptException e2) {
                i2++;
                if (i2 > 4) {
                    throw e2;
                }
                Thread.interrupted();
                Log.e(TAG, "retrying extract_DT_NEEDED due to ClosedByInterruptException", e2);
                elfFileChannel.openChannel();
            }
        }
    }

    public static String[] extract_DT_NEEDED(ElfByteChannel elfByteChannel) throws IOException {
        if (elfByteChannel instanceof ElfFileChannel) {
            return extract_DT_NEEDED_with_retries((ElfFileChannel) elfByteChannel);
        }
        return extract_DT_NEEDED_no_retries(elfByteChannel);
    }

    private static String[] extract_DT_NEEDED_no_retries(ElfByteChannel elfByteChannel) throws IOException {
        long j2;
        long j3;
        long j4;
        String str;
        long j5;
        String str2;
        long j6;
        long j7;
        long j8;
        long j9;
        long j10;
        long j11;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (getu32(elfByteChannel, allocate, 0L) != 1179403647) {
            throw new ElfError("file is not ELF: 0x" + Long.toHexString(j2));
        }
        boolean z = getu8(elfByteChannel, allocate, 4L) == 1;
        if (getu8(elfByteChannel, allocate, 5L) == 2) {
            allocate.order(ByteOrder.BIG_ENDIAN);
        }
        long j12 = z ? getu32(elfByteChannel, allocate, 28L) : get64(elfByteChannel, allocate, 32L);
        long j13 = z ? getu16(elfByteChannel, allocate, 44L) : getu16(elfByteChannel, allocate, 56L);
        int i2 = getu16(elfByteChannel, allocate, z ? 42L : 54L);
        if (j13 == 65535) {
            long j14 = z ? getu32(elfByteChannel, allocate, 32L) : get64(elfByteChannel, allocate, 40L);
            if (z) {
                j11 = getu32(elfByteChannel, allocate, j14 + 28);
            } else {
                j11 = getu32(elfByteChannel, allocate, j14 + 44);
            }
            j13 = j11;
        }
        long j15 = j12;
        long j16 = 0;
        while (true) {
            if (j16 >= j13) {
                j3 = 0;
                break;
            }
            if (z) {
                j10 = getu32(elfByteChannel, allocate, j15 + 0);
            } else {
                j10 = getu32(elfByteChannel, allocate, j15 + 0);
            }
            if (j10 != 2) {
                j15 += i2;
                j16++;
            } else if (z) {
                j3 = getu32(elfByteChannel, allocate, j15 + 4);
            } else {
                j3 = get64(elfByteChannel, allocate, j15 + 8);
            }
        }
        long j17 = 0;
        if (j3 == 0) {
            throw new ElfError("ELF file does not contain dynamic linking information");
        }
        long j18 = j3;
        long j19 = 0;
        int i3 = 0;
        while (true) {
            boolean z2 = z;
            long j20 = z ? getu32(elfByteChannel, allocate, j18 + j17) : get64(elfByteChannel, allocate, j18 + j17);
            long j21 = j3;
            String str3 = "malformed DT_NEEDED section";
            if (j20 != 1) {
                j4 = j20;
                if (j20 == 5) {
                    j19 = z2 ? getu32(elfByteChannel, allocate, j18 + 4) : get64(elfByteChannel, allocate, j18 + 8);
                }
            } else if (i3 == Integer.MAX_VALUE) {
                throw new ElfError("malformed DT_NEEDED section");
            } else {
                i3++;
                j4 = j20;
            }
            long j22 = 16;
            j18 += z2 ? 8L : 16L;
            j17 = 0;
            if (j4 != 0) {
                z = z2;
                j3 = j21;
            } else if (j19 == 0) {
                throw new ElfError("Dynamic section string-table not found");
            } else {
                int i4 = 0;
                while (true) {
                    if (i4 >= j13) {
                        str = str3;
                        j5 = 0;
                        break;
                    }
                    if (z2) {
                        j6 = getu32(elfByteChannel, allocate, j12 + j17);
                    } else {
                        j6 = getu32(elfByteChannel, allocate, j12 + j17);
                    }
                    if (j6 == 1) {
                        if (z2) {
                            j7 = getu32(elfByteChannel, allocate, j12 + 8);
                        } else {
                            j7 = get64(elfByteChannel, allocate, j12 + j22);
                        }
                        if (z2) {
                            str = str3;
                            j8 = getu32(elfByteChannel, allocate, j12 + 20);
                        } else {
                            str = str3;
                            j8 = get64(elfByteChannel, allocate, j12 + 40);
                        }
                        if (j7 <= j19 && j19 < j8 + j7) {
                            if (z2) {
                                j9 = getu32(elfByteChannel, allocate, j12 + 4);
                            } else {
                                j9 = get64(elfByteChannel, allocate, j12 + 8);
                            }
                            j5 = j9 + (j19 - j7);
                        }
                    } else {
                        str = str3;
                    }
                    j12 += i2;
                    i4++;
                    str3 = str;
                    j22 = 16;
                    j17 = 0;
                }
                long j23 = 0;
                if (j5 == 0) {
                    throw new ElfError("did not find file offset of DT_STRTAB table");
                }
                String[] strArr = new String[i3];
                long j24 = j21;
                int i5 = 0;
                while (true) {
                    long j25 = j24 + j23;
                    long j26 = z2 ? getu32(elfByteChannel, allocate, j25) : get64(elfByteChannel, allocate, j25);
                    if (j26 == 1) {
                        strArr[i5] = getSz(elfByteChannel, allocate, (z2 ? getu32(elfByteChannel, allocate, j24 + 4) : get64(elfByteChannel, allocate, j24 + 8)) + j5);
                        if (i5 == Integer.MAX_VALUE) {
                            throw new ElfError(str);
                        }
                        i5++;
                        str2 = str;
                    } else {
                        str2 = str;
                    }
                    j24 += z2 ? 8L : 16L;
                    if (j26 == 0) {
                        if (i5 == i3) {
                            return strArr;
                        }
                        throw new ElfError(str2);
                    }
                    str = str2;
                    j23 = 0;
                }
            }
        }
    }

    private static String getSz(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j2) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j3 = 1 + j2;
            short u8Var = getu8(elfByteChannel, byteBuffer, j2);
            if (u8Var != 0) {
                sb.append((char) u8Var);
                j2 = j3;
            } else {
                return sb.toString();
            }
        }
    }

    private static void read(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, int i2, long j2) throws IOException {
        int read;
        byteBuffer.position(0);
        byteBuffer.limit(i2);
        while (byteBuffer.remaining() > 0 && (read = elfByteChannel.read(byteBuffer, j2)) != -1) {
            j2 += read;
        }
        if (byteBuffer.remaining() > 0) {
            throw new ElfError("ELF file truncated");
        }
        byteBuffer.position(0);
    }

    private static long get64(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j2) throws IOException {
        read(elfByteChannel, byteBuffer, 8, j2);
        return byteBuffer.getLong();
    }

    private static long getu32(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j2) throws IOException {
        read(elfByteChannel, byteBuffer, 4, j2);
        return byteBuffer.getInt() & 4294967295L;
    }

    private static int getu16(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j2) throws IOException {
        read(elfByteChannel, byteBuffer, 2, j2);
        return byteBuffer.getShort() & UShort.MAX_VALUE;
    }

    private static short getu8(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j2) throws IOException {
        read(elfByteChannel, byteBuffer, 1, j2);
        return (short) (byteBuffer.get() & 255);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static class ElfError extends RuntimeException {
        ElfError(String str) {
            super(str);
        }
    }
}
