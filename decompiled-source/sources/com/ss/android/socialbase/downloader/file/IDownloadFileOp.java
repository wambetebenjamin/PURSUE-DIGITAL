package com.ss.android.socialbase.downloader.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public interface IDownloadFileOp {
    boolean canWrite();

    boolean delete();

    boolean exists();

    String getAbsolutePath();

    String getCanonicalPath();

    String getExtraMsg();

    File getFile();

    int getFileType();

    File getParentFile();

    String getPath();

    boolean isDirectory();

    long lastModified();

    long length();

    boolean mkdirs();

    FileInputStream obtainInputStream();

    FileOutputStream obtainOutputStream();

    boolean renameTo(DownloadFile downloadFile);

    boolean setLastModified(long j2);
}
