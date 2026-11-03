package com.ss.android.socialbase.downloader.file;

import android.net.Uri;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
/* loaded from: classes9.dex */
public class DownloadFile implements IDownloadFileOp {
    public static final int TYPE_AUDIO = 4;
    public static final int TYPE_DOWNLOADS = 5;
    public static final int TYPE_ERROR = -1;
    public static final int TYPE_FILE = 1;
    public static final int TYPE_IMAGE = 2;
    public static final int TYPE_VIDEO = 3;
    public IDownloadFileOp downloadFileOp;

    public DownloadFile(File file) {
        this.downloadFileOp = new FileOpImpl(file);
    }

    public DownloadFile(String str, String str2) {
        this(str, str2, true, true);
    }

    public DownloadFile(String str, String str2, boolean z, boolean z2) {
        int typeByUri = DownloadFileUtils.getTypeByUri(str);
        if (typeByUri == 1) {
            this.downloadFileOp = new FileOpImpl(str, str2, z);
            return;
        }
        try {
            Uri parse = Uri.parse(str);
            DownloadFileUtils.checkUri(parse, typeByUri);
            this.downloadFileOp = new MediaStoreOpImpl(parse, typeByUri, z2);
        } catch (Throwable th) {
            this.downloadFileOp = new ErrorOpImpl(th.toString());
            th.printStackTrace();
        }
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public long length() {
        return this.downloadFileOp.length();
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public boolean exists() {
        return this.downloadFileOp.exists();
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public boolean isDirectory() {
        return this.downloadFileOp.isDirectory();
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public boolean mkdirs() {
        return this.downloadFileOp.mkdirs();
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public boolean delete() {
        return this.downloadFileOp.delete();
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public String getPath() {
        return this.downloadFileOp.getPath();
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public boolean canWrite() {
        return this.downloadFileOp.canWrite();
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public File getFile() {
        return this.downloadFileOp.getFile();
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public boolean setLastModified(long j2) {
        return this.downloadFileOp.setLastModified(j2);
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public long lastModified() {
        return this.downloadFileOp.lastModified();
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public FileInputStream obtainInputStream() {
        return this.downloadFileOp.obtainInputStream();
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public FileOutputStream obtainOutputStream() {
        return this.downloadFileOp.obtainOutputStream();
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public File getParentFile() {
        return this.downloadFileOp.getParentFile();
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public String getAbsolutePath() {
        return this.downloadFileOp.getAbsolutePath();
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public String getCanonicalPath() {
        return this.downloadFileOp.getCanonicalPath();
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public boolean renameTo(DownloadFile downloadFile) {
        return this.downloadFileOp.renameTo(downloadFile);
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public int getFileType() {
        return this.downloadFileOp.getFileType();
    }

    public boolean isTypeFile() {
        return this.downloadFileOp.getFileType() == 1;
    }

    public boolean isTypeMediaStore() {
        int fileType = this.downloadFileOp.getFileType();
        return fileType == 2 || fileType == 3 || fileType == 4 || fileType == 5;
    }

    public boolean isTypeError() {
        return this.downloadFileOp.getFileType() == -1;
    }

    @Override // com.ss.android.socialbase.downloader.file.IDownloadFileOp
    public String getExtraMsg() {
        return this.downloadFileOp.getExtraMsg();
    }
}
