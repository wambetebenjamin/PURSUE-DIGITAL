package com.google.android.play.core.assetpacks;

import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.zip.ZipException;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class bt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static AssetLocation a(String str, String str2) throws IOException {
        Long l;
        int a2;
        com.google.android.play.core.assetpacks.internal.aj.b(str != null, "Attempted to get file location from a null apk path.");
        com.google.android.play.core.assetpacks.internal.aj.b(str2 != null, String.format("Attempted to get file location in apk %s with a null file path.", str));
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, DownloadFileUtils.MODE_READ);
        byte[] bArr = new byte[22];
        randomAccessFile.seek(randomAccessFile.length() - 22);
        randomAccessFile.readFully(bArr);
        bs b2 = br.b(bArr, 0) == 1347093766 ? b(bArr) : null;
        byte b3 = 5;
        if (b2 == null) {
            long length = randomAccessFile.length() - 22;
            int min = (int) Math.min(1024L, randomAccessFile.length());
            byte[] bArr2 = new byte[min];
            byte[] bArr3 = new byte[22];
            long j2 = length;
            loop0: while (true) {
                long j3 = length - 65536;
                long j4 = j3 < 0 ? 0L : j3;
                j2 = Math.max((j2 - min) + 3, j4);
                randomAccessFile.seek(j2);
                randomAccessFile.readFully(bArr2);
                int i2 = min - 4;
                while (i2 >= 0) {
                    byte b4 = bArr2[i2];
                    int i3 = b4 != b3 ? b4 != 6 ? b4 != 75 ? b4 != 80 ? -1 : 0 : 1 : 3 : 2;
                    if (i3 >= 0 && i2 >= i3 && br.b(bArr2, i2 - i3) == 1347093766) {
                        randomAccessFile.seek((j2 + i2) - i3);
                        randomAccessFile.readFully(bArr3);
                        b2 = b(bArr3);
                        break loop0;
                    }
                    i2 -= 4;
                    b3 = 5;
                }
                if (j2 == j4) {
                    throw new ZipException(String.format("End Of Central Directory signature not found in APK %s", str));
                }
                b3 = 5;
            }
        }
        long j5 = b2.f16542a;
        byte[] bytes = str2.getBytes("UTF-8");
        byte[] bArr4 = new byte[46];
        byte[] bArr5 = new byte[str2.length()];
        int i4 = 0;
        while (true) {
            if (i4 >= b2.f16543b) {
                l = null;
                break;
            }
            randomAccessFile.seek(j5);
            randomAccessFile.readFully(bArr4);
            int b5 = br.b(bArr4, 0);
            if (b5 == 1347092738) {
                randomAccessFile.seek(j5 + 28);
                if (br.a(bArr4, 28) == str2.length()) {
                    randomAccessFile.seek(46 + j5);
                    randomAccessFile.read(bArr5);
                    if (Arrays.equals(bArr5, bytes)) {
                        l = Long.valueOf(br.c(bArr4, 42));
                        break;
                    }
                }
                j5 += a2 + 46 + br.a(bArr4, 30) + br.a(bArr4, 32);
                i4++;
            } else {
                throw new ZipException(String.format("Missing central directory file header signature when looking for file %s in APK %s. Read %d entries out of %d. Found %d instead of the header signature %d.", str2, str, Integer.valueOf(i4), Integer.valueOf(b2.f16543b), Integer.valueOf(b5), 1347092738));
            }
        }
        if (l == null) {
            return null;
        }
        long longValue = l.longValue();
        byte[] bArr6 = new byte[8];
        randomAccessFile.seek(22 + longValue);
        randomAccessFile.readFully(bArr6);
        return new bl(str, longValue + 30 + br.a(bArr6, 4) + br.a(bArr6, 6), br.c(bArr6, 0));
    }

    private static bs b(byte[] bArr) {
        int a2 = br.a(bArr, 10);
        return new bs(br.c(bArr, 16), br.c(bArr, 12), a2);
    }
}
