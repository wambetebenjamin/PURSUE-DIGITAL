package com.ss.android.ttmd5;

import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import java.io.File;
import java.io.RandomAccessFile;
/* compiled from: FileRandomAccess.java */
/* loaded from: classes9.dex */
public class a implements b {

    /* renamed from: a  reason: collision with root package name */
    private final RandomAccessFile f18599a;

    public a(File file) {
        this.f18599a = new RandomAccessFile(file, DownloadFileUtils.MODE_READ);
    }

    @Override // com.ss.android.ttmd5.b
    public long length() {
        return this.f18599a.length();
    }

    @Override // com.ss.android.ttmd5.b
    public int read(byte[] bArr, int i2, int i3) {
        return this.f18599a.read(bArr, i2, i3);
    }

    @Override // com.ss.android.ttmd5.b
    public void seek(long j2, long j3) {
        this.f18599a.seek(j2);
    }

    @Override // com.ss.android.ttmd5.b
    public void close() {
        this.f18599a.close();
    }
}
