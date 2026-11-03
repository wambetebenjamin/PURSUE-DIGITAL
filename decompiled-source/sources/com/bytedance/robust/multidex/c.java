package com.bytedance.robust.multidex;

import android.support.v4.media.session.PlaybackStateCompat;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import com.meituan.robust.PatchProxyResult;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;
import java.util.zip.ZipException;
/* compiled from: ZipUtil.java */
/* loaded from: classes2.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public static ChangeQuickRedirect f12147a = null;

    /* renamed from: b  reason: collision with root package name */
    private static final int f12148b = 22;

    /* renamed from: c  reason: collision with root package name */
    private static final int f12149c = 101010256;

    /* renamed from: d  reason: collision with root package name */
    private static final int f12150d = 16384;

    c() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long a(File file) throws IOException {
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{file}, null, f12147a, true, "3698533e87ebddfafeef394bcc6b9b88");
        if (proxy != null) {
            return ((Long) proxy.result).longValue();
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, DownloadFileUtils.MODE_READ);
        try {
            return a(randomAccessFile, a(randomAccessFile));
        } finally {
            randomAccessFile.close();
        }
    }

    static a a(RandomAccessFile randomAccessFile) throws IOException, ZipException {
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{randomAccessFile}, null, f12147a, true, "d8cbe5302d72e4b13e54fe428e77ac74");
        if (proxy != null) {
            return (a) proxy.result;
        }
        long length = randomAccessFile.length() - 22;
        if (length < 0) {
            throw new ZipException("File too short to be a zip file: " + randomAccessFile.length());
        }
        long j2 = length - PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        long j3 = j2 >= 0 ? j2 : 0L;
        int reverseBytes = Integer.reverseBytes(f12149c);
        do {
            randomAccessFile.seek(length);
            if (randomAccessFile.readInt() == reverseBytes) {
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                a aVar = new a();
                aVar.f12152b = Integer.reverseBytes(randomAccessFile.readInt()) & 4294967295L;
                aVar.f12151a = Integer.reverseBytes(randomAccessFile.readInt()) & 4294967295L;
                return aVar;
            }
            length--;
        } while (length >= j3);
        throw new ZipException("End Of Central Directory signature not found");
    }

    static long a(RandomAccessFile randomAccessFile, a aVar) throws IOException {
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{randomAccessFile, aVar}, null, f12147a, true, "a4e34cc0d67026b537edac56d55969f0");
        if (proxy != null) {
            return ((Long) proxy.result).longValue();
        }
        CRC32 crc32 = new CRC32();
        long j2 = aVar.f12152b;
        randomAccessFile.seek(aVar.f12151a);
        int min = (int) Math.min(16384L, j2);
        byte[] bArr = new byte[16384];
        while (true) {
            int read = randomAccessFile.read(bArr, 0, min);
            if (read == -1) {
                break;
            }
            crc32.update(bArr, 0, read);
            j2 -= read;
            if (j2 == 0) {
                break;
            }
            min = (int) Math.min(16384L, j2);
        }
        return crc32.getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ZipUtil.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        long f12151a;

        /* renamed from: b  reason: collision with root package name */
        long f12152b;

        a() {
        }
    }
}
