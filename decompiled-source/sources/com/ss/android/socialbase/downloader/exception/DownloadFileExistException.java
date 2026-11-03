package com.ss.android.socialbase.downloader.exception;
/* loaded from: classes9.dex */
public class DownloadFileExistException extends BaseException {
    private String existTargetFileName;
    private String existTargetFilePath;

    public DownloadFileExistException(String str, String str2) {
        this.existTargetFilePath = str;
        this.existTargetFileName = str2;
    }

    public String getExistTargetFileName() {
        return this.existTargetFileName;
    }

    public String getExistTargetFilePath() {
        return this.existTargetFilePath;
    }
}
