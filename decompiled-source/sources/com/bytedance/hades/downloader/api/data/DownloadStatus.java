package com.bytedance.hades.downloader.api.data;
/* loaded from: classes2.dex */
public class DownloadStatus {
    public static final int CANCELED = -4;
    public static final int CONNECTED = 3;
    public static final int FAILED = -1;
    public static final int IDLE = 0;
    public static final int PAUSED = -2;
    public static final int PREPARE = 1;
    public static final int PROGRESS = 4;
    public static final int START = 2;
    public static final int SUCCEED = -3;

    public static boolean isDownloadingStatus(int i2) {
        return i2 >= 1 && i2 <= 4;
    }
}
