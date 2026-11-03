package com.ss.android.socialbase.downloader.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
/* loaded from: classes9.dex */
public class ErrorOpImpl implements IDownloadFileOp {
    public String extraMsg;

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public boolean canWrite() {
        return false;
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public boolean delete() {
        return false;
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public boolean exists() {
        return false;
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public String getAbsolutePath() {
        return "";
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public String getCanonicalPath() {
        return "";
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public File getFile() {
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public int getFileType() {
        return -1;
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public File getParentFile() {
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public String getPath() {
        return "";
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public boolean isDirectory() {
        return false;
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public long lastModified() {
        return 0L;
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public long length() {
        return 0L;
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public boolean mkdirs() {
        return false;
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public FileInputStream obtainInputStream() {
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public FileOutputStream obtainOutputStream() {
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public boolean renameTo(DownloadFile downloadFile) {
        return false;
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public boolean setLastModified(long j2) {
        return false;
    }

    public ErrorOpImpl(String str) {
        this.extraMsg = "";
        this.extraMsg = str;
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public String getExtraMsg() {
        return this.extraMsg;
    }
}
