package androidx.multidex;

import android.support.v4.media.session.PlaybackStateCompat;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;
import java.util.zip.ZipException;
/* loaded from: classes.dex */
final class ZipUtil {
    private static final int BUFFER_SIZE = 16384;
    private static final int ENDHDR = 22;
    private static final int ENDSIG = 101010256;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class CentralDirectory {
        long offset;
        long size;

        CentralDirectory() {
        }
    }

    ZipUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getZipCrc(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, DownloadFileUtils.MODE_READ);
        try {
            return computeCrcOfCentralDir(randomAccessFile, findCentralDirectory(randomAccessFile));
        } finally {
            randomAccessFile.close();
        }
    }

    static CentralDirectory findCentralDirectory(RandomAccessFile randomAccessFile) throws IOException, ZipException {
        long length = randomAccessFile.length() - 22;
        if (length < 0) {
            throw new ZipException("File too short to be a zip file: " + randomAccessFile.length());
        }
        long j2 = length - PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        long j3 = j2 >= 0 ? j2 : 0L;
        int reverseBytes = Integer.reverseBytes(ENDSIG);
        do {
            randomAccessFile.seek(length);
            if (randomAccessFile.readInt() == reverseBytes) {
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                CentralDirectory centralDirectory = new CentralDirectory();
                centralDirectory.size = Integer.reverseBytes(randomAccessFile.readInt()) & 4294967295L;
                centralDirectory.offset = Integer.reverseBytes(randomAccessFile.readInt()) & 4294967295L;
                return centralDirectory;
            }
            length--;
        } while (length >= j3);
        throw new ZipException("End Of Central Directory signature not found");
    }

    static long computeCrcOfCentralDir(RandomAccessFile randomAccessFile, CentralDirectory centralDirectory) throws IOException {
        CRC32 crc32 = new CRC32();
        long j2 = centralDirectory.size;
        randomAccessFile.seek(centralDirectory.offset);
        byte[] bArr = new byte[16384];
        int read = randomAccessFile.read(bArr, 0, (int) Math.min(16384L, j2));
        while (read != -1) {
            crc32.update(bArr, 0, read);
            j2 -= read;
            if (j2 == 0) {
                break;
            }
            read = randomAccessFile.read(bArr, 0, (int) Math.min(16384L, j2));
        }
        return crc32.getValue();
    }
}
